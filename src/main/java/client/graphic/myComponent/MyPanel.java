package client.graphic.myComponent;

import client.enums.GraphicLayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel {

    private String imagePath;

    public MyPanel(JLayeredPane pane, GraphicLayer layer, String imagePath, Bounds bounds, boolean visible) {
        this.imagePath = imagePath;
        setVisible(visible);
        setLayout(null);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        if(pane!=null) {
            pane.add(this, Integer.valueOf(layer.getLayer()));
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = null;
        if (this.imagePath != null) {
            try {
                image = ImageIO.read(new File(this.imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            setOpaque(false);
            super.paintComponent(g);
            setOpaque(true);
        }
    }

}
