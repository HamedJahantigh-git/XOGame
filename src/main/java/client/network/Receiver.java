package client.network;

import client.action.AccountAction;
import client.action.GameAction;
import client.action.MainMenuAction;
import client.enums.MessageEnum;
import client.graphic.Graphic;
import common.protocol.ErrorExceptionEnum;
import common.protocol.NetworkProtocol;
import common.protocol.ParameterTyp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver {

    private ObjectInputStream inputStream;
    private NetworkProtocol networkProtocol;

    private Graphic graphic;
    private AccountAction accountAction;
    private MainMenuAction mainMenuAction;
    private GameAction gameAction;

    private int cnt = 0;

    private AccountHandler accountHandler;
    private GameHandler gameHandler;

    public Receiver(Graphic graphic, Socket socket) throws IOException {
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.accountHandler = new AccountHandler();
        this.gameHandler = new GameHandler();
        this.graphic = graphic;
    }

    private void setActions(){
        this.accountAction = this.graphic.getAccountFrame().getAccountAction();
        this.mainMenuAction = this.graphic.getUserFrame().getMainMenu().getMainMenuAction();
    }

    public void start(){
        setActions();
        while (true) {
            try {
                    networkProtocol = (NetworkProtocol) inputStream.readObject();
                    handleReceiveProtocol();
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }
    }

    private void handleReceiveProtocol() {
        switch (networkProtocol.getProtocolType()) {
            case SIGN_IN_SUCCESS:
                accountHandler.signInSuccessHandler();
                break;
            case ERROR:
                handleErrorProtocol();
                break;
            case START_GAME:
                gameHandler.startGame();
                break;
            case GAME_STATE:
                gameHandler.newGameState();
                break;
            case GAME_EQUALITY:
                gameHandler.gameEquality();
                break;
            case GAME_LOSE:
                gameHandler.gameLose();
                break;
            case GAME_WIN:
                gameHandler.gameWin();
                break;

        }
    }

    private void handleErrorProtocol() {
        ErrorExceptionEnum parameter = ErrorExceptionEnum.valueOf(
                networkProtocol.getParameter().get(ParameterTyp.ERROR));
        switch (parameter) {
            case EMPTY_FIlD:
                accountAction.errorPanel(MessageEnum.EMPTY_IMPORT);
                break;
            case EXIST_USERNAME:
                accountAction.errorPanel(MessageEnum.REPEATED_USERNAME);
                break;
            case WRONG_PASSWORD:
                accountAction.errorPanel(MessageEnum.WRONG_PASSWORD);
                break;
            case NOT_VALID_USERNAME:
                accountAction.errorPanel(MessageEnum.USER_NO_EXIST);
                break;
        }
    }

    class AccountHandler {

        private void signInSuccessHandler() {
            graphic.getClientNetwork().setAuthToken(
                    networkProtocol.getParameter().get(ParameterTyp.AUTH_TOKEN));
            String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
            String winNum = networkProtocol.getParameter().get(ParameterTyp.WIN_NUM);
            String loseNum = networkProtocol.getParameter().get(ParameterTyp.LOSE_NUM);
            accountAction.signInSuccess(username, winNum, loseNum);
        }
    }

    class GameHandler{

        public void startGame() {
            mainMenuAction.startGame(networkProtocol.getGameState());
            gameAction = graphic.getUserFrame().getGameMenu().getGameAction();
        }

        public void newGameState() {
            gameAction.newGameState(networkProtocol.getGameState());
        }


        public void gameEquality() {
            String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
            String winNum = networkProtocol.getParameter().get(ParameterTyp.WIN_NUM);
            String loseNum = networkProtocol.getParameter().get(ParameterTyp.LOSE_NUM);
            gameAction.gameFinished(MessageEnum.EQUALITY,username,winNum,loseNum);
        }

        public void gameWin() {
            String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
            String winNum = networkProtocol.getParameter().get(ParameterTyp.WIN_NUM);
            String loseNum = networkProtocol.getParameter().get(ParameterTyp.LOSE_NUM);
            gameAction.gameFinished(MessageEnum.WINING,username,winNum,loseNum);
        }

        public void gameLose() {
            String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
            String winNum = networkProtocol.getParameter().get(ParameterTyp.WIN_NUM);
            String loseNum = networkProtocol.getParameter().get(ParameterTyp.LOSE_NUM);
            gameAction.gameFinished(MessageEnum.LOSING, username,winNum,loseNum);
        }
    }
}
