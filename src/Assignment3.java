import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Point;

/**
 * Program to graph the path taken by a car with properties determined by user input
 */
public class Assignment3 {
    private final static int width = 600;  // application window width
    private final static int height = 600; // application window height

    /**
     * Creates a JFrame object to render the data gathered
     * @return a JFrame object with desired properties
     */
    private static JFrame initApp() {
        JFrame app = new JFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(width, height);
        app.setVisible(true);
        return app;
    }

    /**
     * Requests an integer from the user via a dialogue popup
     * @param message the request message to display to the user
     * @return a valid integer greater than zero
     */
    private static int getNum(String message) {
        int val = 0; // unnecessary, but removes warning in return statement

        try {
            // Loop until valid integer in expected range is given or until exception is thrown
            do {
                val = Integer.parseInt(JOptionPane.showInputDialog(message));
            } while (val <= 0);
        } catch (Exception e) {
            // If a non-int is entered, print the following message and terminate the program
            JOptionPane.showMessageDialog(null, "Invalid data entered. Exiting.");
            System.exit(1);
        }

        return val;
    }

    /**
     * @param message to be displayed to the user in the popup
     * @return a valid double based on user input from the popup
     */
    private static double getRatio(String message) {
        double val = 0.0;

        try {
            val = Double.parseDouble(JOptionPane.showInputDialog(message));
        } catch (Exception e) {
            // If a non-number is entered, print the following message and terminate the program
            JOptionPane.showMessageDialog(null, "Invalid data entered. Exiting.");
            System.exit(1);
        }

        return val;
    }

    public static void main(String[] args) {
        // Get user input
        String carDesc = JOptionPane.showInputDialog("Please enter the car's description.");
        int maxFuel = getNum("Please enter the fuel tank capacity");
        String engineDesc = JOptionPane.showInputDialog("Please enter the engine's description");
        int mpg = getNum("Please enter the miles per gallon");
        int maxSpeed = getNum("Please enter the max speed");

        // Create the car with the specified conditions
        Car c = new Car(carDesc, maxFuel, new Engine(engineDesc, mpg, maxSpeed));
        // Show the car's description to the user via popup
        JOptionPane.showMessageDialog(null, c.getDescription());
        c.fillUp();

        // number of legs on the trip that the car makes (number of line segments to draw)
        int numLegs = getNum("Please enter the number of legs on the trip");

        Point[] coordinates = new Point[numLegs]; // Stores the ending coordinates of each drive segment

        for (int i = 0; i < numLegs; i++) {
            int distance = getNum(String.format("Please enter the distance for leg %d", i + 1));
            double xRatio = getRatio(String.format("Please enter the x ratio for leg %d", i + 1));
            double yRatio = getRatio(String.format("Please enter the y ratio for leg %d", i + 1));

            // Drive the car based on given input, then store the results in the coordinate array.
            c.drive(distance, xRatio, yRatio);
            coordinates[i] = new Point(c.getX(), c.getY());
        }

        // Create application window and render results
        JFrame app = initApp();
        DrivePanel mainPanel = new DrivePanel(coordinates);
        app.add(mainPanel);
    }
}