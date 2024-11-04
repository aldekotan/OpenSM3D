package code;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public final class SettingsScreen extends ListScreen 
{

   public byte[] settings = new byte[]{(byte)1, (byte)1};


   public final void loadSettings() 
   {
      this.settings[0] = (byte) (SoundAndVibro.soundsEnabled ? 1 : 0);
      this.settings[1] = (byte) (SoundAndVibro.vibroEnabled ? 1 : 0);
   }

   public final void keyPressed(int key) 
   {
      if((key == Command.CANCEL || key == Canvas.FIRE) && settings[super.selectedIndex] != -1) 
      {
         this.settings[super.selectedIndex] = (byte)(this.settings[super.selectedIndex] == 0?1:0);
         
         SoundAndVibro.soundsEnabled = settings[0] == 1;
         SoundAndVibro.vibroEnabled = settings[1] == 1;
         
         if(!SoundAndVibro.soundsEnabled) 
         {
            SoundAndVibro.stopPlayingSound();
         }

         super.redrawAnimOnly = 4;//можно рисовать интерфейс, если 5 - то анимку
      }
      //Хардресет сейва с прогрессом игры
      else if((key == 3 || key == 8) && this.settings[super.selectedIndex] == -1) 
      {
         try 
         {
            RecordStore.deleteRecordStore("qpmrsp");
         } 
         catch (RecordStoreException error) 
         {
            error.printStackTrace();
         }

         ((PlayersRecords)AllScreens.recordsScreen).deletePlayerRecord(Main.main.currentPlayerRecordId);
         Main.main.setScreen(AllScreens.loadingScreen, (byte)6);
      } 
      else if(key == 4) 
      {
         Main.main.setScreen(AllScreens.loadingScreen, (byte)3);
      } 
      else 
      {
         super.keyPressed(key);
      }

      Main.main.repaint();
   }

   //надписи "вкл выкл" справа от опций настроек
   public final void drawItem(int settingsId) 
   {
      super.drawItem(settingsId);
      if(this.settings[settingsId] != -1) 
      {
         //textId: 61 on; 62 off;
         TextCreator.FindParametersnDrawText(1, 
                 this.settings[settingsId] == 0?62:61, 
                 MenuScreen.textClipX + MenuScreen.textClipWidth, 
                 MenuScreen.textClipY + 20 + 
                         AllScreens.SINGLE_TEXT_LINE_HEIGHT * settingsId + 3, 2);
      }
   }
}
