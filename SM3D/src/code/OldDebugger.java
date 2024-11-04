package code;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

//дебаггер, мать вашу
public final class OldDebugger implements Screen 
{ 
   private static final Font FONT = Font.getFont(0, 1, 8);
   private static final int FONT_SMALL_HEIGHT = FONT.getHeight();
   public static final byte FONT_MEDIUM_HEIGHT = (byte)(FONT_SMALL_HEIGHT + 6);
   private static int debuggersTotal;
   private static int keyDebuggerId;
   private static OldDebugger[] debuggers = new OldDebugger[10];
   private String debuggerName;
   private String[] debuggerNames = new String[10];
   private static boolean noIndent;

   private OldDebugger(String name) 
   {
      this.debuggerName = name;
      if(debuggersTotal == 10) 
      {
         OldDebugger[] newDebuggers = new OldDebugger[debuggersTotal + 10];
         System.arraycopy(debuggers, 0, newDebuggers, 0, debuggers.length);
         debuggers = newDebuggers;
      }

      debuggers[debuggersTotal++] = this;
      this.setDebuggerInfo("Empty debugger", 0);
   }

   public final void resetVariables() {}

   public final void paint(Graphics graphics) 
   {
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      //Заливаем весь экран белым
      graphics.setColor(16777215);
      graphics.fillRect(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      //Текст делаем чёрно-серым
      graphics.setColor(2105376);
      int debId;
      int textHeight;
      //Отображаем на экране все счётчики и показатели
      if(noIndent) {
         for(debId = 0; debId < debuggersTotal; ++debId) 
         {
            textHeight = debId * (FONT_MEDIUM_HEIGHT + 4) + 10;
            if(debId == keyDebuggerId) 
            {
               graphics.drawRect(10, textHeight, MainMenuScreen.scrWidth - 20, FONT_MEDIUM_HEIGHT);
            }

            graphics.drawString(debuggers[debId].debuggerName, 15, textHeight + 3, 0);
         }
      } 
      else 
      {
         graphics.drawString(this.debuggerName, MainMenuScreen.scrWidth >> 1, 13, 17);

         for(debId = 0; debId < this.debuggerNames.length; ++debId) {
            if(this.debuggerNames[debId] != null) {
               textHeight = (debId + 1) * (FONT_MEDIUM_HEIGHT + 4) + 10;
               graphics.drawRect(10, textHeight, MainMenuScreen.scrWidth - 20, FONT_MEDIUM_HEIGHT);
               graphics.drawString(this.debuggerNames[debId], 15, textHeight + 3, 0);
            }
         }
      }

      graphics.drawString("free:" + Runtime.getRuntime().freeMemory() / 1000L, 80, 30, 0);
   }

   public final void keyPressed(int key) 
   {
      //Банальная проверка кодов клавиш, для настройки под разные телефоны
      System.out.println("Debugger.keyPressed code = " + key);
      int debugActionNumber = MasterCanvas.instance.getGameAction(key);
      if(noIndent) {
         System.out.println(" isChoise == true");
         //Не уверен, что этот дебагер отвечает за клавиши, но в других местах
         //с ним связанную логику я не встретил
         switch(debugActionNumber) {
         case 1:
            if(keyDebuggerId != 0) {
               --keyDebuggerId;
            } else {
               keyDebuggerId = debuggersTotal - 1;
            }

            Main.main.repaint();
            break;
         case 6:
            if(keyDebuggerId != debuggersTotal - 1) {
               ++keyDebuggerId;
            } else {
               keyDebuggerId = 0;
            }

            Main.main.repaint();
            break;
         case 8:
            Main.main.setDebugScreen(debuggers[keyDebuggerId], (byte)0);
            noIndent = false;
         }
      //Включаем отступы между строками текста, для большей читаемости
      } else if(debugActionNumber == 4) {
         noIndent = true;
         Main.main.repaint();
      //Обнуляем всё к чертям собачьим
      } else if(debugActionNumber == 42) {
         for(int id = 0; id < this.debuggerNames.length; ++id) {
            this.debuggerNames[id] = null;
         }
      }

      if(key == 48) {
         System.out.println("Deleting all RS in Debugger");
         String[] recordStoreNames = RecordStore.listRecordStores();

         try {
            int i = 0;

            while(i < recordStoreNames.length) {
               System.out.println("Deleting of " + recordStoreNames[i] + " recordstore");
               RecordStore recStore = RecordStore.openRecordStore(recordStoreNames[i], false);

               try {
                  while(true) {
                     recStore.closeRecordStore();
                  }
               } catch (RecordStoreNotOpenException err) {
                  err.printStackTrace();
                  RecordStore.deleteRecordStore(recordStoreNames[i]);
                  ++i;
               }
            }
         } catch (RecordStoreException var8) {
            ;
         }

         Main.main.numberOfPlayers = 0;
         Main.main.currentPlayerRecordId = -1;
      }

   }

   //При открытии окна - сворачиваем размер текста
   public final boolean onShow(byte screenId) {
      noIndent = true;
      return true;
   }

   private void setDebuggerInfo(String info, int debuggerId) 
   {
      if(debuggerId < 10) 
      {
         this.debuggerNames[debuggerId] = info;
      }

   }

   //Пересоздаём дебагеры
   //Похоже на счётчики производительности, которые отрабатывали
   //с определёнными интервалами
   public static OldDebugger restartDebuggers(String name) 
   {
      OldDebugger debugger = null;
      if(debuggersTotal == 0) 
      {
         return new OldDebugger(name);
      } 
      else 
      {
         try 
         {
            int count = 0;

            while(true) 
            {
               if(count >= debuggersTotal) 
               {
                  debugger = new OldDebugger(name);
                  break;
               }

               if(debuggers[count].debuggerName.equals(name)) 
               {
                  debugger = debuggers[count];
                  break;
               }

               ++count;
            }
         } 
         catch (Exception exc) 
         {
            debugger = new OldDebugger(name);
         }

         return debugger;
      }
   }

}
