package client.graphic;

import client.enums.ComponentEnum;
import client.graphic.myComponent.Bounds;

import java.awt.*;

public class GraphicDefault {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static class AccountMenu {
        public static Bounds mainBounds = new Bounds(0, 0,
                screenSize.width * 3 / 10, screenSize.height * 2 / 3);

        public static Bounds messageBound(ComponentEnum state) {
            Bounds messageBounds = new Bounds(15, mainBounds.getHeight() / 4,
                    mainBounds.getWidth() - 45, mainBounds.getHeight() / 2);
            Bounds result = null;
            switch (state) {
                case MESSAGE:
                    result = messageBounds;
                    break;
                case BUTTON:
                    result = new Bounds((messageBounds.getWidth() - messageBounds.getWidth() / 3) / 2,
                            messageBounds.getHeight() - messageBounds.getWidth() * 2 / 8,
                            messageBounds.getWidth() / 3, messageBounds.getWidth() / 8);
                    break;
            }
            return result;
        }

        public static Bounds component(ComponentEnum state) {
            Bounds result = null;
            int numberMenuPart = 6;
            int componentDistance = mainBounds.getHeight() / numberMenuPart;
            int componentHeight = componentDistance * 3 / 4;
            int componentWidth = mainBounds.getWidth() / 2;
            switch (state) {
                case title:
                    result = new Bounds(0, mainBounds.getHeight() * 1 / 30,
                            mainBounds.getWidth(), mainBounds.getHeight() * 1 / 10);
                    break;
                case userNameBox:
                    result = new Bounds(mainBounds.getWidth() / 2, componentHeight * 3 / 2 + 10,
                            mainBounds.getWidth() / 2 - 30, componentHeight * 2 / 3);
                    break;
                case passwordBox:
                    result = new Bounds(mainBounds.getWidth() / 2, componentHeight * 5 / 2 + 10,
                            mainBounds.getWidth() / 2 - 30, componentHeight * 2 / 3);
                    break;
                case userNameLabel:
                    result = new Bounds(20, componentHeight * 3 / 2 + 10,
                            mainBounds.getWidth() / 2 - 30, componentHeight * 2 / 3);
                    break;
                case passwordLabel:
                    result = new Bounds(20, componentHeight * 5 / 2 + 10,
                            mainBounds.getWidth() / 2 - 30, componentHeight * 2 / 3);
                    break;
                case exitButton:
                    result = new Bounds((mainBounds.getWidth() - componentWidth) / 2 - 5,
                            componentHeight * 10 / 2 + 20, componentWidth, componentHeight);
                    break;
                case signInButton:
                    result = new Bounds(5 + mainBounds.getWidth() / 2, componentHeight * 8 / 2 + 10,
                            componentWidth / 2, componentHeight * 7 / 10);
                    break;
                case signUpButton:
                    result = new Bounds(mainBounds.getWidth() / 4 - 5, componentHeight * 8 / 2 + 10,
                            componentWidth / 2, componentHeight * 7 / 10);
                    break;

            }
            return result;
        }
    }

    public static class UserMenu {
        public static Bounds mainBounds = new Bounds(0, 0, screenSize.width * 7 / 10, screenSize.height * 2 / 3);
    }

    public static class MainMenu {
        public static Bounds mainBounds = UserMenu.mainBounds;
        public static Bounds waitBound = new Bounds(
                (mainBounds.getWidth() - mainBounds.getHeight() / 2) / 2, mainBounds.getHeight() / 4,
                mainBounds.getHeight() / 2, mainBounds.getHeight() / 2);

        public static Bounds buttonBound(ComponentEnum state) {
            Bounds result = null;
            switch (state) {
                case PLAY_BUTTON:
                    result = new Bounds(mainBounds.getWidth() * 13 / 20, mainBounds.getHeight() * 11 / 20,
                            mainBounds.getWidth() * 4 / 20, mainBounds.getHeight() * 5 / 40);
                    break;
                case exitButton:
                    result = new Bounds(mainBounds.getWidth() * 13 / 20, mainBounds.getHeight() * 15 / 20,
                            mainBounds.getWidth() * 4 / 20, mainBounds.getHeight() * 5 / 40);
                    break;
            }
            return result;
        }

        public static Bounds scoreBound(ComponentEnum state) {
            Bounds result = null;
            Bounds mainBound = new Bounds(mainBounds.getWidth() / 20, mainBounds.getHeight() / 20,
                    mainBounds.getWidth() * 8 / 20, mainBounds.getHeight() * 18 / 20);
            switch (state) {
                case MAIN_BOX:
                    result = mainBound;
                    break;
            }
            return result;
        }

        public static Bounds userBound(ComponentEnum state) {
            Bounds result = null;

            Bounds mainBound = new Bounds(mainBounds.getWidth() * 11 / 20, mainBounds.getHeight() * 2 / 100,
                    mainBounds.getWidth() * 8 / 20, mainBounds.getHeight() * 10 / 20);
            int round = mainBound.getHeight() *30/100;
            switch (state) {
                case MAIN_BOX:
                    result = mainBound;
                    break;
                case USERNAME:
                    result = new Bounds(0, round, mainBound.getWidth(),
                            (mainBound.getHeight()-2*round) / 3);
                    break;
                case WIN_NUM:
                    result = new Bounds(0, round+(mainBound.getHeight()-2*round) / 3
                            , mainBound.getWidth(), (mainBound.getHeight()-2*round) / 3);
                    break;
                case LOSE_NUM:
                    result = new Bounds(0, round+(mainBound.getHeight()-2*round) * 2 / 3
                            , mainBound.getWidth(), (mainBound.getHeight()-2*round) / 3);
                    break;
            }
            return result;
        }
    }

    public static class GameMenu {
        public static Bounds mainBounds = UserMenu.mainBounds;

        public static Bounds finishPanelBounds = new Bounds(
                mainBounds.getWidth() / 4, mainBounds.getHeight() / 6,
                mainBounds.getWidth() / 2, mainBounds.getHeight() * 2 / 3);

        public static Bounds boardPanel(ComponentEnum state, int i, int j) {
            int blockSize = mainBounds.getHeight() * 2 / 20;
            Bounds mainBox = new Bounds(
                    mainBounds.getHeight() * 2 / 20, mainBounds.getHeight() * 2 / 20,
                    mainBounds.getHeight() * 16 / 20, mainBounds.getHeight() * 8 / 10);
            Bounds result = null;
            switch (state) {
                case MAIN_BOX:
                    result = mainBox;
                    break;
                case BLOCK:
                    result = new Bounds(blockSize * (j + 1) - blockSize / 2,
                            blockSize * (i + 1) - blockSize / 2,
                            blockSize, blockSize);
                    break;

            }
            return result;
        }

        public static Bounds textPanel(ComponentEnum state) {
            Bounds textPanelBound = new Bounds(
                    mainBounds.getWidth() * 11 / 20, mainBounds.getHeight() * 1 / 10,
                    mainBounds.getWidth() * 8 / 20, mainBounds.getHeight() * 4 / 10);
            Bounds result = null;
            switch (state) {
                case MAIN_BOX:
                    result = textPanelBound;
                    break;
                case MINE_MARK_TEXT:
                    result = new Bounds(0, 0,
                            textPanelBound.getWidth(), textPanelBound.getHeight() / 3);
                    break;
                case OPPONENT_TEXT:
                    result = new Bounds(0, textPanelBound.getHeight() / 3,
                            textPanelBound.getWidth(), textPanelBound.getHeight() / 3);
                    break;
                case TURN_TEXT:
                    result = new Bounds(0, textPanelBound.getHeight() * 2 / 3,
                            textPanelBound.getWidth(), textPanelBound.getHeight() / 3);
                    break;

            }
            return result;
        }

    }
}
