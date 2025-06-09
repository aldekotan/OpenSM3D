package code;

import javax.microedition.lcdui.Graphics;

public final class EmptySceneScreen implements Screen 
{
   //Понития не имею, зачем этот класс используется. (aldo 24)
   //Класс игровых меню
   public EmptySceneScreen(MasterInventoryScreen directLink) {}

   public final void resetVariables() {}

   public final void paint(Graphics graphics) 
   {
      ResourceManager.drawUserInterfaceItems(graphics, 50, 0, 0);
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      graphics.setColor(0xffffff);
      graphics.drawString("Empty scene", 
              MainMenuScreen.scrWidth / 2, 
              MainMenuScreen.scrHeight / 2, 17);
   }

   public final void keyPressed(int key) 
   {
      switch(key) 
      {
      case 3:
         Main.main.setScreen(AllScreens.pauseScreen, (byte)2);
      default:
         Main.main.repaint();
      }
   }

   public final boolean onShow(byte screenId) 
   {
      return true;
   }
}
