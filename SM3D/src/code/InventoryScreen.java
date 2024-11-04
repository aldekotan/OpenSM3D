package code;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public final class InventoryScreen implements Screen 
{

   public ItemDescriptionScreen itemDscrScreen;
   public ItemsInteraction itemsInteraction;
   public MasterInventoryScreen masterInvScreen;


   public InventoryScreen(MasterInventoryScreen directLink) 
   {
      this.masterInvScreen = directLink;
   }

   public final void resetVariables() {}

   public static short getItemDescrTextId(short itemId) 
   {
      return (short)(311 + (itemId - 101));
   }

   public final void paint(Graphics graphics) 
   {
      //Отрисовка основы UI и задание рабочей области
      ResourseManager.drawUserInterfaceItems(graphics, 38, 0, 0);
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      
      //Рисуем кнопки. id 6: Назад
      PlayerHUD.drawSoftButtonNames(0, 1, 6, true);
      ResourseManager.drawUserInterfaceItems(MasterCanvas.graphics, 78, 0, 0);
      ResourseManager.drawUserInterfaceItems(MasterCanvas.graphics, 80, 0, 0);

      //Рисуем поле с текущим/максимальным переносимым весом
      byte[] currentPlayerWeight = TextCreator.CreateTextMassiveForNumber(Scripts.ActorCurrentWeight);
      //"кг/"
      byte[] kgSlash = TextCreator.CopyReplicToNewMassive(65);
      byte[] leftPartText = TextCreator.combineTextMassives(currentPlayerWeight, kgSlash);
      byte[] maxPlayerWeight = TextCreator.CreateMassiveWithRankLength(Scripts.playerMaxWeight / 10);
      //"кг"
      byte[] kg = TextCreator.CopyReplicToNewMassive(64); 
      byte[] rightPartText = TextCreator.combineTextMassives(maxPlayerWeight, kg);
      byte[] finalText = TextCreator.combineTextMassives(leftPartText, rightPartText);
      int x_dest = ResourseManager.getUIElementXcoord(39, 20);
      int y_dest = ResourseManager.getUIElementYcoord(39, 20);
      TextCreator.DrawTextMassiveWithAnchor(1, finalText, 
              0, finalText.length, x_dest, y_dest, 5);

      //Поле с весом выбранного предмета (дописываем КГ в конце)
      finalText = TextCreator.combineTextMassives(TextCreator.CreateTextMassiveForNumber(Scripts.getItemWeight(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem])), TextCreator.CopyReplicToNewMassive(64));
      x_dest = ResourseManager.getUIElementXcoord(39, 19);
      y_dest = ResourseManager.getUIElementYcoord(39, 19);
      TextCreator.DrawTextMassiveWithAnchor(1, finalText, 
              0, finalText.length, x_dest, y_dest, 5);
      
      
      short[] moneyText;
      //106 - символ доллара
      (moneyText = TextCreator.surroundNumberWithChars(Scripts.CurrentActorMoney,
              false))[moneyText.length - 1] = 106;
      x_dest = ResourseManager.getUIElementXcoord(39, 21);
      y_dest = ResourseManager.getUIElementYcoord(39, 21);
      TextCreator.drawColoredText(moneyText, x_dest, y_dest, 3);
      
      PlayerHUD.drawDamageIndicatorAndHealthBar(true);
      
      //Если выбран экипированный предмет, но это не Форт-12
      if(Scripts.isItemEquipped(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]) && 
              Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem] != 116) 
      {
         //текст: take off/снять
         PlayerHUD.drawSoftButtonNames(1, 0, 374, true);
      } 
      else 
      {
         //текст id: take/взять
         PlayerHUD.drawSoftButtonNames(1, 0, 373, true);
      }

      //Заполняем описание предмета в инвентаре и подсвечиваем его
      this.masterInvScreen.itemDscrScreen.descrText = 
              getItemDescrTextId(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]);
      this.masterInvScreen.itemDscrScreen.paint(graphics);
      ItemsInteraction.drawOrder = 1;
      this.masterInvScreen.playerItemsZone.paint(graphics);
      this.masterInvScreen.playerItemsZone.highlightSelectedItem(graphics);
   }

   public final void keyPressed(int key) 
   {
      switch(key) 
      {
      case 1:
      case 6:
      case 7:
      default:
         break;
      case 2:
         this.itemsInteraction.keyPressed(2);
         break;
      //Вернуться в меню игровой паузы
      case 3:
         Main.main.setScreen(AllScreens.pauseScreen, (byte)2);
         break;
      case 4:
      //Использовать предмет
      case 8:
         if(Scripts.isItemEquipped(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem])) 
         {
            Scripts.unequipItem(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]);
         } 
         else 
         {
            Scripts.useItem(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]);
         }

         this.masterInvScreen.playerItemsZone.onShow((byte)1);
         break;
      case 5:
         this.itemsInteraction.keyPressed(5);
      }

      Main.main.repaint();
   }

   public final boolean onShow(byte screenId) 
   {
      this.itemDscrScreen = this.masterInvScreen.itemDscrScreen;
      this.itemDscrScreen.resetVariables();
      
      //Задаём фреймворк для окна с названием предмета
      int xStart = ResourseManager.getUIElementXcoord(39, 15);//Левый край 142
      int xEnd = ResourseManager.getUIElementXcoord(39, 16);//Правый край 216
      int yStart = ResourseManager.getUIElementYcoord(39, 15);//Верхний край 54
      int yEnd = ResourseManager.getUIElementYcoord(39, 18);//Нижний край 206
      this.itemDscrScreen.setNameFrameLocation(xStart, xEnd, yStart);
      
      //Задаём фреймворк для окна описания
      xStart = ResourseManager.getUIElementXcoord(39, 17);//137
      xEnd = ResourseManager.getUIElementXcoord(39, 18);//215
      this.itemDscrScreen.setDescriptionFrameLocation(xStart, yStart, xEnd - xStart, yEnd - yStart);//137,54,78,152
      this.itemsInteraction = this.masterInvScreen.playerItemsZone;
      this.itemsInteraction.onShow((byte)1);
      return true;
   }
}
