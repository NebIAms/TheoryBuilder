package tuningSystem;

import java.util.List;

/**
 * A list of doubles that represent the frequencies used to create music
 * @author MT
 *
 */
public interface TuningSystem
{
	/**
	 * Gets the system itself as a list of doubles
	 * @return
	 */
	List<Double> getSystemMap();
	
	/**
	 * The system will re-create itself
	 */
	void create();
}
