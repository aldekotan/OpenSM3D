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
      AllScreens.loadingScreen = loadingScreen = new LoadingScreen(ResourceManager.interfaceImages[0]);
      AllScreens.recordsScreen = recordsScreen = new PlayersRecords();
      AllScreens.settingsScreen = settingsScreen = new SettingsScreen();
      gameScreen = new GameScreen();
      display.setCurrent(canvas);
      setScreen(loadingScreen, (byte)0);
      repaint();
   }

   public final void initializeScreens() 
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
         GameScene.gamePaused = false;
         System.gc();
         main.repaint();
      }

   }

   public final void pauseApp() 
   {
      if(!MasterCanvas.paused2) 
      {
         MasterCanvas.paused = true;
         GameScene.gamePaused = true;
         SoundAndVibro.stopPlayingSound();
         MasterCanvas.paused2 = true;
         System.gc();
         Keys.reset();
      }
   }

   public final void destroyApp(boolean var1) 
   {
      ResourceManager.saveSettings();
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

   /**В данный момент отсылается на KeyboardScreen, 
    * но там не используется */
   public final void addPlayerRecord(byte[] nameText, byte length) {
      byte[] recordData = new byte[length];
      System.arraycopy(nameText, 0, recordData, 0, length);
      ((PlayersRecords)this.recordsScreen).addNewPlayerRecord(recordData);
   }

   public final byte[][] getPlayersRecords() 
   {
      return ((PlayersRecords)recordsScreen).records;
   }

   public final void repaint() 
   {
      this.canvas.repaint();
   }
   
   public void vibrate(int duration) {
       display.vibrate(duration);
   }
}
