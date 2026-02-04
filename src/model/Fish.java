package model;
import java.awt.Color;
import java.util.List;

public class Fish extends Creature {

    Fish() {
        super(Color.ORANGE, 5);
    }

    @Override
    public Action act(List<Cell> neighbours, Cell currentCell) {
        // 1. choose an empty neighbour to move to
        Cell newPosition = move(neighbours, currentCell);

        // 2. decide if the fish should reproduce
        Creature child = reproduce();
        Cell childPosition = null;
        if (child != null) {
            if (newPosition != null && newPosition != currentCell) {
                childPosition = currentCell;
            } else {
                childPosition = chooseEmpty(neighbours, currentCell);
            }
        }

        // 3. update life stats
        incrementChrononsSurvived();

        // 4. return action
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
    public void eat() {
        // do nothing
    }

    @Override
    public Cell move(List<Cell> neighbours, Cell currentCell) {
        return chooseEmpty(neighbours, currentCell);
    }

    @Override
    public Creature reproduce() {
        boolean shouldReproduce = canReproduce();
        return shouldReproduce ? new Fish() : null;
    }
}
