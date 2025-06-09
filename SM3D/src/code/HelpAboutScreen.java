package code;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class HelpAboutScreen extends MenuScreen {

   public short textId;
   private static final short[] ABOUT_TEXT_IDS = new short[]{(short)392, (short)393, (short)379, (short)380, (short)381, (short)382, (short)383, (short)384, (short)395, (short)396, (short)397};
   //id     text
   //392    S.T.A.L.K.E.R.
   //393    Version 1.1.0
   //379    (C) Developer:
   //380    Qplaze
   //381    www.qplaze.com
   //382    (C) Publisher:
   //383    Nomoc
   //384    www.nomoc.com
   public int yStart;
   public int textLinesCount;
   public Vector textLinesStartsEnds;


   public HelpAboutScreen() 
   {
      super.replicsMassive = new short[][]{{(short)0, (short)24, (short)25, (short)17}, {(short)0, (short)0, (short)0, (short)0}, {(short)0, (short)1, (short)1, (short)1}, {(short)0, (short)30, (short)31, (short)35}};
      //id      text
      //0       
      //24      help
      //25      about
      //17      message
      //0       
      //0       
      //0       
      //0       
      //0       
      //1       menu
      //1       menu
      //1       menu
      //0       
      //30      
      /*Help.Control:
      Soft key1 - exit to menu.
      Soft key2 - change weapon.
      1 - reload weapon.
      2,up - move the sight up.
      3 - fast first-aid kit.
      4,left - move the sight to the left.
      5,select - fire, confirm action.
      6,right - move the sight to the right.
      8,down - move the sight down.
      o - go back.
      # - telescopic sight (if available).*/
      //31      here we are
      //35      Too many players. Delete some record
      this.yStart = MenuScreen.textClipY;
      this.textLinesCount = 0;
      this.textLinesStartsEnds = new Vector();
   }

   public final void resetVariables() {
      super.screensTransitionLeftOption = new Screen[]{
          AllScreens.menu2, AllScreens.menu,
          AllScreens.menu, AllScreens.menu};
   }

   public final boolean onShow(byte screenId) {
      super.onShow(screenId);
      this.textId = super.replicsMassive[3][screenId];
      super.interfaceNumber = 44;
      this.textLinesCount = 0;
      this.yStart = MenuScreen.textClipY;
      return true;
   }

   private static void drawAboutText() 
   {
      for(byte id = 0; id < 11; ++id) 
      {
         int x = MainMenuScreen.scrWidth / 
                 2 - TextCreator.getWideLineWidth(1, ABOUT_TEXT_IDS[id]) / 2;
         TextCreator.drawLineByAnchor(1, ABOUT_TEXT_IDS[id], 
                 x, MenuScreen.textClipY + 
                         AllScreens.TEXT_LINE_HEIGHT_NOINDENT * id + 20, 0);
      }
   }

   public final void paint(Graphics graphics) 
   {
      MenuScreen.menuClipX = 0;
      MenuScreen.menuClipY = 0;
      MenuScreen.menuClipWidth = MainMenuScreen.scrWidth;
      MenuScreen.menuClipHight = MainMenuScreen.scrHeight;
      super.paint(graphics);
      //если окно "Об игре"
      if(this.textId == 31) 
      {
         drawAboutText();
      }
      //если окно "Помощь"
      else 
      {
         this.textLinesStartsEnds = TextCreator.splitOnLines(this.textId, MenuScreen.textClipWidth + 15, 1);
         this.textLinesCount = 0;
         graphics.setClip(MenuScreen.textClipX, MenuScreen.textClipY, MenuScreen.textClipWidth + 15, MenuScreen.textClipHeight);
         //Серый цвет текста(рамки)
         graphics.setColor(8421504);
         this.textLinesCount = this.textLinesStartsEnds.size();
         TextCreator.drawReplicInsideFrame(this.textId, 
                 MenuScreen.textClipX + 5, this.yStart, 0, 1, 
                 MasterCanvas.graphics, 0, -1, this.textLinesStartsEnds);
         graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
         this.drawScrollbar();
      }
   }

   private void drawScrollbar() 
   {
      int splitVertical = (this.textLinesCount + 1) * 
              TextCreator.getSymbolHeight(1) 
              - MenuScreen.textClipHeight;
      int currentYpos = Math.abs(this.yStart - MenuScreen.textClipY);
      int x = MenuScreen.textClipX + MenuScreen.textClipWidth + 20;
      int yOuter = ResourceManager.getRectangleParams(44, 4, 0)[1] + 5;
      int heightOuter = MenuScreen.textClipHeight + 25;
      //Серый цвет
      MasterCanvas.graphics.setColor(8421504);
      //Колба снаружи
      MasterCanvas.graphics.drawRect(x, yOuter, 5, heightOuter);
      int height = heightOuter * TextCreator.getSymbolHeight(1) 
              / splitVertical;
      int yInner = yOuter + heightOuter * currentYpos / splitVertical;
      //Водка внутри
      MasterCanvas.graphics.fillRect(x, yInner, 5, height);
   }

    public final void keyPressed(int key) {
        switch (key) {
            //Промотка текста вниз
            case 1:
                if (this.yStart < MenuScreen.textClipY) {
                    this.yStart += TextCreator.getSymbolHeight(1);
                }
                Main.main.repaint();
                return;
            //Возврат в главное меню
            case 3:
                Main.main.setScreen(super.screensTransitionLeftOption[super.drawingScreenId], (byte) 1);
            //Промотка текста вверх
            case 6:
                if (this.yStart + this.textLinesCount
                        * TextCreator.getSymbolHeight(1) > MenuScreen.textClipY
                        + MenuScreen.textClipHeight) {
                    this.yStart -= TextCreator.getSymbolHeight(1);
                }
                Main.main.repaint();
            default:
        }
    }
}
