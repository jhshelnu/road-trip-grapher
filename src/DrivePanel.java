import javax.swing.*;
import java.awt.*;

/**
 * Main view panel containing labels and graphed legs of the trip
 */
class DrivePanel extends JPanel {
    private final static int labelPadding = 10; // Offset the label from the point by 10 pixels horizontally
    private final Point[] coordinates;

    public DrivePanel(Point[] coordinates) {
        this.coordinates = coordinates;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the current height of the window
        int height = getHeight();

        // Draw the point labels
        for (int i = 0; i < coordinates.length; i++) {
            // Draw line segments
            if (i == 0) {
                g.drawLine(0, height, coordinates[i].x, height - coordinates[i].y);
            } else {
                g.drawLine(coordinates[i-1].x, height - coordinates[i-1].y, coordinates[i].x, height - coordinates[i].y);
            }

            // Draw coordinate labels
            g.drawString(String.format("(%d, %d)", coordinates[i].x, coordinates[i].y), coordinates[i].x + labelPadding, height - coordinates[i].y);
        }
    }
}