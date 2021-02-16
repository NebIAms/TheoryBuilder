package theory.structure.scale;

import java.util.List;

import theory.Theory;
import theory.analysis.BASIC_INTERVAL;
import theory.analysis.BasicIntervalAnalysis;
import theoryDependency.DepencyManager;
import theoryDependency.MapDependencyManager;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

public class MajorScale extends AbstractFormatedScale
{
	private boolean made;
	private DepencyManager depencies;
	
	/**
	 * Basic Major Scale constructor
	 * @param system any formatted system to create a major scale for
	 * @param intervals previously created interval analysis with the same system
	 * @dependency (dependencies = {RatioAnalysis.class})
	 */
	public MajorScale()
	{
		this.made = false;
		this.depencies = new MapDependencyManager();
		this.depencies.add(BasicIntervalAnalysis.class);
	}
	
	@Override
	public void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException
	{
		// inherited system
		this.system = system;
		
		this.depencies.fill(madeTheory);
		BasicIntervalAnalysis intervals = (BasicIntervalAnalysis) this.depencies.get(BasicIntervalAnalysis.class);
		
		// size for the scale to move its index by
		this.scaleSize = (int) system.getBaseSpacing();
		
		// Creates the scale
		for (int i = 0; i < intervals.getAnalysis().size(); i++)
		{
			BASIC_INTERVAL interval = intervals.getAnalysis().get(i);
			
			// Only allows included intervals
			if (includedInterval(interval))
			{
				structure.add(i);
			}
		}
		this.made = true;
	}
	
	@Override
	public boolean checkMade()
	{
		return this.made;
	}
	
	/**
	 * Checks if the interval is one of the included ones 
	 * @param interval to check
	 * @return true if the interval is a TONIC, MAJOR, or PERFECT
	 */
	private static boolean includedInterval(BASIC_INTERVAL interval) 
	{
		return interval.equals(BASIC_INTERVAL.TONIC) || interval.equals(BASIC_INTERVAL.MAJOR) || interval.equals(BASIC_INTERVAL.PERFECT);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " Defined as a Major Scale: "; // TODO: not entirely sure how to print out a major scale, or what else should be added
	}	
}
