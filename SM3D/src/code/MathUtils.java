package code;

import java.io.DataInputStream;
import java.util.Random;

public final class MathUtils {

    private static final Random random = new Random(System.currentTimeMillis());
    public static int framesCount = 0;
    public static long previousTime = System.currentTimeMillis();
    public static int fps = 30;

    public static final int getRandomNumber(int max) //Случайное число в пределах [0, max)
    {
        return (random.nextInt() & Integer.MAX_VALUE) % max;
    }

    //https://developer.download.nvidia.com/cg/acos.html
    public static double acos(float x) {
        boolean negate = x < 0;

        x = Math.abs(x);
        double ret = -0.0187293;
        ret = ret * x;
        ret = ret + 0.0742610;
        ret = ret * x;
        ret = ret - 0.2121144;
        ret = ret * x;
        ret = ret + 1.5707288;
        ret = ret * Math.sqrt(1.0 - x);

        if(negate) ret = Math.PI - ret;

        return ret / Math.PI * 180;
    }

    public static float distance2D(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static float distance3D(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (float) Math.sqrt(
                (x2 - x1) * (x2 - x1)
                + (y2 - y1) * (y2 - y1)
                + (z2 - z1) * (z2 - z1)
        );
    }

    public static final int isPositiveOrNegative(int x) //Число больше нуля?
    {
        return x > 0 ? 1 : (x < 0 ? -1 : 0); //1 если больше. -1 если меньше. 0 если равно нулю ^^
    }

    public static final int calcFps() {
        long time = System.currentTimeMillis();
        long measureTime = time - previousTime;
        
        if(measureTime > 1000L) {
            //todo зачем здесь * measureTime / 1000
            fps = Math.max(1, (int) (framesCount * measureTime / 1000));
            //"fps: " + fps; В оригинале вывода не было
            //System.out.println("fps: " + fps);

            framesCount = 0;
            previousTime = time;
        } else {
            framesCount++;
        }
        
        return fps;
    }

    public static final DataInputStream getFileStream(String file) //Загрузить в память конкретный объект
    {
        return new DataInputStream(Main.main.getClass().getResourceAsStream(file));
    }

    public static short[] removeItemFromList(short[] items, int index) {
        short[] newItems;
        
        if(index == items.length - 1) //Если предмет в конце списка
        {
            newItems = new short[index];
            System.arraycopy(items, 0, newItems, 0, index);
        } else {
            //todo можно упростить ?
            for(int i = index; i < items.length - 1; i++) {
                items[i] = items[i + 1];
                items[i + 1] = -1;
            }

            int newLength = 0;
            for(int i = 0; i < items.length; i++) {
                if(items[i] == -1) {
                    newLength = i;
                    break;
                }
            }

            newItems = new short[newLength];
            System.arraycopy(items, 0, newItems, 0, newLength);
        }

        return newItems;
    }
    
    public static int clamp(int value, int min, int max)
    {
        Math.min(Math.max(value, min), max);
        return value;
    }
}
