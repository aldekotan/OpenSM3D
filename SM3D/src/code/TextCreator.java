package code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class TextCreator {
    //адреса текстовых строк в массиве

    public static short[] textLinesAdress;
    //символы текстовых строк
    public static byte[] textLinesSymbols;
    //параметры символов, используемые при отрисовке из набора глифов
    public static final byte[][] symbolXcoord = new byte[2][];
    public static final byte[][] symbolYcoord = new byte[2][];
    public static final byte[][] symbolWidth = new byte[2][];
    public static final byte[] symbolHeight = new byte[2];
    public static final byte[] spaceWidth = new byte[2];
    public static final byte[] intercharacterSpace = new byte[2];
    //наборы глифов для жёлтого (0) и серого (1) текста
    public static final Image[] textSymbolsImages = new Image[2];
    //Константы якорей
    private static final int[] anchorConstants = new int[]{31, 1, 0};
    //id глифов для символов, берущихся из интерфейсного набора
    public static short[] greenDigitsIds;
    public static short[] redDigitsIds;

    //Создание изображений из файла "d"
    private static Image createTextImageFromFile(DataInputStream dataInput, int textColor) throws IOException { //класс переделан для упрощения изменения текста
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream dataOutput;
        (dataOutput = new DataOutputStream(byteArray)).writeInt(-1991225785);
        dataOutput.writeInt(218765834);
        reconstructImage(dataInput, dataOutput, 1229472850);
        reconstructImage(dataInput, dataOutput, 1347179589);
        reconstructImage(dataInput, dataOutput, 1951551059);
        reconstructImage(dataInput, dataOutput, 1229209940);
        dataOutput.writeInt(0);
        dataOutput.writeInt(1229278788);
        dataOutput.writeInt(-1371381630);
        System.gc();
        //Image.createImage(byteArray.toByteArray(), 0, byteArray.size());
        return Image.createImage("/gamedata/textures/text" + textColor + ".png");
    }

    //Загрузка текстовых строк и их адресов
    public static void loadTextLines(DataInputStream dataInput) throws IOException {   //длина первого массива 396, длина второго 25996, ниже оригинал
        textLinesAdress = getLinesAdresses(dataInput, dataInput.readUnsignedShort());
        textLinesSymbols = readBytes(dataInput, dataInput.readUnsignedShort());

        //Замена текста на данные из файла
        ModChanges.parseText();
        //System.out.println("Happy fox");

        //ModChanges:
        //short[] new_massive_wsne = ModChanges.AddNewTextAdress();
        //byte[] new_bt_massive = ModChanges.AddNewText();

        //massive_withStartnEnds = ModChanges.combineTextMassives(ReadShortMassiveFromDataInput(DataInputStream_object_D, DataInputStream_object_D.readUnsignedShort()), new_massive_wsne);
        //byte_text_massive = TextCreator.combineTextMassives(ReadByteMassiveFromDataInput(DataInputStream_object_D, DataInputStream_object_D.readUnsignedShort()), new_bt_massive);
    }

    //Загрузка параметров символов и текстур для текста
    public static void loadTextSymbols(DataInputStream dataInput, int textColor) throws IOException {
        if (textSymbolsImages[textColor] == null) {
            int symbolsCount = dataInput.readUnsignedByte();
            symbolXcoord[textColor] = readBytes(dataInput, symbolsCount); //икс начальная
            symbolYcoord[textColor] = readBytes(dataInput, symbolsCount); //игрек начальная
            symbolWidth[textColor] = readBytes(dataInput, symbolsCount); //ширина символа
            symbolHeight[textColor] = dataInput.readByte(); //высота символа
            spaceWidth[textColor] = dataInput.readByte(); //4
            intercharacterSpace[textColor] = dataInput.readByte(); //2 расстояние между символами, может быть
            //MassiveWithTwoTextImages[number_of_image] = CreateTextImageFromDataInputStream(datainputstream);
            textSymbolsImages[textColor] = createTextImageFromFile(dataInput, textColor);
        }
    }

    //Получить расстояние в пикселях по якорю
    private static int getAnchorOffset(int anchor, int i) {
        return -(i >>> anchorConstants[anchor]);
    }

    //Посчитать сколько цифр в числе
    private static int getDigitsCount(byte[] text, int number)
    {
        int digitCount = 0;
        int tempNumber = number;

        do {
            ++digitCount;
        } while ((tempNumber /= 10) != 0);// сколько там разрядов

        int tempDigitCount = digitCount;
        tempNumber = number;

        do {
            --tempDigitCount;
            text[tempDigitCount] = (byte) (tempNumber % 10);
            tempNumber /= 10;
        } while (tempDigitCount > 0);

        return digitCount;
    }

    //Нарисовать один символ. 0 - оранжевый цвет, 1 - серый
    private static int drawSymbol(int color, int symbolId, int x, int y) {
        //Понятия не имею
        if (symbolId <= -13) {
            symbolId &= 255;
        }
        //Если символ - не является переносом строки или пробелом
        if (symbolId >= 0) {
            MasterCanvas.graphics.drawRegion(textSymbolsImages[color],
                    symbolXcoord[color][symbolId],
                    symbolYcoord[color][symbolId],
                    symbolWidth[color][symbolId],
                    symbolHeight[color],
                    0, x, y, 20); //transform, x dest, y dest, anchor
        }

        //вернуть ширину символа+междусимвольный интервал или ширину пробела
        return getSymbolWidth(color, symbolId);
    }

    //Получить ширину строки в пикселях, за вычетом расстояния между символами?
    private static int getTextWidthMinusInterspace(int color, byte[] text, int first, int last) //1
    {
        return getTextWidth(color, text, first, last) - intercharacterSpace[color];
    }

    public static int getSymbolHeight(int color) {
        return symbolHeight[color];
    }

    //Нарисовать строку текста по якорю
    public static void drawLineByAnchor(int color, int adress, int x, int y, int anchor) //отрисовка однострочной реплики
    { // тип текста - основной 0, дополнительный 1/ массив с всеми текстами/ массив с репликами(название реплики) //расположение по иксу и игреку
       /*if (number_of_replic==377)
         {
         number_of_replic=513;
         }*/
        //System.out.println("DrawReplic: " + number_of_replic + " " + massive_withStartnEnds[number_of_replic-2] + " " + massive_withStartnEnds[number_of_replic-1] + " " + massive_withStartnEnds[number_of_replic] + " " + massive_withStartnEnds[number_of_replic+1]);
        drawTextByAnchor(color, textLinesSymbols, textLinesAdress[adress], textLinesAdress[adress + 1], x, y, anchor);
    }

    //Отрисовка текста по якорю
    public static void drawTextByAnchor(int color, byte[] text, int firstSymbol, int lastSymbol, int x, int y, int anchor) {
        if ((anchor & 3) != 0) {
            x += getAnchorOffset(anchor & 3, getTextWidthMinusInterspace(color, text, firstSymbol, lastSymbol));
        }

        if (anchor > 3) {
            y += getAnchorOffset(anchor >>> 2, getSymbolHeight(color));
        }

        for (int symbolCount = firstSymbol; symbolCount < lastSymbol; ++symbolCount) {
            x += drawSymbol(color, text[symbolCount], x, y);
        }

    }

    //Рисует текст, заменяя все пробелы на запятые
    public static void drawWeightTextByAnchor(int color, byte[] text, int firstSymbol, int lastSymbol, int x, int y, int anchor) {
        if ((anchor & 3) != 0) {
            x += getAnchorOffset(anchor & 3, getTextWidthMinusInterspace(color, text, firstSymbol, lastSymbol));
        }

        if (anchor > 3) {
            y += getAnchorOffset(anchor >>> 2, getSymbolHeight(color));
        }

        for (int symbolCount = firstSymbol; symbolCount < lastSymbol; ++symbolCount) {
            if (text[symbolCount] == -1) //заменить пробел на запятую
            {//63 id= ","
                x += drawSymbol(color, 63, x, y);
            } else {
                x += drawSymbol(color, text[symbolCount], x, y);
            }
        }

    }

    //Рисует число
    public static void drawNumber(int color, int number, int x, int y, int anchor) {
        byte[] text = new byte[10];
        drawTextByAnchor(color, text, 0, getDigitsCount(text, number), x, y, anchor);
    }

    //Добыть адреса строк текста
    private static short[] getLinesAdresses(DataInputStream data, int adressLength) throws IOException {
        short[] adressess = new short[adressLength];

        for (int count = 0; count < adressLength; ++count) {
            adressess[count] = data.readShort();
        }

        return adressess;
    }

    //Добыть символы строк текста
    private static byte[] readBytes(DataInputStream data, int symbolsLength) throws IOException {
        byte[] symbols = new byte[symbolsLength];
        data.read(symbols);
        return symbols;
    }

    //Какие-то операции по созданию пнгшки из сжатого состояния
    private static void reconstructImage(DataInputStream dataInput, DataOutputStream dataOutput, int i) throws IOException {
        short paramsCount;
        if ((paramsCount = dataInput.readShort()) != -1) {
            byte[] byteArray = new byte[paramsCount + 4];
            dataInput.read(byteArray);
            dataOutput.writeInt(paramsCount);
            dataOutput.writeInt(i);
            dataOutput.write(byteArray);
        }
    }

    //Получить ширину символа+интервал между ними или пробела
    public static int getSymbolWidth(int color, int symbolId) //3
    {
        if (symbolId <= -13) {
            symbolId &= 255;
        }
        //Возвращает ширину пустого места, если нужно напечатать пробел
        return (symbolId < 0 ? spaceWidth[color] : symbolWidth[color][symbolId]) + intercharacterSpace[color];
    }

    //Получить ширину текста в пикселях
    public static int getTextWidth(int color, byte[] text, int first, int last) {
        int textWidth = 0;

        for (int symbolCount = first; symbolCount < last; ++symbolCount) {
            textWidth += getSymbolWidth(color, text[symbolCount]);
        }

        return textWidth;
    }

    //Получить длину строки в символах
    public static int getTextLength(int color, byte[] text, int firstSymbol, int lastSymbol) //пропавший оператор
    {
        int lineLength = 0;

        try {
            int symbol;
            for (symbol = firstSymbol; (lineLength += getSymbolWidth(color, text[symbol])) < lastSymbol; ++symbol) {
                //??? 
                ;
            }

            lineLength = symbol - 1;
        } catch (Exception e) {
            lineLength = text.length - 1;
        }

        return lineLength;
    }

    public static int getLineLength(int lineId) {
        return textLinesAdress[lineId + 1] - textLinesAdress[lineId];
    }

    //Получить один символ из строки
    public static byte getSymbolFromLine(int line, int symbol) {
        short firstSymbolOfLine = textLinesAdress[line];
        return textLinesSymbols[firstSymbolOfLine + symbol];
    }

    public static int getDistanceToFirstSymbolFromEnd(int replicId, byte symbolId, int textLength) {
        short symbolStartAdress = textLinesAdress[replicId];
        //Если адрес последнего символа меньше или равен адресу первого, плюс длина текста
        if (textLinesAdress[replicId + 1] <= symbolStartAdress + textLength) {
            return -1;
        } else {
            //Ищем первое появление символа с конца строки
            for (int i = symbolStartAdress + textLength; i > symbolStartAdress; --i) {
                if (symbolId == textLinesSymbols[i]) {
                    return i - symbolStartAdress;
                }
            }

            return -1;
        }
    }

    //Посчитать длину текста, за вычетом какого-то символа (используется только
    //для удаления пробела
    public static int getTextLengthWithoutSymbol(byte[] text, byte symbolId, 
            int textLength) {
        for (int i = textLength; i > 0; --i) {
            if (symbolId == text[i]) {
                return i;
            }
        }

        return -1;
    }

    /**Переносит текст нужной линии из общего массива в только созданный*/
    public static byte[] createTextFromLine(int lineId) {
        if (lineId == -1) {
            return null;
        } else {
            short symbolId = textLinesAdress[lineId];
            byte[] text = new byte[textLinesAdress[lineId + 1] - symbolId];

            for (int i = 0; i < text.length; ++i) {
                text[i] = textLinesSymbols[symbolId + i];
            }

            return text;
        }
    }

    public static byte[] combineText(byte[] firstText, byte[] secondText) {
        byte[] finalText = new byte[firstText.length + secondText.length];

        int symbolCount;
        for (symbolCount = 0; symbolCount < firstText.length; ++symbolCount) {
            finalText[symbolCount] = firstText[symbolCount];
        }

        symbolCount = firstText.length;

        for (int i = firstText.length; symbolCount < finalText.length; ++symbolCount) {
            finalText[symbolCount] = secondText[symbolCount - i];
        }

        return finalText;
    }

    public static byte[] createTextFromNumber(int number) {
        byte[] tempText; //создание массива длиной в количество разрядов у числа
        int arrayLength;
        byte[] finalText = new byte[arrayLength = getDigitsCount(tempText = new byte[10], number)];
        System.arraycopy(tempText, 0, finalText, 0, arrayLength);
        return finalText;
    }

    /**В отличие от createTextFromNumber(int number), между последним и предпоследним
     * символами добавляется запятая-разделитель */
    public static byte[] createTextFromNumberSeparated(int number) {
        byte[] tempText;
        int textLength;
        byte[] finalText = new byte[(textLength = getDigitsCount(tempText = new byte[10], number)) == 1 ? textLength + 2 : textLength + 1];
        System.arraycopy(tempText, 0, finalText, 0, textLength);
        if (textLength == 1) {
            finalText[textLength + 1] = finalText[0]; //1
            finalText[textLength] = -1;                  //-1 (если символ - точка)
            finalText[textLength - 1] = 0;               //0
        } else {
            finalText[textLength] = finalText[textLength - 1];
            finalText[textLength - 1] = -1;
        }

        return finalText;
    }

    public static int drawColoredText(short[] text, int x, int y, int anchor) {
        int textWidth;
        switch (anchor) {
            case 3:
                textWidth = 0;

                int symbol;
                for (symbol = 0; symbol < text.length; ++symbol) {
                    textWidth += ResourceManager.ReturnWidthOfInterfaceImage(text[symbol]);
                }

                x -= textWidth / 2;
                ResourceManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, text[0], x, y, anchor);

                for (symbol = 1; symbol < text.length; ++symbol) {
                    ResourceManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, text[symbol], x += ResourceManager.ReturnWidthOfInterfaceImage(text[symbol - 1]) + 1, y, anchor);
                }

                return ResourceManager.ReturnHeightOfInterfaceImage(text[0]);
            case 6:
                ResourceManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, text[0], x, y, anchor);

                for (textWidth = 1; textWidth < text.length; ++textWidth) {
                    ResourceManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, text[textWidth], x += ResourceManager.ReturnWidthOfInterfaceImage(text[textWidth - 1]) + 1, y, anchor);
                }

                return ResourceManager.ReturnHeightOfInterfaceImage(text[0]);
            case 10:
                ResourceManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, text[text.length - 1], x, y, anchor);

                for (textWidth = text.length - 2; textWidth >= 0; --textWidth) {
                    ResourceManager.DrawInterfaceImageToSelectedRegion(MasterCanvas.graphics, text[textWidth], x -= ResourceManager.ReturnWidthOfInterfaceImage(text[textWidth + 1]) + 1, y, anchor);
                }
        }

        return ResourceManager.ReturnHeightOfInterfaceImage(text[0]);
    }

    public static void setColoredDigitsId() {
        greenDigitsIds = new short[10];
        greenDigitsIds[0] = 95;
        greenDigitsIds[1] = 96;
        greenDigitsIds[2] = 97;
        greenDigitsIds[3] = 98;
        greenDigitsIds[4] = 99;
        greenDigitsIds[5] = 100;
        greenDigitsIds[6] = 101;
        greenDigitsIds[7] = 102;
        greenDigitsIds[8] = 103;
        greenDigitsIds[9] = 104;
        redDigitsIds = new short[10];
        redDigitsIds[0] = 147;
        redDigitsIds[1] = 148;
        redDigitsIds[2] = 149;
        redDigitsIds[3] = 150;
        redDigitsIds[4] = 151;
        redDigitsIds[5] = 152;
        redDigitsIds[6] = 153;
        redDigitsIds[7] = 154;
        redDigitsIds[8] = 155;
        redDigitsIds[9] = 156;
    }

    //Создать набор цветных символов из номера
    public static short[] makeColoredTextFromNumber(int number, boolean addSign) {
        short[] symbolArray = new short[10];
        int digitsCount = 9;
        short[] coloredSymbols;
        short signId;
        if (number < 0) {
            coloredSymbols = redDigitsIds;
            signId = 159;// minus
        } else {
            coloredSymbols = greenDigitsIds;
            signId = 146;// plus
        }

        number = Math.abs(number);

        do {
            symbolArray[digitsCount--] = coloredSymbols[number % 10];
        } while ((number /= 10) > 0);

        short[] finalText = new short[9 - digitsCount + (addSign ? 2 : 1)];
        System.arraycopy(symbolArray, digitsCount + 1,
                finalText, addSign ? 1 : 0, 9 - digitsCount);
        if (addSign) {
            finalText[0] = signId;
        }

        return finalText;
    }

    /** Текст делится на строчки, с учётом переносов и пробелов. В выводе
     получается вектор типа: (номер первого символа линии 1;
     номер последнего символа линии 1;)
     (номер первого символа линии 2 и т.д.*/
    public static Vector splitOnLines(int lineId, int targetWidth, int color) {
        Vector lines = new Vector();
        //boolean var5 = false;
        int textWidth = 1;
        int firstChar = 0;
        boolean lineBreaksAfterFirstWord = false;

        while (true) {
            int distToSpace = getDistanceToFirstSymbol(lineId, -1, textWidth);
            int distToLineBreak = getDistanceToFirstSymbol(lineId, -2, textWidth);
            int distBeforeInterrupt = getLineLength(lineId);
            if (distToLineBreak > 0) {
                distBeforeInterrupt = Math.min(distToSpace, distToLineBreak);
            } else if (distToSpace > 0) {
                distBeforeInterrupt = distToSpace;
            }

            if (textWidth == firstChar + 1) {
                textWidth = distBeforeInterrupt;
            }

            int[] symbolAdrStartEnd;
            if (!lineBreaksAfterFirstWord && getWideLinePartWidth(color, lineId, firstChar, distBeforeInterrupt - 1) <= targetWidth) {
                textWidth = distBeforeInterrupt + 1;
            } else {
                symbolAdrStartEnd = new int[2];
                symbolAdrStartEnd[0] = firstChar;
                symbolAdrStartEnd[1] = textWidth - firstChar;
                lines.addElement(symbolAdrStartEnd);
                firstChar = textWidth++;
                if (lineBreaksAfterFirstWord) {
                    textWidth = distBeforeInterrupt + 1;
                }

                lineBreaksAfterFirstWord = false;
            }

            if (distBeforeInterrupt == getLineLength(lineId)) {
                if (distBeforeInterrupt - firstChar > 0) {
                    (symbolAdrStartEnd = new int[2])[0] = firstChar;
                    symbolAdrStartEnd[1] = distBeforeInterrupt - firstChar;
                    lines.addElement(symbolAdrStartEnd);
                }

                return lines;
            }

            if (distToLineBreak > 0 && distToLineBreak < distToSpace) {
                lineBreaksAfterFirstWord = true;
            }
        }
    }

    //Поиск первого символа с заданным кодом
    private static int getDistanceToFirstSymbol(int lineId, int symbolId, int textLength) {
        for (int symbolNumber = textLinesAdress[lineId] + textLength; symbolNumber < textLinesAdress[lineId + 1]; ++symbolNumber) {
            if (textLinesSymbols[symbolNumber] == symbolId) {
                return symbolNumber - textLinesAdress[lineId];
            }
        }

        return -1;
    }

    //Отрисовка текста по якорю 2. С такими же аргументами
    private static void drawTextByAnchor2(int color, byte[] text, int firstSymbol, int lastSymbol, int x, int y, int anchor) {//прокручиваемый текст
        //Положение первого символа по горизонтали, согласно якорю
        int textWidth = getWideTextWidth(color, text, firstSymbol, lastSymbol);
        x += getWidthAnchorOffset(textWidth, anchor);
        //Положение первого символа по вертикали
        int textHeight = getSymbolHeight(color);
        y += getHeightAnchorOffset(textHeight, anchor);

        //Рисуем текст слева направо
        for (int symbolCount = firstSymbol; symbolCount < lastSymbol; ++symbolCount) {
            x += drawSymbol(color, text[symbolCount], x, y);
        }

    }

    //Рисуем конкретные символы из строки
    private static void drawPartOfTextByAnchor(Graphics graph, int color, 
            int lineId, int startSymbolOffset, int endSymbolOffset, 
            int x, int y, int anchor) {
        drawTextByAnchor2(color, textLinesSymbols, 
                textLinesAdress[lineId] + startSymbolOffset, 
                textLinesAdress[lineId] + startSymbolOffset + endSymbolOffset, 
                x, y, anchor);
    }

    //Меняем местами набор аргументов???
    private static void drawPartOfTextByAnchorWrapper(int lineId, int startOffset, 
            int endOffset, int x, int y, int anchor, int color, Graphics graphics) {
        drawPartOfTextByAnchor(graphics, color, lineId, startOffset, endOffset, 
                x, y, anchor);
    }

    public static void drawReplicInsideFrame(int lineId, int x, int y, int anchor, int color, Graphics graph, int startLine, int lineCount, Vector linesStartsEnds) {
        if (lineCount == -1) {
            lineCount = linesStartsEnds.size();
        }

        int xFinal = x;
        int yFinal = y;
        if ((anchor & 32) == 32) {
            yFinal = y - linesStartsEnds.size() * getSymbolHeight(color);
        } else if ((anchor & 2) == 2) {
            yFinal = y - linesStartsEnds.size() * getSymbolHeight(color) / 2;
        }

        //Object var11 = null;
        int currentLine;
        if ((currentLine = startLine + lineCount) > linesStartsEnds.size()) {
            currentLine = linesStartsEnds.size();
        }

        for (int drawingLine = startLine; drawingLine < currentLine; ++drawingLine) {
            int[] lineSymbolOffset = (int[]) linesStartsEnds.elementAt(drawingLine);
            drawPartOfTextByAnchorWrapper(lineId, lineSymbolOffset[0], lineSymbolOffset[1], xFinal, yFinal, anchor, color, graph);
            yFinal += getSymbolHeight(color);
        }

    }

    //Получить ширину строки с +1 расстоянием между символами, из текста
    private static int getWideTextWidth(int color, byte[] text, int firstSymbol, int lastSymbol) {
        int lineWidth = 0;

        for (int symbolCount = firstSymbol; symbolCount < lastSymbol; ++symbolCount) {
            lineWidth += getSymbolWidth(color, text[symbolCount]) + 1;
        }

        return lineWidth;
    }

    //Получить ширину строки с +1 расстоянием между символами, по номеру строки
    public static int getWideLineWidth(int color, int adress) {
        return getWideTextWidth(color, textLinesSymbols, 
                textLinesAdress[adress], textLinesAdress[adress + 1]);
    }

    //Получить ширину части строки по её адресу
    private static int getWideLinePartWidth(int color, int adress, int firstSymbol, int lastSymbol) {
        return getWideTextWidth(color, textLinesSymbols, 
                textLinesAdress[adress] + firstSymbol, textLinesAdress[adress] + lastSymbol);
    }

    //Оффсет положения первого символа по якорю, по горизонтали
    private static int getWidthAnchorOffset(int textWidth, int anchor) {
        return (anchor & 1) == 1 ? -(textWidth >>> 1) : ((anchor & 8) == 8 ? -textWidth : 0);
    }

    //Оффсет положения первого символа по якорю, по вертикали
    private static int getHeightAnchorOffset(int textHeight, int anchor) {
        return (anchor & 2) == 2 ? -(textHeight >>> 1) : ((anchor & 32) == 32 ? -textHeight : 0);
    }
}
