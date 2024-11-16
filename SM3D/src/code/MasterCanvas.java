package code;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.m3g.Graphics3D;

public final class MasterCanvas extends Canvas { //Общий канвас для обработки экранов игры

    public static MasterCanvas instance;
    public Screen currentScreen;
    public static Graphics graphics;
    
    public static boolean paused;
    public static boolean paused2; //useless?? todo remove?

    public MasterCanvas() {
        instance = this;
        setFullScreenMode(true);
        GameScene.graphics3D = Graphics3D.getInstance();
        SoundAndVibro.prefetchSounds();
    }

    public final void keyPressed(int key) //Клавиша нажата
    {
        if(currentScreen instanceof GameScreen) {
            Keys.updateKey(key, true);
        } else if(
                !(currentScreen instanceof MainMenuScreen) && 
                !(currentScreen instanceof KeyboardScreen) && 
                !(currentScreen instanceof OldDebugger)) {
            currentScreen.keyPressed(getGameAction(key));
        } else {
            currentScreen.keyPressed(key);
        }
    }

    public final void keyReleased(int key) //Клавиша отжата
    {
        if(currentScreen instanceof GameScreen) {
            getGameAction(key); //why? useless
        }

        Keys.updateKey(key, false);
    }

    public final void paint(Graphics g) {
        PlayerHUD.graphics = graphics = g;
        if(currentScreen != null) currentScreen.paint(g);
        
        Thread.yield(); //Дать остальным потокам процессорное 
    }

    public final int getGameAction(int key) {
        switch(key) {
            case -7: //Nokia soft right
                return Command.CANCEL;
            case -6: //Nokia soft left
                return Command.OK;
            case -5: //Nokia ok
                return Canvas.FIRE;
            default:
                return super.getGameAction(key);
        }
    }

    //USELESS??? duplicate of Main.pauseApp todo remove?
    //Canvas was removed from display
    public final void hideNotify() {
        if(!paused2) {
            paused = true;
            GameScene.gamePaused = true;
            
            //Если звук влючён - остановить воспроизведение
            if(SoundAndVibro.soundsEnabled) SoundAndVibro.stopPlayingSound();

            paused2 = true;
            Keys.reset();
            System.gc();
            super.hideNotify();
        }

    }

    //USELESS??? duplicate of Main.startApp todo remove?
    //Canvas shown on display
    public final void showNotify() {
        paused2 = false;
        GameScene.gamePaused = false;
        paused = false;
        System.gc();
        super.showNotify();
        Main.main.repaint();
    }

}
