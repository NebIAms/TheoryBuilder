package theory;

import java.util.List;

import theory.structure.ImproperStructureException;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

/**
 * A theory is a musical structure of some kind that is defined by the input system
 * This basic version just has a method that creates the theory
 * @author MT
 *
 */
public interface Theory
{
	/**
	 * Makes a theory
	 * @param system used for making the theory
	 * @param madeTheory list of theories that are made and used to fill dependencies
	 * @throws MissingTheoryException dependent theory is missing
	 * @throws ImproperStructureException specification for the theory is wrong
	 * @throws IndexOutOfBoundsException specification for the theory does not fit the scale
	 */
	void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException, ImproperStructureException, IndexOutOfBoundsException;
	
	/**
	 * checks if the theory has been made
	 * @return
	 */
	boolean checkMade();
}
