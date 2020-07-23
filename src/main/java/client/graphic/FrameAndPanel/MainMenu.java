package client.graphic.FrameAndPanel;

import client.action.MainMenuAction;
import client.enums.ClientPath;
import client.enums.ComponentEnum;
import client.enums.FontName;
import client.enums.GraphicLayer;
import client.graphic.GraphicDefault;
import client.graphic.myComponent.*;

import java.awt.*;

public class MainMenu {
    private MyPanel mainPanel;
    private UserFrame userFrame;

    private MyPanel userBox;

    private MainMenuAction mainMenuAction;

    public MainMenu(UserFrame userFrame) {
        this.userFrame = userFrame;
        this.mainPanel = new MyPanel(userFrame.getPane(), GraphicLayer.MAIN_PANEL,
                ClientPath.USER_MENU_BACKGROUND, GraphicDefault.UserMenu.mainBounds, false);
        this.mainMenuAction = new MainMenuAction(userFrame.getGraphic());
        init();
    }

    private void init() {
        buildButtons();
        buildScoreBox();
        userBox();
    }

    public void reDrawComponent() {
        drawScoreBoxContent();
    }

    public void drawScoreBoxContent() {
        userBox.clearAllComponent();
        new MyLabel(userBox, "User UserName: " + userFrame.getUsername(),
                FontName.MESSAGE_TEXT.getName(), 20, Color.BLACK,
                GraphicDefault.MainMenu.userBound(ComponentEnum.USERNAME));
        new MyLabel(userBox, "Win Number: " +userFrame.getWinNum(),
                FontName.MESSAGE_TEXT.getName(), 20, Color.BLACK,
                GraphicDefault.MainMenu.userBound(ComponentEnum.WIN_NUM));
        new MyLabel(userBox, "Lose Number: " +userFrame.getLoseNum(),
                FontName.MESSAGE_TEXT.getName(), 20, Color.BLACK,
                GraphicDefault.MainMenu.userBound(ComponentEnum.LOSE_NUM));

    }

    private void userBox() {
        userBox = new MyPanel(userFrame.getPane(), GraphicLayer.USER_PANEL,
                ClientPath.USER_PANEL, GraphicDefault.MainMenu.userBound(
                ComponentEnum.MAIN_BOX), true);
    }

    private void buildScoreBox() {
        MyPanel scorePanel = new MyPanel(userFrame.getPane(), GraphicLayer.SCORE_PANEL,
                ClientPath.SCORE_PANEL, GraphicDefault.MainMenu.scoreBound(
                ComponentEnum.MAIN_BOX), true);
    }

    private void buildButtons() {
        MyButton exitGameButton = new MyButton(mainPanel, "Exit Game", ClientPath.button1,
                GraphicDefault.MainMenu.buttonBound(ComponentEnum.exitButton), Color.WHITE, 30);
        MyButton playButton = new MyButton(mainPanel, "Play Game", ClientPath.button1,
                GraphicDefault.MainMenu.buttonBound(ComponentEnum.PLAY_BUTTON), Color.WHITE, 30);
        mainMenuAction.exitGameAction(exitGameButton);
        mainMenuAction.playAction(playButton);

    }

    public void waitForGamePanel() {
        userFrame.offLayer(GraphicLayer.MAIN_PANEL.getLayer() + 1,
                GraphicLayer.MAIN_MENU_END_LAYER.getLayer());
        mainPanel.offAllComponent();
        MyPanel waitPanel = new MyPanel(userFrame.getPane(), GraphicLayer.MESSAGE_PANEL,
                ClientPath.WAITING_IMAGE, GraphicDefault.MainMenu.waitBound, true);
    }

    public MyPanel getMainPanel() {
        return mainPanel;
    }

    public MainMenuAction getMainMenuAction() {
        return mainMenuAction;
    }
}
