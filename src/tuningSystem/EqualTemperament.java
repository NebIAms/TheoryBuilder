package tuningSystem;

/**
 * A Mathematical system where the distance between each half-step interval is consistent thought the system.
 * @author MT
 *
 */
public class EqualTemperament extends FormattedSystem
{
	/**
	 * Basic constructor that defines both the equal temperament system and the previous abstract classes
	 * @param size
	 * @param centerFrequency
	 * @param base
	 * @param spacing
	 */
	public EqualTemperament(int size, int centerIndex, double centerFrequency, double base, double baseSpacing)
	{
		this.size = size;
		this.centerIndex = centerIndex;
		this.centerFrequency = centerFrequency;
		this.base = base;
		this.baseSpacing = baseSpacing; 
		
		create();
	}

	@Override
	public void create() 
	{
		this.systemMap.clear();
		
		// Creates the system list
		int i = 0;
		for (double s = -centerIndex; s < (size - centerIndex); s++)
		{
			this.systemMap.add(i, this.centerFrequency * Math.pow(this.base, s/this.baseSpacing));
			i++;
		}
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "\n    equalTemperament()";
	}
}
