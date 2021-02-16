package theory.analysis;

import java.util.List;

import theory.Theory;

/**
 * A type of theory that makes an analysis list interpretation of a system
 * @param <T> the type for a list of analysis
 * @author MT
 * 
 */
public interface ListAnalysis<T> extends Theory
{
	/**
	 * Gets the list interpretation of a list analysis
	 * @return
	 */
	List<T> getAnalysis();
}
