package code;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Main extends MIDlet 
{ //крик души

   public static Main main;
   
   private static Display display;
   private MasterCanvas canvas;
   
   private Screen menu;
   private Screen loadingScreen;
   private Screen gameScreen;
   private Screen masterInventory;
   
   private MenuScreen confirmScreen;
   MenuScreen keyboardScreen;
   private MenuScreen helpScreen;
   private MenuScreen pauseScreen;
   private MenuScreen settingsScreen;
   private MenuScreen recordsScreen;
   
   public byte currentPlayerRecordId = -1;
   public byte numberOfPlayers;
   public static Screen currentScreen;
   public static byte currentScreenId;


   public Main() 
   {
      main = this;
      OldDebugger.restartDebuggers("players");
      display = Display.getDisplay(main);
      canvas = new MasterCanvas();
      AllScreens.loadingScreen = loadingScreen = new LoadingScreen(ResourseManager.interfaceImages[0]);
      AllScreens.recordsScreen = recordsScreen = new PlayersRecords();
      AllScreens.settingsScreen = settingsScreen = new SettingsScreen();
      gameScreen = new GameScreen();
      display.setCurrent(canvas);
      setScreen(loadingScreen, (byte)0);
      repaint();
   }

   public final void sub_2c() 
   {
      AllScreens.confirmScreen = confirmScreen = new CentralText();
      keyboardScreen = new KeyboardScreen();
      AllScreens.helpAboutScreen = helpScreen = new HelpAboutScreen();
      AllScreens.pauseScreen = pauseScreen = new ListScreen();
      AllScreens.masterInventory = masterInventory = new MasterInventoryScreen();
      AllScreens.menu = menu = new MainMenuScreen();
      AllScreens.menu2 = AllScreens.menu; //why?? useless. todo remove
      
      loadingScreen.resetVariables();
      confirmScreen.resetVariables();
      keyboardScreen.resetVariables();
      helpScreen.resetVariables();
      pauseScreen.resetVariables();
      settingsScreen.resetVariables();
      masterInventory.resetVariables();
      menu.resetVariables();
   }

   public final void startApp() 
   {
      if(MasterCanvas.paused) 
      {
         MasterCanvas.paused2 = false;
         MasterCanvas.paused = false;
         RenderEngine.gamePaused = false;
         System.gc();
         main.repaint();
      }

   }

   public final void pauseApp() 
   {
      if(!MasterCanvas.paused2) 
      {
         MasterCanvas.paused = true;
         RenderEngine.gamePaused = true;
         SoundAndVibro.stopPlayingSound();
         MasterCanvas.paused2 = true;
         System.gc();
         Keys.reset();
      }
   }

   public final void destroyApp(boolean var1) 
   {
      ResourseManager.saveGameSettings();
      this.notifyDestroyed();
   }

   public final void setScreen(Screen scr, byte screenId) 
   {
      currentScreen = scr;
      currentScreenId = screenId;
      
      if(scr.onShow(screenId)) 
      {
         canvas.currentScreen = scr;
         canvas.repaint();
         System.gc();
      }
   }

   public final void reloadCurrentScreen()
   {
      this.setScreen(currentScreen, currentScreenId);
   }

   public final void showGameScreen() 
   {
      canvas.currentScreen = this.gameScreen;
   }

   public final void setDebugScreen(Screen scr, byte screenId) 
   {
      scr.onShow(screenId);
      canvas.currentScreen = scr;
      canvas.repaint();
      System.gc();
   }

   public final void sub_175(byte[] var1, byte var2) {
      byte[] recordData = new byte[var2];
      System.arraycopy(var1, 0, recordData, 0, var2);
      ((PlayersRecords)this.recordsScreen).addNewPlayerRecord(recordData);
   }

   public final byte[][] sub_1d8() 
   {
      return ((PlayersRecords)recordsScreen).var_56;
   }

   public final void repaint() 
   {
      this.canvas.repaint();
   }
   
   public void vibrate(int duration) {
       display.vibrate(duration);
   }
}
