package code;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

public final class ResourseManager {

    public static DataInputStream DataInputStream_Object_D = new DataInputStream(Main.main.getClass().getResourceAsStream("/d"));
    public static Image[] interfaceImages = new Image[13];
    public static boolean logoImageLoaded = false;
    public static int var_e6;
    public static byte[] MassiveWithInterfaceImagesAdress;
    public static short[] MassiveXsrcOfInterfaceImage;
    public static short[] MassiveYsrcOfInterfaceImage;
    public static short[] MassiveWidthOfInterfaceImage;
    public static short[] MassiveHeightOfInterfaceImage;
    public static byte[] MassiveTransfOfInterfaceImage;
    public static short[] rectangleAdrForInterfaceMassive;
    public static short[] MassiveX_startOfInterfaceImage;
    public static short[] MassiveY_startOfInterfaceImage;
    public static short[] var_419;

    public static void saveGameSettings() {
        byte[] settings;
        (settings = new byte[3])[0] = (byte) (SoundAndVibro.soundsEnabled ? 1 : 0);
        settings[1] = (byte) (SoundAndVibro.vibroEnabled ? 1 : 0);
        settings[2] = Main.main.numberOfPlayers;
        recreateSettingsSaveFile(settings, "qpset");
        System.out.println("ResourceManager.saveSettings: settings saved");
    }

    public static void sub_4d() {//
        try {
            byte[][] tempMassive = Main.main.sub_1d8();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream dataOutput;
            (dataOutput = new DataOutputStream(byteArray)).writeByte(Main.main.numberOfPlayers);

            for (int i = 0; i < Main.main.numberOfPlayers; ++i) {
                dataOutput.writeByte((byte) tempMassive[i].length);
                dataOutput.write(tempMassive[i]);
            }

            recreateSettingsSaveFile(byteArray.toByteArray(), "qppla");
            byteArray.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    private static void recreateSettingsSaveFile(byte[] settingsMassive, String saveFileName) {
        try {
            try {
                RecordStore.deleteRecordStore(saveFileName);
            } catch (RecordStoreException var3) {
                System.out.println("Saving data/ delating old store/ exception: there is no store" + saveFileName);
                var3.printStackTrace();
            }

            RecordStore recStore;
            (recStore = RecordStore.openRecordStore(saveFileName, true)).addRecord(settingsMassive, 0, settingsMassive.length);
            recStore.closeRecordStore();
        } catch (RecordStoreException var4) {
            var4.printStackTrace();
        }
    }

    public static void WriteSavedGame() {
        Main.main.numberOfPlayers = 1;

        try {
            ByteArrayOutputStream var0 = new ByteArrayOutputStream();
            DataOutputStream var1;
            (var1 = new DataOutputStream(var0)).writeInt(RenderEngine.currentLocation);

            int var2;
            for (var2 = 0; var2 < RenderEngine.bool_massive_1st.length; ++var2) {
                var1.writeBoolean(RenderEngine.bool_massive_1st[var2]);
            }

            for (var2 = 0; var2 < RenderEngine.bool_massive_2th.length; ++var2) {
                var1.writeBoolean(RenderEngine.bool_massive_2th[var2]);
            }

            for (var2 = 0; var2 < RenderEngine.bool_massive_3th.length; ++var2) {
                var1.writeBoolean(RenderEngine.bool_massive_3th[var2]);
            }

            var1.writeShort(Scripts.CurrentNumberOfInventaryItems);

            for (var2 = 0; var2 < Scripts.CurrentNumberOfInventaryItems; ++var2) {
                var1.writeShort(Scripts.inventoryItems[var2]);
            }

            for (var2 = 0; var2 < Scripts.equipmentSlots.length; ++var2) {
                var1.writeShort(Scripts.equipmentSlots[var2]);
            }

            var1.writeByte(Scripts.playerActiveWeapon);
            var1.writeShort(Scripts.ActorCurrentHealth);
            var1.writeShort(Scripts.ActorCurrentWeight);
            var1.writeShort(Scripts.CurrentActorMoney);
            var1.writeShort(Scripts.playerMaxHealth);
            var1.writeShort(Scripts.playerMaxWeight);
            var1.writeByte(Scripts.playerAccuracy);
            var1.writeByte(Scripts.playerBulletProtection);
            var1.writeByte(Scripts.playerRadiationResistance);
            var1.writeByte(Scripts.playerAnomalyResistance);
            var1.writeShort(Scripts.currentPlayerLevel);
            var1.writeShort(Scripts.CurrentActorExpirience);

            byte var4;
            for (var4 = 0; var4 < Scripts.CurrentActorCharLevelsMassive.length; ++var4) {
                var1.writeByte(Scripts.CurrentActorCharLevelsMassive[var4]);
            }

            for (var4 = 0; var4 < 4; ++var4) {
                var1.writeShort(Scripts.MassiveActorAmmo[var4]);
            }

            var1.writeByte(Scripts.batyaDialogState);
            var1.writeByte(Scripts.rustyDialogState);
            //"Всего хорошего, нашёл идиота" = 1
            //Первое задание ржавого взято = 2
            //Бандиты на лесной дороге убиты = 3
            //Взято задание на канализацию = 4
            //Получена рация = 6
            var1.writeByte(Scripts.trusDialogState);
            var1.writeByte(Scripts.svistunDialogState);
            var1.writeByte(Scripts.botanikDialogState);
            var1.writeByte(Scripts.zaborDialogState);
            //Диалог с забором = 1
            var1.writeByte(Scripts.galoshQuestState);
            //Задание Галоша выполнено = 1
            var1.writeByte(Scripts.shlangDialogState);
            var1.writeByte(Scripts.militaryDialogState);
            //Первое задание по рации = 1
            var1.writeByte(Scripts.prizrakDialogState);
            var1.writeByte(Scripts.kaynazovskiDialogState);
            var1.writeByte(Scripts.krotDialogState);
            var1.writeByte(Scripts.haryaDialogState);
            var1.writeByte(Scripts.commanderDialogState);
            var1.writeByte(Scripts.manikovskiDialogState);
            var1.writeByte(Scripts.gutalinDialogState);
            var1.writeByte(Scripts.belomorDialogState);
            var1.writeByte(Scripts.koboldDialogState);
            //В начале игры 1
            var1.writeBoolean(Scripts.var_2544);
            var1.writeBoolean(Scripts.var_25bf);
            var1.writeByte(PlayerHUD.previousLocation);
            recreateSettingsSaveFile(var0.toByteArray(), "qpmrsp");
            var0.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    public static void LoadGameSettings() {
        System.out.println("ResourceManager.loadSettings");

        try {
            RecordStore settingsbytemassive = RecordStore.openRecordStore("qpset", false);
            byte[] var1 = settingsbytemassive.getRecord(1);

            SoundAndVibro.soundsEnabled = var1[0] == 1;
            SoundAndVibro.vibroEnabled = var1[1] == 1;
            Main.main.numberOfPlayers = var1[2];
            System.out.println("Main.instance .numberOfPlayers = " + Main.main.numberOfPlayers);
            ((SettingsScreen) AllScreens.settingsScreen).loadSettings();
            settingsbytemassive.closeRecordStore();
        } catch (RecordStoreException var2) {
            var2.printStackTrace();
        }
    }

    public static void LoadSavedGameFile() {
        System.out.println("LOADING RS");

        try {
            RecordStore var0 = RecordStore.openRecordStore("qpmrsp", false);
            DataInputStream var1 = new DataInputStream(new ByteArrayInputStream(var0.getRecord(1)));

            try {
                RenderEngine.currentLocation = var1.readInt();

                int var2;
                for (var2 = 0; var2 < RenderEngine.bool_massive_1st.length; ++var2) {
                    RenderEngine.bool_massive_1st[var2] = var1.readBoolean();
                }

                for (var2 = 0; var2 < RenderEngine.bool_massive_2th.length; ++var2) {
                    RenderEngine.bool_massive_2th[var2] = var1.readBoolean();
                }

                for (var2 = 0; var2 < RenderEngine.bool_massive_3th.length; ++var2) {
                    RenderEngine.bool_massive_3th[var2] = var1.readBoolean();
                }

                Scripts.CurrentNumberOfInventaryItems = var1.readShort();

                for (var2 = 0; var2 < Scripts.CurrentNumberOfInventaryItems; ++var2) {
                    Scripts.inventoryItems[var2] = var1.readShort();
                }

                for (var2 = 0; var2 < Scripts.equipmentSlots.length; ++var2) {
                    Scripts.equipmentSlots[var2] = var1.readShort();
                }

                Scripts.playerActiveWeapon = var1.readByte();
                Scripts.takeGunInHands(Scripts.playerActiveWeapon);
                Scripts.ActorCurrentHealth = var1.readShort();
                Scripts.ActorCurrentWeight = var1.readShort();
                Scripts.CurrentActorMoney = var1.readShort();
                Scripts.playerMaxHealth = var1.readShort();
                Scripts.playerMaxWeight = var1.readShort();
                Scripts.playerAccuracy = var1.readByte();
                Scripts.playerBulletProtection = var1.readByte();
                Scripts.playerRadiationResistance = var1.readByte();
                Scripts.playerAnomalyResistance = var1.readByte();
                Scripts.currentPlayerLevel = var1.readShort();
                Scripts.CurrentActorExpirience = var1.readShort();

                byte var6;
                for (var6 = 0; var6 < Scripts.CurrentActorCharLevelsMassive.length; ++var6) {
                    Scripts.CurrentActorCharLevelsMassive[var6] = var1.readByte();
                }

                for (var6 = 0; var6 < 4; ++var6) {
                    Scripts.MassiveActorAmmo[var6] = var1.readShort();
                }

                Scripts.batyaDialogState = var1.readByte();
                Scripts.rustyDialogState = var1.readByte();
                Scripts.trusDialogState = var1.readByte();
                Scripts.svistunDialogState = var1.readByte();
                Scripts.botanikDialogState = var1.readByte();
                Scripts.zaborDialogState = var1.readByte();
                Scripts.galoshQuestState = var1.readByte();
                Scripts.shlangDialogState = var1.readByte();
                Scripts.militaryDialogState = var1.readByte();
                Scripts.prizrakDialogState = var1.readByte();
                Scripts.kaynazovskiDialogState = var1.readByte();
                Scripts.krotDialogState = var1.readByte();
                Scripts.haryaDialogState = var1.readByte();
                Scripts.commanderDialogState = var1.readByte();
                Scripts.manikovskiDialogState = var1.readByte();
                Scripts.gutalinDialogState = var1.readByte();
                Scripts.belomorDialogState = var1.readByte();
                Scripts.koboldDialogState = var1.readByte();
                Scripts.var_2544 = var1.readBoolean();
                Scripts.var_25bf = var1.readBoolean();
                PlayerHUD.previousLocation = var1.readByte();
                var1.close();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            var0.closeRecordStore();
            System.out.println("RS LOADED");
        } catch (RecordStoreNotFoundException var4) {
            ;
        } catch (RecordStoreException var5) {
            ;
        }
    }

    public static void loadInterfaceImage(int number) {
        if (interfaceImages[number] == null) {
            try {
                interfaceImages[number] = Image.createImage("/gamedata/textures/i" + number + ".png");
            } catch (IOException var6) {
            } catch (NullPointerException var7) {
            } finally {
            }
        }
    }

    public static void loadAllInterfaceImages() {
        loadInterfaceImage(2);
        loadInterfaceImage(10);
        loadInterfaceImage(11);
        loadInterfaceImage(12);
    }

    public static void runGarbageCollector() {
        System.gc();
    }

    public static void removeLogoImageFromMemory() {
        if (logoImageLoaded != false && interfaceImages[0] != null) {
            interfaceImages[0] = null;
        }//мера оптимизации, вероятно

        logoImageLoaded = false;
        System.gc();
    }

    private static byte[] GetFromFileMassiveOfBytesThatLength(int length) throws IOException //Достать из файла массив битов длинной в это число
    {
        byte[] resultbytemassive = new byte[length];
        DataInputStream_Object_D.read(resultbytemassive);
        return resultbytemassive;
    }

    private static short[] GetFromFileMassiveOfShortsThatLength(int length) throws IOException //Достать из файла массив шортов длинной в это число
    {
        short[] resultshortmassive = new short[length];

        for (int var2 = 0; var2 < length; ++var2) {
            resultshortmassive[var2] = DataInputStream_Object_D.readShort();
        }

        return resultshortmassive;
    }

    public static void DrawInterfaceImageToSelectedRegion(Graphics graphics, int element_number, int x_dest, int y_dest, int anchor) {
        if (element_number != -2) {
            graphics.drawRegion(interfaceImages[MassiveWithInterfaceImagesAdress[element_number]], MassiveXsrcOfInterfaceImage[element_number], MassiveYsrcOfInterfaceImage[element_number], MassiveWidthOfInterfaceImage[element_number], MassiveHeightOfInterfaceImage[element_number], MassiveTransfOfInterfaceImage[element_number], x_dest, y_dest, anchor);
        }
    }

    public static void drawUserInterfaceItems(Graphics graphics, int var1, int x_start_dest, int y_start_dest) {//Отрисовка конкретного набора элементов!
        if (var1 >= 300) {
            ModChanges.DrawModIcon(graphics, var1, x_start_dest, y_start_dest);
        } else {
            short var5 = rectangleAdrForInterfaceMassive[var1 + 1];

            for (int var6 = rectangleAdrForInterfaceMassive[var1]; var6 < var5; ++var6) {
                short element_number;
                if ((element_number = var_419[var6]) < 0) {
                    drawUserInterfaceItems(graphics, element_number & 32767, x_start_dest + MassiveX_startOfInterfaceImage[var6], y_start_dest + MassiveY_startOfInterfaceImage[var6]);
                } else {
                    graphics.drawRegion(interfaceImages[MassiveWithInterfaceImagesAdress[element_number]], MassiveXsrcOfInterfaceImage[element_number], MassiveYsrcOfInterfaceImage[element_number], MassiveWidthOfInterfaceImage[element_number], MassiveHeightOfInterfaceImage[element_number], MassiveTransfOfInterfaceImage[element_number], x_start_dest + MassiveX_startOfInterfaceImage[var6], y_start_dest + MassiveY_startOfInterfaceImage[var6], 0);
                }
            }
        }

    }

    public static void ReadDataFromFile_D(DataInputStream datainputstream) {
        try {
            short var1;
            MassiveWithInterfaceImagesAdress = GetFromFileMassiveOfBytesThatLength(var1 = datainputstream.readShort());
            //System.out.println("MassiveWithInterfaceImagesAdress:"); //
            //for(int var6 = 0; var6<=127; ++var6) //
            //{ 
            //    System.out.print(var6 + ":[" + MassiveWithInterfaceImagesAdress[var6] + "] "); //
            //}
            //System.out.println();
            MassiveXsrcOfInterfaceImage = GetFromFileMassiveOfShortsThatLength(var1);
            //System.out.println("MassiveXsrcOfInterfaceImage");
            //for(int var6 = 0; var6<=127; ++var6) //
            //{ 
            //    System.out.print(var6 + ":[" + MassiveXsrcOfInterfaceImage[var6] + "] "); //
            //}
            //System.out.println();
            MassiveYsrcOfInterfaceImage = GetFromFileMassiveOfShortsThatLength(var1);
            //System.out.println("MassiveYsrcOfInterfaceImage");
            //for(int var6 = 0; var6<=127; ++var6) //
            //{ 
            //    System.out.print(var6 + ":[" + MassiveYsrcOfInterfaceImage[var6] + "] "); //
            //}
            //System.out.println();
            MassiveWidthOfInterfaceImage = GetFromFileMassiveOfShortsThatLength(var1);
            //System.out.println("MassiveWidthOfInterfaceImage");
            //for(int var6 = 0; var6<=127; ++var6) //
            //{ 
            //    System.out.print(var6 + ":[" + MassiveWidthOfInterfaceImage[var6] + "] "); //
            //}
            //System.out.println();
            MassiveHeightOfInterfaceImage = GetFromFileMassiveOfShortsThatLength(var1);
            //System.out.println("MassiveHeightOfInterfaceImage");
            //for(int var6 = 0; var6<=127; ++var6) //
            //{ 
            //    System.out.print(var6 + ":[" + MassiveHeightOfInterfaceImage[var6] + "] "); //
            //}
            //System.out.println();
            MassiveTransfOfInterfaceImage = GetFromFileMassiveOfBytesThatLength(var1);
            //System.out.println("MassiveTransfOfInterfaceImage");
            //for(int var6 = 0; var6<=127; ++var6) //
            //{ 
            //    System.out.print(var6 + ":[" + MassiveTransfOfInterfaceImage[var6] + "] "); //
            //}
            //System.out.println();
            rectangleAdrForInterfaceMassive = GetFromFileMassiveOfShortsThatLength(datainputstream.readShort());
            var_419 = GetFromFileMassiveOfShortsThatLength(var1 = datainputstream.readShort());
            MassiveX_startOfInterfaceImage = GetFromFileMassiveOfShortsThatLength(var1);
            MassiveY_startOfInterfaceImage = GetFromFileMassiveOfShortsThatLength(var1);
            int[] var2 = getRectangleParams(50, 2, 10);
            int[] var3 = getRectangleParams(50, 4, 0);
            int[] var4 = getRectangleParams(50, 3, 0);
            AllScreens.menuNameXCoord = var2[0] + var2[2] / 2;
            AllScreens.menuNameYCoord = var2[1] + var2[3] / 2;
            AllScreens.rightSoftTextXCoord = var4[0] + var4[2] / 2;
            AllScreens.rightsoftTextYCoord = var4[1] + var4[3] / 2;
            AllScreens.leftSoftTextXCoord = var3[0] + var3[2] / 2;
            AllScreens.leftSoftTextYCoord = AllScreens.rightsoftTextYCoord;
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    public static int ReturnWidthOfInterfaceImage(int number_of_image) {
        return number_of_image == -2 ? 0 : MassiveWidthOfInterfaceImage[number_of_image];
    }

    public static int ReturnHeightOfInterfaceImage(int number_of_image) { //равно ли -2? Если да, вернуть ноль. Если нет, вернуть высоту интерфейсного изображения
        return number_of_image == -2 ? 0 : MassiveHeightOfInterfaceImage[number_of_image];
    }

    public static int getRectangleWidth(int image_id) {
        try {
            if (image_id >= 300) {
                return ModChanges.NewItemsWidthOfRectangle((short) image_id);
            } else {
                return MassiveWidthOfInterfaceImage[var_419[rectangleAdrForInterfaceMassive[image_id]]];
            }
        } catch (Exception var1) {
            return 35;
        }
    }

    public static int getRectangleHeight(int image_id) {
        if (image_id >= 300) {
            return ModChanges.NewItemsHeightOfRectangle((short) image_id);
        } else {
            return MassiveHeightOfInterfaceImage[var_419[rectangleAdrForInterfaceMassive[image_id]]];
        }
    }

    public static int[] getRectangleParams(int var0, int var1, int var2) {
        int rectangleNumber = rectangleAdrForInterfaceMassive[var0] + var1;
        int[] rectangleParamsMassive = new int[4];
        int[] tempMassive = new int[]{0, 0, 0, 0};
        rectangleParamsMassive[0] = MassiveX_startOfInterfaceImage[rectangleNumber];
        rectangleParamsMassive[1] = MassiveY_startOfInterfaceImage[rectangleNumber];
        short var6;
        if ((var6 = var_419[rectangleNumber]) < 0) {
            tempMassive = getRectangleParams(var6 & 127, var2, 0);
        } else {
            rectangleParamsMassive[2] = MassiveWidthOfInterfaceImage[var6];
            rectangleParamsMassive[3] = MassiveHeightOfInterfaceImage[var6];
        }

        rectangleParamsMassive[0] += tempMassive[0];//xStart
        rectangleParamsMassive[1] += tempMassive[1];//yStart
        rectangleParamsMassive[2] += tempMassive[2];//width
        rectangleParamsMassive[3] += tempMassive[3];//height
        return rectangleParamsMassive;
    }

    public static int getUIElementXcoord(int mainUINumber, int childElementNumber) {
        int number_of_rectangle = rectangleAdrForInterfaceMassive[mainUINumber] + childElementNumber;
        return MassiveX_startOfInterfaceImage[number_of_rectangle];
    }

    public static int getUIElementYcoord(int mainUINumber, int childElementNumber) {
        int number_of_rectangle = rectangleAdrForInterfaceMassive[mainUINumber] + childElementNumber;
        return MassiveY_startOfInterfaceImage[number_of_rectangle];
    }

    static {
        try {
            interfaceImages[0] = Image.createImage("/gamedata/textures/i0.png");
        } catch (IOException var1) {
            var1.printStackTrace();
        }
    }
}
