package view;

import model.*;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class DisplayWorld extends JPanel {

    private final Grid gridModel; // Reference to your data
    private final int cellSize = 10; // Size of each square in pixels

    // Constructor: Needs the Grid to know what to draw
    public DisplayWorld(Grid grid) {
        this.gridModel = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen

        Cell[][] cells = gridModel.getCells();

        // Loop through every cell in the grid
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {

                Creature current = cells[x][y].getCreature();

                // Decide color based on occupant
                // (Note: This assumes your Cell class has getCreature())
                if (current instanceof Shark) {
                    g.setColor(current.getColour());
                } else if (current instanceof Fish) {
                    g.setColor(current.getColour());
                } else {
                    g.setColor(Color.CYAN); // Empty
                }

                // Draw the rectangle
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);

                // Optional: Draw a grid line border
                g.setColor(Color.DARK_GRAY);
                g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
}