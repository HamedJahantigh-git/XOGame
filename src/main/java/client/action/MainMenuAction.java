package client.action;

import client.graphic.Graphic;
import client.graphic.myComponent.MyButton;
import common.protocol.GameState;

public class MainMenuAction extends MyAction {

    public MainMenuAction(Graphic graphic) {
        super(graphic);
    }

    public void exitGameAction(MyButton exitGameButton) {
        exitGameButton.addActionListener(actionEvent -> {
            clientNetwork.getSender().getMainMenuHandler().savePlayer();
            System.exit(0);
        });
    }

    public void playAction(MyButton playButton) {
        playButton.addActionListener(actionEvent -> {
            graphic.getUserFrame().getMainMenu().waitForGamePanel();
            clientNetwork.getSender().getMainMenuHandler().requestForPlay();
        });
    }

    public void startGame(GameState gameState) {
        graphic.getUserFrame().startGame(gameState);
    }

}
