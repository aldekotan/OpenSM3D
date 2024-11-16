package code;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class LoadingScreen implements Screen {

    public static final byte[] statesList = new byte[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    public static final byte[] thingsToLoad = new byte[]{0, 3, 6, 4, 7, 5, 5, 9, 10, 11, 12};
    
    public static Screen[] screens;
    public static final byte[] var_151 = new byte[]{(byte) 1, (byte) 2, (byte) 2, (byte) 1, (byte) 14, (byte) 14, (byte) 1, (byte) 14, (byte) 14, (byte) 14, (byte) 14};
    private Screen var_22d;
    private byte var_25a;
    private Timer timer = new Timer(false, 3000L);
    
    private Image logo;
    private int state = 1;
    private int toLoad;
    private int framesDrawn;

    public LoadingScreen(Image logo) {
        this.logo = logo;
    }

    public final boolean onShow(byte var1) {
        this.state = statesList[var1];
        this.toLoad = thingsToLoad[var1];
        
        if(var1 != 0) {
            this.var_22d = screens[var1];
            this.var_25a = var_151[var1];
        }

        return true;
    }

    public final void resetVariables() {
        screens = new Screen[]{AllScreens.menu2, AllScreens.pauseScreen, AllScreens.pauseScreen, AllScreens.menu, AllScreens.menu2, AllScreens.menu, AllScreens.settingsScreen, AllScreens.menu, AllScreens.menu, AllScreens.menu, AllScreens.menu};
    }

    public static void RunGarbageCollector() {
        System.gc();
        ++PlayerHUD.garbageCollected;
    }

    public final void paint(Graphics g) {
        switch(state) {
            case 1:
                g.drawImage(logo, 0, 0, 0);
                
                framesDrawn++;
                if(framesDrawn > 2) {
                    nextState();
                    framesDrawn = 0;
                    return;
                }

                Main.main.repaint();
                MasterCanvas.instance.serviceRepaints();
                break;
            case 2:
                g.setColor(0); //0
                g.fillRect(0, 0, 
                        MainMenuScreen.scrWidth, 
                        MainMenuScreen.scrHeight
                );
                
                TextCreator.FindParametersnDrawText(0, 41, MainMenuScreen.scrWidth / 2 - 20, MainMenuScreen.scrHeight / 2 - TextCreator.getHeightFromTextParamMassive(0) / 2, 0);

                framesDrawn++;
                if(framesDrawn > 2) {
                    nextState();
                    framesDrawn = 0;
                }
                
                Main.main.repaint();
                MasterCanvas.instance.serviceRepaints();
                break;
            default:
                System.out.println("wtf??? 699");
        }
    }

    public final void keyPressed(int var1) {
    }

    private void nextState() {
        switch(state) //на стартовой заставке 1//
        {
            case 1:
                loadGameStuff();
                
                //Unload logo
                ResourseManager.interfaceImages[0] = null;
                System.gc();
                
                if(SoundAndVibro.soundsEnabled) {
                    Main.main.setScreen(AllScreens.confirmScreen, (byte) 4);
                    return;
                }

                Main.main.setScreen(AllScreens.menu, (byte) 1);
                return;
            case 2:
                this.sub_cc();
                Main.main.setScreen(this.var_22d, this.var_25a);
            default:
        }
    }

    private void sub_cc() //загрузка и начало новой игры, сохранение настроек
    {
        switch(this.toLoad) {
            case 4: //сохранение игровых настроек
                ResourseManager.saveSettings();
                break;
            case 5:
                ResourseManager.sub_4d();
            case 6:
            case 7:
            case 9:
            case 10:
            default:
                break;
            case 8:
                ResourseManager.removeLogoImageFromMemory();
                break;
            case 11: //начало новой игры
                GameScene.setDialogWindowState((short) -2);
                GameScene.init();
                break;
            case 12: //загрузка сохранённой игры
                ResourseManager.loadGameSave();
                PlayerHUD.loadLocationsCoordinates();
                Scripts.sub_72f();
        }

        ResourseManager.var_e6 = 0;
    }

    private void loadGameStuff() {
        ResourseManager.var_e6 = 1;
        if(ResourseManager.var_e6 == 1) {
            try {
                TextCreator.LoadImageAndTextParameters(ResourseManager.DataInputStream_Object_D, 0); //загрузка параметров
                TextCreator.LoadImageAndTextParameters(ResourseManager.DataInputStream_Object_D, 1); //загрузка параметров
                TextCreator.ReadUnsignedByteAndShortMassivesForText(ResourseManager.DataInputStream_Object_D); //загрузка текста и его параметров
                ResourseManager.ReadDataFromFile_D(ResourseManager.DataInputStream_Object_D);
                AllScreens.SymbolHeight = TextCreator.getHeightFromTextParamMassive(1);
                AllScreens.SymbolWidth = TextCreator.GetWidthOfSymbol(1, 10);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

            TextCreator.sub_607();
            Main.main.sub_2c();
            ResourseManager.loadAllInterfaceImages();
            ResourseManager.loadSettings();
        }

        Main.main.repaint();

        while(this.timer.getTimerState() != 0) {
            Thread.yield();
        }

        this.logo = null;
        ResourseManager.logoImageLoaded = true;
        ResourseManager.var_e6 = 8;
    }

}
