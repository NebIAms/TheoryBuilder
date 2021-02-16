package theoryBuilder;

import java.util.List;

import theory.Theory;

/**
 * Theory builders make added theory instances into a result list of theories
 * @author MT
 *
 */
public interface TheoryBuilder
{
	/**
	 * Adds a theory to be built when make all is called
	 * @param theory instance used to call make on
	 */
	void addTheory(Theory theory);
	
	/**
	 * Makes all the added theories
	 * @throws BuildingFailedException make all failed and 
	 */
	void makeAll() throws BuildingFailedException;
	
	/**
	 * Gets the build result
	 * @return the list of theories built
	 */
	List<Theory> getResult();
}
