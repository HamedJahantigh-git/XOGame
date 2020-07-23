package client.graphic.myComponent;

import client.enums.ClientPath;
import client.enums.GraphicLayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel {

    private String imagePath;

    public MyPanel(JLayeredPane pane, GraphicLayer layer, ClientPath clientPath, Bounds bounds, boolean visible) {
        this.imagePath = clientPath.getPath();
        setVisible(visible);
        setLayout(null);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        if(pane!=null) {
            pane.add(this, Integer.valueOf(layer.getLayer()));
        }

    }

    public MyPanel(MyPanel panel, ClientPath clientPath, Bounds bounds) {
        this.imagePath = clientPath.getPath();
        setVisible(true);
        setLayout(null);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        if(panel!=null) {
            panel.add(this);
        }

    }

    public void clearAllComponent(){
        for (Component component : this.getComponents()) {
            this.remove(component);
        }
    }

    public void offAllComponent(){
        for (Component component : this.getComponents()) {
            component.setVisible(false);
            component.setEnabled(false);
        }
    }

    public void showAllComponent(){
        this.setVisible(true);
        this.setEnabled(true);
        for (Component component : this.getComponents()) {
            component.setVisible(true);
            component.setEnabled(true);
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
