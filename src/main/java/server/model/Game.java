package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    private Map<String, Player> players;
    private String playerIndex;
    private Board board;

    public Game(ArrayList<Player> players) {
        setPlayersMark(players);
        this.players = new HashMap<>();
        this.players.put(players.get(0).getAuthToken(), players.get(0));
        this.players.put(players.get(1).getAuthToken(), players.get(1));
        this.board = new Board();
        setRandomPlayerIndex();
    }

    private void setPlayersMark(ArrayList<Player> players) {
        players.get(0).setGameMark(1);
        players.get(1).setGameMark(2);
    }

    private void setRandomPlayerIndex() {
        Random random = new Random();
        int counter = 0;
        for (String key : players.keySet()) {
            if (counter == random.nextInt(2)) {
                playerIndex = key;
                break;
            }
            counter++;
        }
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public String getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(String playerIndex) {
        this.playerIndex = playerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public Player getMinePlayer(String mineAuthToken) {
        return players.get(mineAuthToken);
    }

    public Player getOpponentPlayer(String mineAuthToken) {
        for (String key : players.keySet()) {
            if (!key.equals(mineAuthToken)) {
                return players.get(key);
            }
        }
        return null;
    }

}
