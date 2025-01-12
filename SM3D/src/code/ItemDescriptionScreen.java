package code;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class ItemDescriptionScreen implements Screen {

    public int nameFrameXstart;
    public int nameFrameXend;
    public int nameFrameYstart;

    public int xStart;
    public int yStart;
    public int frameWidth;
    public int frameHeight;

    public int maxTextWindowCapacity;

    public int oldYoffset; //работает странно. Отключает прокрутку текста, дублируя yOffset

    public int linesCounter;
    public int textDescrHeight;
    public int singleLineHeight;

    public int nameText;         //рабочее, но не используется
    public byte[] itemStatsText; //рабочее, но не используется
    public int descrText;

    private static int yOffset;
    private static int currTimeMillis;
    public static int previousTime;
    public byte[][] statsTextAndValues;//см. ниже
    //точность:
    //DAMAGE:
    //RATE OF DUERCGARGE:
    //BULLET FAKA:
    //RADIATION FAKA:
    //ANOMALY FAKA:
    //ЗДОРОВЬЕ:
    //СИЛА:
    public byte[][] statsClosingCharacters;//%%%%%%%%%__
    public static Vector textLinesStartsEnds = new Vector();

    public final void resetVariables() {
        this.nameText = -1;
        this.itemStatsText = null;//new byte[]{15, -1, -1, -1, -1, -1, -1, -1}
        this.descrText = -1;
    }

    public static void resetTextOffset() {
        yOffset = 0;
        currTimeMillis = (int) System.currentTimeMillis();
        previousTime = 0;
    }

    public final void paint(Graphics graphics) {
        boolean var4 = false;
        this.yStart = this.nameFrameYstart + this.drawNameText(0, this.nameText);//Если заменить 
        this.maxTextWindowCapacity = this.frameHeight - this.yStart;
        int totalTextHeight = this.drawStatsText(this.itemStatsText);
        this.textDescrHeight = this.drawDescriptionText(1, this.descrText, totalTextHeight, true)[0];
        int currTime;
        //Если текст выходит за пределы окошка
        if (this.textDescrHeight >= this.maxTextWindowCapacity && (currTime = (int) (System.currentTimeMillis() - (long) currTimeMillis)) >= 3000 && currTime - previousTime >= 200) {
            previousTime = currTime;
            yOffset += this.singleLineHeight / 4;//скорость промотки текста
            if (totalTextHeight - yOffset + this.textDescrHeight < totalTextHeight + this.maxTextWindowCapacity / 2) {
                resetTextOffset();
            }
        }

        Main.main.repaint();
    }

    public final void keyPressed(int key) {
    }

    public final boolean onShow(byte screenId) {
        return true;
    }

    public final void setNameFrameLocation(int xStart, int xEnd, int yStart) {
        this.nameFrameXstart = xStart;
        this.nameFrameXend = xEnd;
        this.nameFrameYstart = yStart;
    }

    public final void setDescriptionFrameLocation(int xStart, int yStart, int width, int height) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.frameWidth = width;
        this.maxTextWindowCapacity = height;
        this.frameHeight = height + yStart;
    }

    private int drawStatsText(byte[] text) {
        if (text == null) {
            return 0;
        } else {
            int textHeight = TextCreator.getSymbolHeight(1) + 0;
            int totalTextBlockHeight = 0;

            for (int i = 0; i < text.length; ++i) {
                if (text[i] != -1) {
                    int[] var5 = this.getTotalTextHeight(1, this.statsTextAndValues[i], totalTextBlockHeight, true);
                    totalTextBlockHeight += var5[0];
                    byte[] textArray = TextCreator.combineText(TextCreator.createTextFromNumber(text[i]), this.statsClosingCharacters[i]);
                    int var7 = TextCreator.getTextWidth(1, textArray, 0, textArray.length);
                    if (this.frameWidth - var5[1] <= var7) {
                        totalTextBlockHeight += textHeight;
                    }

                    this.getTotalTextHeight(1, textArray, totalTextBlockHeight, false);
                    totalTextBlockHeight += textHeight;
                }
            }

            return totalTextBlockHeight + textHeight;
        }
    }

    private int[] getTotalTextHeight(int color, byte[] textArray, int yOffset, boolean fromLeftToRight) {
        if (textArray == null) {
            return new int[]{0, 0};
        } else {
            this.singleLineHeight = TextCreator.getSymbolHeight(color) + 0;
            MasterCanvas.graphics.setClip(this.xStart, this.yStart, this.frameWidth, this.maxTextWindowCapacity);
            int numberOfMassive_start = 0;
            boolean var6 = false;
            this.linesCounter = 0;
            byte emptySpaceSymbolId = TextCreator.getSymbolFromLine(63, 0);
            int[] linesHeights = new int[2];

            int textLength;
            do {
                textLength = TextCreator.getTextLength(color, textArray, numberOfMassive_start, this.frameWidth);
                if (textArray[textLength] != emptySpaceSymbolId && textLength < textArray.length - 1) {
                    int var9;
                    textLength = (var9 = TextCreator.getTextLengthWithoutSymbol(textArray, emptySpaceSymbolId, textLength)) != -1 && var9 != numberOfMassive_start ? var9 : textLength;
                }

                TextCreator.drawTextByAnchor(color, textArray, numberOfMassive_start, textLength + 1, fromLeftToRight ? this.xStart : this.xStart + this.frameWidth, this.yStart + yOffset + this.linesCounter * this.singleLineHeight + this.oldYoffset, fromLeftToRight ? 0 : 10);
                if (textLength == textArray.length - 1) {
                    linesHeights[1] = TextCreator.getTextWidth(color, textArray, numberOfMassive_start, textLength);
                }

                ++this.linesCounter;
                numberOfMassive_start = textLength;
            } while (textLength < textArray.length - 1);

            linesHeights[0] = this.singleLineHeight * this.linesCounter;
            return linesHeights;
        }
    }
    //Отрисовка описания предмета
    private int[] drawDescriptionText(int color, int replicNumber, int yOffsetLocal, boolean fromLeftToRight) {
        if (replicNumber == -1) {
            return new int[]{0, 0};
        } else {
            textLinesStartsEnds = TextCreator.splitOnLines(replicNumber, this.frameWidth, color);
            this.singleLineHeight = TextCreator.getSymbolHeight(color) + 0;
            MasterCanvas.graphics.setClip(this.xStart, this.yStart, this.frameWidth, this.maxTextWindowCapacity);
            int[] var5 = new int[2];
            TextCreator.drawReplicInsideFrame(replicNumber, fromLeftToRight ? this.xStart : this.xStart + this.frameWidth, this.yStart + yOffsetLocal + this.linesCounter * this.singleLineHeight + this.oldYoffset - yOffset, 0, color, MasterCanvas.graphics, 0, -1, textLinesStartsEnds);
            var5[0] = TextCreator.getSymbolHeight(color) * textLinesStartsEnds.size();
            return var5;
        }
    }
    //Имя предмета. Не используется
    private int drawNameText(int color, int replicNumber) {
        if (replicNumber == -1) {
            return 0;
        } else {
            byte[] symbolMassive = TextCreator.createTextFromLine(replicNumber);
            int firstSymbol = 0;
            boolean var5 = false;
            int HeightOfText = 0;
            byte emptySpaceSymbolId = TextCreator.getSymbolFromLine(63, 0);

            int lastSymbol;
            do //считать высоту текста
            {
                lastSymbol = TextCreator.getTextLength(color, symbolMassive, firstSymbol, this.nameFrameXend - this.nameFrameXstart);
                if (symbolMassive[lastSymbol] != emptySpaceSymbolId && lastSymbol < symbolMassive.length - 1) {
                    int var8;
                    lastSymbol = (var8 = TextCreator.getDistanceToFirstSymbolFromEnd(replicNumber, emptySpaceSymbolId, lastSymbol)) == -1 ? lastSymbol : var8;
                }

                TextCreator.drawTextByAnchor(color, symbolMassive, firstSymbol, lastSymbol + 1, this.nameFrameXstart, this.nameFrameYstart + HeightOfText * (TextCreator.getSymbolHeight(color) + 0), 0);
                ++HeightOfText;
                firstSymbol = lastSymbol;
            } while (lastSymbol < symbolMassive.length - 1);

            return HeightOfText * (TextCreator.getSymbolHeight(color) + 0);
        }
    }

}
