import java.lang.Math;

/**
 * Car class used to perform calculations for a trip
 */
public class Car
{
	private final String desc;
	private int xPos;
	private int yPos;
	private GasTank gasTank;
	private final Engine engine;

	public Car(String desc, int maxFuel, Engine engine)
	{
		this.desc = (desc.length() > 0) ? desc : "Generic car";
		this.engine = (engine != null) ? engine : new Engine("", 0, 0);
		this.gasTank = new GasTank(maxFuel);

		xPos = 0;
		yPos = 0;
	}

	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getMPG() { return engine.getMPG(); }
	public int getMaxSpeed() { return engine.getMaxSpeed(); }
	public double getFuelLevel() { return gasTank.getLevel(); }

	public String getDescription()
	{
		return String.format("%s (engine: %s), fuel: %.2f/%d, location: (%d,%d)",
			desc, engine.getDescription(), gasTank.getLevel(), gasTank.getCapacity(),
			xPos, yPos);
	}

	public void fillUp()
	{
		gasTank.setLevel( (double)gasTank.getCapacity() );
	}

	/**
	 * Function to compute ending coordinates of the car given fuel efficiency and fuel levels
	 * @param distance in miles to drive
	 * @param xRatio horizontal component of ratio to determine angle of ascent/descent (negative == left, positive == right)
	 * @param yRatio vertical component of ratio to determine angle of ascent/descent (negative == down, positive == up)
	 * @return the distance driven in miles as a double after ending coordinates are truncated to integers
	 */
	public double drive(int distance, double xRatio, double yRatio)
	{
		// Compute the distance that will actually be travelled
		double distanceTravelled = Math.min(distance, gasTank.getLevel() * engine.getMPG());
		if (distanceTravelled != distance) {
			System.out.printf("Ran out of gas after driving %.2f miles.\n", distanceTravelled);
		}

		// Calculate distanceMultiplier to find new coordinates. (distanceMultiplier * yRatio = yDist, distanceMultiplier * xRatio = xDist.)
		// Derived from the equation (xRatio * dM)^2 + (yRatio * dM)^2 = distanceTravelled^2
		double distanceMultiplier = distanceTravelled / Math.hypot(xRatio, yRatio);

		// Calculate new xPos and yPos.
		xPos += (xRatio * distanceMultiplier);
		yPos += (yRatio * distanceMultiplier);

		// Compute new gas level
		gasTank.setLevel(gasTank.getLevel() - (distanceTravelled / engine.getMPG()));

		// Return the actual distance travelled
		return distanceTravelled;
	}
}