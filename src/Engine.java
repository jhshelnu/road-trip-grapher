public class Engine
{
	private final String desc;  // Description of the engine (e.g. "V8")
	private final int mpg;	    // Miles Per Gallon
	private final int maxSpeed; // Maximum speed the engine allows for

	public Engine(String desc, int mpg, int maxSpeed)
	{
		this.desc = (desc.length() > 0) ? desc : "Generic engine";
		this.mpg = (mpg > 0) ? mpg : 0;
		this.maxSpeed = (maxSpeed > 0) ? maxSpeed : 0;
	}

	public String getDescription()
	{
		return String.format("%s (MPG: %d, Max speed: %d)", desc, mpg, maxSpeed);
	}

	public int getMPG()
	{
		return mpg;
	}

	public int getMaxSpeed()
	{
		return maxSpeed;
	}

}