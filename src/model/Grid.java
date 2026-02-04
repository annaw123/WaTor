package model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private Cell[][] cells;
    private final int width;
    private final int height;
    private int fishCount;
    private int sharkCount;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = setUpGridRandomly();
    }

    private Cell[][] setUpGridRandomly() {
        cells = new Cell[width][height];
        fishCount = 0;
        sharkCount = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                double choice = Math.random();
                if (choice < 0.25) {
                    cells[x][y].setCreature(new Fish());
                    fishCount++;
                }
                else if (choice > 0.75) {
                    cells[x][y].setCreature(new Shark());
                    sharkCount++;
                }
            }
        }
        return cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    private ArrayList<Cell> getCellNeighbours(int x, int y) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        // dx dy directions
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : directions) {
            // can wrap round the edge of the grid (toroidal world)
            int targetX = (x + dir[0] + width) % width;
            int targetY = (y + dir[1] + height) % height;
            neighbours.add(cells[targetX][targetY]);
        }
        return neighbours;
    }

    public void update() {
        List<Action> actions = new ArrayList<>();
        
        // 1. collect actions from all creatures
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Creature c = cells[x][y].getCreature();
                if (c != null) {
                    List<Cell> neighbours = getCellNeighbours(x, y);
                    Cell currentCell = cells[x][y];
                    Action action = c.act(neighbours, currentCell);
                    actions.add(action);
                }
            }
        }

        // 2. Apply actions
        for (Action action : actions) {
            Cell oldCell = action.originalPosition();
            if (oldCell == null) continue; // actor might have died already e.g. preyed on fish
            if (oldCell.getCreature() != action.actor()) continue; // actor has moved or been eaten

            // handle death
            if (action.shouldDie()) {
                oldCell.setCreature(null);
                continue;
            }

            // handle movement
            Cell newCell = action.newPosition();
            if (newCell != null && newCell != oldCell) {
                Creature target = newCell.getCreature();
                boolean canMove = target == null || (action.actor() instanceof Shark && target instanceof Fish);
                if (!canMove) {
                    continue;
                }
                newCell.setCreature(oldCell.getCreature());
                oldCell.setCreature(null);
            }

            // handle reproduction
            Creature child = action.child();
            Cell childCell = action.childPosition();
            if (child != null && childCell != null && childCell.getCreature() == null) {
                childCell.setCreature(child);
            }
        }

        // update stats
        fishCount = 0;
        sharkCount = 0;
        for (Cell[] row : cells) {
            for (Cell c : row) {
                if (c.getCreature() instanceof Fish) fishCount++;
                else if (c.getCreature() instanceof Shark) sharkCount++;
            }
        }
    }

    public int[] getStats() {
        return new int[]{fishCount, sharkCount};
    }
}