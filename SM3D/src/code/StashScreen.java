package code;

import javax.microedition.lcdui.Graphics;

public final class StashScreen implements Screen 
{

   public MasterInventoryScreen masterInvScreen;
   public Screen screen;


   public StashScreen(MasterInventoryScreen directLink) 
   {
      this.masterInvScreen = directLink;
   }

   public final void resetVariables() {}

   public final void paint(Graphics graphics) 
   {
      short[] items;
      int selectedItem;
      if(this.screen == this.masterInvScreen.topAreaItemsZone) 
      {
         items = Scripts.stashItems;
         selectedItem = ItemsInteraction.selectedTopAreaItem;
      } 
      else 
      {
         items = Scripts.inventoryItems;
         selectedItem = ItemsInteraction.selectedInventoryItem;
      }

      ResourseManager.drawUserInterfaceItems(graphics, 46, 0, 0); //отрисовка UI
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      if(this.screen instanceof ItemsInteraction) 
      {
         this.masterInvScreen.itemDscrScreen.descrText = InventoryScreen.getItemDescrTextId(items[selectedItem]);
         this.masterInvScreen.itemDscrScreen.paint(graphics);
      } 
      else if(this.screen instanceof ItemDescriptionScreen) 
      {
         this.masterInvScreen.itemDscrScreen.paint(graphics);
      }

      boolean var4 = false;
      boolean var5 = false;
      graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      ResourseManager.drawUserInterfaceItems(graphics, 91, 0, 0);//?
      ResourseManager.drawUserInterfaceItems(graphics, 82, 0, 0);//?
      if(this.screen == this.masterInvScreen.playerItemsZone) 
      {
         PlayerHUD.drawSoftButtonNames(1, 0, 372, true);//убрать
      } 
      else 
      {
         PlayerHUD.drawSoftButtonNames(1, 0, 10, true);//выбрать
      }

      PlayerHUD.drawSoftButtonNames(0, 1, 6, true);
      int x_dest = ResourseManager.getUIElementXcoord(47, 6);
      int y_dest = ResourseManager.getUIElementYcoord(47, 6);
      TextCreator.FindParametersnDrawText(1, 309, x_dest, y_dest, 5);
      
      byte[] currWeight = TextCreator.CreateTextMassiveForNumber(Scripts.ActorCurrentWeight);
      byte[] kgSlash = TextCreator.CopyReplicToNewMassive(65);
      byte[] playerWeightText = TextCreator.combineTextMassives(currWeight, kgSlash);
      
      byte[] maxWeight = TextCreator.CreateMassiveWithRankLength(Scripts.playerMaxWeight / 10);
      byte[] kg = TextCreator.CopyReplicToNewMassive(64);
      byte[] playerMaxWeightText = TextCreator.combineTextMassives(maxWeight, kg);
      byte[] text = TextCreator.combineTextMassives(playerWeightText, playerMaxWeightText);
      int x_coord = ResourseManager.getUIElementXcoord(47, 10);
      int y_coord = ResourseManager.getUIElementYcoord(47, 10);
      TextCreator.DrawTextMassiveWithAnchor(1, text, 0, text.length, x_coord, y_coord, 5);
      
      text = TextCreator.combineTextMassives(TextCreator.CreateTextMassiveForNumber(Scripts.getItemWeight(items[selectedItem])), TextCreator.CopyReplicToNewMassive(64));
      x_coord = ResourseManager.getUIElementXcoord(47, 7);
      y_coord = ResourseManager.getUIElementYcoord(47, 7);
      TextCreator.DrawTextMassiveWithAnchor(1, text, 0, text.length, x_coord, y_coord, 5);
      
      ItemsInteraction.drawOrder = 3;
      this.masterInvScreen.playerItemsZone.paint(graphics);
      if(this.screen == this.masterInvScreen.playerItemsZone) 
      {
         this.masterInvScreen.playerItemsZone.highlightSelectedItem(graphics);
      }

      ItemsInteraction.drawOrder = 4;
      this.masterInvScreen.topAreaItemsZone.paint(graphics);
      if(this.screen == this.masterInvScreen.topAreaItemsZone) 
      {
         this.masterInvScreen.topAreaItemsZone.highlightSelectedItem(graphics);
      }

   }

   private void restartMovingDescr() 
   {
      this.screen = ((ItemsInteraction)this.screen).itemsInteraction;
      ItemDescriptionScreen.resetTextOffset();
   }

   public final void keyPressed(int key) 
   {
      switch(key)
      {
      case 1:
         if(this.screen instanceof ItemsInteraction) 
         {
            this.restartMovingDescr();
         } 
         else 
         {
            this.screen.keyPressed(key);
         }
         break;
      case 2:
      case 5:
         if(this.screen == this.masterInvScreen.playerItemsZone) 
         {
            ItemsInteraction.drawOrder = 3;
         }

         if(this.screen == this.masterInvScreen.topAreaItemsZone) 
         {
            ItemsInteraction.drawOrder = 4;
         }

         this.screen.keyPressed(key);
         break;
      case 3:
         ResourseManager.runGarbageCollector();
         Main.main.setScreen(AllScreens.menu, (byte)14);
         RenderEngine.setActiveObjState(Scripts.openedActivableObjId, (short)0);
         break;
      case 4:
      case 8://взять предмет себе/дропнуть из инвентаря
         if(this.screen == this.masterInvScreen.topAreaItemsZone && Scripts.stashItems[ItemsInteraction.selectedTopAreaItem] != -1) 
         {
            Scripts.addItemToInventary(Scripts.stashItems[ItemsInteraction.selectedTopAreaItem]);
            Scripts.stashItems = MathUtils.removeItemFromList(Scripts.stashItems, ItemsInteraction.selectedTopAreaItem);
            this.masterInvScreen.topAreaItemsZone.onShow((byte)4);
            this.masterInvScreen.playerItemsZone.onShow((byte)3);
            if(Scripts.stashItems.length == 0) {
               RenderEngine.setActiveObjState(Scripts.openedActivableObjId, (short)0);
               ResourseManager.runGarbageCollector();
               Main.main.setScreen(AllScreens.menu, (byte)14);
               break;
            }
         }

         if(this.screen == this.masterInvScreen.playerItemsZone) 
         {
            if(Scripts.isItemEquipped(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem])) 
            {
               Scripts.unequipItem(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]);
            }

            Scripts.dropItem(Scripts.inventoryItems[ItemsInteraction.selectedInventoryItem]);
            this.masterInvScreen.playerItemsZone.onShow((byte)3);
            this.masterInvScreen.topAreaItemsZone.onShow((byte)4);
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

   public final boolean onShow(byte screenId) 
   {
      this.masterInvScreen.itemDscrScreen.resetVariables();
      int xCoord2 = ResourseManager.getUIElementXcoord(47, 2);
      int xCoord3 = ResourseManager.getUIElementXcoord(47, 3);
      int yCoord2 = ResourseManager.getUIElementYcoord(47, 2);
      int yCoord5 = ResourseManager.getUIElementYcoord(47, 5);
      this.masterInvScreen.itemDscrScreen.setNameFrameLocation(xCoord2, xCoord3, yCoord2);
      xCoord2 = ResourseManager.getUIElementXcoord(47, 4);
      xCoord3 = ResourseManager.getUIElementXcoord(47, 5);
      this.masterInvScreen.itemDscrScreen.setDescriptionFrameLocation(xCoord2, yCoord2, xCoord3 - xCoord2, yCoord5 - yCoord2);
      this.masterInvScreen.playerItemsZone.onShow((byte)3);
      this.masterInvScreen.playerItemsZone.itemsInteraction = this.masterInvScreen.topAreaItemsZone;
      this.masterInvScreen.topAreaItemsZone.onShow((byte)4);
      this.masterInvScreen.topAreaItemsZone.itemsInteraction = this.masterInvScreen.playerItemsZone;
      this.screen = this.masterInvScreen.playerItemsZone;
      return true;
   }
}