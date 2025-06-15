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
    public static short inventoryItemsCount;

    //Будут удалены после выхода с локации
    public static short[] locationInventoryItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};

    //Набор экипировки
    public static short[] equipmentSlots = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1};

    //Локации, после прохождения которых выполняются те или иные квесты
    public static final byte[] StoryLocationMassive = new byte[]{(byte) 3, (byte) 4, (byte) 2, (byte) 5, (byte) 6, (byte) 8, (byte) 7, (byte) 10, (byte) 12, (byte) 11, (byte) 13, (byte) 14, (byte) 15};

    //===Инициализация структуры диалогов===
    //Состояние диалога
    public static byte rustyDialogState;
    //Количество вариантов ответа после каждого ответа главгероя
    public static final byte[] rustyFirstAnsCount = new byte[]{ 1,  1,  1,  3,  1,  1,  1};
    //Адреса на текстовые строки в главном текстовом массиве
    public static final short[] rustyFirstPhrId = new short[]{ 78,  79,  80,  81,  82,  83,  84,  85,  86,  87,  88,  89,  90};
    public static final byte[] rustySecondAnsCount = new byte[]{ 2,  3,  1,  1,  1,  1};
    public static final short[] rustySecondPhrId = new short[]{ 91,  92,  93,  94,  95,  96,  97,  98,  99,  100,  101};
    public static final byte[] rustyThirdAnsCount = new byte[0];
    public static final short[] rustyThirdPhrId = new short[]{ 102};
    public static final byte[] rustyFourthAnsCount = new byte[]{ 1,  1,  3,  2,  1,  1,  1,  1};
    public static final short[] rustyFourthPhrId = new short[]{ 103,  104,  105,  106,  107,  108,  109,  110,  -1,  111,  -1,  112,  113,  114,  115};
    public static final byte[] rustyFifthAnsCount = new byte[0];
    public static final short[] rustyFifthPhrId = new short[]{ 116};
    public static final byte[] rustySixthAnsCount = new byte[]{ 2,  1,  2,  1,  1};
    public static final short[] rustySixthPhrId = new short[]{ 117,  118,  119,  120,  121,  122,  123,  124,  125};
    public static final byte[] rustySeventhAnsCount = new byte[0];
    public static final short[] rustySeventhPhrId = new short[]{ 126};
    public static final byte[][] rustyDialogStructure = new byte[][]{rustyFirstAnsCount, rustySecondAnsCount, rustyThirdAnsCount, rustyFourthAnsCount, rustyFifthAnsCount, rustySixthAnsCount, rustySeventhAnsCount};

    public static byte galoshQuestState;
    public static final byte[] galoshFirstMeetAns = new byte[]{ 5,  1,  1,  1,  1,  1};
    public static final short[] galoshFirstIds = new short[]{ 127, 
        128,  129,  
        130,  131,  
        132,  133,  
        134,  153,  
        154};
    public static final byte[] galoshAfterQuestAns = new byte[]{ 1};
    public static final short[] galoshAfterQuestIds = new short[]{ 155, 156,  157};
    public static final byte[][] galoshDialogStructure = new byte[][]{galoshFirstMeetAns, galoshAfterQuestAns};

    public static byte zaborDialogState;
    public static final byte[] zaborBeforeAnsCount = new byte[]{ 1,  1,  2,  1,  1};
    public static final short[] zaborBeforePhrId = new short[]{ 135,  
        136,  137,  138, 139,
                            140,  141,//-Tы можeшь мнe чeловeчecким языком объяcнить, что ты xочeшь cкaзaть?
                            142};//-Paзговapивaть cнaчaлa нayчиcь, обeзьянa.
    public static final byte[] zaborAfterAnsCount = new byte[0];
    public static final short[] zaborAfterPhrId = new short[]{ 143};
    public static final byte[][] zaborDialogSctucture = new byte[][]{zaborBeforeAnsCount, zaborAfterAnsCount};

    public static byte botanikDialogState;
    public static final byte[] botanikAnsCount = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] botanikPhracesId = new short[]{(short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150, (short) 151, (short) 152};
    public static final byte[][] botanikDialogStructure = new byte[][]{botanikAnsCount};

    public static byte svistunDialogState;
    public static final byte[] svistunAnsCount = new byte[]{(byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] svistunPhrId = new short[]{(short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164};
    public static final byte[][] svistunDialogStructure = new byte[][]{svistunAnsCount};

    public static byte trusDialogState;
    public static final byte[] trusAnsCount = new byte[]{(byte) 1, (byte) 1};
    public static final short[] trusPhrId = new short[]{(short) 165, (short) 166, (short) 167, (short) 168};
    public static final byte[][] trusDialogStruct = new byte[][]{trusAnsCount};

    public static byte krotDialogState;
    public static final byte[] krotAnsCount = new byte[]{(byte) 3, (byte) 4, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] krotPhrId = new short[]{(short) 169, (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178, (short) -1, (short) 179, (short) 180, (short) 181};
    public static final byte[][] krotDialogStruct = new byte[][]{krotAnsCount};

    public static byte haryaDialogState;
    public static final byte[] haryaFirstAnsCount = new byte[]{(byte) 3, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] haryaFirstPhrId = new short[]{(short) 182, (short) 183, (short) 184, (short) 185, (short) 186, (short) 187};
    public static final byte[] haryaSecondAnsCount = new byte[]{(byte) 1};
    public static final short[] haryaSecondPhrId = new short[]{(short) 188, (short) 189, (short) 190};
    public static final byte[] haryaThirdAnsCount = new byte[0];
    public static final short[] haryaThirdPhrId = new short[]{(short) 191};
    public static final byte[] haryaFourthAnsCount = new byte[0];
    public static final short[] haryaFourthPhrId = new short[]{(short) 192};
    public static final byte[][] haryaDialogStruct = new byte[][]{haryaFirstAnsCount, haryaSecondAnsCount, haryaThirdAnsCount, haryaFourthAnsCount};

    public static byte batyaDialogState;
    public static final byte[] batyaFirstAnsCount = new byte[]{(byte) 1, (byte) 1, (byte) 3, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] batyaFirstPhrId = new short[]{(short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203};
    public static final byte[] batyaSecondAnsCount = new byte[]{(byte) 2, (byte) 1, (byte) 1};
    public static final short[] batyaSecondPhrId = new short[]{(short) 204, (short) 205, (short) 206, (short) 207};
    public static final byte[] batyaThirdAnsCount = new byte[]{(byte) 3, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] batyaThirdPhrId = new short[]{(short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 394};
    public static final byte[] batyaFourthAnsCount = new byte[0];
    public static final short[] batyaFourthPhrId = new short[]{(short) 213};
    public static final byte[] batyaFifthAnsCount = new byte[]{(byte) 1, (byte) 1};
    public static final short[] batyaFifthPhrId = new short[]{(short) 214, (short) 215, (short) 216, (short) 217};
    public static final byte[][] batyaDialogStruct = new byte[][]{batyaFirstAnsCount, batyaSecondAnsCount, batyaThirdAnsCount, batyaFourthAnsCount, batyaFifthAnsCount};

    public static byte commanderDialogState;
    public static final byte[] commanderAnsCount = new byte[]{(byte) 1, (byte) 1, (byte) 1};
    public static final short[] commanderPhrId = new short[]{(short) 218, (short) 219, (short) 220, (short) 221, (short) 222, (short) 223};
    public static final byte[][] commanderDialogStruct = new byte[][]{commanderAnsCount};

    public static byte manikovskiDialogState;
    public static final byte[] manikovskiAnsCount = new byte[]{(byte) 2, (byte) 1, (byte) 1};
    public static final short[] manikovskiPhrId = new short[]{(short) 224, (short) 225, (short) -1, (short) 226, (short) 227};
    public static final byte[][] manikovskiDialogStruct = new byte[][]{manikovskiAnsCount};

    public static byte gutalinDialogState;
    public static final byte[] gutalinFirstAnsCount = new byte[]{(byte) 2, (byte) 1, (byte) 1};
    public static final short[] gutalinFirstPhrId = new short[]{(short) 228, (short) 229, (short) 230, (short) 231, (short) 232};
    public static final byte[] gutalinSecondAnsCount = new byte[0];
    public static final short[] gutalinSecondPhrId = new short[]{(short) 233};
    public static final byte[][] gutalinDialogStruct = new byte[][]{gutalinFirstAnsCount, gutalinSecondAnsCount};

    public static byte belomorDialogState;
    public static final byte[] belomorFirstAnsCount = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] belomorFirstPhrId = new short[]{(short) 234, (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241, (short) -1, (short) 242, (short) -1};
    public static final byte[] belomorSecondAnsCount = new byte[0];
    public static final short[] belomorSecondPhrId = new short[]{(short) 248};
    public static final byte[] belomorThirdAnsCount = new byte[]{(byte) 1, (byte) 1};
    public static final short[] belomorThirdPhrId = new short[]{(short) 243, (short) 244, (short) 245, (short) 246};
    public static final byte[] belomorFourthAnsCount = new byte[0];
    public static final short[] belomorFourthPhrId = new short[]{(short) 247};
    public static final byte[][] belomorDialogStruct = new byte[][]{belomorFirstAnsCount, belomorSecondAnsCount, belomorThirdAnsCount, belomorFourthAnsCount};

    public static byte shlangDialogState;
    public static final byte[] shlangFirstAnsCount = new byte[]{(byte) 1, (byte) 2, (byte) 1, (byte) 1};
    public static final short[] shlangFirstPhrId = new short[]{(short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255};
    public static final byte[] shlangSecondAnsCount = new byte[]{(byte) 1};
    public static final short[] shlangSecondPhrId = new short[]{(short) 256, (short) 257};
    public static final byte[] shlangThirdAnsCount = new byte[]{(byte) 1};
    public static final short[] shlangThirdPhrId = new short[]{(short) 258, (short) 259, (short) 260};
    public static final byte[][] shlangDialogStruct = new byte[][]{shlangFirstAnsCount, shlangSecondAnsCount, shlangThirdAnsCount};

    public static byte kaynazovskiDialogState;
    public static final byte[] kaynazovskiFirstAnsCount = new byte[]{(byte) 1, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] kaynazovskiFirstPhrId = new short[]{(short) 261, (short) 262, (short) 263, (short) 264, (short) -1, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269, (short) 270, (short) 271};
    public static final byte[] kaynazovskiSecondAnsCount = new byte[]{(byte) 1, (byte) 1};
    public static final short[] kaynazovskiSecondPhrId = new short[]{(short) 272, (short) 273, (short) 274, (short) 275};
    public static final byte[] kaynazovskiThirdAnsCount = new byte[]{(byte) 1};
    public static final short[] kaynazovskiThirdPhrId = new short[]{(short) 289, (short) 290};
    public static final byte[][] kaynazovskiDialogStruct = new byte[][]{kaynazovskiFirstAnsCount, kaynazovskiSecondAnsCount, kaynazovskiThirdAnsCount};

    public static byte koboldDialogState = 1;
    public static final byte[] koboldFirstAnsCount = new byte[]{(byte) 1, (byte) 1};
    public static final short[] koboldFirstPhrId = new short[]{(short) 276, (short) 277, (short) 278, (short) 279};
    public static final byte[] koboldSecondAnsCount = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] koboldSecondPhrId = new short[]{(short) 280, (short) 281, (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287};
    public static final byte[] koboldThirdAnsCount = new byte[0];
    public static final short[] koboldThirdPhrId = new short[]{(short) 288};
    public static final byte[][] koboldDialogStruct = new byte[][]{koboldFirstAnsCount, koboldSecondAnsCount, koboldThirdAnsCount};

    public static byte prizrakDialogState = 0;
    public static final byte[] prizrakAnsCount = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1};
    public static final short[] prizrakPhrId = new short[]{(short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297, (short) 298, (short) 299, (short) 300};
    public static final byte[][] prizrakDialogStruct = new byte[][]{prizrakAnsCount};

    public static byte militaryDialogState = 0;
    public static final byte[] militaryFirstAnsCount = new byte[]{(byte) 1};
    public static final short[] militaryFirstPhrId = new short[]{(short) 301, (short) 302, (short) 303};
    public static final byte[] militarySecondAnsCount = new byte[]{(byte) 1, (byte) 1};
    public static final short[] militarySecondPhrId = new short[]{(short) 304, (short) 305, (short) 306, (short) 307, (short) 308};
    public static final byte[][] militaryDialogStruct = new byte[][]{militaryFirstAnsCount, militarySecondAnsCount};

    //Используется ли оптический прицел в данный момент?
    public static boolean OpticalSight;
    //Максимальное здоровье главгероя
    public static short playerMaxHealth = 100;
    //Максимальный вес
    public static short playerMaxWeight = 350;
    //Текущее здоровье
    public static short playerHealth = playerMaxHealth;
    //Текущая сумма денег
    public static short playerMoney = 0;
    //Текущий вес
    public static short playerWeight;
    //Аммуниция
    public static short[] playerWeaponsAmmo = new short[4];
    //Точность главгероя
    public static byte playerAccuracy = 50;//50
    //Защита от пуль
    public static byte playerBulletProtection;
    //Защита от радиации
    public static byte playerRadiationResistance;
    //Защита от аномалий
    public static byte playerAnomalyResistance;
    //Текущий опыт главгероя
    public static short playerExp;
    //Текущий уровень главгероя
    public static short playerLevel;
    //Набор значений уровней характеристик
    public static byte[] playerStatLevel = new byte[3];

    //Использован ли антирад?
    public static boolean IsAntiradIsUsed;
    //Продолжительность действия
    public static int AntiradDuration = 20000;

    private static boolean botUnderCursor;
    private static boolean gunSoundPlayed;
    public static boolean gunSwitchInProcess;
    public static short timeToSwitchWeapon = 1000;
    public static int switchingGunTimePassed;
    public static boolean gunReloadInProcess;
    public static short timeToReload = 1000;
    public static int reloadingTimePassed;

    public static boolean playerCanLeaveLevel;
    public static int MoneyTaken;
    public static boolean weaponFireflareHidden;
    public static boolean botHitPlayerBefore;
    public static int[] questLocationDescriptionId;
    public static byte[] questLocationNumber;
    public static byte allQuestsNumber;

    //  Диалоговая система
    public static short currentDialogId;
    public static byte currentDialogActiveObjId;
    public static short[] phracesIdArray = null;
    //Иерархия ответов
    public static byte[] dialogStructure = null;

    public static byte givenAnswersCount;
    public static byte currentNpcPhrase;
    public static byte selectedAnswer;
    public static byte NumberOfAnswers;
    public static boolean svistunQuestCompleted;
    public static boolean dialogCompleted;
    public static boolean capItemGot;
    public static boolean ItemWalkieTalkieInInventaryBool;
    public static boolean endingCutscene;
    public static short[] stashItems;
    public static int openedActivableObjId;
    public static short[] traderItems;
    
    //CONSTANTS
    //MEDS
    private static final short SMALL_MED = 101;
    private static final short ARMY_MED = 102;
    private static final short ANTIRAD = 103;
    
    //WEAPON MAGAZINES
    private static final short AK74_MAG = 107;
    private static final short OC14_MAG = 108;
    private static final short L85_MAG = 109;
    
    //ARMOR
    private static final short LJAC_ARM = 110;
    private static final short MJAC_ARM = 111;
    private static final short MERC_ARM = 112;
    private static final short SEVA_ARM = 113;
    private static final short STAL_ARM = 114;
    private static final short SPP9_ARM = 115;
    
    //WEAPONS
    private static final short FORT_GUN = 116;
    private static final short AK74_GUN = 117;
    private static final short OC14_GUN = 118;
    private static final short L85_GUN = 119;
    
    //ARTEFACTS
    private static final short MEDU_ART = 120;
    private static final short BALL_ART = 121;
    private static final short CRYS_ART = 122;
    private static final short SPAR_ART = 123;
    private static final short MOON_ART = 124;
    private static final short BATT_ART = 125;
    private static final short DUMM_ART = 126;

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
        prizrakDialogState = 0;
        kaynazovskiDialogState = 0;
        krotDialogState = 0;
        haryaDialogState = 0;
        commanderDialogState = 0;
        manikovskiDialogState = 0;
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
        playerWeaponsAmmo[0] = 12;

        for (byte var1 = 1; var1 < playerWeaponsAmmo.length; ++var1) //Опустошить аммуницию основного оружия
        {
            playerWeaponsAmmo[var1] = 0;
        }

        ItemWalkieTalkieInInventaryBool = false;
        playerLevel = 1;
        playerExp = 0;
        playerStatLevel = new byte[3];
        playerMaxHealth = 100;
        playerHealth = playerMaxHealth;
        playerMaxWeight = 350;
        playerWeight = 0;
        playerMoney = 0;
        playerAccuracy = 50;
        playerAnomalyResistance = 0;
        playerRadiationResistance = 0;
        playerBulletProtection = 0;
        svistunQuestCompleted = false;
        capItemGot = false;
        inventoryItems = new short[100];
        equipmentSlots = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        locationInventoryItems = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
        inventoryItemsCount = 0;

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
        addItemToInventory((short) FORT_GUN); //В оригинале выдаётся только пистолет
        /*addItemToInventary((short) 117);
        addItemToInventary((short) 118);
        addItemToInventory((short) 119);
        //add artifacts
        addItemToInventary((short) 120);
        addItemToInventary((short) 121);
        addItemToInventary((short) 122);
        addItemToInventary((short) 123);
        addItemToInventary((short) 124);
        addItemToInventary((short) 125);
        addItemToInventary((short) 126);*/
        
        useItem((short) FORT_GUN);
        //ModChanges
        //ModChanges.NewStartItems();
    }

    public static void disableLockedDoorIcon() {
        PlayerHUD.doorLocked = false;
    }

    public static void levelUpPlayerStat(byte stat_number) {
        if (playerStatLevel[0] + playerStatLevel[1] + playerStatLevel[2] < playerLevel) {//Если сумма всех стат персонажа меньше текущего уровня
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
            playerStatLevel[stat_number]++; //Поднять уровень выбранной характеристики
        }
    }

    public static short getItemWeight(short item) //Возвращает вес выбранного предмета
    {
        switch (item) {
            case SMALL_MED: //Полевая аптечка
                return (short) 5;
            case ARMY_MED: //Военная аптечка
                return (short) 10;
            case ANTIRAD:
                return (short) 3;
            case 104:
                return (short) 4;
            case 105:
                return (short) 12;
            case 106:
                return (short) 3;
            case AK74_MAG:
                return (short) 3;
            case OC14_MAG:
                return (short) 4;
            case L85_MAG:
                return (short) 3;
            case LJAC_ARM:
                return (short) 30;
            case MJAC_ARM:
                return (short) 45;
            case MERC_ARM:
                return (short) 70;
            case SEVA_ARM:
                return (short) 50;
            case STAL_ARM:
                return (short) 110;
            case SPP9_ARM:
                return (short) 80;
            case FORT_GUN:
                return (short) 10;
            case AK74_GUN:
                return (short) 36;
            case OC14_GUN:
                return (short) 25;
            case L85_GUN:
                return (short) 50;
            case MEDU_ART:
                return (short) 5;
            case BALL_ART:
                return (short) 5;
            case CRYS_ART:
                return (short) 5;
            case SPAR_ART:
                return (short) 5;
            case MOON_ART:
                return (short) 0;
            case BATT_ART:
                return (short) 5;
            case DUMM_ART:
                return (short) 5;
            case 300:
                return (short) 30;
            default:
                return (short) 0;
        }
    }

    public static short getItemPrice(short i) {
        switch (i) {
            case SMALL_MED:
                return (short) 100;
            case ARMY_MED:
                return (short) 250;
            case ANTIRAD:
                return (short) 250;
            case 104:
                return (short) 50;
            case 105:
                return (short) 350;
            case 106:
                return (short) 0;
            case AK74_MAG:
                return (short) 60;
            case OC14_MAG:
                return (short) 50;
            case L85_MAG:
                return (short) 140;
            case LJAC_ARM:
                return armorLeatherJacketStats[3];
            case MJAC_ARM:
                return armorMailJacketStats[3];
            case MERC_ARM:
                return armorMercSuitStats[3];
            case SEVA_ARM:
                return armorSevaSuitStats[3];
            case STAL_ARM:
                return armorStalkerSuitStats[3];
            case SPP9_ARM:
                return armorSPP9mStats[3];
            case FORT_GUN:
                return (short) 0;
            case AK74_GUN:
                return (short) 1500;
            case OC14_GUN:
                return (short) 1000;
            case L85_GUN:
                return (short) 2000;
            case MEDU_ART:
                return (short) 400;
            case BALL_ART:
                return (short) 500;
            case CRYS_ART:
                return (short) 750;
            case SPAR_ART:
                return (short) 450;
            case MOON_ART:
                return (short) 600;
            case BATT_ART:
                return (short) 800;
            case DUMM_ART:
                return (short) 500;
            case 300:
                return (short) 1000;
            default:
                return (short) 0;
        }
    }

    private static void NPCShootPlayer() //Урон по игроку огнестрелом
    {                                    //с определённым шансом
        if (GameScene.botIsShooting && GameScene.activeBotId != -1 && !GameScene.showFinalDialog && !botHitPlayerBefore) {
            byte damage = 0;
            boolean hit_bool = false;
            byte var2;
            switch (var2 = Bot.enemyWeaponType[GameScene.activeBotId]) {
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
                damageToPlayer(damage - damage * playerBulletProtection / 100);
            }
        }

    }

    public static void checkPlayerAndSaveGame()
    {
        playerControlCheck(); //Проверить нажатия всех клавиш
        if (PlayerHUD.IsTransitWasCompleted) //Если был выполнен переход по локации
        {
            PlayerHUD.IsTransitWasCompleted = false; //Включение ожидания перехода
            ResourceManager.saveGame(); //Провести сохранение
            GameScene.setDialogWindowState((short) -2);
        }

        if (GameScene.showIntro && PlayerHUD.introRolledFully) {
            PlayerHUD.introRolledFully = false;
            GameScene.showIntro = false;
            GameScene.setDialogWindowState((short) 2);
        }

        if (gunReloadInProcess && GameScene.gameTimeUnpaused - (long) reloadingTimePassed >= (long) (timeToReload / 2) && !gunSoundPlayed) {
            SoundAndVibro.playSound(1);//reload sound
            gunSoundPlayed = true;
        }

        if (gunSwitchInProcess && GameScene.gameTimeUnpaused - (long) switchingGunTimePassed >= (long) (timeToSwitchWeapon / 2) && !gunSoundPlayed) {
            SoundAndVibro.playSound(2);//unholster sound
            gunSoundPlayed = true;
        }

        if (GameScene.currentGameState == 2) {
            updatePlayerLevel();
            botUnderCursor = GameScene.processAim();
            SoundAndVibro.stopTooLongVibro();
            NPCShootPlayer();
        }
    }

    public static void takeGunInHands(byte gun) {
        if (!gunReloadInProcess) {
            gunSwitchInProcess = true;
            switchingGunTimePassed = (int) GameScene.gameTimeUnpaused;
            EncasedWeapon = playerActiveWeapon;
            playerActiveWeapon = gun;
            gunSoundPlayed = false;
            if (OpticalSight) {
                OpticalSight = false;
                GameScene.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
            }
        }
    }

    private static void TakeUpGun() {
        short var0;
        if (playerActiveWeapon == 0) //Если в руках ничего
        {
            var0 = equipmentSlots[0]; //Ячейке присваивается значение слота основного оружия
        } else {
            var0 = FORT_GUN; //Если ничего нет - присвоить значение пистолета
        }

        useItem(var0);
        OpticalSight = false;
        GameScene.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
    }

    private static void reloadGun(byte gunNumber) {
        if (gunNumber != 0) {
            if (!gunSwitchInProcess) {
                OpticalSight = false;
                GameScene.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
                switch (gunNumber) {
                    case 1:
                        if (!searchItem((short) AK74_MAG)) {
                            return;
                        }

                        useItem((short) AK74_MAG);
                        break;
                    case 2:
                        if (!searchItem((short) OC14_MAG)) {
                            return;
                        }

                        useItem((short) OC14_MAG);
                        break;
                    case 3:
                        if (!searchItem((short) L85_MAG)) {
                            return;
                        }

                        useItem((short) L85_MAG);
                }

                gunSoundPlayed = false;
                gunReloadInProcess = true;
                reloadingTimePassed = (int) GameScene.gameTimeUnpaused;
            }
        }
    }

    private static boolean tryDropOrPickupItem(int item, boolean b) {//true для попытки поднять
        short temporaryWeight = playerWeight;
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
            case SMALL_MED:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
                break;
            case ARMY_MED:
                playerWeight = (short) (playerWeight + 10 * coefWeight);
                break;
            case ANTIRAD:
                playerWeight = (short) (playerWeight + 3 * coefWeight);
                break;
            case 104:
                playerWeight = (short) (playerWeight + 4 * coefWeight);
                break;
            case 105:
                playerWeight = (short) (playerWeight + 12 * coefWeight);
                break;
            case 106:
                playerWeight = (short) (playerWeight + 3 * coefWeight);
                break;
            case AK74_MAG:
                playerWeight = (short) (playerWeight + 3 * coefWeight);
                break;
            case OC14_MAG:
                playerWeight = (short) (playerWeight + 4 * coefWeight);
                break;
            case L85_MAG:
                playerWeight = (short) (playerWeight + 3 * coefWeight);
                break;
            case LJAC_ARM:
                playerWeight = (short) (playerWeight + 30 * coefWeight);
                break;
            case MJAC_ARM:
                playerWeight = (short) (playerWeight + 45 * coefWeight);
                break;
            case MERC_ARM:
                playerWeight = (short) (playerWeight + 70 * coefWeight);
                break;
            case SEVA_ARM:
                playerWeight = (short) (playerWeight + 50 * coefWeight);
                break;
            case STAL_ARM:
                playerWeight = (short) (playerWeight + 110 * coefWeight);
                break;
            case SPP9_ARM:
                playerWeight = (short) (playerWeight + 80 * coefWeight);
                break;
            case FORT_GUN:
                playerWeight = (short) (playerWeight + 10 * coefWeight);
                break;
            case AK74_GUN:
                playerWeight = (short) (playerWeight + 36 * coefWeight);
                break;
            case OC14_GUN:
                playerWeight = (short) (playerWeight + 25 * coefWeight);
                break;
            case L85_GUN:
                playerWeight = (short) (playerWeight + 50 * coefWeight);
                break;
            case MEDU_ART:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
                break;
            case BALL_ART:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
                break;
            case CRYS_ART:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
                break;
            case SPAR_ART:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
                break;
            case MOON_ART:
                playerWeight = (short) (playerWeight + 0 * coefWeight);
                break;
            case BATT_ART:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
                break;
            case DUMM_ART:
                playerWeight = (short) (playerWeight + 5 * coefWeight);
            case 300:
                playerWeight = (short) (playerWeight + 30 * coefWeight);
        }

        if (playerWeight > playerMaxWeight) //Если вес предмета превышает максимально возможный вес
        {
            playerWeight = temporaryWeight; //Вернуть всё как было и выкинуть предмет
            return false;
        } else {
            return true; //Иначе взять его
        }
    }

    private static boolean tryTrade(short item, boolean b) {//true для покупки
        short temporaryMoney = playerMoney;
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
            case SMALL_MED:
                playerMoney = (short) (playerMoney + 100 * t / 100);
                break;
            case ARMY_MED:
                playerMoney = (short) (playerMoney + 250 * t / 100);
                break;
            case ANTIRAD:
                playerMoney = (short) (playerMoney + 250 * t / 100);
                break;
            case 104:
                playerMoney = (short) (playerMoney + 50 * t / 100);
                break;
            case 105:
                playerMoney = (short) (playerMoney + 350 * t / 100);
                break;
            case 106:
                playerMoney = (short) (playerMoney + 0 * t / 100);
                break;
            case AK74_MAG:
                playerMoney = (short) (playerMoney + 60 * t / 100);
                break;
            case OC14_MAG:
                playerMoney = (short) (playerMoney + 50 * t / 100);
                break;
            case L85_MAG:
                playerMoney = (short) (playerMoney + 140 * t / 100);
                break;
            case LJAC_ARM:
                playerMoney = (short) (playerMoney + armorLeatherJacketStats[3] * t / 100);
                break;
            case MJAC_ARM:
                playerMoney = (short) (playerMoney + armorMailJacketStats[3] * t / 100);
                break;
            case MERC_ARM:
                playerMoney = (short) (playerMoney + armorMercSuitStats[3] * t / 100);
                break;
            case SEVA_ARM:
                playerMoney = (short) (playerMoney + armorSevaSuitStats[3] * t / 100);
                break;
            case STAL_ARM:
                playerMoney = (short) (playerMoney + armorStalkerSuitStats[3] * t / 100);
                break;
            case SPP9_ARM:
                playerMoney = (short) (playerMoney + armorSPP9mStats[3] * t / 100);
                break;
            case FORT_GUN:
                playerMoney = (short) (playerMoney + 0 * t / 100);
                break;
            case AK74_GUN:
                playerMoney = (short) (playerMoney + 1500 * t / 100);
                break;
            case OC14_GUN:
                playerMoney = (short) (playerMoney + 1000 * t / 100);
                break;
            case L85_GUN:
                playerMoney = (short) (playerMoney + 2000 * t / 100);
                break;
            case MEDU_ART:
                playerMoney = (short) (playerMoney + 400 * t / 100);
                break;
            case BALL_ART:
                playerMoney = (short) (playerMoney + 500 * t / 100);
                break;
            case CRYS_ART:
                playerMoney = (short) (playerMoney + 750 * t / 100);
                break;
            case SPAR_ART:
                playerMoney = (short) (playerMoney + 450 * t / 100);
                break;
            case MOON_ART:
                playerMoney = (short) (playerMoney + 600 * t / 100);
                break;
            case BATT_ART:
                playerMoney = (short) (playerMoney + 800 * t / 100);
                break;
            case DUMM_ART:
                playerMoney = (short) (playerMoney + 500 * t / 100);
            case 300:
                playerMoney = (short) (playerMoney + 1000 * t / 100);
        }

        if (t < 0) //Если мы что-то покупаем
        {
            if (playerMoney < 0) //Но денег не хватает
            {
                playerMoney = temporaryMoney; //Отменить сделку
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
    private static void addToEquipmentSlot(int item) {
        //Экипировка брони, основного и доп. оружия
        if (item <= L85_GUN) {
            switch (item) {
                //Броня
                case LJAC_ARM: //Кожаная куртка
                case MJAC_ARM: //Кольчужная куртка
                case MERC_ARM: //Комбез наёмника
                case SEVA_ARM: //СЕВА
                case STAL_ARM: //Комбинезон сталкера
                case SPP9_ARM: //СПП-9м.
                    equipmentSlots[2] = (short) item;
                    break;
                //Доп.оружие (пистолет)
                case FORT_GUN: 
                    equipmentSlots[1] = (short) item;
                    break;
                //Основное оружие
                case AK74_GUN: //Калаш
                case OC14_GUN: //Гроза
                case L85_GUN: //Энфилд
                    equipmentSlots[0] = (short) item;
            }
        } 
        //Экипировка артефактов
        else 
         if (item >= MEDU_ART && item <= 130) {
                for (byte a = 3; a < 7; ++a) {
                    //Поставить в пустой слот
                    if (equipmentSlots[a] == -1) {
                        equipmentSlots[a] = (short) item;
                        return;
                    }
                }
            }
    }

    public static boolean isItemEquipped(short item) {//Надет ли предмет на персонажа
        return GameScene.isItemInList(item, equipmentSlots);
    }

    public static void addItemToInventory(int item) {
        if (item != 0 && item != 106) //Если предмет не равен нулю или если это не магазин для форта
        {
            if (item == 200) {
                ItemWalkieTalkieInInventaryBool = true;
            } else if (item >= 127 && item <= 129) {
                for (byte var1 = 0; var1 < locationInventoryItems.length; ++var1) {
                    if (locationInventoryItems[var1] == -1) {
                        locationInventoryItems[var1] = (short) item;
                        return;
                    }
                }
            } else if (tryDropOrPickupItem(item, true)) //Если вещь помещается в рюкзаке
            {
                if (item >= AK74_GUN && item <= L85_GUN) {
                    switch (item) {
                        case AK74_GUN:
                            addItemToInventory((short) AK74_MAG);//магазин калаша
                            useItem((short) AK74_MAG);
                            break;
                        case OC14_GUN:
                            addItemToInventory((short) OC14_MAG);//магазин грозы
                            useItem((short) OC14_MAG);
                            break;
                        case L85_GUN:
                            addItemToInventory((short) L85_MAG);//магазин энфилда
                            useItem((short) L85_MAG);
                    }
                }

                inventoryItems[inventoryItemsCount] = (short) item;
                ++inventoryItemsCount;
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

    public static void useItem(int i) {
        //bugfix коэффициенты брони неправильно считаются
        //Если надевается броня
        if(i>L85_MAG&&i<FORT_GUN)
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
                if (GameScene.currentLocation == 10) {
                    ItemWalkieTalkieInInventaryBool = true;
                    dropItem(i);
                } else {
                    playerCanLeaveLevel = true;
                    dropItem(i);
                }
                break;
            case SMALL_MED: //Использование полевой аптечки
                playerHealth = (short) Math.min(playerHealth + 25, playerMaxHealth);
                dropItem(i);
                break;
            case ARMY_MED: //Использование военной аптечки
                playerHealth = (short) Math.min(playerHealth + 75, playerMaxHealth);
                dropItem(i);
                break;
            case ANTIRAD:
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
            case AK74_MAG: //Смена рожка у АК
                if (searchItem((short) AK74_GUN)) {
                    playerWeaponsAmmo[1] = 30;
                    dropItem(i);
                }
                break;
            case OC14_MAG: //Смена рожка у Грозы
                if (searchItem((short) OC14_GUN)) {
                    playerWeaponsAmmo[2] = 20;
                    dropItem(i);
                }
                break;
            case L85_MAG: //Смена рожка у Энфилда
                if (searchItem((short) L85_GUN)) {
                    playerWeaponsAmmo[3] = 25;
                    dropItem(i);
                }
                break;
            case LJAC_ARM:
                changePlayerStatsByItem(i, 1);
                break;
            case MJAC_ARM:
                changePlayerStatsByItem(i, 1);
                break;
            case MERC_ARM:
                changePlayerStatsByItem(i, 1);
                break;
            case SEVA_ARM:
                changePlayerStatsByItem(i, 1);
                break;
            case STAL_ARM:
                changePlayerStatsByItem(i, 1);
                break;
            case SPP9_ARM:
                changePlayerStatsByItem(i, 1);
                break;
            case FORT_GUN: //Экипировка пистолета форт
                takeGunInHands((byte) (i - FORT_GUN));
                break;
            case AK74_GUN: //Экипировка АК
                takeGunInHands((byte) (i - FORT_GUN));
                if (searchItem((short) AK74_MAG)) //Использовать рожок, если есть
                {
                    useItem((short) AK74_MAG);
                }
                break;
            case OC14_GUN: //Экипировка Грозы
                takeGunInHands((byte) (i - FORT_GUN));
                if (searchItem((short) OC14_MAG)) {
                    useItem((short) OC14_MAG);
                }
                break;
            case L85_GUN: //Экипировка Энфилда
                takeGunInHands((byte) (i - FORT_GUN)); //Взять это оружие в руки
                if (searchItem((short) L85_MAG)) {
                    useItem((short) L85_MAG);
                }
                break;
            case MEDU_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case BALL_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case CRYS_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case SPAR_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case MOON_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case BATT_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case DUMM_ART:
                changePlayerStatsByItem(i, 1);
                break;
            case 140:
                playerMoney = (short) (playerMoney + 100);
                MoneyTaken = 100;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 141:
                MoneyTaken = 250;
                playerMoney = (short) (playerMoney + 250);
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 142:
                MoneyTaken = 500;
                playerMoney = (short) (playerMoney + 500);
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 143:
                playerMoney = (short) (playerMoney + 1000);
                MoneyTaken = 1000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 144:
                playerMoney = (short) (playerMoney + 2000);
                MoneyTaken = 2000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 145:
                playerMoney = (short) (playerMoney + 5000);
                MoneyTaken = 5000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 146:
                playerMoney = (short) (playerMoney + 25);
                MoneyTaken = 25;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 147:
                playerMoney = (short) (playerMoney + 50);
                MoneyTaken = 50;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 148:
                playerMoney = (short) (playerMoney + 75);
                MoneyTaken = 75;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 300: //засланный казачок
                playerMoney = (short) (playerMoney + 10000);
                MoneyTaken = 10000;
                PlayerHUD.allowShowingTakenMoney();
                dropItem(i);
                break;
            case 200: //Рация
                if (ItemWalkieTalkieInInventaryBool) {
                    startDialog((short) 21, (byte) 0);
                }
        }

        limitPlayerStats();
    }

    //Экипировка брони и артефактов
    private static void changePlayerStatsByItem(int item, int multip) {
        switch (item) {
            //  БРОНЯ
            case LJAC_ARM:
                setPlayerResistances(armorLeatherJacketStats[0], armorLeatherJacketStats[1], armorLeatherJacketStats[2], multip);
                return;
            case MJAC_ARM:
                setPlayerResistances(armorMailJacketStats[0], armorMailJacketStats[1], armorMailJacketStats[2], multip);
                return;
            case MERC_ARM:
                setPlayerResistances(armorMercSuitStats[0], armorMercSuitStats[1], armorMercSuitStats[2], multip);
                return;
            case SEVA_ARM:
                setPlayerResistances(armorSevaSuitStats[0], armorSevaSuitStats[1], armorSevaSuitStats[2], multip);
                return;
            case STAL_ARM:
                setPlayerResistances(armorStalkerSuitStats[0], armorStalkerSuitStats[1], armorStalkerSuitStats[2], multip);
                return;
            case SPP9_ARM:
                setPlayerResistances(armorSPP9mStats[0], armorSPP9mStats[1], armorSPP9mStats[2], multip);
                return;
            //  АРТЕФАКТЫ
            //Медуза
            case MEDU_ART:
                setPlayerResistances((short) 5, (short) -10, (short) 0, multip);
                return;
            case BALL_ART:
                setPlayerResistances((short) 10, (short) 0, (short) 0, multip);
                playerMaxWeight = (short) (playerMaxWeight - 10 * multip * 10);
                return;
            case CRYS_ART:
                setPlayerResistances((short) -5, (short) 0, (short) 0, multip);
                playerMaxWeight = (short) (playerMaxWeight + 15 * multip * 10);
                return;
            case SPAR_ART:
                playerMaxHealth = (short) (playerMaxHealth + 50 * multip);
                playerAccuracy = (byte) (playerAccuracy - 10 * multip);
                return;
            case MOON_ART:
                playerAccuracy = (byte) (playerAccuracy + 6 * multip);
                playerMaxWeight = (short) (playerMaxWeight - 15 * multip * 10);
                setPlayerResistances((short) 0, (short) -5, (short) 0, multip);
                return;
            case BATT_ART:
                setPlayerResistances((short) 0, (short) 10, (short) -60, multip);
                playerMaxWeight = (short) (playerMaxWeight + 10 * multip * 10);
                playerMaxHealth = (short) (playerMaxHealth + 20 * multip);
                return;
            case DUMM_ART:
                setPlayerResistances((short) 0, (short) 0, (short) 15, multip);
            case FORT_GUN:
            case AK74_GUN:
            case OC14_GUN:
            case L85_GUN:
            default:
        }
    }

    public static void unequipItem(short item) {
        if (item != FORT_GUN) {
            deleteFromEquipmentSlot(item);
            switch (item) {
                case ANTIRAD:
                    IsAntiradIsUsed = false;
                case 104:
                case 105:
                case 106:
                case AK74_MAG:
                case OC14_MAG:
                case L85_MAG:
                case FORT_GUN:
                default:
                    break;
                case LJAC_ARM:
                    changePlayerStatsByItem(item, -1);
                    break;
                case MJAC_ARM:
                    changePlayerStatsByItem(item, -1);
                    break;
                case MERC_ARM:
                    changePlayerStatsByItem(item, -1);
                    break;
                case SEVA_ARM:
                    changePlayerStatsByItem(item, -1);
                    break;
                case STAL_ARM:
                    changePlayerStatsByItem(item, -1);
                    break;
                case SPP9_ARM:
                    changePlayerStatsByItem(item, -1);
                    break;
                case AK74_GUN:
                case OC14_GUN:
                case L85_GUN:
                    useItem((short) FORT_GUN);
                    break;
                case MEDU_ART:
                    changePlayerStatsByItem(item, -1);
                    break;
                case BALL_ART:
                    changePlayerStatsByItem(item, -1);
                    break;
                case CRYS_ART:
                    changePlayerStatsByItem(item, -1);
                    break;
                case SPAR_ART:
                    changePlayerStatsByItem(item, -1);
                    break;
                case MOON_ART:
                    changePlayerStatsByItem(item, -1);
                    break;
                case BATT_ART:
                    changePlayerStatsByItem(item, -1);
                    break;
                case DUMM_ART:
                    changePlayerStatsByItem(item, -1);
            }

            limitPlayerStats();
        }
    }

    public static void buyItem(short item) {
        if (tryTrade(item, true)) {
            addItemToInventory(item);
        }
    }

    public static void sellItem(short item) {
        tryTrade(item, false);
        if (isItemEquipped(item)) {
            unequipItem(item);
        }

        dropItem(item);
    }

    private static void limitPlayerStats() {
        playerHealth = (short) Math.min(playerHealth, playerMaxHealth);
        playerAccuracy = (byte) Math.max(0, playerAccuracy);
        playerMaxWeight = (short) Math.max(0, playerMaxWeight);
        playerBulletProtection = (byte) Math.max(0, playerBulletProtection);
        playerAnomalyResistance = (byte) Math.max(0, playerAnomalyResistance);
        playerRadiationResistance = (byte) Math.max(0, playerRadiationResistance);
    }

    public static void dropItem(int item) {
        if (item != FORT_GUN) //Любой предмет кроме пистолета форт
        {
            short var1 = 0;

            short var2; //Номер ячейки инвентаря
            for (var2 = 1; var2 < inventoryItemsCount; ++var2) //Определение номера ячейки с нужным предметом
            {
                if (inventoryItems[var2] == item) //Если предмет найден в массиве ячеек инвентаря
                {
                    var1 = var2;
                    break;
                }
            }

            if (var1 > 0) {
                for (var2 = var1; var2 < inventoryItemsCount - 1; ++var2) { //Присвоить ячейке значение следующей ячейки инвентаря
                    inventoryItems[var2] = inventoryItems[var2 + 1];
                    inventoryItems[var2 + 1] = -1;
                } //Сделать следующую ячейку инвентаря пустой

                --inventoryItemsCount; //Уменьшить число предметов в инвентаре на единицу
                tryDropOrPickupItem(item, false); //Уменьшить вес инвентаря на вес этого предмета
            }
        }
    }

    private static boolean searchItem(short var0) //Поиск предмета в инвентаре
    {
        for (int var1 = 0; var1 < inventoryItemsCount; ++var1) {
            if (var0 == inventoryItems[var1]) {
                return true; //Вернуть истину если найден
            }
        }
        return false;
    }

    private static void openDoor(int doorId) {
        if (GameScene.dyingBotId == -1) {
            GameScene.sub_c4f(doorId);
        }
    }

    public static void giveItemsForKillingAll() {
        for (int i = 0; i < GameScene.itemsForKillingAllCount[GameScene.currentRoom]; i++) {
            short itemId = GameScene.itemsForKillingAll[GameScene.currentRoom][i];
            if (itemId != 0) {
                addItemToInventory(itemId);
                GameScene.itemsForKillingAll[GameScene.currentRoom][i] = 0;
            }
        }

    }

    private static void killBot() {
        GameScene.botKilled[GameScene.currentRoom][GameScene.botIdUndercursor] = true;
        GameScene.botsKilledCount++;
		
        GameScene.playBotDieAnimation(GameScene.botIdUndercursor);
        GameScene.sub_daf();
		
        switch (Bot.getBotType(GameScene.botIdUndercursor)) {
            case 0:
                ++playerExp;
                break;
            case 1:
                playerExp = (short) (playerExp + 3);
                break;
            case 2:
                playerExp = (short) (playerExp + 0);
                break;
            case 3:
                playerExp = (short) (playerExp + 0);
                break;
            case 4:
                playerExp = (short) (playerExp + 5);
                break;
            case 5:
                playerExp = (short) (playerExp + 7);
                break;
            case 6:
                playerExp = (short) (playerExp + 10);
                break;
            case 7:
                playerExp = (short) (playerExp + 8);
                break;
            case 8:
                playerExp = (short) (playerExp + 10);
                break;
            case 9:
                playerExp = (short) (playerExp + 12);
                break;
            case 10:
                playerExp = (short) (playerExp + 0);
                break;
            case 11:
                playerExp = (short) (playerExp + 14);
                break;
            case 12:
                playerExp = (short) (playerExp + 16);
        }

        if (GameScene.botsCount[GameScene.currentRoom] - GameScene.botsKilledCount == 0) {
            giveItemsForKillingAll();
        }

    }

    private static void updatePlayerLevel() {
        if (playerExp >= 150 && playerExp < 210) {
            playerLevel = (short) Math.max(2, playerLevel);
        } else if (playerExp >= 210 && playerExp < 290) {
            playerLevel = (short) Math.max(3, playerLevel);
        } else if (playerExp >= 290 && playerExp < 390) {
            playerLevel = (short) Math.max(4, playerLevel);
        } else if (playerExp >= 390 && playerExp < 530) {
            playerLevel = (short) Math.max(5, playerLevel);
        } else if (playerExp >= 530 && playerExp < 670) {
            playerLevel = (short) Math.max(6, playerLevel);
        } else if (playerExp >= 670 && playerExp < 810) {
            playerLevel = (short) Math.max(7, playerLevel);
        } else if (playerExp >= 810 && playerExp < 1010) {
            playerLevel = (short) Math.max(8, playerLevel);
        } else if (playerExp >= 1010 && playerExp < 1230) {
            playerLevel = (short) Math.max(9, playerLevel);
        } else if (playerExp >= 1230) {
            playerLevel = (short) Math.min(10, playerLevel);
        }
    }

    private static void shoot(byte gun) {
        if (!gunSwitchInProcess && !gunReloadInProcess) {
            if (playerWeaponsAmmo[gun] > 0 || gun == 0) {
                weaponFireflareHidden = true;
                GameScene.shootStartTime = (int) GameScene.gameTimeUnpaused;
                GameScene.shootShakeActive = true;
                //restore sounds
                int soundId = 4;
                //restore sounds
                switch (gun) {
                    case 0: //Если в руках форт
                        //restore sounds
                        soundId = 5;
                        //restore sounds
                        playerAccuracy = 60; //Точность персонажа 60%
                        --playerWeaponsAmmo[0]; //Убавить боезапас на единицу
                        if (playerWeaponsAmmo[0] < 1) //Если патронов в магазине нет
                        {
                            playerWeaponsAmmo[0] = 12; //Сразу перезарядить
                        }
                        break;
                    case 1:
                        playerAccuracy = 40;
                        --playerWeaponsAmmo[1];
                        break;
                    case 2:
                        playerAccuracy = 50;
                        --playerWeaponsAmmo[2];
                        break;
                    case 3:
                        playerAccuracy = 60;
                        --playerWeaponsAmmo[3];
                }

                //restore sounds
                SoundAndVibro.playSound(soundId);
                //restore sounds
                if (botUnderCursor && MathUtils.getRandomNumber(100) <= playerAccuracy) //Если ты всё-таки попал 
                {
                    killBot();
                }

            }
        }
    }

    /**Нанести повреждение персонажу*/
    public static void damageToPlayer(int value)
    {
        botHitPlayerBefore = true;
        PlayerHUD.playerDamaged = true;
        PlayerHUD.timeToDisplayDamageIndicator = (int) GameScene.gameTimeUnpaused + 500;
        playerHealth = (short) (playerHealth - value);
        //restore sounds
        if (!GameScene.damageEffect)
        {
            SoundAndVibro.playSound(3);
        }
        //restore sounds
        
        SoundAndVibro.vibrate(200);
    }

    public static void openPDA() {
        GameScene.setDialogWindowState((short) 5);
        finishCurrentTask();
    }

    private static void playerControlCheck() {
        if (GameScene.currentGameState != 1 && GameScene.currentGameState != 4 && GameScene.currentGameState != 11 && !endingCutscene && GameScene.currentGameState != 13 && GameScene.currentGameState != 0 && GameScene.currentGameState != -2) {
            switch (GameScene.currentGameState) {
                case 2:
                    if (GameScene.gamePaused) {
                        return;
                    }

                    Keys.leftAccelerate = System.currentTimeMillis() - Keys.leftOr4PressTime >= 200L && Keys.leftOr4PressTime != -1L;
                    Keys.rightAccelerate = System.currentTimeMillis() - Keys.righOr6PressTime >= 200L && Keys.righOr6PressTime != -1L;
                    Keys.downAccelerate = System.currentTimeMillis() - Keys.downOr8PressTime >= 200L && Keys.downOr8PressTime != -1L;
                    Keys.upAccelerate = System.currentTimeMillis() - Keys.upOr2PressTime >= 200L && Keys.upOr2PressTime != -1L;
                    if (Keys.fire || Keys.num5) {
                        Keys.fire = false;
                        Keys.num5 = false;
                        if (GameScene.getOpenDoorIdUnderCursor()) {
                            openDoor(GameScene.openDoorIdUnderCursor);
                        } else if (!GameScene.getActiveObjectIdUnderCursor()) {
                            shoot(playerActiveWeapon);
                        } else {
                            interactionWith(GameScene.activableObjIdUnderCursor);
                        }
                    }

                    if (Keys.left || Keys.num4) {
                        GameScene.TurnLeftTheCamera();
                    }

                    if (Keys.right || Keys.num6) {
                        GameScene.TurnRightTheCamera();
                    }

                    if (Keys.up || Keys.num2) {
                        GameScene.RaiseTheCamera();
                    }

                    if (Keys.down || Keys.num8) {
                        GameScene.LowerTheCamera();
                    }

                    if (Keys.num1) {
                        reloadGun(playerActiveWeapon);
                        Keys.num1 = false;
                    }

                    if (Keys.pound) {
                        Keys.pound = false;
                        if (playerActiveWeapon == 3) //Если в руках в текущий момент Энфилд
                        {
                            OpticalSight = !OpticalSight; //9.01.25 перенёс фов в GameScene
                            /*if (OpticalSight) {
                             * GameScene.camera.setPerspective(30.0F, (float) PlayerHUD.crosshairImages[2].getWidth() / (float) PlayerHUD.crosshairImages[2].getHeight(), 0.1F, 10000.0F);
                             * } else {
                             * GameScene.camera.setPerspective(50.0F, (float) MainMenuScreen.scrWidth / (float) MainMenuScreen.scrHeight, 0.1F, 10000.0F);
                             * }*/
                        }
                    }

                    if (Keys.num3) //Если нажата клавиша 3
                    {
                        Keys.num3 = false;
                        if (searchItem((short) SMALL_MED)) {
                            useItem((short) SMALL_MED);
                        } else if (searchItem((short) ARMY_MED)) {
                            useItem((short) ARMY_MED);
                        }
                    }

                    if (Keys.num0) {
                        Keys.num0 = false;
                        openDoor(GameScene.currentDoorId);
                    }

                    if (Keys.rightSoft) {
                        if (OpticalSight) {
                            OpticalSight = false;
                        }

                        Keys.rightSoft = false;
                        if (ItemWalkieTalkieInInventaryBool) {
                            useItem((short) 200);
                        } else {
                            if (playerCanLeaveLevel) {
                                openPDA();
                                return;
                            }

                            TakeUpGun();
                        }
                    }

                    if (Keys.leftSoft) {
                        Keys.leftSoft = false;
                        GameScene.gamePaused = true;
                        setCurrentQuestsDescriptions();
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
                        ResourceManager.runGarbageCollector();
                        GameScene.setDialogWindowState((short) 2);//закрыть диалоговое окно
                        GameScene.showIntro = false;
                        PlayerHUD.introRolledFully = false;
                    }
            }

        }
    }

    private static void addMarkToPDA(byte var0) {
        playerCanLeaveLevel = true;
        GameScene.locationTaskMark[var0] = true;
    }

    public static byte getLocationNameId(byte var0) {
        return GameScene.sub_165(var0, StoryLocationMassive);
    }

    private static void setCurrentQuestsDescriptions() {
        questLocationDescriptionId = new int[13];
        questLocationNumber = new byte[13];
        allQuestsNumber = 0;

        for (byte location = 1; location < 17; ++location) {
            byte i;
            if (checkLocationAvailability(location) && (i = getLocationNameId(location)) != -1) {
                questLocationDescriptionId[allQuestsNumber] = (short) (i + 354);
                questLocationNumber[allQuestsNumber] = location;
                ++allQuestsNumber;
            }
        }

    }

    /**Если локация не пройдена и локация есть в списке заданий или лагерей,
     * а также если локация отличается от текущей*/
    public static boolean checkLocationAvailability(int location) {
        return !GameScene.locationCompleted[location] && (GameScene.locationCampMark[location] || GameScene.locationTaskMark[location]) || GameScene.currentLocation == location;
    }

    private static void finishCurrentTask() {
        PlayerHUD.textLinesStartsEnds = TextCreator.splitOnLines(getLocationNameId((byte) GameScene.currentLocation) + 354, PlayerHUD.TEXT_TARGET_WIDTH, 0);
        GameScene.nextLocation = GameScene.currentLocation;
        if (playerCanLeaveLevel) {
            GameScene.locationCampMark[GameScene.currentLocation] = GameScene.currentLocation == 1 || GameScene.currentLocation == 6 || GameScene.currentLocation == 12;
            GameScene.locationCompleted[GameScene.currentLocation] = !GameScene.locationCampMark[GameScene.currentLocation];
            if (GameScene.currentLocation == StoryLocationMassive[0]) {
                rustyDialogState = 3;
                playerExp = (short) (playerExp + 20);
            }

            if (GameScene.currentLocation == StoryLocationMassive[1]) {
                rustyDialogState = 5;
                playerExp = (short) (playerExp + 90);
            }

            if (GameScene.currentLocation == StoryLocationMassive[2]) //Задание Галоши
            {
                galoshQuestState = 1;
                playerExp = (short) (playerExp + 40);
            }

            if (GameScene.currentLocation == StoryLocationMassive[3]) {
                playerExp = (short) (playerExp + 110);
            }

            if (GameScene.currentLocation == StoryLocationMassive[5]) {
                haryaDialogState = 2;
                playerExp = (short) (playerExp + 25);
            }

            if (GameScene.currentLocation == StoryLocationMassive[6]) {
                batyaDialogState = 2;
                playerExp = (short) (playerExp + 155);
            }

            if (GameScene.currentLocation == StoryLocationMassive[7]) {
                playerExp = (short) (playerExp + 250);
            }

            if (GameScene.currentLocation == StoryLocationMassive[9]) {
                batyaDialogState = 4;
                playerExp = (short) (playerExp + 32);
            }

            if (GameScene.currentLocation == StoryLocationMassive[10]) {
                belomorDialogState = 2;
                playerExp = (short) (playerExp + 135);
            }

            if (GameScene.currentLocation == StoryLocationMassive[11]) {
                shlangDialogState = 2;
                playerExp = (short) (playerExp + 330);
            }

            if (GameScene.currentLocation == StoryLocationMassive[12]) {
                playerExp = (short) (playerExp + 375);
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

            if (GameScene.nextLocation == GameScene.currentLocation && !GameScene.notInRoom) { //Если локация перехода равна прежней, вернуться
                GameScene.setDialogWindowState(GameScene.prevGameState);
                GameScene.gamePaused = false;
                return;
            }

            if (!checkLocationAvailability(GameScene.nextLocation)) {
                return;
            }

            PlayerHUD.ActivateTransitAnimation();
            GameScene.currentRoomPdawtf = GameScene.currentRoom;
            GameScene.currentLocation = GameScene.nextLocation;
        }

        if (Keys.left || Keys.up || Keys.num2 || Keys.num4) { //Нажать клавишу влево или вверх
            Keys.num4 = false;
            Keys.num2 = false;
            Keys.up = false;
            Keys.left = false;
            if (checkIfAnyLocationAvailable()) {
                while (true) {
                    ++GameScene.nextLocation;
                    if (GameScene.nextLocation > 16) {
                        GameScene.nextLocation = 1;
                    }

                    if (checkLocationAvailability(GameScene.nextLocation)) {
                        break;
                    }

                    if (GameScene.nextLocation == 16) {
                        GameScene.nextLocation = 0;
                    }
                }
            }

            PlayerHUD.textLinesStartsEnds = TextCreator.splitOnLines(getLocationNameId((byte) GameScene.nextLocation) + 354, PlayerHUD.TEXT_TARGET_WIDTH, 0);
        }

        if (Keys.right || Keys.down || Keys.num8 || Keys.num6) { //нажать клавишу вправо или вниз
            Keys.num6 = false;
            Keys.num8 = false;
            Keys.down = false;
            Keys.right = false;
            boolean var0 = GameScene.currentLocation == 0;
            if (checkIfAnyLocationAvailable()) {
                do {
                    --GameScene.nextLocation;
                    byte var1 = (byte) (var0 ? 0 : 1);
                    if (GameScene.nextLocation < var1) {
                        GameScene.nextLocation = 16;
                    }
                } while ((!var0 || GameScene.nextLocation != 0) && !checkLocationAvailability(GameScene.nextLocation));
            }

            PlayerHUD.textLinesStartsEnds = TextCreator.splitOnLines(getLocationNameId((byte) GameScene.nextLocation) + 354, PlayerHUD.TEXT_TARGET_WIDTH, 0);
        }

    }

    private static boolean checkIfAnyLocationAvailable() {
        for (byte location = 1; location < 17; ++location) {
            if (location != GameScene.currentLocation && 
                    checkLocationAvailability(location)) {
                return true;
            }
        }
        return false;
    }

    public static void startDialog(int dialogId, int activeObjId) {
        currentDialogId = (short) dialogId; //
        currentDialogActiveObjId = (byte) activeObjId; //
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
                        phracesIdArray = zaborBeforePhrId;
                        break;
                    case 1: //Если уже поговорили
                        phracesIdArray = zaborAfterPhrId;
                }

                dialogStructure = zaborDialogSctucture[zaborDialogState];
                break;
            case 6:
                switch (rustyDialogState) {
                    case 0:
                        phracesIdArray = rustyFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = rustySecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = rustyThirdPhrId;
                        break;
                    case 3:
                        phracesIdArray = rustyFourthPhrId;
                        break;
                    case 4:
                        phracesIdArray = rustyFifthPhrId;
                        break;
                    case 5:
                        phracesIdArray = rustySixthPhrId;
                        break;
                    case 6:
                        phracesIdArray = rustySeventhPhrId;
                }

                dialogStructure = rustyDialogStructure[rustyDialogState];
                break;
            case 7: //Миссия Галоша
                switch (galoshQuestState) {
                    case 0: //Миссия в подвешенном состоянии
                        phracesIdArray = galoshFirstIds; //Одному массиву присваивается другой
                        break;
                    case 1: //Выполненная миссия
                        phracesIdArray = galoshAfterQuestIds;
                }

                dialogStructure = galoshDialogStructure[galoshQuestState]; //В двойном массиве ячейке Галош присваивается значение единицы
                break;
            case 8:
                switch (krotDialogState) {
                    case 0:
                        phracesIdArray = krotPhrId;
                    default:
                        dialogStructure = krotDialogStruct[krotDialogState];
                        break label142;
                }
            case 9:
                switch (batyaDialogState) {
                    case 0:
                        phracesIdArray = batyaFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = batyaSecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = batyaThirdPhrId;
                        break;
                    case 3:
                        phracesIdArray = batyaFourthPhrId;
                        break;
                    case 4:
                        phracesIdArray = batyaFifthPhrId;
                }

                dialogStructure = batyaDialogStruct[batyaDialogState];
                break;
            case 10:
                switch (haryaDialogState) {
                    case 0:
                        phracesIdArray = haryaFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = haryaSecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = haryaThirdPhrId;
                        break;
                    case 3:
                        phracesIdArray = haryaFourthPhrId;
                }

                dialogStructure = haryaDialogStruct[haryaDialogState];
                break;
            case 11:
                switch (gutalinDialogState) {
                    case 0:
                        phracesIdArray = gutalinFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = gutalinSecondPhrId;
                }

                dialogStructure = gutalinDialogStruct[gutalinDialogState];
                break;
            case 12:
                switch (shlangDialogState) {
                    case -1:
                        return;
                    case 0:
                        phracesIdArray = shlangFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = shlangSecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = shlangThirdPhrId;
                }

                dialogStructure = shlangDialogStruct[shlangDialogState];
                break;
            case 13:
                switch (belomorDialogState) {
                    case 0:
                        phracesIdArray = belomorFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = belomorSecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = belomorThirdPhrId;
                        break;
                    case 3:
                        phracesIdArray = belomorFourthPhrId;
                }

                dialogStructure = belomorDialogStruct[belomorDialogState];
                break;
            case 14:
                switch (kaynazovskiDialogState) {
                    case 0:
                        phracesIdArray = kaynazovskiFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = kaynazovskiSecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = kaynazovskiThirdPhrId;
                }

                dialogStructure = kaynazovskiDialogStruct[kaynazovskiDialogState];
                break;
            case 15:
                switch (trusDialogState) {
                    case 0:
                        phracesIdArray = trusPhrId;
                    default:
                        dialogStructure = trusDialogStruct[trusDialogState];
                        break label142;
                }
            case 16:
                switch (svistunDialogState) {
                    case 0:
                        phracesIdArray = svistunPhrId;
                    default:
                        dialogStructure = svistunDialogStructure[svistunDialogState];
                        break label142;
                }
            case 17:
                switch (manikovskiDialogState) {
                    case 0:
                        phracesIdArray = manikovskiPhrId;
                    default:
                        dialogStructure = manikovskiDialogStruct[manikovskiDialogState];
                        break label142;
                }
            case 18:
                switch (commanderDialogState) {
                    case 0:
                        phracesIdArray = commanderPhrId;
                    default:
                        dialogStructure = commanderDialogStruct[commanderDialogState];
                        break label142;
                }
            case 19:
                switch (koboldDialogState) {
                    case 0:
                        phracesIdArray = koboldFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = koboldSecondPhrId;
                        break;
                    case 2:
                        phracesIdArray = koboldThirdPhrId;
                }

                dialogStructure = koboldDialogStruct[koboldDialogState];
                break;
            case 20:
                switch (prizrakDialogState) {
                    case 0:
                        phracesIdArray = prizrakPhrId;
                    default:
                        dialogStructure = prizrakDialogStruct[prizrakDialogState];
                        break label142;
                }
            case 21:
                switch (militaryDialogState) {
                    case 0:
                        phracesIdArray = militaryFirstPhrId;
                        break;
                    case 1:
                        phracesIdArray = militarySecondPhrId;
                }

                dialogStructure = militaryDialogStruct[militaryDialogState];
        }

        if (phracesIdArray != null) {
            PlayerHUD.splitNPCPhraseToLines();
            PlayerHUD.resetNPCPhraseOffsetByTime();
            GameScene.setDialogWindowState((short) 9);
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
                            playerExp = (short) (playerExp + 70);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1) //если выбран второй вариант ответа
                        {
                            GameScene.setDialogWindowState(GameScene.prevGameState);
                            return;
                        }
                    }
                }

                if (zaborDialogState == 1) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                }
                break;
            //Ржавый
            case 6:
                if (rustyDialogState == 0) {
                    if (rustyDialogStructure[rustyDialogState][givenAnswersCount] == 1) {
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
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                if (rustyDialogState == 3) {
                    if (rustyDialogStructure[rustyDialogState][givenAnswersCount] == 1) {
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
                            addItemToInventory((short) 200);
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
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        return;
                    }
                }

                if (rustyDialogState == 4) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                if (rustyDialogState == 5) {
                    if (givenAnswersCount == 0) {
                        if (selectedAnswer == 0 && svistunQuestCompleted) {
                            rustyDialogState = 6;
                            ++currentNpcPhrase;
                            addItemToInventory((short) 200);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1 && !svistunQuestCompleted) {
                            givenAnswersCount = (byte) (givenAnswersCount + 2);
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            return;
                        }
                    }

                    if (givenAnswersCount == 2) {
                        if (selectedAnswer == 0 && searchItem((short) MOON_ART)) {
                            rustyDialogState = 6;
                            if (isItemEquipped((short) MOON_ART)) {
                                unequipItem((short) MOON_ART);
                            }

                            dropItem((short) MOON_ART);
                            addItemToInventory((short) 200);
                            dialogCompleted = true;
                            ++currentNpcPhrase;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            rustyDialogState = 6;
                            addItemToInventory((short) 200);
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                            return;
                        }
                    }
                }

                if (rustyDialogState == 6) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                }
                break;
            //Галош
            case 7:
                if (galoshQuestState == 0) {
                    //А как насчёт артефактов? Получение квеста
                    if (selectedAnswer == 4) {
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                            GameScene.setDialogWindowState(GameScene.prevGameState);
                        }
                    } else if (givenAnswersCount == 1) {
                        if (selectedAnswer == 0) {
                            ++currentNpcPhrase;
                            playerMoney = (short) (playerMoney - 50);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            playerMoney = (short) (playerMoney - 50);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 2) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 3);
                            playerMoney = (short) (playerMoney - 50);
                            dialogCompleted = true;
                            return;
                        }

                        if (selectedAnswer == 3) {
                            GameScene.setDialogWindowState(GameScene.prevGameState);
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
                            if (playerMoney >= 500) {
                                currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                                dialogCompleted = true;
                                playerMoney = (short) (playerMoney - 500);
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
                        if (playerMoney >= 500) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            playerMoney = (short) (playerMoney - 500);
                            addMarkToPDA((byte) 9);
                            GameScene.locationCompleted[StoryLocationMassive[6]] = true;
                            batyaDialogState = 3;
                        }
                        break;
                    }

                    if (selectedAnswer == 1) {
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        return;
                    }
                }

                if (batyaDialogState == 2) {
                    if (selectedAnswer == 0) {
                        if (searchItem((short) BATT_ART)) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            if (isItemEquipped((short) BATT_ART)) {
                                unequipItem((short) BATT_ART);
                            }

                            dropItem((short) BATT_ART);
                            addMarkToPDA((byte) 9);
                            batyaDialogState = 3;
                        }
                        break;
                    }

                    if (selectedAnswer == 1) {
                        if (playerMoney >= 500) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                            playerMoney = (short) (playerMoney - 500);
                            addMarkToPDA((byte) 9);
                            batyaDialogState = 3;
                        }
                        break;
                    }

                    if (selectedAnswer == 2) {
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        return;
                    }
                }

                if (batyaDialogState == 3) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                if (batyaDialogState == 4) {
                    if (givenAnswersCount == 1) {
                        addMarkToPDA(StoryLocationMassive[8]);
                        GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                    addItemToInventory((short) MERC_ARM);
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                if (haryaDialogState == 3) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                }
                break;
            //Третий лагерь
            //Гуталин
            case 11:
                if (gutalinDialogState == 0) {
                    if (selectedAnswer == 0) {
                        if (playerMoney >= 600) {
                            ++currentNpcPhrase;
                            playerMoney = (short) (playerMoney - 600);
                            ++playerLevel;
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
                    GameScene.setDialogWindowState(GameScene.prevGameState);
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
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                if (shlangDialogState == 2) {
                    ++currentNpcPhrase;
                    dialogCompleted = true;
                    playerMoney = (short) (playerMoney + 2000);
                    GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
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
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        return;
                    }

                    if (selectedAnswer == 1) {
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        return;
                    }
                }

                if (belomorDialogState == 1 || belomorDialogState == 3) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                if (belomorDialogState == 2) {
                    if (givenAnswersCount == 1) {
                        belomorDialogState = 3;
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                        if (selectedAnswer == 0 && !capItemGot) {
                            return;
                        }

                        givenAnswersCount = (byte) (givenAnswersCount + 3);
                        currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                        return;
                    }

                    if (givenAnswersCount == 6) {
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        addMarkToPDA(StoryLocationMassive[12]);
                        kaynazovskiDialogState = 1;
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }

                if (kaynazovskiDialogState == 2) {
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                    koboldDialogState = 2;
                    addItemToInventory((short) 128);
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
                        GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                        GameScene.setDialogWindowState(GameScene.prevGameState);
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
                        if (selectedAnswer == 0 && (searchItem((short) SMALL_MED) || searchItem((short) ARMY_MED))) {
                            ++currentNpcPhrase;
                            dialogCompleted = true;
                            svistunQuestCompleted = true;
                            if (searchItem((short) SMALL_MED)) {
                                dropItem((short) SMALL_MED);
                            } else if (searchItem((short) ARMY_MED)) {
                                dropItem((short) ARMY_MED);
                            }

                            GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                            playerCanLeaveLevel = true;
                            return;
                        }

                        if (selectedAnswer == 1) {
                            currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                            dialogCompleted = true;
                            addItemToInventory((short) 141);
                            addItemToInventory((short) MOON_ART);
                            GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                            playerCanLeaveLevel = true;
                        }
                    }
                }
                break;
            //Маниковский, по заданию Кэпа
            case 17:
                GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                if (selectedAnswer == 0) {
                    capItemGot = true;
                    addItemToInventory((short) 200);
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                    return;
                }

                currentNpcPhrase = (byte) (currentNpcPhrase + 2);
                addMarkToPDA(StoryLocationMassive[8]);
                playerExp = (short) (playerExp + 400);
                dialogCompleted = true;
                return;
            //Кэп
            case 18:
                if (givenAnswersCount == 2) {
                    addMarkToPDA(StoryLocationMassive[7]);
                    GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                    GameScene.setDialogWindowState(GameScene.prevGameState);
                }

                ++currentNpcPhrase;
                ++givenAnswersCount;
                return;
            //Кобольд
            case 19:
                //Перед посещением третьего лагеря
                if (koboldDialogState == 0) {
                    if (givenAnswersCount == 1) {
                        GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                        GameScene.setDialogWindowState(GameScene.prevGameState);
                        addItemToInventory((short) 127);
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
                        GameScene.currentRoomPdawtf = GameScene.currentRoom;
                        GameScene.currentLocation = 16;
                        GameScene.locationCampMark[GameScene.currentLocation] = GameScene.currentLocation == 1 || GameScene.currentLocation == 6 || GameScene.currentLocation == 12;
                        GameScene.locationCompleted[GameScene.currentLocation] = !GameScene.locationCampMark[GameScene.currentLocation];
                        GameScene.setDialogWindowState((short) -2);
                        GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                        return;
                    }

                    ++givenAnswersCount;
                    ++currentNpcPhrase;
                }

                if (koboldDialogState == 2) {
                    GameScene.setDialogWindowState((short) 2);
                    GameScene.setActiveObjState(currentDialogActiveObjId, (short) 0);
                    endingCutscene = true;
                }
                break;
            //Призрак
            case 20:
                if (givenAnswersCount == 4) {
                    Main.main.numberOfPlayers = 0;
                    GameScene.endGame((short) 11, 5000);//финал игры?
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
                GameScene.setDialogWindowState(GameScene.prevGameState);//выполнить действие?
                return;
            }

            updateDialogState();
            PlayerHUD.splitNPCPhraseToLines();
            PlayerHUD.resetNPCPhraseOffsetByTime();
        }

    }

    private static void drawStashInterface() {
        Main.main.setScreen(AllScreens.masterInventory, (byte) 6);
        Main.main.repaint();
    }

    private static void drawTradeInterface() {
        Main.main.setScreen(AllScreens.masterInventory, (byte) 7);
        Main.main.repaint();
    }

    private static void interactionWith(int type) //Interaction - взаимодействие; 1 деньги, 2 тайник, 3 - торговец, 0 - диалог
    {
        openedActivableObjId = type;
        int number;
        if ((number = GameScene.getActivableObjState(type)) != 0 && number > 21) //что там находится?
        {
            if (number < 50 || number > 58) {
                if (number <= DUMM_ART && number != 100) {
                    if (number == 25) {
                        stashItems = new short[]{(short) DUMM_ART, (short) AK74_MAG};//Пустышка и магаз ак
                        drawStashInterface();
                        return;
                    }

                    if (number == 26) {
                        addItemToInventory((short) 127);//ключ от двери
                        addItemToInventory((short) 140);//деньги 100
                        GameScene.setActiveObjState(type, (short) 0);
                        return;
                    }

                    stashItems = new short[]{(short) number};
                    drawStashInterface();
                    return;
                }

                addItemToInventory(number);
                GameScene.setActiveObjState(type, (short) 0);
                return;
            }

            if (number == 50) {
                playerCanLeaveLevel = true;
                stashItems = new short[]{(short) 131};
                drawStashInterface();
            }

            if (number == 51) {
                addItemToInventory((short) 140);
                addItemToInventory((short) 128);
                GameScene.setActiveObjState(type, (short) 0);
            }

            if (number == 52) {
                addItemToInventory((short) 127);//ключ от двери
                stashItems = new short[]{(short) SPAR_ART};//вспышка
                drawStashInterface();
            }

            if (number == 53) {
                stashItems = new short[]{(short) ARMY_MED};
                drawStashInterface();
            }

            if (number == 54) {
                stashItems = new short[]{(short) OC14_GUN};
                drawStashInterface();
            }

            if (number == 55) {
                stashItems = new short[]{(short) SMALL_MED};
                drawStashInterface();
            }

            if (number == 56) {
                playerCanLeaveLevel = true;
                stashItems = new short[]{(short) BATT_ART, (short) ARMY_MED};
                drawStashInterface();
            }

            if (number == 57) {
                stashItems = new short[]{(short) AK74_GUN};
                drawStashInterface();
            }

            if (number == 58) {
                playerCanLeaveLevel = true;
                GameScene.setActiveObjState(type, (short) 0);
            }
        } else {
            if (number == 1) { //Ассортимент первого торговца в деревне
                traderItems = new short[] {
                    SMALL_MED, ANTIRAD, 
                    AK74_MAG, AK74_GUN, LJAC_ARM, MJAC_ARM};
                drawTradeInterface();
                return;
            }

            if (number == 2) {
                traderItems = new short[] {
                    SMALL_MED, ARMY_MED, ANTIRAD,  
                    AK74_MAG, AK74_GUN, LJAC_ARM, MJAC_ARM, MERC_ARM, 
                    OC14_GUN, OC14_MAG};
                drawTradeInterface();
                return;
            }

            if (number == 3) {
                traderItems = new short[] {
                    SMALL_MED, ARMY_MED, ANTIRAD, 
                    AK74_MAG, AK74_GUN, 
                    LJAC_ARM, MJAC_ARM, MERC_ARM, STAL_ARM,  
                    OC14_GUN, OC14_MAG, L85_GUN, L85_MAG};
                drawTradeInterface();
                return;
            }

            if (number != 0) {
                startDialog(number, type);
            }
        }

    }

}
