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
	private static final int[] botTypes = new int[10];

	public static void loadBot(int botType, int botId) {
		if(RenderEngine.persWorld == null) {
			try {
				String path = "/pers.m3g";
				
				Object3D[] objs = Loader.load(path);
				ModChanges.updateM3DModels(objs, path);

				for(int i = 0; i < objs.length; ++i) {
					if(objs[i] instanceof World) {
						RenderEngine.persWorld = (World) objs[i];
						//break should be added?
					}
				}

			} catch(IOException e) {
				System.out.println("Error at loading model pers.m3g");
				e.printStackTrace();
			}
		}

		botTypes[botId] = botType;
		if(RenderEngine.botModelGroup[botId] != null) {
			setBotType(botType, botId);
			setBotAppearance(botType, botId);
		} else {
			if(RenderEngine.botModelGroup[0] == null) {
				RenderEngine.botModelGroup[0] = new Group();

				Mesh newMesh;
				byte var5;
				for(var5 = 0; var5 < 12; ++var5) {
					Mesh mesh = (Mesh) RenderEngine.persWorld.find(var5 + 1);
					newMesh = (Mesh) mesh.duplicate();
					RenderEngine.botModelGroup[0].addChild(newMesh);
					System.gc();
				}

				for(var5 = 30; var5 < 34; ++var5) {
					Mesh mesh = (Mesh) RenderEngine.persWorld.find(var5);
					(newMesh = (Mesh) mesh.duplicate()).setRenderingEnable(false);
					RenderEngine.botModelGroup[0].addChild(newMesh);
					mesh = null;
					System.gc();
				}

				Light light = (Light) RenderEngine.persWorld.find(50);
				Light newLight;
				(newLight = (Light) light.duplicate()).setRenderingEnable(false);
				RenderEngine.botModelGroup[0].addChild(newLight);
				System.gc();
			} else {
				RenderEngine.botModelGroup[botId] = (Group) RenderEngine.botModelGroup[0].duplicate();
			}

			RenderEngine.botModelGroup[botId].setRenderingEnable(false);
			RenderEngine.gameWorld.addChild(RenderEngine.botModelGroup[botId]);
			setBotType(botType, botId);
			setBotAppearance(botType, botId);
		}
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
		RenderEngine.botModelGroup[botId].addChild(weaponCopy);
	}

	private static void setBotAppearance(int expLevel, int botId) {
		String textureName;
		
		if(expLevel >= 0 && expLevel < 4) {
			textureName = "pbandit.png"; //бандит
		} else if(expLevel >= 4 && expLevel < 7) {
			textureName = "pkiller.png"; //наёмник
		} else if(expLevel >= 7 && expLevel < 10) {
			textureName = "pstalker.png"; //сталкер
		} else {
			textureName = "psoldier.png"; //солдат
		}

		Appearance ap = new Appearance();
		ap.setTexture(0, ResourceLoader.getTexture(textureName));
		
		CompositingMode cm = new CompositingMode();
		cm.setBlending(68);
		ap.setCompositingMode(cm);
		
		Material mat = new Material();
		ap.setMaterial(mat);

		for(int i = 0; i < 12; i++) {
			Mesh bodyPart = (Mesh) RenderEngine.botModelGroup[botId].find(i + 1);
			bodyPart.setAppearance(0, ap);
		}

	}

	public static int getBotType(int botId) {
		return botTypes[botId];
	}

	public static void loadStaticBot(byte botId, byte persId) {
		if(RenderEngine.botModelGroup[0] != null) {
			RenderEngine.stationaryPersonages[botId] = (Group) RenderEngine.botModelGroup[0].duplicate();
		}

		RenderEngine.stationaryPersonages[botId].setRenderingEnable(true);
		RenderEngine.gameWorld.addChild(RenderEngine.stationaryPersonages[botId]);
		byte userId = RenderEngine.objectPersId[persId];
		if(userId != 0) {
			Mesh mesh = (Mesh) RenderEngine.persWorld.find(userId);
			Mesh new_mesh = (Mesh) mesh.duplicate();
			RenderEngine.stationaryPersonages[botId].addChild(new_mesh);
		}

		setStaticBotAppearance(botId, persId);
		int time = RenderEngine.objectPersAnimTime[persId] * 100;
		RenderEngine.stationaryPersonages[botId].animate(time);

		System.gc();
	}

	private static void setStaticBotAppearance(byte botId, byte textureId) {
		CompositingMode cmps_mode = new CompositingMode();
		String textureName = RenderEngine.objectTextureName[textureId];
		Appearance aprnc;
		(aprnc = new Appearance()).setTexture(0, ResourceLoader.getTexture(textureName));
		cmps_mode.setBlending(68);
		Material new_mtrl = new Material();
		aprnc.setCompositingMode(cmps_mode);
		aprnc.setMaterial(new_mtrl);

		for(byte number = 0; number < 12; ++number) {
			((Mesh) RenderEngine.stationaryPersonages[botId].find(number + 1)).setAppearance(0, aprnc);
			System.gc();
		}
	}
}
