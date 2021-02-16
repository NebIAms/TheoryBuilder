package theory.structure.scale;

import java.util.List;

import theory.Theory;
import theory.structure.ImproperStructureException;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

public class ChromaticScale extends AbstractFormatedScale
{
	private boolean made;
	
	/**
	 * prepares a chromatic scale
	 * No requirements
	 */
	public ChromaticScale()
	{
		this.made = false;
	}
	
	@Override
	public void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException, ImproperStructureException, IndexOutOfBoundsException
	{
		// inherited system
		this.system = system;
		
		// size for the scale to move its index by
		this.scaleSize = 1;
		
		// Simple structure
		structure.add(0);
		
		this.made = true;
	}

	@Override
	public boolean checkMade()
	{
		return this.made;
	}
}
