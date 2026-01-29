package model;
import java.awt.Color;

public abstract class Creature {
    private Color colour;

    Creature(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public abstract void move();
    public abstract void eat();
    public abstract void reproduce();
    public abstract void die();
}
