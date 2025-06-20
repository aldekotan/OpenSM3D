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

public final class ResourceManager {

    public static DataInputStream data = new DataInputStream(Main.main.getClass().getResourceAsStream("/d"));
    
    public static boolean logoImageLoaded = false;
    public static Image[] interfaceImages = new Image[13];
    
    //Состояние загрузки игры.
    //1 - до появления логотипа
    //8 - после лого
    //0 - после загрузки игровых интерфейсов
    public static int loadingState;
    
    //Параметры изображений
    public static byte[] interfaceImageIds;
    public static short[] interfaceImageXsrc;
    public static short[] interfaceImageYsrc;
    public static short[] interfaceImageWidth;
    public static short[] interfaceImageHeight;
    public static byte[] interfaceImageTransform;
    public static short[] interfaceImageAdditionalIds;
    public static short[] interfaceImageXdest;
    public static short[] interfaceImageYdest;
    public static short[] interfacePackIds;

    public static void saveSettings() {
        byte[] settings = new byte[3];
		
        settings[0] = (byte) (SoundAndVibro.soundsEnabled ? 1 : 0);
        settings[1] = (byte) (SoundAndVibro.vibroEnabled ? 1 : 0);
        settings[2] = Main.main.numberOfPlayers;
		
        saveArrayToRms(settings, "qpset");
		
        System.out.println("ResourceManager.saveSettings: settings saved");
    }

    public static void savePlayersRecords() {//
        try {
            byte[][] records = Main.main.getPlayersRecords();
			
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(byteArray);
			
            dataOutput.writeByte(Main.main.numberOfPlayers);

            for (int i = 0; i < Main.main.numberOfPlayers; ++i) {
                dataOutput.writeByte((byte) records[i].length);
                dataOutput.write(records[i]);
            }

            saveArrayToRms(byteArray.toByteArray(), "qppla");
            byteArray.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void saveArrayToRms(byte[] data, String saveName) {
		try {
			RecordStore.deleteRecordStore(saveName);
		} catch (RecordStoreException e) {
			System.out.println("Saving data/ delating old store/ exception: there is no store" + saveName);
			e.printStackTrace();
		}

        try {
            RecordStore recStore = RecordStore.openRecordStore(saveName, true);
            recStore.addRecord(data, 0, data.length);
            recStore.closeRecordStore();
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
    }

    public static void saveGame() {
        Main.main.numberOfPlayers = 1;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dis = new DataOutputStream(baos);
			
            dis.writeInt(GameScene.currentLocation);

            for(int i = 0; i < GameScene.locationCompleted.length; i++) {
                dis.writeBoolean(GameScene.locationCompleted[i]);
            }

            for(int i = 0; i < GameScene.locationTaskMark.length; i++) {
                dis.writeBoolean(GameScene.locationTaskMark[i]);
            }

            for(int i = 0; i < GameScene.locationCampMark.length; i++) {
                dis.writeBoolean(GameScene.locationCampMark[i]);
            }

            dis.writeShort(Scripts.inventoryItemsCount);

            for(int i = 0; i < Scripts.inventoryItemsCount; i++) {
                dis.writeShort(Scripts.inventoryItems[i]);
            }

            for(int i = 0; i < Scripts.equipmentSlots.length; i++) {
                dis.writeShort(Scripts.equipmentSlots[i]);
            }

            dis.writeByte(Scripts.playerActiveWeapon);
            dis.writeShort(Scripts.playerHealth);
            dis.writeShort(Scripts.playerWeight);
            dis.writeShort(Scripts.playerMoney);
            dis.writeShort(Scripts.playerMaxHealth);
            dis.writeShort(Scripts.playerMaxWeight);
            dis.writeByte(Scripts.playerAccuracy);
            dis.writeByte(Scripts.playerBulletProtection);
            dis.writeByte(Scripts.playerRadiationResistance);
            dis.writeByte(Scripts.playerAnomalyResistance);
            dis.writeShort(Scripts.playerLevel);
            dis.writeShort(Scripts.playerExp);

			//Accuracy, max health, max weight
            for(int i = 0; i < Scripts.playerStatLevel.length; i++) {
                dis.writeByte(Scripts.playerStatLevel[i]);
            }

            for(int i = 0; i < 4; i++) {
                dis.writeShort(Scripts.playerWeaponsAmmo[i]);
            }

            dis.writeByte(Scripts.batyaDialogState);
            dis.writeByte(Scripts.rustyDialogState);
            //"Всего хорошего, нашёл идиота" = 1
            //Первое задание ржавого взято = 2
            //Бандиты на лесной дороге убиты = 3
            //Взято задание на канализацию = 4
            //Получена рация = 6
            dis.writeByte(Scripts.trusDialogState);
            dis.writeByte(Scripts.svistunDialogState);
            dis.writeByte(Scripts.botanikDialogState);
            dis.writeByte(Scripts.zaborDialogState);
            //Диалог с забором = 1
            dis.writeByte(Scripts.galoshQuestState);
            //Задание Галоша выполнено = 1
            dis.writeByte(Scripts.shlangDialogState);
            dis.writeByte(Scripts.militaryDialogState);
            //Первое задание по рации = 1
            dis.writeByte(Scripts.prizrakDialogState);
            dis.writeByte(Scripts.kaynazovskiDialogState);
            dis.writeByte(Scripts.krotDialogState);
            dis.writeByte(Scripts.haryaDialogState);
            dis.writeByte(Scripts.commanderDialogState);
            dis.writeByte(Scripts.manikovskiDialogState);
            dis.writeByte(Scripts.gutalinDialogState);
            dis.writeByte(Scripts.belomorDialogState);
            dis.writeByte(Scripts.koboldDialogState);
            //В начале игры 1
            dis.writeBoolean(Scripts.svistunQuestCompleted);
            dis.writeBoolean(Scripts.capItemGot);
			
            dis.writeByte(PlayerHUD.previousLocation);
			
            saveArrayToRms(baos.toByteArray(), "qpmrsp");
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSettings() {
        System.out.println("ResourceManager.loadSettings");

        try {
            RecordStore rs = RecordStore.openRecordStore("qpset", false);
            byte[] data = rs.getRecord(1);

            SoundAndVibro.soundsEnabled = data[0] == 1;
            SoundAndVibro.vibroEnabled = data[1] == 1;
            Main.main.numberOfPlayers = data[2];
			
            System.out.println("Main.instance .numberOfPlayers = " + Main.main.numberOfPlayers);
			
            ((SettingsScreen) AllScreens.settingsScreen).loadSettings();
			
            rs.closeRecordStore();
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
    }

    public static void loadGameSave() {
        System.out.println("LOADING RS");

        try {
            RecordStore rs = RecordStore.openRecordStore("qpmrsp", false);
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(rs.getRecord(1)));

            try {
                GameScene.currentLocation = dis.readInt();

                for(int i = 0; i < GameScene.locationCompleted.length; i++) {
                    GameScene.locationCompleted[i] = dis.readBoolean();
                }

                for(int i = 0; i < GameScene.locationTaskMark.length; i++) {
                    GameScene.locationTaskMark[i] = dis.readBoolean();
                }

                for(int i = 0; i < GameScene.locationCampMark.length; i++) {
                    GameScene.locationCampMark[i] = dis.readBoolean();
                }

                Scripts.inventoryItemsCount = dis.readShort();

                for(int i = 0; i < Scripts.inventoryItemsCount; i++) {
                    Scripts.inventoryItems[i] = dis.readShort();
                }

                for(int i = 0; i < Scripts.equipmentSlots.length; i++) {
                    Scripts.equipmentSlots[i] = dis.readShort();
                }

                Scripts.playerActiveWeapon = dis.readByte();
                Scripts.takeGunInHands(Scripts.playerActiveWeapon);
				
                Scripts.playerHealth = dis.readShort();
                Scripts.playerWeight = dis.readShort();
                Scripts.playerMoney = dis.readShort();
                Scripts.playerMaxHealth = dis.readShort();
                Scripts.playerMaxWeight = dis.readShort();
                Scripts.playerAccuracy = dis.readByte();
                Scripts.playerBulletProtection = dis.readByte();
                Scripts.playerRadiationResistance = dis.readByte();
                Scripts.playerAnomalyResistance = dis.readByte();
                Scripts.playerLevel = dis.readShort();
                Scripts.playerExp = dis.readShort();

                for(int i = 0; i < Scripts.playerStatLevel.length; i++) {
                    Scripts.playerStatLevel[i] = dis.readByte();
                }

                for(int i = 0; i < 4; i++) {
                    Scripts.playerWeaponsAmmo[i] = dis.readShort();
                }

                Scripts.batyaDialogState = dis.readByte();
                Scripts.rustyDialogState = dis.readByte();
                Scripts.trusDialogState = dis.readByte();
                Scripts.svistunDialogState = dis.readByte();
                Scripts.botanikDialogState = dis.readByte();
                Scripts.zaborDialogState = dis.readByte();
                Scripts.galoshQuestState = dis.readByte();
                Scripts.shlangDialogState = dis.readByte();
                Scripts.militaryDialogState = dis.readByte();
                Scripts.prizrakDialogState = dis.readByte();
                Scripts.kaynazovskiDialogState = dis.readByte();
                Scripts.krotDialogState = dis.readByte();
                Scripts.haryaDialogState = dis.readByte();
                Scripts.commanderDialogState = dis.readByte();
                Scripts.manikovskiDialogState = dis.readByte();
                Scripts.gutalinDialogState = dis.readByte();
                Scripts.belomorDialogState = dis.readByte();
                Scripts.koboldDialogState = dis.readByte();
                Scripts.svistunQuestCompleted = dis.readBoolean();
                Scripts.capItemGot = dis.readBoolean();
				
                PlayerHUD.previousLocation = dis.readByte();
				
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            rs.closeRecordStore();
            System.out.println("RS LOADED");
        } catch (RecordStoreNotFoundException e) {
            ;
        } catch (RecordStoreException e) {
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

    private static byte[] readBytesArray(int length) throws IOException //Достать из файла массив битов длинной в это число
    {
        byte[] byteArray = new byte[length];
        data.read(byteArray);
        return byteArray;
    }

    private static short[] readShortsArray(int length) throws IOException //Достать из файла массив шортов длинной в это число
    {
        short[] shortArray = new short[length];

        for (int i = 0; i < length; ++i) {
            shortArray[i] = data.readShort();
        }

        return shortArray;
    }

    public static void drawUserInterfacePart(Graphics graphics, int element_number, int x_dest, int y_dest, int anchor) {
        if (element_number != -2) {
            graphics.drawRegion(interfaceImages[interfaceImageIds[element_number]], interfaceImageXsrc[element_number], interfaceImageYsrc[element_number], interfaceImageWidth[element_number], interfaceImageHeight[element_number], interfaceImageTransform[element_number], x_dest, y_dest, anchor);
        }
    }

    public static void drawUserInterfaceItems(Graphics graphics, int itemId, int x, int y) {//Отрисовка набора изображений
        //if (item >= 300) {
        //    ModChanges.DrawModIcon(graphics, item, x_start_dest, y_start_dest);
        //} else {
            short lastId = interfaceImageAdditionalIds[itemId + 1];

            for (int firstId = interfaceImageAdditionalIds[itemId]; firstId < lastId; ++firstId) {
                short unpackedItem;
                //Если часть интерфейса сборная - она будет меньше нуля.
                if ((unpackedItem = interfacePackIds[firstId]) < 0) {
                    //Возвращаемся и рисуем уже готовый распакованный элемент
                    drawUserInterfaceItems(graphics, unpackedItem & 32767, 
                            x + interfaceImageXdest[firstId], 
                            y + interfaceImageYdest[firstId]);
                    //System.out.println("ElementNumber: "+unpackedItem+ "&32767"+ " .UI element ID:"+ (unpackedItem & 32767));
                } else {
                    //Отрисовка распакованного элемента
                    graphics.drawRegion(interfaceImages[interfaceImageIds[unpackedItem]], 
                            interfaceImageXsrc[unpackedItem], 
                            interfaceImageYsrc[unpackedItem], 
                            interfaceImageWidth[unpackedItem], 
                            interfaceImageHeight[unpackedItem], 
                            interfaceImageTransform[unpackedItem], 
                            x + interfaceImageXdest[firstId], 
                            y + interfaceImageYdest[firstId], 0);
                }
            }
        //}
    }

    public static void getInterfaceData(DataInputStream datainputstream) {
        try {
            //Одиночные части интерфейса
            short numberOfInterfaces;
            interfaceImageIds = readBytesArray(numberOfInterfaces = datainputstream.readShort());
            interfaceImageXsrc = readShortsArray(numberOfInterfaces);
            interfaceImageYsrc = readShortsArray(numberOfInterfaces);
            interfaceImageWidth = readShortsArray(numberOfInterfaces);
            interfaceImageHeight = readShortsArray(numberOfInterfaces);
            interfaceImageTransform = readBytesArray(numberOfInterfaces);
            //Составные интерфейсные окна
            //Ниже указан диапазон от первого числа до второго. Из него берётся
            //число и по нему вызывается изображение в packIds, с координатами
            interfaceImageAdditionalIds = readShortsArray(datainputstream.readShort());
            //В packIds хранятся айдишники элементов из interfaceImageIds и
            //их положение относительно экрана
            interfacePackIds = readShortsArray(numberOfInterfaces = datainputstream.readShort());
            interfaceImageXdest = readShortsArray(numberOfInterfaces);
            interfaceImageYdest = readShortsArray(numberOfInterfaces);
            
            int[] var2 = getRectangleParams(50, 2, 10);
            int[] var3 = getRectangleParams(50, 4, 0);
            int[] var4 = getRectangleParams(50, 3, 0);
            AllScreens.menuNameXCoord = var2[0] + var2[2] / 2;
            AllScreens.menuNameYCoord = var2[1] + var2[3] / 2;
            AllScreens.rightSoftTextXCoord = ModChanges.getCornerVarUpscale(var4[0] + var4[2] / 2, false);
            AllScreens.rightsoftTextYCoord = ModChanges.getCornerVarUpscale(var4[1] + var4[3] / 2, true);
            AllScreens.leftSoftTextXCoord = var3[0] + var3[2] / 2;
            AllScreens.leftSoftTextYCoord = AllScreens.rightsoftTextYCoord;
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    public static int ReturnWidthOfInterfaceImage(int number_of_image) {
        return number_of_image == -2 ? 0 : interfaceImageWidth[number_of_image];
    }

    public static int ReturnHeightOfInterfaceImage(int number_of_image) { //равно ли -2? Если да, вернуть ноль. Если нет, вернуть высоту интерфейсного изображения
        return number_of_image == -2 ? 0 : interfaceImageHeight[number_of_image];
    }

    public static int getRectangleWidth(int image_id) {
        try {
            if (image_id >= 300) {
                return ModChanges.NewItemsWidthOfRectangle((short) image_id);
            } else {
                return interfaceImageWidth[interfacePackIds[interfaceImageAdditionalIds[image_id]]];
            }
        } catch (Exception var1) {
            return 35;
        }
    }

    public static int getRectangleHeight(int image_id) {
        if (image_id >= 300) {
            return ModChanges.NewItemsHeightOfRectangle((short) image_id);
        } else {
            return interfaceImageHeight[interfacePackIds[interfaceImageAdditionalIds[image_id]]];
        }
    }

    public static int[] getRectangleParams(int interfId, int var1, int var2) {
        int rectangleNumber = interfaceImageAdditionalIds[interfId] + var1;
        int[] rectangleParamsMassive = new int[4];
        int[] tempMassive = new int[]{0, 0, 0, 0};
        rectangleParamsMassive[0] = interfaceImageXdest[rectangleNumber];
        rectangleParamsMassive[1] = interfaceImageYdest[rectangleNumber];
        short var6;
        if ((var6 = interfacePackIds[rectangleNumber]) < 0) {
            tempMassive = getRectangleParams(var6 & 127, var2, 0);
        } else {
            rectangleParamsMassive[2] = interfaceImageWidth[var6];
            rectangleParamsMassive[3] = interfaceImageHeight[var6];
        }

        rectangleParamsMassive[0] += tempMassive[0];//xStart
        rectangleParamsMassive[1] += tempMassive[1];//yStart
        rectangleParamsMassive[2] += tempMassive[2];//width
        rectangleParamsMassive[3] += tempMassive[3];//height
        return rectangleParamsMassive;
    }

    public static int getUIElementXcoord(int mainUINumber, int childElementNumber) {
        int elementId = interfaceImageAdditionalIds[mainUINumber] + childElementNumber;
        return interfaceImageXdest[elementId];
    }

    public static int getUIElementYcoord(int mainUINumber, int childElementNumber) {
        int elementId = interfaceImageAdditionalIds[mainUINumber] + childElementNumber;
        return interfaceImageYdest[elementId];
    }

    static {
        try {
            interfaceImages[0] = Image.createImage("/gamedata/textures/i0.png");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
