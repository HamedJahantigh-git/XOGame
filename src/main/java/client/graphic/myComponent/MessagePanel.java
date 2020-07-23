package client.graphic.myComponent;

import client.enums.ClientPath;
import client.enums.FontName;
import client.enums.GraphicLayer;
import client.enums.MessageEnum;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends MyPanel {

    public MessagePanel(MessageEnum messageEnum, JLayeredPane pane, GraphicLayer layer,
                        Bounds bounds, int fontSize, boolean visible) {
        super(pane, layer, ClientPath.MESSAGE_1, bounds, visible);
        new MyLabel(this, messageEnum.getText(), FontName.MESSAGE_TEXT.getName(), fontSize,
                Color.BLACK, new Bounds(0, 0, this.getWidth(), this.getHeight()));
    }
}
