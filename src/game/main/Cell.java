package game.main;

public class Cell {
    private final int x;
    private final int y;
    private int value;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value=value;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}' +
                ", value: "+this.value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue(){
        return this.value;
    }
}
