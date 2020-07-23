package client.graphic.myComponent;

import client.enums.ClientPath;
import client.enums.GraphicLayer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellComponent extends MyPanel {

    private JButton button;

    public CellComponent(MyPanel panel, ClientPath clientPath, Bounds bounds) {
        super(panel, clientPath, bounds);
        buildButton();
    }

    private void buildButton() {
        button = new JButton();
        button.setContentAreaFilled(false);
        button.setBounds(0, 0, this.getWidth(), this.getHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        selectable();
        this.add(button);
    }

    private void selectable() {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBorder(BorderFactory.createRaisedSoftBevelBorder());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBorder(null);
            }

        });
    }

    public JButton getButton() {
        return button;
    }
}
