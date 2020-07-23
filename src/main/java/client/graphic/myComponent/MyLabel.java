package client.graphic.myComponent;

import client.enums.ClientPath;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyLabel extends JLabel {

    public MyLabel (JPanel panel, String text, String fontName, int fontSize, Color color, Bounds bounds){
        super(text,SwingConstants.CENTER);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        setFont(new MyFont(fontName,fontSize).getFont());
        setForeground(color);
        panel.add(this);
    }

}
