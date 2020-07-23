package client.enums;

public enum ClientPath {
    clientFolder("src/main/resources/Client"),
    graphicFolder(clientFolder.path+"/Graphic Component"),
    fontFolder(clientFolder.path+"/Font"),
    accountMenuBackGround(graphicFolder.path+"/AccountMenu BackGround.jpg"),
    USER_MENU_BACKGROUND(graphicFolder.path+"/UserMenu BackGround.jpg"),
    GAME_MENU_BACKGROUND(graphicFolder.path+"/UserMenu BackGround.jpg"),
    GAME_TEXT_BACKGROUND(graphicFolder.path+"/Game Panel Text.png"),
    GAME_BOARD_BACKGROUND(graphicFolder.path+"/Board Background.jpg"),
    button1(graphicFolder.path+"/Button1.png"),
    MESSAGE_1(graphicFolder.path+"/Message1.png"),
    SCORE_PANEL(graphicFolder.path+"/Score Panel.png"),
    USER_PANEL(graphicFolder.path+"/User Panel.png"),
    WAITING_GIF(graphicFolder.path+"/Waiting.gif"),
    WAITING_IMAGE(graphicFolder.path+"/Waiting Image.png"),

    X_BLOCK(graphicFolder.path+"/X Block.jpg"),
    O_BLOCK(graphicFolder.path+"/O Block.jpg"),
    EMPTY_BLOCK(graphicFolder.path+"/Empty Block.jpg"),

    soundFolder(clientFolder.path+"/Sound"),
    ACCOUNT_SOUND(soundFolder.path+"/Main Sound.wav"),
    MAIN_SOUND(soundFolder.path+"/Main Sound.wav"),
    crossButtonSound(soundFolder.path+"/Cross Button.wav");

    private String path;

    private ClientPath(String path) {
        this.path = path;
    }

    public String  getPath() {
        return path;
    }
}
