package theoryDependency;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import theory.Theory;

/**
 * A dependency manager that is based on a hash map used to associate the class
 * with the filled theory. Accepts both exact classes and subclasses to fill a dependency
 * @author MT
 *
 */
public class MapDependencyManager implements DepencyManager
{
	Map<Class<? extends Theory>, Theory> depencies;
	
	/**
	 * prepares the manager
	 */
	public MapDependencyManager()
	{
		depencies = new HashMap<Class<? extends Theory>, Theory>();
	}

	@Override
	public void add(Class<? extends Theory> theoryClass)
	{
		depencies.put(theoryClass, null);
	}

	@Override
	public void fill(Collection<Theory> theories)
	{
		for (Theory t: theories)
		{
			// contains checks if the hashmap has an exact class or a superclass
			if (this.contains(t) && t.checkMade())
			{
				// replaces the correct exact class or superclass
				depencies.replace(this.getContainedClass(t), t);
			}
		}
	}
	
	/**
	 * Returns true if there is a contained class
	 * @param theory
	 * @return
	 */
	private boolean contains(Theory theory)
	{	
		boolean contains = false;
		
		// Found a contained class that works with the entered class
		if (getContainedClass(theory) != null)
		{
			contains = true;
		}
		
		return contains;
	}
	
	/**
	 * Gets a dependency class that is used to fill in  
	 * @param theory
	 * @return
	 */
	private Class<? extends Theory>  getContainedClass(Theory theory)
	{
		Class<? extends Theory> c = null;
		
		// Contains the exact class
		if (depencies.containsKey(theory.getClass()))
		{
			c = theory.getClass();
		}
		else
		{	
			// Contains some other class that is a superclass/interface to the entered class
			for (Class<? extends Theory> dependency: depencies.keySet())
			{
				if (dependency.isAssignableFrom(theory.getClass()))
				{
					c = dependency;
					break;
				}
			}
		}
		
		return c;	
	}

	@Override
	public Theory get(Class<? extends Theory> theoryClass) throws MissingTheoryException
	{
		Theory t = depencies.get(theoryClass);
		// Check if t has been made
		if (t == null)
		{
			throw new MissingTheoryException("Missing Theory", theoryClass);
		}
		
		return t;
	}
}
