package client.graphic.FrameAndPanel;

import client.action.AccountAction;
import client.enums.ComponentEnum;
import client.enums.ClientPath;
import client.enums.FontName;
import client.enums.GraphicLayer;
import client.graphic.Graphic;
import client.graphic.GraphicDefault;
import client.graphic.myComponent.*;

import javax.swing.*;
import java.awt.*;

public class AccountFrame extends LayerManagement {
    private Graphic graphic;

    private MyFrame frame;
    private MyPanel mainPanel;

    private MyTextField tfUsername;
    private MyPasswordField pfPassword;

    private AccountAction accountAction;

    public AccountFrame(Graphic graphic) {
        this.graphic = graphic;
        this.accountAction = new AccountAction(graphic);
    }

    public void start() {
        accountAction.setClientNetwork();
        buildBase();
        buildBoxes();
        buildButtons();
        mainPanel.setVisible(true);
        mainPanel.repaint();
    }

    private void buildBase() {
        frame = new MyFrame("Account Menu", GraphicDefault.AccountMenu.mainBounds,true);
        pane = frame.getLayeredPane();
        pane.setLayout(null);
        mainPanel = new MyPanel(pane, GraphicLayer.MAIN_PANEL, ClientPath.accountMenuBackGround,
                GraphicDefault.AccountMenu.mainBounds, false);
        new MyLabel(mainPanel, "XO Game", FontName.accountTitle.getName(),
                55, Color.WHITE, GraphicDefault.AccountMenu.component(ComponentEnum.title));

    }

    private void buildBoxes() {
        new MyLabel(mainPanel, "Enter Your UserName:", FontName.accountText.getName(),
                20, Color.WHITE, GraphicDefault.AccountMenu.component(ComponentEnum.userNameLabel));
        new MyLabel(mainPanel, "Enter Your Password:", FontName.accountText.getName(),
                21, Color.WHITE, GraphicDefault.AccountMenu.component(ComponentEnum.passwordLabel));
        tfUsername = new MyTextField(mainPanel, 30, new Color(0, 136, 204),
                GraphicDefault.AccountMenu.component(ComponentEnum.userNameBox));
        pfPassword = new MyPasswordField(mainPanel,
                GraphicDefault.AccountMenu.component(ComponentEnum.passwordBox), 20, new Color(0, 136, 204));
    }

    private void buildButtons() {
        MyButton exitGameButton = new MyButton(mainPanel, "Exit Game", ClientPath.button1,
                GraphicDefault.AccountMenu.component(ComponentEnum.exitButton), Color.WHITE, 30);
        MyButton signInButton = new MyButton(mainPanel, "Sign In", ClientPath.button1,
                GraphicDefault.AccountMenu.component(ComponentEnum.signInButton), Color.WHITE, 25);
        MyButton signUpButton = new MyButton(mainPanel, "Sign Up", ClientPath.button1,
                GraphicDefault.AccountMenu.component(ComponentEnum.signUpButton), Color.WHITE, 25);
        accountAction.exitGame(exitGameButton);
        accountAction.signIn(signInButton, tfUsername, pfPassword);
        accountAction.signUp(signUpButton, tfUsername, pfPassword);
    }

    public void endAccountMenu(){

        frame.dispose();
    }

    public MyPanel getMainPanel() {
        return mainPanel;
    }

    public AccountAction getAccountAction() {
        return accountAction;
    }

}
