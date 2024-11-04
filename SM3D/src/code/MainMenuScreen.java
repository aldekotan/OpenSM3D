package code;

import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDletStateChangeException;

public final class MainMenuScreen implements Screen 
{
   private int prevTextId;
   private int numberOfScreens;
   public int defaultMenuId = 1;
   public int menuTextId;
   private int movingDirection;
   private Screen[] screenByTextId;
   private byte[] var_24a;
   private int newTextX;
   private int prevTextX;
   public static int scrWidth = 240;
   public static int scrHeight = 320;
   
   private static final byte[] var_3d0 = new byte[]{(byte)4, (byte)9, (byte)10, (byte)1, (byte)1, (byte)2, (byte)15, (byte)3};
   /*
   
   */
   private static Screen[] screensWithLoadPrevGame;
   
   private static final byte[] var_43f = new byte[]{(byte)4, (byte)9, (byte)1, (byte)1, (byte)2, (byte)15, (byte)3};
   /*
   
   */
   private static Screen[] screensWithJustNewGame;


   public MainMenuScreen() {
      this.menuTextId = this.defaultMenuId;
      this.movingDirection = -1;
      this.prevTextX = AllScreens.halfScrWidth;
      sub_1c0(50L);
      this.var_24a = var_3d0;
      this.movingDirection = 4;
   }

   public final void resetVariables() {
      screensWithJustNewGame = new Screen[]{AllScreens.loadingScreen, 
          AllScreens.loadingScreen, 
          AllScreens.settingsScreen, 
          AllScreens.helpAboutScreen, 
          AllScreens.helpAboutScreen, 
          this, 
          AllScreens.confirmScreen};
      screensWithLoadPrevGame = new Screen[]{AllScreens.loadingScreen, 
          AllScreens.loadingScreen,
          AllScreens.loadingScreen, 
          AllScreens.settingsScreen,
          AllScreens.helpAboutScreen, 
          AllScreens.helpAboutScreen, 
          this, 
          AllScreens.confirmScreen};
      this.screenByTextId = screensWithLoadPrevGame;
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
         new LoadingScreen(ResourseManager.interfaceImages[0]);
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
         Main.main.setScreen(this.screenByTextId[this.menuTextId], this.var_24a[this.menuTextId]);
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

   public final boolean onShow(byte var1) 
   {
      switch(var1) 
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
         catch (Exception var4) 
         {
            var4.printStackTrace();
         }
      }

      this.checkSavedGame(Main.main.numberOfPlayers > 0);
      if(var1 == 16) 
      {
         SoundAndVibro.soundsEnabled = false;
         ResourseManager.saveGameSettings();
         ((SettingsScreen)AllScreens.settingsScreen).loadSettings();
      }

      return true;
   }

   private void nextOption() 
   {
      if(this.movingDirection != 2) //not left
      {
         this.prevTextId = this.menuTextId;
         if(this.menuTextId == this.numberOfScreens - 1) 
         {
            this.menuTextId = this.defaultMenuId;
         } 
         else 
         {
            ++this.menuTextId;
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
         this.prevTextId = this.menuTextId;
         //если мы не знаем, что было в прошлой опции
         if(this.menuTextId == this.defaultMenuId) 
         {
            this.menuTextId = this.numberOfScreens - 1;
         } 
         else 
         {
            --this.menuTextId;
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
      int x_coord = scrWidth / 2 - ResourseManager.interfaceImages[2].getWidth() / 2;
      int y_coord = scrHeight / 2 - ResourseManager.interfaceImages[2].getHeight() / 2;
      graphics.drawImage(ResourseManager.interfaceImages[2], x_coord, y_coord, 0);
   }

   private void drawMainMenu(Graphics graphics) 
   {
      drawMainMenuLogo(graphics);
      //жёлтый цвет текста. Когда-то использовался для отрисовки в меню?
      graphics.setColor(15637809);
      TextCreator.FindParametersnDrawText(0, AllScreens.var_47[this.menuTextId], AllScreens.halfScrWidth, AllScreens.textY, 9);
   }

   private void moveTextLeft(Graphics graphics) 
   {
      this.newTextX -= 20;
      this.prevTextX -= 20;
      sub_1c0(1L);
      drawMainMenuLogo(graphics);
      graphics.setClip(0, AllScreens.var_2a8, scrWidth, AllScreens.var_231);
      graphics.setColor(15637809);
      TextCreator.FindParametersnDrawText(0, AllScreens.var_47[this.menuTextId], this.newTextX, AllScreens.textY, 9);
      TextCreator.FindParametersnDrawText(0, AllScreens.var_47[this.prevTextId], this.prevTextX, AllScreens.textY, 9);
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
      sub_1c0(1L);
      drawMainMenuLogo(graphics);
      graphics.setClip(0, AllScreens.var_2a8, scrWidth, AllScreens.var_231);
      //cнова задаём жёлтый цвет, который не используется для текста
      graphics.setColor(15637809);
      TextCreator.FindParametersnDrawText(0, AllScreens.var_47[this.menuTextId], this.newTextX, AllScreens.textY, 9);
      TextCreator.FindParametersnDrawText(0, AllScreens.var_47[this.prevTextId], this.prevTextX, AllScreens.textY, 9);
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

   private static void sub_1c0(long var0) 
   {
      long var2 = System.currentTimeMillis() + var0;

      while(System.currentTimeMillis() < var2) 
      {
         ;
      }

   }

   private void checkSavedGame(boolean savedBefore) 
   {
      if(savedBefore) 
      {
         this.screenByTextId = screensWithLoadPrevGame;
         this.var_24a = var_3d0;
         AllScreens.var_47 = AllScreens.var_f1;
         this.numberOfScreens = this.screenByTextId.length;
      } 
      else 
      {
         this.screenByTextId = screensWithJustNewGame;
         this.var_24a = var_43f;
         AllScreens.var_47 = AllScreens.var_a1;
         this.numberOfScreens = this.screenByTextId.length;
      }
   }

}
