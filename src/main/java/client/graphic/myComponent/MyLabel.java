package client.graphic.myComponent;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {

    public MyLabel (JPanel panel, String text, String fontName, int fontSize, Color color, Bounds bounds){
        super(text,SwingConstants.CENTER);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        setFont(new Font(fontName, Font.ITALIC, fontSize));
        setForeground(color);
        panel.add(this);
    }


}
