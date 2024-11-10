package code;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class ListScreen extends MenuScreen 
{

   public int numberOfOptions;
   public int selectedIndex;
   public int i_var_3;
   public byte by_var_1;
   public byte by_var_2;
   public byte redrawAnimOnly;
   private Timer timer;
   public Graphics graphics;
   public int[] i_varMassive_1;
   public Screen[] var_208;
   public byte[] by_varMassive;
   public static int[] var_284 = new int[]{42, 43};
   public static byte[] var_2e2 = new byte[]{(byte)0, (byte)0};
   public static int[] var_326 = new int[]{57, 58, 0, 50, 51, 56, 367};
   public static byte[] var_34a = new byte[]{(byte)14, (byte)1, (byte)0, (byte)3, (byte)5, (byte)14, (byte)3};
   public static int[][] var_377 = new int[][]{null, var_284, var_326, null};
   public static byte[][] var_3cc = new byte[][]{null, var_2e2, var_34a, null};
   public static Screen[] var_404;
   public static Screen[] var_429;
   public static Screen[][] var_47a;
   private Vector[] obj_vectorMassive;
   private int[] i_varMassive_2;
   private int[] i_varMassive_3;
   private int var_57e;
   private int var_588;


   public ListScreen() 
   {
      super.replicsMassive = new short[][]{{(short)0, (short)23, (short)37, (short)367, (short)41}, {(short)0, (short)6, (short)0, (short)0, (short)7}, {(short)0, (short)10, (short)10, (short)6, (short)11}};
      super.screenIdTransitionLeftOption = new byte[]{(byte)0, (byte)1, (byte)0, (byte)2, (byte)0};
      this.by_var_1 = 0;
      this.by_var_2 = (byte)Math.min(MenuScreen.textClipHeight / AllScreens.SINGLE_TEXT_LINE_HEIGHT, this.numberOfOptions);
      this.timer = new Timer(true, 100L);
      Main.main.repaint();
   }

   public final void resetVariables() 
   {
      var_404 = new Screen[]{AllScreens.menu2, AllScreens.menu2};
      var_429 = new Screen[]{AllScreens.menu, AllScreens.menu, AllScreens.menu2, AllScreens.masterInventory, AllScreens.masterInventory, AllScreens.menu, AllScreens.pauseScreen};
      var_47a = new Screen[][]{null, var_404, var_429, null};
      super.screensTransitionLeftOption = new Screen[]{AllScreens.menu2, AllScreens.menu, AllScreens.menu2, AllScreens.pauseScreen, AllScreens.menu2};
   }

   public boolean onShow(byte var1) 
   {
      super.onShow(var1);
      if(var1 == 3) {
         this.numberOfOptions = Scripts.var_2367;
         this.i_varMassive_1 = Scripts.var_2314;
         this.sub_88();
      } else if(!(this instanceof PlayersRecords)) {
         this.i_varMassive_1 = var_377[var1];
         this.var_208 = var_47a[var1];
         this.by_varMassive = var_3cc[var1];
         this.numberOfOptions = this.i_varMassive_1.length;
      }

      super.interfaceNumber = 44;
      this.selectedIndex = 0;
      this.by_var_1 = 0;
      int var2;
      if(var1 == 3) {
         var2 = TextCreator.getHeightFromTextParamMassive(0) * 3;
      } else {
         var2 = 0;
      }

      this.by_var_2 = (byte)Math.min(MenuScreen.textClipHeight / (AllScreens.SINGLE_TEXT_LINE_HEIGHT + var2), this.numberOfOptions);
      this.i_var_3 = 0;
      this.redrawAnimOnly = 4;
      this.timer.resetTimer();
      synchronized(this) {
         this.notifyAll();
      }

      AllScreens.var_4ba = ResourseManager.getUIElementXcoord(45, 0);
      return true;
   }

   private void sub_88() 
   {
      this.var_57e = 0;
      this.var_588 = 0;
      this.i_varMassive_2 = new int[Scripts.var_2367];
      this.i_varMassive_3 = new int[Scripts.var_2367];
      this.obj_vectorMassive = new Vector[Scripts.var_2367];

      for(byte var1 = 0; var1 < Scripts.var_2367; ++var1) 
      {
         this.obj_vectorMassive[var1] = TextCreator.splitOnLines(this.i_varMassive_1[var1], 80, 0);
         if(var1 > 0) 
         {
            this.i_varMassive_2[var1] = this.i_varMassive_2[var1 - 1] + TextCreator.getHeightFromTextParamMassive(0) * (this.obj_vectorMassive[var1].size() - 1);
         } 
         else 
         {
            this.i_varMassive_2[var1] = TextCreator.getHeightFromTextParamMassive(0) * (this.obj_vectorMassive[var1].size() - 1);
         }

         this.i_varMassive_3[var1] = TextCreator.getHeightFromTextParamMassive(0) * (this.obj_vectorMassive[var1].size() - 1);
         this.var_57e += this.i_varMassive_2[var1];
         this.var_588 += this.obj_vectorMassive[var1].size();
      }

   }

   public void drawItem(int var1) 
   {//отрисовка реплик меню паузы или настроек
      if(super.drawingScreenId == 3) 
      {
         int var3 = var1 > 0?this.i_varMassive_2[var1 - 1]:0;
         TextCreator.drawReplicInsideFrame(this.i_varMassive_1[var1], AllScreens.var_4ba + 2, MenuScreen.textClipY + this.i_var_3 + AllScreens.SINGLE_TEXT_LINE_HEIGHT * var1 + 3 + var3, 0, 0, MasterCanvas.graphics, 0, -1, this.obj_vectorMassive[var1]);
      } 
      else 
      {
         byte var2 = 0;
         if(super.drawingScreenId == 2 && (var1 == 5 && !Scripts.var_2204 || var1 == 6 && Scripts.var_2367 == 0)) {
            var2 = 1;
         }

         TextCreator.FindParametersnDrawText(var2, this.i_varMassive_1[var1], AllScreens.var_4ba + 2, MenuScreen.textClipY + this.i_var_3 + AllScreens.SINGLE_TEXT_LINE_HEIGHT * var1 + 3 + 20, 0);
      }
   }

   public final void updateAnimatedImage() 
   {
      short var1 = 0;
      if(this.numberOfOptions != 0) {
         switch(this.timer.getTimerState()) {
         case 0:
            var1 = 132;
            break;
         case 1:
            var1 = 133;
            break;
         case 2:
            var1 = 134;
            break;
         case 3:
            var1 = 135;
            break;
         case 4:
            var1 = 136;
            break;
         case 5:
            var1 = 137;
            break;
         case 6:
            var1 = 138;
            this.timer.resetTimer();
         }

         int var3;
         if(super.drawingScreenId == 3) {
            var3 = this.selectedIndex > 0?this.i_varMassive_2[this.selectedIndex - 1]:0;
         } else {
            var3 = 20;
         }

         ResourseManager.DrawInterfaceImageToSelectedRegion(this.graphics, var1, AllScreens.var_4ba - 2 - (ResourseManager.ReturnWidthOfInterfaceImage(132) >> 1), MenuScreen.textClipY + this.i_var_3 + this.selectedIndex * AllScreens.SINGLE_TEXT_LINE_HEIGHT + 3 + var3, 17);
      }

   }

   public final void paint(Graphics graphics) 
   {
      this.graphics = graphics;
      switch(this.redrawAnimOnly) {
      case 4:
         MenuScreen.menuClipX = 0;
         MenuScreen.menuClipY = 0;
         MenuScreen.menuClipWidth = MainMenuScreen.scrWidth;
         MenuScreen.menuClipHight = MainMenuScreen.scrHeight;
         super.paint(graphics);
         //рисуем надпись "Назад" слева
         if(super.drawingScreenId == 1) {
            PlayerHUD.drawSoftButtonNames(1, 0, 6, false);
         }

         //Серый цвет шрифта
         graphics.setColor(8421504);
         graphics.setClip(MenuScreen.textClipX, MenuScreen.textClipY, MenuScreen.textClipWidth, MenuScreen.textClipHeight);

         for(int n = 0; n < this.numberOfOptions; ++n) {
            this.drawItem(n);
         }

         this.redrawAnimOnly = 5;
      case 5:
         MenuScreen.menuClipX = MenuScreen.textClipX;
         MenuScreen.menuClipY = MenuScreen.textClipY;
         MenuScreen.menuClipWidth = AllScreens.var_4ba - MenuScreen.textClipX;
         MenuScreen.menuClipHight = MenuScreen.textClipHeight;
         super.paint(graphics);
         this.updateAnimatedImage();
      default:
         Main.main.repaint();
      }
   }

   public void keyPressed(int key_number_pressed)
   {
      switch(key_number_pressed) 
          {
          case 1:
          case 50:
             int var8;
             if(this.selectedIndex != 0) 
             {
                --this.selectedIndex;
                boolean var9 = false;
                if(super.drawingScreenId == 3) {
                   var8 = this.i_varMassive_3[this.selectedIndex];
                } else {
                   var8 = 0;
                }

                if(this.selectedIndex < this.by_var_1) {
                   --this.by_var_1;
                   this.i_var_3 += AllScreens.SINGLE_TEXT_LINE_HEIGHT + var8;
                   this.redrawAnimOnly = 4;
                }

                if(super.drawingScreenId == 2 && this.selectedIndex == 2) {
                   --this.selectedIndex;
                }

                if(super.drawingScreenId == 3) {
                   this.selectedIndex = Math.min(Scripts.var_2367 - 1, this.selectedIndex);
                }
             } 
             else
             {
                this.selectedIndex = this.numberOfOptions - 1;
                if(super.drawingScreenId == 3) {
                   this.selectedIndex = Math.min(Scripts.var_2367 - 1, this.selectedIndex);
                }

                this.by_var_1 = (byte)Math.max(this.selectedIndex - this.by_var_2, 0);
                if(super.drawingScreenId == 3) {
                   var8 = TextCreator.getHeightFromTextParamMassive(0) * 3;
                } else {
                   var8 = 0;
                }

                this.i_var_3 = -this.by_var_1 * (AllScreens.SINGLE_TEXT_LINE_HEIGHT + var8);
                this.redrawAnimOnly = 4;
             }

             Main.main.repaint();
             return;
          case 3:
          case 8:
          case 53:
             if(this.i_varMassive_1[this.selectedIndex] == 0) 
             {
                return;
             } 
             else 
             {
                synchronized(this) 
                {
                   ;
                }

                if(super.drawingScreenId == 3) 
                {
                   Main.main.setScreen(super.screensTransitionLeftOption[super.drawingScreenId], super.screenIdTransitionLeftOption[super.drawingScreenId]);
                   return;
                } 
                else 
                {
                   if(super.drawingScreenId == 2 && this.selectedIndex == 1) 
                   {
                      RenderEngine.SetToNullAllWorldnMeshMassives();
                      ResourseManager.loadInterfaceImage(2);
                   }

                   switch(this.selectedIndex) {
                   case 0:
                      RenderEngine.gamePaused = false;
                      Main.main.setScreen(this.var_208[this.selectedIndex], this.by_varMassive[this.selectedIndex]);
                      ResourseManager.runGarbageCollector();
                      return;
                   case 5:
                      if(Scripts.var_2204) {
                         Scripts.sub_72f();
                         Main.main.setScreen(this.var_208[this.selectedIndex], this.by_varMassive[this.selectedIndex]);
                         return;
                      }

                      return;
                   case 6:
                      if(Scripts.var_2367 != 0) {
                         Main.main.setScreen(this.var_208[this.selectedIndex], this.by_varMassive[this.selectedIndex]);
                         return;
                      }

                      return;
                   default:
                      Main.main.setScreen(this.var_208[this.selectedIndex], this.by_varMassive[this.selectedIndex]);
                      Main.main.repaint();
                   }
                }
             }
          case 4:
             if(super.drawingScreenId == 3) {
                return;
             }

             synchronized(this) {
                ;
             }

             Main.main.setScreen(super.screensTransitionLeftOption[super.drawingScreenId], super.screenIdTransitionLeftOption[super.drawingScreenId]);
             return;
          case 6:
          case 56:
             if(this.selectedIndex != this.numberOfOptions - 1) {
                byte var2 = 0;
                if(super.drawingScreenId == 3) {
                   var2 = 1;
                }

                if(this.selectedIndex == this.by_var_1 + this.by_var_2 - var2) {
                   ++this.by_var_1;
                   int var3;
                   if(super.drawingScreenId == 3) {
                      var3 = this.i_varMassive_3[this.by_var_1 - 1];
                   } else {
                      var3 = 0;
                   }

                   this.i_var_3 -= AllScreens.SINGLE_TEXT_LINE_HEIGHT + var3;
                   this.redrawAnimOnly = 4;
                }

                ++this.selectedIndex;
                if(super.drawingScreenId == 2 && this.selectedIndex == 2) {
                   ++this.selectedIndex;
                }

                if(super.drawingScreenId == 3) {
                   this.selectedIndex = Math.min(Scripts.var_2367 - 1, this.selectedIndex);
                }
             } 
             else {
                this.selectedIndex = 0;
                this.by_var_1 = (byte)this.selectedIndex;
                this.i_var_3 = 0;
                this.redrawAnimOnly = 4;
             }

             Main.main.repaint();
          default:
          }
   }

}
