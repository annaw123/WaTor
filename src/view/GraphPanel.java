package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private List<Integer> fishHistory = new ArrayList<>();
    private List<Integer> sharkHistory = new ArrayList<>();
    private int maxHistory = 200; // Keep only the last 200 ticks

    public GraphPanel() {
        setPreferredSize(new Dimension(550, 150)); // Set a reasonable size
        setBackground(Color.WHITE);
    }

    public void addData(int fish, int sharks) {
        fishHistory.add(fish);
        sharkHistory.add(sharks);

        // Keep the list from growing infinitely
        if (fishHistory.size() > maxHistory) {
            fishHistory.remove(0);
            sharkHistory.remove(0);
        }
        repaint(); // Trigger a redraw
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Calculate scaling
        if (fishHistory.isEmpty()) return;

        int w = getWidth();
        int h = getHeight();
        int maxVal = 0;

        // Find highest value to scale the Y-axis
        for (int i : fishHistory) maxVal = Math.max(maxVal, i);
        for (int i : sharkHistory) maxVal = Math.max(maxVal, i);
        if (maxVal == 0) maxVal = 1; // Prevent divide by zero

        // 2. Draw Fish Line (Green)
        g.setColor(Color.GREEN);
        drawHistory(g, fishHistory, w, h, maxVal);

        // 3. Draw Shark Line (Red)
        g.setColor(Color.RED);
        drawHistory(g, sharkHistory, w, h, maxVal);

        // 4. Draw Legend/Text
        g.setColor(Color.BLACK);
        g.drawString("Fish: " + fishHistory.get(fishHistory.size()-1), 10, 15);
        g.drawString("Sharks: " + sharkHistory.get(sharkHistory.size()-1), 10, 30);
    }

    private void drawHistory(Graphics g, List<Integer> history, int w, int h, int maxVal) {
        double xStep = (double) w / (maxHistory - 1);

        for (int i = 0; i < history.size() - 1; i++) {
            int x1 = (int) (i * xStep);
            int y1 = h - (int) ((double) history.get(i) / maxVal * h);
            int x2 = (int) ((i + 1) * xStep);
            int y2 = h - (int) ((double) history.get(i + 1) / maxVal * h);

            g.drawLine(x1, y1, x2, y2);
        }
    }
}