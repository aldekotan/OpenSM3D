package code;

import java.util.Random;
import javax.microedition.lcdui.Graphics;

//Сам класс ледит за ходом времени в игре, обновляя интерфейс и сам мир
public final class GameScreen implements Screen {

    //Нигде не используется
    public byte var_16;

    //На каком-то этапе разработки игровая логика связанная со временем
    //отрабатывалась через GameScreen
    public GameScreen() {
        //Что-то с шансом попасть по игроку
        new Random();
        //Таймер со счётчиком и проверкой, что-то в худе, что было анимировано
        new Timer(true, 10L);
        //Таймер с проверкой, но без счётчика - отрисовка попаданий на худе, мб?
        new Timer(true, false, 100L);
        //Таймер без проверки - идей нет
        new Timer(false, 100L);
        this.var_16 = 8;
        OldDebugger.restartDebuggers("timers");
    }

    public final void resetVariables() {
    }

    //Здесь происходит переход от состояния игрового мира к просмотру UI
    public static void clearMemoryAndLoadUIImages() {
        RenderEngine.SetToNullAllWorldnMeshMassives();
        ResourseManager.loadAllInterfaceImages();
        Main.main.setScreen(AllScreens.menu, (byte) 1);
        Main.main.repaint();
    }

    public final void paint(Graphics g) {
        if (!MasterCanvas.paused) {
            try {
                Scripts.checkPlayerAndSaveGame();
                //Если мы в окне игры-исследования, обновить время
                if (RenderEngine.currentGameState == 2
                        || RenderEngine.currentGameState == 1
                        || RenderEngine.currentGameState == 0
                        || RenderEngine.currentGameState == -2
                        || RenderEngine.currentGameState == 13
                        || RenderEngine.currentGameState == 11
                        || RenderEngine.currentGameState == 4) {
                    RenderEngine.updateGameTime();
                    RenderEngine.renderWorld(MasterCanvas.graphics);
                }

                PlayerHUD.updatePlayerHUDState();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        Main.main.repaint();
    }

    public final void keyPressed(int key) {
    }

    public final boolean onShow(byte ScreenId) {
        return true;
    }
}