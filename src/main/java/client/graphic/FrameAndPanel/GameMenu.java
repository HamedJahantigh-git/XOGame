package client.graphic.FrameAndPanel;

import client.action.GameAction;
import client.enums.*;
import client.graphic.GraphicDefault;
import client.graphic.myComponent.CellComponent;
import client.graphic.myComponent.MessagePanel;
import client.graphic.myComponent.MyLabel;
import client.graphic.myComponent.MyPanel;
import common.MyThread;
import common.protocol.GameState;

import java.awt.*;


public class GameMenu {
    private UserFrame userFrame;
    private MyPanel mainPanel;
    private MyPanel textPanel;
    private MyPanel boardPanel;

    private GameAction gameAction;
    private GameState gameState;

    public GameMenu(UserFrame userFrame) {
        this.userFrame = userFrame;
        this.mainPanel = new MyPanel(userFrame.getPane(), GraphicLayer.GAME_PANEL,
                ClientPath.GAME_MENU_BACKGROUND, GraphicDefault.GameMenu.mainBounds, false);
        this.textPanel = new MyPanel(userFrame.getPane(), GraphicLayer.GAME_TEXT_PANEL,
                ClientPath.GAME_TEXT_BACKGROUND, GraphicDefault.GameMenu.textPanel(
                ComponentEnum.MAIN_BOX), false);
        this.boardPanel = new MyPanel(userFrame.getPane(), GraphicLayer.GAME_BOARD_PANEL,
                ClientPath.GAME_BOARD_BACKGROUND, GraphicDefault.GameMenu.boardPanel(
                ComponentEnum.MAIN_BOX, 0, 0), false);

        this.gameAction = new GameAction(userFrame.getGraphic());
    }

    public void startGame(GameState gameState) {
        this.gameState = gameState;
        drawGameMenu();
        mainPanel.setVisible(true);
        textPanel.setVisible(true);
        gameAction.setClientNetwork();
    }

    public void drawGameMenu() {
        drawGameBoard();
        drawGameText();
    }

    private void drawGameText() {
        textPanel.clearAllComponent();
        new MyLabel(textPanel, "Turn: " + gameState.getPlayerTurnUsername(),
                FontName.MESSAGE_TEXT.getName(), 20, Color.BLACK,
                GraphicDefault.GameMenu.textPanel(ComponentEnum.TURN_TEXT));
        new MyLabel(textPanel, "Opponent Name: " + gameState.getOpponentUsername(),
                FontName.MESSAGE_TEXT.getName(), 20, Color.BLACK,
                GraphicDefault.GameMenu.textPanel(ComponentEnum.OPPONENT_TEXT));
        String mark;
        if (gameState.getMineMark() == 1)
            mark = "X";
        else
            mark = "O";
        new MyLabel(textPanel, "Mine Mark: " + mark,
                FontName.MESSAGE_TEXT.getName(), 20, Color.BLACK,
                GraphicDefault.GameMenu.textPanel(ComponentEnum.MINE_MARK_TEXT));
        textPanel.validate();
        textPanel.repaint();

    }

    private void drawGameBoard() {
        boardPanel.clearAllComponent();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                CellComponent cellComponent;
                switch (gameState.getBoard()[i][j]) {
                    case 0:
                        cellComponent = new CellComponent(boardPanel,
                                ClientPath.EMPTY_BLOCK, GraphicDefault.GameMenu.boardPanel(
                                ComponentEnum.BLOCK, i, j));
                        gameAction.blockSelect(cellComponent.getButton(), i, j);
                        break;
                    case 1:
                        new CellComponent(boardPanel, ClientPath.X_BLOCK,
                                GraphicDefault.GameMenu.boardPanel(ComponentEnum.BLOCK, i, j));
                        break;
                    case 2:
                        new CellComponent(boardPanel, ClientPath.O_BLOCK,
                                GraphicDefault.GameMenu.boardPanel(ComponentEnum.BLOCK, i, j));
                        break;
                }

            }
        }
        boardPanel.validate();
        boardPanel.repaint();

    }

    public GameAction getGameAction() {
        return gameAction;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void drawFinishPanel(MessageEnum messageEnum) {
        userFrame.offLayer(GraphicLayer.GAME_PANEL.getLayer() + 1,
                GraphicLayer.GAME_END.getLayer());
        MessagePanel finishPanel = new MessagePanel(messageEnum, userFrame.getPane(),
                GraphicLayer.GAME_FINISH_PANEL, GraphicDefault.GameMenu.finishPanelBounds,
                45, false);
        finishPanel.setVisible(true);
        MyThread.delay(7000);
        userFrame.startMainMenu();
    }
}
