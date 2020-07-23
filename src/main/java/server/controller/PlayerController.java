package server.controller;

import common.protocol.ErrorExceptionEnum;
import server.enums.ServerPath;
import server.model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PlayerController {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Player player;
    private FileManager fileManager;

    public PlayerController(Socket socket) {
        try {
            this.socket = socket;
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.fileManager = new FileManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signInPlayer(String username, String password) throws Exception {
        if (!fileManager.allFileNameInPath(
                ServerPath.PLAYER_DATA.getPath()).contains(username + ".txt"))
            throw new Exception(ErrorExceptionEnum.NOT_VALID_USERNAME.name());
        player = fileManager.creatPlayerFromFile(username);
        player.setAuthToken(buildAuthToken());
        if (!player.getPassword().equals(password)) {
            throw new Exception(ErrorExceptionEnum.WRONG_PASSWORD.name());
        }
    }

    public void signUpPlayer(String username, String password) throws Exception {
        Date registerTime = new Date();
        if (username.equals("") || password.equals(""))
            throw new Exception(ErrorExceptionEnum.EMPTY_FIlD.name());
        if (checkExistUsername(username))
            throw new Exception(ErrorExceptionEnum.EXIST_USERNAME.name());
        this.player = new Player(username, password, registerTime, numberAllPlayerSignIn()
                , buildAuthToken());
        fileManager.savePlayerToFile(player);
    }

    public void savePlayer(){
        fileManager.savePlayerToFile(player);
    }

    private String buildAuthToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return bytes.toString();
    }

    private boolean checkExistUsername(String username) {
        boolean answer = false;
        ArrayList<String> result;
        result = fileManager.allFileNameInPath(ServerPath.PLAYER_DATA.getPath());
        String compare;
        for (int i = 0; i < result.size(); i++) {
            compare = result.get(i);
            if (compare.equalsIgnoreCase(username + ".txt"))
                answer = true;
        }
        return answer;
    }

    public int numberAllPlayerSignIn() {
        int result;
        ArrayList<String> name;
        name = fileManager.allFileNameInPath(ServerPath.PLAYER_DATA.getPath());
        return name.size() + 1;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public Player getPlayer() {
        return player;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getAuthToken() {
        return player.getAuthToken();
    }


}
