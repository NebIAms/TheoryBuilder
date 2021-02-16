package theory.structure.scale;

import java.util.ArrayList;
import java.util.List;

import theory.Theory;
import theoryDependency.DepencyManager;
import theoryDependency.MapDependencyManager;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

/**
 * A type of scale that is built from a pre-existing scale
 * Rotates a scale's structure
 * @author MT
 *
 */
public class Mode extends AbstractFormatedScale
{
	private boolean made;
	private DepencyManager depencies;
	private int mode;
	
	/**
	 * Preparation for a mode of a scale
	 * @param mode to make of the scale
	 * @dependency (dependencies = {Scale.class})
	 */
	public Mode(int mode)
	{
		this.mode = mode;
		this.made = false;
		this.depencies = new MapDependencyManager();
		this.depencies.add(Scale.class);
	}
	
	@Override
	public void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException
	{
		// inherited system
		this.system = system;
		
		this.depencies.fill(madeTheory);
		Scale scale = (Scale) this.depencies.get(Scale.class);
		
		// Rotate the structure around the index of the mode
		int rotateValue = scale.apply().get(mode);
							
		List<Integer> endStructure = new ArrayList<Integer>();
								
		// new scale
		for (int i = 0; i < scale.apply().size(); i++)
		{
			// Values before the point get added to the end
			if (i < mode)
			{
				endStructure.add(Math.floorMod(scale.apply().get(i) - rotateValue, (int) system.getBaseSpacing()));
			}
			// Values including and after the point get reduced by the rotate value and added
			else
			{
				this.structure.add(scale.apply().get(i) - rotateValue);
			}
		}
								
		this.structure.addAll(endStructure);
		
		this.made = true;
	}
	
	@Override
	public boolean checkMade()
	{
		return this.made;
	}

	@Override
	public String toString()
	{
		return "Mode " + this.mode + ": " + structure.toString();
	}	
}
