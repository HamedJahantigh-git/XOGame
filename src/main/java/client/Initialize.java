package client;

import client.enums.ClientPath;

import java.io.File;

public class Initialize {

    private static Initialize instance = null;

    private Initialize() {

    }

    static Initialize getInstance() {
        if(instance == null)
            instance = new Initialize();
        return instance;
    }

    void InitializeClient(){
        buildFolders();
    }

    private void buildFolders() {
        new File(ClientPath.clientFolder.getPath()).mkdir();
        new File(ClientPath.graphicFolder.getPath()).mkdir();
        new File(ClientPath.fontFolder.getPath()).mkdir();
        new File(ClientPath.soundFolder.getPath()).mkdir();
    }


}
