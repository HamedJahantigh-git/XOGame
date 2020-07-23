package client.graphic.myComponent;

import javax.swing.*;

public class MyFrame extends JFrame {

    private String title;
    private Bounds bounds;

    public MyFrame(String title, Bounds bounds, boolean isVisible) {
        this.title = title;
        this.bounds = bounds;
        initFrame(isVisible);
    }

    private void initFrame(boolean isVisible) {
        setLayout(null);
        setTitle(title);
        setResizable(false);
        setVisible(isVisible);
        setSize(bounds.getWidth(), bounds.getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
