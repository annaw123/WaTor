import model.Grid;
import view.DisplayWorld;
import view.GraphPanel;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;

public class WaTor {
    public static void main(String[] args) {
        Grid myGrid = new Grid(50, 50);

        // Create Views
        DisplayWorld simPanel = new DisplayWorld(myGrid);
        GraphPanel graphPanel = new GraphPanel(); // New!

        JFrame frame = new JFrame("Wa-Tor Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout Manager: Allows stacking components
        frame.setLayout(new BorderLayout());

        frame.add(simPanel, BorderLayout.CENTER);
        frame.add(graphPanel, BorderLayout.SOUTH); // Add graph to bottom

        frame.setSize(550, 700); // Taller window to fit both
        frame.setVisible(true);

        // Update Loop
        Timer timer = new Timer(100, e -> {
            myGrid.update();
            simPanel.repaint();

            // Push new stats to the graph
            int[] stats = myGrid.getStats();
            graphPanel.addData(stats[0], stats[1]);
        });

        timer.start();
    }
}