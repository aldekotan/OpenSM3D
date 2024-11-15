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
	//Уровень уворотов для каждого врага, от 0 до 3. 
	//Сами вероятности см. в NPCShootPlayer

	public static byte[] enemyDodgeLevels = new byte[10];
	//Уровень опытности каждого добавленного на уровень противника (от 0 до 12)
	//По нему вычисляется количество получаемого опыта за убийство
	private static final byte[] botTypes = new byte[10];

	public static void loadBot(int botType, int botId) {
		if(RenderEngine.persWorld == null) {
			try {
				String path = "/gamedata/meshes/m3g/pers.m3g";
				
				Object3D[] objs = Loader.load(path);
				ModChanges.updateM3DModels(objs, path);

				for(int i = 0; i < objs.length; i++) {
					if(objs[i] instanceof World) {
						RenderEngine.persWorld = (World) objs[i];
						break;
					}
				}

			} catch(IOException e) {
				System.out.println("Error at loading model pers.m3g");
				e.printStackTrace();
			}
		}

		botTypes[botId] = (byte) botType;
		
		if(RenderEngine.roomBotGroups[botId] == null) {
			if(RenderEngine.roomBotGroups[0] == null) {
				RenderEngine.roomBotGroups[0] = new Group();

				for(int i = 0; i < 12; ++i) {
					Mesh bodyPart = (Mesh) RenderEngine.persWorld.find(i + 1);
					Mesh meshCopy = (Mesh) bodyPart.duplicate();
					
					RenderEngine.roomBotGroups[0].addChild(meshCopy);
				}

				for(int i = 30; i < 34; ++i) {
					Mesh bloodSpawnMesh = (Mesh) RenderEngine.persWorld.find(i);
					Mesh meshCopy = (Mesh) bloodSpawnMesh.duplicate();
					
					meshCopy.setRenderingEnable(false);
					RenderEngine.roomBotGroups[0].addChild(meshCopy);
				}

				Light flash = (Light) RenderEngine.persWorld.find(50);
				Light lightCopy = (Light) flash.duplicate();
				
				lightCopy.setRenderingEnable(false);
				RenderEngine.roomBotGroups[0].addChild(lightCopy);
			} else {
				RenderEngine.roomBotGroups[botId] = (Group) RenderEngine.roomBotGroups[0].duplicate();
			}

			RenderEngine.roomBotGroups[botId].setRenderingEnable(false);
			RenderEngine.gameWorld.addChild(RenderEngine.roomBotGroups[botId]);
		}
		
		setBotType(botType, botId);
		setBotAppearance(botType, botId);
	}

	private static void setBotType(int botType, int botId) {
		int weaponId;
		
		if(botType == 0) {
			//tutorial bandits
			weaponId = 22;
			enemyDodgeLevels[botId] = (byte) (botType - 0);
		} else if(botType >= 1 && botType < 4) {
			//bandit
			weaponId = botType + 22 - 1;
			enemyDodgeLevels[botId] = (byte) (botType - 0);
		} else if(botType >= 4 && botType < 7) {
			//killer
			weaponId = botType + 22 - 4;
			enemyDodgeLevels[botId] = (byte) (botType - 3);
		} else if(botType >= 7 && botType < 10) {
			//stalker
			weaponId = botType + 22 - 7;
			enemyDodgeLevels[botId] = (byte) (botType - 6);
		} else {
			//soldier
			weaponId = botType + 22 - 10;
			enemyDodgeLevels[botId] = (byte) (botType - 9);
		}

		Mesh weaponMesh = (Mesh) RenderEngine.persWorld.find(weaponId);
		Mesh weaponCopy = (Mesh) weaponMesh.duplicate();
		RenderEngine.roomBotGroups[botId].addChild(weaponCopy);
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
			Mesh bodyPart = (Mesh) RenderEngine.roomBotGroups[botId].find(i + 1);
			bodyPart.setAppearance(0, ap);
		}

	}

	public static byte getBotType(int botId) {
		return botTypes[botId];
	}

	public static void loadStaticBot(int botId, int persId) { 
		if(RenderEngine.roomBotGroups[0] != null) {	
			Group botMdlCopy = (Group) RenderEngine.roomBotGroups[0].duplicate();
			RenderEngine.staticBotMdlGroup[botId] = botMdlCopy;
		}

		RenderEngine.staticBotMdlGroup[botId].setRenderingEnable(true);
		RenderEngine.gameWorld.addChild(RenderEngine.staticBotMdlGroup[botId]);
		
		byte weaponId = RenderEngine.objectStaticBotWeaponId[persId];
		if(weaponId != 0) {
			Mesh weaponMesh = (Mesh) RenderEngine.persWorld.find(weaponId);
			Mesh weaponCopy = (Mesh) weaponMesh.duplicate();
			RenderEngine.staticBotMdlGroup[botId].addChild(weaponCopy);
		}

		setStaticBotAppearance(botId, persId);
		
		int time = RenderEngine.objectStaticBotAnimTime[persId] * 100;
		RenderEngine.staticBotMdlGroup[botId].animate(time);
	}

	private static void setStaticBotAppearance(int botId, int textureId) {
		Appearance ap = new Appearance();
		
		String textureName = RenderEngine.objectTextureName[textureId];
		ap.setTexture(0, ResourceLoader.getTexture(textureName));
		
		CompositingMode cm = new CompositingMode();
		cm.setBlending(CompositingMode.REPLACE);
		ap.setCompositingMode(cm);
		
		Material mat = new Material();
		ap.setMaterial(mat);

		for(int i = 0; i < 12; i++) {
			Mesh bodyPart = (Mesh) RenderEngine.staticBotMdlGroup[botId].find(i + 1);
			bodyPart.setAppearance(0, ap);
		}
	}
}
