package client.graphic.myComponent;

import client.enums.FontName;

import javax.swing.*;
import java.awt.*;

public class MyPasswordField extends JPasswordField  {

    private String fontName = FontName.passwordField.getName();

    public  MyPasswordField(JPanel panel, Bounds bounds, int fontSize, Color color) {
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
       setForeground(color);
        setFont(new Font(fontName, Font.ITALIC, fontSize));
        panel.add(this);
    }
}
