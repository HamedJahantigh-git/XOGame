package client.enums;

public enum ClientPath {
    clientFolder("src/main/resources/Client"),
    graphicFolder(clientFolder.path+"/Graphic Component"),
    fontFolder(clientFolder.path+"/Font"),
    accountMenuBackGround(graphicFolder.path+"/AccountMenu BackGround.jpg"),
    userMenuBackGround(graphicFolder.path+"/UserMenu BackGround.jpg"),
    button1(graphicFolder.path+"/Button1.png"),


    soundFolder(clientFolder.path+"/Sound"),
    mainSound(soundFolder.path+"/Main Sound.wav"),
    crossButtonSound(soundFolder.path+"/Cross Button.wav");

    private String path;

    private ClientPath(String path) {
        this.path = path;
    }

    public String  getPath() {
        return path;
    }
}
