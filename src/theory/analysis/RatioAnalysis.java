package theory.analysis;

import java.util.ArrayList;
import java.util.List;

import theory.Theory;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

/**
 * Ratio analysis is a form of list analysis that creates approximate (within 1%) ratios that 
 * can be used to define a system's basic intervals.
 * @author MT
 *
 */
public class RatioAnalysis implements ListAnalysis<Ratio>
{
	private boolean made;
	private List<Ratio> ratios;
	
	/**
	 * Prepares Ratio Analysis
	 * No dependencies
	 */
	public RatioAnalysis()
	{
		this.made = false;
		this.ratios = new ArrayList<Ratio>();
		// No dependencies
	}

	@Override
	public void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException
	{
		// No dependencies
		
		// Only first octave is analysed, the rest are predictable
		if (Math.floor(system.getBaseSpacing()) == system.getBaseSpacing())
		{
			this.formRatioList(system.getSystemMap().get(0), 
					system.getSystemMap().subList(1, (int) system.getBaseSpacing()));
		}
		// The entire system is analyzed
		else
		{
			this.formRatioList(system.getSystemMap().get(0),
					system.getSystemMap().subList(1, system.getSize() - 1));
		}
		
		this.made = true;
	}
	
	@Override
	public boolean checkMade()
	{
		return this.made;
	}

	/**
	 * forms the ratio list analysis based on the first number (always 1) and the list to analyze
	 * @param first
	 * @param analyse
	 */
	private void formRatioList(double first, List<Double> analyse)
	{
		ratios.clear();
		
		// 1/1 is always the starting ratio
		ratios.add(new Ratio(1, 1));
		
		for (Double element: analyse)
		{
			Ratio r = preRatio(element.doubleValue()/first);
			ratios.add(r);
		}
	}
	
	/**
	 * Defines the setup used for creating a ratio
	 * Defines the bounds for the ratio and makes a fast starting numerator
	 * @param inputRatio
	 * @return the ratio created by formRatio()
	 */
	private static Ratio preRatio(double inputRatio)
	{		
		double lowerBound = inputRatio * (99.0/100.0);
		double upperBound = inputRatio * (101.0/100.0);

		// Fast starting ratio, works well for bigger numbers and requires far less recursion
		int n = (int) (Math.ceil(lowerBound * 2));

		return formRatio(lowerBound, upperBound, n, 2);
	}
	
	/**
	 * Forms a ratio that fits within the bounds based on the n and d. 
	 *  This version of form ratio is recursive and may cause a stack overflow in worse case scenarios
	 *  without proper preparation from preRatio.
	 * @param lowerBound of the output ratio
	 * @param upperBound of the output ratio
	 * @param n current numerator
	 * @param d current denominator
	 * @return the ratio created when n/d fits within the bounds
	 */
	private static Ratio formRatio(double lowerBound, double upperBound, int n, int d)
	{
		double ratioResult = (double)n / (double)d;
		
		if (ratioResult < lowerBound)
		{
			return formRatio(lowerBound, upperBound, n + 1, d);
		}
		else if (ratioResult > upperBound)
		{
			return formRatio(lowerBound, upperBound, d + 2, d + 1);
		}
		
		return new Ratio(n, d);
	}
	
	@Override
	public List<Ratio> getAnalysis()
	{
		return this.ratios;
	}
	
	/**
	 * Creates the tri-tone ratio for a system
	 * Will change when tri-tones are better understood
	 * @param base used to calculate the base value to define the ratio
	 * @return the tri-tone ratio for the input base
	 */
	public static Ratio getTritone(double base)
	{
		double tritone = Math.pow(base, 1/base);
		return preRatio(tritone);
	}

	@Override
	public String toString()
	{
		return "Ratio Analysis: " + ratios.toString();
	}
}
