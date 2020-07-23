package server.model;

import server.controller.GameController;
import server.controller.PlayerController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerModel {

    volatile private Map<String, PlayerController> onlinePlayer;
    volatile private ArrayList<String> playerGameRequestKey;
    volatile private Map<String, GameController> gameControllerMap;

    public ServerModel() {
        this.onlinePlayer = new HashMap<>();
        this.playerGameRequestKey = new ArrayList<>();
        this.gameControllerMap = new HashMap<>();
    }

    public void addOnlinePlayer(String authToken, PlayerController playerController) {
        onlinePlayer.put(authToken, playerController);
    }

    public void addPlayerGameRequestKey(String authToken) {
        playerGameRequestKey.add(authToken);
    }

    public ArrayList<String> getPlayerGameRequestKey() {
        return playerGameRequestKey;
    }

    public Map<String, PlayerController> getOnlinePlayer() {
        return onlinePlayer;
    }

    public Map<String, GameController> getGameControllerMap() {
        return gameControllerMap;
    }
}
