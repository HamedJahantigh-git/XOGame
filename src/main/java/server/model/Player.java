package server.model;

import java.util.Date;

public class Player {
    private String userName;
    private String password;
    private Date registerTime;
    private int ID;
    private String authToken;

    private int gameWining;
    private int gameLosing;

    private int gameMark;

    public Player(String userName, String password, Date registerTime, int ID, String authToken) {
        this.userName = userName;
        this.password = password;
        this.registerTime = registerTime;
        this.ID = ID;
        this.authToken = authToken;
        this.gameWining = 0;
        this.gameWining = 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getGameMark() {
        return gameMark;
    }

    public void setGameMark(int gameMark) {
        this.gameMark = gameMark;
    }

    public int getGameWining() {
        return gameWining;
    }

    public int getGameLosing() {
        return gameLosing;
    }

    public void plusGameWining() {
        this.gameWining++;
    }

    public void plusGameLosing() {
        this.gameLosing++;
    }
}
