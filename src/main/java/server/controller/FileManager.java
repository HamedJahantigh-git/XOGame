package server.controller;

import com.google.gson.Gson;
import server.enums.ServerPath;
import server.model.Player;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private File file;
    private Gson gson;

    public FileManager() {
    }

    public void savePlayerToFile(Player player) {
        gson = new Gson();
        String path = ServerPath.PLAYER_DATA.getPath() + "/" + player.getUserName();
        try {
            Writer writer = new FileWriter(
                    path + ".txt");
            gson.toJson(player, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player creatPlayerFromFile(String name) {
        Player player = null;
        gson = new Gson();
        try (Reader reader = new FileReader(
                ServerPath.PLAYER_DATA.getPath() + "/" + name + ".txt")) {
            player = gson.fromJson(reader, Player.class);
        } catch (Exception ignored) {
        }
        return player;
    }

    public ArrayList<String> allFileNameInPath(String path) {
        ArrayList<String> result = new ArrayList<>();
        file = new File(path);
        String[] name = file.list();
        for (int i = 0; i < name.length; i++)
            result.add(name[i]);
        return result;
    }

}
