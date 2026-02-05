package model;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Shark extends Creature {
    private int energy;

    Shark() {
        super(Color.GRAY, 10);
        energy = 5;
    }

    // HELPER METHODS
    // this could be moved to Creature or overarching Predator class if expanded
    private Cell findPrey(List<Cell> neighbours) {
        List<Cell> prey = neighbours.stream()
                .filter(c -> c.getCreature() instanceof Fish)
                .toList();
        return prey.isEmpty() ? null : prey.get(new Random().nextInt(prey.size()));
    }

    // ACTIONS
    @Override
    public Action act(List<Cell> neighbours, Cell currentCell) {
        // 1. check if the shark is too famished and dies
        energy--;
        if (energy <= 0) {
            return die(currentCell);
        }

        // 2. try to hunt + move
        Cell newPosition = move(neighbours, currentCell);

        // 3. decide if the shark can reproduce
        Creature child = reproduce();
        Cell childPosition = child != null ? currentCell : null;

        // 4. update life stats
        incrementChrononsSurvived();

        // 5. return action
        return new Action(
            this,
            currentCell,
            newPosition,
            false,
            child,
            childPosition
        );
    }

    @Override
    protected Cell move(List<Cell> neighbours, Cell currentCell) {
        // hunt
        Cell preyPosition = findPrey(neighbours);
        if (preyPosition != null) {
            eat();
        }

        // move
        return preyPosition == null ? chooseEmpty(neighbours, currentCell) : preyPosition;
    }

    @Override
    protected Creature reproduce() {
        boolean shouldReproduce = canReproduce();
        return shouldReproduce ? new Shark() : null;
    }

    @Override
    protected void eat() {
        energy += 3;
    }
}
