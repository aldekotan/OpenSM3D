package code;

import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDletStateChangeException;

public final class MainMenuScreen implements Screen 
{
   private int prevMenuOptionId;
   private int numberOfScreens;
   public int defaultMenuOption = 1;
   public int menuOptionId;
   private int movingDirection;
   private Screen[] screenByOptionId;
   private byte[] screenIdByOptionId;
   private int newTextX;
   private int prevTextX;
   public static int scrWidth = 240;
   public static int scrHeight = 320;
   
   private static final byte[] screenIdsIfSavedBefore = new byte[]{(byte)4, (byte)9, (byte)10, (byte)1, (byte)1, (byte)2, (byte)15, (byte)3};
   private static Screen[] screensIfSavedBefore;
   
   private static final byte[] screenIdsNoSaves = new byte[]{(byte)4, (byte)9, (byte)1, (byte)1, (byte)2, (byte)15, (byte)3};
   private static Screen[] screensNoSaves;
   
    //textId|реплика  | screenId
    //20 продолжить      4
    //21 новая игра      9
    //22 загрузка игры   10
    //23 настройки       1
    //24 помощь          1
    //25 об игре         2
    //26 ещё игры        15
    //27 выход           3

   public MainMenuScreen() {
      this.menuOptionId = this.defaultMenuOption;
      this.movingDirection = -1;
      this.prevTextX = AllScreens.halfScrWidth;
      sleepForAWhile(50L);
      this.screenIdByOptionId = screenIdsIfSavedBefore;
      this.movingDirection = 4;
   }

   public final void resetVariables() {
      screensNoSaves = new Screen[]{
          AllScreens.loadingScreen, 
          AllScreens.loadingScreen, 
          AllScreens.settingsScreen, 
          AllScreens.helpAboutScreen, 
          AllScreens.helpAboutScreen, 
          this, 
          AllScreens.confirmScreen};
      screensIfSavedBefore = new Screen[]{
          AllScreens.loadingScreen, 
          AllScreens.loadingScreen,
          AllScreens.loadingScreen, 
          AllScreens.settingsScreen,
          AllScreens.helpAboutScreen, 
          AllScreens.helpAboutScreen, 
          this, 
          AllScreens.confirmScreen};
      this.screenByOptionId = screensIfSavedBefore;
   }

   public final void paint(Graphics graphics) 
   {
      switch(this.movingDirection) 
      {
      case 2:
         this.moveTextLeft(graphics);
         return;
      case 3:
         this.moveTextRight(graphics);
         return;
      case 4:
         this.drawMainMenu(graphics);
         return;
      case 5:
         new LoadingScreen(ResourceManager.interfaceImages[0]);
         this.movingDirection = 4;
      default:
      }
   }

   //Обрабатывает переключение пунктов главного меню
   public final void keyPressed(int key)
   {                                    
      MasterCanvas.instance.getGameAction(key);
      switch(key) 
      {
      case -5:
      case 53:
         Main.main.setScreen(this.screenByOptionId[this.menuOptionId], this.screenIdByOptionId[this.menuOptionId]);
         break;
      case -4:
      case 54:
         this.nextOption();//следующий пункт меню, текст уезжает влево
         break;
      case -3:
      case 52:
         this.previousOption();//предыдущий пункт меню, текст едет вправо
      }

      Main.main.repaint();
   }

   public final boolean onShow(byte screenId) 
   {
      switch(screenId) 
      {
      case 0:
         return false;
      case 1:
         SoundAndVibro.playSound(0);
         break;
      case 10:
         Main.main.destroyApp(false);

         Main.main.notifyDestroyed();
         return false;
      case 14:
         Main.main.showGameScreen();
         return false;
      case 15:
         try 
         {
            Main.main.platformRequest(AllScreens.MORE_GAMES_URL);
            Main.main.destroyApp(true);
         } 
         catch (Exception e) 
         {
            e.printStackTrace();
         }
      }

      this.checkSavedGame(Main.main.numberOfPlayers > 0);
      if(screenId == 16) 
      {
         SoundAndVibro.soundsEnabled = false;
         ResourceManager.saveSettings();
         ((SettingsScreen)AllScreens.settingsScreen).loadSettings();
      }

      return true;
   }

   private void nextOption() 
   {
      if(this.movingDirection != 2) //not left
      {
         this.prevMenuOptionId = this.menuOptionId;
         if(this.menuOptionId == this.numberOfScreens - 1) 
         {
            this.menuOptionId = this.defaultMenuOption;
         } 
         else 
         {
            ++this.menuOptionId;
         }

         if(this.movingDirection == 3) //right
         {
            this.prevTextX += this.newTextX;
            this.newTextX = this.prevTextX - this.newTextX;
            this.prevTextX -= this.newTextX;
         } 
         else 
         {
            this.newTextX = scrWidth + this.prevTextX;
         }

         this.movingDirection = 2; //left
      }
   }

   private void previousOption() 
   {
      if(this.movingDirection != 3) //not right
      {
         this.prevMenuOptionId = this.menuOptionId;
         //если мы не знаем, что было в прошлой опции
         if(this.menuOptionId == this.defaultMenuOption) 
         {
            this.menuOptionId = this.numberOfScreens - 1;
         } 
         else 
         {
            --this.menuOptionId;
         }

         if(this.movingDirection == 2) // left
         {
            this.prevTextX += this.newTextX;
            this.newTextX = this.prevTextX - this.newTextX;
            this.prevTextX -= this.newTextX;
         } 
         else 
         {
            this.newTextX = -scrWidth + this.prevTextX;
         }

         this.movingDirection = 3;//moveTextRight
      }
   }

   private static void drawMainMenuLogo(Graphics graphics) 
   {
      //цвет фона в главном меню игры
      graphics.setColor(0); 
      graphics.fillRect(0, 0, scrWidth, scrHeight);
      int x_coord = scrWidth / 2 - ResourceManager.interfaceImages[2].getWidth() / 2;
      int y_coord = scrHeight / 2 - ResourceManager.interfaceImages[2].getHeight() / 2;
      graphics.drawImage(ResourceManager.interfaceImages[2], x_coord, y_coord, 0);
   }

   private void drawMainMenu(Graphics graphics) 
   {
      drawMainMenuLogo(graphics);
      //жёлтый цвет текста. Когда-то использовался для отрисовки в меню?
      graphics.setColor(15637809);
      TextCreator.drawLineByAnchor(0, AllScreens.mainMenuTextIds[this.menuOptionId], AllScreens.halfScrWidth, AllScreens.textY, 9);
   }

   private void moveTextLeft(Graphics graphics) 
   {
      this.newTextX -= 20;
      this.prevTextX -= 20;
      sleepForAWhile(1L);
      drawMainMenuLogo(graphics);
      graphics.setClip(0, AllScreens.screenClipYstart, scrWidth, AllScreens.screenClipHeight);
      graphics.setColor(15637809);
      TextCreator.drawLineByAnchor(0, AllScreens.mainMenuTextIds[this.menuOptionId], this.newTextX, AllScreens.textY, 9);
      TextCreator.drawLineByAnchor(0, AllScreens.mainMenuTextIds[this.prevMenuOptionId], this.prevTextX, AllScreens.textY, 9);
      graphics.setClip(0, 0, scrWidth, scrHeight);
      if(this.newTextX > AllScreens.halfScrWidth) 
      {
         Main.main.repaint();
      } 
      else 
      {
         this.prevTextX = AllScreens.halfScrWidth;
         this.movingDirection = 4;//stop
         Main.main.repaint();
      }
   }

   private void moveTextRight(Graphics graphics) 
   {
      this.newTextX += 20;
      this.prevTextX += 20;
      sleepForAWhile(1L);
      drawMainMenuLogo(graphics);
      graphics.setClip(0, AllScreens.screenClipYstart, scrWidth, AllScreens.screenClipHeight);
      //cнова задаём жёлтый цвет, который не используется для текста
      graphics.setColor(15637809);
      TextCreator.drawLineByAnchor(0, AllScreens.mainMenuTextIds[this.menuOptionId], this.newTextX, AllScreens.textY, 9);
      TextCreator.drawLineByAnchor(0, AllScreens.mainMenuTextIds[this.prevMenuOptionId], this.prevTextX, AllScreens.textY, 9);
      graphics.setClip(0, 0, scrWidth, scrHeight);
      if(this.newTextX < AllScreens.halfScrWidth) 
      {
         Main.main.repaint();
      } 
      else 
      {
         this.prevTextX = AllScreens.halfScrWidth;
         this.movingDirection = 4;
         Main.main.repaint();
      }
   }

   private static void sleepForAWhile(long milliseconds) 
   {
      //long awakenTime = System.currentTimeMillis() + milliseconds;

       //while (System.currentTimeMillis() < awakenTime) {
           //Починил плавную перемотку 10.11
           try {
               Thread.sleep(milliseconds);
           } catch (InterruptedException ex) {
               ex.printStackTrace();
           }
       //}

   }

   private void checkSavedGame(boolean savedBefore) 
   {
      if(savedBefore) 
      {
         this.screenByOptionId = screensIfSavedBefore;
         this.screenIdByOptionId = screenIdsIfSavedBefore;
         AllScreens.mainMenuTextIds = AllScreens.menuTextIdSaveCreated;
         this.numberOfScreens = this.screenByOptionId.length;
      } 
      else 
      {
         this.screenByOptionId = screensNoSaves;
         this.screenIdByOptionId = screenIdsNoSaves;
         AllScreens.mainMenuTextIds = AllScreens.menuTextIdEmptySave;
         this.numberOfScreens = this.screenByOptionId.length;
      }
   }

}
