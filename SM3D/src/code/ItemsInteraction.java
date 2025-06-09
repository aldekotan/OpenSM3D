package code;

import javax.microedition.lcdui.Graphics;

public final class ItemsInteraction implements Screen 
{
   //Задаём рабочую область, где будут нарисованы предметы инвентаря/экипировки
   private int xClipStart = 10;
   private int yClipStart = 10;
   private int clipWidth;
   private int clipHeight;
   
   //Используется для начальной координаты X
   //при отрисовке рамки выделенного предмета
   private int itemFrameXStart;
   
   //Определяет, на сколько пикселей за пределы экрана (влево) уходят
   //отображаемые иконки предметов
   private int offScreenItemsXOffset;
   
   public ItemsInteraction itemsInteraction;
   private int[] itemsXcoords;
   private int[] itemsYcoords;
   //Общее количество предметов в инвентаре игрока и у торговца/в тайнике
   public int itemsTotalInInventory;
   public static int itemsTotalInTopArea;
   //Выбранный предмет в инвентаре
   public static int selectedInventoryItem;
   //Выбранный предмет в окне обыска или окне предметов торговца
   public static int selectedTopAreaItem;
   //Суммарная ширина иконок всех предметов, с учётом выходящих за экран
   public int totalItemsFrameWidth;
   //Порядок отрисовки. Нужно ли рисовать только инвентарь и/или окно торговли
   //или окно обыска
   public static int drawOrder;


   public ItemsInteraction(int xClip, int yClip, 
           int width, int height) 
   {
      this.clipWidth = MainMenuScreen.scrWidth - 2 * this.xClipStart;
      this.clipHeight = 30;
      
      this.itemsXcoords = new int[14];
      this.itemsYcoords = new int[14];
      
      this.xClipStart = xClip;
      this.yClipStart = yClip;
      this.clipWidth = width;
      this.clipHeight = height;
   }

   private void setItemsXYcoords() 
   {
      for(byte i = 0; i < 14; ++i) 
      {
         this.itemsXcoords[i] = ResourceManager.getUIElementXcoord(39, i);
         this.itemsYcoords[i] = ResourceManager.getUIElementYcoord(39, i);
      }

   }

   private void drawCurrentEquipment(Graphics graphics) 
   {
      graphics.setClip(0, 0, PlayerHUD.SCREEN_WIDTH, PlayerHUD.SCREEN_HEIGHT);

      //2 слота под оружие, 1 под броню, 4 под арты
      for(byte item = 0; item < 7; ++item) 
      {
         if(Scripts.equipmentSlots[item] != -1) 
         {
            ResourceManager.drawUserInterfaceItems(graphics, 
                    getItemImageUIid(Scripts.equipmentSlots[item]), 
                    this.itemsXcoords[item * 2], this.itemsYcoords[item * 2]);
         }
      }

   }

   //Подсветить предмет в экипировке
   private void highlightWornItem(Graphics graphics, int inventaryItem) 
   { 
      int color = 15637809;
      graphics.setClip(0, 0, PlayerHUD.SCREEN_WIDTH, PlayerHUD.SCREEN_HEIGHT);
      graphics.setColor(color);
      graphics.setStrokeStyle(0);

      for(byte equipItem = 0; equipItem < 7; ++equipItem) 
      {
         if(Scripts.inventoryItems[inventaryItem] == Scripts.equipmentSlots[equipItem]) 
         {
            int x_coord = this.itemsXcoords[equipItem * 2];
            int y_coord = this.itemsYcoords[equipItem * 2];
            int width = this.itemsXcoords[equipItem * 2 + 1] - x_coord;
            int height = this.itemsYcoords[equipItem * 2 + 1] - y_coord;
            graphics.drawRect(x_coord, y_coord, width, height);
         }
      }

   }

   //а в начале я был наивнее)//а сейчас хотя бы строки научился переносить (02.22)
   
   //Основной метод отрисовки
   private void drawEquipmentAndInventoryItems(Graphics graphics)
   {
      int itemIndex = 0;
      int itemOffset = 0;
      switch(drawOrder) 
      {
      //Отрисовка экипированных вещей
      case 1:
         this.drawCurrentEquipment(graphics);
         graphics.setClip(this.xClipStart, 0, 
                 PlayerHUD.SCREEN_WIDTH, PlayerHUD.SCREEN_HEIGHT);
      case 2:
      //default:
      //   break;
      case 3:
      //Отрисовка предметов в инвентаре игрока
      case 5:
         do
          {
              int x = this.xClipStart + itemOffset + this.offScreenItemsXOffset;
              ResourceManager.drawUserInterfaceItems(graphics, 
                      getItemImageUIid(Scripts.inventoryItems[itemIndex]), 
                      x, this.yClipStart);
              itemOffset += ResourceManager.getRectangleWidth(getItemImageUIid(Scripts.inventoryItems[itemIndex++]));
              if(itemIndex >= this.itemsTotalInInventory)
              {
                    return;
              }

          }
          //Пока не упрёмся за пределы экрана, с учётом буфера слева
          while(itemOffset + this.offScreenItemsXOffset < this.clipWidth);
         return;
      //Отрисовка предметов в тайнике
      case 4: 
         do 
         {
            if(Scripts.stashItems.length == 0) {
               return;
            }

            ResourceManager.drawUserInterfaceItems(graphics, 
                    getItemImageUIid(Scripts.stashItems[itemIndex]), 
                    this.xClipStart + itemOffset + this.offScreenItemsXOffset, 
                    this.yClipStart);
            itemOffset += ResourceManager.getRectangleWidth(getItemImageUIid(Scripts.stashItems[itemIndex++]));
            if(itemIndex >= itemsTotalInTopArea) 
            {
               return;
            }
         } 
         while(itemOffset + this.offScreenItemsXOffset < this.clipWidth);
         return;
      //Отрисовка предметов у торгаша в окне
      case 6:
         do 
         {
            if(Scripts.traderItems.length == 0) 
            {
               return;
            }

            ResourceManager.drawUserInterfaceItems(graphics, 
                    getItemImageUIid(Scripts.traderItems[itemIndex]), 
                    this.xClipStart + itemOffset + this.offScreenItemsXOffset, this.yClipStart);
            itemOffset += ResourceManager.getRectangleWidth(getItemImageUIid(Scripts.traderItems[itemIndex++]));
         } 
         while(itemIndex < itemsTotalInTopArea && itemOffset + this.offScreenItemsXOffset < this.clipWidth);
      }
      
   }

   //Подсветим выбранный в инвентаре или в верхней зоне предмет
   public final void highlightSelectedItem(Graphics graphics) 
   {
      int selectedItem = selectedInventoryItem;
      short[] items = Scripts.inventoryItems;
      switch(drawOrder) 
      {
      case 1:
      case 3:
      case 5:
         items = Scripts.inventoryItems;
         selectedItem = selectedInventoryItem;
      case 2:
      default:
         break;
      case 4:
         items = Scripts.stashItems;
         selectedItem = selectedTopAreaItem;
         break;
      case 6:
         items = Scripts.traderItems;
         selectedItem = selectedTopAreaItem;
      }

      int color = 1106120;
      //Если код работает с инвентарём
      if(drawOrder == 1 && Scripts.isItemEquipped(Scripts.inventoryItems[selectedItem])) 
      {
         //Цвет желтовато-оранжевый
         color = 15637809; 
         this.highlightWornItem(graphics, selectedItem);
      }

      if(drawOrder == 5 || drawOrder == 6 || drawOrder == 3 || drawOrder == 4) 
      {
         color = 15637809;
      }

      graphics.setColor(color);
      graphics.setStrokeStyle(0);
      int width = ResourceManager.getRectangleWidth(getItemImageUIid(items[selectedItem]));
      int itemHeight = ResourceManager.getRectangleHeight(getItemImageUIid(items[selectedItem]));
      int refItemHeight = ResourceManager.getRectangleHeight(33) - 10;
      int height = Math.min(itemHeight, refItemHeight);
      graphics.setClip(this.xClipStart, 0, PlayerHUD.SCREEN_WIDTH, PlayerHUD.SCREEN_HEIGHT);
      graphics.drawRect(this.xClipStart + this.itemFrameXStart + this.offScreenItemsXOffset, this.yClipStart, width, height);
   }

   //У иконок предметов и UI - свои ID, отличные от ID предметов
   private static int getItemImageUIid(short itemId) 
   {
      if(itemId>=300)
      {
          return ModChanges.NewItemsID(itemId);
      }
      else
      {
          switch(itemId) 
          {
          case 101:
             return 64;
          case 102:
             return 65;
          case 103:
             return 66;
          case 104:
             return 75;
          case 105:
          default:
             return -2;
          case 106:
             return 67;
          case 107:
             return 55;
          case 108:
             return 56;
          case 109:
             return 57;
          case 110:
             return 58;
          case 111:
             return 59;
          case 112:
             return 60;
          case 113:
             return 62;
          case 114:
             return 61;
          case 115:
             return 63;
          case 116:
             return 51;
          case 117:
             return 52;
          case 118:
             return 53;
          case 119:
             return 54;
          case 120:
             return 68;
          case 121:
             return 69;
          case 122:
             return 70;
          case 123:
             return 71;
          case 124:
             return 72;
          case 125:
             return 73;
          case 126:
             return 74;
          }
      }
   }

   public final void keyPressed(int key)
   {
      int selectedItem = 0;
      int itemsTotalNumber = 0;
      short[] items = Scripts.inventoryItems;
      ItemDescriptionScreen.resetTextOffset();
      switch(drawOrder) 
      {
      case 1:
      case 3:
      case 5:
         selectedItem = selectedInventoryItem;
         itemsTotalNumber = this.itemsTotalInInventory;
         items = Scripts.inventoryItems;
      case 2:
      default:
         break;
      case 4:
         selectedItem = selectedTopAreaItem;
         itemsTotalNumber = itemsTotalInTopArea;
         items = Scripts.stashItems;
         break;
      case 6:
         selectedItem = selectedTopAreaItem;
         itemsTotalNumber = itemsTotalInTopArea;
         items = Scripts.traderItems;
      }

      switch(key) 
      {
      //выбрать предыдущий предмет
      case 2:
         --selectedItem;
         if(selectedItem < 0) 
         {
            selectedItem = itemsTotalNumber - 1;
            if(itemsTotalNumber - 1 > 0) 
            {
               this.itemFrameXStart = this.totalItemsFrameWidth - ResourceManager.getRectangleWidth(getItemImageUIid(items[itemsTotalNumber - 1]));
               this.offScreenItemsXOffset = this.totalItemsFrameWidth < this.clipWidth?0:this.clipWidth - this.totalItemsFrameWidth;
            }
         } 
         else 
         {
            this.itemFrameXStart -= ResourceManager.getRectangleWidth(getItemImageUIid(items[selectedItem]));
            if(-this.offScreenItemsXOffset > this.itemFrameXStart) 
            {
               this.offScreenItemsXOffset = -this.itemFrameXStart;
            }
         }
         break;
      case 3:
         if(drawOrder == 1) {
            Main.main.reloadCurrentScreen();
         } else {
            Main.main.setScreen(AllScreens.menu, (byte)14);
            GameScene.setActiveObjState(Scripts.openedActivableObjId, (short)0);
         }
      case 4:
      //выбрать следующий предмет
      case 5:
         if(selectedItem < items.length) 
         {
            this.itemFrameXStart += ResourceManager.getRectangleWidth(getItemImageUIid(items[selectedItem]));
         }

         ++selectedItem;
         if(selectedItem >= itemsTotalNumber) 
         {
            selectedItem = 0;
            this.itemFrameXStart = this.offScreenItemsXOffset = 0;
         } 
         else if(selectedItem < items.length && -this.offScreenItemsXOffset + this.clipWidth < this.itemFrameXStart + ResourceManager.getRectangleWidth(getItemImageUIid(items[selectedItem]))) {
            this.offScreenItemsXOffset = -this.itemFrameXStart - ResourceManager.getRectangleWidth(getItemImageUIid(items[selectedItem])) + this.clipWidth;
         }
       default:
         break;  
      }

      switch(drawOrder) 
      {
      case 1:
      case 3:
      case 5:
         selectedInventoryItem = selectedItem;
         this.itemsTotalInInventory = itemsTotalNumber;
      case 2:
      default:
         break;
      case 4:
      case 6:
         selectedTopAreaItem = selectedItem;
         itemsTotalInTopArea = itemsTotalNumber;
      }

      Main.main.repaint();
   }

   public final boolean onShow(byte screenId) 
   {
      ItemDescriptionScreen.resetTextOffset();
      System.gc();
      this.itemFrameXStart = this.offScreenItemsXOffset = 0;
      this.totalItemsFrameWidth = 0;
      int itemIndex = 0;

      try 
      {
         label36:
         switch(screenId) 
         {
         case 1:
            this.setItemsXYcoords();
         case 2:
         //default: здесь они в оригинале
         //   break;
         case 3:
         case 5:
            selectedInventoryItem = 0;
            this.itemsTotalInInventory = 0;

            while(true) 
            {
               if(itemIndex >= Scripts.inventoryItemsCount) 
               {
                  break label36;
               }

               ++this.itemsTotalInInventory;
               this.totalItemsFrameWidth += ResourceManager.getRectangleWidth(getItemImageUIid(Scripts.inventoryItems[itemIndex++]));
            }
         default: //а здесь их нет
             break;
         case 4:
            itemsTotalInTopArea = 0;
            selectedTopAreaItem = 0;

            while(true) 
            {
               if(itemIndex >= Scripts.stashItems.length) 
               {
                  break label36;
               }

               ++itemsTotalInTopArea;
               this.totalItemsFrameWidth += ResourceManager.getRectangleWidth(getItemImageUIid(Scripts.stashItems[itemIndex++]));
            }
         case 6:
            itemsTotalInTopArea = 0;

            for(selectedTopAreaItem = 0; itemIndex < Scripts.traderItems.length; this.totalItemsFrameWidth += ResourceManager.getRectangleWidth(getItemImageUIid(Scripts.traderItems[itemIndex++]))) {
               ++itemsTotalInTopArea;
            }
         }
      } 
      catch (Exception exc) 
      {
         exc.printStackTrace();
      }

      Main.main.repaint();
      return true;
   }

   public final void resetVariables() {}

   public final void paint(Graphics graphics)
   {
      graphics.setClip(this.xClipStart, this.yClipStart, 
              this.clipWidth, this.clipHeight);
      this.drawEquipmentAndInventoryItems(graphics);
   }

}
