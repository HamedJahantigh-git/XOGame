package server.network;

import common.ConfigLoader;
import common.MyThread;
import server.controller.PlayerController;
import server.model.ServerModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerNetwork extends MyThread {

    private ServerSocket serverSocket;
    private ServerModel serverModel;

    public ServerNetwork(ConfigLoader configLoader) {
        try {
            this.serverSocket = new ServerSocket(configLoader.readPortNumber());
            this.serverModel = new ServerModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                PlayerController playerController = new PlayerController(socket);
                new Transceiver(serverModel, playerController).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
