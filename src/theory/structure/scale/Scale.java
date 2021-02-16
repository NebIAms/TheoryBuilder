package theory.structure.scale;

import theory.structure.ImproperStructureException;
import theory.structure.TheoryStructure;
import theory.structure.chord.Chord;

/**
 * Scales are a type of structure that has a list of tones that can be played in sequence
 * This interface allows the scale to start anywhere in the system and move up/down the system following
 * only the tones in that scale
 * @author MT
 *
 */
public interface Scale extends TheoryStructure
{
	/**
	 * moves the starting place for traveling the system
	 * @param startingIndex where the scale should start
	 * @param scaleIndex where the scale index would start
	 * @throws IndexOutOfBoundsException the scale index is not accessible
	 */
	void start(int startingIndex, int scaleIndex) throws IndexOutOfBoundsException;
	
	/**
	 * Moves to the next step in the scale
	 * @return the current index
	 */
	int nextStep();
	
	/**
	 * Moves to the previous step in the scale
	 * @return the current index
	 */
	int previousStep();
	
	// TODO: These parts of the interface are more complicated, maybe these should be part of a different interface
	
	/**
	 * Changes the scale mode
	 * @param mode the number for the mode to set
	 */
	Scale mode(int mode);
	
	/**
	 * Creates a chord from the scale from the starting index based on the number of tones
	 * @param startIndex
	 * @param numberOfTones
	 * @return
	 * @throws ImproperStructureException
	 */
	Chord chord(int structureIndex, int numberOfTones) throws ImproperStructureException;
}
