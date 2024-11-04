package code;

import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public final class KeyboardScreen extends CentralText {

    public static final int var_36 = AllScreens.TEXT_LINE_HEIGHT_NOINDENT + 4;
    public byte[] var_8c = new byte[8];
    public int var_12b;
    public byte var_18a;

    public final boolean onShow(byte screenId) {
        if (Main.main.numberOfPlayers == 3) {
            try {
                RecordStore.deleteRecordStore("qpmrsp");
            } catch (RecordStoreException exc) {
                exc.printStackTrace();
            }

            ((PlayersRecords) AllScreens.recordsScreen).deletePlayerRecord((byte) 0);
            ResourseManager.sub_4d();
        }

        super.onShow(screenId);
        this.var_12b = 0;
        return true;
    }

    public final void paint(Graphics graphics) {
        super.paint(graphics);
        TextCreator.drawReplicWithParameters(0, this.var_8c, 0, this.var_12b, MenuScreen.textClipX + (MenuScreen.textClipWidth >> 1), MenuScreen.textClipY, 9);
        graphics.setColor(0);
        graphics.fillRect(MainMenuScreen.scrWidth / 4, MainMenuScreen.scrHeight / 4 - 5, 2 * MainMenuScreen.scrWidth / 4, 2 * MainMenuScreen.scrHeight / 4 + 5);
        short var2 = TextCreator.massive_withStartnEnds[60];

        for (int var3 = 0; var3 < TextCreator.ReturnLengthOfReplic(60); ++var3) {
            int var4 = MenuScreen.textClipX + var3 % 5 * (MenuScreen.textClipWidth / 5) + 3;
            int var5 = MenuScreen.textClipY + var3 / 5 * var_36 + 3;
            TextCreator.drawReplicWithParameters(0, TextCreator.byte_text_massive, var2 + var3, var2 + var3 + 1, var4, var5, 0);
        }

        graphics.setColor(8421504);
        graphics.drawRect(MenuScreen.textClipX + this.var_18a % 5 * (MenuScreen.textClipWidth / 5), MenuScreen.textClipY + this.var_18a / 5 * var_36, AllScreens.SymbolWidth + 6, AllScreens.SymbolHeight + 6);
    }

    public final void keyPressed(int var1) {
        switch (var1) {
            case -7:
                Main.main.sub_175(this.var_8c, (byte) this.var_12b);
                Main.main.currentPlayerRecordId = (byte) (Main.main.numberOfPlayers - 1);
                Main.main.setScreen(AllScreens.loadingScreen, (byte) 5);
                break;
            case -6:
                if (this.var_12b > 0) {
                    this.var_12b = Math.max(0, this.var_12b - 1);
                } else {
                    Main.main.setScreen(AllScreens.menu, (byte) 1);
                }
                break;
            case -5:
            case 53:
                if (this.var_12b >= 8) {
                    return;
                }

                short var2 = TextCreator.massive_withStartnEnds[60];
                byte var3 = TextCreator.byte_text_massive[var2 + this.var_18a];
                this.var_8c[this.var_12b++] = var3;
                break;
            case -4:
                if (this.var_18a < TextCreator.ReturnLengthOfReplic(60) - 1) {
                    ++this.var_18a;
                }
                break;
            case -3:
                if (this.var_18a > 0) {
                    --this.var_18a;
                }
                break;
            case -2:
                if (this.var_18a / 5 < TextCreator.ReturnLengthOfReplic(60) / 5) {
                    this.var_18a = (byte) (this.var_18a + 5);
                }

                if (this.var_18a >= TextCreator.ReturnLengthOfReplic(60)) {
                    this.var_18a = (byte) (this.var_18a - 5);
                }
                break;
            case -1:
                if (this.var_18a / 5 > 0) {
                    this.var_18a = (byte) (this.var_18a - 5);
                }
        }

        Main.main.repaint();
    }

}
