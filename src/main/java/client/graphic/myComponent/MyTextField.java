package client.graphic.myComponent;

import client.enums.FontName;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {


    private String fontName = FontName.textField.getName();

    public MyTextField (JPanel panel, int fontSize, Color color, Bounds bounds){
        setFont(new Font(fontName, Font.ITALIC, fontSize));
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        setForeground(color);
        panel.add(this);
    }
}

