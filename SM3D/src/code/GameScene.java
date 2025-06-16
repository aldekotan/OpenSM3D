package code;


import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.Background;
import javax.microedition.m3g.Camera;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Light;
import javax.microedition.m3g.Loader;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.Sprite3D;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.World;
import javax.microedition.m3g.Image2D;

public final class GameScene {
    
    public static final byte[] levelUseThirdperson;

    public static int currentLocation;
    public static int nextLocation;
    
    public static int currentRoom;
    public static int currentRoomPdawtf; //unused //remove?
    private static int nextRoom;
	
    public static boolean[] locationCompleted = new boolean[17];
    public static boolean[] locationTaskMark = new boolean[17];
    public static boolean[] locationCampMark = new boolean[17];
    public static boolean gamePaused;
    public static Graphics3D graphics3D;
    public static World gameWorld = new World();
    public static short currentGameState;
    public static short prevGameState;
    
    //Camera
    public static Camera camera;
    private static float[] cameraPos = new float[3];
    private static float[] prevCameraPos = new float[3]; //Used in cutscenes?
    public static int currentDoorId;
    private static float cameraXRot, cameraYRot;
    private static int cameraYRotOffset;
	
    private static Group playerModel;

    public static int roomsCount;
    private static float[][] roomSettings = new float[15][8];
    //[roomId][setting]
    
    //room center x [0]
    //unused room center y [1]
    //room center z [2]
    //?? unused [3]
    //?? unused [4]
    //ambient color [5]
    //ambient intensity [6]
    //background color [7]
    private static int backgroundColor; //todo remove?

    private static byte[] doorsCount = new byte[15];
    private static float[][][] doorSettings = new float[15][5][8];
    //[roomId][doorId][settings]
    
    //x [0]
    //y [1]
    //z [2]
    //next room id (0 is unusable) [3]
    //next room door id (0 is unusable) [4]
    //max camera rotation to left (0-180) [5]
    //max camera rotation to right (0-180) [6]
    //locked (0/1) [7]
    private static byte[][][] loadedDoorsSettings = new byte[15][5][2]; //useless? doorSettings can be used instead
    //[roomId][doorId][settings]
    //next room id (0 is unusable) [0]
    //next room door id (0 is unusable) [1]
    public static short[][] doorKeys = new short[15][5];
    //[roomId][doorId]
    //item to unlock door
    private static boolean[] doorUnlocked = new boolean[5];
    public static boolean[] doorIsUnderCursor;
    public static int openDoorIdUnderCursor;
    public static float[] doorYAngle, doorMaxXAngle, doorMinXAngle;
    private static int nextDoorId;
    
    private static int walkAnimStartTime;
    public static int walkCurrentDoorId;
    public static int walkNextDoorId;
    private static float walkPointTargetX;
    private static float walkPointTargetZ;
    private static int walkTimeToWalkPoint;
    private static int walkTimeToDoor;
    private static float walkStartYRot;
    private static float walkPointYRot;
	
    private static byte[] walkPointsCount = new byte[15];
    private static float[][][] walkPoints = new float[15][4][6];
    //[roomId][walkPoint][settings]
    
    //door 1 id [0]
	//door 2 id [1]
    //x [2]
    //z [3]
    //walk time to door 2 [4]
    //walk time to door 1 [5]
    
    private static byte[] bckMeshesCount = new byte[15];
    public static Mesh[] bckMeshes;
    public static Sprite3D[] bckSprites;
    public static boolean[] bckSpritesAnimEnabled;
    private static int[] bckSpritesW;
    private static int[] bckSpritesH;
    private static byte[] roomBckMeshesMdlId;
    private static float[][][] bckMeshSettings = new float[15][32][6];
    //[roomId][mesh][settings]
    //x [0]
    //y [1]
    //z [2]
    //rotation y[3]
    //model id [4]
    //scale [5]
    
    private static byte[] lightsCount = new byte[15];
    public static Light[] roomLights;
    private static Light ambientLight;
    private static int rotatingLightId;
    private static float[][][] lightSettings = new float[15][32][5];
    //[roomId][mesh][settings]
    //x [0]
    //y [1]
    //z [2]
    //rotation y [3]
    //light id [4]
    
    private static byte[] meshesCount = new byte[15];
    public static Mesh[] roomMeshes;
    private static float[][][] meshSettings = new float[15][32][8];
    //[roomId][mesh][settings]
    //x [0]
    //y [1]
    //z [2]
    //rotation x [3]
    //rotation y [4]
    //rotation z [5]
    //model id [6]
    //scale [7]
    
    private static byte[] activableObjsCount = new byte[15];
    public static Mesh[] activableObjMeshes;
    public static Group[] staticBotMdlGroup;
    private static float[][][] activableObjSettings = new float[15][10][8];
    //[roomId][obj][settings]
    //x [0]
    //y [1]
    //z [2]
    //rotation x [3] (was unused in m3g)
    //rotation y [4]
    //rotation z [5] (was unused in m3g)
    //model id [6]
    //activable object id (0 is unusable) [7]
    public static boolean[] activableObjIsUnderCursor;
    public static int activableObjIdUnderCursor;
    public static float[] activObjYAngle, activObjMaxXAngle, activObjMinXAngle;
    
    public static byte[] botsCount = new byte[15];
    private static byte botsAlive;
    public static byte botsKilledCount;
    public static Group[] roomBotGroups;
    private static float[][][] botSettings = new float[15][10][15];
    //[roomId][bot][settings]
    //x, z [0,1] [2,3] [4,5]
    //y [6]
    //rotation y [7]
    //door id [8]
    //is state 0 can be selected [9] (second x and z, crouching)
    //is state 1 can be selected [10] (second x and z, stading)
    //is state 2 can be selected [11] (first x and z, stading)
    //is state 3 can be selected [12] (third x and z, stading)
    //is state 4 can be selected [13] (third x and z, crouching)
    //bot type [14]
    public static boolean[][] botKilled = new boolean[15][10];
    //[roomId][bot]
    private static float[][] botPositions = new float[10][3];
    //[bot][x, y, z]
    public static int dyingBotId;
    private static int[] botDeadAnimEndTime;
    public static boolean[] botActive;
    public static boolean isBotDeathAnimFinished; //makes no sense todo remove ???
	
	//something bot related too
    private static int[] botsStateChangeTimePassed; 
    private static int botsStateChangeTime;
    private static float[][] botCenterStatePos; //used for hide/look out position interpolation??
	
    public static boolean[] botIsUnderCursor;
    public static float[] botYAngle, botMaxXAngle, botMinXAngle;
    public static int activeBotId;
    private static boolean activeBotCrouching;
    public static int botIdUndercursor; //not sure
    private static byte[] botCurrentPosState;
	
    public static boolean botIsShooting;
	
    private static Sprite3D bloodSprite;
    private static Sprite3D muzzleFlashSprite;
    
    public static byte[] itemsForKillingAllCount = new byte[15];
    public static short[][] itemsForKillingAll = new short[15][10];
    //[roomId]{items}
    
    public static byte[] damageZonesTypes = new byte[15];
    //[roomId]
    private static byte[] damageZonesUnused = new byte[15];
    //unused ??
    private static int lastTerrainDamageTime;
    
    public static boolean[][] allBotsKilledInRoom; //useless? todo remove? not sure
	
	//Objects data
    public static boolean objectsDataLoaded;
    public static byte[] objectModelSource;
    public static byte[] objectUnusedField;
    public static String[] objectModelName;
    public static String[] objectTextureName;
    public static byte[] objectSpriteBlendMode;
    public static byte[] objectM3GId;
    public static float[] objectScale;
    public static byte[] objectStaticBotWeaponId;
    public static byte[] objectStaticBotAnimTime;
	
    public static float[] tmpMehsScale;
    public static Transform tmpTrans;
    public static float[] tmpTransArr;
    private static boolean resetCachedAssetsArrays;
	
    public static World persWorld;
	
    private static boolean aimAssistActive;
    private static int aimAssistStartTime;
    private static float aimAssistOrigYRot;
    private static float aimAssistOrigXRot;
	
    public static boolean damageEffect;
	
    public static int shootStartTime;
    public static boolean shootShakeActive;
	
    public static boolean useThirdPerson;
    public static boolean showFinalDialog;
    public static boolean showIntro;
    private static final int screenWidth; //todo remove?
    private static final int screenHeight; //todo remove?
	
    public static long currentTime;
    public static long gameTime; //unused todo remove?
    public static long gameTimeUnpaused; //prev renderTimeOnly3D
    private static int tickDuration;
   //Возвращаю старый дебаггер
    private static int fpsCounterEnabled;
	
	//some timers ?
    private static int timeToEndGame;
    private static int koboldCutsceneStartTime;
    private static int finalDialogStartTime;
	
    private static boolean onlyOneBotLeft; //todo remove?
    public static boolean notInRoom;
    private static boolean walkingSoundActive = false;

    static {
        botCenterStatePos = new float[10][3];
        currentGameState = -2;
        
        allBotsKilledInRoom = new boolean[17][15];
        objectModelSource = new byte[127];
        objectUnusedField = new byte[127];

        objectModelName = new String[127];
        objectTextureName = new String[127];

        objectSpriteBlendMode = new byte[127];
        objectM3GId = new byte[127];
        objectScale = new float[127];
        objectStaticBotWeaponId = new byte[100];
        objectStaticBotAnimTime = new byte[100];
		
        tmpMehsScale = new float[]{1.0F, 1.0F, 1.0F};
        resetCachedAssetsArrays = true;
        bckMeshes = new Mesh[32];
        roomMeshes = new Mesh[32];
        activableObjMeshes = new Mesh[10];
        roomLights = new Light[32];
        bckSprites = new Sprite3D[32];
        staticBotMdlGroup = new Group[10];
        roomBotGroups = new Group[10];
        bckSpritesAnimEnabled = new boolean[32];
        roomBckMeshesMdlId = new byte[32];
        rotatingLightId = -1;
        doorYAngle = new float[5];
        doorMaxXAngle = new float[5];
        doorMinXAngle = new float[5];
        botYAngle = new float[10];
        botMaxXAngle = new float[10];
        botMinXAngle = new float[10];
        activObjYAngle = new float[10];
        activObjMaxXAngle = new float[10];
        activObjMinXAngle = new float[10];
        doorIsUnderCursor = new boolean[5];
        botIsUnderCursor = new boolean[10];
        activableObjIsUnderCursor = new boolean[10];
        aimAssistStartTime = -1;
        levelUseThirdperson = new byte[]{0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
        screenWidth = MainMenuScreen.scrWidth;
        screenHeight = MainMenuScreen.scrHeight;
        tmpTrans = new Transform();
        tmpTransArr = new float[16];
        isBotDeathAnimFinished = true;
        dyingBotId = -1;
        botsStateChangeTimePassed = new int[10];
        botDeadAnimEndTime = new int[10];
        botsStateChangeTime = 2000;
        botCurrentPosState = new byte[10];
        activeBotId = -1;
        bckSpritesW = new int[32];
        bckSpritesH = new int[32];
        botActive = new boolean[10];
        notInRoom = true;

        try {
        fpsCounterEnabled = Integer.parseInt(Main.main.getAppProperty("ShowFPS"));
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    public static void init() {
        botsStateChangeTime = 2000;
        currentLocation = 0; //в оригинале 0
        //0 - лесная тропа
        //1 - первый лагерь сталкеров
        //2 - место, где водятся артефакты(Галош)
        //3 - лагерь бандитов на лесной тропе (Ржавый)
        //4 - найти сталкера в канализации (Ржавый)
        //5 - задание по рации
        //6 - второй лагерь сталкеров
        //7 - задание батьки "батарейка"
        //8 - задание хари "разобраться с бандитами"
        //9 - туннели с военными, поиск Кэпа
        //10 - ??? агропром с наёмниками
        //11 - задание командования "прибыть в точку выхода"
        //12 - третий лагерь сталкеров
        //13 - друзья беломора
        nextLocation = 0;
        showIntro = true; //не посмотрел интро
        locationCompleted = new boolean[17];
        locationTaskMark = new boolean[17];
        locationCampMark = new boolean[17];
        locationCampMark[1] = true;
        doorIsUnderCursor = new boolean[5];
        activableObjIsUnderCursor = new boolean[10];
        botIsUnderCursor = new boolean[10];
        Scripts.setAllActorStatsToDefault();
    }

    private static void resetLocation() {
        Scripts.playerCanLeaveLevel = false;
        Scripts.locationInventoryItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        botActive = new boolean[10];
        botKilled = new boolean[15][10];
    }

    private static void loadLocation(int levelId) {//что-то с загрузкой локации
        resetLocation();
        loadLocationData(levelId);
        nextRoom = currentRoom;
        ConfigureAndActivateCamera();
        if(resetCachedAssetsArrays) {
            ResourceLoader.initTexturesMassive();//Инициализация пустых массивов

            for(int i = 0; i < 32; i++) {
                roomBckMeshesMdlId[i] = -1;
            }

            resetCachedAssetsArrays = false;
        } else {
            prepareObjectArraysForNextRoom();
        }

        loadObjectsData();
        LoadingScreen.RunGarbageCollector();
        PlayerHUD.loadHUDTexturesAndLocationCoorditates();
        LoadingScreen.RunGarbageCollector();
        ConfigureAndActivateCamera();
        if(roomBotGroups[0] == null) //Если модели сталкеров не загружены
        {
            Bot.loadBot((byte) 1, (byte) 0);
        }

        loadRoom(currentRoom);
		
        if(levelUseThirdperson[levelId] == 1 && roomsCount > 1) {
            loadPlayerModel();
        } else {
            gameWorld.removeChild(playerModel);
            playerModel = null;
        }

        ResourceLoader.clearTextures();
        setupAllObjects(currentRoom);
        gamePaused = false;
    }

    private static void setupAllObjects(int roomId) {
        setRoomEnvironment(roomId);
        setRoomLights(roomId);
        setRoomBckMeshes(roomId);
        setRoomBots(roomId);
        setRoomActivableObjs(roomId);
        setRoomMeshes(roomId);
        setCamera(roomId, currentDoorId);
		setObjAngles(roomId);
    }

    public static byte findInMassive(byte var0, byte[] massive) {
        for(byte i = 0; i < massive.length; ++i) {
            if(var0 == massive[i]) {
                return i;
            }
        }

        return (byte) -1;
    }

    public static boolean isItemInList(int targetItem, short[] items) //надета ли вещь?
    {
        for(int i = 0; i < items.length; i++) {
            if(targetItem == items[i]) return true;
        }

        return false;
    }

    public static int getActivableObjState(int id) {
        return (int) activableObjSettings[currentRoom][id][7];
    }

    public static void setActiveObjState(int id, int state) {
        activableObjSettings[currentRoom][id][7] = (float) state;
    }

    private static void addObjectToGameWorld(int objMdlId, int roomObjId, int objType) {
        try {
            int mdlSource = objectModelSource[objMdlId];
            if(mdlSource == -1) return;

            String mdlPath = "/" + objectModelName[objMdlId];
            float mdlScale = objectScale[objMdlId];
			
			if(mdlSource == 0 || mdlSource == 4) {
				//mdlSource 0 - z1 (for bck and room meshes)
				//mdlSource 4 - bot for activable objects, z1 for bck and room meshes (why ???)
				//split mdlSource into another if else ?
				
				if(objType == 1 || objType == 2) {
					//Background and room mesh obj types
					
					if(objType == 2 || bckMeshes[roomObjId] == null) {
						//Load new mesh if isn't reused from previous room
						Mesh mesh = ResourceLoader.loadZ1Model(mdlPath);
						
						if(objType == 1) bckMeshes[roomObjId] = mesh;
						else roomMeshes[roomObjId] = mesh;
						
						mesh.getScale(tmpMehsScale);
						mesh.setScale(
							tmpMehsScale[0] * mdlScale, 
							tmpMehsScale[1] * mdlScale, 
							tmpMehsScale[2] * mdlScale
						);
						
						mesh.setRenderingEnable(true);
						gameWorld.addChild(mesh);
					} else {
						//Bck mesh is reused from previous room
						//Revert original game object scale since
						//scale will be applied again in setRoomBckMeshes 
						float tmpScale = 0.1f * mdlScale; //just 0.1f in original
						bckMeshes[roomObjId].setScale(tmpScale, tmpScale, tmpScale);
						
						gameWorld.addChild(bckMeshes[roomObjId]);
					}
				} else if(objType == 100) {
					//Activable obj mesh type
					
					if(mdlSource == 4) {
						Bot.loadStaticBot(roomObjId, objMdlId);
					} else if(activableObjMeshes[roomObjId] == null) {
						activableObjMeshes[roomObjId] = ResourceLoader.loadZ1Model(mdlPath);
						gameWorld.addChild(activableObjMeshes[roomObjId]);
					} /*else {
						//Copied from bck mesh loading code? useless?
						activableObjMeshes[roomObjId].setScale(0.1F, 0.1F, 0.1F);
						gameWorld.addChild(activableObjMeshes[roomObjId]);
					}*/
				}
			} else if(mdlSource == 1) {
				//mdlSource 1 - sprite (only for background meshes)
				bckMeshes[roomObjId] = null;
				
				Sprite3D spr = ResourceLoader.getSprite(objMdlId);
				bckSprites[roomObjId] = spr;
				
				if(spr != null) {
					if(objectTextureName[objMdlId].equals("sfire1.png")) {
						bckSpritesAnimEnabled[roomObjId] = true;
						
						Image2D fireImg = spr.getAppearance().getTexture(0).getImage();
						
						bckSpritesW[roomObjId] = fireImg.getWidth();
						bckSpritesH[roomObjId] = fireImg.getHeight();
						
						animateBckSprites();
					}

					spr.getScale(tmpMehsScale);
					spr.setScale(
						tmpMehsScale[0] * mdlScale, 
						tmpMehsScale[1] * mdlScale, 
						tmpMehsScale[2] * mdlScale
					);
					
					spr.setRenderingEnable(true);
					gameWorld.addChild(spr);
				}
			} else if(mdlSource == 2 || mdlSource == 3) {
				//mdlSource 2 - m3g lights (only for light object type)
				//mdlSource 3 - m3g meshes (only for bck and room meshes)
				
				Object3D[] objs = Loader.load("/gamedata/meshes/m3g" + mdlPath);
				ModChanges.updateM3DModels(objs, "/gamedata/meshes/m3g" + mdlPath);

				World m3gWorld = null;

				for(int i = 0; i < objs.length; i++) {
					if(objs[i] instanceof World) {
						m3gWorld = (World) objs[i];
						break;
					}
				}
				
				Node node = (Node) m3gWorld.find(objectM3GId[objMdlId]).duplicate();
				
				if(objType == 1 || objType == 2) {
					Mesh mesh = (Mesh) node;
					
					if(objType == 1) bckMeshes[roomObjId] = mesh;
					else roomMeshes[roomObjId] = mesh;
					
					mesh.getScale(tmpMehsScale);
					mesh.setScale(
						tmpMehsScale[0] * mdlScale, 
						tmpMehsScale[1] * mdlScale, 
						tmpMehsScale[2] * mdlScale
					);
				} else if(objType == 3) {
					roomLights[roomObjId] = (Light) node;
				}

				node.setRenderingEnable(true);
				gameWorld.addChild(node);
			}
        } catch (Exception e) {
            System.out.println("Error type= " + objectModelSource[objMdlId]);
            System.out.println(objectModelName[objMdlId]);
			
            e.printStackTrace();
        }
    }

    private static String readStringFromDis(DataInputStream dis) {
		//Загрузка массива букв
        try {
            char[] chars = new char[dis.readByte()]; //Прочесть несколько букв и занести в массив
			
			//Заполнить массив буквами
            for(int i = 0; i < chars.length; ++i)  {
                chars[i] = (char) dis.readByte();
            }

            return String.valueOf(chars);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void loadObjectsData() {
        if(!objectsDataLoaded) {
            try {
                DataInputStream dis = MathUtils.getFileStream("/objects");
				
                dis.skip(2L); //Header?
                int objsCount = dis.readByte(); //Первое восьмибитное значение

                for(int objId = 0; objId < objsCount; ++objId) {
					
                    objectModelSource[objId] = dis.readByte(); //0?
                    objectUnusedField[objId] = dis.readByte(); //1?
                    objectModelName[objId] = readStringFromDis(dis); //Название объекта
                    objectTextureName[objId] = readStringFromDis(dis); //название текстур
					
                    if(objectModelSource[objId] == 4) {
						//load from pers??
                        objectStaticBotWeaponId[objId] = dis.readByte();
                        objectStaticBotAnimTime[objId] = dis.readByte();
                    } else {
						//load from lights??
                        objectSpriteBlendMode[objId] = dis.readByte();
                        objectM3GId[objId] = dis.readByte();
                    }

                    objectScale[objId] = (float) dis.readInt() / 100.0F;
                }

                objectsDataLoaded = true;
                dis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadLocationData(int locationId) {
        try {
            DataInputStream dis = MathUtils.getFileStream("/gamedata/levels/l" + locationId); //Загрузка локаций
            dis.skip(2);
            dis.readByte();

            currentRoom = dis.readByte(); //limiterYrotation
            currentRoom--;
            
            currentDoorId = dis.readByte(); //выполнена ли 15-ая миссия
            currentDoorId--;
            
            roomsCount = dis.readByte();

            for(int roomId = 0; roomId < roomsCount; roomId++) {
                //room x, y, z
                roomSettings[roomId][0] = dis.readInt() / 100f;
                roomSettings[roomId][1] = dis.readInt() / 100f;
                roomSettings[roomId][2] = dis.readInt() / 100f;

                //?? unused
                roomSettings[roomId][3] = dis.readInt() / 100f;
                roomSettings[roomId][4] = dis.readByte();

                //ambient color, ambient intensity, background color
                roomSettings[roomId][5] = dis.readInt();
                roomSettings[roomId][6] = dis.readInt();
                roomSettings[roomId][7] = dis.readInt();

                //Doors
                doorsCount[roomId] = dis.readByte();

                for(int i = 0; i < doorsCount[roomId]; i++) {
                    //door x, y, z
                    doorSettings[roomId][i][0] = dis.readInt() / 100.0F;
                    doorSettings[roomId][i][1] = dis.readInt() / 100.0F;
                    doorSettings[roomId][i][2] = dis.readInt() / 100.0F;
                    
                    //next room id
                    doorSettings[roomId][i][3] = dis.readByte();
                    //next room door id
                    doorSettings[roomId][i][4] = dis.readByte();
                    
                    //max camera rotation to left, right (0-180?)
                    doorSettings[roomId][i][5] = dis.readUnsignedByte();
                    doorSettings[roomId][i][6] = dis.readUnsignedByte();
                    
                    //locked (0/1)
                    doorSettings[roomId][i][7] = dis.readByte();
                    if(doorSettings[roomId][i][7] != 0.0F) {
                        //item to unlock
                        doorKeys[roomId][i] = (short) dis.readUnsignedByte();
                    }
                }

                //Walk points
                walkPointsCount[roomId] = dis.readByte();

                for(int i = 0; i < walkPointsCount[roomId]; i++) {
                    //door 1 and 2
                    walkPoints[roomId][i][0] = dis.readByte();
                    walkPoints[roomId][i][1] = dis.readByte();

                    //x, z
                    walkPoints[roomId][i][2] = dis.readInt() / 100.0F;
                    walkPoints[roomId][i][3] = dis.readInt() / 100.0F;
                    
                    //walk time to door 2 and 1
                    walkPoints[roomId][i][4] = dis.readByte();
                    walkPoints[roomId][i][5] = dis.readByte();
                }

                //Background meshes
                bckMeshesCount[roomId] = dis.readByte();

                for(int i = 0; i < bckMeshesCount[roomId]; i++) {
                    //x, y, z
                    bckMeshSettings[roomId][i][0] = dis.readInt() / 100.0F;
                    bckMeshSettings[roomId][i][1] = dis.readInt() / 100.0F;
                    bckMeshSettings[roomId][i][2] = dis.readInt() / 100.0F;
                    
                    //rotation y
                    bckMeshSettings[roomId][i][3] = dis.readInt() / 100.0F;
                    
                    //model id
                    bckMeshSettings[roomId][i][4] = dis.readByte();
                    
                    //scale
                    bckMeshSettings[roomId][i][5] = dis.readInt() / 100.0F;
                }

                //Light sources
                lightsCount[roomId] = dis.readByte();

                for(int i = 0; i < lightsCount[roomId]; i++) {
                    //x, y, z
                    lightSettings[roomId][i][0] = dis.readInt() / 100.0F;
                    lightSettings[roomId][i][1] = dis.readInt() / 100.0F;
                    lightSettings[roomId][i][2] = dis.readInt() / 100.0F;
                    
                    //rotation y
                    lightSettings[roomId][i][3] = dis.readInt() / 100.0F;
                    
                    //light id
                    lightSettings[roomId][i][4] = dis.readByte();
                }

                //Meshes
                meshesCount[roomId] = dis.readByte();

                for(int i = 0; i < meshesCount[roomId]; i++) {
                    //x, y, z
                    meshSettings[roomId][i][0] = dis.readInt() / 100.0F;
                    meshSettings[roomId][i][1] = dis.readInt() / 100.0F;
                    meshSettings[roomId][i][2] = dis.readInt() / 100.0F;
                    
                    //rotation x, y, z
                    meshSettings[roomId][i][3] = dis.readInt() / 100.0F;
                    meshSettings[roomId][i][4] = dis.readInt() / 100.0F;
                    meshSettings[roomId][i][5] = dis.readInt() / 100.0F;
                    
                    //model id
                    meshSettings[roomId][i][6] = dis.readByte();
                    
                    //scale
                    meshSettings[roomId][i][7] = dis.readInt() / 100.0F;
                }

                //Interactive objects
                activableObjsCount[roomId] = dis.readByte(); //третий набор мешей

                for(int i = 0; i < activableObjsCount[roomId]; i++) {
                    //x, y, z
                    activableObjSettings[roomId][i][0] = dis.readInt() / 100.0F;
                    activableObjSettings[roomId][i][1] = dis.readInt() / 100.0F;
                    activableObjSettings[roomId][i][2] = dis.readInt() / 100.0F;
                    
                    //rotation x, y, z
                    activableObjSettings[roomId][i][3] = dis.readInt() / 100.0F;
                    activableObjSettings[roomId][i][4] = dis.readInt() / 100.0F;
                    activableObjSettings[roomId][i][5] = dis.readInt() / 100.0F;
                    
                    //model id
                    activableObjSettings[roomId][i][6] = dis.readUnsignedByte();
					
					//activable object id
                    activableObjSettings[roomId][i][7] = dis.readUnsignedByte();
                }

                //Bots
                botsCount[roomId] = dis.readByte();

                for(int i = 0; i < botsCount[roomId]; i++) {
					//main x and z (used when bot hides or when bot shoots from pos 2)
					botSettings[roomId][i][0] = dis.readInt() / 100.0F;
					botSettings[roomId][i][1] = dis.readInt() / 100.0F;
					//second x, z
					botSettings[roomId][i][2] = dis.readInt() / 100.0F;
					botSettings[roomId][i][3] = dis.readInt() / 100.0F;
					//third x, z
					botSettings[roomId][i][4] = dis.readInt() / 100.0F;
					botSettings[roomId][i][5] = dis.readInt() / 100.0F;
					
					//y
					botSettings[roomId][i][6] = dis.readInt() / 100.0F;
					
					//rotation y
					botSettings[roomId][i][7] = dis.readInt() / 100.0F;

					//bot door id
					botSettings[roomId][i][8] = dis.readByte();
					
					//is hiding position 0 1 2 3 4 can be selected (0 / 1)
					botSettings[roomId][i][9] = dis.readByte(); //second x and z, crouching
					botSettings[roomId][i][10] = dis.readByte(); //second x and z, stading
					botSettings[roomId][i][11] = dis.readByte(); //first x and z, stading
					botSettings[roomId][i][12] = dis.readByte(); //third x and z, stading
					botSettings[roomId][i][13] = dis.readByte(); //third x and z, crouching
					
					//bot type (from 0 to 12)
					botSettings[roomId][i][14] = dis.readByte();
                }

                //Items for killing everyone in room
                itemsForKillingAllCount[roomId] = dis.readByte();

                for(int i = 0; i < itemsForKillingAllCount[roomId]; i++) {
                    itemsForKillingAll[roomId][i] = (short) dis.readUnsignedByte();
                }

                int isDamageZone = dis.readByte();
                if(isDamageZone != 0) {
                    damageZonesTypes[roomId] = dis.readByte();
                    damageZonesUnused[roomId] = dis.readByte();
                } else {
                    damageZonesTypes[roomId] = -1;
                }
            }

            dis.close();
            
            mirrorLocationByZ();
            loadDoors(); //useless?
        } catch (Exception e) {
            System.out.println("Error on level loading");
            e.printStackTrace();
        }
    }

    //useless?
    private static void loadDoors() {
        for(int roomId = 0; roomId < roomsCount; ++roomId) {
            for(int doorId = 0; doorId < doorsCount[roomId]; ++doorId) {
                loadedDoorsSettings[roomId][doorId][0] = (byte) doorSettings[roomId][doorId][3];
                loadedDoorsSettings[roomId][doorId][1] = (byte) doorSettings[roomId][doorId][4];
            }
        }

    }

    private static void mirrorLocationByZ() {
        for(int roomdId = 0; roomdId < roomsCount; ++roomdId) {
            roomSettings[roomdId][2] *= -1;

            for(int i = 0; i < bckMeshesCount[roomdId]; ++i) {
                bckMeshSettings[roomdId][i][2] *= -1;
            }

            for(int i = 0; i < botsCount[roomdId]; ++i) {
                botSettings[roomdId][i][1] *= -1;
                botSettings[roomdId][i][5] *= -1;
                botSettings[roomdId][i][3] *= -1;
            }

            for(int i = 0; i < meshesCount[roomdId]; ++i) {
                meshSettings[roomdId][i][2] *= -1;
            }

            for(int i = 0; i < doorsCount[roomdId]; ++i) {
                doorSettings[roomdId][i][2] *= -1;
            }

            for(int i = 0; i < activableObjsCount[roomdId]; ++i) {
                activableObjSettings[roomdId][i][2] *= -1;
            }

            for(int i = 0; i < walkPointsCount[roomdId]; ++i) {
                walkPoints[roomdId][i][3] *= -1;
            }

            for(int i = 0; i < lightsCount[roomdId]; ++i) {
                lightSettings[roomdId][i][2] *= -1;
            }
        }

    }

    private static void prepareObjectArraysForNextRoom() {
        Mesh[] var0 = new Mesh[32];
        byte[] var1 = new byte[64];
        byte[] var2 = new byte[64];
        byte var3 = 0;
		
        byte var5;
        for(var5 = 0; var5 < var1.length; ++var5) {
            var1[var5] = var2[var5] = -1;
        }

        try {
			//reuse meshes from previous room in next room
            byte var4;
            for(var4 = 0; var4 < 32; ++var4) {
                for(var5 = 0; var5 < bckMeshesCount[nextRoom]; ++var5) {
                    if(roomBckMeshesMdlId[var4] == (byte) bckMeshSettings[nextRoom][var5][4]) {
                        var1[var3] = var4;
                        var2[var3] = var5;
                        ++var3;
					}
				}
			}
                
            for(var4 = 0; var4 < var1.length; ++var4) {
                if(var1[var4] != -1 && bckMeshes[var1[var4]] != null && var0[var2[var4]] == null) {
                    var0[var2[var4]] = (Mesh) bckMeshes[var1[var4]].duplicate();
                }
            }

            for(var4 = 0; var4 < 32; ++var4) {
                gameWorld.removeChild(bckMeshes[var4]);
                bckMeshes[var4] = null;
                gameWorld.removeChild(bckSprites[var4]);
                bckSprites[var4] = null;
            }

            for(var4 = 0; var4 < 32; ++var4) {
                if(var0[var4] != null) {
                    bckMeshes[var4] = (Mesh) var0[var4].duplicate();
                }
            }

            for(var4 = 0; var4 < var0.length; ++var4) {
                var0[var4] = null;
            }

            System.gc();
            byte var6 = botsCount[nextRoom];

            for(var4 = 1; var4 < 10; ++var4) {
                if(var4 >= var6) {
                    gameWorld.removeChild(roomBotGroups[var4]);
                    roomBotGroups[var4] = null;
                }
            }

            for(var4 = 0; var4 < 32; ++var4) {
                gameWorld.removeChild(roomMeshes[var4]);
                roomMeshes[var4] = null;
            }

            System.gc();

            for(var4 = 0; var4 < 10; ++var4) {
                gameWorld.removeChild(activableObjMeshes[var4]);
                activableObjMeshes[var4] = null;
            }

            for(var4 = 0; var4 < roomLights.length; ++var4) {
                gameWorld.removeChild(roomLights[var4]);
                roomLights[var4] = null;
            }

            for(var4 = 0; var4 < 10; ++var4) {
                gameWorld.removeChild(staticBotMdlGroup[var4]);
                staticBotMdlGroup[var4] = null;
            }

            gameWorld.removeChild(ambientLight);
            ambientLight = null;
            System.gc();
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    private static void loadRoom(int roomId) {
        loadSprites();

        for(int i = 0; i < bckMeshesCount[roomId]; i++) {
            addObjectToGameWorld((int) bckMeshSettings[roomId][i][4], i, (byte) 1);
            roomBckMeshesMdlId[i] = (byte) bckMeshSettings[roomId][i][4];
        }

        for(int i = 0; i < activableObjsCount[roomId]; i++) {
            addObjectToGameWorld((int) activableObjSettings[roomId][i][6], i, (byte) 100);
        }
		
        if(botsCount[roomId] == 0) {
            roomBotGroups[0].setRenderingEnable(false);
        }

        for(int i = 0; i < botsCount[roomId]; i++) {
			Bot.loadBot((int) botSettings[roomId][i][14], i);
        }

        for(int i = 0; i < meshesCount[roomId]; i++) {
            addObjectToGameWorld((int) meshSettings[roomId][i][6], i, (byte) 2);
        }

        for(int i = 0; i < lightsCount[roomId]; i++) {
            addObjectToGameWorld((int) lightSettings[roomId][i][4], i, (byte) 3);
        }
		
        notInRoom = false;
    }

    private static void loadSprites() {
        if(bloodSprite == null) {
            bloodSprite = ResourceLoader.getSprite(-100);
            bloodSprite.setScale(0.6F, 0.6F, 0.6F);
			
            gameWorld.addChild(bloodSprite);
        }

        bloodSprite.setRenderingEnable(false);
		
        if(muzzleFlashSprite == null) {
            muzzleFlashSprite = ResourceLoader.getSprite(-101);
            gameWorld.addChild(muzzleFlashSprite);
        }

        muzzleFlashSprite.setRenderingEnable(false);
    }

    private static void loadPlayerModel() {
        if(playerModel == null) {
            playerModel = new Group();
			
            if(roomBotGroups[0] != null) {
                playerModel = (Group) roomBotGroups[0].duplicate();
                System.out.println("STALKER COPIED !!! ");
            }

            setPlayerModelAppearance();
            gameWorld.addChild(playerModel);
        }
    }

    private static void setPlayerModelAppearance() {
        Appearance ap = new Appearance(); //Внешний вид
        ap.setTexture(0, ResourceLoader.getTexture("pstalker.png"));//текстура
		
        CompositingMode compositMode = new CompositingMode(); //Способ зарисовки в мире
        compositMode.setBlending(CompositingMode.REPLACE); //смешивание
        ap.setCompositingMode(compositMode);
		
        Material mat = new Material(); //освещение и блеск
        ap.setMaterial(mat);

        for(int i = 0; i < 12; ++i) {
			Mesh bodyPart = (Mesh) playerModel.find(i + 1);
            bodyPart.setAppearance(0, ap); //внешний вид для конкретного сабмеша
        }

        Mesh backpackModel = (Mesh) persWorld.find(25);
        Mesh backpackCopy = (Mesh) backpackModel.duplicate();
        playerModel.addChild(backpackCopy);
    }
	
    private static void preparePlayerModel(byte weaponId) {
        if(weaponId > 0) {
            Mesh weaponModel = (Mesh) persWorld.find(weaponId + 22 - 1);
            Mesh weaponCopy = (Mesh) weaponModel.duplicate();
            playerModel.addChild(weaponCopy);
        }

        playerModel.setOrientation(0.0F, 0.0F, 0.0F, 0.0F);
        playerModel.setRenderingEnable(true);
    }

    private static void backupCameraPos() {
        System.arraycopy(cameraPos, 0, prevCameraPos, 0, 3);
    }

    private static void setRoomEnvironment(int roomId) {
        float lightIntensity = roomSettings[roomId][6] / 100.0F;
		
        if(lightIntensity != 0.0F) {
            ambientLight = new Light();
            ambientLight.setMode(Light.AMBIENT);
			
            int lightCol = (int) roomSettings[roomId][5];
			
            ambientLight.setColor(lightCol); //(21431)
            ambientLight.setIntensity(lightIntensity); //
			
            gameWorld.addChild(ambientLight);
        }

        backgroundColor = (int) roomSettings[roomId][7];//
        
		if(gameWorld.getBackground() == null) {
            Background background = new Background();
            gameWorld.setBackground(background);
            //todo отключать очистку фона если мы в здании
        }
        gameWorld.getBackground().setColor(backgroundColor);

    }

    private static void setRoomLights(int roomId) {
        rotatingLightId = -1;

        for(int i = 0; i < lightsCount[roomId]; i++) {
			float x = lightSettings[roomId][i][0];
			float y = lightSettings[roomId][i][1];
			float z = lightSettings[roomId][i][2];
			float rotY = lightSettings[roomId][i][3];
			
            roomLights[i].setOrientation(rotY, 0.0F, 1.0F, 0.0F);
            roomLights[i].setTranslation(x, y, z);
            
			if(roomLights[i].getAnimationTrackCount() != 0) rotatingLightId = i;
        }
    }

    private static void setBotPosition(int botId, float x, float y, float z) {
        botPositions[botId][0] = x;
        botPositions[botId][1] = y;
        botPositions[botId][2] = z;
		
        roomBotGroups[botId].setTranslation(x, y, z);
    }

    private static void setRoomBots(int roomId) {
        activeBotId = -1;
        botsKilledCount = 0;

        for(int i = 0; i < botsCount[roomId]; i++) {
            botsStateChangeTimePassed[i] = (int) gameTimeUnpaused + botsStateChangeTime;
            botDeadAnimEndTime[i] = (int) gameTimeUnpaused + 500;
            botCurrentPosState[i] = 2;
			
            roomBotGroups[i].setOrientation(botSettings[roomId][i][7], 0.0F, 1.0F, 0.0F);
            setBotPosition(
				i, 
				botSettings[roomId][i][0], 
				botSettings[roomId][i][6], 
				botSettings[roomId][i][1]
			);
        }

        if(botsCount[roomId] == 0) {
            Scripts.giveItemsForKillingAll();
        }

        updateBotsStates();
    }

    private static void setRoomBckMeshes(int roomId) {
        for(int i = 0; i < bckMeshesCount[roomId]; i++) {
			
            float x = bckMeshSettings[roomId][i][0];
            float y = bckMeshSettings[roomId][i][1];
            float z = bckMeshSettings[roomId][i][2];
            float scale = bckMeshSettings[roomId][i][5];
			
            if(bckMeshes[i] != null) {
				Mesh bckMesh = bckMeshes[i];
				
                bckMesh.getScale(tmpMehsScale);

                bckMesh.setScale(
						tmpMehsScale[0] * scale, 
						tmpMehsScale[1] * scale, 
						tmpMehsScale[2] * scale
				);
				
                bckMesh.setOrientation(bckMeshSettings[roomId][i][3], 0.0F, 1.0F, 0.0F);
                bckMesh.setTranslation(x, y, z);
            } else if(bckSprites[i] != null) {
				Sprite3D bckSpr = bckSprites[i];
				
                bckSpr.setScale(scale, scale, scale);
                bckSpr.setTranslation(x, y, z);
            }
        }
    }

    private static void setRoomMeshes(int roomId) {
        for(int i = 0; i < meshesCount[roomId]; i++) {
			Mesh mesh = roomMeshes[i];
			if(mesh == null) continue;
			
			float scale = meshSettings[roomId][i][7];
			mesh.getScale(tmpMehsScale);

			mesh.setScale(
					tmpMehsScale[0] * scale, 
					tmpMehsScale[1] * scale, 
					tmpMehsScale[2] * scale
			);
			
			float x = meshSettings[roomId][i][0];
			float y = meshSettings[roomId][i][1];
			float z = meshSettings[roomId][i][2];

			mesh.setTranslation(x, y, z);
			
			mesh.preRotate(meshSettings[roomId][i][3], 1.0F, 0.0F, 0.0F);
			mesh.preRotate(meshSettings[roomId][i][5], 0.0F, 0.0F, 1.0F);
			mesh.preRotate(meshSettings[roomId][i][4], 0.0F, 1.0F, 0.0F);
        }
    }

    private static void setRoomActivableObjs(int roomId) {
        for(int i = 0; i < activableObjsCount[roomId]; i++) {
			float x = activableObjSettings[roomId][i][0];
			float y = activableObjSettings[roomId][i][1];
			float z = activableObjSettings[roomId][i][2];
			
			float rotX = activableObjSettings[roomId][i][3];
			float rotY = activableObjSettings[roomId][i][4];
			float rotZ = activableObjSettings[roomId][i][5];
			
            if(activableObjMeshes[i] != null) {
                activableObjMeshes[i].setOrientation(rotX, 1.0F, 0.0F, 0.0F);
                activableObjMeshes[i].preRotate(rotZ, 0.0F, 0.0F, 1.0F);
                activableObjMeshes[i].preRotate(rotY, 0.0F, 1.0F, 0.0F);
                activableObjMeshes[i].setTranslation(x, y, z);
            } else if(staticBotMdlGroup[i] != null) {
                staticBotMdlGroup[i].setOrientation(rotX, 1.0F, 0.0F, 0.0F);
                staticBotMdlGroup[i].preRotate(rotZ, 0.0F, 0.0F, 1.0F);
                staticBotMdlGroup[i].preRotate(rotY, 0.0F, 1.0F, 0.0F);
                staticBotMdlGroup[i].setTranslation(x, y, z);
            }
        }
    }

    //temp name
    private static float getActivObjYAngle(float x1, float y1, float x2, float y2, float x3, float y3) 
    {
        float dist1to2 = MathUtils.distance2D(x1, y1, x2, y2);
        float dist1to3 = MathUtils.distance2D(x1, y1, x3, y3);
        float dist2to3 = MathUtils.distance2D(x2, y2, x3, y3);
        return dist1to2 != 0.0F && dist1to3 != 0.0F ? (float) MathUtils.acos((dist1to2 * dist1to2 + dist1to3 * dist1to3 - dist2to3 * dist2to3) / (2.0F * dist1to2 * dist1to3)) : -500.0F;
    }

    //temp name
    private static float getXAngleACos(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
        float var9 = MathUtils.distance3D(var0, var1, var2, var3, var4, var5);
        float var10 = MathUtils.distance3D(var0, var1, var2, var6, var7, var8);
        float var11 = MathUtils.distance3D(var3, var4, var5, var6, var7, var8);
        return (float) MathUtils.acos((var9 * var9 + var10 * var10 - var11 * var11) / (2.0F * var9 * var10));
    }

    private static void ConfigureAndActivateCamera() {
        byte var0;
        for(var0 = 0; var0 < doorIsUnderCursor.length; ++var0) {
            doorIsUnderCursor[var0] = false;
        }

        for(var0 = 0; var0 < 10; ++var0) {
            botIsUnderCursor[var0] = false;
        }

        cameraYRot = 0.0F;
        cameraXRot = 0.0F;
        bckSpritesAnimEnabled = new boolean[32];
        lastTerrainDamageTime = (int) gameTimeUnpaused;
        shootShakeActive = false;
        aimAssistActive = false;
        doorUnlocked = new boolean[doorsCount[currentRoom]];
        doorIsUnderCursor = new boolean[5];
        activableObjIsUnderCursor = new boolean[10];
        botIsUnderCursor = new boolean[10];

        for(var0 = 0; var0 < doorUnlocked.length; ++var0) {
            doorUnlocked[var0] = doorSettings[currentRoom][var0][7] == 0.0F;
        }

        dyingBotId = -1;
        Scripts.disableLockedDoorIcon();
    }

    private static void setCamera(int roomId, int doorId) {
        gameWorld.removeChild(camera);
        camera = null; //useless
        camera = new Camera();
        
        float scrW = MainMenuScreen.scrWidth;
        float scrH = MainMenuScreen.scrHeight;
        
        camera.setPerspective(50, scrW / scrH, 0.1f, 10000);
        gameWorld.addChild(camera);
        
        camera.setRenderingEnable(true);
        gameWorld.setActiveCamera(camera);
        
        cameraPos[0] = doorSettings[roomId][doorId][0];
        cameraPos[1] = doorSettings[roomId][doorId][1];
        cameraPos[2] = doorSettings[roomId][doorId][2];
        
        setCameraPos(cameraPos[0], cameraPos[1], cameraPos[2]);
        backupCameraPos();
        
        float camX = cameraPos[0];
        float camZ = cameraPos[2];
        
        float lookAtX = roomSettings[roomId][0];
        float lookAtZ = roomSettings[roomId][2];
        
        float[] vec1 = getVectorForCrossProduct(camX, camZ, camX, camZ - 10.0F);
        float[] vec2 = getVectorForCrossProduct(camX, camZ, lookAtX, lookAtZ);
		
        cameraYRotOffset = getCrossProductSign(vec1, vec2) *
                (int) getCamToRoomCenterAngle(roomId);
        camera.setOrientation((float) cameraYRotOffset, 0.0F, 1.0F, 0.0F); //задаёт направление камеры
    }
	
	private static void setObjAngles(int roomId) {
        for(int objId = 0; objId < doorsCount[roomId]; ++objId) {
            setDoorAngles(objId, roomId);
        }

        for(int objId = 0; objId < botsCount[roomId]; ++objId) {
            setBotAngles(objId, roomId, botCurrentPosState[objId]);
        }

        for(int objId = 0; objId < activableObjsCount[roomId]; ++objId) {
            setActivableObjAngles(objId, roomId);
        }
	}

    //генерирует вектора для cross productа
    private static float[] getVectorForCrossProduct(float x1, float y1, 
            float x2, float y2) {
        return new float[]{x2 - x1, y2 - y1};
    }
    
    //считает cross product
    private static byte getCrossProductSign(float[] vec1, float[] vec2) {
        return (byte) (vec1[1] * vec2[0] - vec1[0] * vec2[1] > 0.0F ? 1 : -1);
    }

    //считает угол от 0 до 180 от угла от камеры к центру комнаты
    private static float getCamToRoomCenterAngle(int roomId) {
        float camX = cameraPos[0];          
        float camZ = cameraPos[2];
        float camLookAtZ = camZ - 1.0F;
        
        float lookAtX = roomSettings[roomId][0];
        float lookAtZ = roomSettings[roomId][2];
        
        float var6 = MathUtils.distance2D(camX, camZ, lookAtX, lookAtZ);
        float var7 = MathUtils.distance2D(camX, camZ, camX, camLookAtZ);
        float var8 = MathUtils.distance2D(lookAtX, lookAtZ, camX, camLookAtZ);
        
        float cos = (var6 * var6 + var7 * var7 - var8 * var8) / (2.0F * var6 * var7);
        
        return (float) MathUtils.acos(cos);
    }

    private static void setBotAngles(int botId, int roomId, int botPosId) {
        float var3 = roomSettings[roomId][0];
        float var4 = roomSettings[roomId][2];
        float var5 = cameraPos[0];
        float var6 = cameraPos[1];
        float var7 = cameraPos[2];
        float var9 = cameraPos[1] - 1.6F;
        activeBotCrouching = botPosId == 0 || botPosId == 4;
        float var11 = 0.0F;
        float var12 = botSettings[roomId][botId][6] + (activeBotCrouching ? 1.0999999F : 1.8F);
        float var13 = activeBotCrouching ? botSettings[roomId][botId][6] : botSettings[roomId][botId][6] + 0.9F;
        float var14 = 0.0F;
        switch(botPosId) {
            case 0:
            case 1:
                var11 = botSettings[roomId][botId][2];
                var14 = botSettings[roomId][botId][3];
                break;
            case 2:
                var11 = botSettings[roomId][botId][0];
                var14 = botSettings[roomId][botId][1];
                break;
            case 3:
            case 4:
                var11 = botSettings[roomId][botId][4];
                var14 = botSettings[roomId][botId][5];
        }

        botYAngle[botId] = getActivObjYAngle(var5, var7, var11, var14, var3, var4);
        float[] var15 = getVectorForCrossProduct(var5, var7, var3, var4);
        float[] var16 = getVectorForCrossProduct(var5, var7, var11, var14);
        byte var17 = getCrossProductSign(var15, var16);
        botYAngle[botId] *= (float) var17;
        if(var12 <= var6) {
            botMaxXAngle[botId] = -(90.0F - getXAngleACos(var5, var6, var7, var11, var12, var14, var5, var9, var7));
            botMinXAngle[botId] = -(90.0F - getXAngleACos(var5, var6, var7, var11, var13, var14, var5, var9, var7));
        } else {
            botMaxXAngle[botId] = getXAngleACos(var5, var6, var7, var11, var12, var14, var5, var9, var7) - 90.0F;
            botMinXAngle[botId] = getXAngleACos(var5, var6, var7, var11, var13, var14, var5, var9, var7) - 90.0F;
        }
    }

    private static void setDoorAngles(int doorId, int roomId) {
        float var2 = roomSettings[roomId][0];
        float var3 = roomSettings[roomId][2];
        float var4 = cameraPos[0];
        float var5 = cameraPos[1];
        float var6 = cameraPos[2];
        float var7 = doorSettings[roomId][doorId][0];
        float var8;
        float var9 = (var8 = doorSettings[roomId][doorId][1] + 0.4F) - 2.0F;
        float var10 = doorSettings[roomId][doorId][2];
        float var12 = cameraPos[1] - 1.6F;
        doorYAngle[doorId] = getActivObjYAngle(var4, var6, var7, var10, var2, var3);
        float[] var14 = getVectorForCrossProduct(var4, var6, var2, var3);
        float[] var15 = getVectorForCrossProduct(var4, var6, var7, var10);
        byte var16 = getCrossProductSign(var14, var15);
        doorYAngle[doorId] *= (float) var16;
        if(loadedDoorsSettings[currentRoom][doorId][0] - 1 < 0) {
            doorYAngle[doorId] = -500.0F;
        }

        if(var8 <= var5) {
            doorMaxXAngle[doorId] = -(90.0F - getXAngleACos(var4, var5, var6, var7, var8, var10, var4, var12, var6));
            doorMinXAngle[doorId] = -(90.0F - getXAngleACos(var4, var5, var6, var7, var9, var10, var4, var12, var6));
        } else {
            doorMaxXAngle[doorId] = getXAngleACos(var4, var5, var6, var7, var8, var10, var4, var12, var6) - 90.0F;
            doorMinXAngle[doorId] = getXAngleACos(var4, var5, var6, var7, var9, var10, var4, var12, var6) - 90.0F;
        }
    }

    //temp name
    private static float[] getWalkPointsThirdperson(int walkCurrentDoorId, boolean thirdPerson) {
        float var2 = roomSettings[currentRoom][0];
        float var3 = roomSettings[currentRoom][2];
        float var4 = cameraPos[0];
        float var5 = cameraPos[2];
        float var6 = walkPointTargetX;
        float var7 = walkPointTargetZ;
        float var8 = doorSettings[currentRoom][walkCurrentDoorId][0];
        float var9 = doorSettings[currentRoom][walkCurrentDoorId][2];
        float var10 = doorSettings[currentRoom][walkNextDoorId][0];
        float var11 = doorSettings[currentRoom][walkNextDoorId][2];
        useThirdPerson = thirdPerson;
        float var12 = getActivObjYAngle(var4, var5, var6, var7, var2, var3);
        float[] var13 = getVectorForCrossProduct(var4, var5, var2, var3);
        float[] var14 = getVectorForCrossProduct(var4, var5, var6, var7);
        byte var15 = getCrossProductSign(var13, var14);
        float var16 = getActivObjYAngle(var6, var7, var10, var11, var8, var9);
        var13 = getVectorForCrossProduct(var6, var7, var10, var11);
        var14 = getVectorForCrossProduct(var6, var7, var8, var9);
        byte var17 = getCrossProductSign(var13, var14);
        return new float[]{var12 * (float) var15, (180.0F - var16) * (float) var17};
    }

    private static void setActivableObjAngles(int objId, int roomId) {//wip
        float xRoomPos = roomSettings[roomId][0];
        float zRoomPos = roomSettings[roomId][2];
        float xCamPos = cameraPos[0];
        float yCamPos = cameraPos[1];
        float zCamPos = cameraPos[2];
        float xObjPos = activableObjSettings[roomId][objId][0];
        float yObjMaxPos;
        float yObjMinPos = (yObjMaxPos = activableObjSettings[roomId][objId][1] + 1.5F) - 1.5F;
        float zObjPos = activableObjSettings[roomId][objId][2];
        float yCamMinPos = cameraPos[1] - 1.6F;
        activObjYAngle[objId] = getActivObjYAngle(xCamPos, zCamPos, xObjPos, zObjPos, xRoomPos, zRoomPos);//2d distance acos
        float[] var14 = getVectorForCrossProduct(xCamPos, zCamPos, xRoomPos, zRoomPos);//3d distance acos
        float[] var15 = getVectorForCrossProduct(xCamPos, zCamPos, xObjPos, zObjPos);//3d distance acos
        byte var16 = getCrossProductSign(var14, var15);
        activObjYAngle[objId] *= (float) var16;
        if(yCamPos <= yObjMaxPos) {
            activObjMaxXAngle[objId] = -(90.0F - getXAngleACos(xCamPos, yCamPos, zCamPos, xObjPos, yObjMaxPos, zObjPos, xCamPos, yCamMinPos, zCamPos));
            activObjMinXAngle[objId] = -(90.0F - getXAngleACos(xCamPos, yCamPos, zCamPos, xObjPos, yObjMinPos, zObjPos, xCamPos, yCamMinPos, zCamPos));
        } else {
            activObjMaxXAngle[objId] = getXAngleACos(xCamPos, yCamPos, zCamPos, xObjPos, yObjMaxPos, zObjPos, xCamPos, yCamMinPos, zCamPos) - 90.0F;
            activObjMinXAngle[objId] = getXAngleACos(xCamPos, yCamPos, zCamPos, xObjPos, yObjMinPos, zObjPos, xCamPos, yCamMinPos, zCamPos) - 90.0F;
        }
    }

    private static void checkDoorAngles() {//дверь открыта, если враги мертвы
        PlayerHUD.doorLocked = false;
		
        if(allBotsKilledInRoom[currentLocation][currentRoom] || botsAlive == 0) {
            for(int doorId = 0; doorId < doorsCount[currentRoom]; doorId++) {
                if(cameraYRot > (float) ((int) doorYAngle[doorId] - 10) && cameraYRot < (float) ((int) doorYAngle[doorId] + 10) && cameraXRot >= doorMinXAngle[doorId] && cameraXRot <= doorMaxXAngle[doorId]) {
                    doorIsUnderCursor[doorId] = true;
                    PlayerHUD.doorLocked = !doorUnlocked[doorId];
                } else {
                    doorIsUnderCursor[doorId] = false;
                }
            }

        }
    }

    private static void ifActiveBotIsUnderCursor() {
        int botId = activeBotId;
        if(activeBotId >= 0) {
            if(cameraYRot > botYAngle[botId] - 10.0F && cameraYRot < botYAngle[botId] + 10.0F && cameraXRot >= botMinXAngle[botId] - 5.0F && cameraXRot <= botMaxXAngle[botId] + 5.0F) {
                botIsUnderCursor[botId] = botCurrentPosState[activeBotId] != -1;
            } else {
                botIsUnderCursor[botId] = false;
            }
        }
    }

    private static void checkActiveObjsAngles() {//активный объект доступен, если враги мертвы
        if(allBotsKilledInRoom[currentLocation][currentRoom] || botsAlive == 0) {
            for(int objId = 0; objId < activableObjsCount[currentRoom]; objId++) {
                if(cameraYRot > activObjYAngle[objId] - 5.0F && cameraYRot < activObjYAngle[objId] + 5.0F && cameraXRot >= activObjMinXAngle[objId] && cameraXRot <= activObjMaxXAngle[objId]) {
                    activableObjIsUnderCursor[objId] = true;
                } else {
                    activableObjIsUnderCursor[objId] = false;
                }
            }

        }
    }

    public static boolean playerDamagedFromRightSide() {
        return activeBotId >= 0 && botYAngle[activeBotId] - cameraYRot >= 0.0F;
    }

    private static boolean terrainDamageTimer() {
        if((int) gameTimeUnpaused - lastTerrainDamageTime >= 1000) {
            lastTerrainDamageTime = (int) gameTimeUnpaused;
            return true;
        } else {
            return false;
        }
    }

    private static void antiradTimer() {
        if(Scripts.IsAntiradIsUsed) {
            Scripts.AntiradDuration -= tickDuration;
            if(Scripts.AntiradDuration <= 0) {
                Scripts.IsAntiradIsUsed = false;
                Scripts.AntiradDuration = 20000;
            }
        }

        if(damageZonesTypes[currentRoom] == -1) {
            disableDamageEffect();
        } else if(terrainDamageTimer()) {
            DealTerrainDamageThroughArmor();
        } else {
            if(gameTimeUnpaused - (long) lastTerrainDamageTime >= 500L) {
                disableDamageEffect();
            }

        }
    }

    private static void DealTerrainDamageThroughArmor() {
        if(!Scripts.IsAntiradIsUsed) {
            int hitpoints = damageZonesTypes[currentRoom] > 0 ? 7 : 4;//Радиация или аномалия, сильный или слабый урон. Что есть что?
            damageEffect = true;
            Scripts.damageToPlayer(hitpoints - hitpoints * ((Scripts.playerAnomalyResistance + Scripts.playerRadiationResistance) / 2) / 100);
        }
    }

    private static void disableDamageEffect() {
        damageEffect = false;
    }

    public static boolean getOpenDoorIdUnderCursor() {
        for(int i = 0; i < doorsCount[currentRoom]; i++) {
            if(i != currentDoorId && doorIsUnderCursor[i] && (doorUnlocked[i] || isItemInList(doorKeys[currentRoom][i], Scripts.locationInventoryItems))) {
                openDoorIdUnderCursor = (byte) i;
                return true;
            }
        }
        return false;
    }

    public static boolean getActiveObjectIdUnderCursor() {
        for(int i = 0; i < activableObjsCount[currentRoom]; i++) {
            if(activableObjIsUnderCursor[i] && getActivableObjState(i) != 0) {
                activableObjIdUnderCursor = i;
                return true;
            }
        }

        return false;
    }

    public static void playBotDieAnimation(int botId) {
        dyingBotId = botId;
        botDeadAnimEndTime[botId] = (int) gameTimeUnpaused + 500;
    }

    public static boolean processAim() {//доведение прицела до врага, если жив
        botIdUndercursor = -100;
        if(activeBotId < 0) return false;
		
        if(botIsUnderCursor[activeBotId] && !botKilled[currentRoom][activeBotId] && botActive[activeBotId] && !allBotsKilledInRoom[currentLocation][currentRoom]) {
            botIdUndercursor = activeBotId;
            
            if(!Scripts.endingCutscene) {
                if(aimAssistStartTime == -1) {
                    aimAssistStartTime = (int) gameTimeUnpaused;
                    aimAssistOrigYRot = cameraYRot;
                    aimAssistOrigXRot = cameraXRot;
                }

                if(gameTimeUnpaused - (long) aimAssistStartTime < 300L) {
					//interpolate to bot angle
                    cameraYRot = aimAssistOrigYRot + (botYAngle[activeBotId] - aimAssistOrigYRot) * (gameTimeUnpaused - aimAssistStartTime) / 300.0F;
                    float newXRot = botMaxXAngle[activeBotId] - (botMaxXAngle[activeBotId] - botMinXAngle[activeBotId]) / (float) (activeBotCrouching ? 4 : 2);
                    cameraXRot = aimAssistOrigXRot + (newXRot - aimAssistOrigXRot) * (gameTimeUnpaused - aimAssistStartTime) / 300.0F;
                } else {
                    aimAssistStartTime = -1;
                }
            }

            aimAssistActive = true;
        } else {
            aimAssistActive = false;
        }
		
		return aimAssistActive;
    }

    public static void setDialogWindowState(short state) {
        prevGameState = currentGameState;//Предыдущее окно?
        currentGameState = state;   //Текущее окно
        if(currentGameState == 2 && prevGameState == 9) {
            ResourceManager.runGarbageCollector();
        }

        switch(currentGameState) {
            case 0:
                PlayerHUD.garbageCollected = 0;
                SoundAndVibro.stopPlayingSound();
                if(!showIntro) {
                    ResourceManager.runGarbageCollector();
                }
                break;
            case 1:
                if(nextDoorId == walkCurrentDoorId) {
                    return;
                }

                for(int i = 0; i < walkPointsCount[currentRoom]; i++) {
					int door1 = ((int) walkPoints[currentRoom][i][0]) - 1;
					int door2 = ((int) walkPoints[currentRoom][i][1]) - 1;
					
					if(
						(door1 == walkCurrentDoorId && door2 == nextDoorId) ||
						(door2 == walkCurrentDoorId && door1 == nextDoorId)
					) {
						walkNextDoorId = nextDoorId;
						
						walkPointTargetX = walkPoints[currentRoom][i][2];
						walkPointTargetZ = walkPoints[currentRoom][i][3];
						
						if(door1 == walkNextDoorId) {
                            walkTimeToWalkPoint = (int) walkPoints[currentRoom][i][5] * 1000 / 3;
                            walkTimeToDoor = (int) walkPoints[currentRoom][i][4] * 1000 / 3;
                        } else {
                            walkTimeToWalkPoint = (int) walkPoints[currentRoom][i][4] * 1000 / 3;
                            walkTimeToDoor = (int) walkPoints[currentRoom][i][5] * 1000 / 3;
                        }
						
						System.out.println("waypoint " + i);
						
						break;
					}
                }

                float[] walkpoints = getWalkPointsThirdperson(walkCurrentDoorId, levelUseThirdperson[currentLocation] == 1);
                walkStartYRot = walkpoints[0] != 180.0F ? walkpoints[0] : 0.0F;
                walkPointYRot = walkStartYRot + walkpoints[1];
                walkAnimStartTime = (int) gameTimeUnpaused;
                backupCameraPos();
                if(useThirdPerson) {
                    preparePlayerModel(Scripts.playerActiveWeapon);
                }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                break;
            case 13:
                PlayerHUD.garbageCollected = 0;
                SoundAndVibro.stopPlayingSound();
                return;
            case 14:
                PlayerHUD.prepareIntroText();
        }

    }

    public static void endGame(short var0, int var1) {//11 - прод. след., 4 - конец игры
        timeToEndGame = (int) gameTimeUnpaused + var1;
        prevGameState = currentGameState;
        currentGameState = var0;
    }

    private static void checkIfTimeToEndGamePassed() {
        if(gameTimeUnpaused >= (long) timeToEndGame) {
            switch(currentGameState) {
                case 4:
                    openGameMainMenu();
                    return;
                case 11:
                    openGameMainMenu();
            }
        }

    }

    private static void openGameMainMenu() {
        GameScreen.clearMemoryAndLoadUIImages();
    }

    private static void openKoboldEndingDialog() {
        Scripts.koboldDialogState = 2;
        loadPlayerModel();
        preparePlayerModel(Scripts.playerActiveWeapon);
        Scripts.startDialog((short) 19, (byte) 0);
        botsStateChangeTime = 500;
        koboldCutsceneStartTime = (int) gameTimeUnpaused;//todo переименовать
    }

    private static void updateGameState() {
        switch(currentGameState) {
            case -2:
            case 3:
                setDialogWindowState((short) 0);
                return;
            case 0://начало игры
                loadLocation(currentLocation);
                if(showIntro) {
                    setDialogWindowState((short) 14);
                    return;
                }

                setDialogWindowState((short) 2);
                return;
            case 1://переход между уровнями?
                if(nextDoorId == walkCurrentDoorId) {
                    PlayerHUD.garbageCollected = 0;
                    loadNextRoom();
                    return;
                }

                updateCurrentCutscene();
                return;
            case 2://Основное игровое окно
                countDeadBots();//подсчёт мёртвых
                updateBotsStates();//обновление позиций ботов
                ifActiveBotIsUnderCursor();//
                checkDoorAngles();//doors obj status
                checkActiveObjsAngles();//activable obj status
                antiradTimer();
                if(Scripts.playerHealth <= 0) {
                    endGame((short) 4, 3000);
                }

                updateCurrentCutscene();
                return;
            case 4:
                return;
            case 5:
                return;
            case 13:
                loadRoom(currentRoom);
                ResourceLoader.clearTextures();
                setupAllObjects(currentRoom);
                setDialogWindowState((short) 2);
                if(currentLocation == 16 && currentRoom == 6 && currentDoorId == 0) {
                    openKoboldEndingDialog();
                }
            case -1:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
        }
    }

    private static void SetCameraOrientAndPostRotate() {
        camera.setOrientation((float) cameraYRotOffset + cameraYRot, 0.0F, 1.0F, 0.0F);
        camera.postRotate(cameraXRot, 1.0F, 0.0F, 0.0F);
    }

    public static void initWalkingTowardsDoor(int doorId) {//переход по локации, если враги мертвы
        if(allBotsKilledInRoom[currentLocation][currentRoom] || botsAlive == 0) {
            walkCurrentDoorId = currentDoorId;
            nextRoom = (byte) (loadedDoorsSettings[currentRoom][doorId][0] - 1);
            if(nextRoom >= 0) {
                currentDoorId = (byte) (loadedDoorsSettings[currentRoom][doorId][1] - 1);
                nextDoorId = doorId;
                setDialogWindowState((short) 1);
            }
        }
    }

    private static void setCameraPos(float x, float y, float z) {
        camera.setTranslation(x, y, z);
    }

    private static void updateGameTicksAndUnpausedTime() {
        long time = System.currentTimeMillis();
		
        tickDuration = Math.min(250, (int) (time - currentTime));
        if(tickDuration < 0) tickDuration = 0;

        currentTime = time;
        gameTime += tickDuration;
        if(!gamePaused) gameTimeUnpaused += tickDuration;

    }

    private static void shakeCameraIfActive() {
        if(shootShakeActive) {
            boolean tooLowFps = MathUtils.fps < 10;
            int var1;
            if((var1 = (int) gameTimeUnpaused - shootStartTime) <= 200) {
                if(!tooLowFps) {
                    float x_angle;
                    float y_angle;
                    if(var1 <= 100) {
                        x_angle = cameraXRot + 6.0F * (float) var1 / 200.0F;
                        y_angle = (float) cameraYRotOffset + cameraYRot - 6.0F * (float) var1 / 200.0F;
                        camera.setOrientation(0.0F, 0.0F, 0.0F, 0.0F);
                        camera.preRotate(x_angle, 1.0F, 0.0F, 0.0F);
                        camera.preRotate(y_angle, 0.0F, 1.0F, 0.0F);
                        return;
                    }

                    x_angle = cameraXRot + 6.0F * (float) (200 - var1) / 200.0F;
                    y_angle = (float) cameraYRotOffset + cameraYRot - 6.0F * (float) (200 - var1) / 200.0F;
                    camera.setOrientation(0.0F, 0.0F, 0.0F, 0.0F);
                    camera.preRotate(x_angle, 1.0F, 0.0F, 0.0F);
                    camera.preRotate(y_angle, 0.0F, 1.0F, 0.0F);
                }
            } else {
                camera.setOrientation((float) cameraYRotOffset + cameraYRot, 0.0F, 1.0F, 0.0F);
                camera.postRotate(cameraXRot, 1.0F, 0.0F, 0.0F);
                shootShakeActive = false;
                bloodSprite.setRenderingEnable(false);
            }

        }
    }

    public static void enableRenderOfBloodSprite() {
		Mesh var_e6b;
        switch(botCurrentPosState[botIdUndercursor]) {
            case 0:
            case 4:
                var_e6b = (Mesh) roomBotGroups[botIdUndercursor].find(30);
                var_e6b.getTransformTo(gameWorld, tmpTrans);
                tmpTrans.get(tmpTransArr);
                bloodSprite.setTranslation(tmpTransArr[3], tmpTransArr[7], tmpTransArr[11]);
                break;
            case 1:
            case 2:
            case 3:
                var_e6b = (Mesh) roomBotGroups[botIdUndercursor].find(31);
                var_e6b.getTransformTo(gameWorld, tmpTrans);
                tmpTrans.get(tmpTransArr);
                bloodSprite.setTranslation(tmpTransArr[3], tmpTransArr[7], tmpTransArr[11]);
        }

        bloodSprite.setRenderingEnable(true);
    }

    private static void CameraXPostRotate() {
        int current_time;
        if((current_time = (int) gameTimeUnpaused & 1000) <= 1000) {
            float angle;
            if(current_time <= 500) {
                angle = cameraXRot + 2.0F * (float) current_time / 1000.0F;
                camera.postRotate(angle, 1.0F, 0.0F, 0.0F);
            } else {
                angle = cameraXRot + 2.0F * (float) (1000 - current_time) / 1000.0F;
                camera.postRotate(angle, 1.0F, 0.0F, 0.0F);
            }
        } else {
            camera.postRotate(cameraXRot, 1.0F, 0.0F, 0.0F);
        }
    }

    private static void playBotDeadAnim(int botId, int botPosState) {
        int timeElapsed = (int) (gameTimeUnpaused - (botDeadAnimEndTime[botId] - 500));
		
        int startFrame, endFrame;
		
		if(botPosState == 0 || botPosState == 4) {
			startFrame = 1500;
            endFrame = 2000;
		} else {
			startFrame = 1700;
			endFrame = 2000;
		}

        if(timeElapsed <= 500) {
            int frame = startFrame + (endFrame - startFrame) * timeElapsed / 500;
            roomBotGroups[botId].animate(frame);
            isBotDeathAnimFinished = false;
        } else {
            dyingBotId = -1;
            isBotDeathAnimFinished = true;
            bloodSprite.setRenderingEnable(false);
        }
    }

    private static void updateAnimationOfBotState(int botId, int botPosState) {
        short startingAnimTime = 0;
        short endAnimTime = 0;
        switch(botPosState) {
            case 0:
                startingAnimTime = 300;
                endAnimTime = 500;
                break;
            case 1:
                startingAnimTime = 900;
                endAnimTime = 1100;
                break;
            case 2:
                startingAnimTime = 0;
                endAnimTime = 200;
                break;
            case 3:
                startingAnimTime = 1200;
                endAnimTime = 1400;
                break;
            case 4:
                startingAnimTime = 600;
                endAnimTime = 800;
        }

        int var4 = (int) (gameTimeUnpaused - botsStateChangeTimePassed[botId] + botsStateChangeTime);
        int var5 = botsStateChangeTime;
        muzzleFlashSprite.setRenderingEnable(false);
        if(var4 > var5 / 2 - 750 && var4 < var5 / 2 + 750) {
            botIsShooting = true;
            roomBotGroups[botId].animate(endAnimTime);
            if(var4 % 300 <= 150) {
                Light light = (Light) roomBotGroups[activeBotId].find(50);
                light.getTransformTo(gameWorld, tmpTrans);
                tmpTrans.get(tmpTransArr);
                muzzleFlashSprite.setTranslation(tmpTransArr[3], tmpTransArr[7], tmpTransArr[11]);
                muzzleFlashSprite.setRenderingEnable(true);
            } else {
                muzzleFlashSprite.setRenderingEnable(false);
            }
        } else {
            botIsShooting = false;
            float var7 = botPositions[botId][0] - botCenterStatePos[botId][0];
            float var8 = botPositions[botId][2] - botCenterStatePos[botId][2];
            float var9 = 0.0F;
            float var10 = botSettings[currentRoom][botId][6];
            float var11 = 0.0F;
            if(var4 <= var5) {
                int var6;
                int var13;
				
				//interpolation ?
                if(var4 <= var5 / 2 - 750) {
                    var9 = botCenterStatePos[botId][0] + var7 * (float) var4 / (float) (botsStateChangeTime / 2 - 750);
                    var11 = botCenterStatePos[botId][2] + var8 * (float) var4 / (float) (botsStateChangeTime / 2 - 750);
                    var13 = (endAnimTime - startingAnimTime) * var4 / (botsStateChangeTime / 2 - 750);
                    var6 = startingAnimTime + var13;
                    roomBotGroups[botId].animate(var6);
                }

                if(var4 >= var5 / 2 + 750) {
                    var9 = botCenterStatePos[botId][0] + var7 * (float) (var5 - var4) / (float) (botsStateChangeTime / 2 - 750);
                    var11 = botCenterStatePos[botId][2] + var8 * (float) (var5 - var4) / (float) (botsStateChangeTime / 2 - 750);
                    var13 = (endAnimTime - startingAnimTime) * (var5 - var4) / (botsStateChangeTime / 2 - 750);
                    var6 = startingAnimTime + var13;
                    roomBotGroups[botId].animate(var6);
                }

                roomBotGroups[botId].setTranslation(var9, var10, var11);
            } else {
                botCurrentPosState[botId] = -1;
            }
        }
    }

    /**Проверка на то, прошло ли достаточно времени с последней атаки*/
    private static boolean checkIfEnoughTimePassedForBot(int botId) {
        return botId == -1 ? 
                true : 
                (botsCount[currentRoom] - botsKilledCount == 1 ? //Если бот остался один
                gameTimeUnpaused >= (long) (botsStateChangeTimePassed[botId] + 2000) //Прошло 2 секунды
                : gameTimeUnpaused >= (long) botsStateChangeTimePassed[botId]); //
    }

    private static boolean checkIfBotIsReadyAndAlive(int botId) {
        return botActive[botId] && !botKilled[currentRoom][botId] && checkIfEnoughTimePassedForBot(activeBotId);
    }

    private static void changeRandomBotPositionAndAnimation() {
        byte botsInTheRoom;
        //если в комнате есть боты
        if((botsInTheRoom = botsCount[currentRoom]) != 0) {
            //берём случайного бота
            float botXpos = 0.0F;
            float botZpos = 0.0F;
            byte aliveBotId = (byte) MathUtils.getRandomNumber(botsInTheRoom);
            float botYpos = botSettings[currentRoom][aliveBotId][6];
            //если он уже активен, ищем следующего
            if(aliveBotId == activeBotId) {
                aliveBotId = (byte) MathUtils.getRandomNumber(botsInTheRoom);
            }

            //если живых ботов не осталось
            if(botsInTheRoom - botsKilledCount == 0) {
                aimAssistActive = false;
                muzzleFlashSprite.setRenderingEnable(false);
                if(dyingBotId != -1) {
                    playBotDeadAnim(dyingBotId, botCurrentPosState[activeBotId]);
                    return;
                }
            }

            byte botState = (byte) MathUtils.getRandomNumber(5);
            onlyOneBotLeft = botsInTheRoom - botsKilledCount == 1;
            byte botId;
            //Если бот последний, забить на рандом и выбрать его
            if(onlyOneBotLeft) {
                for(botId = 0; botId < botsInTheRoom; ++botId) {
                    if(!botKilled[currentRoom][botId]) {
                        aliveBotId = botId;
                        break;
                    }
                }
            }

            //Если поза у бота доступна и никто не умирает, установить эту позу
            if(botSettings[currentRoom][aliveBotId][9 + botState] != 0.0F && checkIfBotIsReadyAndAlive(aliveBotId) && dyingBotId == -1) {
                switch(botState) {
                    case 0:
                    case 1:
                        botXpos = botSettings[currentRoom][aliveBotId][2];
                        botZpos = botSettings[currentRoom][aliveBotId][3];
                        break;
                    case 2:
                        botXpos = botSettings[currentRoom][aliveBotId][0];
                        botZpos = botSettings[currentRoom][aliveBotId][1];
                        break;
                    case 3:
                    case 4:
                        botXpos = botSettings[currentRoom][aliveBotId][4];
                        botZpos = botSettings[currentRoom][aliveBotId][5];
                }

                Scripts.botHitPlayerBefore = false;
                botCurrentPosState[aliveBotId] = botState;
                setBotAngles(aliveBotId, currentRoom, botState);
                activeBotId = aliveBotId;
                setBotPosition(aliveBotId, botXpos, botYpos, botZpos);
                botCenterStatePos[aliveBotId][0] = botSettings[currentRoom][aliveBotId][0];
                botCenterStatePos[aliveBotId][1] = botSettings[currentRoom][aliveBotId][6];
                botCenterStatePos[aliveBotId][2] = botSettings[currentRoom][aliveBotId][1];
                botsStateChangeTimePassed[aliveBotId] = (int) gameTimeUnpaused + botsStateChangeTime;
            }

            
            if(activeBotId != -1 && !botKilled[currentRoom][activeBotId]) {
                if(!Scripts.endingCutscene) {
                    updateAnimationOfBotState(activeBotId, botCurrentPosState[activeBotId]);
                } else {
                    updateAnimationOfBotState(activeBotId, (byte) 2);
                }
            }

            if(Scripts.endingCutscene) {
                for(botId = 0; botId < botsInTheRoom; ++botId) {
                    botXpos = botSettings[currentRoom][botId][0];
                    botZpos = botSettings[currentRoom][botId][1];
                    float yPos = botSettings[currentRoom][botId][6];
                    setBotPosition(botId, botXpos, yPos, botZpos);
                    roomBotGroups[botId].animate(200);
                }
            }

            //Если бот не активен, и не умирает
            if(dyingBotId != -1 && activeBotId != -1) {
                muzzleFlashSprite.setRenderingEnable(false);
                playBotDeadAnim(dyingBotId, botCurrentPosState[activeBotId]);
            } else {
                for(botId = 0; botId < botsInTheRoom; ++botId) {
                    
                    if(Scripts.endingCutscene) {
                        roomBotGroups[botId].setRenderingEnable(true);
                    } else {
                        //Если не активен, но жив
                        if(!botKilled[currentRoom][botId] && botId != activeBotId) {
                            roomBotGroups[botId].setRenderingEnable(false);
                        }

                        //Если умер
                        if(botKilled[currentRoom][botId]) {
                            roomBotGroups[botId].animate(2000);
                        }
                    }
                }

            }
        }
    }

    private static void animateRotatingLight() {
        if(rotatingLightId != -1) {
            int time = (int) gameTimeUnpaused & 1000;
            roomLights[rotatingLightId].animate(time);
        }
    }

    private static void animateBckSprites() {
        for(int i = 0; i < 32; i++) {
            if(bckSpritesAnimEnabled[i]) {
                int w = GameScene.bckSpritesW[i] / 8;
				int h = bckSpritesH[i];
				
                int frameTime = (int) (gameTimeUnpaused + (long) (i * 300)) % 1000 / 200;
                int x = w * frameTime;
				
                bckSprites[i].setCrop(x, 0, w, h);
            }
        }
    }

    private static void animateSpritesAndLights() {
        animateRotatingLight();
        animateBckSprites();
    }

    public static void updateGameTime() {
        if(currentGameState != 0 && currentGameState != 13 && currentGameState != -2) {
            updateGameTicksAndUnpausedTime();
            animateSpritesAndLights();
            updateGameState();
            checkIfTimeToEndGamePassed();
        } else {
            updateGameState();
        }
    }

    private static void countDeadBots() {//проверка на то, убиты ли все враги
        if(botsCount[currentRoom] == 0) {
            allBotsKilledInRoom[currentLocation][currentRoom] = true;
			return;
        }

        int botsDead = 0;

        for(int botId = 0; botId < botsCount[currentRoom]; botId++) {
            if(botKilled[currentRoom][botId]) botsDead++;
        }

        allBotsKilledInRoom[currentLocation][currentRoom] = botsDead == botsCount[currentRoom];
    }

    private static void updateBotsStates() {
        botsAlive = 0;

        for(int i = 0; i < botsCount[currentRoom]; ++i) {
            if(Scripts.endingCutscene) {
                roomBotGroups[i].setRenderingEnable(true);
            } else {
                if(botSettings[currentRoom][i][8] - 1 != currentDoorId) {
                    roomBotGroups[i].setRenderingEnable(false);
                    botActive[i] = false;
                } else {
                    roomBotGroups[i].setRenderingEnable(true);
                    botActive[i] = true;
					
                    if(!botKilled[currentRoom][i]) {
                        botsAlive++;
                    }
                }

                if(allBotsKilledInRoom[currentLocation][currentRoom] || botKilled[currentRoom][i]) {
                    roomBotGroups[i].setRenderingEnable(true);
                }
            }
        }

        changeRandomBotPositionAndAnimation();
    }

    private static void updateWalkAnimation(Node node) {
        int timeElapsed = (int) gameTimeUnpaused - walkAnimStartTime;
		
        if(timeElapsed <= 500) {
			//Rotate camera first
            float rotY = cameraYRot + (walkStartYRot - cameraYRot) * (float) timeElapsed / 500;
            camera.setOrientation((float) cameraYRotOffset + rotY, 0, 1, 0);
			
        } else if(timeElapsed - 500 < walkTimeToWalkPoint + walkTimeToDoor) {
			timeElapsed -= 500; //Subtract camera rotation time
			
            if(timeElapsed <= walkTimeToWalkPoint) {
				//Walk to walk point
                float x = prevCameraPos[0] + (walkPointTargetX - prevCameraPos[0]) * (float) timeElapsed / walkTimeToWalkPoint;
                float y = useThirdPerson ? 0 : cameraPos[1];
                float z = prevCameraPos[2] + (walkPointTargetZ - prevCameraPos[2]) * (float) timeElapsed / walkTimeToWalkPoint;
				
                node.setTranslation(x, y, z);
				
                if(useThirdPerson) {
                    node.setOrientation(cameraYRotOffset + 180 + walkStartYRot, 0, 1, 0);
                } else {
					float rotY = walkStartYRot + (walkPointYRot - walkStartYRot) * (float) timeElapsed / walkTimeToWalkPoint;
                    node.setOrientation(cameraYRotOffset + rotY, 0, 1, 0);
                }
            } else {
				//Walk to door
                timeElapsed -= walkTimeToWalkPoint; //Subtract walk time to walk point
				
				float doorX = doorSettings[currentRoom][walkNextDoorId][0];
				float doorZ = doorSettings[currentRoom][walkNextDoorId][2];
				
                float x = walkPointTargetX + (doorX - walkPointTargetX) * (float) timeElapsed / walkTimeToDoor;
                float y = useThirdPerson ? 0 : cameraPos[1];
                float z = walkPointTargetZ + (doorZ - walkPointTargetZ) * (float) timeElapsed / walkTimeToDoor;
				
                node.setTranslation(x, y, z);
                node.setOrientation(cameraYRotOffset + walkPointYRot + (useThirdPerson ? 180 : 0), 0, 1, 0);
            }

            if(useThirdPerson) {
                int playerModelAnimTime = 2400 + 390 * (int) (gameTimeUnpaused & 1000L) / 1000;
                node.animate(playerModelAnimTime);
            } else {
                CameraXPostRotate();
            }
            
            //restore audio walking
            //if (timeElapsed % 800 < 100)
            if (walkingSoundActive == false)
            {
                walkingSoundActive = true;
                SoundAndVibro.playSound(6);
            }
            //restore audio walking
        } else {
			//Walk anim end
            if(useThirdPerson) playerModel.setRenderingEnable(false);

            loadNextRoom();
        }
    }

    private static void updateCurrentCutscene() {
        try {
            switch(currentGameState) {
                case 1:
                    if(useThirdPerson) updateWalkAnimation(playerModel);
					else updateWalkAnimation(camera);
					
                    return;
                default:
                    if(Scripts.endingCutscene && !showFinalDialog) {
                        boolean var12 = false;
                        int var13 = (int) gameTimeUnpaused - koboldCutsceneStartTime;
                        boolean var14 = false;
                        setCameraPos(61.5F, 3.0F, 134.0F);
                        playerModel.setTranslation(75.2F, 0.0F, 130.0F);
                        playerModel.setOrientation((float) (cameraYRotOffset + 180), 0.0F, 1.0F, 0.0F);
                        if(var13 <= 6000) {
                            if(var13 >= 5000) {
                                playerModel.animate(1700 + 300 * (1000 - (6000 - var13)) / 1000);
                                camera.setOrientation((float) (cameraYRotOffset + 65 + 23 * (1000 - (6000 - var13)) / 1000), 0.0F, 1.0F, 0.0F);
                                camera.postRotate(-10.0F, 1.0F, 0.0F, 0.0F);
                            } else {
                                camera.setOrientation((float) (cameraYRotOffset + 65), 0.0F, 1.0F, 0.0F);
                                camera.postRotate(-10.0F, 1.0F, 0.0F, 0.0F);
                                playerModel.animate(200);
                            }
                        } else {
                            finalDialogStartTime = (int) gameTimeUnpaused;
                            showFinalDialog = true;
                        }
                    } else if(showFinalDialog) {
                        int var0 = (int) gameTimeUnpaused - finalDialogStartTime;
                        float var2 = 61.5F;
                        float var3 = 75.2F;
                        float var4 = 3.0F;
                        float var6 = 134.0F;
                        float var7 = 130.0F;
                        if(var0 <= 3750) {
                            float var8 = var2 + (var3 - var2) * (float) var0 / (float) 5000;
                            float var9 = var4 + 0.0F * (float) var0 / (float) 5000;
                            float var10 = var6 + (var7 - var6) * (float) var0 / (float) 5000;
                            setCameraPos(var8, var9, var10);
                        } else {
                            Scripts.endingCutscene = false;
                            Scripts.startDialog((short) 20, (byte) 0);
                            showFinalDialog = false;
                        }
                    } else {
                        SetCameraOrientAndPostRotate();
                        setCameraPos(cameraPos[0], cameraPos[1], cameraPos[2]);
                        shakeCameraIfActive();
                    }

            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }

    public static void SetToNullAllWorldnMeshMassives() {
        rotatingLightId = -1;

        int number;
        for(number = 0; number < 32; ++number) {
            gameWorld.removeChild(bckMeshes[number]);
            bckMeshes[number] = null;
            gameWorld.removeChild(bckSprites[number]);
            bckSprites[number] = null;
        }

        System.gc();

        for(number = 0; number < 10; ++number) {
            gameWorld.removeChild(roomBotGroups[number]);
            roomBotGroups[number] = null;
        }

        System.gc();

        for(number = 0; number < 32; ++number) {
            gameWorld.removeChild(roomMeshes[number]);
            roomMeshes[number] = null;
        }

        System.gc();

        for(number = 0; number < 10; ++number) {
            gameWorld.removeChild(activableObjMeshes[number]);
            activableObjMeshes[number] = null;
        }

        System.gc();

        for(number = 0; number < 32; ++number) {
            gameWorld.removeChild(roomLights[number]);
            roomLights[number] = null;
        }

        System.gc();

        for(number = 0; number < 10; ++number) {
            gameWorld.removeChild(staticBotMdlGroup[number]);
            staticBotMdlGroup[number] = null;
        }

        System.gc();
        gameWorld.removeChild(playerModel);
        playerModel = null;
        System.gc();
        gameWorld.removeChild(ambientLight);
        ambientLight = null;
        System.gc();
        notInRoom = true;
    }

    private static void loadNextRoom() {
        prepareObjectArraysForNextRoom();
        useThirdPerson = false;
        currentRoom = nextRoom;
        ConfigureAndActivateCamera();
        setDialogWindowState((short) 13);
        //audioStuttersFix
            walkingSoundActive = false;
            SoundAndVibro.stopPlayingSound();
        //
    }

    public static void renderWorld(Graphics graphics) {
        try {
            if(currentGameState != -2 && currentGameState != 0 && currentGameState != 13 && currentGameState != 14) {
                graphics3D.bindTarget(graphics, true, Graphics3D.OVERWRITE);
                
                //9.01.25 Меняем размер области отрисовки при прицеливании
                if (Scripts.OpticalSight) {
                    int w1 = (MainMenuScreen.scrWidth - PlayerHUD.crosshairImages[2].getWidth()) / 2;
                    int h1 = (MainMenuScreen.scrHeight - PlayerHUD.crosshairImages[2].getHeight()) / 2;
                    graphics3D.setViewport(w1, h1,
                            PlayerHUD.crosshairImages[2].getWidth(),
                            PlayerHUD.crosshairImages[2].getHeight());
                    
                    camera.setPerspective(30.0F, (float) PlayerHUD.crosshairImages[2].getWidth() / (float) PlayerHUD.crosshairImages[2].getHeight(), 0.1F, 10000.0F);
                } else {
                    graphics3D.setViewport(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
                    
                    camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
                }
                //
                
                graphics3D.render(gameWorld);
                graphics3D.releaseTarget();
                
                if(fpsCounterEnabled==1) {
                    int fps = MathUtils.calcFps();
                    graphics.setColor(2670136);
                    //graphics_cont.fillRect(200, 12, 60, 15);
                    //graphics_cont.setColor(1594115);
                    graphics.drawString(String.valueOf(fps), 200, 12, 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//<editor-fold defaultstate="collapsed" desc="Методы поворота камеры">  
//<editor-fold defaultstate="collapsed" desc="Ограничители поворота камеры">
    private static float XRotationLimit(float rotation_angle) //Вниз-вверх
    {
        return rotation_angle >= 50.0F ? 50.0F : (rotation_angle <= -50.0F ? -50.0F : rotation_angle); //Ограничитель в -50/+50F
    }

    private static float YRotationLimit(float rotation_angle) //Влево-вправо
    {
        float max_rotation = doorSettings[currentRoom][currentDoorId][5]; //Справа
        float min_rotation = doorSettings[currentRoom][currentDoorId][6]; //Слева
        return rotation_angle <= -min_rotation ? -min_rotation : (rotation_angle >= max_rotation ? max_rotation : rotation_angle); //Если выходит за рамки обнулить
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Поворот влево-вправо">

    public static void TurnLeftTheCamera() {
        if(!aimAssistActive) {
            cameraYRot += Keys.leftAccelerate ? 8.0F : 2.0F;
            cameraYRot = YRotationLimit(cameraYRot);
        }
    }

    public static void TurnRightTheCamera() {
        if(!aimAssistActive) {
            cameraYRot -= Keys.rightAccelerate ? 8.0F : 2.0F;
            cameraYRot = YRotationLimit(cameraYRot);
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Поворот вверх или вниз">

    public static void RaiseTheCamera() {
        if(!aimAssistActive) {
            float float_num = Keys.upAccelerate ? 8.0F : 2.0F; //Если клавиша нажата равно 8
            cameraXRot += float_num;
            cameraXRot = XRotationLimit(cameraXRot);
        }
    }

    public static void LowerTheCamera() {
        if(!aimAssistActive) {
            float float_num = Keys.downAccelerate ? 10.0F : 2.0F;
            cameraXRot -= float_num;
            cameraXRot = XRotationLimit(cameraXRot);
        }
    }
//</editor-fold>
//</editor-fold>
}
