package code;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public final class SoundAndVibro {

    public static boolean soundsEnabled = true, vibroEnabled = true;

    private static final Player[] players = new Player[3];
    private static int lastPlayedSound = -1; //Текущий воспроизводимый звук
    private static long lastVibrationTime;

    static {
        //int[] var10000 = new int[]{0, 0, 0, 250, 0}; Чтение из массива не выполняется, скрыл за ненадобностью
    }

    //Последовательная загрузка звуковых файлов
    public static final void prefetchSounds() {
        
        for(int i = 0; i < 3; i++) {
            try {
                String path, format;
                
                //Музыка в midi
                if(i == 0) {
                    path = "/gamedata/sounds/" + i + ".mid";
                    format = "audio/midi";
                } else {
                    path = "/gamedata/sounds/" + i + ".amr";
                    format = "audio/amr";
                }
                
                players[i] = Manager.createPlayer(MathUtils.getFileStream(path), format);

                //Подготовка звука для загрузки и воспроизведения
                players[i].realize();
                if(i != 0) players[i].prefetch();

                //Установить громкость в 80% (зачем лол)
                VolumeControl volumeControl = (VolumeControl) players[i].getControl("VolumeControl");
                volumeControl.setLevel(80);

            } catch (Exception e) {
                players[i] = null;
            }
        }
    }

    //Воспроизвести выбранный звук
    public static final void playSound(int sound) {
        if(sound >= 0 && soundsEnabled) {
            //Остановить воспроизведение активного звука
            stopPlayingSound();

            try {
                players[sound].start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            //Запись в переменную текущего проигрываемого звука
            lastPlayedSound = sound;
        }
    }

    public static void stopTooLongVibro() {
        if(lastVibrationTime != 0 && RenderEngine.Only3DRenderTime - lastVibrationTime >= 200L) {
            Main.main.vibrate(0); //Остановить вибрацию телефона
            lastVibrationTime = 0;
        }
    }

    public static void vibrate(int duration) {
        if(vibroEnabled && duration != 0) {
            lastVibrationTime = RenderEngine.Only3DRenderTime;
            Main.main.vibrate(duration);
        }
    }

    public static final void stopPlayingSound() {
        if(lastPlayedSound == -1) return;

        try {
            players[lastPlayedSound].stop();
            //Переводит время воспроизведения звука на его начало
            players[lastPlayedSound].setMediaTime(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
