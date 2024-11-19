package code;

import javax.microedition.lcdui.Graphics;

public final class MasterInventoryScreen implements Screen 
{

   public Screen screen;
   public Screen[] screens = new Screen[8];
   public ItemsInteraction playerItemsZone;
   public ItemsInteraction topAreaItemsZone;
   public ItemDescriptionScreen itemDscrScreen;

   private void setItemsZones() 
   {
       //Текст ниже использовался, вероятно, для дебаг-меню и отрисовки
       //свойств снаряжения, пока не был заменён на графическую часть
       //точность:
       //DAMAGE:
       //RATE OF DUERCGARGE:
       //BULLET FAKA:
       //RADIATION FAKA:
       //ANOMALY FAKA:
       //ЗДОРОВЬЕ:
       //СИЛА:
       //%%%%%%%%%%%
       
      //Окно предметов игрока
      int xStart = ResourseManager.getUIElementXcoord(49, 14);
      int yStart = ResourseManager.getUIElementYcoord(49, 14);
      int xEnd = ResourseManager.getUIElementXcoord(49, 15);
      int yEnd = ResourseManager.getUIElementYcoord(49, 15);
      this.playerItemsZone = new ItemsInteraction(xStart, yStart + 2, xEnd - xStart, yEnd - yStart);
      //Окно торгаша/тайника
      xStart = ResourseManager.getUIElementXcoord(49, 0);
      yStart = ResourseManager.getUIElementYcoord(49, 0);
      xEnd = ResourseManager.getUIElementXcoord(49, 1);
      yEnd = ResourseManager.getUIElementYcoord(49, 1);
      this.topAreaItemsZone = new ItemsInteraction(xStart, yStart, xEnd - xStart, yEnd - yStart);
      //Окно описания предметов
      this.itemDscrScreen = new ItemDescriptionScreen();
      //Окно характеристик игрока
      this.itemDscrScreen.statsTextAndValues = new byte[][]{TextCreator.CopyReplicToNewMassive(68), TextCreator.CopyReplicToNewMassive(69), TextCreator.CopyReplicToNewMassive(70), TextCreator.CopyReplicToNewMassive(71), TextCreator.CopyReplicToNewMassive(72), TextCreator.CopyReplicToNewMassive(73), TextCreator.CopyReplicToNewMassive(74), TextCreator.CopyReplicToNewMassive(75)};
      //id text: Пауза?
      short textStartId = TextCreator.textLinesAdress[60];
      //id text: Меню?
      byte[] var6 = new byte[]{TextCreator.textLinesSymbols[textStartId + 18]};
      //76 id: "%"; 63 id: " "
      this.itemDscrScreen.statsClosingCharacters = new byte[][]{TextCreator.CopyReplicToNewMassive(76), 
          TextCreator.CopyReplicToNewMassive(76), var6, 
          TextCreator.CopyReplicToNewMassive(76), 
          TextCreator.CopyReplicToNewMassive(76), 
          TextCreator.CopyReplicToNewMassive(76), 
          TextCreator.CopyReplicToNewMassive(63), 
          TextCreator.CopyReplicToNewMassive(63)};
   }

   //В каждом есть графический отдел и контроль над клавишами
   public MasterInventoryScreen() 
   { 
      this.screens[0] = new EmptySceneScreen(this);
      this.screens[1] = new EmptySceneScreen(this);
      this.screens[2] = new EmptySceneScreen(this);
      this.screens[3] = new InventoryScreen(this);
      this.screens[4] = new EmptySceneScreen(this);
      this.screens[5] = new PlayerStatsScreen(this);
      this.screens[6] = new StashScreen(this);
      this.screens[7] = new TradeScreen(this);
      this.setItemsZones();
   }

   public final void resetVariables() 
   {
      for(int screenNumber = 0; screenNumber < this.screens.length; ++screenNumber) 
      {
         this.screens[screenNumber].resetVariables();
      }

   }

   public final void paint(Graphics graphics) 
   {
      graphics.setColor(0);
      graphics.fillRect(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);
      this.screen.paint(graphics);
   }

   public final void keyPressed(int key) 
   {
      this.screen.keyPressed(key);
   }

   public final boolean onShow(byte screenId) 
   {
      this.screen = this.screens[screenId];
      this.screen.onShow(screenId);
      return true;
   }
}
