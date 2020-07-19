package client.graphic.myComponent;

import client.enums.ClientPath;
import client.enums.FontName;
import client.graphic.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MyButton extends JButton {

    private String fontName = FontName.button.getName();
    private Sound crossSound;

    public MyButton(JPanel panel, String text, ClientPath backgroundsPath,
                    Bounds bounds, Color color, int fontSize) {
        super(text);
        if (backgroundsPath != null)
            setIcon(new ImageIcon(setImage(bounds.getWidth(),
                    bounds.getHeight(), backgroundsPath.getPath())));
        crossSound = new Sound(ClientPath.crossButtonSound);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setFont(new Font(fontName, Font.ITALIC, fontSize));
        setForeground(color);
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        selectable();
        panel.add(this);
    }


    public MyButton(JPanel panel, String text,
                    Bounds bounds, Color color, int fontSize) {
        this(panel, text, null, bounds, color, fontSize);
    }


    private Image setImage(int width, int height, String path) {
        Image image = null;
        try {
            image = ImageIO.read(new File(path))
                    .getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private void selectable() {
        this.addMouseListener(new MouseAdapter() {
            Color oldColor = getForeground();

            public void mouseEntered(MouseEvent me) {
                crossSound.playOne();
                oldColor = getForeground();
                setForeground(Color.red);
            }

            public void mouseExited(MouseEvent me) {
                setForeground(oldColor);
            }
        });
    }

}
