package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Shark extends Creature {
    private int energy;
    private final int chrononsToReproduce = 10; // Sharks breed slower than fish

    Shark() {
        super(Color.GRAY);
        energy = 5; // Lower starting energy for balance
    }

    public int getEnergy() {
        return energy;
    }

    public int getChrononsToReproduce() {
        return chrononsToReproduce;
    }

    @Override
    public void act(Grid grid, int x, int y) {
        // 1. At each jump, a shark moves to an adjacent square occupied by a fish, or a random adjacent square
        //    if there are no fish. If there are no free squares, no movement takes place. - DONE
        // 2. At each jump, a shark loses a unit of energy - DONE
        // 3. Upon reaching zero energy, the shark dies - DONE
        // 4. If a shark moves to a square occupied by a fish, it eats the fish and earns a certain amount of
        //    energy. - DONE
        // 5. Once a shark has survived a certain number of chronons it may reproduce in exactly the same way
        //    as the fish. - DONE
        energy--;
        if (energy <= 0) {
            die(grid, x, y);
            return;
        }

        // Check reproduction BEFORE moving (baby stays at old position)
        boolean shouldReproduce = (super.getChrononsSurvived() > 0 &&
                (super.getChrononsSurvived() % this.getChrononsToReproduce()) == 0);
        
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
        ArrayList<Cell> fishNeighbours = new ArrayList<>();
        ArrayList<Cell> emptyNeighbours = new ArrayList<>();
        for (Cell cell : neighbours) {
            if (cell.getCreature() instanceof Fish) {
                fishNeighbours.add(cell);
            } else if (cell.isEmpty()) {
                emptyNeighbours.add(cell);
            }
        }

        Cell target = null;

        // STRATEGY 1: HUNT
        if (!fishNeighbours.isEmpty()) {
            Random rand = new Random();
            target = fishNeighbours.get(rand.nextInt(fishNeighbours.size()));
            eat(); // Gain energy for eating!
        }
        // STRATEGY 2: SWIM
        else if (!emptyNeighbours.isEmpty()) {
            Random rand = new Random();
            target = emptyNeighbours.get(rand.nextInt(emptyNeighbours.size()));
        }

        // EXECUTE MOVE
        if (target != null) {
            int newX = target.getRow();
            int newY = target.getCol();

            // Move shark to new position (overwrites Fish if hunting)
            grid.getCells()[newX][newY].setCreature(this);
            grid.getCells()[x][y].setCreature(null);

            setMoved(true);
            return true;
        }

        return false;
    }

    @Override
    public void eat() {
        energy += 3;
    }

    @Override
    public void reproduce(Grid grid, int x, int y) {
        Shark baby = new Shark();
        baby.setMoved(true); // Baby already "acted" this turn
        grid.getCells()[x][y].setCreature(baby);
    }
}
