package code;

import javax.microedition.lcdui.Graphics;

public class MenuScreen implements Screen 
{
   //Отвечает за отображение интерфейса и надписей над правым и
   //левым софткеем, а также в верхней части экрана. Но не за все, а за те, что
   //рисуются с заглавной буквы, тонким шрифтом? Странная логика
   public byte interfaceNumber;
   public short[][] replicsMassive;
   public Screen[] screenMassive;
   public byte[] screenIds;
   public Screen[] screenMassive2;
   public byte[] screenIds2;
   
   //id текста
   public short menuNameTextId;
   public short leftSoftTextId;
   public short rightSoftTextId;
   public byte drawingScreenId;
   
   //текстовая зона, центральный блок
   public static int textClipX; //55;
   public static int textClipY; //70;
   public static int textClipWidth; //125
   public static int textClipHeight; //180
   //зона заливки, как правило весь экран
   public static int menuClipX;
   public static int menuClipY;
   public static int menuClipWidth;
   public static int menuClipHight;

   public MenuScreen() 
   {
      setTextClipVariables();
   }

   public boolean onShow(byte screenId) 
   {
      this.menuNameTextId = this.replicsMassive[0][screenId];
      this.leftSoftTextId = this.replicsMassive[1][screenId];
      this.rightSoftTextId = this.replicsMassive[2][screenId];
      this.drawingScreenId = screenId;
      return true;
   }

   public void resetVariables() {}

   public void paint(Graphics graphics) 
   {
       //Заливаем всё чёрным фоном
       graphics.setClip(menuClipX, menuClipY, menuClipWidth, menuClipHight);
       graphics.setColor(0);
       graphics.fillRect(0, 0, 
               MainMenuScreen.scrWidth, 
               MainMenuScreen.scrHeight);
       ResourseManager.drawUserInterfaceItems(graphics,
               this.interfaceNumber, 0, 0);
       //Жёлтый цвет (цвет надписей по дефолту)
       graphics.setColor(15637809);
       
       //Надписи "Настройки", "Помощь", "Об игре", "Меню" слева вверху
       TextCreator.FindParametersnDrawText(0, this.menuNameTextId,
       AllScreens.menuNameXCoord, AllScreens.menuNameYCoord, 9);
       
       //"Нет" в меню звука, слева.
       TextCreator.FindParametersnDrawText(0, this.leftSoftTextId,
       AllScreens.leftSoftTextXCoord, AllScreens.leftSoftTextYCoord, 5);
       
       //"Да" в меню звука, справа. "Выбор", "Меню"
       TextCreator.FindParametersnDrawText(0, this.rightSoftTextId,
       AllScreens.rightSoftTextXCoord, AllScreens.rightsoftTextYCoord, 5);
   }

   public void keyPressed(int key) {} //заглушка

   private static void setTextClipVariables() 
   {
      textClipX = 55;
      textClipY = 70;
      textClipWidth = 125;
      textClipHeight = 180;
   }
}
