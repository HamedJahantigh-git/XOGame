package server.controller;

import common.protocol.ErrorExceptionEnum;
import common.protocol.GameState;
import server.model.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameController {
    private Game game;
    private PlayerController player1;
    private PlayerController player2;

    public GameController(PlayerController player1, PlayerController player2) {
        this.game = new Game(new ArrayList<>(
                Arrays.asList(player1.getPlayer(), player2.getPlayer())));
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game getGame() {
        return game;
    }

    public String selectBlock(String authToken, int row, int column) throws Exception {
        if (!authToken.equals(game.getPlayerIndex()))
            throw new Exception(ErrorExceptionEnum.NOT_TURN.name());
        if (game.getBoard().getCells()[row][column] != 0)
            throw new Exception(ErrorExceptionEnum.FULL_BLOCK.name());
        game.getBoard().fillCell(row, column, game.getMinePlayer(authToken).getGameMark());
        setNextPlayerIndex();
        return checkGameFinished(authToken);
    }

    private String checkGameFinished(String authToken) {
        if (winier() != null) {
            if (winier().equals(authToken)) {
                game.getMinePlayer(authToken).plusGameWining();
                game.getOpponentPlayer(authToken).plusGameLosing();
                return authToken;
            }
            if (winier().equals(game.getOpponentPlayer(authToken).getAuthToken())) {
                game.getMinePlayer(authToken).plusGameLosing();
                game.getOpponentPlayer(authToken).plusGameWining();
                return game.getOpponentPlayer(authToken).getAuthToken();
            }
            if (winier().equals("FULL_BLOCK")) {
                return "FULL_BLOCK";
            }
            savePlayersFile();
        }
        return null;
    }

    private void savePlayersFile() {
        player2.savePlayer();
        player1.savePlayer();
    }

    private String winier() {
        int[][] cells = game.getBoard().getCells();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] != 0 &&
                        cells[i][j] == cells[i + 1][j] &&
                        cells[i][j] == cells[i + 2][j] &&
                        cells[i][j] == cells[i + 3][j]) {
                    return blockDetector(cells[i][j]);
                }
                if (cells[i][j] != 0 &&
                        cells[i][j] == cells[i][j + 1] &&
                        cells[i][j] == cells[i][j + 2] &&
                        cells[i][j] == cells[i][j + 3]) {
                    return blockDetector(cells[i][j]);
                }
                if (cells[i][j] != 0 &&
                        cells[i][j] == cells[i + 1][j + 1] &&
                        cells[i][j] == cells[i + 2][j + 2] &&
                        cells[i][j] == cells[i + 3][j + 3]) {
                    return blockDetector(cells[i][j]);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j < 7; j++) {
                if (cells[i][j] != 0 &&
                        cells[i][j] == cells[i + 1][j - 1] &&
                        cells[i][j] == cells[i + 2][j - 2] &&
                        cells[i][j] == cells[i + 3][j - 3]) {
                    return blockDetector(cells[i][j]);
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (cells[i][j] != 0)
                    counter++;
            }
        }
        if (counter == 49)
            return "FULL_BLOCK";
        return null;
    }


    private String blockDetector(int mark) {
        String p1Key = game.getPlayerIndex();
        if (game.getMinePlayer(p1Key).getGameMark() == mark)
            return p1Key;
        if (game.getOpponentPlayer(p1Key).getGameMark() == mark)
            return getNextPlayerIndex();
        return null;
    }

    public String getNextPlayerIndex() {
        for (String key : game.getPlayers().keySet()) {
            if (!game.getPlayerIndex().equals(key)) {
                return key;
            }
        }
        return null;
    }


    public void setNextPlayerIndex() {
        for (String key : game.getPlayers().keySet()) {
            if (!game.getPlayerIndex().equals(key)) {
                game.setPlayerIndex(key);
                break;
            }
        }
    }

    public GameState buildGameState(String authToken) {
        return new GameState(game.getPlayers().get(game.getPlayerIndex()).getUserName(),
                game.getOpponentPlayer(authToken).getUserName(), game.getBoard().getCells(),
                game.getMinePlayer(authToken).getGameMark());
    }

}
