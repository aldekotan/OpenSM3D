package code;

import javax.microedition.lcdui.Graphics;

//Игровой экран
public interface Screen {

   void resetVariables();

   void paint(Graphics graphics);
   void keyPressed(int key);

   //19.03.2021. Понятия не имею, что это, но скоро узнаю!
   boolean onShow(byte screenId); 
   //22.06.2024. Не прошло и года.
}
