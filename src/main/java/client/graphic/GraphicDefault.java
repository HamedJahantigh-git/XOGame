package client.graphic;

import client.enums.ComponentEnum;
import client.graphic.myComponent.Bounds;

import java.awt.*;

public class GraphicDefault {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static class AccountMenu {
        public static Bounds mainBounds = new Bounds(0, 0,
                screenSize.width * 3 / 10, screenSize.height * 2 / 3);
//        public static Bounds messageBounds = new Bounds(15, mainBounds.getHeight() / 4,
//                mainBounds.getWidth() - 45, mainBounds.getHeight() / 2);
//        public static Bounds messageButtonBounds = new Bounds((messageBounds.getWidth() - messageBounds.getWidth() / 3) / 2,
//                messageBounds.getHeight() - messageBounds.getWidth() / 4 - 20,
//                messageBounds.getWidth() / 3, messageBounds.getWidth() / 5);


        public static Bounds component(ComponentEnum state) {
            Bounds result = null;
            int numberMenuPart = 6;
            int componentDistance = mainBounds.getHeight() / numberMenuPart;
            int componentHeight = componentDistance * 3 / 4;
            int componentWidth = mainBounds.getWidth() /2;
            switch (state) {
                case title:
                    result = new Bounds(0, mainBounds.getHeight() *1/30,
                            mainBounds.getWidth() ,  mainBounds.getHeight() *1/10);
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
}
