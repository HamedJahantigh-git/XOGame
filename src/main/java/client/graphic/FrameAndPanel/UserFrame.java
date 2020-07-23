package client.graphic.FrameAndPanel;

import client.enums.AllMenuSwitch;

import client.enums.GraphicLayer;
import client.graphic.Graphic;
import client.graphic.GraphicDefault;
import client.graphic.myComponent.LayerManagement;
import client.graphic.myComponent.MyFrame;
import common.protocol.GameState;


public class UserFrame extends LayerManagement {

    private Graphic graphic;
    private MyFrame frame;
    private MainMenu mainMenu;
    private GameMenu gameMenu;

    private String username;
    private String winNum;
    private String loseNum;

    public UserFrame(Graphic graphic) {
        this.graphic = graphic;
        this.frame = new MyFrame("XO Game", GraphicDefault.UserMenu.mainBounds,false);
        pane = frame.getLayeredPane();
        this.mainMenu = new MainMenu(this);
    }

    public void buildFrame() {
        mainMenu.getMainMenuAction().setClientNetwork();
        frame.setVisible(true);
        this.pane = frame.getLayeredPane();
    }


    public void startMainMenu() {
        showPanel(AllMenuSwitch.MAIN_MENU);
    }

    public void startGame(GameState gameState) {
        deleteLayer(GraphicLayer.GAME_START.getLayer(),
                GraphicLayer.GAME_END.getLayer());
        gameMenu = new GameMenu(this);
        gameMenu.startGame(gameState);
        showPanel(AllMenuSwitch.GAME_MENU);
    }

    void showPanel(AllMenuSwitch allMenuSwitch) {
        switch (allMenuSwitch) {
            case MAIN_MENU:
                offAllLayer();
                mainMenu.drawScoreBoxContent();
                onLayer(GraphicLayer.MAIN_PANEL.getLayer(),
                        GraphicLayer.MAIN_MENU_END_LAYER.getLayer());
                mainMenu.getMainPanel().showAllComponent();
                break;
            case GAME_MENU:
                deleteLayer(GraphicLayer.MESSAGE_PANEL.getLayer(),
                        GraphicLayer.MESSAGE_PANEL.getLayer() + 1);
                offAllLayer();
                onLayer(GraphicLayer.GAME_START.getLayer(),
                        GraphicLayer.GAME_END.getLayer());
                break;
        }
    }

    public MyFrame getFrame() {
        return frame;
    }

    public Graphic getGraphic() {
        return graphic;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public void setUserInfo(String username, String winNum, String loseNum) {
        this.username = username;
        this.winNum = winNum;
        this.loseNum = loseNum;
    }

    public String getUsername() {
        return username;
    }

    public String getWinNum() {
        return winNum;
    }

    public String getLoseNum() {
        return loseNum;
    }
}
