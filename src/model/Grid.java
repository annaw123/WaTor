package model;

public class Grid {
    private Cell[][] grid;
    private final int width;
    private final int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = setUpGridRandomly();
    }

    public Cell[][] setUpGridRandomly() {
        grid = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Cell(x, y);
                double choice = Math.random();
                if (choice < 0.25) {
                    grid[x][y].setCreature(new Fish());
                } else if (choice > 0.75) {
                    grid[x][y].setCreature(new Shark());
                }
            }
        }
        return grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGridCell(int x, int y, Creature creature) {
        grid[x][y].setCreature(creature);
    }
}