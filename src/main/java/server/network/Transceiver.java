package server.network;

import common.MyThread;
import common.protocol.*;
import server.controller.GameController;
import server.controller.PlayerController;
import server.model.Player;
import server.model.ServerModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Transceiver extends MyThread {
    private PlayerController playerController;
    private ServerModel serverModel;

    private AccountHandler accountHandler;
    private MainMenuHandler mainMenuHandler;
    private GameHandler gameHandler;

    private ObjectOutputStream mineOutputStream;
    private ObjectInputStream inputStream;
    private NetworkProtocol networkProtocol;

    public Transceiver(ServerModel serverModel,
                       PlayerController playerController) throws IOException {
        this.playerController = playerController;
        this.serverModel = serverModel;

        this.accountHandler = new AccountHandler();
        this.mainMenuHandler = new MainMenuHandler();
        this.gameHandler = new GameHandler();

        this.inputStream = playerController.getInputStream();
        this.mineOutputStream = playerController.getOutputStream();
    }

    @Override
    public void run() {
        receive();
    }

    private void receive() {
        while (true) {
            try {
                networkProtocol = (NetworkProtocol) inputStream.readObject();
                handleReceiveProtocol();
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }
    }

    private void send() {
        try {

            mineOutputStream.writeObject(networkProtocol);
            mineOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToAnother(ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(networkProtocol);
            mineOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleReceiveProtocol() {
        switch (networkProtocol.getProtocolType()) {
            case SIGN_IN:
                accountHandler.signIn();
                break;
            case SIGN_UP:
                accountHandler.signUp();
                break;
            case REQUEST_GAME:
                mainMenuHandler.requestForGame();
                break;
            case SELECT_BLOCK:
                gameHandler.selectBlock();
                break;
            case REQUEST_SAVE_PLAYER:
                mainMenuHandler.savePlayer();
        }
    }

    class AccountHandler {

        private void signUp() {
            try {
                String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
                String password = networkProtocol.getParameter().get(ParameterTyp.PASSWORD);
                playerController.signUpPlayer(username, password);
                serverModel.addOnlinePlayer(playerController.getAuthToken(), playerController);
                networkProtocol = new NetworkProtocol(ProtocolType.SIGN_IN_SUCCESS);
                networkProtocol.signInSuccess(playerController.getAuthToken(),
                        playerController.getPlayer().getUserName(),
                        playerController.getPlayer().getGameWining(),
                        playerController.getPlayer().getGameLosing());
                send();
            } catch (Exception e) {
                if (e.getMessage().equals(ErrorExceptionEnum.EXIST_USERNAME.name())) {
                    networkProtocol = new NetworkProtocol(ProtocolType.ERROR);
                    networkProtocol.error(ErrorExceptionEnum.EXIST_USERNAME);
                    send();
                }
                if (e.getMessage().equals(ErrorExceptionEnum.EMPTY_FIlD.name())) {
                    networkProtocol = new NetworkProtocol(ProtocolType.ERROR);
                    networkProtocol.error(ErrorExceptionEnum.EMPTY_FIlD);
                    send();
                }
            }
        }

        private void signIn() {
            try {
                String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
                String password = networkProtocol.getParameter().get(ParameterTyp.PASSWORD);
                playerController.signInPlayer(username, password);
                serverModel.addOnlinePlayer(playerController.getAuthToken(), playerController);
                networkProtocol = new NetworkProtocol(ProtocolType.SIGN_IN_SUCCESS);
                networkProtocol.signInSuccess(playerController.getAuthToken(),
                        playerController.getPlayer().getUserName(),
                        playerController.getPlayer().getGameWining(),
                        playerController.getPlayer().getGameLosing());
                send();
            } catch (Exception e) {
                if (e.getMessage().equals(ErrorExceptionEnum.WRONG_PASSWORD.name())) {
                    networkProtocol = new NetworkProtocol(ProtocolType.ERROR);
                    networkProtocol.error(ErrorExceptionEnum.WRONG_PASSWORD);
                    send();
                }
                if (e.getMessage().equals(ErrorExceptionEnum.NOT_VALID_USERNAME.name())) {
                    networkProtocol = new NetworkProtocol(ProtocolType.ERROR);
                    networkProtocol.error(ErrorExceptionEnum.NOT_VALID_USERNAME);
                    send();
                }
            }
        }
    }

    class MainMenuHandler {

        private void requestForGame() {
            if (serverModel.getPlayerGameRequestKey().size() > 0) {
                String opponentKey = serverModel.getPlayerGameRequestKey().get(0);
                serverModel.getPlayerGameRequestKey().remove(0);
                PlayerController requestPlayer = serverModel.getOnlinePlayer().get(
                        networkProtocol.getAuthToken());
                PlayerController opponentPlayer = serverModel.getOnlinePlayer().get(opponentKey);
                GameController gameController = new GameController(requestPlayer, opponentPlayer);
                serverModel.getGameControllerMap().put(networkProtocol.getAuthToken(), gameController);
                serverModel.getGameControllerMap().put(opponentPlayer.getAuthToken(), gameController);
                networkProtocol = new NetworkProtocol(requestPlayer.getAuthToken(),
                        ProtocolType.START_GAME);
                networkProtocol.setGameState(gameController.buildGameState(requestPlayer.getAuthToken()));
                send();
                networkProtocol = new NetworkProtocol(opponentKey,
                        ProtocolType.START_GAME);
                networkProtocol.setGameState(gameController.buildGameState(opponentKey));
                sendToAnother(opponentPlayer.getOutputStream());
            } else {
                serverModel.addPlayerGameRequestKey(networkProtocol.getAuthToken());
            }
        }

        public void savePlayer() {
            playerController.savePlayer();
        }
    }

    class GameHandler {

        private void selectBlock() {
            try {
                String mineAuthToken = networkProtocol.getAuthToken();
                GameController gameController = serverModel.getGameControllerMap()
                        .get(mineAuthToken);
                String opponentAuthToken = gameController.getGame().
                        getOpponentPlayer(mineAuthToken).getAuthToken();
                Player minePlayer = gameController.getGame().getMinePlayer(mineAuthToken);
                Player opponentPlayer = gameController.getGame().getOpponentPlayer(mineAuthToken);
                int row = Integer.parseInt(networkProtocol.getParameter().get(ParameterTyp.I_ROW));
                int column = Integer.parseInt(networkProtocol.getParameter().get(ParameterTyp.J_COLUMN));
                String result = gameController.selectBlock(networkProtocol.getAuthToken(), row, column);
                if (result == null) {
                    networkProtocol = new NetworkProtocol(mineAuthToken
                            , ProtocolType.GAME_STATE);
                    networkProtocol.setGameState(gameController.buildGameState(mineAuthToken));
                    send();
                    networkProtocol = new NetworkProtocol(opponentAuthToken
                            , ProtocolType.GAME_STATE);
                    networkProtocol.setGameState(gameController.buildGameState(opponentAuthToken));
                    sendToAnother(serverModel.getOnlinePlayer().get(opponentAuthToken).getOutputStream());
                } else {
                    if (result.equals(mineAuthToken)) {
                        networkProtocol = new NetworkProtocol(mineAuthToken
                                , ProtocolType.GAME_WIN);
                        networkProtocol.userInfo(minePlayer.getUserName(),
                                minePlayer.getGameWining(), minePlayer.getGameLosing());
                        send();
                        networkProtocol = new NetworkProtocol(opponentAuthToken
                                , ProtocolType.GAME_LOSE);
                        networkProtocol.userInfo(opponentPlayer.getUserName(),
                                opponentPlayer.getGameWining(), opponentPlayer.getGameLosing());
                        sendToAnother(serverModel.getOnlinePlayer().get(opponentAuthToken).getOutputStream());
                    }
                    if (result.equals(opponentAuthToken)) {
                        networkProtocol = new NetworkProtocol(mineAuthToken
                                , ProtocolType.GAME_LOSE);
                        networkProtocol.userInfo(minePlayer.getUserName(),
                                minePlayer.getGameWining(), minePlayer.getGameLosing());
                        send();
                        networkProtocol = new NetworkProtocol(opponentAuthToken
                                , ProtocolType.GAME_WIN);
                        networkProtocol.userInfo(opponentPlayer.getUserName(),
                                opponentPlayer.getGameWining(), opponentPlayer.getGameLosing());
                        sendToAnother(serverModel.getOnlinePlayer().get(opponentAuthToken).getOutputStream());
                    }
                    if (result.equals("FULL_BLOCK")) {
                        networkProtocol = new NetworkProtocol(mineAuthToken
                                , ProtocolType.GAME_EQUALITY);
                        networkProtocol.userInfo(minePlayer.getUserName(),
                                minePlayer.getGameWining(), minePlayer.getGameLosing());
                        send();
                        networkProtocol = new NetworkProtocol(mineAuthToken
                                , ProtocolType.GAME_EQUALITY);
                        networkProtocol.userInfo(opponentPlayer.getUserName(),
                                opponentPlayer.getGameWining(), opponentPlayer.getGameLosing());

                        sendToAnother(serverModel.getOnlinePlayer().get(opponentAuthToken).getOutputStream());
                    }
                }
            } catch (Exception e) {
                if (e.getMessage().equals(ErrorExceptionEnum.NOT_TURN.name())) {
                }
                if (e.getMessage().equals(ErrorExceptionEnum.FULL_BLOCK.name())) {

                }
            }
        }
    }

}
