package tuningSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Template class that provides the system map
 * @author MT
 *
 */
public abstract class GenericSystem implements TuningSystem
{
	protected int size;
	protected List<Double> systemMap = new ArrayList<Double>();
	
	// A list of common sizes for systems
	public enum TuningSystemSize
	{
		MIDI(128);
		
		int size;
		
		private TuningSystemSize(int size)
		{
			this.size = size;
		}
		
		public int getSize()
		{
			return this.size;
		}
	}
	
	public List<Double> getSystemMap()	{ return this.systemMap; }
	public int getSize()				{ return size; }
	public void setSize(int size)		{ this.size = size; }
	
	@Override
	public String toString()
	{
		return "System map: " + this.systemMap.toString() + "\nDefined by:";
	}
}
