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
        //System.gc();
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
                
                TextCreator.drawLineByAnchor(0, 41, MainMenuScreen.scrWidth / 2 - 20, MainMenuScreen.scrHeight / 2 - TextCreator.getSymbolHeight(0) / 2, 0);

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
                ResourceManager.interfaceImages[0] = null;
                System.gc();
                
                if(SoundAndVibro.soundsEnabled) {
                    Main.main.setScreen(AllScreens.confirmScreen, (byte) 4);
                    return;
                }

                Main.main.setScreen(AllScreens.menu, (byte) 1);
                return;
            case 2:
                this.gamePreload();
                Main.main.setScreen(this.var_22d, this.var_25a);
            default:
        }
    }

    private void gamePreload() //загрузка и начало новой игры, сохранение настроек
    {
        switch(this.toLoad) {
            case 4: //сохранение игровых настроек
                ResourceManager.saveSettings();
                break;
            case 5:
                ResourceManager.savePlayersRecords();
            case 6:
            case 7:
            case 9:
            case 10:
            default:
                break;
            case 8:
                ResourceManager.removeLogoImageFromMemory();
                break;
            case 11: //начало новой игры
                GameScene.setDialogWindowState((short) -2);
                GameScene.init();
                break;
            case 12: //загрузка сохранённой игры
                ResourceManager.loadGameSave();
                PlayerHUD.loadLocationsCoordinates();
                Scripts.openMap();
        }

        ResourceManager.loadingState = 0;
    }

    private void loadGameStuff() {
        ResourceManager.loadingState = 1;
        if(ResourceManager.loadingState == 1) {
            try {
                TextCreator.loadTextSymbols(ResourceManager.data, 0); //загрузка параметров
                TextCreator.loadTextSymbols(ResourceManager.data, 1); //загрузка параметров
                TextCreator.loadTextLines(ResourceManager.data); //загрузка текста и его параметров
                ResourceManager.getInterfaceData(ResourceManager.data); //загрузка параметров изображений интерфейса
                AllScreens.SymbolHeight = TextCreator.getSymbolHeight(1);
                AllScreens.SymbolWidth = TextCreator.getSymbolWidth(1, 10);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

            TextCreator.setColoredDigitsId();
            Main.main.initializeScreens();
            ResourceManager.loadAllInterfaceImages();
            ResourceManager.loadSettings();
        }

        Main.main.repaint();

        while(this.timer.getTimerState() != 0) {
            Thread.yield();
        }

        this.logo = null;
        ResourceManager.logoImageLoaded = true;
        ResourceManager.loadingState = 8;
    }

}
