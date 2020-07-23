package common;

import client.enums.ClientPath;
import server.enums.ServerPath;

import java.io.File;

public class Initialize {

    private static Initialize instance = null;

    private Initialize() {

    }

    public static Initialize getInstance() {
        if (instance == null)
            instance = new Initialize();
        return instance;
    }

    public void initializeClient() {
        buildClientFolders();
    }

    public void initializeServer() {
        buildServerFolders();
    }

    private void buildClientFolders() {
        new File(CommonPath.configFolder.getPath()).mkdir();
        new File(ClientPath.clientFolder.getPath()).mkdir();
        new File(ClientPath.graphicFolder.getPath()).mkdir();
        new File(ClientPath.fontFolder.getPath()).mkdir();
        new File(ClientPath.soundFolder.getPath()).mkdir();
    }

    private void buildServerFolders() {
        new File(CommonPath.configFolder.getPath()).mkdir();
        new File(ServerPath.SERVER_FOLDER.getPath()).mkdir();
        new File(ServerPath.PLAYER_DATA.getPath()).mkdir();
    }


}
