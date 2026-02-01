package model;

import java.util.ArrayList;

public class Grid {
    private Cell[][] cells;
    private final int width;
    private final int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = setUpGridRandomly();
    }

    public Cell[][] setUpGridRandomly() {
        cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                double choice = Math.random();
                if (choice < 0.25) {
                    cells[x][y].setCreature(new Fish());
                } else if (choice > 0.75) {
                    cells[x][y].setCreature(new Shark());
                }
            }
        }
        return cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setGridCell(int x, int y, Creature creature) {
        cells[x][y].setCreature(creature);
    }

    public ArrayList<Cell> getNeighbours(int x, int y) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        // Offset pairs: {dx, dy} for Up, Down, Left, Right
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : directions) {
            // Calculate new coordinate with Wrapping (Modulo Arithmetic)
            // (x + dx + width) % width handles negative numbers correctly
            int targetX = (x + dir[0] + width) % width;
            int targetY = (y + dir[1] + height) % height;
            neighbours.add(cells[targetX][targetY]);
        }
        return neighbours;
    }

    public void update() {

        // PASS 1: Reset flags
        // We must mark everyone as "not moved" before the turn starts
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].getCreature() != null) {
                    cells[x][y].getCreature().setMoved(false);
                }
            }
        }

        // PASS 2: Move everyone
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                // Get the creature at this spot
                Creature c = cells[x][y].getCreature();

                // Only act if:
                // 1. There is a creature here
                // 2. It hasn't already moved this turn
                if (c != null && !c.hasMoved()) {
                    c.act(this, x, y);
                }
            }
        }
    }

    // In Grid.java
    public int[] getStats() {
        int fishCount = 0;
        int sharkCount = 0;

        // Efficiently count during a scan (or maintain a running count to be faster)
        for (Cell[] row : cells) {
            for (Cell c : row) {
                if (c.getCreature() instanceof Fish) fishCount++;
                else if (c.getCreature() instanceof Shark) sharkCount++;
            }
        }
        return new int[]{fishCount, sharkCount};
    }
}