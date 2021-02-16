package theoryBuilder;

/**
 * Represents any type of exception that causes building a set of theories to fail
 * @author MT
 *
 */
public class BuildingFailedException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3256203644862213933L;
	
	/**
	 * default constructor
	 */
	public BuildingFailedException()
	{
		
	}
	
	/**
	 * weak constructor
	 * @param message that just says the error
	 */
	public BuildingFailedException(String message)
	{
		super(message);	
	}
	
	/**
	 * Preferred constructor
	 * @param message that just says the error
	 * @param cause exception that caused the exception
	 */
	public BuildingFailedException(String message, Exception cause)
	{
		super(message, cause);
	}
}
