package code;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.Image2D;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.World;

public class ModChanges //Здесь буду размещать внесённые мною изменения
{
    //Значения для адаптивного интерфейса
    public static boolean adaptiveUI = true;
    private static final int scrWidth = code.MainMenuScreen.scrWidth;
    private static final int scrHeight = code.MainMenuScreen.scrHeight;
    private static final int widthDiffCenter = (scrWidth-240)/2;
    private static final int heightDiffCenter = (scrHeight-320)/2;
    private static final int heightDiff = scrHeight-320;
    private static final int widthDiff = scrWidth-240;
    
    public static int dialogDrawWidth = 200; //160
    public static boolean ModChanges_Active = false;
    //1.08.17 Пробую ввести новое оружие
    public static Image[] ModWeapons = new Image[1];
    public static Image[] ModIcons = new Image[1];
    private static boolean Textures_loaded = false;
    //13.11.17 Внёс его весьма удачно ;)
    private static byte byte_massive[] = new byte[2000];
    public static short startingintroduction = 378; //адрес строки с стартовым вступлением
    
    public static final String gameAlphabet = 
        "01234s6789" +
                
        "ABCDEFGHIJ" +
        "KLMNOPQRST" +
        "UVWXYZ" +
        "abcdefghij" +
        "klmnpqrt" +
        "uvwxyz" +
                
        "=:.,!?+-#/" +
        "\\\'\"()$*%" +
                
        "БГДЁЖЗИЙЛП" + 
        "ФЦЧЩЪЫЬЭЮЯ" + 
        "бгдёжзийлт" + 
        "фцчщъыьэюя";
    
    public static final String gameAlphabetRu =
        "о123456789" +
                
        "АВСDЕFGНIJ" +
        "КLМNОРQRSТ" +
        "UVШХУZ" +
        "авсdеfgнij" +
        "кlмпрqrt" +
        "uvшхуz" +
                
        "=:.,!?+-#/" +
        "\\\'\"()$*%" +
                
        "БГДЁЖЗИЙЛП" + 
        "ФЦЧЩЪЫЬЭЮЯ" + 
        "бгдёжзийлт" + 
        "фцчщъыьэюя";
    
    //Добываем текст из txt
    public static void parseText() throws IOException {
        DataInputStream inputText = new DataInputStream(
                Main.main.getClass().getResourceAsStream("/gamedata/text/allStrings.txt"));
        byte[] tempByteArray = new byte[1];

        //Чтение каждого бита вплоть до конца
        int inputLength = 0;

        while (inputText.read(tempByteArray) != -1) {
            inputLength++;
        }
        //System.out.println("End of the file, bytes readed:" + inputLength);
        byte byteText[] = new byte[inputLength];
        inputText.close();
        inputText = new DataInputStream(
                Main.main.getClass().getResourceAsStream("/gamedata/text/allStrings.txt"));
        inputText.read(byteText);

        String tempStr = new String(byteText, "UTF-8");


        //System.out.println("tempStr length: " + tempStr.length());

        //Узнаем количество строк в массиве
        int lastNumberStart = tempStr.lastIndexOf('[') + 1;
        int lastNumberEnd = tempStr.lastIndexOf(']');
        String intStr = tempStr.substring(lastNumberStart, lastNumberEnd);
        int totalLineCount = Integer.parseInt(intStr);
        //System.out.println("Total lines count:" + totalLineCount);

        //Узнаём содержимое для каждой строки массива
        int numberStart = 0;
        int numberEnd = 0;
        int prevNumberStart = 0;
        int prevNumberEnd = 0;

        int textStart = 0;
        int textEnd = 0;
        int prevTextStart = 0;
        int prevTextEnd = 0;

        int prevLineAdress = 0;
        int lineAdress = 0;

        String textStr;
        byte[] trsStr = new byte[0];
        //byte[] trsStr = new byte[inputLength];
        short[] adrLine = new short[totalLineCount + 1];
        for (int i = 0; i <= totalLineCount; i++) {
            prevNumberStart = numberStart;
            prevNumberEnd = numberEnd;
            numberStart = tempStr.indexOf("[", prevNumberStart) + 1;
            numberEnd = tempStr.indexOf("]", prevNumberEnd) + 1;

            intStr = tempStr.substring(numberStart, numberEnd - 1);


            prevTextStart = textStart;
            prevTextEnd = textEnd;
            textStart = tempStr.indexOf("<", prevTextStart) + 1;
            textEnd = tempStr.indexOf(">", prevTextEnd) + 1;

            textStr = tempStr.substring(textStart, textEnd - 1);

            //System.out.println("Line number:" + intStr + " with text:" + textStr + ".");

            //Кладём набор символов в байт формате
            byte[] translatedBytes = translateText(textStr);
            int translatedLength = translatedBytes.length;
            
            //Динамический размер массива
            byte[] prevBytes = trsStr;
            trsStr = new byte[prevBytes.length+translatedBytes.length];
            System.arraycopy(prevBytes, 0, trsStr, 0, prevBytes.length);
            
            System.arraycopy(translatedBytes, 0, trsStr, prevLineAdress, translatedLength);

            //Добавляем адрес к байтам в массив

            prevLineAdress = prevLineAdress + translatedLength;
            adrLine[Integer.parseInt(intStr)] = (short) prevLineAdress;

            //System.arraycopy(translateText(textStr), 0, trsStr, prevLineAdress, translateText(textStr).length);
            //lineAdress = trsStr.length;

        }

        //Создание массива с адресами строк
        TextCreator.textLinesAdress = adrLine;
        //Создание массива со строками
        TextCreator.textLinesSymbols = trsStr;
    }

    private static void NewWeapons() throws IOException {
        ModWeapons[0] = Image.createImage("/gamedata/textures/w1.png");
        //загрузка текстуры оружия
        ModIcons[0] = Image.createImage("/gamedata/textures/i11.png");
        Textures_loaded = true;
    }

    public static short NewItemsID(short item_id) {//Здесь можно передать игре ID новых предметов
        switch(item_id) {
            case 300: //Новоиспечённый пистолет
                return 300;
            default:
                return -2;//просто заглушка
        }
    }

    public static int NewItemsWidthOfRectangle(short item_id) {//Здесь можно передать игре ширину прямоугольников в инвентаре
        switch(item_id) //тех, что вокруг предметов
        {
            case 300: //Новоиспечённый пистолет
                return 21;
            default:
                return -2;//просто заглушка
        }
    }

    public static int NewItemsHeightOfRectangle(short item_id) {//Здесь можно передать игре высоту прямоугольников в инвентаре
        switch(item_id) {
            case 300: //Новоиспечённый пистолет
                return 11;
            default:
                return -2;//просто заглушка
        }
    }

    public static void NewStartItems() {
        if(ModChanges_Active == true) {
            Scripts.addItemToInventory((short) 300);
        }
    }

    public static void DrawModIcon(Graphics graphics, int item_number, int x_start_dest, int y_start_dest) {
        if(Textures_loaded == true) {
            if(item_number == 300) { //Пистолет или новый пистолет, по умолчанию 43, 90
                graphics.drawRegion(ModIcons[0], 43, 90, 21, 14, 0, x_start_dest, y_start_dest, 0);
            }
        } else {
            try {
                NewWeapons();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            DrawModIcon(graphics, item_number, x_start_dest, y_start_dest);
        }
    }

    public static short[] AddNewTextAdress() { //новые адреса после 396
        short short_massive[] = new short[200];

        //short_massive[0] = 25997; //396 и т.д.
        //short_massive[1] = 26002;
        short_massive[114] = 25996; //510 - наш новый пистолет
        short_massive[115] = 26093; //511
        short_massive[116] = 26094; //512 - новое вступление
        short_massive[117] = 26373; //513
        short_massive[118] = 26375;

        return short_massive;
    }

    public static byte[] AddNewText() { //новые тексты после 25996
        //ниже пример
        //byte_massive[0] = 80; //25996
        String str = "Экспериментальный прототип пистолета Фора. Единичный экземпляр. Можно встретить в локации Кордон."
                + "Они мертвы... всё из-за этой чёртовой аномалии! Они бы выжили если бы я... Чёрт! Чёрт! Чёрт! Так. Нужно"
                + " держать себя в руках... Нигде нет тела кэпа, может он выжил? Но у меня нет ничего чтобы связаться с"
                + " ним... Судя по карте, дальше по дороге деревушка. Попробую найти помощь там."
                + "/ ";
        return translateText(str);
    }

    public static short[] CombineTwoTextMassives(short[] first_text_massive, short[] second_text_massive) {
        short[] new_text_massive = new short[first_text_massive.length + second_text_massive.length];

        int var3;
        for(var3 = 0; var3 < first_text_massive.length; ++var3) {
            new_text_massive[var3] = first_text_massive[var3];
        }

        var3 = first_text_massive.length;

        for(int var4 = first_text_massive.length; var3 < new_text_massive.length; ++var3) {
            new_text_massive[var3] = second_text_massive[var3 - var4];
        }

        return new_text_massive;
    }
    
    public static byte[] translateText(String string) {
        byte[] bytes = new byte[string.length()];
        int sizeDiff = 0;
        
        for(int i=0;i<bytes.length;i++) {
            char ch = string.charAt(i);
            
            if(ch==' ') {
                bytes[i+sizeDiff] = -1; continue;
            }
            if(ch=='\n') {
                bytes[i+sizeDiff] = -2; 
                continue;
            }
            if(ch=='\r') {
                sizeDiff--;
                continue;
            }
            //if(ch=='О'||ch=='o'||ch=='O') {
            if(ch=='o') {
                bytes[i+sizeDiff] = 0; continue;
            }
            
            bytes[i+sizeDiff] = (byte) gameAlphabet.indexOf(ch);
            if(bytes[i+sizeDiff]==-1){
                bytes[i+sizeDiff] = (byte) gameAlphabetRu.indexOf(ch);
            }
            
        
        }
        
        byte[] finalBytes = new byte[bytes.length+sizeDiff];
        System.arraycopy(bytes, 0, finalBytes, 0, bytes.length+sizeDiff);
        //
        return finalBytes;
    }
    
    public static void updateZ1Models(Mesh mesh, String path) {
		if(path.indexOf("Sky") != -1) {
			Appearance ap = mesh.getAppearance(0);
			CompositingMode cm = ap.getCompositingMode();

			//cm.setDepthTestEnable(false);
			cm.setDepthWriteEnable(false);
			ap.setLayer(3); //render sky after everything
			ap.getTexture(0).setBlending(Texture2D.FUNC_REPLACE);
		}
		
		if(path.indexOf("Forest") != -1) {
			Appearance ap = mesh.getAppearance(0);
			CompositingMode cm = ap.getCompositingMode();
			
			ap.setLayer(1); //render forest after level
			cm.setAlphaThreshold(0.5f);
		}
		
		if(path.indexOf("Asphalt") != -1) {
			Appearance ap = mesh.getAppearance(0);
			CompositingMode cm = ap.getCompositingMode();
			
			ap.setLayer(2); //render terrain forest
		}
	}
    
    public static void updateM3DModels(Object3D[] objs, String path) {
        boolean forest = path.indexOf("ForestDecors.m3g") != -1;
        boolean pers = path.indexOf("pers.m3g") != -1;
        if(!pers && !forest) return;

        World world = null;
        for(int i = 0; i < objs.length; i++) {
            if(objs[i] instanceof World) world = (World) objs[i];
        }
        if(world == null) return;

        if(forest) {
            int[] list = new int[]{1, 4, 5, 6};

            for(int i = 0; i < list.length; i++) {
                Mesh child = meshSearch(world, list[i]);
				Appearance ap = child.getAppearance(0);
				CompositingMode cm = ap.getCompositingMode();
				
                ap.setTexture(0, ResourceLoader.getTexture("texforest1.png"));
				
				ap.setLayer(1); //render forest after level
				
				cm.setBlending(CompositingMode.REPLACE); //for some reason alpha blending was enabled
				cm.setAlphaThreshold(0.5f); //render using alpha threshold instead
            }

            list = new int[]{0, 2, 3};
            for(int i = 0; i < list.length; i++) {
                Mesh child = meshSearch(world, list[i]);
				Appearance ap = child.getAppearance(0);
				CompositingMode cm = ap.getCompositingMode();
				
                ap.setTexture(0, ResourceLoader.getTexture("texobj2.png"));
				ap.getTexture(0).setBlending(Texture2D.FUNC_REPLACE);
				
				ap.setLayer(2); //render terrain after forest
            }
        }
		
		if(pers) {
            int[] list = new int[]{12, 13, 14, 15};

            for(int i = 0; i < list.length; i++) {
                Mesh child = meshSearch(world, list[i]);
				Appearance ap = child.getAppearance(0);
				
                ap.setTexture(0, ResourceLoader.getTexture("texobj2.png"));
				ap.getTexture(0).setBlending(Texture2D.FUNC_MODULATE);
				
				ap.setMaterial(new Material());
            }
        }

    }

    public static Mesh meshSearch(World world, int id) {
        int c = 0;

        for(int i = 0; i < world.getChildCount(); i++) {
            Node mesh = world.getChild(i);
            if(mesh instanceof Mesh) {
                if(c == id) return (Mesh) mesh;
                c++;
            }
        }
        return null;
    }
    
    //todo replace with tga?
    public static Image2D loadIndexedImage(String path) throws IOException {
        path = path.substring(0, path.length() - 3);
        
        InputStream is = (new Object()).getClass().getResourceAsStream(path + "data");

        int w = is.read() + 1;
        int h = is.read() + 1;
        byte[] indexes = new byte[w * h];

        int pos = 0;
        while(pos < indexes.length) {
            pos += is.read(indexes, pos, indexes.length - pos);
        }

        is.close();
        is = (new Object()).getClass().getResourceAsStream(path + "data.pal");

        byte[] palette = new byte[256 * 4];

        for(int i = 0; i < 256; i++) {
            int rgb = 0;
            rgb |= is.read() << 16;
            rgb |= is.read() << 8;
            rgb |= is.read();
            if(i != 0 || rgb != 0x00ff00) rgb |= 0xff000000;
            
            palette[i * 4] = (byte) ((rgb & 0x00ff0000) >> 16);
            palette[i * 4 + 1] = (byte) ((rgb & 0x0000ff00) >> 8);
            palette[i * 4 + 2] = (byte) (rgb & 0x000000ff);
            palette[i * 4 + 3] = (byte) ((rgb & 0xff000000) >>> 24);
        }
        
        is.close();
        
        Image2D img2d = new Image2D(Image2D.RGBA, w, h, indexes, palette);
        return img2d;
    }

    public static void drawAdaptiveUI(Graphics graphics, int uiNumber, int x, int y)
            {
                //Если интерфейс уже подогнан под нужное разрешение или
                //адаптивка отключена                                                                                                                                                                                                                                                                                                                                                                                                                              
                if ((scrWidth == 240 && scrHeight == 320) || !adaptiveUI)
                {
                    ResourceManager.drawUserInterfaceItems(graphics, uiNumber, x, y);
                }
                else
                {
                    //System.out.println("Adaptive interface activated");
                    
                    switch (uiNumber) {
                        case 1:
                            //Ingame frame window
                            
                            //Horizontal
                            //Top
                            int tempX = 4;
                            int tempY = 3;
                            for(int i = 0; i+44<=164+widthDiff; i=i+44)
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 3, tempX, tempY, 0);
                                tempX+=44;
                            }
                            ResourceManager.drawUserInterfacePart(graphics, 3, 164+widthDiff+4, tempY, 16|8);
                            //Bottom
                            tempX = 2;
                            tempY = 317+heightDiff;
                            for(int i = 0; i+44<=234+widthDiff; i=i+44)
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 3, tempX, tempY, 0);
                                tempX+=44;
                            }
                            ResourceManager.drawUserInterfacePart(graphics, 3, scrWidth-2, tempY, 16|8);
                            
                            //Vertical
                            //Right
                            tempX = 237+widthDiff;
                            tempY = 11;
                            for(int i = 0; i+71<=307+heightDiff; i=i+71)//307
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 1, tempX, tempY, 0);
                                ResourceManager.drawUserInterfacePart(graphics, 2, tempX, tempY+29, 0);
                                tempY+=71;
                            }
                            ResourceManager.drawUserInterfacePart(graphics, 1, tempX+3, scrHeight-44, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 2, tempX+3, scrHeight-2, 32|8);
                            //Left
                            tempX = 0;
                            tempY = 6;
                            for(int i = 0; i+71<=312+heightDiff; i=i+71)//307
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 1, tempX, tempY, 0);
                                ResourceManager.drawUserInterfacePart(graphics, 2, tempX, tempY+29, 0);
                                tempY+=71;
                            }
                            ResourceManager.drawUserInterfacePart(graphics, 1, tempX+3, scrHeight-44, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 2, tempX+3, scrHeight-2, 32|8);
                            
                            //Additional stuff
                            ResourceManager.drawUserInterfacePart(graphics, 0, 0, 3, 0);
                            ResourceManager.drawUserInterfacePart(graphics, 4, scrWidth-38, 3, 16|8);
                            ResourceManager.drawUserInterfacePart(graphics, 5, scrWidth-19, 2, 16|8);
                            ResourceManager.drawUserInterfacePart(graphics, 6, scrWidth, 0, 16|8);
                            break;
                        case 3:   
                            //Bottom concrete panel with fence
                            //middle
                            tempX = 37;
                            tempY = scrHeight-2;
                            for(int i = 0; i+30<=200+widthDiff;i=i+30)
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 31, tempX, tempY, 32|4);
                                tempX+=30;
                            }
                            //fence back and top
                            tempX = 34;
                            tempY = scrHeight-19;
                            for(int i = 0; i+24<=142+widthDiff;i=i+24)
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 19, tempX, tempY, 32|4);
                                ResourceManager.drawUserInterfacePart(graphics, 20, tempX, tempY-2, 32|4);
                                tempX+=24;
                            }
                            //дорисовка сетки
                            for(int i = tempX-24; i+8<=scrWidth-80;i=i+8)
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 19, i, tempY-0, 32|4);    //0
                                ResourceManager.drawUserInterfacePart(graphics, 20, i, tempY-2, 32|4);//-2
                                //tempX+=8;
                            }
                            
                            //left
                            ResourceManager.drawUserInterfacePart(graphics, 30, 7, scrHeight-2, 32|4);
                            ResourceManager.drawUserInterfacePart(graphics, 18, 9, scrHeight-19, 32|4);
                            
                            //right
                            ResourceManager.drawUserInterfacePart(graphics, 31, scrWidth-3, scrHeight-2, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 19, scrWidth-15, scrHeight-19, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 19, scrWidth-7, scrHeight-19, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 19, scrWidth-39, scrHeight-19, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 20, scrWidth-40, scrHeight-21, 32|8);
                            ResourceManager.drawUserInterfacePart(graphics, 21, scrWidth-9, scrHeight-21, 32|8);                            
                            break;
                        case 5:
                            //Top concrete panel with lightbulp and black screen
                            //middle
                            tempX = 33;
                            tempY = 1;
                            for(int i = 0; i+30<=200+widthDiff;i=i+30)
                            {
                                ResourceManager.drawUserInterfacePart(graphics, 31, tempX, tempY, 0);
                                tempX+=30;
                            }
                            //top right
                            ResourceManager.drawUserInterfacePart(graphics, 25, scrWidth-35, 2, 16|8);
                            ResourceManager.drawUserInterfacePart(graphics, 32, scrWidth-2, 1, 16|8);
                            
                            //top left
                            ResourceManager.drawUserInterfacePart(graphics, 34, 2, 3, 0);
                            ResourceManager.drawUserInterfacePart(graphics, 30, 3, 1, 0);
                            ResourceManager.drawUserInterfacePart(graphics, 33, 10, 3, 0);
                            break;
                        case 8:
                            //Bottom left button
                            ResourceManager.drawUserInterfaceItems(graphics, uiNumber, x, y+heightDiff);
                            break;
                        case 9:
                            //Bottom right button
                            ResourceManager.drawUserInterfaceItems(graphics, 9, x+widthDiff, y+heightDiff);
                            break;
                        case 11:
                            //Health bar
                            ResourceManager.drawUserInterfaceItems(graphics, 11, x, y);
                            break;
                        case 13:
                            //Ammo background
                            ResourceManager.drawUserInterfaceItems(graphics, 13, x+widthDiffCenter, y+heightDiff-2);
                            break;
                        case 19:
                            //large wide frame
                            tempX = 46+widthDiffCenter;
                            tempY = 47+heightDiffCenter;
                            ResourceManager.drawUserInterfaceItems(graphics, 19, tempX, tempY);
                            break;
                        case 37:
                            //3D Ingame HUD
                            drawAdaptiveUI(graphics, 1, x, y);
                            drawAdaptiveUI(graphics, 13, x, y);
                            drawAdaptiveUI(graphics, 9, x, y);
                            drawAdaptiveUI(graphics, 8, x, y);
                            drawAdaptiveUI(graphics, 11, x, y);
                            break;
                        case 44:
                            //Menu frame with small frame inside
                            drawAdaptiveUI(graphics, 3, x, y);
                            drawAdaptiveUI(graphics, 1, x, y);
                            drawAdaptiveUI(graphics, 5, x, y);
                            drawAdaptiveUI(graphics, 9, x, y);
                            drawAdaptiveUI(graphics, 19, x, y);
                            break;
                        default:
                            System.out.println("UI#"+uiNumber+" has not created");
                            ResourceManager.drawUserInterfaceItems(graphics, uiNumber, x, y);
                    }
                }
            }
            //ResourceManager.drawUserInterfaceItems
            //ResourceManager.DrawInterfaceImageToSelectedRegion
    public static int getTextClipVars(int varNumber)
    {
        int varResult = 0;
        switch (varNumber)
        {
            case 55:
                varResult=varNumber+widthDiffCenter;
                break;
            case 70:
                varResult=varNumber+heightDiffCenter;
                break;
            case 125:
                //varResult=varNumber+widthDiffCenter;
                varResult=varNumber;
                break;
            case 180:
                //varResult=varNumber+heightDiffCenter;
                varResult=varNumber;
                break;
            default:
                varResult = 0;
        }
        return adaptiveUI?varResult:varNumber;
    }
    
    public static int getCenteredVarUpscale(int var, boolean height)
    {
        int varResult = 0;
        if(height)
        {
            varResult=var+heightDiffCenter;
        }
        else
        {
            varResult=var+widthDiffCenter;
        }
        return adaptiveUI?varResult:var;
    }
    public static int getCornerVarUpscale(int var, boolean height)
    {
        int varResult = 0;
        if(height)
        {
            varResult=var+heightDiff;
        }
        else
        {
            varResult=var+widthDiff;
        }
        return adaptiveUI?varResult:var;
    }
}
