package model;

import java.awt.*;

public class Shark extends Creature {
    private int energy;

    Shark() {
        super(Color.GRAY);
        energy = 30; // COMEBACK: Might have to adjust this!
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public void move() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void die() {

    }
}
