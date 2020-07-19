package client.enums;

public enum GraphicLayer {
    mainPanel(1);

    private int layer;

    private GraphicLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
