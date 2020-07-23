package client.network;

import common.protocol.NetworkProtocol;
import common.protocol.ProtocolType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {

    private ClientNetwork clientNetwork;

    private ObjectOutputStream outputStream;
    private NetworkProtocol networkProtocol;

    private AccountHandler accountHandler;
    private MainMenuHandler mainMenuHandler;
    private GameHandler gameHandler;

    public Sender(ClientNetwork clientNetwork, Socket socket) throws IOException {
        this.clientNetwork = clientNetwork;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.accountHandler = new AccountHandler();
        this.mainMenuHandler = new MainMenuHandler();
        this.gameHandler = new GameHandler();
    }

    private void send() {
        try {
            outputStream.writeObject(networkProtocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainMenuHandler getMainMenuHandler() {
        return mainMenuHandler;
    }

    public AccountHandler getAccountHandler() {
        return accountHandler;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public class AccountHandler {

        public void signIn(String username, String password) {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SIGN_IN);
            networkProtocol.signInUp(username, password);
            send();
        }

        public void signUp(String username, String password) {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SIGN_UP);
            networkProtocol.signInUp(username, password);
            send();
        }
    }

    public class MainMenuHandler {
        public void requestForPlay() {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.REQUEST_GAME);
            send();
        }

        public void savePlayer() {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.REQUEST_SAVE_PLAYER);
            send();
        }
    }

    public class GameHandler {

        public void selectBlock(int i, int j) {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(),
                    ProtocolType.SELECT_BLOCK);
            networkProtocol.selectBlock(i, j);
            send();
        }
    }


}
