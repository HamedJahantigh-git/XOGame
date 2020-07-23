package server;

import common.CommonPath;
import common.ConfigLoader;
import common.Initialize;
import server.model.Board;
import server.network.ServerNetwork;

import java.security.SecureRandom;
import java.util.Arrays;

public class ServerMain {

    public static void main(String[] args) {
        getInstance().startServer();
    }

    private static ServerMain instance = null;
    private ServerNetwork serverNetwork;
    private ConfigLoader configLoader;

    private ServerMain() {
        this.configLoader = new ConfigLoader(CommonPath.configFile.getPath());
        this.serverNetwork = new ServerNetwork(configLoader);
    }

    public static ServerMain getInstance() {
        if (instance == null)
            instance = new ServerMain();
        return instance;
    }

    private void startServer() {
        Initialize.getInstance().initializeServer();
        serverNetwork.start();
    }
}
