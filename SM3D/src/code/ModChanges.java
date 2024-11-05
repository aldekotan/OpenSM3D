package code;

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
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.World;

public class ModChanges //Здесь буду размещать внесённые мною изменения
{

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
        "0123456789" +
                
        "ABCDEFGHI" +
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
            Scripts.addItemToInventary((short) 300);
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
        
        for(int i=0;i<bytes.length;i++) {
            char ch = string.charAt(i);
            
            if(ch==' ') {
                bytes[i] = -1; continue;
            }
            
            bytes[i] = (byte) gameAlphabet.indexOf(ch);
        }
        
        return bytes;
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
        } else {
            int[] list = new int[]{12, 13, 14, 15};

            for(int i = 0; i < list.length; i++) {
                Mesh child = meshSearch(world, list[i]);
                child.getAppearance(0).setTexture(0, ResourceLoader.getTexture("texobj2.png"));
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
}