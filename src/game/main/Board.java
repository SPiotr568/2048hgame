package game.main;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] board;
    private final int score;

    public Board(int[][] board, int score) {
        this.board = new int[4][4];
        this.score = 0;

        for(int i=0; i <4; i++){
            for(int j=0; j <4; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public int getCell(Cell c){
        return this.board[c.getX()][c.getY()];
    }

    public boolean isEmpty(Cell c){
        return getCell(c) == 0;
    }

    public List<Cell> emptyCells() {
        List<Cell> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Cell cell = new Cell(i, j);
                if (isEmpty(cell)) {
                    result.add(cell);
                }
            }
        }
        return result;
    }
}
