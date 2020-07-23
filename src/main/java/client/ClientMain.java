package client;

import client.enums.ClientPath;
import client.graphic.Graphic;
import common.CommonPath;
import common.Initialize;

public class ClientMain {

    public static void main(String[] args) {
        new ClientMain().startNewClient();
    }

    private void startNewClient(){
        Initialize.getInstance().initializeClient();
        new Graphic(CommonPath.configFile.getPath()).start();
    }
}
