package code;

import javax.microedition.lcdui.Graphics;

public final class TradeScreen implements Screen 
{

   public MasterInventoryScreen masterInvScreen;
   public Screen screen;


   public TradeScreen(MasterInventoryScreen directLink) 
   {
      this.masterInvScreen = directLink;
   }

   public final void resetVariables() {}

   public final void paint(Graphics graphics)
   {
      short[] items;
      int selectedItem;
      //поле торговца (верхнее)
      //выбирается та строка, где находится подсвеченный предмет
      //у игрока или у торговца соотв.
      if(this.screen == this.masterInvScreen.topAreaItemsZone) 
      {
         items = Scripts.traderItems;
         selectedItem = ItemsInteraction.selectedTopAreaItem;
      }
      //поле наших вещей (нижнее)
      else 
      {
         items = Scripts.inventoryItems;
         selectedItem = ItemsInteraction.selectedInventoryItem;
      }

      //МЕНЮ ТОРГОВЛИ. ОСНОВА
      ResourceManager.drawUserInterfaceItems(graphics, 48, 0, 0);
      //надпись ТОРГОВЛЯ
      ResourceManager.drawUserInterfaceItems(graphics, 92, 0, 0);
      
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      //предмет вне окна предпрсмотра
      if(this.screen instanceof ItemsInteraction) 
      {
         this.masterInvScreen.itemDscrScreen.descrText = InventoryScreen.getItemDescrTextId(items[selectedItem]);
         this.masterInvScreen.itemDscrScreen.paint(graphics);
      }
      //предмет в окне предпросмотра
      else if(this.screen instanceof ItemDescriptionScreen) 
      {
         this.masterInvScreen.itemDscrScreen.paint(graphics);
      }

      boolean var4 = false;
      boolean var5 = false;
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      ResourceManager.drawUserInterfaceItems(graphics, 82, 0, 0); //надпись ИНФО
      if(this.screen == this.masterInvScreen.playerItemsZone) 
      {
         PlayerHUD.drawSoftButtonNames(1, 0, 375, true);//продать
      } 
      else 
      {
         PlayerHUD.drawSoftButtonNames(1, 0, 376, true);//купить
      }

      PlayerHUD.drawSoftButtonNames(0, 1, 6, true);//назад
      int x1 = ResourceManager.getUIElementXcoord(49, 6);
      int y2 = ResourceManager.getUIElementYcoord(49, 6);
      TextCreator.drawLineByAnchor(1, 309, x1, y2, 5);
      
      x1 = ResourceManager.getUIElementXcoord(49, 10);
      y2 = ResourceManager.getUIElementYcoord(49, 10);
      TextCreator.drawLineByAnchor(1, 310, x1, y2, 5);
      
      byte[] currWeight = TextCreator.createTextFromNumberSeparated(Scripts.playerWeight);
      byte[] kgSlash = TextCreator.createTextFromLine(65);
      byte[] playerWeightText = TextCreator.combineText(currWeight, kgSlash);
      
      byte[] maxWeight = TextCreator.createTextFromNumber(Scripts.playerMaxWeight / 10);
      byte[] kg = TextCreator.createTextFromLine(64);
      byte[] playerMaxWeightText = TextCreator.combineText(maxWeight, kg);
      byte[] text = TextCreator.combineText(playerWeightText, playerMaxWeightText);
      int x = ResourceManager.getUIElementXcoord(49, 12);
      int y = ResourceManager.getUIElementYcoord(49, 12);
      TextCreator.drawWeightTextByAnchor(1, text, 0, text.length, x, y, 5);
      
      text = TextCreator.combineText(TextCreator.createTextFromNumberSeparated(Scripts.getItemWeight(items[selectedItem])), TextCreator.createTextFromLine(64));
      x = ResourceManager.getUIElementXcoord(49, 7);
      y = ResourceManager.getUIElementYcoord(49, 7);
      TextCreator.drawWeightTextByAnchor(1, text, 0, text.length, x, y, 5);
      
      int price;
      if(this.screen == this.masterInvScreen.playerItemsZone) {
         price = Scripts.getItemPrice(items[selectedItem]) / 2;
      } 
      else 
      {
         price = Scripts.getItemPrice(items[selectedItem]);
      }

      text = TextCreator.combineText(TextCreator.createTextFromNumber(price), TextCreator.createTextFromLine(66));
      x = ResourceManager.getUIElementXcoord(49, 11);
      y = ResourceManager.getUIElementYcoord(49, 11);
      TextCreator.drawTextByAnchor(1, text, 0, text.length, x, y, 5);
      
      short[] playerMoneyText;
      (playerMoneyText = TextCreator.makeColoredTextFromNumber(Scripts.playerMoney, false))[playerMoneyText.length - 1] = 106;
      x = ResourceManager.getUIElementXcoord(49, 13);
      y = ResourceManager.getUIElementYcoord(49, 13);
      TextCreator.drawColoredText(playerMoneyText, x, y, 3);
      
      ItemsInteraction.drawOrder = 5;
      this.masterInvScreen.playerItemsZone.paint(graphics);
      if(this.screen == this.masterInvScreen.playerItemsZone) {
         this.masterInvScreen.playerItemsZone.highlightSelectedItem(graphics);
      }

      ItemsInteraction.drawOrder = 6;
      this.masterInvScreen.topAreaItemsZone.paint(graphics);
      if(this.screen == this.masterInvScreen.topAreaItemsZone) {
         this.masterInvScreen.topAreaItemsZone.highlightSelectedItem(graphics);
      }

   }

   private void restartMovingDescr() {
      this.screen = ((ItemsInteraction)this.screen).itemsInteraction;
      ItemDescriptionScreen.resetTextOffset();
   }

   public final void keyPressed(int key) 
   {
      switch(key) {
      case 1:
         if(this.screen instanceof ItemsInteraction) {
            this.restartMovingDescr();
         } else {
            this.screen.keyPressed(key);
         }
         break;
      case 2:
      case 5:
         if(this.screen == this.masterInvScreen.playerItemsZone) {
            ItemsInteraction.drawOrder = 5;
         }

         if(this.screen == this.masterInvScreen.topAreaItemsZone) {
            ItemsInteraction.drawOrder = 6;
         }

         this.screen.keyPressed(key);
         break;
      case 3:
         ResourceManager.runGarbageCollector();
         Main.main.setScreen(AllScreens.menu, (byte)14);
         break;
      case 4:
      case 8://покупка/продажа
         if(this.screen == this.masterInvScreen.topAreaItemsZone &&
                 Scripts.traderItems[ItemsInteraction.selectedTopAreaItem] != -1) 
         {
            Scripts.buyItem(Scripts.traderItems[ItemsInteraction.selectedTopAreaItem]);
            this.masterInvScreen.topAreaItemsZone.onShow((byte)6);
            this.masterInvScreen.playerItemsZone.onShow((byte)5);
            if(Scripts.traderItems.length == 0) //Если у торговца предметов нет
            {
               ResourceManager.runGarbageCollector();
               Main.main.setScreen(AllScreens.menu, (byte)14);
               break;
            }
         }

         if(this.screen == this.masterInvScreen.playerItemsZone) 
         {
            Scripts.sellItem(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]);
            this.masterInvScreen.playerItemsZone.onShow((byte)5);
            this.masterInvScreen.topAreaItemsZone.onShow((byte)6);
         }
         break;
      case 6:
         if(this.screen instanceof ItemsInteraction) {
            this.restartMovingDescr();
         } else {
            this.screen.keyPressed(key);
         }
      case 7:
      }

      Main.main.repaint();
   }

   public final boolean onShow(byte var1) {
      this.masterInvScreen.itemDscrScreen.resetVariables();
      int xCoord2 = ResourceManager.getUIElementXcoord(49, 2);
      int xCoord3 = ResourceManager.getUIElementXcoord(49, 3);
      int yCoord2 = ResourceManager.getUIElementYcoord(49, 2);
      int yCoord5 = ResourceManager.getUIElementYcoord(49, 5);
      this.masterInvScreen.itemDscrScreen.setNameFrameLocation(xCoord2, 
              xCoord3, yCoord2);
      xCoord2 = ResourceManager.getUIElementXcoord(49, 4);
      xCoord3 = ResourceManager.getUIElementXcoord(49, 5);
      this.masterInvScreen.itemDscrScreen.setDescriptionFrameLocation(xCoord2, 
              yCoord2, xCoord3 - xCoord2, yCoord5 - yCoord2);
      this.masterInvScreen.playerItemsZone.onShow((byte)5);
      this.masterInvScreen.playerItemsZone.itemsInteraction =
              this.masterInvScreen.topAreaItemsZone;
      this.masterInvScreen.topAreaItemsZone.onShow((byte)6);
      this.masterInvScreen.topAreaItemsZone.itemsInteraction =
              this.masterInvScreen.playerItemsZone;
      this.screen = this.masterInvScreen.playerItemsZone;
      return true;
   }
}
