package client.graphic.myComponent;

import javax.swing.*;
import java.awt.*;

public class LayerManagement {
    protected JLayeredPane pane;

    public void offAllLayer() {
        for (Component component : pane.getComponents()) {
            component.setVisible(false);
            component.setEnabled(false);
        }
    }

    public void onLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                component.setVisible(true);
                component.setEnabled(true);
            }
        }
    }

    public void offLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                component.setVisible(false);
                component.setEnabled(false);
            }
        }
    }

    public void deleteLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                pane.remove(component);
            }
        }
    }

    public JLayeredPane getPane() {
        return pane;
    }
}
