package code;

import javax.microedition.lcdui.Graphics;

public final class PlayerStatsScreen implements Screen {

    public byte selectedStat;

    //Прямая ссылка в этот раз пуста, хотя в других классах она передавалась в
    //this. Возможно, в этом окне ранее была возможность поменять одежду
    //в инвентаре?
    public PlayerStatsScreen(MasterInventoryScreen directLink) {
    }

    public final void resetVariables() {
    }

    public final void paint(Graphics graphics) {
        //Рисуем рамку интерфейса и задаём рабочую область
        ResourceManager.drawUserInterfaceItems(graphics, 42, 0, 0);

        graphics.setClip(0, 0, MainMenuScreen.scrWidth, MainMenuScreen.scrHeight);

        ResourceManager.drawUserInterfaceItems(MasterCanvas.graphics, 87, 0, 0);
        ResourceManager.drawUserInterfaceItems(MasterCanvas.graphics, 86, 0, 0);

        //отрисовка текущего уровня игрока
        //id 39: УРОВЕНЬ:__
        byte[] currentValueText = 
                TextCreator.combineText(TextCreator.createTextFromLine(39), 
                        TextCreator.createTextFromNumber(Scripts.playerLevel));
        int x_start = ResourceManager.getUIElementXcoord(43, 0);
        int y_start = ResourceManager.getUIElementYcoord(43, 0);
        TextCreator.drawTextByAnchor(0, currentValueText, 0, currentValueText.length, x_start, y_start, 4);

        //сохраняем характеристики персонажа и вкаченные уровни в массив
        byte[] statsLevels = getPlayerStatsLevel();
        short[] statsValues = getPlayerStats();
        boolean var7 = false;
        //0 color is highlighted, 1 is not
        int color;
        
        //Поле слева вверху. Отрисовка названия уровней для поднятия
        //id 68: ТОЧНОСТЬ: 
        TextCreator.drawLineByAnchor(color = this.selectedStat == 0 ? 0 : 1, 
                68, ResourceManager.getUIElementXcoord(43, 2), 
                ResourceManager.getUIElementYcoord(43, 2), 4);
        x_start = ResourceManager.getUIElementXcoord(43, 3);
        y_start = ResourceManager.getUIElementYcoord(43, 3);
        byte[] currentPlayerLevelText = TextCreator.createTextFromNumber(statsLevels[0]);
        TextCreator.drawTextByAnchor(color, 
                currentPlayerLevelText, 0, currentPlayerLevelText.length, 
                x_start, y_start, 6);

        //67 id text: "+"
        if (this.selectedStat == 0) {
            TextCreator.drawLineByAnchor(color, 67, 
                    x_start + 10, y_start, 5);
        }

        //id 74: ЗДОРОВЬЕ:
        TextCreator.drawLineByAnchor(color = this.selectedStat == 1 ? 0 : 1,
                74, ResourceManager.getUIElementXcoord(43, 4), 
                ResourceManager.getUIElementYcoord(43, 4), 4);
        x_start = ResourceManager.getUIElementXcoord(43, 5);
        y_start = ResourceManager.getUIElementYcoord(43, 5);
        currentPlayerLevelText = TextCreator.createTextFromNumber(statsLevels[1]);
        TextCreator.drawTextByAnchor(color, 
                currentPlayerLevelText, 0, currentPlayerLevelText.length, 
                x_start, y_start, 6);
        
        if (this.selectedStat == 1) {
            TextCreator.drawLineByAnchor(color, 67, 
                    x_start + 10, y_start, 5);
        }

        //id 75: СИЛА:
        TextCreator.drawLineByAnchor(color = this.selectedStat == 2 ? 0 : 1, 
                75, ResourceManager.getUIElementXcoord(43, 6), 
                ResourceManager.getUIElementYcoord(43, 6), 4);
        x_start = ResourceManager.getUIElementXcoord(43, 7);
        y_start = ResourceManager.getUIElementYcoord(43, 7);
        currentPlayerLevelText = TextCreator.createTextFromNumber(statsLevels[2]);
        TextCreator.drawTextByAnchor(color, 
                currentPlayerLevelText, 0, currentPlayerLevelText.length, 
                x_start, y_start, 6);
        
        if (this.selectedStat == 2) {
            TextCreator.drawLineByAnchor(color, 67, 
                    x_start + 10, y_start, 5);
        }

        //Поле справа
        //Отрисовка характеристик игрока, цветным текстом
        short[] statValue;
        //здоровье
        (statValue = TextCreator.makeColoredTextFromNumber(statsValues[0], 
                true))[statValue.length - 1] = 
                (short) (statsValues[0] < 0 ? 157 : 105);
        TextCreator.drawColoredText(statValue, 
                ResourceManager.getUIElementXcoord(43, 14),
                ResourceManager.getUIElementYcoord(43, 14), 10);
        
        //точность
        (statValue = TextCreator.makeColoredTextFromNumber(statsValues[1], 
                true))[statValue.length - 1] = -2;
        TextCreator.drawColoredText(statValue, 
                ResourceManager.getUIElementXcoord(43, 15), 
                ResourceManager.getUIElementYcoord(43, 15), 10);
        
        //пулестойкость
        (statValue = TextCreator.makeColoredTextFromNumber(statsValues[2], 
                true))[statValue.length - 1] = (short) (statsValues[2] < 0 ? 157 : 105);
        TextCreator.drawColoredText(statValue, 
                ResourceManager.getUIElementXcoord(43, 16), 
                ResourceManager.getUIElementYcoord(43, 16), 10);
        
        //устойчивость к радиации
        (statValue = TextCreator.makeColoredTextFromNumber(statsValues[3], 
                true))[statValue.length - 1] = (short) (statsValues[3] < 0 ? 157 : 105);
        TextCreator.drawColoredText(statValue, 
                ResourceManager.getUIElementXcoord(43, 17), 
                ResourceManager.getUIElementYcoord(43, 17), 10);
        
        //защита от аномалий
        (statValue = TextCreator.makeColoredTextFromNumber(statsValues[4], 
                true))[statValue.length - 1] = (short) (statsValues[4] < 0 ? 157 : 105);
        TextCreator.drawColoredText(statValue, 
                ResourceManager.getUIElementXcoord(43, 18), 
                ResourceManager.getUIElementYcoord(43, 18), 10);
        
        //Поле внизу, слева. Свободные очки прокачки
        //id text: ОЧКИ: 
        byte[] sparePointsText = 
                TextCreator.combineText(TextCreator.createTextFromLine(77), 
                        TextCreator.createTextFromNumber(statsLevels[4]));
        TextCreator.drawTextByAnchor(1, sparePointsText, 
                0, sparePointsText.length, 
                ResourceManager.getUIElementXcoord(43, 12), 
                ResourceManager.getUIElementYcoord(43, 12), 4);

        //Отрисовка веса, справа внизу
        currentValueText = 
                TextCreator.combineText(TextCreator.createTextFromNumber(Scripts.playerMaxWeight / 10), 
                        TextCreator.createTextFromLine(64));
        x_start = ResourceManager.getUIElementXcoord(43, 13);
        y_start = ResourceManager.getUIElementYcoord(43, 13);
        TextCreator.drawTextByAnchor(0, currentValueText, 
                0, currentValueText.length, x_start, y_start, 5);

        //Отрисовка полоски опыта
        int width = ResourceManager.getRectangleParams(42, 5, 10)[2] + ResourceManager.getRectangleParams(42, 5, 11)[2] + ResourceManager.getRectangleParams(42, 5, 13)[2] + ResourceManager.getRectangleParams(42, 5, 14)[2];
        int height = ResourceManager.getRectangleParams(42, 5, 8)[3] / 3 - 1;
        x_start = ResourceManager.getRectangleParams(42, 5, 10)[0];
        y_start = ResourceManager.getRectangleParams(42, 5, 10)[1] + height + 3;
        int outlineColor = 1594115; //тёмно-зелёный
        int inlineColor = 2670136; //светло-зелёный

        MasterCanvas.graphics.setColor(outlineColor);
        MasterCanvas.graphics.fillRect(x_start, y_start, 
                width * Scripts.playerLevel / 10, height / 3);

        MasterCanvas.graphics.setColor(inlineColor);
        MasterCanvas.graphics.fillRect(x_start, y_start + height / 3, 
                width * Scripts.playerLevel / 10, height / 3);

        MasterCanvas.graphics.setColor(outlineColor);
        MasterCanvas.graphics.fillRect(x_start, y_start + 2 * height / 3, 
                width * Scripts.playerLevel / 10, height / 3);

        //Отрисовка кнопок меню
        PlayerHUD.drawSoftButtonNames(1, 0, 391, true);
        PlayerHUD.drawSoftButtonNames(0, 1, 6, true);
    }

    //Переключение пунктов меню при поднятии уровней
    public final void keyPressed(int key)
    {
        switch (key) {
            //вернуться назад на 1 пункт
            case 1:
                --this.selectedStat;
                if (this.selectedStat == -1) {
                    this.selectedStat = 2;
                }
            case 2:
            case 5:
            default:
                break;
            case 3:
                //вернуться назад в меню Паузы
                Main.main.setScreen(AllScreens.pauseScreen, (byte) 2);
                break;
            case 4:
                Scripts.levelUpPlayerStat(this.selectedStat);
                break;
            case 6:
                ++this.selectedStat;
                if (this.selectedStat > 2) {
                    this.selectedStat = 0;
                }
        }

        Main.main.repaint();
    }

    public final boolean onShow(byte screenId) {
        return true;
    }

    private static byte[] getPlayerStatsLevel() {
        byte statsLevelSumm = 
                (byte) (Scripts.playerStatLevel[0] 
                + Scripts.playerStatLevel[1] 
                + Scripts.playerStatLevel[2]);
        return new byte[]{
            Scripts.playerStatLevel[0], 
            Scripts.playerStatLevel[1], 
            Scripts.playerStatLevel[2], 
            (byte) 0, 
            (byte) (Scripts.playerLevel - statsLevelSumm), 
            (byte) Scripts.playerLevel};
    }

    private static short[] getPlayerStats() {
        return new short[]{
            (short) Scripts.playerAccuracy, 
            Scripts.playerMaxHealth, 
            (short) Scripts.playerBulletProtection, 
            (short) Scripts.playerRadiationResistance, 
            (short) Scripts.playerAnomalyResistance};
    }
}
