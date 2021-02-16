package theory.analysis;

import java.util.Map.Entry;

/**
 * Represents a musical ratio 
 * @author MT
 *
 */
public class Ratio implements Entry<Integer, Integer>
{	
	private Integer numerator;
	private Integer denominator;
	
	/**
	 * Standard Ratio that represents numerator/denominator
	 * @param numerator
	 * @param denominator
	 */
	public Ratio(Integer numerator, Integer denominator)
	{
		this.numerator = numerator;
		this.denominator = denominator;
	}

	@Override
	public Integer getKey()
	{ 
		return this.numerator;
	}

	@Override
	public Integer getValue() 
	{
		return this.denominator;
	}


	@Override
	public Integer setValue(Integer value) 
	{
		return this.denominator = value;
	}
	
	/**
	 * Computes the ratio result of numerator/denominator
	 * @return numerator/denominator
	 */
	public double getResult()
	{
		return this.numerator.doubleValue() / this.getValue().doubleValue();
	}
	
	/**
	 * Checks if the ratios have equal complexity
	 * @param r input ratio
	 * @return true if the ratios have equal complexity
	 */
	public boolean equalComplexity(Ratio r)
	{
		return this.denominator == r.denominator;
	}
	
	/**
	 * Checks if the current ratio is minor to (more complex than) the input ratio
	 * @param r input ratio
	 * @return true if minor to, false is not minor to
	 */
	public boolean minorTo(Ratio r)
	{
		return this.denominator > r.denominator;
	}
	
	/**
	 * Checks if the current ratio is major to (less complex than) the input ratio
	 * @param r input ratio
	 * @return true if major to, false if not major to
	 */ 
	public boolean majorTo(Ratio r)
	{
		return this.denominator < r.denominator;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numerator == null) ? 0 : numerator.hashCode());
		result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ratio other = (Ratio) obj;
		if (numerator == null) {
			if (other.numerator != null)
				return false;
		} else if (!numerator.equals(other.numerator))
			return false;
		if (denominator == null) {
			if (other.denominator != null)
				return false;
		} else if (!denominator.equals(other.denominator))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return numerator + "/" + denominator;
	}
}
