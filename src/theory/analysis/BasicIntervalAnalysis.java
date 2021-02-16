package theory.analysis;

import java.util.ArrayList;
import java.util.List;

import theory.Theory;
import theoryDependency.DepencyManager;
import theoryDependency.MapDependencyManager;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

/**
 * This type of list analysis defines a set of basic interval types depending on 
 * a list of ratios.
 * @see BASIC_INTERVAL
 * @author MT
 * 
 */
public class BasicIntervalAnalysis implements ListAnalysis<BASIC_INTERVAL> 
{	
	private boolean made;
	private List<BASIC_INTERVAL> intervals;
	private DepencyManager depencies;
	
	/**
	 * Preparation for an interval analysis
	 * @dependency (dependencies = {RatioAnalysis.class})
	 */
	public BasicIntervalAnalysis()
	{
		this.made = false;
		this.intervals = new ArrayList<BASIC_INTERVAL>();
		this.depencies = new MapDependencyManager();
		this.depencies.add(RatioAnalysis.class);
	}
	
	@Override
	public void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException
	{
		this.depencies.fill(madeTheory);
		RatioAnalysis ratios = (RatioAnalysis) this.depencies.get(RatioAnalysis.class);
		
		Ratio triTone = RatioAnalysis.getTritone(system.getBase());
		// Goes through each ratio to try and define a parallel list of interval types
		for (int i1 = 0; i1 < ratios.getAnalysis().size(); i1++)
		{
			Ratio r1 = ratios.getAnalysis().get(i1);
			
			// if i1 is a special case (tonic or tritone)
			// normal continuation (no skipping) 
			if (isSpecialCase(r1, triTone))
			{
				assignSpecialCase(i1, r1, triTone);
			}
			else
			{
				// if is not special case, then get i2 and skip i1 later
				int i2 = i1 + 1;
				Ratio r2 = ratios.getAnalysis().get(i2);
				
				// Check if i2 is a special case
				// assigns perfect/imperfect and i2 to a special case
				// skips two (i2 and i3)
				if (isSpecialCase(r2, triTone))
				{
					
					// TODO: move to another 
					assignPerfectTritone(i1, r1, i2, r2, triTone);
					 
					// have i1 skip two steps
					i1 += 2;
				}
				// i1 and i2 are a major minor pair
				else
				{
					assignMajorMinor(i1, r1, i2, r2);
					
					// have i1 skip
					i1++;
				}	
			}
		}
		this.made = true;
	}

	@Override
	public boolean checkMade()
	{
		return this.made;
	}
	
	@Override
	public List<BASIC_INTERVAL> getAnalysis()
	{
		return this.intervals;
	}
	
	/**
	 * Checks if a ratio is a tonic or tritone
	 * @param r ratio to check
	 * @param tritone ratio for the current system
	 * @return true if a ratio is a special case
	 */
	private boolean isSpecialCase(Ratio r, Ratio triTone)
	{
		return isTonic(r) || isTriTone(r, triTone);
	}
	
	/**
	 * Assigns a tonic of tritone ratio for the interval analysis
	 * @param i index to place the interval
	 * @param r ratio to assign
	 * @param triTone ratio for the current system
	 */
	private void assignSpecialCase(int i, Ratio r, Ratio triTone)
	{
		if (isTonic(r))
		{
			intervals.add(i, BASIC_INTERVAL.TONIC);
		}
		else if (isTriTone(r, triTone))
		{
			intervals.add(i, BASIC_INTERVAL.TRI_TONE);
		}
		else
		{
			// other intervals
		}
	}
	
	/**
	 * Assigns a special case where perfect/imperfect pair surrounds a tritone
	 * @param i1 first index of ratio
	 * @param r1 first ratio to check
	 * @param i2 second index of ratio
	 * @param r2 second ratio to check
	 * @param triTone ratio for the current system
	 */
	private void assignPerfectTritone(int i1, Ratio r1, int i2, Ratio r2, Ratio triTone)
	{
		boolean isPerfect = false;
		
		// sorts the difference between perfect/imperfect
		// 	not sure how to really sort these
		if (r1.majorTo(triTone))
		{
			isPerfect = true;
		}
		
		// Imperfect/perfect intervals are a pair
		//  not sure if this assumption makes sense
		if (isPerfect)
		{
			intervals.add(i1, BASIC_INTERVAL.PERFECT);
		}
		else
		{
			intervals.add(i1, BASIC_INTERVAL.IMPERFECT);
		}
		
		assignSpecialCase(i2, r2, triTone);
		
		int i3 = i2 + 1;
		
		if (isPerfect)
		{
			intervals.add(i3, BASIC_INTERVAL.PERFECT);
		}
		else
		{
			intervals.add(i3, BASIC_INTERVAL.IMPERFECT);
		}
	}
	
	/**
	 * Checks if the interval is a tonic
	 * @param r ratio to check
	 * @return true if the ratio is a tonic
	 */
	private boolean isTonic(Ratio r)
	{
		double result = r.getResult();
		return (result == Math.floor(result));
	}
	
	/**
	 * Checks if a ratio matches the tritone ratio for the base
	 * @param r ratio to check
	 * @param triTone to check against
	 * @return true if r equals triTone
	 */
	private boolean isTriTone(Ratio r, Ratio triTone)
	{
		return r.equals(triTone);
	}
	
	/**
	 * Assigns r1 and r2 to an equal or a major minor pair depending on the ratio complexity difference
	 * @param i1 index of ratio 1
	 * @param r1 first ratio to assign
	 * @param i2 index of ratio 2
	 * @param r2 second ratio to assign
	 */
	private void assignMajorMinor(int i1, Ratio r1, int i2, Ratio r2)
	{
		// complexity is equal, both intervals are equal
		if (r1.equalComplexity(r2))
		{
			intervals.add(i1, BASIC_INTERVAL.EQUAL);
			intervals.add(i2, BASIC_INTERVAL.EQUAL);
		}
		// r1 is major, r2 is minor
		else if (r1.majorTo(r2))
		{
			intervals.add(i1, BASIC_INTERVAL.MAJOR);
			intervals.add(i2, BASIC_INTERVAL.MINOR);
		}
		// r1 is minor, r2 is major
		else if (r1.minorTo(r2)) 
		{
			intervals.add(i1, BASIC_INTERVAL.MINOR);
			intervals.add(i2, BASIC_INTERVAL.MAJOR);
		}
		else
		{
			// should be unreachable
		}
	}
	
	@Override
	public String toString()
	{
		return "Basic Interval Analysis: " + intervals.toString();
	}
}
