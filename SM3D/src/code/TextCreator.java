package code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class TextCreator 
{

   public static short[] massive_withStartnEnds;
   public static byte[] byte_text_massive;
   public static final byte[][] x_src_TextImageMassive = new byte[2][];
   public static final byte[][] y_src_TextImageMassive = new byte[2][];
   public static final byte[][] width_TextImageMassive = new byte[2][];
   public static final byte[] height_TextImageMassive = new byte[2];
   public static final byte[] spaceWidth = new byte[2];
   public static final byte[] perLetterInterval = new byte[2];
   public static final Image[] MassiveWithTwoTextImages = new Image[2];
   private static final int[] var_23a = new int[]{31, 1, 0};
   public static short[] var_273;
   public static short[] var_295;
   
   private static Image CreateTextImageFromDataInputStream(DataInputStream object_D, int color_of_text) throws IOException 
   { //класс переделан для упрощения изменения текста
      ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
      DataOutputStream dataOutputStream;
      (dataOutputStream = new DataOutputStream(byteArrayOutput)).writeInt(-1991225785);
      dataOutputStream.writeInt(218765834);
      sub_2f4(object_D, dataOutputStream, 1229472850);
      sub_2f4(object_D, dataOutputStream, 1347179589);
      sub_2f4(object_D, dataOutputStream, 1951551059);
      sub_2f4(object_D, dataOutputStream, 1229209940);
      dataOutputStream.writeInt(0);
      dataOutputStream.writeInt(1229278788);
      dataOutputStream.writeInt(-1371381630);
      System.gc();
      //Image.createImage(byteArrayOutput.toByteArray(), 0, byteArrayOutput.size());
      return Image.createImage("/gamedata/textures/text" + color_of_text + ".png");
   }

   public static final void ReadUnsignedByteAndShortMassivesForText(DataInputStream DataInputStream_object_D) throws IOException 
   {   //длина первого массива 396, длина второго 25996, ниже оригинал
      massive_withStartnEnds = ReadShortMassiveFromDataInput(DataInputStream_object_D, DataInputStream_object_D.readUnsignedShort());
      byte_text_massive = ReadByteMassiveFromDataInput(DataInputStream_object_D, DataInputStream_object_D.readUnsignedShort());
      
      //short[] new_massive_wsne = ModChanges.AddNewTextAdress();
      //byte[] new_bt_massive = ModChanges.AddNewText();
      
      //massive_withStartnEnds = ModChanges.combineTextMassives(ReadShortMassiveFromDataInput(DataInputStream_object_D, DataInputStream_object_D.readUnsignedShort()), new_massive_wsne);
      //byte_text_massive = TextCreator.combineTextMassives(ReadByteMassiveFromDataInput(DataInputStream_object_D, DataInputStream_object_D.readUnsignedShort()), new_bt_massive);
      
      //временные исправления
   }

   public static final void LoadImageAndTextParameters(DataInputStream object_D, int color_of_text) throws IOException 
   {
      if(MassiveWithTwoTextImages[color_of_text] == null) //если изображения нет - продолжить
      {
         int massive_length = object_D.readUnsignedByte();
         x_src_TextImageMassive[color_of_text] = ReadByteMassiveFromDataInput(object_D, massive_length); //икс начальная
         y_src_TextImageMassive[color_of_text] = ReadByteMassiveFromDataInput(object_D, massive_length); //игрек начальная
         width_TextImageMassive[color_of_text] = ReadByteMassiveFromDataInput(object_D, massive_length); //ширина символа
         height_TextImageMassive[color_of_text] = object_D.readByte(); //высота символа
         spaceWidth[color_of_text] = object_D.readByte(); //4
         perLetterInterval[color_of_text] = object_D.readByte(); //2 расстояние между символами, может быть
         //MassiveWithTwoTextImages[number_of_image] = CreateTextImageFromDataInputStream(datainputstream);
         MassiveWithTwoTextImages[color_of_text] = CreateTextImageFromDataInputStream(object_D, color_of_text);
      }
   }

   private static int sub_8a(int var0, int var1) 
   {
      return -(var1 >>> var_23a[var0]);
   }

   private static int HowManyRanksInNumber(byte[] symbol_massive, int number) //Сколько разрядов в числе?
   {
      int end_of_replic_in_massive = 0;
      int number_ = number;

      do 
      {
         ++end_of_replic_in_massive;
      } 
      while((number_ /= 10) != 0);// сколько там разрядов

      int var4 = end_of_replic_in_massive;
      number_ = number;

      do 
      {
         --var4;
         symbol_massive[var4] = (byte)(number_ % 10);
         number_ /= 10;
      } 
      while(var4 > 0);

      return end_of_replic_in_massive;
   }

   private static int DrawThatSymbolHere(int color_of_text, int number_of_symbol, int x_dest, int y_dest) 
   {
      if(number_of_symbol <= -13) 
      {
         number_of_symbol &= 255;
      }
      //0 для обычного текста, 1 для серого
      if(number_of_symbol >= 0) 
      {
         MasterCanvas.graphics.drawRegion(MassiveWithTwoTextImages[color_of_text], x_src_TextImageMassive[color_of_text][number_of_symbol], y_src_TextImageMassive[color_of_text][number_of_symbol], width_TextImageMassive[color_of_text][number_of_symbol], height_TextImageMassive[color_of_text], 0, x_dest, y_dest, 20);
      }

      return GetWidthOfSymbol(color_of_text, number_of_symbol); //вернуть ширину буквы+некий коэффициент
   }

   private static int sub_12d(int number_of_image, byte[] symbolMassive, int numberOfMassiveStart, int numberOfMassiveEnd) //1
   {
      return sub_363(number_of_image, symbolMassive, numberOfMassiveStart, numberOfMassiveEnd) - perLetterInterval[number_of_image];
   }

   public static final int getHeightFromTextParamMassive(int color_of_text) 
   {
      return height_TextImageMassive[color_of_text];
   }

   public static final void FindParametersnDrawText(int type_of_text, int number_of_replic, int x_dest, int y_dest, int var4) //отрисовка однострочной реплики
   { // тип текста - основной 0, дополнительный 1/ массив с всеми текстами/ массив с репликами(название реплики) //расположение по иксу и игреку
       /*if (number_of_replic==377)
       {
       number_of_replic=513;
       }*/
      //System.out.println("DrawReplic: " + number_of_replic + " " + massive_withStartnEnds[number_of_replic-2] + " " + massive_withStartnEnds[number_of_replic-1] + " " + massive_withStartnEnds[number_of_replic] + " " + massive_withStartnEnds[number_of_replic+1]);
      drawReplicWithParameters(type_of_text, byte_text_massive, massive_withStartnEnds[number_of_replic], massive_withStartnEnds[number_of_replic + 1], x_dest, y_dest, var4);
   }

   public static final void drawReplicWithParameters(int number_of_image, byte[] symbolMassive, int numberOfMassiveStart, int numberOfMassiveEnd, int x_dest, int y_dest, int var6) 
   {
      if((var6 & 3) != 0) 
      {
         x_dest += sub_8a(var6 & 3, sub_12d(number_of_image, symbolMassive, numberOfMassiveStart, numberOfMassiveEnd));
      }

      if(var6 > 3) 
      {
         y_dest += sub_8a(var6 >>> 2, getHeightFromTextParamMassive(number_of_image));
      }

      for(int symbolNumberInMassive = numberOfMassiveStart; symbolNumberInMassive < numberOfMassiveEnd; ++symbolNumberInMassive) 
      {
         x_dest += DrawThatSymbolHere(number_of_image, symbolMassive[symbolNumberInMassive], x_dest, y_dest);
      }

   }

   public static final void DrawTextMassiveWithAnchor(int number_of_image, byte[] symbolMassive, int numberOfMassiveStart, int numberOfMassiveEnd, int x_dest, int y_dest, int anchor) 
   {
      if((anchor & 3) != 0) 
      {
         x_dest += sub_8a(anchor & 3, sub_12d(number_of_image, symbolMassive, numberOfMassiveStart, numberOfMassiveEnd));
      }

      if(anchor > 3) 
      {
         y_dest += sub_8a(anchor >>> 2, getHeightFromTextParamMassive(number_of_image));
      }

      for(int symbolsNymberInMassive = numberOfMassiveStart; symbolsNymberInMassive < numberOfMassiveEnd; ++symbolsNymberInMassive) 
      {
         if(symbolMassive[symbolsNymberInMassive] == -1) //если -1, то поставить точку
         {
            x_dest += DrawThatSymbolHere(number_of_image, 63, x_dest, y_dest); //нарисовать символ, затем прибавить его ширину
         } 
         else 
         {
            x_dest += DrawThatSymbolHere(number_of_image, symbolMassive[symbolsNymberInMassive], x_dest, y_dest);
         }
      }

   }

   public static final void drawNumbers(int type_of_text, int length, int x_dest, int y_dest, int var4) 
   {
      byte[] symbol_massive = new byte[10];
      drawReplicWithParameters(type_of_text, symbol_massive, 0, HowManyRanksInNumber(symbol_massive, length), x_dest, y_dest ,var4);
   }

   private static short[] ReadShortMassiveFromDataInput(DataInputStream datainputstream, int short_massive_length) throws IOException 
   {
      short[] short_massive = new short[short_massive_length];

      for(int var3 = 0; var3 < short_massive_length; ++var3) 
      {
         short_massive[var3] = datainputstream.readShort();
      }

      return short_massive;
   }

   private static byte[] ReadByteMassiveFromDataInput(DataInputStream datainputstream_object_D, int byte_massive_length) throws IOException 
   {
      byte[] byte_massive = new byte[byte_massive_length];
      datainputstream_object_D.read(byte_massive);
      return byte_massive;
   }

   private static void sub_2f4(DataInputStream var0, DataOutputStream var1, int var2) throws IOException 
   {
      short var3;
      if((var3 = var0.readShort()) != -1) 
      {
         byte[] var4 = new byte[var3 + 4];
         var0.read(var4);
         var1.writeInt(var3);
         var1.writeInt(var2);
         var1.write(var4);
      }
   }

   public static final int GetWidthOfSymbol(int color_of_text, int number_of_symbol) //3
   {
      if(number_of_symbol <= -13) 
      {
         number_of_symbol &= 255;
      }
      //если номер символа меньше нуля, вернуть 4 или 0 (в случае с серым текстом). Если больше, вернуть 1 или 0 (для серого текста)
      return (number_of_symbol < 0?spaceWidth[color_of_text]:width_TextImageMassive[color_of_text][number_of_symbol]) + perLetterInterval[color_of_text];
   }

   public static final int sub_363(int number_of_image, byte[] symbolMassive, int numberOfMassiveStart, int numberOfMassiveEnd) //2
   {
      int var4 = 0;

      for(int symbolNumberInMassive = numberOfMassiveStart; symbolNumberInMassive < numberOfMassiveEnd; ++symbolNumberInMassive) 
      {
         var4 += GetWidthOfSymbol(number_of_image, symbolMassive[symbolNumberInMassive]);
      }

      return var4;
   }

   public static int sub_3ad(int textType, byte[] symbolMassive, int lineNumber, int var3) //пропавший оператор
   {
      int var4 = 0;

      try 
      {
         int var5;
         for(var5 = lineNumber; (var4 += GetWidthOfSymbol(textType, symbolMassive[var5])) < var3; ++var5) 
         {
            ;
         }

         var4 = var5 - 1;
      } 
      catch (Exception var6) 
      {
         var4 = symbolMassive.length - 1;
      }

      return var4;
   }

   public static final int ReturnLengthOfReplic(int number_of_replic) 
   {
      return massive_withStartnEnds[number_of_replic + 1] - massive_withStartnEnds[number_of_replic];
   }

   public static byte ReturnSymbolOfReplic(int number_of_replic, int symbol_in_replic) 
   {
      short number = massive_withStartnEnds[number_of_replic];
      return byte_text_massive[number + symbol_in_replic];
   }

   public static int sub_44a(int number_of_replic, byte var1, int var2) 
   {
      short number_of_start = massive_withStartnEnds[number_of_replic];
      if(massive_withStartnEnds[number_of_replic + 1] <= number_of_start + var2) 
      {
         return -1;
      } 
      else 
      {
         for(int var4 = number_of_start + var2; var4 > number_of_start; --var4) 
         {
            if(var1 == byte_text_massive[var4]) 
            {
               return var4 - number_of_start;
            }
         }

         return -1;
      }
   }

   public static int sub_4a3(byte[] var0, byte var1, int var2) 
   {
      for(int var3 = var2; var3 > 0; --var3) 
      {
         if(var1 == var0[var3]) 
         {
            return var3;
         }
      }

      return -1;
   }

   public static byte[] CopyReplicToNewMassive(int number_of_replic) 
   {
      if(number_of_replic == -1) 
      {
         return null;
      } 
      else 
      {
         short number_of_start = massive_withStartnEnds[number_of_replic];
         byte[] new_textMassive = new byte[massive_withStartnEnds[number_of_replic + 1] - number_of_start];

         for(int var4 = 0; var4 < new_textMassive.length; ++var4) 
         {
            new_textMassive[var4] = byte_text_massive[number_of_start + var4];
         }

         return new_textMassive;
      }
   }

   public static byte[] combineTextMassives(byte[] first_text_massive, byte[] second_text_massive) 
   {
      byte[] new_text_massive = new byte[first_text_massive.length + second_text_massive.length];

      int var3;
      for(var3 = 0; var3 < first_text_massive.length; ++var3) 
      {
         new_text_massive[var3] = first_text_massive[var3];
      }

      var3 = first_text_massive.length;

      for(int var4 = first_text_massive.length; var3 < new_text_massive.length; ++var3) 
      {
         new_text_massive[var3] = second_text_massive[var3 - var4];
      }

      return new_text_massive;
   }

   public static byte[] CreateMassiveWithRankLength(int number) 
   {
      byte[] first_byte_massive; //создание массива длиной в количество разрядов у числа
      int massive_length;
      byte[] second_byte_massive = new byte[massive_length = HowManyRanksInNumber(first_byte_massive = new byte[10], number)];
      System.arraycopy(first_byte_massive, 0, second_byte_massive, 0, massive_length);
      return second_byte_massive;
   }

   public static byte[] CreateTextMassiveForNumber(int number) 
   {
      byte[] empty_massive;
      int ranksInNumber;
      byte[] text_massive = new byte[(ranksInNumber = HowManyRanksInNumber(empty_massive = new byte[10], number)) == 1?ranksInNumber + 2:ranksInNumber + 1];
      System.arraycopy(empty_massive, 0, text_massive, 0, ranksInNumber);
      if(ranksInNumber == 1) 
      {
         text_massive[ranksInNumber + 1] = text_massive[0]; //1
         text_massive[ranksInNumber] = -1;                  //-1 (если символ - точка)
         text_massive[ranksInNumber - 1] = 0;               //0
      } 
      else 
      {
         text_massive[ranksInNumber] = text_massive[ranksInNumber - 1];
         text_massive[ranksInNumber - 1] = -1;
      }

      return text_massive;
   }

   public static int drawColoredText(short[] symbol_massive, int x_dest, int y_dest, int anchor) 
   {
      int var4;
      switch(anchor) 
      {
      case 3:
         var4 = 0;

         int var5;
         for(var5 = 0; var5 < symbol_massive.length; ++var5) 
         {
            var4 += ResourseManager.ReturnWidthOfInterfaceImage(symbol_massive[var5]);
         }

         x_dest -= var4 / 2;
         ResourseManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, symbol_massive[0], x_dest, y_dest, anchor);

         for(var5 = 1; var5 < symbol_massive.length; ++var5) 
         {
            ResourseManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, symbol_massive[var5], x_dest += ResourseManager.ReturnWidthOfInterfaceImage(symbol_massive[var5 - 1]) + 1, y_dest, anchor);
         }

         return ResourseManager.ReturnHeightOfInterfaceImage(symbol_massive[0]);
      case 6:
         ResourseManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, symbol_massive[0], x_dest, y_dest, anchor);

         for(var4 = 1; var4 < symbol_massive.length; ++var4) 
         {
            ResourseManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, symbol_massive[var4], x_dest += ResourseManager.ReturnWidthOfInterfaceImage(symbol_massive[var4 - 1]) + 1, y_dest, anchor);
         }

         return ResourseManager.ReturnHeightOfInterfaceImage(symbol_massive[0]);
      case 10:
         ResourseManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, symbol_massive[symbol_massive.length - 1], x_dest, y_dest, anchor);

         for(var4 = symbol_massive.length - 2; var4 >= 0; --var4) 
         {
            ResourseManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, symbol_massive[var4], x_dest -= ResourseManager.ReturnWidthOfInterfaceImage(symbol_massive[var4 + 1]) + 1, y_dest, anchor);
         }
      }

      return ResourseManager.ReturnHeightOfInterfaceImage(symbol_massive[0]);
   }

   public static void sub_607() 
   {
      var_273 = new short[10];
      var_273[0] = 95;
      var_273[1] = 96;
      var_273[2] = 97;
      var_273[3] = 98;
      var_273[4] = 99;
      var_273[5] = 100;
      var_273[6] = 101;
      var_273[7] = 102;
      var_273[8] = 103;
      var_273[9] = 104;
      var_295 = new short[10];
      var_295[0] = 147;
      var_295[1] = 148;
      var_295[2] = 149;
      var_295[3] = 150;
      var_295[4] = 151;
      var_295[5] = 152;
      var_295[6] = 153;
      var_295[7] = 154;
      var_295[8] = 155;
      var_295[9] = 156;
   }

   public static short[] surroundNumberWithChars(int number, boolean var1) 
   {
      short[] new_short_first_massive = new short[10];
      int var5 = 9;
      short[] new_short_second_massive;
      short var4;
      if(number < 0) 
      {
         new_short_second_massive = var_295;
         var4 = 159;
      } 
      else 
      {
         new_short_second_massive = var_273;
         var4 = 146;
      }

      number = Math.abs(number);

      do 
      {
         new_short_first_massive[var5--] = new_short_second_massive[number % 10];
      } 
      while((number /= 10) > 0);

      short[] var6 = new short[9 - var5 + (var1?2:1)];
      System.arraycopy(new_short_first_massive, var5 + 1, var6, var1?1:0, 9 - var5);
      if(var1) 
      {
         var6[0] = var4;
      }

      return var6;
   }

    public static Vector splitOnLines(int textId, int targetWidth, int var2) {
        Vector lines = new Vector();
        boolean var5 = false;
        int textWidth = 1;
        int firstChar = 0;
        boolean var10 = false;

        while(true) {
            int var6 = sub_689(textId, -1, textWidth);
            int var7 = sub_689(textId, -2, textWidth);
            int lastChar = ReturnLengthOfReplic(textId);
            if(var7 > 0) {
                lastChar = Math.min(var6, var7);
            } else if(var6 > 0) {
                lastChar = var6;
            }

            if(textWidth == firstChar + 1) {
                textWidth = lastChar;
            }

            int[] var9;
            if(!var10 && sub_84c(var2, textId, firstChar, lastChar - 1) <= targetWidth) {
                textWidth = lastChar + 1;
            } else {
                var9 = new int[2];
                var9[0] = firstChar;
                var9[1] = textWidth - firstChar;
                lines.addElement(var9);
                firstChar = textWidth++;
                if(var10) {
                    textWidth = lastChar + 1;
                }

                var10 = false;
            }

            if(lastChar == ReturnLengthOfReplic(textId)) {
                if(lastChar - firstChar > 0) {
                    (var9 = new int[2])[0] = firstChar;
                    var9[1] = lastChar - firstChar;
                    lines.addElement(var9);
                }

                return lines;
            }

            if(var7 > 0 && var7 < var6) {
                var10 = true;
            }
        }
    }

   private static int sub_689(int number_of_replic, int var1, int var2) 
   {
      for(int var3 = massive_withStartnEnds[number_of_replic] + var2; var3 < massive_withStartnEnds[number_of_replic + 1]; ++var3) 
      {
         if(byte_text_massive[var3] == var1) 
         {
            return var3 - massive_withStartnEnds[number_of_replic];
         }
      }

      return -1;
   }

   private static void sub_6c6(int number_of_image, byte[] symbolMassive, int numberOfMassiveStart, int numberOfMassiveEnd, int x_dest, int y_dest, int var6) 
   {//прокручиваемый текст
      int var7 = sub_7ce(number_of_image, symbolMassive, numberOfMassiveStart, numberOfMassiveEnd);
      x_dest += sub_8ac(var7, var6);
      int var8 = getHeightFromTextParamMassive(number_of_image);
      y_dest += sub_8bb(var8, var6);

      for(int symbolNumberInMassive = numberOfMassiveStart; symbolNumberInMassive < numberOfMassiveEnd; ++symbolNumberInMassive) 
      {
         x_dest += DrawThatSymbolHere(number_of_image, symbolMassive[symbolNumberInMassive], x_dest, y_dest);
      }

   }

   private static void DrawRollingReplicWithParameters(Graphics var0, int number_of_image, int number_of_replic, int var3, int var4, int x_dest, int y_dest, int var7) 
   {
      sub_6c6(number_of_image, byte_text_massive, massive_withStartnEnds[number_of_replic] + var3, massive_withStartnEnds[number_of_replic] + var3 + var4, x_dest, y_dest, var7);
   }

   private static void sub_731(int number_of_replic, int var1, int var2, int x_dest, int y_dest, int var5, int color_of_text, Graphics graphics) 
   {
      DrawRollingReplicWithParameters(graphics, color_of_text, number_of_replic, var1, var2, x_dest, y_dest, var5);
   }

   public static final void drawReplicInsideFrame(int number_of_replic, int xDest, int yDest, int var3, int color_of_text, Graphics graphics, int var6, int var7, Vector var8) 
   {
      if(var7 == -1) 
      {
         var7 = var8.size();
      }

      int x_dest = xDest;
      int y_dest = yDest;
      if((var3 & 32) == 32) 
      {
         y_dest = yDest - var8.size() * getHeightFromTextParamMassive(color_of_text);
      } 
      else if((var3 & 2) == 2) 
      {
         y_dest = yDest - var8.size() * getHeightFromTextParamMassive(color_of_text) / 2;
      }

      Object var11 = null;
      int var12;
      if((var12 = var6 + var7) > var8.size()) {
         var12 = var8.size();
      }

      for(int var13 = var6; var13 < var12; ++var13) 
      {
         int[] var14 = (int[])var8.elementAt(var13);
         sub_731(number_of_replic, var14[0], var14[1], x_dest, y_dest, var3, color_of_text, graphics);
         y_dest += getHeightFromTextParamMassive(color_of_text);
      }

   }

   private static int sub_7ce(int var0, byte[] var1, int var2, int var3) 
   {
      int var4 = 0;

      for(int var5 = var2; var5 < var3; ++var5) 
      {
         var4 += GetWidthOfSymbol(var0, var1[var5]) + 1;
      }

      return var4;
   }

   public static final int sub_7eb(int var0, int var1) 
   {
      return sub_7ce(var0, byte_text_massive, massive_withStartnEnds[var1], massive_withStartnEnds[var1 + 1]);
   }

   private static int sub_84c(int var0, int var1, int var2, int var3) 
   {
      return sub_7ce(var0, byte_text_massive, massive_withStartnEnds[var1] + var2, massive_withStartnEnds[var1] + var3);
   }

   private static int sub_8ac(int var0, int var1) 
   {
      return (var1 & 1) == 1?-(var0 >>> 1):((var1 & 8) == 8?-var0:0);
   }

   private static int sub_8bb(int var0, int var1) 
   {
      return (var1 & 2) == 2?-(var0 >>> 1):((var1 & 32) == 32?-var0:0);
   }

}
