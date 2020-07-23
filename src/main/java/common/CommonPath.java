package common;

public enum CommonPath {
    configFolder("src/main/resources/Configuration"),
    configFile(configFolder.getPath()+"/Game Config.txt");

    private String path;

    private CommonPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
