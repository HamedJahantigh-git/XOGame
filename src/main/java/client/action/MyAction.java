package client.action;

import client.enums.GraphicLayer;
import client.graphic.Graphic;
import client.graphic.myComponent.LayerManagement;
import client.graphic.myComponent.MyButton;
import client.graphic.myComponent.MyPanel;
import client.network.ClientNetwork;

import javax.swing.*;

public class MyAction {
    protected Graphic graphic;
    protected ClientNetwork clientNetwork;

    protected MyAction(Graphic graphic) {
        this.graphic = graphic;
    }

    public void setClientNetwork() {
        this.clientNetwork = graphic.getClientNetwork();
    }

    protected void okMessageButton(MyPanel backPanel, LayerManagement layer, MyButton button) {
        button.addActionListener(actionEvent -> {
            backPanel.showAllComponent();
            layer.deleteLayer(GraphicLayer.MESSAGE_PANEL.getLayer(),
                    GraphicLayer.MESSAGE_PANEL.getLayer()+1);
        });
    }
}
