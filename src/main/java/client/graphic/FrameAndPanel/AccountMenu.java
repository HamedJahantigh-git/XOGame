package client.graphic.FrameAndPanel;

import client.action.AccountAction;
import client.enums.ComponentEnum;
import client.enums.ClientPath;
import client.enums.FontName;
import client.enums.GraphicLayer;
import client.graphic.GraphicDefault;
import client.graphic.myComponent.*;

import javax.swing.*;
import java.awt.*;

public class AccountMenu {
    private MyFrame accountFrame;
    private MyPanel mainPanel;
    private JLayeredPane pane;

    private MyTextField tfUsername;
    private MyPasswordField pfPassword;

    private AccountAction accountAction;

    public AccountMenu() {
        this.accountAction = new AccountAction();

//        messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
//                GraphicsDefault.AccountMenu.messageBounds, pane, false, 2);
    }

    public void start() {
        buildBase();
        buildBoxes();
        buildButtons();
        mainPanel.setVisible(true);
        mainPanel.repaint();
    }

    private void buildBase() {
        accountFrame = new MyFrame("Account Menu", GraphicDefault.AccountMenu.mainBounds);
        pane = accountFrame.getLayeredPane();
        pane.setLayout(null);
        mainPanel = new MyPanel(pane, GraphicLayer.mainPanel, ClientPath.accountMenuBackGround.getPath(),
                GraphicDefault.AccountMenu.mainBounds, false);
        new MyLabel(mainPanel, "XO Game", FontName.accountTitle.getName(),
                35, Color.WHITE, GraphicDefault.AccountMenu.component(ComponentEnum.title));

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
                GraphicDefault.AccountMenu.component(ComponentEnum.exitButton), Color.WHITE, 18);
        MyButton signInButton = new MyButton(mainPanel, "Sign In", ClientPath.button1,
                GraphicDefault.AccountMenu.component(ComponentEnum.signInButton), Color.WHITE, 18);
        MyButton signUpButton = new MyButton(mainPanel, "Sign Up", ClientPath.button1,
                GraphicDefault.AccountMenu.component(ComponentEnum.signUpButton), Color.WHITE, 18);
        // accountMenuAction.exitGame(exitGameButton);
        //accountMenuAction.signIn(accountFrame, messagePanel, mainPanel, signInButton, tfUsername, pfPassword,accountSound);
        //accountMenuAction.signUp(accountFrame, messagePanel, mainPanel, signUpButton, tfUsername, pfPassword,accountSound);
    }
}
