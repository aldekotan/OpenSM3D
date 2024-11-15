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

public final class RenderEngine {

    public static int currentLocation;
    public static int nextLocation;
    
    public static byte currentRoom;
    private static byte nextRoom;
    
    public static boolean[] locationCompleted = new boolean[17];
    public static boolean[] locationTaskMark = new boolean[17];
    public static boolean[] locationCampMark = new boolean[17];
    public static boolean gamePaused;
    public static Graphics3D graphics3D;
    public static World gameWorld = new World();
    private static float[][] float_doublemassive_1st;
    public static short currentGameState;
    public static short prevGameState;
    
    //Camera
    public static Camera camera;
    private static float[] cameraPos = new float[3];
    private static float[] prevCameraPos = new float[3]; //Used in cutscenes?
    public static byte currentDoorId;
    private static float cameraXRot, cameraYRot;
    private static int cameraYRotOffset;
	
    private static Group playerModel;

    public static byte roomsCount;
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
    
    private static int walkAnimStartTime;
    public static byte walkCurrentDoorId;
    public static int walkNextDoorId;
    private static float walkPointTargetX;
    private static float walkPointTargetZ;
    private static int walkTimeToWalkPoint;
    private static int walkTimeToDoor;
    private static float walkStartYRot;
    private static float walkPointYRot;
	
    private static byte[] walkPointsCount = new byte[15];
    private static float[][][] walkPoints = new float[15][4][5];
    //[roomId][walkPoint][settings]
    
    //?? [0]
    //x [1]
    //z [2]
    //walk to time
    //walk from time
    
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
    private static byte[] roomMeshesMdlId;
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
    private static byte[] roomActivableMeshesMdlId;
    private static float[][][] activableObjSettings = new float[15][10][8];
    //[roomId][obj][settings]
    //x [0]
    //y [1]
    //z [2]
    //?? unused [3]
    //rotation y [4]
    //?? unused [5]
    //model id [6]
    //activable object id (0 is unusable) [7]
    
    public static byte[] botsCount = new byte[15];
    public static Group[] roomBotGroups;
    private static float[][][] botSettings = new float[15][10][15];
    //[roomId][bot][settings]
    //x, z [0,1] [2,3] [4,5]
    //y [6]
    //rotation ?? [7]
    //position number ?? wtf [8]
    //idk.. ?? [9+]
    //idk.. ?? [14]
    public static boolean[][] botKilled = new boolean[15][10];
    //[roomId][bot]
    private static float[][] botPositions = new float[10][3];
    //[bot][x, y, z]
    
    public static byte[] itemsForKillingAllCount = new byte[15];
    public static short[][] itemsForKillingAll = new short[15][10];
    //[roomId]{items}
    
    public static byte[] damageZonesTypes = new byte[15];
    //[roomId]
    private static byte[] damageZonesUnused = new byte[15];
    //unused ??
    
    public static byte var_7ee;
    public static boolean[][] var_84a;
	
	//Objects data
    public static boolean objectsDataLoaded;
    public static byte[] objectModelSource;
    public static byte[] objectUnusedField;
    public static String[] objectModelName;
    public static String[] objectTextureName;
    public static byte[] objectLighstBlendMode;
    public static byte[] objectM3GId;
    public static float[] objectScale;
    public static byte[] objectStaticBotWeaponId;
    public static byte[] objectStaticBotAnimTime;
	
    public static float[] tmpMehsScale;
    private static boolean resetCachedAssetsArrays;
    public static boolean showIntro;
	
    private static Sprite3D bloodSprite;
    private static Sprite3D muzzleFlashSprite;
	
    public static World persWorld;
	
    public static float[] var_143c;
    public static float[] var_1485;
    public static float[] var_14de;
    public static float[] var_1539;
    public static float[] var_156e;
    public static float[] var_157d;
    public static float[] objYreachAngle;
    public static float[] objXMaxReachAngle;
    public static float[] objXMinReachAngle;
    private static boolean var_1669;
    public static boolean[] var_16a2;
    public static boolean[] var_1700;
    public static boolean[] activableObjsStatus;
    public static boolean DamageEffect;
    public static byte var_17f2;
    public static byte botIdUndercursor; //not sure
    public static byte TypeOfInteractWithObjectAhead;
    private static int var_185a;
    private static int var_1877;
    private static float var_18b0;
    private static float var_18bf;
    private static boolean CameraMovementDeactive;
    public static boolean useThirdPerson;
    public static final byte[] levelUseThirdperson;
    private static int var_1a13;
    private static int var_1a54;
    private static int var_1ab8;
    public static boolean showFinalDialog;
    private static byte var_1afa;
    private static final int screen_width;
    private static final int screen_height;
    public static long var_1bb1;
    public static long renderTimeOnly3D;
    public static long currentTimeMill;
    private static int TickDurationMills;
    public static int var_1c84;
    public static boolean var_1cda;
    public static Transform transform;
    public static float[] var_1d3c;
    public static boolean IsWayAheadLocked;
    public static byte var_1d93;
    public static boolean var_1db5;
    private static int[] var_1dce;
    private static int[] var_1e05;
    private static int var_1e37;
    private static byte[] var_1e92;
    public static byte var_1eae;
    private static boolean var_1ec4;
    public static byte var_1fb1;
    public static boolean[] botActive;
    private static byte botsAlive;
    public static boolean var_20a4;
   //Возвращаю старый дебаггер
    private static int fpsCounterEnabled;

    static {
        float_doublemassive_1st = new float[10][3];
        currentGameState = -2;
        
        var_84a = new boolean[17][15];
        objectModelSource = new byte[127];
        objectUnusedField = new byte[127];

        objectModelName = new String[127];
        objectTextureName = new String[127];

        objectLighstBlendMode = new byte[127];
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
        roomMeshesMdlId = new byte[32];
        roomActivableMeshesMdlId = new byte[10];
        rotatingLightId = -1;
        var_143c = new float[5];
        var_1485 = new float[5];
        var_14de = new float[5];
        var_1539 = new float[10];
        var_156e = new float[10];
        var_157d = new float[10];
        objYreachAngle = new float[10];
        objXMaxReachAngle = new float[10];
        objXMinReachAngle = new float[10];
        var_16a2 = new boolean[5];
        var_1700 = new boolean[10];
        activableObjsStatus = new boolean[10];
        var_1877 = -1;
        levelUseThirdperson = new byte[]{0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
        screen_width = MainMenuScreen.scrWidth;
        screen_height = MainMenuScreen.scrHeight;
        transform = new Transform();
        var_1d3c = new float[16];
        IsWayAheadLocked = true;
        var_1d93 = -1;
        var_1dce = new int[10];
        var_1e05 = new int[10];
        var_1e37 = 2000;
        var_1e92 = new byte[10];
        var_1eae = -1;
        bckSpritesW = new int[32];
        bckSpritesH = new int[32];
        botActive = new boolean[10];
        var_20a4 = true;

        try {
        fpsCounterEnabled = Integer.parseInt(Main.main.getAppProperty("ShowFPS"));
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    public static void init() {
        var_1e37 = 2000;
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
        var_16a2 = new boolean[5];
        activableObjsStatus = new boolean[10];
        var_1700 = new boolean[10];
        Scripts.setAllActorStatsToDefault();
    }

    private static void resetLocation() {
        Scripts.var_2204 = false;
        Scripts.locationExclusiveItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        botActive = new boolean[10];
        botKilled = new boolean[15][10];
    }

    private static void loadLocation(int levelId) {//что-то с загрузкой локации
        resetLocation();
        loadLocationData(levelId);
        LoadingScreen.RunGarbageCollector();
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
        SetTranformOfAllObjects(currentRoom);
        gamePaused = false;
    }

    private static void SetTranformOfAllObjects(byte place_number) {
        setRoomEnvironment(place_number);
        LoadingScreen.RunGarbageCollector();
        setRoomLights(place_number);
        LoadingScreen.RunGarbageCollector();
        setRoomBckMeshes(place_number);
        LoadingScreen.RunGarbageCollector();
        setRoomBots(place_number);
        LoadingScreen.RunGarbageCollector();
        setRoomActivableObjs(place_number); //настраивает группы или меши в дополнительной базе
        LoadingScreen.RunGarbageCollector();
        setRoomMeshes(place_number);
        LoadingScreen.RunGarbageCollector();
        SetCameraTranslationAndOrientation(place_number, currentDoorId);
        LoadingScreen.RunGarbageCollector();
    }

    public static byte sub_165(byte var0, byte[] var1) {
        for(byte var2 = 0; var2 < var1.length; ++var2) {
            if(var0 == var1[var2]) {
                return var2;
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

    public static short SearchInteractionPoint(byte type) //
    {
        return (short) ((int) activableObjSettings[currentRoom][type][7]);
    }

    public static void setActiveObjState(byte id, short state) {
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
                        objectLighstBlendMode[objId] = dis.readByte();
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

                //??
                walkPointsCount[roomId] = dis.readByte();

                for(int i = 0; i < walkPointsCount[roomId]; i++) {
                    //??
                    walkPoints[roomId][i][0] = dis.readByte() + dis.readByte();

                    //x, z
                    walkPoints[roomId][i][1] = dis.readInt() / 100.0F;
                    walkPoints[roomId][i][2] = dis.readInt() / 100.0F;
                    
                    //walk to time, walk from time
                    walkPoints[roomId][i][3] = dis.readByte();
                    walkPoints[roomId][i][4] = dis.readByte();
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
                    
                    //rot x, rot y, rot z
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
                    
                    //unused ??
                    activableObjSettings[roomId][i][3] = dis.readInt() / 100.0F;
                    
                    //rotation y
                    activableObjSettings[roomId][i][4] = dis.readInt() / 100.0F;
                    
                    //unused ??
                    activableObjSettings[roomId][i][5] = dis.readInt() / 100.0F;
                    
                    //model id, activable object id
                    activableObjSettings[roomId][i][6] = dis.readUnsignedByte();
                    activableObjSettings[roomId][i][7] = dis.readUnsignedByte();
                }

                //Bots
                botsCount[roomId] = dis.readByte();

                for(int i = 0; i < botsCount[roomId]; i++) {
                    //uuh
                    for(int t = 0; t < 15; ++t) {
                        if(t < 8) {
                            botSettings[roomId][i][t] = dis.readInt() / 100.0F;
                        } else {
                            botSettings[roomId][i][t] = dis.readByte();
                        }
                    }
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
                walkPoints[roomdId][i][2] *= -1;
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
            roomActivableMeshesMdlId[i] = (byte) activableObjSettings[roomId][i][6];
        }
		
        if(botsCount[roomId] == 0) {
            roomBotGroups[0].setRenderingEnable(false);
        }

        for(int i = 0; i < botsCount[roomId]; i++) {
			Bot.loadBot((int) botSettings[roomId][i][14], i);
        }

        for(int i = 0; i < meshesCount[roomId]; i++) {
            addObjectToGameWorld((int) meshSettings[roomId][i][6], i, (byte) 2);
            roomMeshesMdlId[i] = (byte) meshSettings[roomId][i][6];
        }

        for(int i = 0; i < lightsCount[roomId]; i++) {
            addObjectToGameWorld((int) lightSettings[roomId][i][4], i, (byte) 3);
        }
		
        var_20a4 = false;
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
        var_1eae = -1;
        var_1fb1 = 0;

        for(int i = 0; i < botsCount[roomId]; i++) {
            var_1dce[i] = (int) renderTimeOnly3D + var_1e37;
            var_1e05[i] = (int) renderTimeOnly3D + 500;
            var_1e92[i] = 2;
			
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

        sub_1072();
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
			
			float rotY = activableObjSettings[roomId][i][4];
			
            if(activableObjMeshes[i] != null) {
                activableObjMeshes[i].setOrientation(rotY, 0.0F, 1.0F, 0.0F);
                activableObjMeshes[i].setTranslation(x, y, z);
            } else if(staticBotMdlGroup[i] != null) {
                staticBotMdlGroup[i].setOrientation(rotY, 0.0F, 1.0F, 0.0F);
                staticBotMdlGroup[i].setTranslation(x, y, z);
            }
        }
    }

    private static float sub_5d9(float x1, float y1, float x2, float y2, float x3, float y3) 
    {
        float dist1to2 = MathUtils.distance2D(x1, y1, x2, y2);
        float dist1to3 = MathUtils.distance2D(x1, y1, x3, y3);
        float dist2to3 = MathUtils.distance2D(x2, y2, x3, y3);
        return dist1to2 != 0.0F && dist1to3 != 0.0F ? (float) MathUtils.acos((dist1to2 * dist1to2 + dist1to3 * dist1to3 - dist2to3 * dist2to3) / (2.0F * dist1to2 * dist1to3)) : -500.0F;
    }

    private static float sub_5e5(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
        float var9 = MathUtils.distance3D(var0, var1, var2, var3, var4, var5);
        float var10 = MathUtils.distance3D(var0, var1, var2, var6, var7, var8);
        float var11 = MathUtils.distance3D(var3, var4, var5, var6, var7, var8);
        return (float) MathUtils.acos((var9 * var9 + var10 * var10 - var11 * var11) / (2.0F * var9 * var10));
    }

    private static void ConfigureAndActivateCamera() {
        byte var0;
        for(var0 = 0; var0 < var_16a2.length; ++var0) {
            var_16a2[var0] = false;
        }

        for(var0 = 0; var0 < 10; ++var0) {
            var_1700[var0] = false;
        }

        cameraYRot = 0.0F;
        cameraXRot = 0.0F;
        bckSpritesAnimEnabled = new boolean[32];
        var_185a = (int) renderTimeOnly3D;
        var_1cda = false;
        CameraMovementDeactive = false;
        doorUnlocked = new boolean[doorsCount[currentRoom]];
        var_16a2 = new boolean[5];
        activableObjsStatus = new boolean[10];
        var_1700 = new boolean[10];

        for(var0 = 0; var0 < doorUnlocked.length; ++var0) {
            doorUnlocked[var0] = doorSettings[currentRoom][var0][7] == 0.0F;
        }

        var_1d93 = -1;
        Scripts.sub_8b();
    }

    private static void SetCameraTranslationAndOrientation(byte roomId, byte doorId) {
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
        
        float camX = cameraPos[0];
        float camZ = cameraPos[2];
        
        float lookAtX = roomSettings[roomId][0];
        float lookAtZ = roomSettings[roomId][2];
        
        float[] vec1 = getVectorForCrossProduct(camX, camZ, camX, camZ - 10.0F);
        float[] vec2 = getVectorForCrossProduct(camX, camZ, lookAtX, lookAtZ);
        
        cameraYRotOffset = getCrossProductSign(vec1, vec2) *
                (int) getCamToRoomCenterAngle(roomId);
        SetCameraTranslation(cameraPos[0], cameraPos[1], cameraPos[2]);
        backupCameraPos();
        camera.setOrientation((float) cameraYRotOffset, 0.0F, 1.0F, 0.0F); //задаёт направление камеры

        byte objId;
        for(objId = 0; objId < doorsCount[roomId]; ++objId) {
            sub_79c(objId, roomId);
        }

        for(objId = 0; objId < botsCount[roomId]; ++objId) {
            sub_775(objId, roomId, var_1e92[objId]);
        }

        for(objId = 0; objId < activableObjsCount[roomId]; ++objId) {
            sub_825(objId, roomId);
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

    private static void sub_775(byte var0, byte var1, byte var2) {
        float var3 = roomSettings[var1][0];
        float var4 = roomSettings[var1][2];
        float var5 = cameraPos[0];
        float var6 = cameraPos[1];
        float var7 = cameraPos[2];
        float var9 = cameraPos[1] - 1.6F;
        var_1669 = var2 == 0 || var2 == 4;
        float var11 = 0.0F;
        float var12 = botSettings[var1][var0][6] + (var_1669 ? 1.0999999F : 1.8F);
        float var13 = var_1669 ? botSettings[var1][var0][6] : botSettings[var1][var0][6] + 0.9F;
        float var14 = 0.0F;
        switch(var2) {
            case 0:
            case 1:
                var11 = botSettings[var1][var0][2];
                var14 = botSettings[var1][var0][3];
                break;
            case 2:
                var11 = botSettings[var1][var0][0];
                var14 = botSettings[var1][var0][1];
                break;
            case 3:
            case 4:
                var11 = botSettings[var1][var0][4];
                var14 = botSettings[var1][var0][5];
        }

        var_1539[var0] = sub_5d9(var5, var7, var11, var14, var3, var4);
        float[] var15 = getVectorForCrossProduct(var5, var7, var3, var4);
        float[] var16 = getVectorForCrossProduct(var5, var7, var11, var14);
        byte var17 = getCrossProductSign(var15, var16);
        var_1539[var0] *= (float) var17;
        if(var12 <= var6) {
            var_156e[var0] = -(90.0F - sub_5e5(var5, var6, var7, var11, var12, var14, var5, var9, var7));
            var_157d[var0] = -(90.0F - sub_5e5(var5, var6, var7, var11, var13, var14, var5, var9, var7));
        } else {
            var_156e[var0] = sub_5e5(var5, var6, var7, var11, var12, var14, var5, var9, var7) - 90.0F;
            var_157d[var0] = sub_5e5(var5, var6, var7, var11, var13, var14, var5, var9, var7) - 90.0F;
        }
    }

    private static void sub_79c(byte var0, byte var1) {
        float var2 = roomSettings[var1][0];
        float var3 = roomSettings[var1][2];
        float var4 = cameraPos[0];
        float var5 = cameraPos[1];
        float var6 = cameraPos[2];
        float var7 = doorSettings[var1][var0][0];
        float var8;
        float var9 = (var8 = doorSettings[var1][var0][1] + 0.4F) - 2.0F;
        float var10 = doorSettings[var1][var0][2];
        float var12 = cameraPos[1] - 1.6F;
        var_143c[var0] = sub_5d9(var4, var6, var7, var10, var2, var3);
        float[] var14 = getVectorForCrossProduct(var4, var6, var2, var3);
        float[] var15 = getVectorForCrossProduct(var4, var6, var7, var10);
        byte var16 = getCrossProductSign(var14, var15);
        var_143c[var0] *= (float) var16;
        if(loadedDoorsSettings[currentRoom][var0][0] - 1 < 0) {
            var_143c[var0] = -500.0F;
        }

        if(var8 <= var5) {
            var_1485[var0] = -(90.0F - sub_5e5(var4, var5, var6, var7, var8, var10, var4, var12, var6));
            var_14de[var0] = -(90.0F - sub_5e5(var4, var5, var6, var7, var9, var10, var4, var12, var6));
        } else {
            var_1485[var0] = sub_5e5(var4, var5, var6, var7, var8, var10, var4, var12, var6) - 90.0F;
            var_14de[var0] = sub_5e5(var4, var5, var6, var7, var9, var10, var4, var12, var6) - 90.0F;
        }
    }

    private static float[] sub_7cb(byte walkCurrentDoorId, boolean var1) {
        float var2 = roomSettings[currentRoom][0];
        float var3 = roomSettings[currentRoom][2];
        float var4 = cameraPos[0];
        float var5 = cameraPos[2];
        float var6 = walkPointTargetX;
        float var7 = walkPointTargetZ;
        float var8 = doorSettings[currentRoom][walkCurrentDoorId][0];
        float var9 = doorSettings[currentRoom][walkCurrentDoorId][2];
        float var10 = doorSettings[currentRoom][walkNextDoorId - 1][0];
        float var11 = doorSettings[currentRoom][walkNextDoorId - 1][2];
        useThirdPerson = var1;
        float var12 = sub_5d9(var4, var5, var6, var7, var2, var3);
        float[] var13 = getVectorForCrossProduct(var4, var5, var2, var3);
        float[] var14 = getVectorForCrossProduct(var4, var5, var6, var7);
        byte var15 = getCrossProductSign(var13, var14);
        float var16 = sub_5d9(var6, var7, var10, var11, var8, var9);
        var13 = getVectorForCrossProduct(var6, var7, var10, var11);
        var14 = getVectorForCrossProduct(var6, var7, var8, var9);
        byte var17 = getCrossProductSign(var13, var14);
        return new float[]{var12 * (float) var15, (180.0F - var16) * (float) var17};
    }

    private static void sub_825(byte objId, byte roomId) {//wip
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
        objYreachAngle[objId] = sub_5d9(xCamPos, zCamPos, xObjPos, zObjPos, xRoomPos, zRoomPos);//2d distance acos
        float[] var14 = getVectorForCrossProduct(xCamPos, zCamPos, xRoomPos, zRoomPos);//3d distance acos
        float[] var15 = getVectorForCrossProduct(xCamPos, zCamPos, xObjPos, zObjPos);//3d distance acos
        byte var16 = getCrossProductSign(var14, var15);
        objYreachAngle[objId] *= (float) var16;
        if(yCamPos <= yObjMaxPos) {
            objXMaxReachAngle[objId] = -(90.0F - sub_5e5(xCamPos, yCamPos, zCamPos, xObjPos, yObjMaxPos, zObjPos, xCamPos, yCamMinPos, zCamPos));
            objXMinReachAngle[objId] = -(90.0F - sub_5e5(xCamPos, yCamPos, zCamPos, xObjPos, yObjMinPos, zObjPos, xCamPos, yCamMinPos, zCamPos));
        } else {
            objXMaxReachAngle[objId] = sub_5e5(xCamPos, yCamPos, zCamPos, xObjPos, yObjMaxPos, zObjPos, xCamPos, yCamMinPos, zCamPos) - 90.0F;
            objXMinReachAngle[objId] = sub_5e5(xCamPos, yCamPos, zCamPos, xObjPos, yObjMinPos, zObjPos, xCamPos, yCamMinPos, zCamPos) - 90.0F;
        }
    }

    private static void sub_844() {//дверь открыта, если враги мертвы
        PlayerHUD.doorLocked = false;
        if(var_84a[currentLocation][currentRoom] || botsAlive == 0) {
            for(byte var0 = 0; var0 < doorsCount[currentRoom]; ++var0) {
                if(cameraYRot > (float) ((int) var_143c[var0] - 10) && cameraYRot < (float) ((int) var_143c[var0] + 10) && cameraXRot >= var_14de[var0] && cameraXRot <= var_1485[var0]) {
                    var_16a2[var0] = true;
                    PlayerHUD.doorLocked = !doorUnlocked[var0];
                } else {
                    var_16a2[var0] = false;
                }
            }

        }
    }

    private static void sub_86e() {
        byte var0 = var_1eae;
        if(var_1eae >= 0) {
            if(cameraYRot > var_1539[var0] - 10.0F && cameraYRot < var_1539[var0] + 10.0F && cameraXRot >= var_157d[var0] - 5.0F && cameraXRot <= var_156e[var0] + 5.0F) {
                var_1700[var0] = var_1e92[var_1eae] != -1;
            } else {
                var_1700[var0] = false;
            }
        }
    }

    private static void sub_8d1() {//активный объект доступен, если враги мертвы
        if(var_84a[currentLocation][currentRoom] || botsAlive == 0) {
            for(byte objId = 0; objId < activableObjsCount[currentRoom]; ++objId) {
                if(cameraYRot > objYreachAngle[objId] - 5.0F && cameraYRot < objYreachAngle[objId] + 5.0F && cameraXRot >= objXMinReachAngle[objId] && cameraXRot <= objXMaxReachAngle[objId]) {
                    activableObjsStatus[objId] = true;
                } else {
                    activableObjsStatus[objId] = false;
                }
            }

        }
    }

    public static boolean sub_911() {
        return var_1eae >= 0 && var_1539[var_1eae] - cameraYRot >= 0.0F;
    }

    private static boolean sub_966() {
        if((int) renderTimeOnly3D - var_185a >= 1000) {
            var_185a = (int) renderTimeOnly3D;
            return true;
        } else {
            return false;
        }
    }

    private static void antiradTimer() {
        if(Scripts.IsAntiradIsUsed) {
            Scripts.AntiradDuration -= TickDurationMills;
            if(Scripts.AntiradDuration <= 0) {
                Scripts.IsAntiradIsUsed = false;
                Scripts.AntiradDuration = 20000;
            }
        }

        if(damageZonesTypes[currentRoom] == -1) {
            StopDamageEffect();
        } else if(sub_966()) {
            DealTerrainDamageThroughArmor();
        } else {
            if(renderTimeOnly3D - (long) var_185a >= 500L) {
                StopDamageEffect();
            }

        }
    }

    private static void DealTerrainDamageThroughArmor() {
        if(!Scripts.IsAntiradIsUsed) {
            int hitpoints = damageZonesTypes[currentRoom] > 0 ? 7 : 4;//Радиация или аномалия, сильный или слабый урон. Что есть что?
            DamageEffect = true;
            Scripts.CauseDamageToActor(hitpoints - hitpoints * ((Scripts.playerAnomalyResistance + Scripts.playerRadiationResistance) / 2) / 100);
        }
    }

    private static void StopDamageEffect() {
        DamageEffect = false;
    }

    public static boolean sub_a33() {
        for(byte var0 = 0; var0 < doorsCount[currentRoom]; ++var0) {
            if(var0 != currentDoorId && var_16a2[var0] && (doorUnlocked[var0] || isItemInList(doorKeys[currentRoom][var0], Scripts.locationExclusiveItems))) {
                var_17f2 = var0;
                return true;
            }
        }

        return false;
    }

    public static boolean sub_a69() {
        for(byte var0 = 0; var0 < activableObjsCount[currentRoom]; ++var0) {
            if(activableObjsStatus[var0] && SearchInteractionPoint(var0) != 0) {
                TypeOfInteractWithObjectAhead = var0;
                return true;
            }
        }

        return false;
    }

    public static void sub_ab1(byte var0) {
        var_1d93 = var0;
        var_1e05[var0] = (int) renderTimeOnly3D + 500;
    }

    public static boolean sub_acc() {//доведение прицела до врага, если жив
        byte var0 = var_1eae;
        botIdUndercursor = -100;
        if(var0 < 0) {
            return false;
        } else if(var_1700[var0] && !botKilled[currentRoom][var0] && botActive[var0] && !var_84a[currentLocation][currentRoom]) {
            botIdUndercursor = var0;
            
            if(!Scripts.endingCutscene) {
                if(var_1877 == -1) {
                    var_1877 = (int) renderTimeOnly3D;
                    var_18b0 = cameraYRot;
                    var_18bf = cameraXRot;
                }

                if(renderTimeOnly3D - (long) var_1877 < 300L) {
                    cameraYRot = var_18b0 + (var_1539[var0] - var_18b0) * (float) (renderTimeOnly3D - (long) var_1877) / 300.0F;
                    float var1 = var_156e[var0] - (var_156e[var0] - var_157d[var0]) / (float) (var_1669 ? 4 : 2);
                    cameraXRot = var_18bf + (var1 - var_18bf) * (float) (renderTimeOnly3D - (long) var_1877) / 300.0F;
                } else {
                    var_1877 = -1;
                }
            }

            CameraMovementDeactive = true;
            return true;
        } else {
            CameraMovementDeactive = false;
            return false;
        }
    }

    public static void setDialogWindowState(short state) {
        prevGameState = currentGameState;//Предыдущее окно?
        currentGameState = state;   //Текущее окно
        if(currentGameState == 2 && prevGameState == 9) {
            ResourseManager.runGarbageCollector();
        }

        switch(currentGameState) {
            case 0:
                PlayerHUD.garbageCollected = 0;
                SoundAndVibro.stopPlayingSound();
                if(!showIntro) {
                    ResourseManager.runGarbageCollector();
                }
                break;
            case 1:
                if(var_1afa == walkCurrentDoorId) {
                    return;
                }

                for(int i = 0; i < walkPointsCount[currentRoom]; ++i) {
                    walkNextDoorId = ((int) walkPoints[currentRoom][i][0]) - 1 - walkCurrentDoorId;
					
                    if(walkNextDoorId == var_1afa + 1) {
                        walkPointTargetX = walkPoints[currentRoom][i][1];
                        walkPointTargetZ = walkPoints[currentRoom][i][2];
						
                        if(walkNextDoorId - 1 > walkCurrentDoorId) {
                            walkTimeToWalkPoint = (int) walkPoints[currentRoom][i][3] * 1000 / 3;
                            walkTimeToDoor = (int) walkPoints[currentRoom][i][4] * 1000 / 3;
                        } else {
                            walkTimeToWalkPoint = (int) walkPoints[currentRoom][i][4] * 1000 / 3;
                            walkTimeToDoor = (int) walkPoints[currentRoom][i][3] * 1000 / 3;
                        }
						
                        break;
                    }
                }

                float[] var3 = sub_7cb(walkCurrentDoorId, levelUseThirdperson[currentLocation] == 1);
                walkStartYRot = var3[0] != 180.0F ? var3[0] : 0.0F;
                walkPointYRot = walkStartYRot + var3[1];
                walkAnimStartTime = (int) renderTimeOnly3D;
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
        var_1a13 = (int) renderTimeOnly3D + var1;
        prevGameState = currentGameState;
        currentGameState = var0;
    }

    private static void sub_b67() {
        if(renderTimeOnly3D >= (long) var_1a13) {
            switch(currentGameState) {
                case 4:
                    sub_b83();
                    return;
                case 11:
                    sub_b83();
            }
        }

    }

    private static void sub_b83() {
        GameScreen.clearMemoryAndLoadUIImages();
    }

    private static void sub_bd0() {
        Scripts.koboldDialogState = 2;
        loadPlayerModel();
        preparePlayerModel(Scripts.playerActiveWeapon);
        Scripts.startDialog((short) 19, (byte) 0);
        var_1e37 = 500;
        var_1a54 = (int) renderTimeOnly3D;
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
                if(var_1afa == walkCurrentDoorId) {
                    PlayerHUD.garbageCollected = 0;
                    sub_115f();
                    return;
                }

                sub_1117();
                return;
            case 2://Основное игровое окно
                sub_103b();//подсчёт мёртвых и живых
                sub_1072();//рендер неписей
                sub_86e();//??
                sub_844();//doors obj status
                sub_8d1();//activable obj status
                antiradTimer();
                if(Scripts.playerHealth <= 0) {
                    endGame((short) 4, 3000);
                }

                sub_1117();
                return;
            case 4:
                return;
            case 5:
                return;
            case 13:
                loadRoom(currentRoom);
                ResourceLoader.clearTextures();
                SetTranformOfAllObjects(currentRoom);
                setDialogWindowState((short) 2);
                if(currentLocation == 16 && currentRoom == 6 && currentDoorId == 0) {
                    sub_bd0();
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

    public static void sub_c4f(byte var0) {//переход по локации, если враги мертвы
        if(var_84a[currentLocation][currentRoom] || botsAlive == 0) {
            walkCurrentDoorId = currentDoorId;
            nextRoom = (byte) (loadedDoorsSettings[currentRoom][var0][0] - 1);
            if(nextRoom >= 0) {
                currentDoorId = (byte) (loadedDoorsSettings[currentRoom][var0][1] - 1);
                var_1afa = var0;
                setDialogWindowState((short) 1);
            }
        }
    }

    private static void SetCameraTranslation(float x, float y, float z) {
        camera.setTranslation(x, y, z);
    }

    private static void sub_d23() {
        long curr_time = System.currentTimeMillis();
        TickDurationMills = Math.min(250, (int) (curr_time - currentTimeMill));
        if(TickDurationMills < 0) {
            TickDurationMills = 0;
        }

        var_1bb1 += (long) TickDurationMills;
        currentTimeMill = curr_time;
        if(!gamePaused) {
            renderTimeOnly3D += (long) TickDurationMills;
        }

    }

    private static void sub_d9f() {
        if(var_1cda) {
            boolean var0 = MathUtils.fps < 10;
            int var1;
            if((var1 = (int) renderTimeOnly3D - var_1c84) <= 200) {
                if(!var0) {
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
                var_1cda = false;
                bloodSprite.setRenderingEnable(false);
            }

        }
    }

    public static void sub_daf() {
		Mesh var_e6b;
        switch(var_1e92[botIdUndercursor]) {
            case 0:
            case 4:
                var_e6b = (Mesh) roomBotGroups[botIdUndercursor].find(30);
                var_e6b.getTransformTo(gameWorld, transform);
                transform.get(var_1d3c);
                bloodSprite.setTranslation(var_1d3c[3], var_1d3c[7], var_1d3c[11]);
                break;
            case 1:
            case 2:
            case 3:
                var_e6b = (Mesh) roomBotGroups[botIdUndercursor].find(31);
                var_e6b.getTransformTo(gameWorld, transform);
                transform.get(var_1d3c);
                bloodSprite.setTranslation(var_1d3c[3], var_1d3c[7], var_1d3c[11]);
        }

        bloodSprite.setRenderingEnable(true);
    }

    private static void CameraXPostRotate() {
        int current_time;
        if((current_time = (int) renderTimeOnly3D & 1000) <= 1000) {
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

    private static void sub_e34(byte var0, byte var1) {
        int var2 = 500 - (var_1e05[var0] - (int) renderTimeOnly3D);
        short var3 = 0;
        short var4 = 0;
        switch(var1) {
            case 0:
            case 4:
                var3 = 1500;
                var4 = 2000;
                break;
            case 1:
            case 2:
            case 3:
                var3 = 1700;
                var4 = 2000;
        }

        if(var2 <= 500) {
            int var5 = var3 + (var4 - var3) * var2 / 500;
            roomBotGroups[var0].animate(var5);
            IsWayAheadLocked = false;
        } else {
            var_1d93 = -1;
            IsWayAheadLocked = true;
            bloodSprite.setRenderingEnable(false);
        }
    }

    private static void sub_e55(byte var0, byte var1) {
        short var2 = 0;
        short var3 = 0;
        switch(var1) {
            case 0:
                var2 = 300;
                var3 = 500;
                break;
            case 1:
                var2 = 900;
                var3 = 1100;
                break;
            case 2:
                var2 = 0;
                var3 = 200;
                break;
            case 3:
                var2 = 1200;
                var3 = 1400;
                break;
            case 4:
                var2 = 600;
                var3 = 800;
        }

        int var4 = var_1e37 - (var_1dce[var0] - (int) renderTimeOnly3D);
        int var5 = var_1e37;
        muzzleFlashSprite.setRenderingEnable(false);
        if(var4 > var5 / 2 - 750 && var4 < var5 / 2 + 750) {
            var_1db5 = true;
            roomBotGroups[var0].animate(var3);
            if(var4 % 300 <= 150) {
                Light light = (Light) roomBotGroups[var_1eae].find(50);
                light.getTransformTo(gameWorld, transform);
                transform.get(var_1d3c);
                muzzleFlashSprite.setTranslation(var_1d3c[3], var_1d3c[7], var_1d3c[11]);
                muzzleFlashSprite.setRenderingEnable(true);
            } else {
                muzzleFlashSprite.setRenderingEnable(false);
            }
        } else {
            var_1db5 = false;
            float var7 = botPositions[var0][0] - float_doublemassive_1st[var0][0];
            float var8 = botPositions[var0][2] - float_doublemassive_1st[var0][2];
            float var9 = 0.0F;
            float var10 = botSettings[currentRoom][var0][6];
            float var11 = 0.0F;
            if(var4 <= var5) {
                int var6;
                int var13;
                if(var4 <= var5 / 2 - 750) {
                    var9 = float_doublemassive_1st[var0][0] + var7 * (float) var4 / (float) (var_1e37 / 2 - 750);
                    var11 = float_doublemassive_1st[var0][2] + var8 * (float) var4 / (float) (var_1e37 / 2 - 750);
                    var13 = (var3 - var2) * var4 / (var_1e37 / 2 - 750);
                    var6 = var2 + var13;
                    roomBotGroups[var0].animate(var6);
                }

                if(var4 >= var5 / 2 + 750) {
                    var9 = float_doublemassive_1st[var0][0] + var7 * (float) (var5 - var4) / (float) (var_1e37 / 2 - 750);
                    var11 = float_doublemassive_1st[var0][2] + var8 * (float) (var5 - var4) / (float) (var_1e37 / 2 - 750);
                    var13 = (var3 - var2) * (var5 - var4) / (var_1e37 / 2 - 750);
                    var6 = var2 + var13;
                    roomBotGroups[var0].animate(var6);
                }

                roomBotGroups[var0].setTranslation(var9, var10, var11);
            } else {
                var_1e92[var0] = -1;
            }
        }
    }

    private static boolean sub_e95(byte var0) {
        return var0 == -1 ? true : (botsCount[currentRoom] - var_1fb1 == 1 ? renderTimeOnly3D >= (long) (var_1dce[var0] + 2000) : renderTimeOnly3D >= (long) var_1dce[var0]);
    }

    private static boolean sub_eee(byte var0) {
        return botActive[var0] && !botKilled[currentRoom][var0] && sub_e95(var_1eae);
    }

    private static void sub_f2e() {
        byte var0;
        if((var0 = botsCount[currentRoom]) != 0) {
            float var1 = 0.0F;
            float var2 = 0.0F;
            byte var3 = (byte) MathUtils.getRandomNumber(var0);
            float var4 = botSettings[currentRoom][var3][6];
            if(var3 == var_1eae) {
                var3 = (byte) MathUtils.getRandomNumber(var0);
            }

            if(var0 - var_1fb1 == 0) {
                CameraMovementDeactive = false;
                muzzleFlashSprite.setRenderingEnable(false);
                if(var_1d93 != -1) {
                    sub_e34(var_1d93, var_1e92[var_1eae]);
                    return;
                }
            }

            byte var5 = (byte) MathUtils.getRandomNumber(4);
            var_1ec4 = var0 - var_1fb1 == 1;
            byte botId;
            if(var_1ec4) {
                for(botId = 0; botId < var0; ++botId) {
                    if(!botKilled[currentRoom][botId]) {
                        var3 = botId;
                        break;
                    }
                }
            }

            if(botSettings[currentRoom][var3][9 + var5] != 0.0F && sub_eee(var3) && var_1d93 == -1) {
                switch(var5) {
                    case 0:
                    case 1:
                        var1 = botSettings[currentRoom][var3][2];
                        var2 = botSettings[currentRoom][var3][3];
                        break;
                    case 2:
                        var1 = botSettings[currentRoom][var3][0];
                        var2 = botSettings[currentRoom][var3][1];
                        break;
                    case 3:
                    case 4:
                        var1 = botSettings[currentRoom][var3][4];
                        var2 = botSettings[currentRoom][var3][5];
                }

                Scripts.var_22f1 = false;
                var_1e92[var3] = var5;
                sub_775(var3, currentRoom, var5);
                var_1eae = var3;
                setBotPosition(var3, var1, var4, var2);
                float_doublemassive_1st[var3][0] = botSettings[currentRoom][var3][0];
                float_doublemassive_1st[var3][1] = botSettings[currentRoom][var3][6];
                float_doublemassive_1st[var3][2] = botSettings[currentRoom][var3][1];
                var_1dce[var3] = (int) renderTimeOnly3D + var_1e37;
            }

            if(var_1eae != -1 && !botKilled[currentRoom][var_1eae]) {
                if(!Scripts.endingCutscene) {
                    sub_e55(var_1eae, var_1e92[var_1eae]);
                } else {
                    sub_e55(var_1eae, (byte) 2);
                }
            }

            if(Scripts.endingCutscene) {
                for(botId = 0; botId < var0; ++botId) {
                    var1 = botSettings[currentRoom][botId][0];
                    var2 = botSettings[currentRoom][botId][1];
                    float var7 = botSettings[currentRoom][botId][6];
                    setBotPosition(botId, var1, var7, var2);
                    roomBotGroups[botId].animate(200);
                }
            }

            if(var_1d93 != -1 && var_1eae != -1) {
                muzzleFlashSprite.setRenderingEnable(false);
                sub_e34(var_1d93, var_1e92[var_1eae]);
            } else {
                for(botId = 0; botId < var0; ++botId) {
                    //если атакует?
                    if(Scripts.endingCutscene) {
                        roomBotGroups[botId].setRenderingEnable(true);
                    } else {
                        //отключить отрисовку, если спрятался за укрытием
                        if(!botKilled[currentRoom][botId] && botId != var_1eae) {
                            roomBotGroups[botId].setRenderingEnable(false);
                        }

                        //включить анимацию смерти
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
            int time = (int) renderTimeOnly3D & 1000;
            roomLights[rotatingLightId].animate(time);
        }
    }

    private static void animateBckSprites() {
        for(int i = 0; i < 32; i++) {
            if(bckSpritesAnimEnabled[i]) {
                int w = RenderEngine.bckSpritesW[i] / 8;
				int h = bckSpritesH[i];
				
                int frameTime = (int) (renderTimeOnly3D + (long) (i * 300)) % 1000 / 200;
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
            sub_d23();
            animateSpritesAndLights();
            updateGameState();
            sub_b67();
        } else {
            updateGameState();
        }
    }

    private static void sub_103b() {//проверка на то, убиты ли все враги
        if(botsCount[currentRoom] == 0) {
            var_84a[currentLocation][currentRoom] = true;
        }

        byte var0 = 0;

        for(int bot = 0; bot < botsCount[currentRoom]; ++bot) {
            if(botKilled[currentRoom][bot]) {
                ++var0;
            }
        }

        var_84a[currentLocation][currentRoom] = var0 == botsCount[currentRoom];
    }

    private static void sub_1072() {//выглядывание ботов
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

                if(var_84a[currentLocation][currentRoom] || botKilled[currentRoom][i]) {
                    roomBotGroups[i].setRenderingEnable(true);
                }
            }
        }

        sub_f2e();
    }

    private static void updateWalkAnimation(Node node) {
        int timeElapsed = (int) renderTimeOnly3D - walkAnimStartTime;
		
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
				
				float doorX = doorSettings[currentRoom][walkNextDoorId - 1][0];
				float doorZ = doorSettings[currentRoom][walkNextDoorId - 1][2];
				
                float x = walkPointTargetX + (doorX - walkPointTargetX) * (float) timeElapsed / walkTimeToDoor;
                float y = useThirdPerson ? 0 : cameraPos[1];
                float z = walkPointTargetZ + (doorZ - walkPointTargetZ) * (float) timeElapsed / walkTimeToDoor;
				
                node.setTranslation(x, y, z);
                node.setOrientation(cameraYRotOffset + walkPointYRot + (useThirdPerson ? 180 : 0), 0, 1, 0);
            }

            if(useThirdPerson) {
                int playerModelAnimTime = 2400 + 390 * (int) (renderTimeOnly3D & 1000L) / 1000;
                node.animate(playerModelAnimTime);
            } else {
                CameraXPostRotate();
            }
        } else {
			//Walk anim end
            if(useThirdPerson) playerModel.setRenderingEnable(false);

            sub_115f();
        }
    }

    private static void sub_1117() {
        try {
            switch(currentGameState) {
                case 1:
                    if(useThirdPerson) updateWalkAnimation(playerModel);
					else updateWalkAnimation(camera);
					
                    return;
                default:
                    if(Scripts.endingCutscene && !showFinalDialog) {
                        boolean var12 = false;
                        int var13 = (int) renderTimeOnly3D - var_1a54;
                        boolean var14 = false;
                        SetCameraTranslation(61.5F, 3.0F, 134.0F);
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
                            var_1ab8 = (int) renderTimeOnly3D;
                            showFinalDialog = true;
                        }
                    } else if(showFinalDialog) {
                        int var0 = (int) renderTimeOnly3D - var_1ab8;
                        float var2 = 61.5F;
                        float var3 = 75.2F;
                        float var4 = 3.0F;
                        float var6 = 134.0F;
                        float var7 = 130.0F;
                        if(var0 <= 3750) {
                            float var8 = var2 + (var3 - var2) * (float) var0 / (float) 5000;
                            float var9 = var4 + 0.0F * (float) var0 / (float) 5000;
                            float var10 = var6 + (var7 - var6) * (float) var0 / (float) 5000;
                            SetCameraTranslation(var8, var9, var10);
                        } else {
                            Scripts.endingCutscene = false;
                            Scripts.startDialog((short) 20, (byte) 0);
                            showFinalDialog = false;
                        }
                    } else {
                        SetCameraOrientAndPostRotate();
                        SetCameraTranslation(cameraPos[0], cameraPos[1], cameraPos[2]);
                        sub_d9f();
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
        var_20a4 = true;
    }

    private static void sub_115f() {
        prepareObjectArraysForNextRoom();
        useThirdPerson = false;
        currentRoom = nextRoom;
        ConfigureAndActivateCamera();
        setDialogWindowState((short) 13);
    }

    public static void renderWorld(Graphics graphics) {
        try {
            if(currentGameState != -2 && currentGameState != 0 && currentGameState != 13 && currentGameState != 14) {
                graphics3D.bindTarget(graphics, true, Graphics3D.OVERWRITE);
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
        if(!CameraMovementDeactive) {
            cameraYRot += Keys.leftAccelerate ? 8.0F : 2.0F;
            cameraYRot = YRotationLimit(cameraYRot);
        }
    }

    public static void TurnRightTheCamera() {
        if(!CameraMovementDeactive) {
            cameraYRot -= Keys.rightAccelerate ? 8.0F : 2.0F;
            cameraYRot = YRotationLimit(cameraYRot);
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Поворот вверх или вниз">

    public static void RaiseTheCamera() {
        if(!CameraMovementDeactive) {
            float float_num = Keys.upAccelerate ? 8.0F : 2.0F; //Если клавиша нажата равно 8
            cameraXRot += float_num;
            cameraXRot = XRotationLimit(cameraXRot);
        }
    }

    public static void LowerTheCamera() {
        if(!CameraMovementDeactive) {
            float float_num = Keys.downAccelerate ? 10.0F : 2.0F;
            cameraXRot -= float_num;
            cameraXRot = XRotationLimit(cameraXRot);
        }
    }
//</editor-fold>
//</editor-fold>
}
