public class GasTank
{
	private final int MAX_CAPACITY;
	private double fuelLevel;

	public GasTank(int maxFuel)
	{
		this.fuelLevel = 0.0;
		MAX_CAPACITY = (maxFuel > 0) ? maxFuel : 0;
	}

	public int getCapacity()
	{
		return MAX_CAPACITY;
	}

	public double getLevel()
	{
		return fuelLevel;
	}

	public void setLevel(double levelIn)
	{
		if (levelIn > MAX_CAPACITY) {fuelLevel = MAX_CAPACITY;}
		else if (levelIn < 0) {fuelLevel = 0.0;}
		else {fuelLevel = levelIn;}
	}
}