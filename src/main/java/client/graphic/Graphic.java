package client.graphic;

import client.enums.ClientPath;
import client.graphic.FrameAndPanel.AccountMenu;
import common.MyThread;

public class Graphic extends MyThread {
    private AccountMenu accountMenu;

    private Sound mainSound;

    public Graphic() {
        this.accountMenu = new AccountMenu();
        this.mainSound = new Sound(ClientPath.mainSound);
    }

    @Override
    public void run() {
        this.accountMenu.start();
        this.mainSound.playLoop();
    }
}
