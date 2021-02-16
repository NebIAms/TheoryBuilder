package theoryDependency;



import theory.Theory;

/**
 * Should be thrown when make is called for a theory but that theory has a dependency 
 * that was never filled by the builder
 * @author MT
 *
 */
public class MissingTheoryException extends Exception
{	
	private Class<? extends Theory> missingTheoryClass;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2461901645063260178L;
	

	/**
	 * default 
	 */
	public MissingTheoryException()
	{
		
	}
	
	/**
	 * simple
	 * @param message
	 */
	public MissingTheoryException(String message)
	{
		super(message);
	}
	
	/**
	 * preferred constructor
	 * @param message the message
	 * @param theoryClass the class that was missing
	 */
	public MissingTheoryException(String message, Class<? extends Theory> theoryClass)
	{
		super(message);
		this.missingTheoryClass = theoryClass;
	}

	/*
	 * @return the missing theory class
	 */
	public Class<? extends Theory> getMissingTheory()
	{
		return this.missingTheoryClass;
	}
}
