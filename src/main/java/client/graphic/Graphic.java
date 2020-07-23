package client.graphic;

import client.enums.ClientPath;
import client.graphic.FrameAndPanel.AccountFrame;
import client.graphic.FrameAndPanel.UserFrame;
import client.network.ClientNetwork;
import common.ConfigLoader;
import common.MyThread;

public class Graphic extends MyThread {
    private ClientNetwork clientNetwork;
    private ConfigLoader configLoader;

    private AccountFrame accountFrame;
    private UserFrame userFrame;
    private Sound mainSound;

    public Graphic(String configPath) {
        this.configLoader = new ConfigLoader(configPath);
        this.accountFrame = new AccountFrame(this);
        this.userFrame = new UserFrame(this);
        this.mainSound = new Sound(ClientPath.ACCOUNT_SOUND);
        this.clientNetwork = new ClientNetwork(this, configLoader);
    }

    @Override
    public void run() {
        this.accountFrame.start();
        this.clientNetwork.start();
        this.mainSound.playLoop();
    }

    public ClientNetwork getClientNetwork() {
        return clientNetwork;
    }

    public AccountFrame getAccountFrame() {
        return accountFrame;
    }

    public UserFrame getUserFrame() {
        return userFrame;
    }
}
