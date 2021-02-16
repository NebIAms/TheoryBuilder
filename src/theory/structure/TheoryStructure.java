package theory.structure;

import java.util.List;

import theory.Theory;

/**
 * This type of theory is a musical structure such as a scale or chord
 * The methods here are just to apply the full structure at a given point 
 * in the system to produce that structure
 * @author MT
 *
 */
public interface TheoryStructure extends Theory
{
	/**
	 * Returns the structure list starting at 0
	 * Used to see the raw data the structure defines
	 * @return The structure list starting at 0
	 */
	List<Integer> apply();
	
	/**
	 * Adds an index where the structure starts
	 * Used to apply the structure at a specific starting point
	 * @param index
	 * @return The structure list starting at index
	 */
	List<Integer> applyAt(int index);
}
