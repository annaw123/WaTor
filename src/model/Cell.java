package model;

public class Cell {
    private final int row;
    private final int col;
    private Creature creature;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.creature = null;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public boolean isEmpty() {
        return creature == null;
    }
}
