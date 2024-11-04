package code;

import javax.microedition.lcdui.Graphics;

public class CentralText extends MenuScreen {//отрисовывает текст по центру в нескольких местах по игре

    public CentralText() {
        //id текстовых фраз, выводимых на экран в главном меню
        //0
        //21 новая игра
        //18 
        //27 выход
        //19 
        //16 Переписать?
        //7 Отмена
        //4 Нет
        //3 Да
        //2 Ок
        //28 Начать новую игру?
        //33 Покинуть игру?
        //32 Покинуть приложение?
        //34 Включить звук?
        //16 Переписать
        super.replicsMassive = new short[][]{{(short) 0, (short) 21, (short) 18,
            (short) 27, (short) 19, (short) 16}, {(short) 0, (short) 7, (short) 4,
            (short) 4, (short) 4, (short) 7}, {(short) 0, (short) 2, (short) 3,
            (short) 3, (short) 3, (short) 2}, {(short) 0, (short) 28,
            (short) 33, (short) 32, (short) 34, (short) 16}};
        //
        super.screenIds = new byte[]{(byte) 0, (byte) 1, (byte) 1, (byte) 10,
            (byte) 1, (byte) 2};
        //
        //
        super.screenIds2 = new byte[]{(byte) 0, (byte) 1, (byte) 14, (byte) 1,
            (byte) 16, (byte) 2};
    }

    public final void resetVariables() {
        super.screenMassive = new Screen[]{AllScreens.menu2, AllScreens.loadingScreen, AllScreens.menu, AllScreens.menu2, AllScreens.menu, AllScreens.loadingScreen};
        super.screenMassive2 = new Screen[]{AllScreens.menu2, AllScreens.menu, AllScreens.menu2, AllScreens.menu, AllScreens.menu, AllScreens.pauseScreen};
    }

    public boolean onShow(byte screenId) {
        super.onShow(screenId);
        super.interfaceNumber = 50;
        return true;
    }

    public void paint(Graphics graphics) {
        MenuScreen.menuClipX = 0;
        MenuScreen.menuClipY = 0;
        MenuScreen.menuClipWidth = MainMenuScreen.scrWidth;
        MenuScreen.menuClipHight = MainMenuScreen.scrHeight;
        super.paint(graphics);
        graphics.setColor(0);
        graphics.fillRect(MainMenuScreen.scrWidth / 5, MainMenuScreen.scrHeight / 5, 3 * MainMenuScreen.scrWidth / 5, 3 * MainMenuScreen.scrHeight / 5);
        if (!(this instanceof KeyboardScreen)) {// надпись по центру
            TextCreator.FindParametersnDrawText(0, super.replicsMassive[3][super.drawingScreenId], MenuScreen.textClipX + (MenuScreen.textClipWidth >> 1), MenuScreen.textClipY + (MenuScreen.textClipHeight >> 1), 9);
        }//55+(125>>1=62)=117                   70+(180>>1=90)=160
    }

    public void keyPressed(int key) //Да нет?
    {
        //Нет
        if (key == 4)
        {
            Main.main.setScreen(super.screenMassive2[super.drawingScreenId], super.screenIds2[super.drawingScreenId]);
        } else if (key == 3) {
            Main.main.setScreen(super.screenMassive[super.drawingScreenId], super.screenIds[super.drawingScreenId]);
        }
    }
}
