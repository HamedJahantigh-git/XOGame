package client.action;

import client.enums.MessageEnum;
import client.graphic.Graphic;
import common.protocol.GameState;

import javax.swing.*;

public class GameAction extends MyAction {
    public GameAction(Graphic graphic) {
        super(graphic);
    }

    public void blockSelect(JButton button, int i, int j) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().getGameHandler().selectBlock(i, j);
        });
    }

    public void newGameState(GameState gameState) {
        graphic.getUserFrame().getGameMenu().setGameState(gameState);
        graphic.getUserFrame().getGameMenu().drawGameMenu();
    }

    public void gameFinished(MessageEnum messageEnum, String username, String winNum, String loseNum) {
        graphic.getUserFrame().setUserInfo(username,winNum,loseNum);
        graphic.getUserFrame().getGameMenu().drawFinishPanel(messageEnum);
    }
}
