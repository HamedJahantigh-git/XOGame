package server.enums;

public enum ServerPath {
    SERVER_FOLDER("src/main/resources/Server"),
    PLAYER_DATA(SERVER_FOLDER.path+"/Player Data");

    private String path;

    private ServerPath(String path) {
        this.path = path;
    }

    public String  getPath() {
        return path;
    }
}
