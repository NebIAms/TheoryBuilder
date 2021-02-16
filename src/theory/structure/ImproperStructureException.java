package theory.structure;

/**
 * This exception describes when a structure is not properly constructed
 * @author MT
 *
 */
public class ImproperStructureException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6099494780993582302L;

	/**
	 * Default constructor
	 */
	public ImproperStructureException()
	{
		
	}
	
	/**
	 * Preferred constructor
	 * @param message super message
	 */
	public ImproperStructureException(String message)
	{
		super(message);
	}
}
