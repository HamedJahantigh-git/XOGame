package client.enums;

public enum GraphicLayer {
    MAIN_PANEL(1),
    SCORE_PANEL(3),
    USER_PANEL(4),
    MESSAGE_PANEL(9),
    MAIN_MENU_END_LAYER(10),
    GAME_START(11),
    GAME_PANEL(12),
    GAME_TEXT_PANEL(13),
    GAME_BOARD_PANEL(14),
    GAME_FINISH_PANEL(19),
    GAME_END(20);

    private int layer;

    private GraphicLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
