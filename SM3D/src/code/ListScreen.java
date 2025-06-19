package code;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class ListScreen extends MenuScreen {

    public int numberOfOptions;
    public int selectedIndex;
    public int offScreenTextHeight;
    public byte offScreenIndex;
    public byte screenOptionsCapacity;
    public byte redrawAnimOnly;
    private Timer timer;
    public Graphics graphics;
    public int[] optionsTextId;
    public Screen[] screenByOptionId;
    public byte[] screenIdByOptionId;
    
    public static int[][] allListOptionsTextId = new int[][] {
		null,
                //settings options text ids:
                //42 sound
                //43 vibro
		new int[] {42, 43},
                //pause options text ids:
                //57 continue
                //58 back to main menu
                //0 
                //50 inventory
                //51 character
                //56 map
                //367 magazine(tasks)
		new int[] {57, 58, 0, 50, 51, 56, 367}, 
		null
	};
	
    public static byte[][] allListScreenIds = new byte[][] {
		null,
                //settings screen
                //new screen Ids after choosing an option
                //0 no change
		new byte[] {0, 0},
                //pause screen
                //new screen Ids after choosing an option
                //14 - game/map
                //1 - main menu
                //3 - inventory/tasks
                //5 - character leveling
		new byte[] {14, 1, 0, 3, 5, 14, 3}, 
		null
	};
	
    public static Screen[] settingsScreens;
    public static Screen[] pauseScreens;
    public static Screen[][] allListScreens;
    private Vector[] textLinesStartsEnds;
    private int[] optionNameHeight;
    private int[] optionDescrHeight;
    private int optionsNamesTotalHeight;
    private int optionsTextTotalSize;

    public ListScreen() {
        super.replicsMassive = new short[][]{{(short) 0, (short) 23, (short) 37, (short) 367, (short) 41}, {(short) 0, (short) 6, (short) 0, (short) 0, (short) 7}, {(short) 0, (short) 10, (short) 10, (short) 6, (short) 11}};
        super.screenIdTransitionLeftOption = new byte[]{(byte) 0, (byte) 1, (byte) 0, (byte) 2, (byte) 0};
        this.offScreenIndex = 0;
        this.screenOptionsCapacity = (byte) Math.min(MenuScreen.textClipHeight / AllScreens.SINGLE_TEXT_LINE_HEIGHT, this.numberOfOptions);
        this.timer = new Timer(true, 100L);
        Main.main.repaint();
    }

    public final void resetVariables() {
        settingsScreens = new Screen[]{AllScreens.menu2, AllScreens.menu2};
        pauseScreens = new Screen[]{AllScreens.menu, AllScreens.menu, AllScreens.menu2, AllScreens.masterInventory, AllScreens.masterInventory, AllScreens.menu, AllScreens.pauseScreen};
        allListScreens = new Screen[][]{null, settingsScreens, pauseScreens, null};
        super.screensTransitionLeftOption = new Screen[]{AllScreens.menu2, AllScreens.menu, AllScreens.menu2, AllScreens.pauseScreen, AllScreens.menu2};
    }

    public boolean onShow(byte screenId) {
        super.onShow(screenId);
        if (screenId == 3) {
            this.numberOfOptions = Scripts.allQuestsNumber;
            this.optionsTextId = Scripts.questLocationDescriptionId;
            this.initList();
        } else if (!(this instanceof PlayersRecords)) {
            this.optionsTextId = allListOptionsTextId[screenId];//
            this.screenByOptionId = allListScreens[screenId];//screen
            this.screenIdByOptionId = allListScreenIds[screenId];//
            this.numberOfOptions = this.optionsTextId.length;
        }

        super.interfaceNumber = 44;
        this.selectedIndex = 0;
        this.offScreenIndex = 0;
        int var2;
        if (screenId == 3) {
            var2 = TextCreator.getSymbolHeight(0) * 3;
        } else {
            var2 = 0;
        }

        this.screenOptionsCapacity = (byte) Math.min(MenuScreen.textClipHeight / (AllScreens.SINGLE_TEXT_LINE_HEIGHT + var2), this.numberOfOptions);
        this.offScreenTextHeight = 0;
        this.redrawAnimOnly = 4;
        this.timer.resetTimer();
        synchronized (this) {
            this.notifyAll();
        }

        AllScreens.listDefaultXoffset = ResourceManager.getUIElementXcoord(45, 0);
        return true;
    }

    private void initList() {
        this.optionsNamesTotalHeight = 0;
        this.optionsTextTotalSize = 0;
        this.optionNameHeight = new int[Scripts.allQuestsNumber];
        this.optionDescrHeight = new int[Scripts.allQuestsNumber];
        this.textLinesStartsEnds = new Vector[Scripts.allQuestsNumber];

        for (byte option = 0; option < Scripts.allQuestsNumber; ++option) {
            this.textLinesStartsEnds[option] = TextCreator.splitOnLines(this.optionsTextId[option], 80, 0);
            if (option > 0) {
                this.optionNameHeight[option] = this.optionNameHeight[option - 1] + TextCreator.getSymbolHeight(0) * (this.textLinesStartsEnds[option].size() - 1);
            } else {
                this.optionNameHeight[option] = TextCreator.getSymbolHeight(0) * (this.textLinesStartsEnds[option].size() - 1);
            }

            this.optionDescrHeight[option] = TextCreator.getSymbolHeight(0) * (this.textLinesStartsEnds[option].size() - 1);
            this.optionsNamesTotalHeight += this.optionNameHeight[option];
            this.optionsTextTotalSize += this.textLinesStartsEnds[option].size();
        }

    }

    //отрисовка реплик меню паузы или настроек или пда?
    public void drawItem(int item) {
        if (super.drawingScreenId == 3) //3
        {
            int var3 = item > 0 ? this.optionNameHeight[item - 1] : 0;
            TextCreator.drawReplicInsideFrame(this.optionsTextId[item], AllScreens.listDefaultXoffset + 2, MenuScreen.textClipY + this.offScreenTextHeight + AllScreens.SINGLE_TEXT_LINE_HEIGHT * item + 3 + var3, 0, 0, MasterCanvas.graphics, 0, -1, this.textLinesStartsEnds[item]);
        } else {
            byte var2 = 0;
            if (super.drawingScreenId == 2 && (item == 5 && !Scripts.playerCanLeaveLevel || item == 6 && Scripts.allQuestsNumber == 0)) {
                var2 = 1;
            }

            TextCreator.drawLineByAnchor(var2, this.optionsTextId[item], AllScreens.listDefaultXoffset + 2, MenuScreen.textClipY + this.offScreenTextHeight + AllScreens.SINGLE_TEXT_LINE_HEIGHT * item + 3 + 20, 0);
        }
    }

    public final void updateAnimatedImage() {
        short var1 = 0;
        if (this.numberOfOptions != 0) {
            switch (this.timer.getTimerState()) {
                case 0:
                    var1 = 132;
                    break;
                case 1:
                    var1 = 133;
                    break;
                case 2:
                    var1 = 134;
                    break;
                case 3:
                    var1 = 135;
                    break;
                case 4:
                    var1 = 136;
                    break;
                case 5:
                    var1 = 137;
                    break;
                case 6:
                    var1 = 138;
                    this.timer.resetTimer();
            }

            int var3;
            if (super.drawingScreenId == 3) {
                var3 = this.selectedIndex > 0 ? this.optionNameHeight[this.selectedIndex - 1] : 0;
            } else {
                var3 = 20;
            }

            ResourceManager.drawUserInterfacePart(this.graphics, var1, AllScreens.listDefaultXoffset - 2 - (ResourceManager.ReturnWidthOfInterfaceImage(132) >> 1), MenuScreen.textClipY + this.offScreenTextHeight + this.selectedIndex * AllScreens.SINGLE_TEXT_LINE_HEIGHT + 3 + var3, 17);
        }

    }

    public final void paint(Graphics graphics) {
        this.graphics = graphics;
        switch (this.redrawAnimOnly) {
            case 4:
                MenuScreen.menuClipX = 0;
                MenuScreen.menuClipY = 0;
                MenuScreen.menuClipWidth = MainMenuScreen.scrWidth;
                MenuScreen.menuClipHight = MainMenuScreen.scrHeight;
                super.paint(graphics);
                //рисуем надпись "Назад" слева
                if (super.drawingScreenId == 1) {
                    PlayerHUD.drawSoftButtonNames(1, 0, 6, false);
                }

                //Серый цвет шрифта
                graphics.setColor(8421504);
                graphics.setClip(MenuScreen.textClipX, MenuScreen.textClipY, MenuScreen.textClipWidth, MenuScreen.textClipHeight);

                for (int n = 0; n < this.numberOfOptions; ++n) {
                    this.drawItem(n);
                }

                this.redrawAnimOnly = 5;
            case 5:
                MenuScreen.menuClipX = MenuScreen.textClipX;
                MenuScreen.menuClipY = MenuScreen.textClipY;
                MenuScreen.menuClipWidth = AllScreens.listDefaultXoffset - MenuScreen.textClipX;
                MenuScreen.menuClipHight = MenuScreen.textClipHeight;
                super.paint(graphics);
                this.updateAnimatedImage();
            default:
                Main.main.repaint();
        }
    }

    public void keyPressed(int key) {
        switch (key) {
            case 1:
            case 50:
                int var8;
                if (this.selectedIndex != 0) {
                    --this.selectedIndex;
                    boolean var9 = false;
                    if (super.drawingScreenId == 3) {
                        var8 = this.optionDescrHeight[this.selectedIndex];
                    } else {
                        var8 = 0;
                    }

                    if (this.selectedIndex < this.offScreenIndex) {
                        --this.offScreenIndex;
                        this.offScreenTextHeight += AllScreens.SINGLE_TEXT_LINE_HEIGHT + var8;
                        this.redrawAnimOnly = 4;
                    }

                    if (super.drawingScreenId == 2 && this.selectedIndex == 2) {
                        --this.selectedIndex;
                    }

                    if (super.drawingScreenId == 3) {
                        this.selectedIndex = Math.min(Scripts.allQuestsNumber - 1, this.selectedIndex);
                    }
                } else {
                    this.selectedIndex = this.numberOfOptions - 1;
                    if (super.drawingScreenId == 3) {
                        this.selectedIndex = Math.min(Scripts.allQuestsNumber - 1, this.selectedIndex);
                    }

                    this.offScreenIndex = (byte) Math.max(this.selectedIndex - this.screenOptionsCapacity, 0);
                    if (super.drawingScreenId == 3) {
                        var8 = TextCreator.getSymbolHeight(0) * 3;
                    } else {
                        var8 = 0;
                    }

                    this.offScreenTextHeight = -this.offScreenIndex * (AllScreens.SINGLE_TEXT_LINE_HEIGHT + var8);
                    this.redrawAnimOnly = 4;
                }

                Main.main.repaint();
                return;
            case 3:
            case 8:
            case 53:
                if (this.optionsTextId[this.selectedIndex] == 0) {
                    return;
                } else {
                    synchronized (this) {
                        ;
                    }

                    if (super.drawingScreenId == 3) {
                        Main.main.setScreen(super.screensTransitionLeftOption[super.drawingScreenId], super.screenIdTransitionLeftOption[super.drawingScreenId]);
                        return;
                    } else {
                        if (super.drawingScreenId == 2 && this.selectedIndex == 1) {
                            GameScene.SetToNullAllWorldnMeshMassives();
                            ResourceManager.loadInterfaceImage(2);
                            if (GameScene.currentLocation == 1 || GameScene.currentLocation == 6 || GameScene.currentLocation == 12)
                            {
                                ResourceManager.saveGame();
                            }
                        }

                        switch (this.selectedIndex) {
                            case 0:
                                GameScene.gamePaused = false;
                                Main.main.setScreen(this.screenByOptionId[this.selectedIndex], this.screenIdByOptionId[this.selectedIndex]);
                                ResourceManager.runGarbageCollector();
                                return;
                            case 5:
                                if (Scripts.playerCanLeaveLevel) {
                                    Scripts.openPDA();
                                    Main.main.setScreen(this.screenByOptionId[this.selectedIndex], this.screenIdByOptionId[this.selectedIndex]);
                                    return;
                                }

                                return;
                            case 6:
                                if (Scripts.allQuestsNumber != 0) {
                                    Main.main.setScreen(this.screenByOptionId[this.selectedIndex], this.screenIdByOptionId[this.selectedIndex]);
                                    return;
                                }

                                return;
                            default:
                                Main.main.setScreen(this.screenByOptionId[this.selectedIndex], this.screenIdByOptionId[this.selectedIndex]);
                                Main.main.repaint();
                        }
                    }
                }
            case 4:
                if (super.drawingScreenId == 3) {
                    return;
                }

                synchronized (this) {
                    ;
                }

                Main.main.setScreen(super.screensTransitionLeftOption[super.drawingScreenId], super.screenIdTransitionLeftOption[super.drawingScreenId]);
                return;
            case 6:
            case 56:
                if (this.selectedIndex != this.numberOfOptions - 1) {
                    byte var2 = 0;
                    if (super.drawingScreenId == 3) {
                        var2 = 1;
                    }

                    if (this.selectedIndex == this.offScreenIndex + this.screenOptionsCapacity - var2) {
                        ++this.offScreenIndex;
                        int var3;
                        if (super.drawingScreenId == 3) {
                            var3 = this.optionDescrHeight[this.offScreenIndex - 1];
                        } else {
                            var3 = 0;
                        }

                        this.offScreenTextHeight -= AllScreens.SINGLE_TEXT_LINE_HEIGHT + var3;
                        this.redrawAnimOnly = 4;
                    }

                    ++this.selectedIndex;
                    if (super.drawingScreenId == 2 && this.selectedIndex == 2) {
                        ++this.selectedIndex;
                    }

                    if (super.drawingScreenId == 3) {
                        this.selectedIndex = Math.min(Scripts.allQuestsNumber - 1, this.selectedIndex);
                    }
                } else {
                    this.selectedIndex = 0;
                    this.offScreenIndex = (byte) this.selectedIndex;
                    this.offScreenTextHeight = 0;
                    this.redrawAnimOnly = 4;
                }

                Main.main.repaint();
            default:
        }
    }
}
