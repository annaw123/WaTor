package model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Fish extends Creature {

    Fish() {
        super(Color.ORANGE);
    }

    @Override
    public void act(Grid grid, int x, int y) {
        // 1. At each jump, a fish moves randomly to an adjacent opposite square. If there are no free squares,
        // no movement takes place.
        // 2. When a fish survives a certain number of jumps, it can reproduce by moving and leaving a new fish
        // in its old position
        
        // Check reproduction BEFORE moving (baby stays at old position)
        boolean shouldReproduce = (super.getChrononsSurvived() > 0 &&
                (super.getChrononsSurvived() % super.getChrononsToReproduce()) == 0);
        
        if (move(grid, x, y)) {
            // If moved successfully and ready to reproduce, create baby at old spot
            if (shouldReproduce) {
                reproduce(grid, x, y);
            }
        }
        incrementChrononsSurvived();
    }

    @Override
    public boolean move(Grid grid, int x, int y) {
        ArrayList<Cell> neighbours = grid.getNeighbours(x, y);
        ArrayList<Cell> availableCells = new ArrayList<>();
        for (Cell cell : neighbours) {
            if (cell.isEmpty()) {
                availableCells.add(cell);
            }
        }

        if (!availableCells.isEmpty()) {
            Random rand = new Random();
            Cell newCell = availableCells.get(rand.nextInt(availableCells.size()));
            int newX = newCell.getRow();
            int newY = newCell.getCol();
            
            // Move to new cell
            grid.getCells()[newX][newY].setCreature(this);
            grid.getCells()[x][y].setCreature(null);
            setMoved(true);
            return true;
        }
        return false;
    }

    @Override
    public void eat() {}

    @Override
    public void reproduce(Grid grid, int x, int y) {
        Fish baby = new Fish();
        baby.setMoved(true); // Baby already "acted" this turn
        grid.getCells()[x][y].setCreature(baby);
    }
}
