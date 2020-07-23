package common.protocol;

import server.controller.GameController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NetworkProtocol implements Serializable {

    private String authToken;
    private ProtocolType protocolType;
    private Map<ParameterTyp, String> parameter;
    private GameState gameState;

    public NetworkProtocol(String authToken, ProtocolType protocolType) {
        this.protocolType = protocolType;
        this.parameter = new HashMap<>();
        this.authToken = authToken;
        this.gameState = null;
    }

    public NetworkProtocol(ProtocolType protocolType) {
        this(null, protocolType);
    }

    public void signInUp(String username, String password) {
        parameter.put(ParameterTyp.USERNAME, username);
        parameter.put(ParameterTyp.PASSWORD, password);
    }

    public void signInSuccess(String authToken, String userName,
                              int winNum, int loseNum) {
        parameter.put(ParameterTyp.AUTH_TOKEN, authToken);
        userInfo(userName, winNum, loseNum);
    }

    public void userInfo(String userName, int winNum, int loseNum) {
        parameter.put(ParameterTyp.USERNAME, userName);
        parameter.put(ParameterTyp.WIN_NUM, String.valueOf(winNum));
        parameter.put(ParameterTyp.LOSE_NUM, String.valueOf(loseNum));
    }


    public void error(ErrorExceptionEnum errorExceptionEnum) {
        parameter.put(ParameterTyp.ERROR, errorExceptionEnum.name());
    }

    public void selectBlock(int i, int j) {
        parameter.put(ParameterTyp.I_ROW, String.valueOf(i));
        parameter.put(ParameterTyp.J_COLUMN, String.valueOf(j));
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public Map<ParameterTyp, String> getParameter() {
        return parameter;
    }

    public String getAuthToken() {
        return authToken;
    }


}
