package client.action;

import client.enums.ClientPath;
import client.enums.ComponentEnum;
import client.enums.GraphicLayer;
import client.enums.MessageEnum;
import client.graphic.Graphic;
import client.graphic.GraphicDefault;
import client.graphic.myComponent.MessagePanel;
import client.graphic.myComponent.MyButton;
import client.graphic.myComponent.MyPasswordField;
import client.graphic.myComponent.MyTextField;

import java.awt.*;

public class AccountAction extends MyAction {

    public AccountAction(Graphic graphic) {
        super(graphic);
    }

    public void exitGame(MyButton exitGameButton) {
        exitGameButton.addActionListener(actionEvent -> {
            System.exit(0);
        });
    }

    public void signIn(MyButton signInButton, MyTextField tfUsername, MyPasswordField pfPassword) {
        signInButton.addActionListener(actionEvent -> {
            clientNetwork.getSender().getAccountHandler().signIn(tfUsername.getText(),
                    String.valueOf(pfPassword.getPassword()));
        });
    }

    public void signUp(MyButton signUpButton, MyTextField tfUsername, MyPasswordField pfPassword) {
        signUpButton.addActionListener(actionEvent -> {
            clientNetwork.getSender().getAccountHandler().signUp(tfUsername.getText(),
                    String.valueOf(pfPassword.getPassword()));
        });
    }

    public void signInSuccess(String username, String winNum, String loseNum) {
        graphic.getAccountFrame().endAccountMenu();
        graphic.getUserFrame().setUserInfo(username,winNum,loseNum);
        graphic.getUserFrame().buildFrame();
        graphic.getUserFrame().startMainMenu();
    }

    public void errorPanel(MessageEnum message) {
        MessagePanel messagePanel = new MessagePanel(message,graphic.getAccountFrame().getPane(),
                GraphicLayer.MESSAGE_PANEL, GraphicDefault.AccountMenu.messageBound(ComponentEnum.MESSAGE),
                25,false);
        MyButton okButton = new MyButton(messagePanel, "OK", ClientPath.button1,
                GraphicDefault.AccountMenu.messageBound(ComponentEnum.BUTTON), Color.WHITE, 25);
        graphic.getAccountFrame().getMainPanel().offAllComponent();
        graphic.getAccountFrame().onLayer(GraphicLayer.MESSAGE_PANEL.getLayer(),
                GraphicLayer.MESSAGE_PANEL.getLayer()+1);
        okMessageButton(graphic.getAccountFrame().getMainPanel(),graphic.getAccountFrame(),
                okButton);
    }
}
