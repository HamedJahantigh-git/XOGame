package common.protocol;

import java.io.Serializable;

public class GameState implements Serializable {
    private String playerTurnUsername;
    private String opponentUsername;

    private String board;
    private int mineMark;
    private int opponentMark;

    public GameState(String playerTurnUsername, String opponentUsername,
                     int[][] board, int mineMark) {
        this.playerTurnUsername = playerTurnUsername;
        this.opponentUsername = opponentUsername;
        this.mineMark = mineMark;
        this.opponentMark = ((mineMark)%2)+1;
        this.board = setStringBoard(board);
    }

    private String setStringBoard(int[][] board) {
        String result = "";
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <7 ; j++) {
                result = result.concat(String.valueOf(board[i][j]));
            }
        }
        return result;
    }

    public String getPlayerTurnUsername() {
        return playerTurnUsername;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    public int[][] getBoard() {
        int [][]result = new int[7][7];
        for (int i = 0; i <49 ; i++) {
            result[i/7][i%7] = Integer.parseInt(board.substring(i,i+1));
        }
        return result;
    }

    public int getMineMark() {
        return mineMark;
    }

    public int getOpponentMark() {
        return opponentMark;
    }
}
