import model.Grid;
import view.DisplayWorld;
import javax.swing.JFrame;

public class WaTor {
    public static void main(String[] args) {
        // 1. Create the Model (e.g., 50x50 grid)
        Grid myGrid = new Grid(50, 50);

        // 2. Create the View
        DisplayWorld panel = new DisplayWorld(myGrid);

        // 3. Create the Window (JFrame)
        JFrame frame = new JFrame("Wa-Tor Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        // Size the window automatically based on grid size
        // (50 cells * 10 pixels + window borders)
        frame.setSize(550, 550);
        frame.setVisible(true);
    }
}