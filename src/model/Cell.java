package model;

public class Cell {
    // ATTRIBUTES
    private final int row;
    private final int col;
    private Creature creature;

    // CONSTRUCTOR
    Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.creature = null;
    }

    // GETTERS & SETTERS
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

    // METHODS
    public boolean isEmpty() {
        return creature == null;
    }
}
