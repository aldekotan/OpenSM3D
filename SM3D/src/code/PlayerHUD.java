package code;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class PlayerHUD {

    //Резервируем всё под графику
    public static Graphics graphics = MasterCanvas.graphics;
    public static final int SCREEN_WIDTH = MainMenuScreen.scrWidth;
    public static final int SCREEN_HEIGHT = MainMenuScreen.scrHeight;
    public static Image[] weaponHUDImages = new Image[4];
    public static Image[] crosshairImages = new Image[3];
    private static Image lockImage;
    private static Image damageImage;
    public static Image weaponFireImage;
    public static Image antiradHUDImage;
    public static boolean HUDImagesLoaded = false;
    
    //Координаты локаций на глобальной карте в ПДА
    public static int[] locCoord4;
    public static int[] locCoord5;
    public static int[] locCoord6;
    public static int[] locCoord7;
    public static int[] locCoord8;
    public static int[] locCoord9;
    public static int[] locCoord10;
    public static int[] locCoord11;
    public static int[] locCoord12;
    public static int[] locCoord13;
    public static int[] locCoord14;
    public static int[] locCoord15;
    public static int[] locCoord16;
    public static int[] locCoord17;
    public static int[] locCoord18;
    public static int[] locCoord19;
    public static int[] locCoord19second;
    public static int[][] locationsCoordinates;
    
    //Индикатор урона
    public static boolean playerDamaged;
    public static int timeToDisplayDamageIndicator;
    
    //Диалоги
    private static Vector[] npcPhraseLinesStartsEnds;
    public static int var_937;
    public static int var_993;
    public static int yNPCReplicOffset;
    private static int refTime;
    public static int npcPhrasesTimePassed;
    private static final int yPlayerAnswersStart;
    private static final int var_a97;
    private static int var_ad1;
    private static byte var_ae7;
    private static boolean var_b3a;
    
    //Переход по карте
    public static boolean IsTransitActive;
    public static boolean IsTransitWasCompleted;
    public static int transitStartTime;
    public static byte previousLocation;
    //Макс ширина текстового поля с названием локации
    public static final int TEXT_TARGET_WIDTH;
    //Позиции букв для названия локации на карте
    public static Vector textLinesStartsEnds;
    //Позиции букв для костыля с названием "Лагерь" на карте
    private static Vector textLinesSpecialStartsEnds;
    //Растёт во время загрузки, сообщая об очистке памяти
    public static int garbageCollected;
    //Интро-текст
    public static boolean introRolledFully;
    private static Vector introTextLinesStartsEnds;
    private static int textTimeToRoll;
    private static int textRollsInterval;
    //Курсор-замок
    public static boolean doorLocked;
    //Деньги
    public static boolean showMoneyTaken;
    private static byte moneyTakenCount;

    public static void loadLocationsCoordinates() {
        //TODO: выяснить, к каким коордам какая локация относится на карте
        //x 209        y 289    Место крушения
        locCoord4 = new int[]{ResourceManager.getRectangleParams(41, 4, 0)[0], 
            ResourceManager.getRectangleParams(41, 4, 0)[1]};
        //x 197        y 281    Лагерь (Первый)
        locCoord5 = new int[]{ResourceManager.getRectangleParams(41, 5, 0)[0], 
            ResourceManager.getRectangleParams(41, 5, 0)[1]};
        //x 219        y 274    Найти артефакт на базе бандитов. Галош
        locCoord6 = new int[]{ResourceManager.getRectangleParams(41, 6, 0)[0], 
            ResourceManager.getRectangleParams(41, 6, 0)[1]};
        //x 195        y 256    Найти сталкера в канализации. Ржавый
        locCoord8 = new int[]{ResourceManager.getRectangleParams(41, 8, 0)[0], 
            ResourceManager.getRectangleParams(41, 8, 0)[1]};
        //x 204        y 266    Убить бандитов на лесной дороге. Ржавый
        locCoord7 = new int[]{ResourceManager.getRectangleParams(41, 7, 0)[0], 
            ResourceManager.getRectangleParams(41, 7, 0)[1]};
        //x 206        y 244
        locCoord9 = new int[]{ResourceManager.getRectangleParams(41, 9, 0)[0], 
            ResourceManager.getRectangleParams(41, 9, 0)[1]};
        //x 206        y 222
        locCoord10 = new int[]{ResourceManager.getRectangleParams(41, 10, 0)[0], 
            ResourceManager.getRectangleParams(41, 10, 0)[1]};
        //x 186        y 230
        locCoord11 = new int[]{ResourceManager.getRectangleParams(41, 11, 0)[0], 
            ResourceManager.getRectangleParams(41, 11, 0)[1]};
        //x 166        y 242
        locCoord12 = new int[]{ResourceManager.getRectangleParams(41, 12, 0)[0], 
            ResourceManager.getRectangleParams(41, 12, 0)[1]};
        //x 102        y 280
        locCoord13 = new int[]{ResourceManager.getRectangleParams(41, 13, 0)[0], 
            ResourceManager.getRectangleParams(41, 13, 0)[1]};
        //x 66        y 273
        locCoord14 = new int[]{ResourceManager.getRectangleParams(41, 14, 0)[0], 
            ResourceManager.getRectangleParams(41, 14, 0)[1]};
        //x 88        y 252
        locCoord15 = new int[]{ResourceManager.getRectangleParams(41, 15, 0)[0], 
            ResourceManager.getRectangleParams(41, 15, 0)[1]};
        //x 96        y 34
        locCoord16 = new int[]{ResourceManager.getRectangleParams(41, 16, 0)[0], 
            ResourceManager.getRectangleParams(41, 16, 0)[1]};
        //x 169        y 50
        locCoord17 = new int[]{ResourceManager.getRectangleParams(41, 17, 0)[0], 
            ResourceManager.getRectangleParams(41, 17, 0)[1]};
        //x 114       y 80
        locCoord18 = new int[]{ResourceManager.getRectangleParams(41, 18, 0)[0], 
            ResourceManager.getRectangleParams(41, 18, 0)[1]};
        //x 26        y 108
        locCoord19 = new int[]{ResourceManager.getRectangleParams(41, 19, 0)[0], 
            ResourceManager.getRectangleParams(41, 19, 0)[1]};
        //x 26        y 108
        locCoord19second = new int[]{ResourceManager.getRectangleParams(41, 19, 0)[0], 
            ResourceManager.getRectangleParams(41, 19, 0)[1]};
        locationsCoordinates = new int[][]{locCoord4, locCoord5, locCoord6, 
            locCoord7, locCoord8, locCoord9, locCoord10, locCoord11, locCoord12, 
            locCoord13, locCoord14, locCoord15, locCoord16, locCoord17, 
            locCoord18, locCoord19, locCoord19second};
        //Число локаций совпадает с количеством локаций в игровых файлах.
        //Ниже неполный список локаций, которые мы посещаем.
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
    }

    public static void loadHUDTexturesAndLocationCoorditates() {
        if (!HUDImagesLoaded) {
            loadLocationsCoordinates();

            try {
                int i;
                for (i = 0; i < 4; ++i)
                {
                    weaponHUDImages[i] = Image.createImage("/gamedata/textures/w" 
                            + i + ".png");
                }

                for (i = 0; i < 3; ++i)
                {
                    crosshairImages[i] = Image.createImage("/gamedata/textures/p" 
                            + i + ".png");
                }

                //Загрузка значка "замок" для закрытых дверей
                lockImage = Image.createImage("/gamedata/textures/lock.png"); 
                //Загрузка индикатора попадания по персонажу
                damageImage = Image.createImage("/gamedata/textures/dmgL.png"); 
                //Загрузка текстуры вспышки оружия при выстреле
                weaponFireImage = Image.createImage("/gamedata/textures/wm.png"); 
                //Загрузка текстуры с индикатором действия антирада
                antiradHUDImage = Image.createImage("/gamedata/textures/antirad.png"); 
                HUDImagesLoaded = true;
            } catch (Exception ex) {
            }
        }
    }

    //  ОРУЖЕЙНАЯ СИСТЕМА
    //Сама пушка
    private static void drawWeapon(byte weapId) {
        int var1 = SCREEN_HEIGHT - ResourceManager.getRectangleHeight(9) + 10;
        int x = SCREEN_WIDTH - weaponHUDImages[weapId].getWidth();
        int y = var1 - weaponHUDImages[weapId].getHeight();
        //рисуем вспышку от выстрела
        if (GameScene.shootShakeActive || Scripts.var_228e) {
            byte var4 = 0;
            byte var5 = 0;
            switch (weapId) {
                case 1:
                    var4 = 25;
                    var5 = 10;
                    break;
                case 2:
                    var4 = 10;
                    var5 = 25;
                    break;
                case 3:
                    var4 = 10;
                    var5 = 20;
            }

            graphics.drawImage(weaponFireImage, x + var4 - (weaponFireImage.getWidth() >> 1), y + var5 - (weaponFireImage.getHeight() >> 1), 0);
            Scripts.var_228e = false;
        }

        int var8;
        short var9;
        if (Scripts.var_215f) {
            var8 = (int) GameScene.gameTimeUnpaused - Scripts.var_21d2;
            var9 = Scripts.timeToReload;
            if (var8 <= var9) {
                if (var8 <= var9 / 2) {
                    y += (var1 - weaponHUDImages[weapId].getHeight()) * var8 / var9;
                } else {
                    y = var1 - weaponHUDImages[weapId].getHeight() * var8 / var9;
                }
            } else {
                Scripts.var_215f = false;
            }
        }

        if (Scripts.var_2075) {
            var8 = (int) GameScene.gameTimeUnpaused - Scripts.var_2108;
            var9 = Scripts.timeToSwitchWeapon;
            if (var8 <= var9) {
                if (var8 <= var9 / 2) {
                    int var6 = var1 - weaponHUDImages[Scripts.EncasedWeapon].getHeight();
                    int var7 = (var1 - weaponHUDImages[Scripts.EncasedWeapon].getHeight() - 10) * var8 / var9;
                    y = var6 + var7;
                    x = SCREEN_WIDTH - weaponHUDImages[Scripts.EncasedWeapon].getWidth();
                    graphics.drawImage(weaponHUDImages[Scripts.EncasedWeapon], x, y, 0);
                    return;
                }

                y = var1 - weaponHUDImages[weapId].getHeight() * var8 / var9;
            } else {
                Scripts.var_2075 = false;
            }
        }

        //Рисуем само оружие
        graphics.drawImage(weaponHUDImages[weapId], x, y, 0);
    }
    //Прицел
    private static void drawCrosshair(byte type) {
        int x = SCREEN_WIDTH / 2 - crosshairImages[type].getWidth() / 2;
        int y = SCREEN_HEIGHT / 2 - crosshairImages[type].getHeight() / 2;
        graphics.drawImage(crosshairImages[type], x, y, 0);
        //9.01.25 Добавлены чёрные области по краям от опт. прицела
        if (type == 2) {//Если используется оптический прицел
            //Заливаем окружение прицела чёрным цветом
            graphics.setColor(0, 0, 0);
            int w1 = (SCREEN_WIDTH - crosshairImages[type].getWidth()) / 2;
            //Левый край
            graphics.fillRect(0, 0, w1, SCREEN_HEIGHT);
            int h1 = (SCREEN_HEIGHT - crosshairImages[type].getHeight()) / 2;
            //Верхний центр и вправо
            graphics.fillRect(w1, 0, crosshairImages[type].getWidth() + w1, h1);
            //Правый край и вниз
            graphics.fillRect(SCREEN_WIDTH - w1, h1, w1, SCREEN_HEIGHT - h1);
            //Нижний центр
            graphics.fillRect(w1, SCREEN_HEIGHT - h1,
                    crosshairImages[type].getWidth(), h1);
        }
        //9.01.25
        if (GameScene.getActiveObjectIdUnderCursor() && type == 0) {
            int var3;
            if ((var3 = getNPCNameTextId2(GameScene.getActivableObjState(GameScene.activableObjIdUnderCursor))) == -1) {
                return;
            }

            x = SCREEN_WIDTH / 2 - TextCreator.getWideLineWidth(0, var3) / 2 + 5;
            y -= TextCreator.getSymbolHeight(0);
            TextCreator.drawLineByAnchor(0, var3, x, y, 0);//check var3
        }

    }
    //Или замок, по ситуации
    private static void DrawLockAtCenter() {
        if (GameScene.isBotDeathAnimFinished) {
            graphics.drawImage(lockImage, SCREEN_WIDTH / 2 - lockImage.getWidth() / 2, SCREEN_HEIGHT / 2 - lockImage.getHeight() / 2, 0);
        }

    }
    //Если продамажили
    public static void drawDamageIndicatorAndHealthBar(boolean insideInv) {
        graphics.setClip(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        if (playerDamaged && !insideInv) {
            if (GameScene.gameTimeUnpaused >= (long) timeToDisplayDamageIndicator) {
                playerDamaged = false;
            }

            if (GameScene.damageEffect) {
                graphics.drawImage(damageImage, 0, SCREEN_HEIGHT / 2 - damageImage.getHeight() / 2, 0);
                graphics.drawRegion(damageImage, 0, 0, damageImage.getWidth(), damageImage.getHeight(), 2, SCREEN_WIDTH - damageImage.getWidth(), SCREEN_HEIGHT / 2 - damageImage.getHeight() / 2, 0);
            } else if (GameScene.sub_911()) {
                graphics.drawImage(damageImage, 0, SCREEN_HEIGHT / 2 - damageImage.getHeight() / 2, 0);
            } else {
                graphics.drawRegion(damageImage, 0, 0, damageImage.getWidth(), damageImage.getHeight(), 2, SCREEN_WIDTH - damageImage.getWidth(), SCREEN_HEIGHT / 2 - damageImage.getHeight() / 2, 0);
            }
        }

        int var1 = 55 * Scripts.playerHealth / Scripts.playerMaxHealth;
        int var2;
        int var3;
        if (insideInv) {
            var2 = ResourceManager.getRectangleParams(38, 7, 0)[0] + ResourceManager.getRectangleWidth(11);
            var3 = ResourceManager.getRectangleParams(38, 7, 0)[1] + ResourceManager.getRectangleHeight(11) / 2 - 1;
        } else {
            var2 = ResourceManager.getRectangleParams(11, 1, 0)[0] + 1;
            var3 = ResourceManager.getRectangleParams(11, 1, 0)[1] + ResourceManager.getRectangleHeight(11) / 2 - 1;
        }

        int var4 = 0;

        for (byte var5 = 0; var5 < 3; ++var5) {
            switch (var5) {
                case 0: //
                case 2:
                    var4 = 9251614; //Тёмно-красный
                    break;
                case 1:
                    var4 = 15704963; //Светло-красный
            }

            graphics.setColor(var4);
            graphics.fillRect(var2, var3 + 3 * var5 / 3, var1, 1);
        }

    }
    //Если ширнулись антирадом
    private static void drawAntiradIndicator() {
        int var0 = ResourceManager.getRectangleParams(12, 0, 0)[0];
        int var1 = ResourceManager.getRectangleParams(12, 1, 0)[1] + ResourceManager.getRectangleHeight(12) / 2 - 1;
        int var2 = ResourceManager.getRectangleParams(12, 1, 0)[0] + 1;
        int var3 = 55 * Scripts.AntiradDuration / 20000;
        graphics.drawImage(antiradHUDImage, var0, var1 - (ResourceManager.getRectangleHeight(12) / 2 - 1), 0);
        int var4 = 0;

        for (byte var5 = 0; var5 < 3; ++var5) {
            switch (var5) {
                case 0:
                case 2:
                    var4 = 1594115; //тёмно-зелёный цвет
                    break;
                case 1:
                    var4 = 2670136; //светло-зелёный цвет
            }

            graphics.setColor(var4);
            graphics.fillRect(var2, var1 + 3 * var5 / 3, var3, 1);
        }

    }
    //Патроны в магазине и объём магазина
    private static void drawWeaponHUD() {
        graphics.setColor(0);
        graphics.fillRect(0, SCREEN_HEIGHT - (ResourceManager.getRectangleHeight(8) >> 1), SCREEN_WIDTH, ResourceManager.getRectangleHeight(8) >> 1);
        ResourceManager.drawUserInterfaceItems(graphics, 37, 0, 0);
        int var0 = SCREEN_WIDTH / 2 - 36 + 7;
        int var1 = SCREEN_HEIGHT - ResourceManager.getRectangleHeight(13);
        drawSoftButtonNames(1, 0, 1, true);//меню/menu
        short rightSoftText;
        if (Scripts.ItemWalkieTalkieInInventaryBool) {
            rightSoftText = 388;//рация
        } else if (Scripts.playerCanLeaveLevel) {
            rightSoftText = 38;//карта
        } else {
            rightSoftText = 389;//оружие
        }

        drawSoftButtonNames(1, 0, 1, true);
        drawSoftButtonNames(0, 1, rightSoftText, true);
        int var3 = TextCreator.getWideLineWidth(1, 377); //символ /
        int var4 = var0 + 24 - (Scripts.playerWeaponsAmmo[Scripts.playerActiveWeapon] / 10 > 0 ? var3 * 2 : var3 + 1);
        int var5 = var1 + ResourceManager.getRectangleHeight(13) / 2 - TextCreator.getSymbolHeight(1) / 2;
        TextCreator.drawNumber(1, Scripts.playerWeaponsAmmo[Scripts.playerActiveWeapon], var4, var5, 0);
        int var6 = var0 + 24;
        TextCreator.drawLineByAnchor(1, 377, var6, var5, 0);// символ /
        int var8 = var0 + 24 + var3;
        byte var10;
        switch (Scripts.playerActiveWeapon) {
            case 0:
                var10 = 12;
                break;
            case 1:
                var10 = 30;
                break;
            case 2:
                var10 = 20;
                break;
            case 3:
                var10 = 25;
                break;
            default:
                var10 = 0;
        }

        TextCreator.drawNumber(1, var10, var8, var5, 0);
    }

    //  ДИАЛОГОВАЯ СИСТЕМА
    //Делим текст на линии
    public static void splitNPCPhraseToLines() {
        var_937 = 0;
        int length;
        npcPhraseLinesStartsEnds = new Vector[length = Scripts.phracesIdArray.length];

        for (byte i = 0; i < length; ++i) {
            if (Scripts.phracesIdArray[i] != -1) {
                npcPhraseLinesStartsEnds[i] = TextCreator.splitOnLines(Scripts.phracesIdArray[i], ModChanges.dialogDrawWidth, 0);
            }
        }

    }
    //Сбрасываем время промотки текста
    public static void resetNPCPhraseOffsetByTime() {
        refTime = (int) System.currentTimeMillis();
        var_993 = 0;
        npcPhrasesTimePassed = 0;
    }
    //Рисуем текущую реплику нпс в диалоге с ним
    private static void drawNPCcurrentPhrase() {
        int var0 = TextCreator.getSymbolHeight(0);
        int var1 = 92 / var0;
        int var2 = TextCreator.getSymbolHeight(0) * npcPhraseLinesStartsEnds[Scripts.currentNpcPhrase * 2].size();
        if (npcPhraseLinesStartsEnds[Scripts.currentNpcPhrase * 2].size() > var1) {
            int timePassed;
            if ((timePassed = (int) (System.currentTimeMillis() - (long) refTime)) >= 4000 && timePassed - npcPhrasesTimePassed >= 200) {
                npcPhrasesTimePassed = timePassed;
                var_993 += var0 / 4;
                yNPCReplicOffset = 24 + var0 - var_993;
                if (var_993 > var2) {
                    yNPCReplicOffset = 24 + var0;
                    resetNPCPhraseOffsetByTime();
                }
            }
        } else {
            yNPCReplicOffset = 24 + var0;
        }

        graphics.setClip(20, 24 + var0, ModChanges.dialogDrawWidth, 91);
        TextCreator.drawReplicInsideFrame(Scripts.phracesIdArray[Scripts.currentNpcPhrase * 2], 20, yNPCReplicOffset, 0, 0, graphics, 0, -1, npcPhraseLinesStartsEnds[Scripts.currentNpcPhrase * 2]);
    }
    //Рисуем ответы главгероя, если они есть
    private static void drawPlayerAnswers() {
        boolean var0 = Scripts.currentDialogId == 21;
        graphics.setClip(20, yPlayerAnswersStart, ModChanges.dialogDrawWidth, 91);
        if (!Scripts.dialogCompleted && Scripts.dialogStructure.length != 0) {
            int var1 = 0;
            int var3 = yPlayerAnswersStart;
            byte var4 = Scripts.dialogStructure[Scripts.givenAnswersCount];

            for (byte var5 = 0; var5 < var4; ++var5) {
                int var6 = var5 != Scripts.selectedAnswer && !var0 ? 1 : 0;
                int line = (Scripts.currentNpcPhrase + var1) * 2 + 1;
                byte var8 = Scripts.givenAnswersCount + 1 + var1 > Scripts.dialogStructure.length - 1 ? 0 : Scripts.dialogStructure[Scripts.givenAnswersCount + 1 + var1];
                var1 += var8 > 1 ? var8 + 1 : var8;
                npcPhraseLinesStartsEnds[line] = TextCreator.splitOnLines(Scripts.phracesIdArray[line], ModChanges.dialogDrawWidth, var6);
                int var9 = TextCreator.getSymbolHeight(var6);
                int var10 = var3;
                boolean var11 = Scripts.selectedAnswer != var_ae7;
                var_ae7 = Scripts.selectedAnswer;
                if (var11) {
                    var_b3a = false;
                }

                boolean var12 = var3 + npcPhraseLinesStartsEnds[line].size() * var9 >= var_a97;
                if (var3 - var_ad1 < yPlayerAnswersStart && var5 == Scripts.selectedAnswer && !var0) {
                    var_ad1 = 0;
                }

                if (var12 && !var_b3a) {
                    var6 = 1;
                }

                if (var0) {
                    var6 = 0;
                }

                if (var0 && var12) {
                    ++var_937;
                    if (var_937 >= 50) {//50
                        var_ad1 += var9 >> 3;
                        if (var3 + npcPhraseLinesStartsEnds[line].size() * var9 - var_ad1 <= yPlayerAnswersStart + 46) {
                            var_ad1 = 0;
                            var_937 = 0;
                        }
                    }
                }

                TextCreator.drawReplicInsideFrame(Scripts.phracesIdArray[line], 20, var3 - var_ad1, 0, var6, graphics, 0, -1, npcPhraseLinesStartsEnds[line]);
                var3 = var3 + npcPhraseLinesStartsEnds[line].size() * var9 + var9 / 2;
                if (var12 && var5 == Scripts.selectedAnswer && !var_b3a && !var0) {
                    var_ad1 = var10 + npcPhraseLinesStartsEnds[line].size() * var9 - var_a97;
                    var_b3a = true;
                    return;
                }
            }
        }

    }
    //Получить имя нпс через текущий диалог
    private static short getNpcNameTextId() {
        switch (Scripts.currentDialogId) {
            case 4:
                return (short) 340;//Wonk
            case 5:
                return (short) 339;//Fence
            case 6:
                return (short) 337;//Rusty
            case 7:
                return (short) 338;//Galosh
            case 8:
                return (short) 343;//Mole
            case 9:
                return (short) 345;//Parent
            case 10:
                return (short) 344;//Muzzle
            case 11:
                return (short) 348;//Gutalin
            case 12:
                return (short) 350;//Hose
            case 13:
                return (short) 349;//Belomor
            case 14:
                return (short) 351;//Kainazovskiy
            case 15:
                return (short) 342;//Coward
            case 16:
                return (short) 341;//Whistler
            case 17:
                return (short) 347;//Manikovskiy
            case 18:
                return (short) 346;//Cap
            case 19:
                return (short) 352;//Kobold
            case 20:
                return (short) 353;//Ghost
            case 21:
                return (short) 387;//RADIO
            default:
                return (short) -1;
        }
    }
    //Получить имя нпс через ID диалога
    private static short getNPCNameTextId2(int dialogId) {
        switch (dialogId) {
            case 4:
                return (short) 340;
            case 5:
                return (short) 339;
            case 6:
                return (short) 337;
            case 7:
                return (short) 338;
            case 8:
                return (short) 343;
            case 9:
                return (short) 345;
            case 10:
                return (short) 344;
            case 11:
                return (short) 348;
            case 12:
                return (short) 350;
            case 13:
                return (short) 349;
            case 14:
                return (short) 351;
            case 15:
                return (short) 342;
            case 16:
                return (short) 341;
            case 17:
                return (short) 347;
            case 18:
                return (short) 346;
            case 19:
                return (short) 352;
            case 20:
                return (short) 353;
            case 21:
                return (short) 387;
            default:
                return (short) -1;
        }
    }
    //Рисуем рамку диалогового окна
    private static void drawDialogUI() {
        graphics.setColor(0);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        if (Scripts.phracesIdArray != null && Scripts.dialogStructure != null) {

            ResourceManager.drawUserInterfaceItems(graphics, 35, 0, -3);
            int yDest = 10;
            short nameId;
            if ((nameId = getNpcNameTextId()) == 387) {
                yDest = 10 + (ResourceManager.getRectangleParams(2, 6, 0)[3] 
                        + ResourceManager.getRectangleParams(2, 7, 0)[3] 
                        + ResourceManager.getRectangleParams(2, 8, 0)[3] 
                        + ResourceManager.getRectangleParams(2, 9, 0)[3] 
                        + ResourceManager.getRectangleParams(2, 10, 0)[3] - 12);
            }

            TextCreator.drawLineByAnchor(0, nameId, 15, yDest, 0);
            drawSoftButtonNames(0, 1, 371, true);
            drawNPCcurrentPhrase();
            drawPlayerAnswers();
        }

    }
    //Софтовые клавиши. 1.1 не работают, как и 0.0
    public static void drawSoftButtonNames(int drawLeft, int left0right1, 
            int textId, boolean uiAlreadyDrawn) {
        int width;
        int height;

        if (drawLeft == 1) {
            if (!uiAlreadyDrawn) {
                ResourceManager.drawUserInterfaceItems(graphics, 8, 0, 0);
                width = ResourceManager.getRectangleWidth(8) / 4;
                height = SCREEN_HEIGHT - ResourceManager.getRectangleHeight(8) + 2;
            } else {
                width = ResourceManager.getRectangleWidth(8) / 4;
                height = SCREEN_HEIGHT - ResourceManager.getRectangleHeight(8) + 1;
            }

            if (textId != -1) {
                if (GameScene.currentGameState == 1) {
                    TextCreator.drawLineByAnchor(1, textId, width, height, 0);
                    return;
                }

                TextCreator.drawLineByAnchor(0, textId, width, height, 0);
            }
        } else if (left0right1 == 1) {
            if (!uiAlreadyDrawn) {
                ResourceManager.drawUserInterfaceItems(graphics, 9, 0, 0);
                width = SCREEN_WIDTH - 3 * ResourceManager.getRectangleWidth(8) / 4;
                height = SCREEN_HEIGHT - ResourceManager.getRectangleHeight(8) + 2;
            } else {
                width = SCREEN_WIDTH - 3 * ResourceManager.getRectangleWidth(8) / 4;
                height = SCREEN_HEIGHT - ResourceManager.getRectangleHeight(8) + 1;
            }

            if (textId != -1) {
                //id 389: оружие
                if (textId == 389 && !Scripts.isItemEquipped((short) 117) && !Scripts.isItemEquipped((short) 118) && !Scripts.isItemEquipped((short) 119) || GameScene.currentGameState == 1) {
                    TextCreator.drawLineByAnchor(1, textId, width, height, 0);
                    return;
                }

                TextCreator.drawLineByAnchor(0, textId, width, height, 0);
            }
        }

    }

    //  ГЛОБАЛЬНАЯ КАРТА
    //Отрисовка самого пда
    private static void drawPDA() {
        graphics.setClip(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphics.setColor(0);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphics.drawImage(ResourceManager.interfaceImages[12], 0, 3, 0);
        drawLinesForPDA();
        //Для тестов. Возвращение старого PDA
        //ResourseManager.drawUserInterfaceItems(graphics, 41, 0, 0);
        //
        //красный цвет
        graphics.setColor(16711680); 

        for (byte locId = 0; locId < 17; ++locId) {
            if (Scripts.checkLocationAvailability(locId) || locId == GameScene.currentLocation || locId == previousLocation) {
                if (!GameScene.locationCampMark[locId] && locId != 0) {
                    ResourceManager.DrawInterfaceImageToSelectedRegion(graphics, 58, locationsCoordinates[locId][0] - 5, locationsCoordinates[locId][1] - 5, 0);
                } else {
                    ResourceManager.DrawInterfaceImageToSelectedRegion(graphics, 59, locationsCoordinates[locId][0] - 5, locationsCoordinates[locId][1] - 5, 0);
                }
            }
        }

        drawLocationName();
        //Идти (GO)
        drawSoftButtonNames(0, 1, 390, true);
    }
    //Анимируем переход по карте
    public static void ActivateTransitAnimation() {
        transitStartTime = (int) System.currentTimeMillis();
        previousLocation = (byte) GameScene.currentLocation;
        IsTransitActive = true;
    }
    //Рисуем линии, указывающие на нужную нам позицию
    private static void drawLinesForPDA() {
        graphics.setColor('\uff00');
        int x_start;
        int y_start;
        int x_end;
        int y_end;
        if (IsTransitActive) {
            x_start = (int) System.currentTimeMillis() - transitStartTime;
            y_start = locationsCoordinates[previousLocation][0];
            x_end = locationsCoordinates[previousLocation][1];
            y_end = locationsCoordinates[GameScene.nextLocation][0];
            int y_second_start = locationsCoordinates[GameScene.nextLocation][1];
            int var5 = (int) Math.sqrt((double) ((y_end - y_start) * (y_end - y_start) + (y_second_start - x_end) * (y_second_start - x_end)));
            int var6 = 1000 * var5 / 30;
            int x_second_end;
            int y_second_end;
            if (x_start <= var6 && var6 > 0) {
                x_second_end = y_start + (y_end - y_start) * x_start / var6;
                y_second_end = x_end + (y_second_start - x_end) * x_start / var6;
                ResourceManager.DrawInterfaceImageToSelectedRegion(graphics, 61, x_second_end, y_second_end, 0);
            } else {
                //вертикальная линия
                y_end = locationsCoordinates[GameScene.nextLocation][0];
                x_second_end = locationsCoordinates[GameScene.nextLocation][0];
                y_second_end = SCREEN_HEIGHT;
                graphics.drawLine(y_end, 0, x_second_end, y_second_end);
                
                //горизонтальная линия
                y_second_start = locationsCoordinates[GameScene.nextLocation][1];
                x_second_end = SCREEN_WIDTH;
                y_second_end = locationsCoordinates[GameScene.nextLocation][1];
                graphics.drawLine(0, y_second_start, x_second_end, y_second_end);
                
                IsTransitActive = false;
                IsTransitWasCompleted = true;
                previousLocation = -1;
            }
        }

        x_start = locationsCoordinates[GameScene.nextLocation][0];
        x_end = locationsCoordinates[GameScene.nextLocation][0];
        y_end = SCREEN_HEIGHT;
        graphics.drawLine(x_start, 0, x_end, y_end);
        y_start = locationsCoordinates[GameScene.nextLocation][1];
        x_end = SCREEN_WIDTH;
        y_end = locationsCoordinates[GameScene.nextLocation][1];
        graphics.drawLine(0, y_start, x_end, y_end);
    }
    //Рисуем название выделенной локации
    private static void drawLocationName() {
        int y = SCREEN_HEIGHT - 2 * ResourceManager.getRectangleHeight(8) 
                - TextCreator.getSymbolHeight(0) 
                * (textLinesStartsEnds.size() + 1);
        //Названия сюж.локаций начинаются с 354 индекса
        int locationName = Scripts.getLocationNameId((byte) GameScene.nextLocation) + 354;
        //Если мы ещё не покинули место крушения вертолёта
        if (GameScene.nextLocation == 0) {
            //385 = Место крушения
            textLinesStartsEnds = TextCreator.splitOnLines(385, TEXT_TARGET_WIDTH, 0);
            TextCreator.drawReplicInsideFrame(385, 
                    15, y, 0, 0, graphics, 0, -1, textLinesStartsEnds);
        } 
        //Обычная процедура отрисовки названия локации
        else if (locationName >= 354) {
            TextCreator.drawReplicInsideFrame(locationName, 
                    15, y, 0, 0, graphics, 0, -1, textLinesStartsEnds);
        } 
        //По сути-то, костыль для названия первого лагеря, 
        //срабатывающий из всех трёх
        else if (GameScene.nextLocation == 1 
                || GameScene.nextLocation == 6 
                || GameScene.nextLocation == 12) {
            //386 id: Лагерь
            textLinesSpecialStartsEnds = TextCreator.splitOnLines(386, TEXT_TARGET_WIDTH, 0);
            TextCreator.drawReplicInsideFrame(386, 
                    15, y, 0, 0, graphics, 0, -1, textLinesSpecialStartsEnds);
        }
    }

    //  ОПОВЕЩЕНИЕ ПО ЦЕНТРУ ЭКРАНА
    //ПРОДОЛЖЕНИЕ СЛЕДУЕТ
    private static void drawToBeContinued() {
        graphics.setClip(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphics.setColor(0);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        TextCreator.drawLineByAnchor(0, 368, SCREEN_WIDTH / 2 - TextCreator.symbolWidth[0][10] * 11 / 2 - 5, SCREEN_HEIGHT / 2 - TextCreator.symbolHeight[0], 0);
        TextCreator.drawLineByAnchor(0, 369, SCREEN_WIDTH / 2 - TextCreator.symbolWidth[0][10] * 7 / 2 - 5, SCREEN_HEIGHT / 2 + TextCreator.symbolHeight[0], 0);
    }
    //ИГРА ОКОНЧЕНА
    private static void drawGameOver() {
        graphics.setClip(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        graphics.setColor(0);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        TextCreator.drawLineByAnchor(0, 370, SCREEN_WIDTH / 2 - TextCreator.symbolWidth[0][10] * 9 / 2, SCREEN_HEIGHT / 2, 0);
    }
    //ЗАГРУЗКА
    private static void drawLoadingText() {
        graphics.setColor(0);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        TextCreator.drawLineByAnchor(0, 41, SCREEN_WIDTH / 2 - 20, SCREEN_HEIGHT / 2 - TextCreator.getSymbolHeight(0) / 2, 0);
    }

    //  ИНТРО
    //Подготовка
    public static void prepareIntroText() {
        introTextLinesStartsEnds = TextCreator.splitOnLines(ModChanges.startingintroduction, 135, 0);
        textTimeToRoll = (introTextLinesStartsEnds.size() - 1) * 2000;
        textRollsInterval = (int) System.currentTimeMillis() - 3000;
    }
    //Отрисовка
    private static void drawIntroText() {
        int textHeight = introTextLinesStartsEnds.size() * TextCreator.getSymbolHeight(0);
        int y = 70;
        int time = (int) (System.currentTimeMillis() - (long) textRollsInterval);
        boolean timeRanOut = false;
        if (time >= 6000) {
            time -= 6000;
            timeRanOut = true;
        }

        //Эта красота возвращает промотку текста в начало, когда время истекает
        int timePassed = textTimeToRoll;
        if (timeRanOut) {
            if (time > timePassed) {
                introRolledFully = true;
                return;
            }

            y = 70 - textHeight * time / timePassed;
        }

        graphics.setColor(0);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        ResourceManager.drawUserInterfaceItems(graphics, 44, 0, 0);
        graphics.setClip(58, 70, 135, 185);
        TextCreator.drawReplicInsideFrame(ModChanges.startingintroduction, 
                58, y, 0, 0, graphics, 0, -1, introTextLinesStartsEnds);
        graphics.setClip(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    //  ДЕНЬГИ
    //Сбрасываем счётчик и даём отрисоваться значению
    public static void allowShowingTakenMoney() {
        moneyTakenCount = 0;
        showMoneyTaken = true;
    }
    //Отрисовка поднятых денег (тут был какой-то баг, который я так и не исправил)
    private static void drawMoneyTaken() {
        ++moneyTakenCount;
        if (moneyTakenCount <= MathUtils.fps) {
            byte timeToShow = 2;
            if (Scripts.MoneyTaken < 100) {
                timeToShow = 2;
            }

            if (Scripts.MoneyTaken < 1000) {
                timeToShow = 3;
            }

            if (Scripts.MoneyTaken >= 1000) {
                timeToShow = 4;
            }

            int textWidth = 6 * timeToShow;
            int x = SCREEN_WIDTH - textWidth;
            int y = SCREEN_HEIGHT / 2;
            TextCreator.drawNumber(0, Scripts.MoneyTaken, x, y, 0);
            TextCreator.drawLineByAnchor(0, 66, SCREEN_WIDTH - 6, y, 0);
        } else {
            moneyTakenCount = 0;
            showMoneyTaken = false;
        }
    }

    public static void updatePlayerHUDState() {
        if (GameScene.currentGameState != 0 && GameScene.currentGameState != 13 && GameScene.currentGameState != -2) {
            if (GameScene.currentGameState == 14) {
                drawIntroText();
                //next/дальше
                drawSoftButtonNames(0, 1, 371, true);
            } else if (!Scripts.endingCutscene) {
                if (GameScene.currentGameState == 11) {
                    drawToBeContinued();
                } else if (GameScene.currentGameState == 4) {
                    drawGameOver();
                } else if (GameScene.currentGameState == 5) {
                    drawPDA();
                } else if (GameScene.currentGameState == 9) {
                    drawDialogUI();
                } else {
                    if (!GameScene.useThirdPerson) {
                        if (!Scripts.OpticalSight) {
                            drawWeapon(Scripts.playerActiveWeapon);
                            if (!GameScene.getActiveObjectIdUnderCursor() && !GameScene.getOpenDoorIdUnderCursor()) {
                                if (doorLocked) {
                                    DrawLockAtCenter();
                                } else {
                                    drawCrosshair((byte) 1);
                                }
                            } else {
                                drawCrosshair((byte) 0);
                            }
                        } else {
                            drawCrosshair((byte) 2);
                        }
                    }

                    drawWeaponHUD();
                    drawDamageIndicatorAndHealthBar(false);
                    if (Scripts.IsAntiradIsUsed) {
                        drawAntiradIndicator();
                    }

                    if (showMoneyTaken) {
                        drawMoneyTaken();
                    }

                }
            }
        } else {
            drawLoadingText();
        }
    }

    static {
        Font.getFont(0, 2, 16);
        Font.getFont(0, 0, 8);
        Font.getFont(0, 1, 8);
        yPlayerAnswersStart = SCREEN_HEIGHT / 2 + 12;
        var_a97 = yPlayerAnswersStart + 92;
        TEXT_TARGET_WIDTH = SCREEN_WIDTH / 2;
        textLinesStartsEnds = new Vector();
        textLinesSpecialStartsEnds = new Vector();
        introTextLinesStartsEnds = new Vector();
    }
}
