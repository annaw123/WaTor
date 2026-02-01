package model;
import java.awt.Color;

public abstract class Creature {
    private Color colour;
    private boolean hasMoved;
    private boolean isAlive;
    private int chrononsSurvived;
    private final int chrononsToReproduce;

    Creature(Color colour) {
        this.colour = colour;
        this.chrononsSurvived = 0;
        this.chrononsToReproduce = 5; // Fish breed every 5 turns (overridden in Shark)
        this.isAlive = true; // Creatures start alive!
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getChrononsSurvived() {
        return chrononsSurvived;
    }

    public void setChrononsSurvived(int chrononsSurvived) {
        this.chrononsSurvived = chrononsSurvived;
    }

    public void incrementChrononsSurvived() {
        this.chrononsSurvived++;
    }

    public int getChrononsToReproduce() {
        return chrononsToReproduce;
    }

    public abstract void act(Grid grid, int x, int y);
    public abstract boolean move(Grid grid, int x, int y);
    public abstract void eat();
    public abstract void reproduce(Grid grid, int x, int y);
    public void die(Grid grid, int x, int y) {
        isAlive = false;
        grid.getCells()[x][y].setCreature(null);
    };
}
