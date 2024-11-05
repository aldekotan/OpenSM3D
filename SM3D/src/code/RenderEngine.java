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
import java.lang.Integer;

public final class RenderEngine {

    public static int currentLocation;
    public static int nextLocation;
    
    public static byte currentRoom;
    private static byte nextRoom;
    
    public static boolean[] bool_massive_1st = new boolean[17];
    public static boolean[] bool_massive_2th = new boolean[17];
    public static boolean[] bool_massive_3th = new boolean[17];
    public static boolean gamePaused;
    public static Graphics3D graphics3D;
    public static World gameWorld = new World();
    private static float[][] float_doublemassive_1st;
    public static short currentGameState;
    public static short prevGameState;
    private static int int_1st;
    
    //Camera
    public static Camera camera;
    private static float[] cameraPos = new float[3];
    private static float[] prevCameraPos = new float[3]; //Used in cutscenes?
    public static byte cameraDoorId;
    private static float cameraXRot, cameraYRot;
    private static int cameraYRotOffset;

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
    private static int backgroundColor;

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
    
    private static byte[] walkPointsCount = new byte[15];
    private static float[][][] walkPoints = new float[15][4][5];
    //[roomId][walkPoint][settings]
    
    //?? [0]
    //x [1]
    //z [2]
    //walk to time
    //walk from time
    
    private static byte[] bckMeshesCount = new byte[15];
    private static float[][][] bckMeshSettings = new float[15][32][6];
    //[roomId][mesh][settings]
    //x [0]
    //y [1]
    //z [2]
    //rotation y[3]
    //model id [4]
    //scale [5]
    
    private static byte[] lightsCount = new byte[15];
    private static float[][][] lightSettings = new float[15][32][5];
    //[roomId][mesh][settings]
    //x [0]
    //y [1]
    //z [2]
    //rotation y [3]
    //light id [4]
    
    private static byte[] meshesCount = new byte[15];
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
    
    public static byte[] npcsCount = new byte[15];
    private static float[][][] npcSettings = new float[15][10][15];
    //[roomId][npc][settings]
    //x, z [0,1] [2,3] [4,5]
    //y [6]
    //rotation ?? [7]
    //position number ?? wtf [8]
    //idk.. ?? [9+]
    //idk.. ?? [14]
    public static boolean[][] npcKilled = new boolean[15][10];
    private static float[][] npcPositions = new float[10][3];
    //[npc][x, y, z]
    
    public static byte[] itemsForKillingAllCount = new byte[15];
    public static short[][] itemsForKillingAll = new short[15][10];
    //[roomId]{items}
    
    public static byte[] damageZonesTypes = new byte[15];
    //[roomId]
    private static byte[] damageZonesUnused = new byte[15];
    //unused ??
    
    public static byte var_7ee;
    public static boolean[][] var_84a;
    public static byte var_891;
    public static byte var_8f5;
	
    public static boolean objectsDataLoaded;
    public static byte[] objectModelSource;
    public static byte[] objectUnusedField;
    public static String[] objectModelName;
    public static String[] objectTextureName;
    public static byte[] objectBlendMode;
    public static byte[] objectLightsId;
    public static float[] objectScale;
    public static byte[] objectPersId;
    public static byte[] objectPersAnimTime;
	
    private static float var_b80;
    private static float var_bb1;
    private static int var_bf7;
    private static int var_c21;
    public static float[] tmpMehsScale;
    private static boolean isTexture_n_AdressMassivesFull;
    public static boolean showIntro;
    public static Mesh[] world_meshes_massive;
    public static Mesh[] mesh_massive_second;
    public static Mesh[] mesh_massive_third;
    public static Light[] light_massive_1st;
    public static Sprite3D[] world_sprites3D_massive;
    private static Sprite3D var_e1c;
    private static Sprite3D var_e59;
    public static Mesh var_e6b;
    private static Light var_ea6;
    public static World lightsWorld;
    public static Group[] group_massive_2st;
    public static Group[] StalkersAppearanceGroups;
    private static Group enemies_group;
    private static boolean needToLoadPersM3G;
    public static World persWorld;
    public static boolean[] FireAnimActiveFrames;
    private static byte[] byte_massive_1st;
    private static byte[] var_12f0;
    private static byte[] var_1310;
    private static Light light;
    private static byte RotatingLight;
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
    public static byte npcUnderCursor; //not sure
    public static byte TypeOfInteractWithObjectAhead;
    private static int var_185a;
    private static int var_1877;
    private static float var_18b0;
    private static float var_18bf;
    private static boolean CameraMovementDeactive;
    private static float var_191d;
    private static float var_1974;
    public static boolean var_19b5;
    public static final byte[] var_1a01;
    private static int var_1a13;
    private static int var_1a54;
    private static int var_1ab8;
    public static boolean showFinalDialog;
    private static byte var_1afa;
    private static final int screen_width;
    private static final int screen_height;
    public static long var_1bb1;
    public static long Only3DRenderTime;
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
    private static int[] cropWidth;
    private static int[] cropHeight;
    public static byte var_1fb1;
    public static boolean[] var_1ffd;
    private static byte var_205e;
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

        objectBlendMode = new byte[127];
        objectLightsId = new byte[127];
        objectScale = new float[127];
        objectPersId = new byte[100];
        objectPersAnimTime = new byte[100];
		
        tmpMehsScale = new float[]{1.0F, 1.0F, 1.0F};
        isTexture_n_AdressMassivesFull = true;
        world_meshes_massive = new Mesh[32];
        mesh_massive_second = new Mesh[32];
        mesh_massive_third = new Mesh[10];
        light_massive_1st = new Light[32];
        world_sprites3D_massive = new Sprite3D[32];
        lightsWorld = null;
        group_massive_2st = new Group[10];
        StalkersAppearanceGroups = new Group[10];
        FireAnimActiveFrames = new boolean[32];
        byte_massive_1st = new byte[32];
        var_12f0 = new byte[32];
        var_1310 = new byte[10];
        RotatingLight = -1;
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
        var_1a01 = new byte[]{(byte) 0, (byte) 0, (byte) 1, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0};
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
        cropWidth = new int[32];
        cropHeight = new int[32];
        var_1ffd = new boolean[10];
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
        bool_massive_1st = new boolean[17];
        bool_massive_2th = new boolean[17];
        bool_massive_3th = new boolean[17];
        bool_massive_3th[1] = true;
        var_16a2 = new boolean[5];
        activableObjsStatus = new boolean[10];
        var_1700 = new boolean[10];
        Scripts.setAllActorStatsToDefault();
    }

    private static void resetLocation() {
        Scripts.var_2204 = false;
        Scripts.locationExclusiveItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        var_1ffd = new boolean[10];
        npcKilled = new boolean[15][10];
    }

    private static void loadLocation(int location_number) {//что-то с загрузкой локации
        resetLocation();
        loadLocationData(location_number);
        LoadingScreen.RunGarbageCollector();
        nextRoom = currentRoom;
        ConfigureAndActivateCamera();
        if(isTexture_n_AdressMassivesFull) {
            ResourceLoader.initTexturesMassive();//Инициализация пустых массивов

            for(byte var1 = 0; var1 < byte_massive_1st.length; ++var1) {
                byte_massive_1st[var1] = -1;
            }

            isTexture_n_AdressMassivesFull = false;
        } else {
            sub_328();
        }

        loadObjectsData();
        LoadingScreen.RunGarbageCollector();
        PlayerHUD.loadHUDTexturesAndLocationCoorditates();
        LoadingScreen.RunGarbageCollector();
        ConfigureAndActivateCamera();
        if(StalkersAppearanceGroups[0] == null) //Если модели сталкеров не загружены
        {
            Personage.loadPersM3G((byte) 1, (byte) 0);
        }

        sub_360(currentRoom);
        if(var_1a01[location_number] == 1 && roomsCount > 1) //Если враги на уровне вообще есть 
        {
            DuplicateStalkerModel();
        } else {
            gameWorld.removeChild(enemies_group);
            enemies_group = null;
            System.gc();
        }

        ResourceLoader.clearTextures();
        SetTranformOfAllObjects(currentRoom);
        gamePaused = false;
    }

    private static void SetTranformOfAllObjects(byte place_number) {
        CreateLightInTheWorld(place_number);
        LoadingScreen.RunGarbageCollector();
        OrientAndTranslateLights(place_number);
        LoadingScreen.RunGarbageCollector();
        Meshes_ScaleOrientTranslation(place_number);
        LoadingScreen.RunGarbageCollector();
        GroupsOrientAndTranslate(place_number);
        LoadingScreen.RunGarbageCollector();
        sub_56b(place_number); //настраивает группы или меши в дополнительной базе
        LoadingScreen.RunGarbageCollector();
        Meshes_ScaleTranslationPreRotate(place_number);
        LoadingScreen.RunGarbageCollector();
        SetCameraTranslationAndOrientation(place_number, cameraDoorId);
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

    private static void addObjectToGameWorld(byte objId, byte gameObjId, byte var2) {
        try {
            if(needToLoadPersM3G) {
                try {
                    Personage.loadPersM3G(objId, gameObjId);
                    return;
                } catch (Exception ex) {
                    System.out.println("error enemy-model loading");
                    ex.printStackTrace();
                    return;
                }
            }

            System.gc();
			
            byte mdlSource = objectModelSource[objId];
            if(mdlSource == -1) return;

            String adress = "/" + objectModelName[objId];
            float scale = objectScale[objId];
			
            int c;
            label155:
            switch(mdlSource) {
                case 0:
                case 4:
                    switch(var2) {
                        case 0:
                        default:
                            break label155;
                        case 1:
                            if(world_meshes_massive[gameObjId] == null) {
                                world_meshes_massive[gameObjId] = ResourceLoader.loadZ1Model(adress);
                                System.gc();
                                gameWorld.addChild(world_meshes_massive[gameObjId]);
                                world_meshes_massive[gameObjId].getScale(tmpMehsScale);

                                for(int i = 0; i < 3; ++i) {
                                    tmpMehsScale[i] *= scale;
                                }

                                world_meshes_massive[gameObjId].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                                world_meshes_massive[gameObjId].setRenderingEnable(true);
                            } else {
                                gameWorld.addChild(world_meshes_massive[gameObjId]);
                                world_meshes_massive[gameObjId].setScale(0.1F, 0.1F, 0.1F);
                            }
                            break label155;
                        case 2:
                            mesh_massive_second[gameObjId] = ResourceLoader.loadZ1Model(adress);
                            System.gc();
                            gameWorld.addChild(mesh_massive_second[gameObjId]);
                            mesh_massive_second[gameObjId].getScale(tmpMehsScale);

                            for(int i = 0; i < 3; ++i) {
                                tmpMehsScale[i] *= scale;
                            }

                            mesh_massive_second[gameObjId].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                            mesh_massive_second[gameObjId].setRenderingEnable(true);
                            break label155;
                        case 100:
                            if(mdlSource == 4) {
                                Personage.DuplicatePersonageWithThisPose(gameObjId, objId);
                            } else if(mesh_massive_third[gameObjId] == null) {
                                mesh_massive_third[gameObjId] = ResourceLoader.loadZ1Model(adress);
                                System.gc();
                                gameWorld.addChild(mesh_massive_third[gameObjId]);
                            } else {
                                gameWorld.addChild(mesh_massive_third[gameObjId]);
                                mesh_massive_third[gameObjId].setScale(0.1F, 0.1F, 0.1F);
                            }
                            break label155;
                    }
                case 1:
                    world_meshes_massive[gameObjId] = null;
                    world_sprites3D_massive[gameObjId] = ResourceLoader.getSprite(objId);
                    if(world_sprites3D_massive[gameObjId] != null) {
                        if(objectTextureName[objId].equals("sfire1.png")) {
                            FireAnimActiveFrames[gameObjId] = true;
                            cropWidth[gameObjId] = ResourceLoader.getSpriteWidth();
                            cropHeight[gameObjId] = ResourceLoader.getSpriteHeight();
                            AnimateFire();
                        }

                        gameWorld.addChild(world_sprites3D_massive[gameObjId]);
                        world_sprites3D_massive[gameObjId].getScale(tmpMehsScale);

                        for(int i = 0; i < 3; ++i) {
                            tmpMehsScale[i] *= scale;
                        }

                        world_sprites3D_massive[gameObjId].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                        world_sprites3D_massive[gameObjId].setRenderingEnable(true);
                    }
                    break;
                case 2:
                    try {
						//light???
                        Object3D[] obj = Loader.load("/gamedata/meshes/m3g" + adress);
                        ModChanges.updateM3DModels(obj, "/gamedata/meshes/m3g" + adress);
                        

                        for(c = 0; c < obj.length; ++c) {
                            if(obj[c] instanceof World) {
                                lightsWorld = (World) obj[c];
                            }
                        }

                        var_ea6 = (Light) lightsWorld.find(objectLightsId[objId]);
                        light_massive_1st[gameObjId] = (Light) var_ea6.duplicate();
                        var_ea6 = null;
                        System.gc();
                        gameWorld.addChild(light_massive_1st[gameObjId]);
                        light_massive_1st[gameObjId].setRenderingEnable(true);
                    } catch (Exception var9) {
                        System.out.println("error light loading" + var9);

                        var9.printStackTrace();
                        System.out.println(var9.getMessage());
                    }
                    break;
                case 3:
                    switch(var2) {
                        case 0:
                        case 3:
                        case 4:
                        default:
                            break;
                        case 1:
                            try {
                                Object3D[] obj = Loader.load(adress); //в оригинале просто adress
                                ModChanges.updateM3DModels(obj, adress);

                                for(c = 0; c < obj.length; ++c) {
                                    if(obj[c] instanceof World) {
                                        lightsWorld = (World) obj[c];
                                    }
                                }
                            } catch (Exception var11) {
                                System.out.println("error m3g-model loading case1");
                                var11.printStackTrace();
                            }

                            var_e6b = (Mesh) lightsWorld.find(objectLightsId[objId]);
                            world_meshes_massive[gameObjId] = (Mesh) var_e6b.duplicate();
                            var_e6b = null;
                            System.gc();
                            gameWorld.addChild(world_meshes_massive[gameObjId]);
                            world_meshes_massive[gameObjId].getScale(tmpMehsScale);

                            for(int i = 0; i < 3; ++i) {
                                tmpMehsScale[i] *= scale;
                            }

                            world_meshes_massive[gameObjId].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                            world_meshes_massive[gameObjId].setRenderingEnable(true);
                            break;
                        case 2:
                            try {
                                Object3D[] obj = Loader.load(adress);
                                ModChanges.updateM3DModels(obj, adress);

                                for(c = 0; c < obj.length; ++c) {
                                    if(obj[c] instanceof World) {
                                        lightsWorld = (World) obj[c];
                                    }
                                }
                            } catch (Exception var10) {
                                System.out.println("error m3g-model loading case2");
                                var10.printStackTrace();
                            }

                            var_e6b = (Mesh) lightsWorld.find(objectLightsId[objId]);
                            mesh_massive_second[gameObjId] = (Mesh) var_e6b.duplicate();
                            var_e6b = null;
                            System.gc();
                            gameWorld.addChild(mesh_massive_second[gameObjId]);
                            mesh_massive_second[gameObjId].getScale(tmpMehsScale);

                            for(int i = 0; i < 3; ++i) {
                                tmpMehsScale[i] *= scale;
                            }

                            mesh_massive_second[gameObjId].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                            mesh_massive_second[gameObjId].setRenderingEnable(true);
                    }
            }

            lightsWorld = null;
        } catch (Exception ex) {
            System.out.println("Error type= " + objectModelSource[objId]);
            System.out.println(objectModelName[objId]);
            ex.printStackTrace();
        }

        System.gc();
    }

    private static String ReadStringsFromDataContainer(DataInputStream datacontainer) //Загрузка массива букв
    {
        try //На выходе получаются адреса моделей с расширением z1
        {
            char[] charmassive = new char[datacontainer.readByte()]; //Прочесть несколько букв и занести в массив

            for(int var2 = 0; var2 < charmassive.length; ++var2) //Заполнить массив буквами
            {
                charmassive[var2] = (char) datacontainer.readByte();
            }

            return String.valueOf(charmassive);
        } catch (IOException var3) {
            var3.printStackTrace();
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
                    objectModelName[objId] = ReadStringsFromDataContainer(dis); //Название объекта
                    objectTextureName[objId] = ReadStringsFromDataContainer(dis); //название текстур
					
                    if(objectModelSource[objId] == 4) {
						//load from pers??
                        objectPersId[objId] = dis.readByte();
                        objectPersAnimTime[objId] = dis.readByte();
                    } else {
						//load from lights??
                        objectBlendMode[objId] = dis.readByte();
                        objectLightsId[objId] = dis.readByte();
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
            --currentRoom;
            
            cameraDoorId = dis.readByte(); //выполнена ли 15-ая миссия
            --cameraDoorId;
            
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

                //Npcs
                npcsCount[roomId] = dis.readByte();

                for(int i = 0; i < npcsCount[roomId]; i++) {
                    //uuh
                    for(int t = 0; t < 15; ++t) {
                        if(t < 8) {
                            npcSettings[roomId][i][t] = dis.readInt() / 100.0F;
                        } else {
                            npcSettings[roomId][i][t] = dis.readByte();
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

            for(int i = 0; i < npcsCount[roomdId]; ++i) {
                npcSettings[roomdId][i][1] *= -1;
                npcSettings[roomdId][i][5] *= -1;
                npcSettings[roomdId][i][3] *= -1;
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

    private static void sub_328() {
        Mesh[] var0 = new Mesh[32];
        byte[] var1 = new byte[64];
        byte[] var2 = new byte[64];
        byte var3 = 0;

        byte var5;
        for(var5 = 0; var5 < var1.length; ++var5) {
            var1[var5] = var2[var5] = -1;
        }

        try {
            byte var4;
            for(var4 = 0; var4 < 32; ++var4) {
                for(var5 = 0; var5 < bckMeshesCount[nextRoom]; ++var5) {
                    if((float) byte_massive_1st[var4] == bckMeshSettings[nextRoom][var5][4]) {
                        var1[var3] = var4;
                        var2[var3] = var5;
                        ++var3;
                    }
                }
            }

            for(var4 = 0; var4 < var1.length; ++var4) {
                if(var1[var4] != -1 && world_meshes_massive[var1[var4]] != null && var0[var2[var4]] == null) {
                    var0[var2[var4]] = (Mesh) world_meshes_massive[var1[var4]].duplicate();
                }
            }

            for(var4 = 0; var4 < 32; ++var4) {
                gameWorld.removeChild(world_meshes_massive[var4]);
                world_meshes_massive[var4] = null;
                gameWorld.removeChild(world_sprites3D_massive[var4]);
                world_sprites3D_massive[var4] = null;
            }

            for(var4 = 0; var4 < 32; ++var4) {
                if(var0[var4] != null) {
                    world_meshes_massive[var4] = (Mesh) var0[var4].duplicate();
                }
            }

            for(var4 = 0; var4 < var0.length; ++var4) {
                var0[var4] = null;
            }

            System.gc();
            byte var6 = npcsCount[nextRoom];

            for(var4 = 1; var4 < 10; ++var4) {
                if(var4 >= var6) {
                    gameWorld.removeChild(StalkersAppearanceGroups[var4]);
                    StalkersAppearanceGroups[var4] = null;
                }
            }

            for(var4 = 0; var4 < 32; ++var4) {
                gameWorld.removeChild(mesh_massive_second[var4]);
                mesh_massive_second[var4] = null;
            }

            System.gc();

            for(var4 = 0; var4 < 10; ++var4) {
                gameWorld.removeChild(mesh_massive_third[var4]);
                mesh_massive_third[var4] = null;
            }

            for(var4 = 0; var4 < light_massive_1st.length; ++var4) {
                gameWorld.removeChild(light_massive_1st[var4]);
                light_massive_1st[var4] = null;
            }

            for(var4 = 0; var4 < 10; ++var4) {
                gameWorld.removeChild(group_massive_2st[var4]);
                group_massive_2st[var4] = null;
            }

            gameWorld.removeChild(light);
            light = null;
            System.gc();
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    private static void sub_360(int var0) {
        try {
            System.gc();
            Thread.sleep(2000L);
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

        LoadingScreen.RunGarbageCollector();
        sub_387();
        LoadingScreen.RunGarbageCollector();

        byte var1;
        for(var1 = 0; var1 < bckMeshesCount[var0]; ++var1) {
            addObjectToGameWorld((byte) ((int) bckMeshSettings[var0][var1][4]), var1, (byte) 1);
            byte_massive_1st[var1] = (byte) ((int) bckMeshSettings[var0][var1][4]);
        }

        LoadingScreen.RunGarbageCollector();

        for(var1 = 0; var1 < activableObjsCount[var0]; ++var1) {
            addObjectToGameWorld((byte) ((int) activableObjSettings[var0][var1][6]), var1, (byte) 100);
            var_1310[var1] = (byte) ((int) activableObjSettings[var0][var1][6]);
        }

        LoadingScreen.RunGarbageCollector();
        needToLoadPersM3G = true;
        if(npcsCount[var0] == 0) {
            StalkersAppearanceGroups[0].setRenderingEnable(false);
        }

        for(var1 = 0; var1 < npcsCount[var0]; ++var1) {
            addObjectToGameWorld((byte) ((int) npcSettings[var0][var1][14]), var1, (byte) 4);
        }

        needToLoadPersM3G = false;
        LoadingScreen.RunGarbageCollector();

        for(var1 = 0; var1 < meshesCount[var0]; ++var1) {
            addObjectToGameWorld((byte) ((int) meshSettings[var0][var1][6]), var1, (byte) 2);
            var_12f0[var1] = (byte) ((int) meshSettings[var0][var1][6]);
        }

        LoadingScreen.RunGarbageCollector();

        for(var1 = 0; var1 < lightsCount[var0]; ++var1) {
            addObjectToGameWorld((byte) ((int) lightSettings[var0][var1][4]), var1, (byte) 3);
        }

        LoadingScreen.RunGarbageCollector();
        LoadingScreen.RunGarbageCollector();
        var_20a4 = false;
    }

    private static void sub_387() {
        if(var_e1c == null) {
            var_e1c = ResourceLoader.getSprite(-100);
            var_e1c.setScale(0.6F, 0.6F, 0.6F);
            gameWorld.addChild(var_e1c);
        }

        var_e1c.setRenderingEnable(false);
        LoadingScreen.RunGarbageCollector();
        if(var_e59 == null) {
            var_e59 = ResourceLoader.getSprite(-101);
            gameWorld.addChild(var_e59);
        }

        var_e59.setRenderingEnable(false);
    }

    private static void sub_3e4(byte var0) {
        byte var2 = 0;
        switch(var0) {
            case 0:
                var2 = 0;
                break;
            case 1:
                var2 = 22;
                break;
            case 2:
                var2 = 23;
                break;
            case 3:
                var2 = 24;
        }

        if(var2 != 0) {
            var_e6b = (Mesh) persWorld.find(var2);
            Mesh var1 = (Mesh) var_e6b.duplicate();
            enemies_group.addChild(var1);
            var_e6b = null;
        }

        enemies_group.setOrientation(0.0F, 0.0F, 0.0F, 0.0F);
        enemies_group.setRenderingEnable(true);
    }

    private static void DuplicateStalkerModel() {
        if(enemies_group == null) {
            enemies_group = new Group();
            if(StalkersAppearanceGroups[0] != null) {
                enemies_group = (Group) StalkersAppearanceGroups[0].duplicate();
                System.out.println("STALKER COPIED !!! ");
            }

            DefineStalkerModelAppearance();
            gameWorld.addChild(enemies_group);
        }
    }

    private static void DefineStalkerModelAppearance() {
        CompositingMode compositMode = new CompositingMode(); //Способ зарисовки в мире
        Appearance appearance; //Внешний вид
        (appearance = new Appearance()).setTexture(0, ResourceLoader.getTexture("pstalker.png"));//текстура
        compositMode.setBlending(68); //смешивание
        Material material = new Material(); //освещение и блеск
        appearance.setCompositingMode(compositMode);
        appearance.setMaterial(material);

        for(byte var4 = 0; var4 < 12; ++var4) {
            ((Mesh) enemies_group.find(var4 + 1)).setAppearance(0, appearance); //внешний вид для конкретного сабмеша
            System.gc();
        }

        var_e6b = (Mesh) persWorld.find(25);
        Mesh var0 = (Mesh) var_e6b.duplicate();
        enemies_group.addChild(var0);
        var_e6b = null;
    }

    private static void backupCameraPos() {
        System.arraycopy(cameraPos, 0, prevCameraPos, 0, 3);
    }

    private static void CreateLightInTheWorld(int place_number) {
        float light_intencity;
        if((light_intencity = roomSettings[place_number][6] / 100.0F) != 0.0F) {
            light = new Light();
            light.setMode(Light.AMBIENT);
            int light_color = (int) roomSettings[place_number][5];
            light.setColor(light_color); //(21431)
            light.setIntensity(light_intencity); //
            gameWorld.addChild(light);
        }

        backgroundColor = (int) roomSettings[place_number][7];//
        if(gameWorld.getBackground() == null) {
            Background background = new Background();
            gameWorld.setBackground(background);
            //todo отключать очистку фона если мы в здании
        }
        gameWorld.getBackground().setColor(backgroundColor);

    }

    private static void OrientAndTranslateLights(int place_number) {
        RotatingLight = -1;

        for(byte light_number = 0; light_number < lightsCount[place_number]; ++light_number) {
            light_massive_1st[light_number].setOrientation(lightSettings[place_number][light_number][3], 0.0F, 1.0F, 0.0F);
            light_massive_1st[light_number].setTranslation(lightSettings[place_number][light_number][0], lightSettings[place_number][light_number][1], lightSettings[place_number][light_number][2]);
            if(light_massive_1st[light_number].getAnimationTrackCount() != 0) {
                RotatingLight = light_number;
            }
        }

    }

    private static void GroupsOrientAndTranslate(int place_number) {
        var_1eae = -1;
        var_1fb1 = 0;

        for(byte group_number = 0; group_number < npcsCount[place_number]; ++group_number) {
            var_1dce[group_number] = (int) Only3DRenderTime + var_1e37;
            var_1e05[group_number] = (int) Only3DRenderTime + 500;
            var_1e92[group_number] = 2;
            StalkersAppearanceGroups[group_number].setOrientation(npcSettings[place_number][group_number][7], 0.0F, 1.0F, 0.0F);
            SetGroupTranslation(group_number, npcSettings[place_number][group_number][0], npcSettings[place_number][group_number][6], npcSettings[place_number][group_number][1]);
            StalkersAppearanceGroups[group_number].setTranslation(npcPositions[group_number][0], npcPositions[group_number][1], npcPositions[group_number][2]);
        }

        if(npcsCount[place_number] == 0) {
            Scripts.giveItemsForKillingAll();
        }

        sub_1072();
    }

    private static void Meshes_ScaleOrientTranslation(int place_number) {
        for(int number_of_mesh = 0; number_of_mesh < bckMeshesCount[place_number]; ++number_of_mesh) {
            float mesh_transl_1 = bckMeshSettings[place_number][number_of_mesh][0];
            float mesh_transl_2 = bckMeshSettings[place_number][number_of_mesh][1];
            float mesh_transl_3 = bckMeshSettings[place_number][number_of_mesh][2];
            float mesh_scale = bckMeshSettings[place_number][number_of_mesh][5];
            if(world_meshes_massive[number_of_mesh] != null) {
                world_meshes_massive[number_of_mesh].getScale(tmpMehsScale);

                for(byte var6 = 0; var6 < 3; ++var6) {
                    tmpMehsScale[var6] *= mesh_scale;
                }

                world_meshes_massive[number_of_mesh].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                world_meshes_massive[number_of_mesh].setOrientation(bckMeshSettings[place_number][number_of_mesh][3], 0.0F, 1.0F, 0.0F);
                world_meshes_massive[number_of_mesh].setTranslation(mesh_transl_1, mesh_transl_2, mesh_transl_3);
            } else if(world_sprites3D_massive[number_of_mesh] != null) {
                world_sprites3D_massive[number_of_mesh].setTranslation(mesh_transl_1, mesh_transl_2, mesh_transl_3);
                world_sprites3D_massive[number_of_mesh].setScale(mesh_scale, mesh_scale, mesh_scale);
            }
        }

    }

    private static void Meshes_ScaleTranslationPreRotate(int place_number) {
        for(int mesh_number = 0; mesh_number < meshesCount[place_number]; ++mesh_number) {
            if(mesh_massive_second[mesh_number] != null) {
                float scale = meshSettings[place_number][mesh_number][7];
                mesh_massive_second[mesh_number].getScale(tmpMehsScale);

                for(byte var3 = 0; var3 < 3; ++var3) {
                    tmpMehsScale[var3] *= scale;
                }

                mesh_massive_second[mesh_number].setScale(tmpMehsScale[0], tmpMehsScale[1], tmpMehsScale[2]);
                float x = meshSettings[place_number][mesh_number][0];
                float y = meshSettings[place_number][mesh_number][1];
                float z = meshSettings[place_number][mesh_number][2];
                mesh_massive_second[mesh_number].setTranslation(x, y, z);
                mesh_massive_second[mesh_number].preRotate(meshSettings[place_number][mesh_number][3], 1.0F, 0.0F, 0.0F);
                mesh_massive_second[mesh_number].preRotate(meshSettings[place_number][mesh_number][5], 0.0F, 0.0F, 1.0F);
                mesh_massive_second[mesh_number].preRotate(meshSettings[place_number][mesh_number][4], 0.0F, 1.0F, 0.0F);
            }
        }

    }

    private static void sub_56b(int place_number) {
        for(int number = 0; number < activableObjsCount[place_number]; ++number) {
            float var2;
            float var3;
            float var4;
            if(mesh_massive_third[number] != null) {
                mesh_massive_third[number].setOrientation(activableObjSettings[place_number][number][4], 0.0F, 1.0F, 0.0F);
                var2 = activableObjSettings[place_number][number][0];
                var3 = activableObjSettings[place_number][number][1];
                var4 = activableObjSettings[place_number][number][2];
                mesh_massive_third[number].setTranslation(var2, var3, var4);
            } else if(group_massive_2st[number] != null) {
                group_massive_2st[number].setOrientation(activableObjSettings[place_number][number][4], 0.0F, 1.0F, 0.0F);
                var2 = activableObjSettings[place_number][number][0];
                var3 = activableObjSettings[place_number][number][1];
                var4 = activableObjSettings[place_number][number][2];
                group_massive_2st[number].setTranslation(var2, var3, var4);
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
        FireAnimActiveFrames = new boolean[32];
        var_185a = (int) Only3DRenderTime;
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

        for(objId = 0; objId < npcsCount[roomId]; ++objId) {
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
        float var12 = npcSettings[var1][var0][6] + (var_1669 ? 1.0999999F : 1.8F);
        float var13 = var_1669 ? npcSettings[var1][var0][6] : npcSettings[var1][var0][6] + 0.9F;
        float var14 = 0.0F;
        switch(var2) {
            case 0:
            case 1:
                var11 = npcSettings[var1][var0][2];
                var14 = npcSettings[var1][var0][3];
                break;
            case 2:
                var11 = npcSettings[var1][var0][0];
                var14 = npcSettings[var1][var0][1];
                break;
            case 3:
            case 4:
                var11 = npcSettings[var1][var0][4];
                var14 = npcSettings[var1][var0][5];
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

    private static float[] sub_7cb(byte var0, boolean var1) {
        float var2 = roomSettings[currentRoom][0];
        float var3 = roomSettings[currentRoom][2];
        float var4 = cameraPos[0];
        float var5 = cameraPos[2];
        float var6 = var_b80;
        float var7 = var_bb1;
        float var8 = doorSettings[currentRoom][var0][0];
        float var9 = doorSettings[currentRoom][var0][2];
        float var10 = doorSettings[currentRoom][var_8f5 - 1][0];
        float var11 = doorSettings[currentRoom][var_8f5 - 1][2];
        var_19b5 = var1;
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

    private static void sub_844() {
        PlayerHUD.doorLocked = false;
        if(var_84a[currentLocation][currentRoom] || var_205e == 0) {
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

    private static void sub_8d1() {
        if(var_84a[currentLocation][currentRoom] || var_205e == 0) {
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
        if((int) Only3DRenderTime - var_185a >= 1000) {
            var_185a = (int) Only3DRenderTime;
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
            if(Only3DRenderTime - (long) var_185a >= 500L) {
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
            if(var0 != cameraDoorId && var_16a2[var0] && (doorUnlocked[var0] || isItemInList(doorKeys[currentRoom][var0], Scripts.locationExclusiveItems))) {
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
        var_1e05[var0] = (int) Only3DRenderTime + 500;
    }

    public static boolean sub_acc() {
        byte var0 = var_1eae;
        npcUnderCursor = -100;
        if(var0 < 0) {
            return false;
        } else if(var_1700[var0] && !npcKilled[currentRoom][var0] && var_1ffd[var0] && !var_84a[currentLocation][currentRoom]) {
            npcUnderCursor = var0;
            
            if(!Scripts.var_2602) {
                if(var_1877 == -1) {
                    var_1877 = (int) Only3DRenderTime;
                    var_18b0 = cameraYRot;
                    var_18bf = cameraXRot;
                }

                if(Only3DRenderTime - (long) var_1877 < 300L) {
                    cameraYRot = var_18b0 + (var_1539[var0] - var_18b0) * (float) (Only3DRenderTime - (long) var_1877) / 300.0F;
                    float var1 = var_156e[var0] - (var_156e[var0] - var_157d[var0]) / (float) (var_1669 ? 4 : 2);
                    cameraXRot = var_18bf + (var1 - var_18bf) * (float) (Only3DRenderTime - (long) var_1877) / 300.0F;
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
                if(var_1afa == var_891) {
                    return;
                }

                byte var1 = (byte) (var_891 + 1);

                for(byte var2 = 0; var2 < walkPointsCount[currentRoom]; ++var2) {
                    var_8f5 = (byte) ((int) (walkPoints[currentRoom][var2][0] - (float) var1));
                    if(var_8f5 == var_1afa + 1) {
                        var_b80 = walkPoints[currentRoom][var2][1];
                        var_bb1 = walkPoints[currentRoom][var2][2];
                        if(var_8f5 - 1 > var_891) {
                            var_bf7 = (int) walkPoints[currentRoom][var2][3] * 1000 / 3;
                            var_c21 = (int) walkPoints[currentRoom][var2][4] * 1000 / 3;
                        } else {
                            var_bf7 = (int) walkPoints[currentRoom][var2][4] * 1000 / 3;
                            var_c21 = (int) walkPoints[currentRoom][var2][3] * 1000 / 3;
                        }
                        break;
                    }
                }

                float[] var3;
                var_191d = (var3 = sub_7cb(var_891, var_1a01[currentLocation] == 1))[0] != 180.0F ? var3[0] : 0.0F;
                var_1974 = var_191d + var3[1];
                int_1st = (int) Only3DRenderTime;
                backupCameraPos();
                if(var_19b5) {
                    sub_3e4(Scripts.CurrentGunInTheHands);
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
        var_1a13 = (int) Only3DRenderTime + var1;
        prevGameState = currentGameState;
        currentGameState = var0;
    }

    private static void sub_b67() {
        if(Only3DRenderTime >= (long) var_1a13) {
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
        DuplicateStalkerModel();
        sub_3e4(Scripts.CurrentGunInTheHands);
        Scripts.startDialog((short) 19, (byte) 0);
        var_1e37 = 500;
        var_1a54 = (int) Only3DRenderTime;
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
                if(var_1afa == var_891) {
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
                if(Scripts.ActorCurrentHealth <= 0) {
                    endGame((short) 4, 3000);
                }

                sub_1117();
                return;
            case 4:
                return;
            case 5:
                return;
            case 13:
                sub_360(currentRoom);
                ResourceLoader.clearTextures();
                SetTranformOfAllObjects(currentRoom);
                setDialogWindowState((short) 2);
                if(currentLocation == 16 && currentRoom == 6 && cameraDoorId == 0) {
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

    public static void sub_c4f(byte var0) {
        if(var_84a[currentLocation][currentRoom] || var_205e == 0) {
            var_891 = cameraDoorId;
            nextRoom = (byte) (loadedDoorsSettings[currentRoom][var0][0] - 1);
            if(nextRoom >= 0) {
                cameraDoorId = (byte) (loadedDoorsSettings[currentRoom][var0][1] - 1);
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
            Only3DRenderTime += (long) TickDurationMills;
        }

    }

    private static void AnimateFireAndLight() {
        AnimateLight();
        AnimateFire();
    }

    private static void AnimateLight() {
        if(RotatingLight != -1) {
            int time = (int) Only3DRenderTime & 1000;
            light_massive_1st[RotatingLight].animate(time);
        }

    }

    private static void sub_d9f() {
        if(var_1cda) {
            boolean var0 = MathUtils.fps < 10;
            int var1;
            if((var1 = (int) Only3DRenderTime - var_1c84) <= 200) {
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
                var_e1c.setRenderingEnable(false);
            }

        }
    }

    public static void sub_daf() {
        switch(var_1e92[npcUnderCursor]) {
            case 0:
            case 4:
                var_e6b = (Mesh) StalkersAppearanceGroups[npcUnderCursor].find(30);
                var_e6b.getTransformTo(gameWorld, transform);
                transform.get(var_1d3c);
                var_e1c.setTranslation(var_1d3c[3], var_1d3c[7], var_1d3c[11]);
                var_e6b = null;
                break;
            case 1:
            case 2:
            case 3:
                var_e6b = (Mesh) StalkersAppearanceGroups[npcUnderCursor].find(31);
                var_e6b.getTransformTo(gameWorld, transform);
                transform.get(var_1d3c);
                var_e1c.setTranslation(var_1d3c[3], var_1d3c[7], var_1d3c[11]);
                var_e6b = null;
        }

        var_e1c.setRenderingEnable(true);
    }

    private static void CameraXPostRotate() {
        int current_time;
        if((current_time = (int) Only3DRenderTime & 1000) <= 1000) {
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
        int var2 = 500 - (var_1e05[var0] - (int) Only3DRenderTime);
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
            StalkersAppearanceGroups[var0].animate(var5);
            IsWayAheadLocked = false;
        } else {
            var_1d93 = -1;
            IsWayAheadLocked = true;
            var_e1c.setRenderingEnable(false);
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

        int var4 = var_1e37 - (var_1dce[var0] - (int) Only3DRenderTime);
        int var5 = var_1e37;
        var_e59.setRenderingEnable(false);
        if(var4 > var5 / 2 - 750 && var4 < var5 / 2 + 750) {
            var_1db5 = true;
            StalkersAppearanceGroups[var0].animate(var3);
            if(var4 % 300 <= 150) {
                var_ea6 = (Light) StalkersAppearanceGroups[var_1eae].find(50);
                var_ea6.getTransformTo(gameWorld, transform);
                transform.get(var_1d3c);
                var_e59.setTranslation(var_1d3c[3], var_1d3c[7], var_1d3c[11]);
                var_ea6 = null;
                var_e59.setRenderingEnable(true);
            } else {
                var_e59.setRenderingEnable(false);
            }
        } else {
            var_1db5 = false;
            float var7 = npcPositions[var0][0] - float_doublemassive_1st[var0][0];
            float var8 = npcPositions[var0][2] - float_doublemassive_1st[var0][2];
            float var9 = 0.0F;
            float var10 = npcSettings[currentRoom][var0][6];
            float var11 = 0.0F;
            if(var4 <= var5) {
                int var6;
                int var13;
                if(var4 <= var5 / 2 - 750) {
                    var9 = float_doublemassive_1st[var0][0] + var7 * (float) var4 / (float) (var_1e37 / 2 - 750);
                    var11 = float_doublemassive_1st[var0][2] + var8 * (float) var4 / (float) (var_1e37 / 2 - 750);
                    var13 = (var3 - var2) * var4 / (var_1e37 / 2 - 750);
                    var6 = var2 + var13;
                    StalkersAppearanceGroups[var0].animate(var6);
                }

                if(var4 >= var5 / 2 + 750) {
                    var9 = float_doublemassive_1st[var0][0] + var7 * (float) (var5 - var4) / (float) (var_1e37 / 2 - 750);
                    var11 = float_doublemassive_1st[var0][2] + var8 * (float) (var5 - var4) / (float) (var_1e37 / 2 - 750);
                    var13 = (var3 - var2) * (var5 - var4) / (var_1e37 / 2 - 750);
                    var6 = var2 + var13;
                    StalkersAppearanceGroups[var0].animate(var6);
                }

                StalkersAppearanceGroups[var0].setTranslation(var9, var10, var11);
            } else {
                var_1e92[var0] = -1;
            }
        }
    }

    private static boolean sub_e95(byte var0) {
        return var0 == -1 ? true : (npcsCount[currentRoom] - var_1fb1 == 1 ? Only3DRenderTime >= (long) (var_1dce[var0] + 2000) : Only3DRenderTime >= (long) var_1dce[var0]);
    }

    private static boolean sub_eee(byte var0) {
        return var_1ffd[var0] && !npcKilled[currentRoom][var0] && sub_e95(var_1eae);
    }

    private static void sub_f2e() {
        byte var0;
        if((var0 = npcsCount[currentRoom]) != 0) {
            float var1 = 0.0F;
            float var2 = 0.0F;
            byte var3 = (byte) MathUtils.getRandomNumber(var0);
            float var4 = npcSettings[currentRoom][var3][6];
            if(var3 == var_1eae) {
                var3 = (byte) MathUtils.getRandomNumber(var0);
            }

            if(var0 - var_1fb1 == 0) {
                CameraMovementDeactive = false;
                var_e59.setRenderingEnable(false);
                if(var_1d93 != -1) {
                    sub_e34(var_1d93, var_1e92[var_1eae]);
                    return;
                }
            }

            byte var5 = (byte) MathUtils.getRandomNumber(4);
            var_1ec4 = var0 - var_1fb1 == 1;
            byte npcId;
            if(var_1ec4) {
                for(npcId = 0; npcId < var0; ++npcId) {
                    if(!npcKilled[currentRoom][npcId]) {
                        var3 = npcId;
                        break;
                    }
                }
            }

            if(npcSettings[currentRoom][var3][9 + var5] != 0.0F && sub_eee(var3) && var_1d93 == -1) {
                switch(var5) {
                    case 0:
                    case 1:
                        var1 = npcSettings[currentRoom][var3][2];
                        var2 = npcSettings[currentRoom][var3][3];
                        break;
                    case 2:
                        var1 = npcSettings[currentRoom][var3][0];
                        var2 = npcSettings[currentRoom][var3][1];
                        break;
                    case 3:
                    case 4:
                        var1 = npcSettings[currentRoom][var3][4];
                        var2 = npcSettings[currentRoom][var3][5];
                }

                Scripts.var_22f1 = false;
                var_1e92[var3] = var5;
                sub_775(var3, currentRoom, var5);
                var_1eae = var3;
                SetGroupTranslation(var3, var1, var4, var2);
                float_doublemassive_1st[var3][0] = npcSettings[currentRoom][var3][0];
                float_doublemassive_1st[var3][1] = npcSettings[currentRoom][var3][6];
                float_doublemassive_1st[var3][2] = npcSettings[currentRoom][var3][1];
                var_1dce[var3] = (int) Only3DRenderTime + var_1e37;
            }

            if(var_1eae != -1 && !npcKilled[currentRoom][var_1eae]) {
                if(!Scripts.var_2602) {
                    sub_e55(var_1eae, var_1e92[var_1eae]);
                } else {
                    sub_e55(var_1eae, (byte) 2);
                }
            }

            if(Scripts.var_2602) {
                for(npcId = 0; npcId < var0; ++npcId) {
                    var1 = npcSettings[currentRoom][npcId][0];
                    var2 = npcSettings[currentRoom][npcId][1];
                    float var7 = npcSettings[currentRoom][npcId][6];
                    SetGroupTranslation(npcId, var1, var7, var2);
                    StalkersAppearanceGroups[npcId].animate(200);
                }
            }

            if(var_1d93 != -1 && var_1eae != -1) {
                var_e59.setRenderingEnable(false);
                sub_e34(var_1d93, var_1e92[var_1eae]);
            } else {
                for(npcId = 0; npcId < var0; ++npcId) {
                    //если атакует?
                    if(Scripts.var_2602) {
                        StalkersAppearanceGroups[npcId].setRenderingEnable(true);
                    } else {
                        //отключить отрисовку, если спрятался за укрытием
                        if(!npcKilled[currentRoom][npcId] && npcId != var_1eae) {
                            StalkersAppearanceGroups[npcId].setRenderingEnable(false);
                        }

                        //включить анимацию смерти
                        if(npcKilled[currentRoom][npcId]) {
                            StalkersAppearanceGroups[npcId].animate(2000);
                        }
                    }
                }

            }
        }
    }

    private static void SetGroupTranslation(byte group_number, float trans_x, float trans_y, float trans_z) {
        npcPositions[group_number][0] = trans_x;
        npcPositions[group_number][1] = trans_y;
        npcPositions[group_number][2] = trans_z;
        StalkersAppearanceGroups[group_number].setTranslation(trans_x, trans_y, trans_z);
    }

    private static void AnimateFire() {
        for(byte FrameNumber = 0; FrameNumber < 32; ++FrameNumber) {
            if(FireAnimActiveFrames[FrameNumber]) {
                int cropWidth = RenderEngine.cropWidth[FrameNumber] / 8;
                int frame_to_time = (int) (Only3DRenderTime + (long) (FrameNumber * 300)) % 1000 / 200;
                int cropX = cropWidth * frame_to_time;
                world_sprites3D_massive[FrameNumber].setCrop(cropX, 0, cropWidth, cropHeight[FrameNumber]);
            }
        }

    }

    public static void updateGameTime() {
        if(currentGameState != 0 && currentGameState != 13 && currentGameState != -2) {
            sub_d23();
            AnimateFireAndLight();
            updateGameState();
            sub_b67();
        } else {
            updateGameState();
        }
    }

    private static void sub_103b() {
        if(npcsCount[currentRoom] == 0) {
            var_84a[currentLocation][currentRoom] = true;
        }

        byte var0 = 0;

        for(int npc = 0; npc < npcsCount[currentRoom]; ++npc) {
            if(npcKilled[currentRoom][npc]) {
                ++var0;
            }
        }

        var_84a[currentLocation][currentRoom] = var0 == npcsCount[currentRoom];
    }

    private static void sub_1072() {
        var_205e = 0;

        for(int i = 0; i < npcsCount[currentRoom]; ++i) {
            if(Scripts.var_2602) {
                StalkersAppearanceGroups[i].setRenderingEnable(true);
            } else {
                if(npcSettings[currentRoom][i][8] - 1.0F != (float) cameraDoorId) {
                    StalkersAppearanceGroups[i].setRenderingEnable(false);
                    var_1ffd[i] = false;
                } else {
                    StalkersAppearanceGroups[i].setRenderingEnable(true);
                    var_1ffd[i] = true;
                    if(!npcKilled[currentRoom][i]) {
                        ++var_205e;
                    }
                }

                if(var_84a[currentLocation][currentRoom] || npcKilled[currentRoom][i]) {
                    StalkersAppearanceGroups[i].setRenderingEnable(true);
                }
            }
        }

        sub_f2e();
    }

    private static void sub_10cf(Node var0) {
        int var1;
        float var2;
        if((var1 = (int) Only3DRenderTime - int_1st) <= 500) {
            var2 = cameraYRot + (var_191d - cameraYRot) * (float) var1 / 500.0F;
            camera.setOrientation((float) cameraYRotOffset + var2, 0.0F, 1.0F, 0.0F);
        } else if((var1 = (int) Only3DRenderTime - (int_1st + 500)) < var_bf7 + var_c21) {
            float var3;
            float var4;
            if(var1 <= var_bf7) {
                var2 = prevCameraPos[0] + (var_b80 - prevCameraPos[0]) * (float) var1 / (float) var_bf7;
                var3 = !var_19b5 ? cameraPos[1] : 0.0F;
                var4 = prevCameraPos[2] + (var_bb1 - prevCameraPos[2]) * (float) var1 / (float) var_bf7;
                var0.setTranslation(var2, var3, var4);
                float var5 = var_191d + (var_1974 - var_191d) * (float) var1 / (float) var_bf7;
                if(var_19b5) {
                    var0.setOrientation((float) (cameraYRotOffset + 180) + var_191d, 0.0F, 1.0F, 0.0F);
                } else {
                    var0.setOrientation((float) cameraYRotOffset + var5, 0.0F, 1.0F, 0.0F);
                }
            } else {
                var1 = (int) Only3DRenderTime - (int_1st + 500 + var_bf7);
                var2 = var_b80 + (doorSettings[currentRoom][var_8f5 - 1][0] - var_b80) * (float) var1 / (float) var_c21;
                var3 = !var_19b5 ? cameraPos[1] : 0.0F;
                var4 = var_bb1 + (doorSettings[currentRoom][var_8f5 - 1][2] - var_bb1) * (float) var1 / (float) var_c21;
                var0.setTranslation(var2, var3, var4);
                if(var_19b5) {
                    var0.setOrientation((float) (cameraYRotOffset + 180) + var_1974, 0.0F, 1.0F, 0.0F);
                } else {
                    var0.setOrientation((float) cameraYRotOffset + var_1974, 0.0F, 1.0F, 0.0F);
                }
            }

            if(var_19b5) {
                int var6 = 2400 + 390 * (int) (Only3DRenderTime & 1000L) / 1000;
                var0.animate(var6);
            } else {
                CameraXPostRotate();
            }
        } else {
            if(var_19b5) {
                enemies_group.setRenderingEnable(false);
            }

            sub_115f();
        }
    }

    private static void sub_1117() {
        try {
            switch(currentGameState) {
                case 1:
                    if(var_19b5) {
                        sub_10cf(enemies_group);
                        return;
                    }

                    sub_10cf(camera);
                    return;
                default:
                    if(Scripts.var_2602 && !showFinalDialog) {
                        boolean var12 = false;
                        int var13 = (int) Only3DRenderTime - var_1a54;
                        boolean var14 = false;
                        SetCameraTranslation(61.5F, 3.0F, 134.0F);
                        enemies_group.setTranslation(75.2F, 0.0F, 130.0F);
                        enemies_group.setOrientation((float) (cameraYRotOffset + 180), 0.0F, 1.0F, 0.0F);
                        if(var13 <= 6000) {
                            if(var13 >= 5000) {
                                enemies_group.animate(1700 + 300 * (1000 - (6000 - var13)) / 1000);
                                camera.setOrientation((float) (cameraYRotOffset + 65 + 23 * (1000 - (6000 - var13)) / 1000), 0.0F, 1.0F, 0.0F);
                                camera.postRotate(-10.0F, 1.0F, 0.0F, 0.0F);
                            } else {
                                camera.setOrientation((float) (cameraYRotOffset + 65), 0.0F, 1.0F, 0.0F);
                                camera.postRotate(-10.0F, 1.0F, 0.0F, 0.0F);
                                enemies_group.animate(200);
                            }
                        } else {
                            var_1ab8 = (int) Only3DRenderTime;
                            showFinalDialog = true;
                        }
                    } else if(showFinalDialog) {
                        int var0 = (int) Only3DRenderTime - var_1ab8;
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
                            Scripts.var_2602 = false;
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
        RotatingLight = -1;

        int number;
        for(number = 0; number < 32; ++number) {
            gameWorld.removeChild(world_meshes_massive[number]);
            world_meshes_massive[number] = null;
            gameWorld.removeChild(world_sprites3D_massive[number]);
            world_sprites3D_massive[number] = null;
        }

        System.gc();

        for(number = 0; number < 10; ++number) {
            gameWorld.removeChild(StalkersAppearanceGroups[number]);
            StalkersAppearanceGroups[number] = null;
        }

        System.gc();

        for(number = 0; number < 32; ++number) {
            gameWorld.removeChild(mesh_massive_second[number]);
            mesh_massive_second[number] = null;
        }

        System.gc();

        for(number = 0; number < 10; ++number) {
            gameWorld.removeChild(mesh_massive_third[number]);
            mesh_massive_third[number] = null;
        }

        System.gc();

        for(number = 0; number < 32; ++number) {
            gameWorld.removeChild(light_massive_1st[number]);
            light_massive_1st[number] = null;
        }

        System.gc();

        for(number = 0; number < 10; ++number) {
            gameWorld.removeChild(group_massive_2st[number]);
            group_massive_2st[number] = null;
        }

        System.gc();
        gameWorld.removeChild(enemies_group);
        enemies_group = null;
        System.gc();
        gameWorld.removeChild(light);
        light = null;
        System.gc();
        var_20a4 = true;
    }

    private static void sub_115f() {
        sub_328();
        var_19b5 = false;
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
        float max_rotation = doorSettings[currentRoom][cameraDoorId][5]; //Справа
        float min_rotation = doorSettings[currentRoom][cameraDoorId][6]; //Слева
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