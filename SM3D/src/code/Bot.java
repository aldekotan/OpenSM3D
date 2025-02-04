package code;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Light;
import javax.microedition.m3g.Loader;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.World;

public final class Bot //Персонажи, модели, освещение, спрайты...
{
	//Вид оружия для каждого врага, от 0 до 3. 
	//Сами вероятности см. в NPCShootPlayer
	public static byte[] enemyWeaponType = new byte[10];
	//Тип каждого добавленного на уровень противника (от 0 до 12)
	//По нему вычисляется количество получаемого опыта за убийство
	private static final byte[] botTypes = new byte[10];

	public static void loadBot(int botType, int botId) {
		if(GameScene.persWorld == null) {
			try {
				String path = "/gamedata/meshes/m3g/pers.m3g";
				
				Object3D[] objs = Loader.load(path);
				ModChanges.updateM3DModels(objs, path);

				for(int i = 0; i < objs.length; i++) {
					if(objs[i] instanceof World) {
						GameScene.persWorld = (World) objs[i];
						break;
					}
				}

			} catch(IOException e) {
				System.out.println("Error at loading model pers.m3g");
				e.printStackTrace();
			}
		}

		botTypes[botId] = (byte) botType;
		
		if(GameScene.roomBotGroups[botId] == null) {
			if(GameScene.roomBotGroups[0] == null) {
				GameScene.roomBotGroups[0] = new Group();

				for(int i = 0; i < 12; ++i) {
					Mesh bodyPart = (Mesh) GameScene.persWorld.find(i + 1);
					Mesh meshCopy = (Mesh) bodyPart.duplicate();
					
					GameScene.roomBotGroups[0].addChild(meshCopy);
				}

				for(int i = 30; i < 34; ++i) {
					Mesh bloodSpawnMesh = (Mesh) GameScene.persWorld.find(i);
					Mesh meshCopy = (Mesh) bloodSpawnMesh.duplicate();
					
					meshCopy.setRenderingEnable(false);
					GameScene.roomBotGroups[0].addChild(meshCopy);
				}

				Light flash = (Light) GameScene.persWorld.find(50);
				Light lightCopy = (Light) flash.duplicate();
				
				lightCopy.setRenderingEnable(false);
				GameScene.roomBotGroups[0].addChild(lightCopy);
			} else {
				GameScene.roomBotGroups[botId] = (Group) GameScene.roomBotGroups[0].duplicate();
			}

			GameScene.roomBotGroups[botId].setRenderingEnable(false);
			GameScene.gameWorld.addChild(GameScene.roomBotGroups[botId]);
		}
		
		setBotType(botType, botId);
		setBotAppearance(botType, botId);
	}

	private static void setBotType(int botType, int botId) {
		int weaponId;
		
		//0 is minimal 12 is max
		if(botType == 0) {
			//tutorial bandits
			weaponId = 22;
			enemyWeaponType[botId] = (byte) (botType - 0);
		} else if(botType >= 1 && botType < 4) {
			//bandit
			weaponId = botType + 22 - 1;
			enemyWeaponType[botId] = (byte) (botType - 0);
		} else if(botType >= 4 && botType < 7) {
			//killer
			weaponId = botType + 22 - 4;
			enemyWeaponType[botId] = (byte) (botType - 3);
		} else if(botType >= 7 && botType < 10) {
			//stalker
			weaponId = botType + 22 - 7;
			enemyWeaponType[botId] = (byte) (botType - 6);
		} else {
			//soldier
			weaponId = botType + 22 - 10;
			enemyWeaponType[botId] = (byte) (botType - 9);
		}

		Mesh weaponMesh = (Mesh) GameScene.persWorld.find(weaponId);
		Mesh weaponCopy = (Mesh) weaponMesh.duplicate();
		GameScene.roomBotGroups[botId].addChild(weaponCopy);
	}

	private static void setBotAppearance(int botType, int botId) {
		String textureName;
		
		if(botType >= 0 && botType < 4) {
			textureName = "pbandit.png"; //бандит
		} else if(botType >= 4 && botType < 7) {
			textureName = "pkiller.png"; //наёмник
		} else if(botType >= 7 && botType < 10) {
			textureName = "pstalker.png"; //сталкер
		} else {
			textureName = "psoldier.png"; //солдат
		}

		Appearance ap = new Appearance();
		ap.setTexture(0, ResourceLoader.getTexture(textureName));
		
		CompositingMode cm = new CompositingMode();
		cm.setBlending(CompositingMode.REPLACE);
		ap.setCompositingMode(cm);
		
		Material mat = new Material();
		ap.setMaterial(mat);

		for(int i = 0; i < 12; i++) {
			Mesh bodyPart = (Mesh) GameScene.roomBotGroups[botId].find(i + 1);
			bodyPart.setAppearance(0, ap);
		}

	}

	public static byte getBotType(int botId) {
		return botTypes[botId];
	}

	public static void loadStaticBot(int botId, int persId) { 
		if(GameScene.roomBotGroups[0] != null) {	
			Group botMdlCopy = (Group) GameScene.roomBotGroups[0].duplicate();
			GameScene.staticBotMdlGroup[botId] = botMdlCopy;
		}

		GameScene.staticBotMdlGroup[botId].setRenderingEnable(true);
		GameScene.gameWorld.addChild(GameScene.staticBotMdlGroup[botId]);
		
		byte weaponId = GameScene.objectStaticBotWeaponId[persId];
		if(weaponId != 0) {
			Mesh weaponMesh = (Mesh) GameScene.persWorld.find(weaponId);
			Mesh weaponCopy = (Mesh) weaponMesh.duplicate();
			GameScene.staticBotMdlGroup[botId].addChild(weaponCopy);
		}

		setStaticBotAppearance(botId, persId);
		
		int time = GameScene.objectStaticBotAnimTime[persId] * 100;
		GameScene.staticBotMdlGroup[botId].animate(time);
	}

	private static void setStaticBotAppearance(int botId, int textureId) {
		Appearance ap = new Appearance();
		
		String textureName = GameScene.objectTextureName[textureId];
		ap.setTexture(0, ResourceLoader.getTexture(textureName));
		
		CompositingMode cm = new CompositingMode();
		cm.setBlending(CompositingMode.REPLACE);
		ap.setCompositingMode(cm);
		
		Material mat = new Material();
		ap.setMaterial(mat);

		for(int i = 0; i < 12; i++) {
			Mesh bodyPart = (Mesh) GameScene.staticBotMdlGroup[botId].find(i + 1);
			bodyPart.setAppearance(0, ap);
		}
	}
}
