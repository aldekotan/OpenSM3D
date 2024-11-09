package code;

import javax.microedition.lcdui.Font;

public final class AllScreens {

   public static short[] mainMenuTextIds;
   //массив с репликами, куда копируются значения из переменных ниже
   public static final short[] menuTextIdEmptySave = new short[]{(short)20, (short)21, (short)23, (short)24, (short)25, (short)26, (short)27};
   //id реплик меню, если сейвов нет
   //id|реплика
   //20 продолжить
   //21 новая игра
   //23 настройки
   //24 помощь
   //25 об игре
   //26 ещё игры
   //27 выход
   public static final short[] menuTextIdSaveCreated = new short[]{(short)20, (short)21, (short)22, (short)23, (short)24, (short)25, (short)26, (short)27};
   //id реплик меню, если сохранения уже были
    //textId|реплика  | screenId
    //20 продолжить      4
    //21 новая игра      9
    //22 загрузка игры   10
    //23 настройки       1
    //24 помощь          1
    //25 об игре         2
    //26 ещё игры        15
    //27 выход           3
   
   public static final String MORE_GAMES_URL = Main.main.getAppProperty("SiteURL");
   public static int TEXT_LINE_HEIGHT_NOINDENT;
   public static int SymbolHeight;
   public static int SymbolWidth;
   public static final int var_1ec;
   public static final int var_231;
   public static final int var_2a8;
   public static final int halfScrWidth;
   public static final int textY;
   public static int menuNameXCoord;
   public static int menuNameYCoord;
   public static int rightSoftTextXCoord;
   public static int rightsoftTextYCoord;
   public static int leftSoftTextXCoord;
   public static int leftSoftTextYCoord;
   public static int var_4ba;
   public static final int SINGLE_TEXT_LINE_HEIGHT;
   //Main screens duplicate?
   public static Screen confirmScreen;
   public static Screen helpAboutScreen;
   public static Screen pauseScreen;
   public static Screen settingsScreen;
   public static Screen recordsScreen;
   public static Screen masterInventory;
   public static Screen loadingScreen;
   public static Screen menu;
   public static Screen menu2; //why?? useless. todo remove


   static 
   {
      Font.getFont(0, 1, 8);
      TEXT_LINE_HEIGHT_NOINDENT = 15;
      var_1ec = TEXT_LINE_HEIGHT_NOINDENT + -1 + 3;//17
      var_231 = TEXT_LINE_HEIGHT_NOINDENT + 1 + 3;//18
      var_2a8 = MainMenuScreen.scrHeight - var_231;//320-18=302
      halfScrWidth = MainMenuScreen.scrWidth >> 1;
      textY = MainMenuScreen.scrHeight - 3;
      var_4ba = 60;
      SINGLE_TEXT_LINE_HEIGHT = TEXT_LINE_HEIGHT_NOINDENT + 6;
   }
}
