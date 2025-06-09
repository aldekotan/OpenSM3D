package code;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;


public final class PlayersRecords extends ListScreen 
{

   public byte[][] records = new byte[3][];


   public PlayersRecords() 
   {
      super.optionsTextId = new int[3];
   }

   public final boolean onShow(byte screenId) 
   {
      super.onShow(screenId);
      super.numberOfOptions = Main.main.numberOfPlayers;
      return true;
   }

   public final void drawItem(int item) 
   {
      TextCreator.drawTextByAnchor(0, this.records[item], 0, this.records[item].length, AllScreens.var_4ba + 2, MenuScreen.textClipY + AllScreens.SINGLE_TEXT_LINE_HEIGHT * item + 3, 0);
   }

   public final void addNewPlayerRecord(byte[] recordData) 
   {
      this.records[Main.main.numberOfPlayers] = recordData;
      if(++Main.main.numberOfPlayers > 3) 
      {
         Main.main.numberOfPlayers = 3;
      }

      super.numberOfOptions = Main.main.numberOfPlayers;
      super.by_var_2 = (byte)Math.min(MenuScreen.textClipHeight / AllScreens.SINGLE_TEXT_LINE_HEIGHT, super.numberOfOptions);
   }

   public final void deletePlayerRecord(byte id) 
   {
      if(id < Main.main.numberOfPlayers - 1) 
      {
         System.arraycopy(this.records, id + 1, this.records, id, Main.main.numberOfPlayers - id - 1);
      }

      --Main.main.numberOfPlayers;
      super.numberOfOptions = Main.main.numberOfPlayers;
      super.by_var_2 = (byte)Math.min(MenuScreen.textClipHeight / AllScreens.SINGLE_TEXT_LINE_HEIGHT, super.numberOfOptions);
      Main.main.currentPlayerRecordId = -1;
   }

   public final void keyPressed(int key_pressed) 
   {
      switch(key_pressed) 
      {
      case Command.CANCEL: //Right soft
      case Canvas.FIRE:
         Main.main.currentPlayerRecordId = (byte)super.selectedIndex;
         synchronized(this) 
         {
            ;
         }

         Main.main.setScreen(AllScreens.menu, (byte)14); //Load new game??
         return;
      case Command.OK: //Left soft
         synchronized(this) 
         {
            ;
         }

         Main.main.setScreen(AllScreens.menu, (byte)1); //Return to menu?
         Main.main.repaint();
         return;
      default:
         super.keyPressed(key_pressed);
      }
   }
}
