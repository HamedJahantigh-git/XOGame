package client;

import client.graphic.FrameAndPanel.AccountMenu;
import client.graphic.Graphic;

public class ClientMain {

    public static void main(String[] args) {
        new ClientMain().startNewClient();
    }

    public void startNewClient(){
        Initialize.getInstance().InitializeClient();
        new Graphic().start();
    }
}
