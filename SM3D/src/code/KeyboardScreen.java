package code;

import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public final class KeyboardScreen extends CentralText {

    public static final int nameHeight = AllScreens.TEXT_LINE_HEIGHT_NOINDENT + 4;
    public byte[] nameText = new byte[8];
    public int nameLength;
    public byte currentSelectedSymbol;

    public final boolean onShow(byte screenId) {
        if (Main.main.numberOfPlayers == 3) {
            try {
                RecordStore.deleteRecordStore("qpmrsp");
            } catch (RecordStoreException exc) {
                exc.printStackTrace();
            }

            ((PlayersRecords) AllScreens.recordsScreen).deletePlayerRecord((byte) 0);
            ResourceManager.savePlayersRecords();
        }

        super.onShow(screenId);
        this.nameLength = 0;
        return true;
    }

    public final void paint(Graphics graphics) {
        super.paint(graphics);
        TextCreator.drawTextByAnchor(0, this.nameText, 0, this.nameLength, MenuScreen.textClipX + (MenuScreen.textClipWidth >> 1), MenuScreen.textClipY, 9);
        graphics.setColor(0);
        graphics.fillRect(MainMenuScreen.scrWidth / 4, MainMenuScreen.scrHeight / 4 - 5, 2 * MainMenuScreen.scrWidth / 4, 2 * MainMenuScreen.scrHeight / 4 + 5);
        short var2 = TextCreator.textLinesAdress[60];

        for (int var3 = 0; var3 < TextCreator.getLineLength(60); ++var3) {
            int var4 = MenuScreen.textClipX + var3 % 5 * (MenuScreen.textClipWidth / 5) + 3;
            int var5 = MenuScreen.textClipY + var3 / 5 * nameHeight + 3;
            TextCreator.drawTextByAnchor(0, TextCreator.textLinesSymbols, var2 + var3, var2 + var3 + 1, var4, var5, 0);
        }

        graphics.setColor(8421504);
        graphics.drawRect(MenuScreen.textClipX + this.currentSelectedSymbol % 5 * (MenuScreen.textClipWidth / 5), MenuScreen.textClipY + this.currentSelectedSymbol / 5 * nameHeight, AllScreens.SymbolWidth + 6, AllScreens.SymbolHeight + 6);
    }

    public final void keyPressed(int var1) {
        switch (var1) {
            case -7:
                Main.main.addPlayerRecord(this.nameText, (byte) this.nameLength);
                Main.main.currentPlayerRecordId = (byte) (Main.main.numberOfPlayers - 1);
                Main.main.setScreen(AllScreens.loadingScreen, (byte) 5);
                break;
            case -6:
                if (this.nameLength > 0) {
                    this.nameLength = Math.max(0, this.nameLength - 1);
                } else {
                    Main.main.setScreen(AllScreens.menu, (byte) 1);
                }
                break;
            case -5:
            case 53:
                if (this.nameLength >= 8) {
                    return;
                }

                short var2 = TextCreator.textLinesAdress[60];
                byte symbolId = TextCreator.textLinesSymbols[var2 + this.currentSelectedSymbol];
                this.nameText[this.nameLength++] = symbolId;
                break;
            case -4:
                if (this.currentSelectedSymbol < TextCreator.getLineLength(60) - 1) {
                    ++this.currentSelectedSymbol;
                }
                break;
            case -3:
                if (this.currentSelectedSymbol > 0) {
                    --this.currentSelectedSymbol;
                }
                break;
            case -2:
                if (this.currentSelectedSymbol / 5 < TextCreator.getLineLength(60) / 5) {
                    this.currentSelectedSymbol = (byte) (this.currentSelectedSymbol + 5);
                }

                if (this.currentSelectedSymbol >= TextCreator.getLineLength(60)) {
                    this.currentSelectedSymbol = (byte) (this.currentSelectedSymbol - 5);
                }
                break;
            case -1:
                if (this.currentSelectedSymbol / 5 > 0) {
                    this.currentSelectedSymbol = (byte) (this.currentSelectedSymbol - 5);
                }
        }

        Main.main.repaint();
    }

}
