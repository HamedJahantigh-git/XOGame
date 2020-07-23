package server.model;

public class Board {
    private int[][] cells;

    public Board() {
        this.cells = new int[7][7];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <7 ; j++) {
                cells[i][j] = 0;
            }
        }
    }

    public int[][] getCells() {
        return cells;
    }

    public void fillCell(int row, int column, int mark){
        cells[row][column] = mark;
    }
}
