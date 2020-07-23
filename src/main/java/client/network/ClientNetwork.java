package client.network;

import client.graphic.Graphic;
import common.ConfigLoader;
import common.MyThread;

import java.io.IOException;
import java.net.Socket;

public class ClientNetwork extends MyThread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private String authToken;

    public ClientNetwork(Graphic graphic, ConfigLoader configLoader) {
        try {
            this.authToken = null;
            this.socket = new Socket(configLoader.readIP(),
                    configLoader.readPortNumber());
            this.sender = new Sender(this, socket);
            this.receiver = new Receiver(graphic, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    @Override
    public void run() {
        receiver.start();
    }

    public Sender getSender() {
        return sender;
    }
}
