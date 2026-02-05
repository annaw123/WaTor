package model;
import java.awt.Color;
import java.util.List;
import java.util.Random;

public abstract class Creature {
    private final Color colour;
    private boolean isAlive;
    private int chrononsSurvived;
    private final int chrononsToReproduce;

    Creature(Color colour, int chrononsToReproduce) {
        this.colour = colour;
        this.chrononsSurvived = 0;
        this.chrononsToReproduce = chrononsToReproduce;
        this.isAlive = true;
    }

    // SETTERS & GETTERS
    public Color getColour() {
        return colour;
    }

    public boolean getAlive() { return isAlive; }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    // HELPER METHODS
    protected void incrementChrononsSurvived() {
        this.chrononsSurvived++;
    }

    protected Cell chooseEmpty(List<Cell> neighbours, Cell exclude) {
        List<Cell> emptyNeighbours = neighbours.stream()
                .filter(Cell::isEmpty)
                .filter(c -> c != exclude)
                .toList();
        return emptyNeighbours.isEmpty() ? null : emptyNeighbours.get(new Random().nextInt(emptyNeighbours.size()));
    }

    protected boolean canReproduce() {
        return chrononsSurvived > 0 &&
                chrononsSurvived % chrononsToReproduce == 0;
    }


    // CREATURE ACTIONS
    public abstract Action act(List<Cell> neighbours, Cell currentCell);
    protected abstract Cell move(List<Cell> neighbours, Cell currentCell);
    protected abstract void eat();
    protected abstract Creature reproduce();
    protected Action die(Cell currentCell) {
        return new Action(
                this,
                currentCell,
                currentCell,
                true,
                null, null
        );
    }
}
