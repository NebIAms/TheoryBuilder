package theoryDependency;

import java.util.Collection;

import theory.Theory;

/**
 * Helps a theory manage it's dependencies
 * Allows adding theories as classes (not instances)
 * Allows filling those dependencies
 * Then get a specific theory
 * @author MT
 *
 */
public interface DepencyManager
{
	/**
	 * 
	 * @param theoryClass
	 */
	void add(Class<? extends Theory> theoryClass);
	
	/**
	 * 
	 * @param theoryList
	 */
	void fill(Collection<Theory> theoryList);
	
	/**
	 * 
	 * @param theoryClass
	 * @return
	 * @throws MissingTheoryException
	 */
	Theory get(Class<? extends Theory> theoryClass) throws MissingTheoryException;	
}
