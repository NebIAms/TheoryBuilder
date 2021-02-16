package theory.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import tuningSystem.FormattedSystem;

/**
 * Used for any single musical structure such as chords or scales
 * @author MT
 *
 */
public abstract class AbstractFormattedStructure implements TheoryStructure
{
	protected List<Integer> structure = new ArrayList<Integer>();
	protected FormattedSystem system;
	
	@Override
	public List<Integer> apply()
	{
		return structure;
	}
	
	@Override
	public List<Integer> applyAt(int index) throws NoSuchElementException
	{
		List<Integer> shiftedScale = new ArrayList<Integer>();
		for (Integer step: this.structure)
		{
			// Check if the step is within bounds before adding it
			try 
			{
				shiftedScale.add(checkIndex(step + index));
			}	
			catch (NoSuchElementException e) 
			{
				throw e;
			}
		}
		
		return shiftedScale;
	}
	
	/**
	 * Checks if the index is within bounds before returning it
	 * @param index
	 * @return index
	 * @throws NoSuchElementException
	 */
	protected int checkIndex(int index) throws NoSuchElementException
	{
		// will throw an exception if this is impossible
		system.getSystemMap().get(index);
		
		return index;
	}
}
