package code;

import javax.microedition.lcdui.Canvas;

public final class Keys {

    public static boolean wasPressed; //Unused. todo remove?
    
    public static boolean up, down, left, right, fire;
    public static boolean leftSoft, rightSoft;

    public static boolean num1, num2, num3;
    public static boolean num4, num5, num6;
    public static boolean num8;
    public static boolean star, num0, pound;

    public static boolean leftAccelerate, rightAccelerate, upAccelerate, downAccelerate;

    public static long leftOr4PressTime = -1L;
    public static long righOr6PressTime = -1L;
    public static long upOr2PressTime = -1L;
    public static long downOr8PressTime = -1L;

    public static final void reset() {
        wasPressed = false;
        
        leftSoft = rightSoft = false;
        up = down = left = right = fire = false;
        num1 = num2 = num3 = false;
        num4 = num5 = num6 = false;
        num8 = false;
        star = num0 = pound = false;
    }

    public static final void updateKey(int key, boolean pressed) {
        wasPressed |= pressed;

        switch(key) {
            case -7: //nokia right soft
                rightSoft = pressed;
                break;
            case -6: //nokia left soft
                leftSoft = pressed;
                break;
            case -5: //Nokia ok
                fire = pressed;
                break;
            case -4: //Nokia right
                righOr6PressTime = pressed ? System.currentTimeMillis() : -1L;
                right = pressed;
                break;
            case -3: //Nokia left
                leftOr4PressTime = pressed ? System.currentTimeMillis() : -1L;
                left = pressed;
                break;
            case -2: //Nokia down
                downOr8PressTime = pressed ? System.currentTimeMillis() : -1L;
                down = pressed;
                break;
            case -1: //Nokia up
                upOr2PressTime = pressed ? System.currentTimeMillis() : -1L;
                up = pressed;
                break;
            case Canvas.KEY_POUND:
                pound = pressed;
                break;
            case Canvas.KEY_STAR:
                star = pressed;
                break;
            case Canvas.KEY_NUM0:
                num0 = pressed;
                break;
            case Canvas.KEY_NUM1:
                num1 = pressed;
                break;
            case Canvas.KEY_NUM2:
                upOr2PressTime = pressed ? System.currentTimeMillis() : -1L;
                num2 = pressed;
                break;
            case Canvas.KEY_NUM3:
                num3 = pressed;
                break;
            case Canvas.KEY_NUM4:
                leftOr4PressTime = pressed ? System.currentTimeMillis() : -1L;
                num4 = pressed;
                break;
            case Canvas.KEY_NUM5:
                num5 = pressed;
                break;
            case Canvas.KEY_NUM6:
                righOr6PressTime = pressed ? System.currentTimeMillis() : -1L;
                num6 = pressed;
                break;
            case Canvas.KEY_NUM8:
                downOr8PressTime = pressed ? System.currentTimeMillis() : -1L;
                num8 = pressed;
                break;
        }
    }

}
