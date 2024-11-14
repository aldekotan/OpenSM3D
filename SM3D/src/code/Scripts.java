package code;

//Класс отвечающий РПГ составляющую

public final class Scripts {
    //Оружие на поясе

    public static byte EncasedWeapon;
    //Оружие в руках
    public static byte playerActiveWeapon;

    //===Характеристики бронежилетов=== //Защита от пуль, радиации, аномалий, и цена бронежилета
    public static final short[] armorLeatherJacketStats = new short[]{(short) 10, (short) 0, (short) 10, (short) 200};
    public static final short[] armorMailJacketStats = new short[]{(short) 15, (short) 0, (short) 0, (short) 400};
    public static final short[] armorMercSuitStats = new short[]{(short) 25, (short) 5, (short) 5, (short) 850};
    public static final short[] armorSevaSuitStats = new short[]{(short) 45, (short) 20, (short) 30, (short) 3000};
    public static final short[] armorStalkerSuitStats = new short[]{(short) 30, (short) 15, (short) 20, (short) 1200};
    public static final short[] armorSPP9mStats = new short[]{(short) 20, (short) 80, (short) 90, (short) 4000};

    //Размер инвентаря
    public static short[] inventoryItems = new short[100];
    //Текущее количество предметов в нём
    public static short CurrentNumberOfInventaryItems;

    //Будут удалены после выхода с локации
    public static short[] locationExclusiveItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};

    //Набор экипировки
    public static short[] equipmentSlots = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1};

    //Локации, после прохождения которых выполняются те или иные квесты
    public static final byte[] StoryLocationMassive = new byte[]{(byte) 3, (byte) 4, (byte) 2, (byte) 5, (byte) 6, (byte) 8, (byte) 7, (byte) 10, (byte) 12, (byte) 11, (byte) 13, (byte) 14, (byte) 15};

    //===Инициализация структуры диалогов===
    //Состояние диалога
    public static byte rustyDialogState;
    //Количество вариантов ответа после каждого ответа главгероя
    public static final byte[] var_2a9 = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 3, (byte) 1, (byte) 1, (byte) 1};
    //Адреса на текстовые строки в главном текстовом массиве
    public static final short[] var_2cc = new short[]{(short) 78, (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, (short) 86, (short) 87, (short) 88, (short) 89, (short) 90};
    public static final byte[] var_30f = new byte[]{(byte) 2, (byte) 3, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_369 = new short[]{(short) 91, (short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 101};
    public static final byte[] var_412 = new byte[0];
    public static final short[] var_44c = new short[]{(short) 102};
    public static final byte[] var_488 = new byte[]{(byte) 1, (byte) 1, (byte) 3, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_4d9 = new short[]{(short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) -1, (short) 111, (short) -1, (short) 112, (short) 113, (short) 114, (short) 115};
    public static final byte[] var_51d = new byte[0];
    public static final short[] var_533 = new short[]{(short) 116};
    public static final byte[] var_54c = new byte[]{(byte) 2, (byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_5a5 = new short[]{(short) 117, (short) 118, (short) 119, (short) 120, (short) 121, (short) 122, (short) 123, (short) 124, (short) 125};
    public static final byte[] var_5c1 = new byte[0];
    public static final short[] var_5dc = new short[]{(short) 126};
    public static final byte[][] rustyHierarchy = new byte[][]{var_2a9, var_30f, var_412, var_488, var_51d, var_54c, var_5c1};

    public static byte galoshQuestState;
    public static final byte[] var_6a7 = new byte[]{(byte) 5, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_6e4 = new short[]{(short) 127, (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, (short) 153, (short) 154};
    public static final byte[] var_715 = new byte[]{(byte) 1};
    public static final short[] var_74b = new short[]{(short) 155, (short) 156, (short) 157};
    public static final byte[][] galoshHierarchy = new byte[][]{var_6a7, var_715};

    public static byte zaborDialogState;
    public static final byte[] var_882 = new byte[]{(byte) 1, (byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] zaborPhIdBefore = new short[]{(short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, (short) 142};
    public static final byte[] var_8fe = new byte[0];
    public static final short[] zaborPhIdAfter = new short[]{(short) 143};
    public static final byte[][] zaborDialogSctucture = new byte[][]{var_882, var_8fe};

    public static byte botanikDialogState;
    public static final byte[] var_9ab = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] botanikPhracesId = new short[]{(short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150, (short) 151, (short) 152};
    public static final byte[][] botanikDialogStructure = new byte[][]{var_9ab};

    public static byte svistunDialogState;
    public static final byte[] var_acc = new byte[]{(byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_afa = new short[]{(short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164};
    public static final byte[][] var_b08 = new byte[][]{var_acc};

    public static byte trusDialogState;
    public static final byte[] var_b5d = new byte[]{(byte) 1, (byte) 1};
    public static final short[] var_bad = new short[]{(short) 165, (short) 166, (short) 167, (short) 168};
    public static final byte[][] var_bc1 = new byte[][]{var_b5d};

    public static byte krotDialogState;
    public static final byte[] var_c09 = new byte[]{(byte) 3, (byte) 4, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_c56 = new short[]{(short) 169, (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178, (short) -1, (short) 179, (short) 180, (short) 181};
    public static final byte[][] var_c64 = new byte[][]{var_c09};

    public static byte haryaDialogState;
    public static final byte[] var_cad = new byte[]{(byte) 3, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_cf9 = new short[]{(short) 182, (short) 183, (short) 184, (short) 185, (short) 186, (short) 187};
    public static final byte[] var_d08 = new byte[]{(byte) 1};
    public static final short[] var_d49 = new short[]{(short) 188, (short) 189, (short) 190};
    public static final byte[] var_d6a = new byte[0];
    public static final short[] var_da7 = new short[]{(short) 191};
    public static final byte[] var_de0 = new byte[0];
    public static final short[] var_ded = new short[]{(short) 192};
    public static final byte[][] var_e4b = new byte[][]{var_cad, var_d08, var_d6a, var_de0};

    public static byte batyaDialogState;
    public static final byte[] var_e7f = new byte[]{(byte) 1, (byte) 1, (byte) 3, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_e8e = new short[]{(short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203};
    public static final byte[] var_e9a = new byte[]{(byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_ed0 = new short[]{(short) 204, (short) 205, (short) 206, (short) 207};
    public static final byte[] var_f30 = new byte[]{(byte) 3, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_f44 = new short[]{(short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 394};
    public static final byte[] var_f64 = new byte[0];
    public static final short[] var_fad = new short[]{(short) 213};
    public static final byte[] var_ffd = new byte[]{(byte) 1, (byte) 1};
    public static final short[] var_102d = new short[]{(short) 214, (short) 215, (short) 216, (short) 217};
    public static final byte[][] var_1084 = new byte[][]{var_e7f, var_e9a, var_f30, var_f64, var_ffd};

    public static byte commanderDialogState;
    public static final byte[] var_10a1 = new byte[]{(byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_10ce = new short[]{(short) 218, (short) 219, (short) 220, (short) 221, (short) 222, (short) 223};
    public static final byte[][] var_10e7 = new byte[][]{var_10a1};

    public static byte manikovskiDialogState;
    public static final byte[] var_1148 = new byte[]{(byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_1153 = new short[]{(short) 224, (short) 225, (short) -1, (short) 226, (short) 227};
    public static final byte[][] var_117d = new byte[][]{var_1148};

    public static byte gutalinDialogState;
    public static final byte[] var_11ae = new byte[]{(byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_11d1 = new short[]{(short) 228, (short) 229, (short) 230, (short) 231, (short) 232};
    public static final byte[] var_11e4 = new byte[0];
    public static final short[] var_123d = new short[]{(short) 233};
    public static final byte[][] var_1254 = new byte[][]{var_11ae, var_11e4};

    public static byte belomorDialogState;
    public static final byte[] var_12cc = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_132d = new short[]{(short) 234, (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241, (short) -1, (short) 242, (short) -1};
    public static final byte[] var_1385 = new byte[0];
    public static final short[] var_1397 = new short[]{(short) 248};
    public static final byte[] var_13ec = new byte[]{(byte) 1, (byte) 1};
    public static final short[] var_1411 = new short[]{(short) 243, (short) 244, (short) 245, (short) 246};
    public static final byte[] var_145f = new byte[0];
    public static final short[] var_14b3 = new short[]{(short) 247};
    public static final byte[][] var_14ef = new byte[][]{var_12cc, var_1385, var_13ec, var_145f};

    public static byte shlangDialogState;
    public static final byte[] var_1551 = new byte[]{(byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] var_15b3 = new short[]{(short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255};
    public static final byte[] var_15dd = new byte[]{(byte) 1};
    public static final short[] var_1609 = new short[]{(short) 256, (short) 257};
    public static final byte[] var_1621 = new byte[]{(byte) 1};
    public static final short[] var_1680 = new short[]{(short) 258, (short) 259, (short) 260};
    public static final byte[][] var_16a8 = new byte[][]{var_1551, var_15dd, var_1621};

    public static byte kaynazovskiDialogState;
    public static final byte[] var_1733 = new byte[]{(byte) 1, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_178f = new short[]{(short) 261, (short) 262, (short) 263, (short) 264, (short) -1, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269, (short) 270, (short) 271};
    public static final byte[] var_17e5 = new byte[]{(byte) 1, (byte) 1};
    public static final short[] var_1821 = new short[]{(short) 272, (short) 273, (short) 274, (short) 275};
    public static final byte[] var_1837 = new byte[]{(byte) 1};
    public static final short[] var_187a = new short[]{(short) 289, (short) 290};
    public static final byte[][] var_18fb = new byte[][]{var_1733, var_17e5, var_1837};

    public static byte koboldDialogState = 1;
    public static final byte[] var_193a = new byte[]{(byte) 1, (byte) 1};
    public static final short[] var_198b = new short[]{(short) 276, (short) 277, (short) 278, (short) 279};
    public static final byte[] var_1995 = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_19e8 = new short[]{(short) 280, (short) 281, (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287};
    public static final byte[] var_1a16 = new byte[0];
    public static final short[] var_1a64 = new short[]{(short) 288};
    public static final byte[][] var_1a9d = new byte[][]{var_193a, var_1995, var_1a16};

    public static byte prizrakDialogState = 0;
    public static final byte[] var_1b1c = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] var_1b50 = new short[]{(short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297, (short) 298, (short) 299, (short) 300};
    public static final byte[][] var_1b69 = new byte[][]{var_1b1c};

    public static byte militaryDialogState = 0;
    public static final byte[] var_1bb3 = new byte[]{(byte) 1};
    public static final short[] var_1bde = new short[]{(short) 301, (short) 302, (short) 303};
    public static final byte[] var_1c02 = new byte[]{(byte) 1, (byte) 1};
    public static final short[] var_1c1e = new short[]{(short) 304, (short) 305, (short) 306, (short) 307, (short) 308};
    public static final byte[][] var_1c6a = new byte[][]{var_1bb3, var_1c02};

    //Используется ли оптический прицел в данный момент?
    public static boolean OpticalSight;
    //Максимальное здоровье главгероя
    public static short playerMaxHealth = 100;
    //Максимальный вес
    public static short playerMaxWeight = 350;
    //Текущее здоровье
    public static short ActorCurrentHealth = playerMaxHealth;
    //Текущая сумма денег
    public static short CurrentActorMoney = 0;
    //Текущий вес
    public static short ActorCurrentWeight;
    //Аммуниция
    public static short[] MassiveActorAmmo = new short[4];
    //Точность главгероя
    public static byte playerAccuracy = 50;//50
    //Защита от пуль
    public static byte playerBulletProtection;
    //Защита от радиации
    public static byte playerRadiationResistance;
    //Защита от аномалий
    public static byte playerAnomalyResistance;
    //Текущий опыт главгероя
    public static short CurrentActorExpirience;
    //Текущий уровень главгероя
    public static short currentPlayerLevel;
    //Набор значений уровней характеристик
    public static byte[] CurrentActorCharLevelsMassive = new byte[3];

    //Использован ли антирад?
    public static boolean IsAntiradIsUsed;
    //Продолжительность действия
    public static int AntiradDuration = 20000;

    private static boolean var_1fe2;
    private static boolean var_2040;
    public static boolean var_2075;
    public static short var_20d6 = 1000;
    public static int var_2108;
    public static boolean var_215f;
    public static short var_21a7 = 1000;
    public static int var_21d2;

    public static boolean var_2204;
    public static int MoneyTaken;
    public static boolean var_228e;
    public static boolean var_22f1;
    public static int[] var_2314;
    public static byte[] var_2326;
    public static byte var_2367;

    //  Диалоговая система
    public static short currentDialogId;
    public static byte var_23b6;
    public static short[] phracesIdArray = null;
    //Иерархия ответов
    public static byte[] dialogStructure = null;

    public static byte givenAnswersCount;
    public static byte currentNpcPhrase;
    public static byte selectedAnswer;
    public static byte NumberOfAnswers;
    public static boolean var_2544;
    public static boolean dialogCompleted;
    public static boolean var_25bf;
    public static boolean ItemWalkieTalkieInInventaryBool;
    public static boolean endingCutscene;
    public static short[] stashItems;
    public static byte openedActivableObjId;
    public static short[] traderItems;

    private static void resetActorQuestAndDialogProgress() {
        batyaDialogState = 0;
        rustyDialogState = 0; //2- первое задание Ржавого, 4-второе, 6-рация
        trusDialogState = 0;
        svistunDialogState = 0;
        botanikDialogState = 0;
        zaborDialogState = 0;
        galoshQuestState = 0; //1 при взятой и выполненной миссии Галоша
        shlangDialogState = 0;
        militaryDialogState = 0; //1 при взятой миссии полученной по рации
        //
        prizrakDialogState = 0;
        //
        kaynazovskiDialogState = 0;
        krotDialogState = 0;
        haryaDialogState = 0;
        //
        commanderDialogState = 0;
        manikovskiDialogState = 0;
        //
        gutalinDialogState = 0;
        belomorDialogState = 0;
        koboldDialogState = 1;
    }

    public static void setAllActorStatsToDefault() {
        
        for (short var0 = 0; var0 < 100; ++var0) //Опустошить инвентарь
        {
            inventoryItems[var0] = -1;
            if (var0 < equipmentSlots.length) {
                equipmentSlots[var0] = -1;
            }
        }

        resetActorQuestAndDialogProgress();
        MassiveActorAmmo[0] = 12;

        for (byte var1 = 1; var1 < MassiveActorAmmo.length; ++var1) //Опустошить аммуницию основного оружия
        {
            MassiveActorAmmo[var1] = 0;
        }

        ItemWalkieTalkieInInventaryBool = false;
        currentPlayerLevel = 1;
        CurrentActorExpirience = 0;
        CurrentActorCharLevelsMassive = new byte[3];
        playerMaxHealth = 100;
        ActorCurrentHealth = playerMaxHealth;
        playerMaxWeight = 350;
        ActorCurrentWeight = 0;
        CurrentActorMoney = 0;
        playerAccuracy = 50;
        playerAnomalyResistance = 0;
        playerRadiationResistance = 0;
        playerBulletProtection = 0;
        var_2544 = false;
        var_25bf = false;
        /*//Для тестов
        playerMaxWeight = 350;
        //*/
        inventoryItems = new short[100];
        equipmentSlots = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        locationExclusiveItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        CurrentNumberOfInventaryItems = 0;

        /*//add medkits
        addItemToInventary((short) 101);
        addItemToInventary((short) 102);
        addItemToInventary((short) 103);
        //add ammo
        addItemToInventary((short) 107);
        addItemToInventary((short) 108);
        addItemToInventary((short) 109);
        //add armor
        addItemToInventary((short) 110);
        addItemToInventary((short) 111);
        addItemToInventary((short) 112);
        addItemToInventary((short) 113);
        addItemToInventary((short) 114);
        addItemToInventary((short) 115);*/
        //add weapons
        addItemToInventary((short) 116); //В оригинале выдаётся только пистолет
        /*addItemToInventary((short) 117);
        addItemToInventary((short) 118);
        addItemToInventary((short) 119);
        //add artifacts
        addItemToInventary((short) 120);
        addItemToInventary((short) 121);
        addItemToInventary((short) 122);
        addItemToInventary((short) 123);
        addItemToInventary((short) 124);
        addItemToInventary((short) 125);
        addItemToInventary((short) 126);*/
        
        useItem((short) 116);
        //ModChanges
        //ModChanges.NewStartItems();
    }

    public static void sub_8b() {
        PlayerHUD.doorLocked = false;
    }

    public static void levelUpPlayerStat(byte stat_number) {
        if (CurrentActorCharLevelsMassive[0] + CurrentActorCharLevelsMassive[1] + CurrentActorCharLevelsMassive[2] < currentPlayerLevel) {//Если сумма всех стат персонажа меньше текущего уровня
            switch (stat_number) {
                case 0:
                    playerAccuracy = (byte) (playerAccuracy + playerAccuracy * 10 / 100); //Увеличить точность
                    break;
                case 1:
                    playerMaxHealth = (short) (playerMaxHealth + 10); //Увеличить здоровье
                    break;
                case 2:
                    playerMaxWeight = (short) (playerMaxWeight + 50); //Увеличить силу
            }
            ++CurrentActorCharLevelsMassive[stat_number]; //Поднять уровень выбранной характеристики
        }
    }

    public static short getItemWeight(short item) //Возвращает вес выбранного предмета
    {
        switch (item) {
            case 101: //Полевая аптечка
                return (short) 5;
            case 102: //Военная аптечка
                return (short) 10;
            case 103:
                return (short) 3;
            case 104:
                return (short) 4;
            case 105:
                return (short) 12;
            case 106:
                return (short) 3;
            case 107:
                return (short) 3;
            case 108:
                return (short) 4;
            case 109:
                return (short) 3;
            case 110:
                return (short) 30;
            case 111:
                return (short) 45;
            case 112:
                return (short) 70;
            case 113:
                return (short) 50;
            case 114:
                return (short) 110;
            case 115:
                return (short) 80;
            case 116:
                return (short) 10;
            case 117:
                return (short) 36;
            case 118:
                return (short) 25;
            case 119:
                return (short) 50;
            case 120:
                return (short) 5;
            case 121:
                return (short) 5;
            case 122:
                return (short) 5;
            case 123:
                return (short) 5;
            case 124:
                return (short) 0;
            case 125:
                return (short) 5;
            case 126:
                return (short) 5;
            case 300:
                return (short) 30;
            default:
                return (short) 0;
        }
    }

    public static short getItemPrice(short i) {
        switch (i) {
            case 101:
                return (short) 100;
            case 102:
                return (short) 250;
            case 103:
                return (short) 250;
            case 104:
                return (short) 50;
            case 105:
                return (short) 350;
            case 106:
                return (short) 0;
            case 107:
                return (short) 60;
            case 108:
                return (short) 50;
            case 109:
                return (short) 140;
            case 110:
                return armorLeatherJacketStats[3];
            case 111:
                return armorMailJacketStats[3];
            case 112:
                return armorMercSuitStats[3];
            case 113:
                return armorSevaSuitStats[3];
            case 114:
                return armorStalkerSuitStats[3];
            case 115:
                return armorSPP9mStats[3];
            case 116:
                return (short) 0;
            case 117:
                return (short) 1500;
            case 118:
                return (short) 1000;
            case 119:
                return (short) 2000;
            case 120:
                return (short) 400;
            case 121:
                return (short) 500;
            case 122:
                return (short) 750;
            case 123:
                return (short) 450;
            case 124:
                return (short) 600;
            case 125:
                return (short) 800;
            case 126:
                return (short) 500;
            case 300:
                return (short) 1000;
            default:
                return (short) 0;
        }
    }

    private static void NPCShootPlayer() //Урон по игроку огнестрелом
    {                                    //с определённым шансом
        if (RenderEngine.var_1db5 && RenderEngine.var_1eae != -1 && !RenderEngine.showFinalDialog && !var_22f1) {
            byte damage = 0;
            boolean hit_bool = false;
            byte var2;
            switch (var2 = Bot.enemyDodgeLevels[RenderEngine.var_1eae]) {
                case 0:
                    damage = 3;
                    hit_bool = MathUtils.getRandomNumber(100) <= 60;
                    break;
                case 1:
                    damage = 7;
                    hit_bool = MathUtils.getRandomNumber(100) <= 40;
                    break;
                case 2:
                    damage = 9;
                    hit_bool = MathUtils.getRandomNumber(100) <= 50;
                    break;
                case 3:
                    damage = 15;
                    hit_bool = MathUtils.getRandomNumber(100) <= 60;
            }

            if (hit_bool) {
                CauseDamageToActor(damage - damage * playerBulletProtection / 100);
            }
        }

    }

    public static void checkPlayerAndSaveGame()
    {
        playerControlCheck(); //Проверить нажатия всех клавиш
        if (PlayerHUD.IsTransitWasCompleted) //Если был выполнен переход по локации
        {
            PlayerHUD.IsTransitWasCompleted = false; //Включение ожидания перехода
            ResourseManager.WriteSavedGame(); //Провести сохранение
            RenderEngine.setDialogWindowState((short) -2);
        }

        if (RenderEngine.showIntro && PlayerHUD.introRolledFully) {
            PlayerHUD.introRolledFully = false;
            RenderEngine.showIntro = false;
            RenderEngine.setDialogWindowState((short) 2);
        }

        if (var_215f && RenderEngine.renderTimeOnly3D - (long) var_21d2 >= (long) (var_21a7 / 2) && !var_2040) {
            SoundAndVibro.playSound(1);
            var_2040 = true;
        }

        if (var_2075 && RenderEngine.renderTimeOnly3D - (long) var_2108 >= (long) (var_20d6 / 2) && !var_2040) {
            SoundAndVibro.playSound(2);
            var_2040 = true;
        }

        if (RenderEngine.currentGameState == 2) {
            ActorLevelUpIfItNeed();
            var_1fe2 = RenderEngine.sub_acc();
            SoundAndVibro.stopTooLongVibro();
            NPCShootPlayer();
        }
    }

    public static void takeGunInHands(byte var0) {
        if (!var_215f) {
            var_2075 = true;
            var_2108 = (int) RenderEngine.renderTimeOnly3D;
            EncasedWeapon = playerActiveWeapon;
            playerActiveWeapon = var0;
            var_2040 = false;
            if (OpticalSight) {
                OpticalSight = false;
                RenderEngine.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
            }
        }
    }

    private static void TakeUpGun() {
        short var0;
        if (playerActiveWeapon == 0) //Если в руках ничего
        {
            var0 = equipmentSlots[0]; //Ячейке присваивается значение слота основного оружия
        } else {
            var0 = 116; //Если ничего нет - присвоить значение пистолета
        }

        useItem(var0);
        OpticalSight = false;
        RenderEngine.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
    }

    private static void ReloadThisGun(byte var0) {
        if (var0 != 0) {
            if (!var_2075) {
                OpticalSight = false;
                RenderEngine.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
                switch (var0) {
                    case 1:
                        if (!searchItem((short) 107)) {
                            return;
                        }

                        useItem((short) 107);
                        break;
                    case 2:
                        if (!searchItem((short) 108)) {
                            return;
                        }

                        useItem((short) 108);
                        break;
                    case 3:
                        if (!searchItem((short) 109)) {
                            return;
                        }

                        useItem((short) 109);
                }

                var_2040 = false;
                var_215f = true;
                var_21d2 = (int) RenderEngine.renderTimeOnly3D;
            }
        }
    }

    private static boolean tryDropOrPickupItem(short item, boolean b) {//true для попытки поднять
        short temporaryWeight = ActorCurrentWeight;
        short coefWeight = -1;

        if (b == true) {
            coefWeight = 1;
        }

        switch (item) {
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            default:
                break;
            case 101:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
                break;
            case 102:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 10 * coefWeight);
                break;
            case 103:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 3 * coefWeight);
                break;
            case 104:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 4 * coefWeight);
                break;
            case 105:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 12 * coefWeight);
                break;
            case 106:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 3 * coefWeight);
                break;
            case 107:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 3 * coefWeight);
                break;
            case 108:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 4 * coefWeight);
                break;
            case 109:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 3 * coefWeight);
                break;
            case 110:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 30 * coefWeight);
                break;
            case 111:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 45 * coefWeight);
                break;
            case 112:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 70 * coefWeight);
                break;
            case 113:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 50 * coefWeight);
                break;
            case 114:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 110 * coefWeight);
                break;
            case 115:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 80 * coefWeight);
                break;
            case 116:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 10 * coefWeight);
                break;
            case 117:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 36 * coefWeight);
                break;
            case 118:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 25 * coefWeight);
                break;
            case 119:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 50 * coefWeight);
                break;
            case 120:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
                break;
            case 121:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
                break;
            case 122:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
                break;
            case 123:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
                break;
            case 124:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 0 * coefWeight);
                break;
            case 125:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
                break;
            case 126:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 5 * coefWeight);
            case 300:
                ActorCurrentWeight = (short) (ActorCurrentWeight + 30 * coefWeight);
        }

        if (ActorCurrentWeight > playerMaxWeight) //Если вес предмета превышает максимально возможный вес
        {
            ActorCurrentWeight = temporaryWeight; //Вернуть всё как было и выкинуть предмет
            return false;
        } else {
            return true; //Иначе взять его
        }
    }

    private static boolean tryTrade(short item, boolean b) {//true для покупки
        short temporaryMoney = CurrentActorMoney;
        short t = -100;//50 продажа, -100 покупка

        if (b == false) {
            t = 50;
        }

        switch (item) {
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            default:
                break;
            case 101:
                CurrentActorMoney = (short) (CurrentActorMoney + 100 * t / 100);
                break;
            case 102:
                CurrentActorMoney = (short) (CurrentActorMoney + 250 * t / 100);
                break;
            case 103:
                CurrentActorMoney = (short) (CurrentActorMoney + 250 * t / 100);
                break;
            case 104:
                CurrentActorMoney = (short) (CurrentActorMoney + 50 * t / 100);
                break;
            case 105:
                CurrentActorMoney = (short) (CurrentActorMoney + 350 * t / 100);
                break;
            case 106:
                CurrentActorMoney = (short) (CurrentActorMoney + 0 * t / 100);
                break;
            case 107:
                CurrentActorMoney = (short) (CurrentActorMoney + 60 * t / 100);
                break;
            case 108:
                CurrentActorMoney = (short) (CurrentActorMoney + 50 * t / 100);
                break;
            case 109:
                CurrentActorMoney = (short) (CurrentActorMoney + 140 * t / 100);
                break;
            case 110:
                CurrentActorMoney = (short) (CurrentActorMoney + armorLeatherJacketStats[3] * t / 100);
                break;
            case 111:
                CurrentActorMoney = (short) (CurrentActorMoney + armorMailJacketStats[3] * t / 100);
                break;
            case 112:
                CurrentActorMoney = (short) (CurrentActorMoney + armorMercSuitStats[3] * t / 100);
                break;
            case 113:
                CurrentActorMoney = (short) (CurrentActorMoney + armorSevaSuitStats[3] * t / 100);
                break;
            case 114:
                CurrentActorMoney = (short) (CurrentActorMoney + armorStalkerSuitStats[3] * t / 100);
                break;
            case 115:
                CurrentActorMoney = (short) (CurrentActorMoney + armorSPP9mStats[3] * t / 100);
                break;
            case 116:
                CurrentActorMoney = (short) (CurrentActorMoney + 0 * t / 100);
                break;
            case 117:
                CurrentActorMoney = (short) (CurrentActorMoney + 1500 * t / 100);
                break;
            case 118:
                CurrentActorMoney = (short) (CurrentActorMoney + 1000 * t / 100);
                break;
            case 119:
                CurrentActorMoney = (short) (CurrentActorMoney + 2000 * t / 100);
                break;
            case 120:
                CurrentActorMoney = (short) (CurrentActorMoney + 400 * t / 100);
                break;
            case 121:
                CurrentActorMoney = (short) (CurrentActorMoney + 500 * t / 100);
                break;
            case 122:
                CurrentActorMoney = (short) (CurrentActorMoney + 750 * t / 100);
                break;
            case 123:
                CurrentActorMoney = (short) (CurrentActorMoney + 450 * t / 100);
                break;
            case 124:
                CurrentActorMoney = (short) (CurrentActorMoney + 600 * t / 100);
                break;
            case 125:
                CurrentActorMoney = (short) (CurrentActorMoney + 800 * t / 100);
                break;
            case 126:
                CurrentActorMoney = (short) (CurrentActorMoney + 500 * t / 100);
            case 300:
                CurrentActorMoney = (short) (CurrentActorMoney + 1000 * t / 100);
        }

        if (t < 0) //Если мы что-то покупаем
        {
            if (CurrentActorMoney < 0) //Но денег не хватает
            {
                CurrentActorMoney = temporaryMoney; //Отменить сделку
                return false;
            } else //Если денег хватает
            {
                return true; //Провернуть сделку
            }
        } else {
            return true; //"Спасибо за покупку"
        }
    }

    //Убрать из слотов экипировки
    private static void deleteFromEquipmentSlot(short item) {
        for (byte j = 0; j < 7; ++j) {
            if (equipmentSlots[j] == item) {
                equipmentSlots[j] = -1;
            }
        }

    }

    //Добавить предмет в слоты
    private static void addToEquipmentSlot(short item) {
        //Экипировка брони, основного и доп. оружия
        if (item <= 119) {
            switch (item) {
                //Броня
                case 110: //Кожаная куртка
                case 111: //Кольчужная куртка
                case 112: //Комбез наёмника
                case 113: //СЕВА
                case 114: //Комбинезон сталкера
                case 115: //СПП-9м.
                    equipmentSlots[2] = item;
                    break;
                //Доп.оружие (пистолет)
                case 116: 
                    equipmentSlots[1] = item;
                    break;
                //Основное оружие
                case 117: //Калаш
                case 118: //Гроза
                case 119: //Энфилд
                    equipmentSlots[0] = item;
            }
        } 
        //Экипировка артефактов
        else 
         if (item >= 120 && item <= 130) {
                for (byte a = 3; a < 7; ++a) {
                    //Поставить в пустой слот
                    if (equipmentSlots[a] == -1) {
                        equipmentSlots[a] = item;
                        return;
                    }
                }
            }
    }

    public static boolean isItemEquipped(short item) {//Надет ли предмет на персонажа
        return RenderEngine.isItemInList(item, equipmentSlots);
    }

    public static void addItemToInventary(short item) {
        if (item != 0 && item != 106) //Если предмет не равен нулю или если это не магазин для форта
        {
            if (item == 200) {
                ItemWalkieTalkieInInventaryBool = true;
            } else if (item >= 127 && item <= 129) {
                for (byte var1 = 0; var1 < locationExclusiveItems.length; ++var1) {
                    if (locationExclusiveItems[var1] == -1) {
                        locationExclusiveItems[var1] = item;
                        return;
                    }
                }
            } else if (tryDropOrPickupItem(item, true)) //Если вещь помещается в рюкзаке
            {
                if (item >= 117 && item <= 119) {
                    switch (item) {
                        case 117:
                            addItemToInventary((short) 107);//магазин калаша
                            useItem((short) 107);
                            break;
                        case 118:
                            addItemToInventary((short) 108);//магазин грозы
                            useItem((short) 108);
                            break;
                        case 119:
                            addItemToInventary((short) 109);//магазин энфилда
                            useItem((short) 109);
                    }
                }

                inventoryItems[CurrentNumberOfInventaryItems] = item;
                ++CurrentNumberOfInventaryItems;
                if (item >= 140 && item <= 148 || item == 100) //Если подняты деньги - сразу использовать
                {
                    useItem(item);
                }
            }
        }
    }

    private static void setPlayerResistances(short bullet, short rad, short anom, int multip) {
        playerBulletProtection = (byte) Math.max(0, playerBulletProtection + bullet * multip);
        playerRadiationResistance = (byte) Math.max(0, playerRadiationResistance + rad * multip);
        playerAnomalyResistance = (byte) Math.max(0, playerAnomalyResistance + anom * multip);
    }

    public static void useItem(short i) {
        //bugfix коэффициенты брони неправильно считаются
        //Если надевается броня
        if(i>109&&i<116)
        {
            //Если у нас уже экипирована броня
            if(equipmentSlots[2]!=-1)
            {
                unequipItem(equipmentSlots[2]);
            }
        }
        //bugfix коэффициенты брони неправильно считаются
        addToEquipmentSlot(i);
        switch (i) {
            case 100:
                if (RenderEngine.currentLocation == 10) {
                    ItemWalkieTalkieInInventaryBool = true;
                    dropItem(i);
                } else {
                    var_2204 = true;
                    dropItem(i);
                }
                break;
            case 101: //Использование полевой аптечки
                ActorCurrentHealth = (short) Math.min(ActorCurrentHealth + 25, playerMaxHealth);
                dropItem(i);
                break;
            case 102: //Использование военной аптечки
                ActorCurrentHealth = (short) Math.min(ActorCurrentHealth + 75, playerMaxHealth);
                dropItem(i);
                break;
            case 103:
                IsAntiradIsUsed = true;
                dropItem(i);
            case 104:
            case 105:
            case 106:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            default:
                break;
            case 107: //Смена рожка у АК
                if (searchItem((short) 117)) {
                    MassiveActorAmmo[1] = 30;
                    dropItem(i);
                }
                break;
            case 108: //Смена рожка у Грозы
                if (searchItem((short) 118)) {
                    MassiveActorAmmo[2] = 20;
                    dropItem(i);
                }
                break;
            case 109: //Смена рожка у Энфилда
                if (searchItem((short) 119)) {
                    MassiveActorAmmo[3] = 25;
                    dropItem(i);
                }
                break;
            case 110:
                changePlayerStatsByItem(i, 1);
                break;
            case 111:
                changePlayerStatsByItem(i, 1);
                break;
            case 112:
                changePlayerStatsByItem(i, 1);
                break;
            case 113:
                changePlayerStatsByItem(i, 1);
                break;
            case 114:
                changePlayerStatsByItem(i, 1);
                break;
            case 115:
                changePlayerStatsByItem(i, 1);
                break;
            case 116: //Экипировка пистолета форт
                takeGunInHands((byte) (i - 116));
                break;
            case 117: //Экипировка АК
                takeGunInHands((byte) (i - 116));
                if (searchItem((short) 107)) //Использовать рожок, если есть
                {
                    useItem((short) 107);
                }
                break;
            case 118: //Экипировка Грозы
                takeGunInHands((byte) (i - 116));
                if (searchItem((short) 108)) {
                    useItem((short) 108);
                }
                break;
            case 119: //Экипировка Энфилда
                takeGunInHands((byte) (i - 116)); //Взять это оружие в руки
                if (searchItem((short) 109)) {
                    useItem((short) 109);
                }
                break;
            case 120:
                changePlayerStatsByItem(i, 1);
                break;
            case 121:
                changePlayerStatsByItem(i, 1);
                break;
            case 122:
                changePlayerStatsByItem(i, 1);
                break;
            case 123:
                changePlayerStatsByItem(i, 1);
                break;
            case 124:
                changePlayerStatsByItem(i, 1);
                break;
            case 125:
                changePlayerStatsByItem(i, 1);
                break;
            case 126:
                changePlayerStatsByItem(i, 1);
                break;
            case 140:
                CurrentActorMoney = (short) (CurrentActorMoney + 100);
                MoneyTaken = 100;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 141:
                MoneyTaken = 250;
                CurrentActorMoney = (short) (CurrentActorMoney + 250);
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 142:
                MoneyTaken = 500;
                CurrentActorMoney = (short) (CurrentActorMoney + 500);
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 143:
                CurrentActorMoney = (short) (CurrentActorMoney + 1000);
                MoneyTaken = 1000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 144:
                CurrentActorMoney = (short) (CurrentActorMoney + 2000);
                MoneyTaken = 2000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 145:
                CurrentActorMoney = (short) (CurrentActorMoney + 5000);
                MoneyTaken = 5000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 146:
                CurrentActorMoney = (short) (CurrentActorMoney + 25);
                MoneyTaken = 25;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 147:
                CurrentActorMoney = (short) (CurrentActorMoney + 50);
                MoneyTaken = 50;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 148:
                CurrentActorMoney = (short) (CurrentActorMoney + 75);
                MoneyTaken = 75;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 300: //засланный казачок
                CurrentActorMoney = (short) (CurrentActorMoney + 10000);
                MoneyTaken = 10000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 200: //Рация
                if (ItemWalkieTalkieInInventaryBool) {
                    startDialog((short) 21, (byte) 0);
                }
        }

        ActorAllCharControll();
    }

    //Экипировка брони и артефактов
    private static void changePlayerStatsByItem(short item, int multip) {
        switch (item) {
            //  БРОНЯ
            case 110:
                setPlayerResistances(armorLeatherJacketStats[0], armorLeatherJacketStats[1], armorLeatherJacketStats[2], multip);
                return;
            case 111:
                setPlayerResistances(armorMailJacketStats[0], armorMailJacketStats[1], armorMailJacketStats[2], multip);
                return;
            case 112:
                setPlayerResistances(armorMercSuitStats[0], armorMercSuitStats[1], armorMercSuitStats[2], multip);
                return;
            case 113:
                setPlayerResistances(armorSevaSuitStats[0], armorSevaSuitStats[1], armorSevaSuitStats[2], multip);
                return;
            case 114:
                setPlayerResistances(armorStalkerSuitStats[0], armorStalkerSuitStats[1], armorStalkerSuitStats[2], multip);
                return;
            case 115:
                setPlayerResistances(armorSPP9mStats[0], armorSPP9mStats[1], armorSPP9mStats[2], multip);
                return;
            //  АРТЕФАКТЫ
            //Медуза
            case 120:
                setPlayerResistances((short) 5, (short) -10, (short) 0, multip);
                return;
            case 121:
                setPlayerResistances((short) 10, (short) 0, (short) 0, multip);
                playerMaxWeight = (short) (playerMaxWeight - 10 * multip * 10);
                return;
            case 122:
                setPlayerResistances((short) -5, (short) 0, (short) 0, multip);
                playerMaxWeight = (short) (playerMaxWeight + 15 * multip * 10);
                return;
            case 123:
                playerMaxHealth = (short) (playerMaxHealth + 50 * multip);
                playerAccuracy = (byte) (playerAccuracy - 10 * multip);
                return;
            case 124:
                playerAccuracy = (byte) (playerAccuracy + 6 * multip);
                playerMaxWeight = (short) (playerMaxWeight - 15 * multip * 10);
                setPlayerResistances((short) 0, (short) -5, (short) 0, multip);
                return;
            case 125:
                setPlayerResistances((short) 0, (short) 10, (short) -60, multip);
                playerMaxWeight = (short) (playerMaxWeight + 10 * multip * 10);
                playerMaxHealth = (short) (playerMaxHealth + 20 * multip);
                return;
            case 126:
                setPlayerResistances((short) 0, (short) 0, (short) 15, multip);
            case 116:
            case 117:
            case 118:
            case 119:
            default:
        }
    }

    public static void unequipItem(short item) {
        if (item != 116) {
            deleteFromEquipmentSlot(item);
            switch (item) {
                case 103:
                    IsAntiradIsUsed = false;
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 116:
                default:
                    break;
                case 110:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 111:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 112:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 113:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 114:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 115:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 117:
                case 118:
                case 119:
                    useItem((short) 116);
                    break;
                case 120:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 121:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 122:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 123:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 124:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 125:
                    changePlayerStatsByItem(item, -1);
                    break;
                case 126:
                    changePlayerStatsByItem(item, -1);
            }

            ActorAllCharControll();
        }
    }

    public static void buyItem(short item) {
        if (tryTrade(item, true)) {
            addItemToInventary(item);
        }
    }

    public static void sellItem(short item) {
        tryTrade(item, false);
        if (isItemEquipped(item)) {
            unequipItem(item);
        }

        dropItem(item);
    }

    private static void ActorAllCharControll() {
        ActorCurrentHealth = (short) Math.min(ActorCurrentHealth, playerMaxHealth);
        playerAccuracy = (byte) Math.max(0, playerAccuracy);
        playerMaxWeight = (short) Math.max(0, playerMaxWeight);
        playerBulletProtection = (byte) Math.max(0, playerBulletProtection);
        playerAnomalyResistance = (byte) Math.max(0, playerAnomalyResistance);
        playerRadiationResistance = (byte) Math.max(0, playerRadiationResistance);
    }

    public static void dropItem(short item) {
        if (item != 116) //Любой предмет кроме пистолета форт
        {
            short var1 = 0;

            short var2; //Номер ячейки инвентаря
            for (var2 = 1; var2 < CurrentNumberOfInventaryItems; ++var2) //Определение номера ячейки с нужным предметом
            {
                if (inventoryItems[var2] == item) //Если предмет найден в массиве ячеек инвентаря
                {
                    var1 = var2;
                    break;
                }
            }

            if (var1 > 0) {
                for (var2 = var1; var2 < CurrentNumberOfInventaryItems - 1; ++var2) { //Присвоить ячейке значение следующей ячейки инвентаря
                    inventoryItems[var2] = inventoryItems[var2 + 1];
                    inventoryItems[var2 + 1] = -1;
                } //Сделать следующую ячейку инвентаря пустой

                --CurrentNumberOfInventaryItems; //Уменьшить число предметов в инвентаре на единицу
                tryDropOrPickupItem(item, false); //Уменьшить вес инвентаря на вес этого предмета
            }
        }
    }

    private static boolean searchItem(short var0) //Поиск предмета в инвентаре
    {
        for (int var1 = 0; var1 < CurrentNumberOfInventaryItems; ++var1) {
            if (var0 == inventoryItems[var1]) {
                return true; //Вернуть истину если найден
            }
        }
        return false;
    }

    private static void sub_62f(byte var0) {
        if (RenderEngine.var_1d93 == -1) {
            RenderEngine.sub_c4f(var0);
        }
    }

    public static void giveItemsForKillingAll() {
        for (int i = 0; i < RenderEngine.itemsForKillingAllCount[RenderEngine.currentRoom]; i++) {
            short itemId = RenderEngine.itemsForKillingAll[RenderEngine.currentRoom][i];
            if (itemId != 0) {
                addItemToInventary(itemId);
                RenderEngine.itemsForKillingAll[RenderEngine.currentRoom][i] = 0;
            }
        }

    }

    private static void GetExperienceForKill() {
        RenderEngine.botKilled[RenderEngine.currentRoom][RenderEngine.botIdUndercursor] = true;
        ++RenderEngine.var_1fb1;
        RenderEngine.sub_ab1(RenderEngine.botIdUndercursor);
        RenderEngine.sub_daf();
        switch (Bot.getBotType(RenderEngine.botIdUndercursor)) {
            case 0:
                ++CurrentActorExpirience;
                break;
            case 1:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 3);
                break;
            case 2:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 0);
                break;
            case 3:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 0);
                break;
            case 4:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 5);
                break;
            case 5:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 7);
                break;
            case 6:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 10);
                break;
            case 7:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 8);
                break;
            case 8:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 10);
                break;
            case 9:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 12);
                break;
            case 10:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 0);
                break;
            case 11:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 14);
                break;
            case 12:
                CurrentActorExpirience = (short) (CurrentActorExpirience + 16);
        }

        if (RenderEngine.botsCount[RenderEngine.currentRoom] - RenderEngine.var_1fb1 == 0) {
            giveItemsForKillingAll();
        }

    }

    private static void ActorLevelUpIfItNeed() {
        if (CurrentActorExpirience >= 150 && CurrentActorExpirience < 210) {
            currentPlayerLevel = (short) Math.max(2, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 210 && CurrentActorExpirience < 290) {
            currentPlayerLevel = (short) Math.max(3, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 290 && CurrentActorExpirience < 390) {
            currentPlayerLevel = (short) Math.max(4, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 390 && CurrentActorExpirience < 530) {
            currentPlayerLevel = (short) Math.max(5, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 530 && CurrentActorExpirience < 670) {
            currentPlayerLevel = (short) Math.max(6, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 670 && CurrentActorExpirience < 810) {
            currentPlayerLevel = (short) Math.max(7, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 810 && CurrentActorExpirience < 1010) {
            currentPlayerLevel = (short) Math.max(8, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 1010 && CurrentActorExpirience < 1230) {
            currentPlayerLevel = (short) Math.max(9, currentPlayerLevel);
        } else if (CurrentActorExpirience >= 1230) {
            currentPlayerLevel = (short) Math.min(10, currentPlayerLevel);
        }
    }

    private static void shoot(byte gun) {
        if (!var_2075 && !var_215f) {
            if (MassiveActorAmmo[gun] > 0 || gun == 0) {
                var_228e = true;
                RenderEngine.var_1c84 = (int) RenderEngine.renderTimeOnly3D;
                RenderEngine.var_1cda = true;
                switch (gun) {
                    case 0: //Если в руках форт
                        playerAccuracy = 60; //Точность персонажа 60%
                        --MassiveActorAmmo[0]; //Убавить боезапас на единицу
                        if (MassiveActorAmmo[0] < 1) //Если патронов в магазине нет
                        {
                            MassiveActorAmmo[0] = 12; //Сразу перезарядить
                        }
                        break;
                    case 1:
                        playerAccuracy = 40;
                        --MassiveActorAmmo[1];
                        break;
                    case 2:
                        playerAccuracy = 50;
                        --MassiveActorAmmo[2];
                        break;
                    case 3:
                        playerAccuracy = 60;
                        --MassiveActorAmmo[3];
                }

                if (var_1fe2 && MathUtils.getRandomNumber(100) <= playerAccuracy) //Если ты всё-таки попал 
                {
                    GetExperienceForKill();
                }

            }
        }
    }

    public static void CauseDamageToActor(int Hitpoints) //Нанести повреждение персонажу
    {
        var_22f1 = true;
        PlayerHUD.playerDamaged = true;
        PlayerHUD.timeToDisplayDamageIndicator = (int) RenderEngine.renderTimeOnly3D + 500;
        ActorCurrentHealth = (short) (ActorCurrentHealth - Hitpoints);
        SoundAndVibro.vibrate(200);
    }

    public static void sub_72f() {
        RenderEngine.setDialogWindowState((short) 5);
        GoToNextLocation();
    }

    private static void playerControlCheck() {
        if (RenderEngine.currentGameState != 1 && RenderEngine.currentGameState != 4 && RenderEngine.currentGameState != 11 && !endingCutscene && RenderEngine.currentGameState != 13 && RenderEngine.currentGameState != 0 && RenderEngine.currentGameState != -2) {
            switch (RenderEngine.currentGameState) {
                case 2:
                    if (RenderEngine.gamePaused) {
                        return;
                    }

                    Keys.leftAccelerate = System.currentTimeMillis() - Keys.leftOr4PressTime >= 200L && Keys.leftOr4PressTime != -1L;
                    Keys.rightAccelerate = System.currentTimeMillis() - Keys.righOr6PressTime >= 200L && Keys.righOr6PressTime != -1L;
                    Keys.downAccelerate = System.currentTimeMillis() - Keys.downOr8PressTime >= 200L && Keys.downOr8PressTime != -1L;
                    Keys.upAccelerate = System.currentTimeMillis() - Keys.upOr2PressTime >= 200L && Keys.upOr2PressTime != -1L;
                    if (Keys.fire || Keys.num5) {
                        Keys.fire = false;
                        Keys.num5 = false;
                        if (RenderEngine.sub_a33()) {
                            sub_62f(RenderEngine.var_17f2);
                        } else if (!RenderEngine.sub_a69()) {
                            shoot(playerActiveWeapon);
                        } else {
                            InteractionWith(RenderEngine.TypeOfInteractWithObjectAhead);
                        }
                    }

                    if (Keys.left || Keys.num4) {
                        RenderEngine.TurnLeftTheCamera();
                    }

                    if (Keys.right || Keys.num6) {
                        RenderEngine.TurnRightTheCamera();
                    }

                    if (Keys.up || Keys.num2) {
                        RenderEngine.RaiseTheCamera();
                    }

                    if (Keys.down || Keys.num8) {
                        RenderEngine.LowerTheCamera();
                    }

                    if (Keys.num1) {
                        ReloadThisGun(playerActiveWeapon);
                        Keys.num1 = false;
                    }

                    if (Keys.pound) {
                        Keys.pound = false;
                        if (playerActiveWeapon == 3) //Если в руках в текущий момент Энфилд
                        {
                            OpticalSight = !OpticalSight; //поменять значение активности
                            if (OpticalSight) {
                                RenderEngine.camera.setPerspective(30.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
                            } else {
                                RenderEngine.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
                            }
                        }
                    }

                    if (Keys.num3) //Если нажата клавиша 3
                    {
                        Keys.num3 = false;
                        if (searchItem((short) 101)) {
                            useItem((short) 101);
                        } else if (searchItem((short) 102)) {
                            useItem((short) 102);
                        }
                    }

                    if (Keys.num0) {
                        Keys.num0 = false;
                        sub_62f(RenderEngine.currentDoorId);
                    }

                    if (Keys.rightSoft) {
                        if (OpticalSight) {
                            OpticalSight = false;
                        }

                        Keys.rightSoft = false;
                        if (ItemWalkieTalkieInInventaryBool) {
                            useItem((short) 200);
                        } else {
                            if (var_2204) {
                                sub_72f();
                                return;
                            }

                            TakeUpGun();
                        }
                    }

                    if (Keys.leftSoft) {
                        Keys.leftSoft = false;
                        RenderEngine.gamePaused = true;
                        sub_7cd();
                        AllScreens.masterInventory = new MasterInventoryScreen();
                        Main.main.setScreen(AllScreens.pauseScreen, (byte) 2);
                        Main.main.repaint();
                    }

                    if (Keys.star) {
                        //тест активен
                        //ModChanges.test++;
                        //конец теста
                        Keys.star = false;
                    }
                case 3:
                case 6:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 13:
                default:
                    break;
                case 4:
                    if (Keys.leftSoft) {
                        Keys.leftSoft = false;
                    }

                    if (Keys.rightSoft) {
                        Keys.rightSoft = false;
                    }
                    break;
                case 5:
                    PDA_Keypad();
                    return;
                case 9:
                    dialogSelector();
                    return;
                case 14:
                    if (Keys.rightSoft) {
                        Keys.rightSoft = false;
                        ResourseManager.runGarbageCollector();
                        RenderEngine.setDialogWindowState((short) 2);//закрыть диалоговое окно
                        RenderEngine.showIntro = false;
                        PlayerHUD.introRolledFully = false;
                    }
            }

        }
    }

    private static void addMarkToPDA(byte var0) {
        var_2204 = true;
        RenderEngine.bool_massive_2th[var0] = true;
    }

    public static byte getLocationNameId(byte var0) {
        return RenderEngine.sub_165(var0, StoryLocationMassive);
    }

    private static void sub_7cd() {
        var_2314 = new int[13];
        var_2326 = new byte[13];
        var_2367 = 0;

        for (byte var1 = 1; var1 < 17; ++var1) {
            byte var0;
            if (sub_819(var1) && (var0 = getLocationNameId(var1)) != -1) {
                var_2314[var_2367] = (short) (var0 + 354);
                var_2326[var_2367] = var1;
                ++var_2367;
            }
        }

    }

    public static boolean sub_819(int var0) {
        return !RenderEngine.bool_massive_1st[var0] && (RenderEngine.bool_massive_3th[var0] || RenderEngine.bool_massive_2th[var0]) || RenderEngine.currentLocation == var0;
    }

    private static void GoToNextLocation() {
        PlayerHUD.textLinesPos = TextCreator.splitOnLines(getLocationNameId((byte) RenderEngine.currentLocation) + 354, PlayerHUD.TEXT_TARGET_WIDTH, 0);
        RenderEngine.nextLocation = RenderEngine.currentLocation;
        if (var_2204) {
            RenderEngine.bool_massive_3th[RenderEngine.currentLocation] = RenderEngine.currentLocation == 1 || RenderEngine.currentLocation == 6 || RenderEngine.currentLocation == 12;
            RenderEngine.bool_massive_1st[RenderEngine.currentLocation] = !RenderEngine.bool_massive_3th[RenderEngine.currentLocation];
            if (RenderEngine.currentLocation == StoryLocationMassive[0]) {
                rustyDialogState = 3;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 20);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[1]) {
                rustyDialogState = 5;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 90);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[2]) //Задание Галоши
            {
                galoshQuestState = 1;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 40);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[3]) {
                CurrentActorExpirience = (short) (CurrentActorExpirience + 110);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[5]) {
                haryaDialogState = 2;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 25);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[6]) {
                batyaDialogState = 2;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 155);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[7]) {
                CurrentActorExpirience = (short) (CurrentActorExpirience + 250);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[9]) {
                batyaDialogState = 4;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 32);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[10]) {
                belomorDialogState = 2;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 135);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[11]) {
                shlangDialogState = 2;
                CurrentActorExpirience = (short) (CurrentActorExpirience + 330);
            }

            if (RenderEngine.currentLocation == StoryLocationMassive[12]) {
                CurrentActorExpirience = (short) (CurrentActorExpirience + 375);
            }

        }
    }

    private static void PDA_Keypad() { //меню, вызываемое в PDA
        if (Keys.rightSoft || Keys.fire || Keys.num5) {//Если нажать "перейти"
            Keys.num5 = false;
            Keys.fire = false;
            Keys.rightSoft = false;
            if (PlayerHUD.IsTransitActive) {
                return;
            }

            if (RenderEngine.nextLocation == RenderEngine.currentLocation && !RenderEngine.var_20a4) { //Если локация перехода равна прежней, вернуться
                RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                RenderEngine.gamePaused = false;
                return;
            }

            if (!sub_819(RenderEngine.nextLocation)) {
                return;
            }

            PlayerHUD.ActivateTransitAnimation();
            RenderEngine.var_7ee = RenderEngine.currentRoom;
            RenderEngine.currentLocation = RenderEngine.nextLocation;
        }

        if (Keys.left || Keys.up || Keys.num2 || Keys.num4) { //Нажать клавишу влево или вверх
            Keys.num4 = false;
            Keys.num2 = false;
            Keys.up = false;
            Keys.left = false;
            if (sub_8da()) {
                while (true) {
                    ++RenderEngine.nextLocation;
                    if (RenderEngine.nextLocation > 16) {
                        RenderEngine.nextLocation = 1;
                    }

                    if (sub_819(RenderEngine.nextLocation)) {
                        break;
                    }

                    if (RenderEngine.nextLocation == 16) {
                        RenderEngine.nextLocation = 0;
                    }
                }
            }

            PlayerHUD.textLinesPos = TextCreator.splitOnLines(getLocationNameId((byte) RenderEngine.nextLocation) + 354, PlayerHUD.TEXT_TARGET_WIDTH, 0);
        }

        if (Keys.right || Keys.down || Keys.num8 || Keys.num6) { //нажать клавишу вправо или вниз
            Keys.num6 = false;
            Keys.num8 = false;
            Keys.down = false;
            Keys.right = false;
            boolean var0 = RenderEngine.currentLocation == 0;
            if (sub_8da()) {
                do {
                    --RenderEngine.nextLocation;
                    byte var1 = (byte) (var0 ? 0 : 1);
                    if (RenderEngine.nextLocation < var1) {
                        RenderEngine.nextLocation = 16;
                    }
                } while ((!var0 || RenderEngine.nextLocation != 0) && !sub_819(RenderEngine.nextLocation));
            }

            PlayerHUD.textLinesPos = TextCreator.splitOnLines(getLocationNameId((byte) RenderEngine.nextLocation) + 354, PlayerHUD.TEXT_TARGET_WIDTH, 0);
        }

    }

    private static boolean sub_8da() {
        for (byte var0 = 1; var0 < 17; ++var0) {
            if (var0 != RenderEngine.currentLocation && sub_819(var0)) {
                return true;
            }
        }

        return false;
    }

    public static void startDialog(short id, byte var1) {
        currentDialogId = id; //
        var_23b6 = var1; //
        givenAnswersCount = 0;
        selectedAnswer = 0;
        currentNpcPhrase = 0;
        dialogCompleted = false;
        phracesIdArray = null;
        dialogStructure = null;
        System.gc();
        label142:
        switch (currentDialogId) {
            case 4:
                switch (botanikDialogState) {
                    case 0:
                        phracesIdArray = botanikPhracesId;
                    default:
                        dialogStructure = botanikDialogStructure[botanikDialogState];
                        break label142;
                }
            case 5:
                switch (zaborDialogState) {
                    case 0: //Если ещё не говорили
                        phracesIdArray = zaborPhIdBefore;
                        break;
                    case 1: //Если уже поговорили
                        phracesIdArray = zaborPhIdAfter;
                }

                dialogStructure = zaborDialogSctucture[zaborDialogState];
                break;
            case 6:
                switch (rustyDialogState) {
                    case 0:
                        phracesIdArray = var_2cc;
                        break;
                    case 1:
                        phracesIdArray = var_369;
                        break;
                    case 2:
                        phracesIdArray = var_44c;
                        break;
                    case 3:
                        phracesIdArray = var_4d9;
                        break;
                    case 4:
                        phracesIdArray = var_533;
                        break;
                    case 5:
                        phracesIdArray = var_5a5;
                        break;
                    case 6:
                        phracesIdArray = var_5dc;
                }

                dialogStructure = rustyHierarchy[rustyDialogState];
                break;
            case 7: //Миссия Галоша
                switch (galoshQuestState) {
                    case 0: //Миссия в подвешенном состоянии
                        phracesIdArray = var_6e4; //Одному массиву присваивается другой
                        break;
                    case 1: //Выполненная миссия
                        phracesIdArray = var_74b;
                }

                dialogStructure = galoshHierarchy[galoshQuestState]; //В двойном массиве ячейке Галош присваивается значение единицы
                break;
            case 8:
                switch (krotDialogState) {
                    case 0:
                        phracesIdArray = var_c56;
                    default:
                        dialogStructure = var_c64[krotDialogState];
                        break label142;
                }
            case 9:
                switch (batyaDialogState) {
                    case 0:
                        phracesIdArray = var_e8e;
                        break;
                    case 1:
                        phracesIdArray = var_ed0;
                        break;
                    case 2:
                        phracesIdArray = var_f44;
                        break;
                    case 3:
                        phracesIdArray = var_fad;
                        break;
                    case 4:
                        phracesIdArray = var_102d;
                }

                dialogStructure = var_1084[batyaDialogState];
                break;
            case 10:
                switch (haryaDialogState) {
                    case 0:
                        phracesIdArray = var_cf9;
                        break;
                    case 1:
                        phracesIdArray = var_d49;
                        break;
                    case 2:
                        phracesIdArray = var_da7;
                        break;
                    case 3:
                        phracesIdArray = var_ded;
                }

                dialogStructure = var_e4b[haryaDialogState];
                break;
            case 11:
                switch (gutalinDialogState) {
                    case 0:
                        phracesIdArray = var_11d1;
                        break;
                    case 1:
                        phracesIdArray = var_123d;
                }

                dialogStructure = var_1254[gutalinDialogState];
                break;
            case 12:
                switch (shlangDialogState) {
                    case -1:
                        return;
                    case 0:
                        phracesIdArray = var_15b3;
                        break;
                    case 1:
                        phracesIdArray = var_1609;
                        break;
                    case 2:
                        phracesIdArray = var_1680;
                }

                dialogStructure = var_16a8[shlangDialogState];
                break;
            case 13:
                switch (belomorDialogState) {
                    case 0:
                        phracesIdArray = var_132d;
                        break;
                    case 1:
                        phracesIdArray = var_1397;
                        break;
                    case 2:
                        phracesIdArray = var_1411;
                        break;
                    case 3:
                        phracesIdArray = var_14b3;
                }

                dialogStructure = var_14ef[belomorDialogState];
                break;
            case 14:
                switch (kaynazovskiDialogState) {
                    case 0:
                        phracesIdArray = var_178f;
                        break;
                    case 1:
                        phracesIdArray = var_1821;
                        break;
                    case 2:
                        phracesIdArray = var_187a;
                }

                dialogStructure = var_18fb[kaynazovskiDialogState];
                break;
            case 15:
                switch (trusDialogState) {
                    case 0:
                        phracesIdArray = var_bad;
                    default:
                        dialogStructure = var_bc1[trusDialogState];
                        break label142;
                }
            case 16:
                switch (svistunDialogState) {
                    case 0:
                        phracesIdArray = var_afa;
                    default:
                        dialogStructure = var_b08[svistunDialogState];
                        break label142;
                }
            case 17:
                switch (manikovskiDialogState) {
                    case 0:
                        phracesIdArray = var_1153;
                    default:
                        dialogStructure = var_117d[manikovskiDialogState];
                        break label142;
                }
            case 18:
                switch (commanderDialogState) {
                    case 0:
                        phracesIdArray = var_10ce;
                    default:
                        dialogStructure = var_10e7[commanderDialogState];
                        break label142;
                }
            case 19:
                switch (koboldDialogState) {
                    case 0:
                        phracesIdArray = var_198b;
                        break;
                    case 1:
                        phracesIdArray = var_19e8;
                        break;
                    case 2:
                        phracesIdArray = var_1a64;
                }

                dialogStructure = var_1a9d[koboldDialogState];
                break;
            case 20:
                switch (prizrakDialogState) {
                    case 0:
                        phracesIdArray = var_1b50;
                    default:
                        dialogStructure = var_1b69[prizrakDialogState];
                        break label142;
                }
            case 21:
                switch (militaryDialogState) {
                    case 0:
                        phracesIdArray = var_1bde;
                        break;
                    case 1:
                        phracesIdArray = var_1c1e;
                }

                dialogStructure = var_1c6a[militaryDialogState];
        }

        if (phracesIdArray != null) {
            PlayerHUD.splitNPCPhraseToLines();
            PlayerHUD.resetNPCPhraseOffsetByTime();
            RenderEngine.setDialogWindowState((short) 9);
        }

    }

    private static void updateDialogState() {
        
        switch (currentDialogId) {
            //Первый лагерь
            //Ботаник
            case 4:
                if (botanikDialogState == 0) {
                    if (givenAnswersCount == 3) {
                        ++currentNpcPhrase;
                        dialogCompleted = true;
                        return;
                    }

                    if (botanikDialogStructure[botanikDialogState][givenAnswersCount] == 1) {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                    }
                }
                break;
            //Забор
            case 5:
                if (zaborDialogState == 0) {
                    if (zaborDialogSctucture[zaborDialogState][givenAnswersCount] == 1) //если развилка одна
                    {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                        return;//вернуться
                    }

                    if (givenAnswersCount == 2) //если два диалога пройдено
                    {
                        if (selectedAnswer == 0) //если выбран первый вариант ответа
                        {
                            ++currentNpcPhrase;
                            zaborDialogState = 1;
                            CurrentActorExpirience = (short) (CurrentActorExpirience + 70);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1) //если выбран второй вариант ответа
                        {
                            RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                            return;
                        }
                    }
                }

                if (zaborDialogState == 1) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                }
                break;
            //Ржавый
            case 6:
                if (rustyDialogState == 0) {
                    if (rustyHierarchy[rustyDialogState][givenAnswersCount] == 1) {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                        return;
                    }

                    if (selectedAnswer == 0) {
                        rustyDialogState = 2;
                        givenAnswersCount = 0;
                        ++currentNpcPhrase;
                        addMarkToPDA(StoryLocationMassive[0]);//дать наводку на локацию
                        dialogCompleted = true;
                        return;
                    }

                    if (selectedAnswer == 1) {
                        currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                        dialogCompleted = true;
                        return;
                    }

                    if (selectedAnswer == 2) {
                        currentNpcPhrase = (byte) (currentNpcPhrase + 3);
                        rustyDialogState = 1;
                        dialogCompleted = true;
                        return;
                    }
                }

                if (rustyDialogState == 1) {
                    if (givenAnswersCount == 0) {
                        if (selectedAnswer == 0) {
                            ++givenAnswersCount;
                            ++currentNpcPhrase;
                            return;
                        }

                        givenAnswersCount = 0;
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }

                    if (givenAnswersCount == 1) {
                        if (selectedAnswer == 0) {
                            rustyDialogState = 2;
                            ++currentNpcPhrase;
                            addMarkToPDA(StoryLocationMassive[0]);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                        }

                        if (selectedAnswer == 2) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 3);
                            rustyDialogState = 1;
                            dialogCompleted = true;
                            return;
                        }
                    }
                }

                if (rustyDialogState == 2) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                if (rustyDialogState == 3) {
                    if (rustyHierarchy[rustyDialogState][givenAnswersCount] == 1) {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                        return;
                    }

                    if (givenAnswersCount == 2) {
                        if (selectedAnswer == 0) {
                            ++givenAnswersCount;
                            ++currentNpcPhrase;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            rustyDialogState = 6;
                            currentNpcPhrase = (byte) (currentNpcPhrase + 4);
                            dialogCompleted = true;
                            addItemToInventary((short) 200);
                            return;
                        }

                        if (selectedAnswer == 2) {
                            dialogCompleted = true;
                            currentNpcPhrase = (byte) (currentNpcPhrase + 5);
                            return;
                        }
                    }

                    if (givenAnswersCount == 3 && (selectedAnswer == 0 || selectedAnswer == 1)) {
                        rustyDialogState = 4;
                        addMarkToPDA(StoryLocationMassive[1]);
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }
                }

                if (rustyDialogState == 4) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                if (rustyDialogState == 5) {
                    if (givenAnswersCount == 0) {
                        if (selectedAnswer == 0 && var_2544) {
                            rustyDialogState = 6;
                            ++currentNpcPhrase;
                            addItemToInventary((short) 200);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1 && !var_2544) {
                            givenAnswersCount = (byte) (givenAnswersCount + 2);
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            return;
                        }
                    }

                    if (givenAnswersCount == 2) {
                        if (selectedAnswer == 0 && searchItem((short) 124)) {
                            rustyDialogState = 6;
                            if (isItemEquipped((short) 124)) {
                                unequipItem((short) 124);
                            }

                            dropItem((short) 124);
                            addItemToInventary((short) 200);
                            dialogCompleted = true;
                            ++currentNpcPhrase;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            rustyDialogState = 6;
                            addItemToInventary((short) 200);
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                            return;
                        }
                    }
                }

                if (rustyDialogState == 6) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                }
                break;
            //Галош
            case 7:
                if (galoshQuestState == 0) {
                    //А как насчёт артефактов? Получение квеста
                    if (selectedAnswer == 4) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }

                    currentNpcPhrase = (byte) (currentNpcPhrase + selectedAnswer + 1);
                    dialogCompleted = true;
                    if (selectedAnswer == 3) {
                        addMarkToPDA(StoryLocationMassive[2]);
                    }
                }

                //Если квест выполнен. Благодарим за помощь
                if (galoshQuestState == 1 && selectedAnswer == 0) {
                    dialogCompleted = true;
                    ++currentNpcPhrase;
                }
                break;
            
            //Второй лагерь
            //Крот
            case 8:
                if (krotDialogState == 0) {
                    if (givenAnswersCount == 0) {
                        if (selectedAnswer == 0) {
                            ++currentNpcPhrase;
                            ++givenAnswersCount;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 6);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 2) {
                            RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        }
                    } else if (givenAnswersCount == 1) {
                        if (selectedAnswer == 0) {
                            ++currentNpcPhrase;
                            CurrentActorMoney = (short) (CurrentActorMoney - 50);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            CurrentActorMoney = (short) (CurrentActorMoney - 50);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 2) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 3);
                            CurrentActorMoney = (short) (CurrentActorMoney - 50);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 3) {
                            RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        }
                    }
                }
                break;
            //Батька
            case 9:
                if (batyaDialogState == 0) {
                    if (givenAnswersCount == 0 || givenAnswersCount == 1) {
                        ++currentNpcPhrase;
                        ++givenAnswersCount;
                        return;
                    }

                    if (givenAnswersCount == 2) {
                        if (selectedAnswer == 0) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            batyaDialogState = 1;
                            addMarkToPDA(StoryLocationMassive[6]);
                            return;
                        }

                        if (selectedAnswer == 1) {
                            if (CurrentActorMoney >= 500) {
                                currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                                dialogCompleted = true;
                                CurrentActorMoney = (short) (CurrentActorMoney - 500);
                                addMarkToPDA((byte) 9);
                                batyaDialogState = 3;
                            }
                            break;
                        }

                        if (selectedAnswer == 2) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 3);
                            dialogCompleted = true;
                            return;
                        }
                    }
                }

                if (batyaDialogState == 1) {
                    if (selectedAnswer == 0) {
                        if (CurrentActorMoney >= 500) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            CurrentActorMoney = (short) (CurrentActorMoney - 500);
                            addMarkToPDA((byte) 9);
                            RenderEngine.bool_massive_1st[StoryLocationMassive[6]] = true;
                            batyaDialogState = 3;
                        }
                        break;
                    }

                    if (selectedAnswer == 1) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }
                }

                if (batyaDialogState == 2) {
                    if (selectedAnswer == 0) {
                        if (searchItem((short) 125)) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            if (isItemEquipped((short) 125)) {
                                unequipItem((short) 125);
                            }

                            dropItem((short) 125);
                            addMarkToPDA((byte) 9);
                            batyaDialogState = 3;
                        }
                        break;
                    }

                    if (selectedAnswer == 1) {
                        if (CurrentActorMoney >= 500) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                            CurrentActorMoney = (short) (CurrentActorMoney - 500);
                            addMarkToPDA((byte) 9);
                            batyaDialogState = 3;
                        }
                        break;
                    }

                    if (selectedAnswer == 2) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }
                }

                if (batyaDialogState == 3) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                if (batyaDialogState == 4) {
                    if (givenAnswersCount == 1) {
                        addMarkToPDA(StoryLocationMassive[8]);
                        RenderEngine.setActiveObjState(var_23b6, (short) 0);
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }

                    ++currentNpcPhrase;
                    ++givenAnswersCount;
                }
                break;
            //Харя
            case 10:
                if (haryaDialogState == 0) {
                    if (selectedAnswer == 0) {
                        ++currentNpcPhrase;
                        dialogCompleted = true;
                        haryaDialogState = 1;
                        addMarkToPDA(StoryLocationMassive[5]);
                        return;
                    }

                    if (selectedAnswer == 1) {
                        currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                        dialogCompleted = true;
                        return;
                    }

                    if (selectedAnswer == 2) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }
                }

                if (haryaDialogState == 1) {
                    ++currentNpcPhrase;
                    dialogCompleted = true;
                    return;
                }

                if (haryaDialogState == 2) {
                    haryaDialogState = 3;
                    addItemToInventary((short) 112);
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                if (haryaDialogState == 3) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                }
                break;
            //Третий лагерь
            //Гуталин
            case 11:
                if (gutalinDialogState == 0) {
                    if (selectedAnswer == 0) {
                        if (CurrentActorMoney >= 600) {
                            ++currentNpcPhrase;
                            CurrentActorMoney = (short) (CurrentActorMoney - 600);
                            ++currentPlayerLevel;
                            gutalinDialogState = 1;
                            dialogCompleted = true;
                        }
                        break;
                    }

                    if (selectedAnswer == 1) {
                        currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                        dialogCompleted = true;
                    }
                }

                if (gutalinDialogState == 1) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                }
                break;
            //Шланг
            case 12:
                if (shlangDialogState == 0) {
                    if (givenAnswersCount == 0) {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                        return;
                    }

                    if (selectedAnswer == 0) {
                        ++currentNpcPhrase;
                        shlangDialogState = 1;
                        addMarkToPDA(StoryLocationMassive[11]);
                        dialogCompleted = true;
                        return;
                    }

                    if (selectedAnswer == 1) {
                        currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                        dialogCompleted = true;
                        return;
                    }
                }

                if (shlangDialogState == 1) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                if (shlangDialogState == 2) {
                    ++currentNpcPhrase;
                    dialogCompleted = true;
                    CurrentActorMoney = (short) (CurrentActorMoney + 2000);
                    RenderEngine.setActiveObjState(var_23b6, (short) 0);
                    shlangDialogState = -1;
                }
                break;
            //Беломор
            case 13:
                if (belomorDialogState == 0) {
                    if (givenAnswersCount < 3) {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                        return;
                    }

                    if (selectedAnswer == 0) {
                        addMarkToPDA(StoryLocationMassive[10]);
                        belomorDialogState = 1;
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }

                    if (selectedAnswer == 1) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }
                }

                if (belomorDialogState == 1 || belomorDialogState == 3) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                if (belomorDialogState == 2) {
                    if (givenAnswersCount == 1) {
                        belomorDialogState = 3;
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }
                break;
            //Кайназовский
            case 14:
                if (kaynazovskiDialogState == 0) {
                    if (givenAnswersCount == 1) {
                        if (selectedAnswer == 0 && !var_25bf) {
                            return;
                        }

                        givenAnswersCount = (byte) (givenAnswersCount + 3);
                        currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                        return;
                    }

                    if (givenAnswersCount == 6) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        addMarkToPDA(StoryLocationMassive[12]);
                        kaynazovskiDialogState = 1;
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                    return;
                }

                if (kaynazovskiDialogState == 1) {
                    if (givenAnswersCount == 1) {
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        addMarkToPDA(StoryLocationMassive[12]);
                        kaynazovskiDialogState = 1;
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }

                if (kaynazovskiDialogState == 2) {
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    RenderEngine.setActiveObjState(var_23b6, (short) 0);
                    koboldDialogState = 2;
                    addItemToInventary((short) 128);
                }
                break;
            //Трус. По заданию с рации
            case 15:
                if (trusDialogState == 0) {
                    if (givenAnswersCount == 0) {
                        ++currentNpcPhrase;
                        ++givenAnswersCount;
                        return;
                    }

                    if (givenAnswersCount == 1) {
                        addMarkToPDA(StoryLocationMassive[4]);
                        RenderEngine.setActiveObjState(var_23b6, (short) 0);
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    }
                }
                break;
            //Друг Ржавого в канализации, Свистун
            case 16:
                if (svistunDialogState == 0) {
                    if (givenAnswersCount == 0) {
                        ++givenAnswersCount;
                        ++currentNpcPhrase;
                        return;
                    }

                    if (givenAnswersCount == 1) {
                        if (selectedAnswer == 0 && (searchItem((short) 101) || searchItem((short) 102))) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            var_2544 = true;
                            if (searchItem((short) 101)) {
                                dropItem((short) 101);
                            } else if (searchItem((short) 102)) {
                                dropItem((short) 102);
                            }

                            RenderEngine.setActiveObjState(var_23b6, (short) 0);
                            var_2204 = true;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                            addItemToInventary((short) 141);
                            addItemToInventary((short) 124);
                            RenderEngine.setActiveObjState(var_23b6, (short) 0);
                            var_2204 = true;
                        }
                    }
                }
                break;
            //Маниковский, по заданию Кэпа
            case 17:
                RenderEngine.setActiveObjState(var_23b6, (short) 0);
                if (selectedAnswer == 0) {
                    var_25bf = true;
                    addItemToInventary((short) 200);
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                    return;
                }

                currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                addMarkToPDA(StoryLocationMassive[8]);
                CurrentActorExpirience = (short) (CurrentActorExpirience + 400);
                dialogCompleted = true;
                return;
            //Кэп
            case 18:
                if (givenAnswersCount == 2) {
                    addMarkToPDA(StoryLocationMassive[7]);
                    RenderEngine.setActiveObjState(var_23b6, (short) 0);
                    RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                }

                ++currentNpcPhrase;
                ++givenAnswersCount;
                return;
            //Кобольд
            case 19:
                //Перед посещением третьего лагеря
                if (koboldDialogState == 0) {
                    if (givenAnswersCount == 1) {
                        RenderEngine.setActiveObjState(var_23b6, (short) 0);
                        RenderEngine.setDialogWindowState(RenderEngine.prevGameState);
                        addItemToInventary((short) 127);
                        kaynazovskiDialogState = 2;
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }
                
                //Повторный разговор, уже в лагере
                if (koboldDialogState == 1) {
                    if (givenAnswersCount == 3) {
                        koboldDialogState = 0;
                        RenderEngine.var_7ee = RenderEngine.currentRoom;
                        RenderEngine.currentLocation = 16;
                        RenderEngine.bool_massive_3th[RenderEngine.currentLocation] = RenderEngine.currentLocation == 1 || RenderEngine.currentLocation == 6 || RenderEngine.currentLocation == 12;
                        RenderEngine.bool_massive_1st[RenderEngine.currentLocation] = !RenderEngine.bool_massive_3th[RenderEngine.currentLocation];
                        RenderEngine.setDialogWindowState((short) -2);
                        RenderEngine.setActiveObjState(var_23b6, (short) 0);
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }

                if (koboldDialogState == 2) {
                    RenderEngine.setDialogWindowState((short) 2);
                    RenderEngine.setActiveObjState(var_23b6, (short) 0);
                    endingCutscene = true;
                }
                break;
            //Призрак
            case 20:
                if (givenAnswersCount == 4) {
                    Main.main.numberOfPlayers = 0;
                    RenderEngine.endGame((short) 11, 5000);//финал игры?
                    return;
                }

                ++currentNpcPhrase;
                ++givenAnswersCount;
                return;
            //Рация. Командование
            case 21:
                if (militaryDialogState == 0) {
                    dialogCompleted = true;
                    addMarkToPDA(StoryLocationMassive[3]);
                    ++currentNpcPhrase;
                    ItemWalkieTalkieInInventaryBool = false;
                    militaryDialogState = 1;
                    return;
                }

                if (militaryDialogState == 1) {
                    if (givenAnswersCount == 1) {
                        dialogCompleted = true;
                        addMarkToPDA(StoryLocationMassive[9]);
                        ++currentNpcPhrase;
                        ItemWalkieTalkieInInventaryBool = false;
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }
        }
        

    }

    private static void dialogSelector() {
        if (dialogStructure.length != 0) //диалоги?
        {
            NumberOfAnswers = dialogStructure[givenAnswersCount];
            if (Keys.left || Keys.up) {
                Keys.up = false;
                Keys.left = false;
                selectedAnswer = (byte) Math.max(0, selectedAnswer - 1);
            }

            
            if (Keys.right || Keys.down) {
                Keys.down = false;
                Keys.right = false;
                selectedAnswer = (byte) Math.min(NumberOfAnswers - 1, selectedAnswer + 1);
            }
        }

        //подтвердить выбор
        if (Keys.rightSoft || Keys.fire) {
            Keys.rightSoft = false;
            Keys.fire = false;
            if (dialogCompleted) {
                RenderEngine.setDialogWindowState(RenderEngine.prevGameState);//выполнить действие?
                return;
            }

            updateDialogState();
            PlayerHUD.splitNPCPhraseToLines();
            PlayerHUD.resetNPCPhraseOffsetByTime();
        }

    }

    private static void sub_99d() {
        Main.main.setScreen(AllScreens.masterInventory, (byte) 6);
        Main.main.repaint();
    }

    private static void sub_9d1() {
        Main.main.setScreen(AllScreens.masterInventory, (byte) 7);
        Main.main.repaint();
    }

    private static void InteractionWith(byte type) //Interaction - взаимодействие; 1 деньги, 2 тайник, 3 - торговец, 0 - диалог
    {
        openedActivableObjId = type;
        short number;
        if ((number = RenderEngine.SearchInteractionPoint(type)) != 0 && number > 21) //что там находится?
        {
            if (number < 50 || number > 58) {
                if (number <= 126 && number != 100) {
                    if (number == 25) {
                        stashItems = new short[]{(short) 126, (short) 107};
                        sub_99d();
                        return;
                    }

                    if (number == 26) {
                        addItemToInventary((short) 127);
                        addItemToInventary((short) 140);
                        RenderEngine.setActiveObjState(type, (short) 0);
                        return;
                    }

                    stashItems = new short[]{number};
                    sub_99d();
                    return;
                }

                addItemToInventary(number);
                RenderEngine.setActiveObjState(type, (short) 0);
                return;
            }

            if (number == 50) {
                var_2204 = true;
                stashItems = new short[]{(short) 131};
                sub_99d();
            }

            if (number == 51) {
                addItemToInventary((short) 140);
                addItemToInventary((short) 128);
                RenderEngine.setActiveObjState(type, (short) 0);
            }

            if (number == 52) {
                addItemToInventary((short) 127);
                stashItems = new short[]{(short) 123};
                sub_99d();
            }

            if (number == 53) {
                stashItems = new short[]{(short) 102};
                sub_99d();
            }

            if (number == 54) {
                stashItems = new short[]{(short) 118};
                sub_99d();
            }

            if (number == 55) {
                stashItems = new short[]{(short) 101};
                sub_99d();
            }

            if (number == 56) {
                var_2204 = true;
                stashItems = new short[]{(short) 125, (short) 102};
                sub_99d();
            }

            if (number == 57) {
                stashItems = new short[]{(short) 117};
                sub_99d();
            }

            if (number == 58) {
                var_2204 = true;
                RenderEngine.setActiveObjState(type, (short) 0);
            }
        } else {
            if (number == 1) { //Ассортимент первого торговца в деревне (п. аптечка, антирад, магазин ак, сам калаш и два броника)
                traderItems = new short[]{(short) 101, (short) 103, (short) 107, (short) 117, (short) 110, (short) 111};
                sub_9d1();
                return;
            }

            if (number == 2) {
                traderItems = new short[]{(short) 101, (short) 102, (short) 103, (short) 107, (short) 117, (short) 110, (short) 111, (short) 112, (short) 118, (short) 108};
                sub_9d1();
                return;
            }

            if (number == 3) {
                traderItems = new short[]{(short) 101, (short) 102, (short) 103, (short) 107, (short) 117, (short) 110, (short) 111, (short) 112, (short) 114, (short) 118, (short) 108, (short) 119, (short) 109};
                sub_9d1();
                return;
            }

            if (number != 0) {
                startDialog(number, type);
            }
        }

    }

}
