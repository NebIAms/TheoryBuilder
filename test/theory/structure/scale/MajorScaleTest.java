package theory.structure.scale;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import theory.Theory;
import theory.analysis.BasicIntervalAnalysis;
import theory.analysis.RatioAnalysis;
import theory.structure.scale.MajorScale;
import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.GenericSystem;

class MajorScaleTest
{

	// T1 Major Scale, gets all tonic intervals (only tonic)
	@Test
	void T1_MajorScale_AllTonicIntervals()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 2);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
		scale.add(0);
		scale.add(1);
		
		assertEquals(scale, testScale.apply());
	}
	
	// T2 Major Scale, gets all major intervals
	@Test
	void T2_MajorScale_AllMajorIntervals()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 3);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
		scale.add(0);
		scale.add(1);
		
		assertEquals(scale, testScale.apply());
	}
	
	// T3 Major Scale, gets all perfect intervals
	@Test
	void T3_MajorScale_AllPerfectIntervals()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 4);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
		scale.add(0);
		scale.add(1);
		scale.add(3);
		
		assertEquals(scale, testScale.apply());
	}
	
	// T4 Major Scale, full system base 2 with tritone and perfects eqt(2, 12)
	@Test
	void T4_MajorScale_FullSystem_Base2WithTritoneAndPerfects()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 12);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
		scale.add(0);
		scale.add(2);
		scale.add(4);
		scale.add(5);
		scale.add(7);
		scale.add(9);
		scale.add(11);
		
		assertEquals(scale, testScale.apply());
	}
	
	// T5 Major Scale, full system base 2 without tritone eqt(2, 11)
	@Test
	void T5_MajorScale_FullSystem_Base2WithoutTritone()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
		scale.add(0);
		scale.add(2);
		scale.add(3);
		scale.add(5);
		scale.add(8);
		scale.add(9);

		assertEquals(scale, testScale.apply());
	}
	
	// T6 Major Scale, full system base 2 with equal eqt(2, 7) 
	// [not fully sure how to handle these intervals in major scales]
	@Test
	void T6_MajorScale_FullSystem_Base2WithEqualIntervals()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 7);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
//		scale.add(0);
//		scale.add(2);
//		scale.add(3);
//		scale.add(5);
//		scale.add(8);
//		
//		assertEquals(scale, testScale.apply());
	}
	
	// T7 Major Scale, full system base 2 with tritone but not perfects eqt(2, 10)
	@Test
	void T7_MajorScale_FullSystem_Base2WithTritoneNoPerfects()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 10);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
		scale.add(0);
		scale.add(2);
		scale.add(3);
		scale.add(7);
		scale.add(8);
		
		assertEquals(scale, testScale.apply());
	}
	
	// T8 Major Scale, full system base 2 non-cyclic system (2, 6.283185307179586476925286766559) or 2*Pi
	// [requires the solution from ealier tests in ratio analysis and interval analysis]
	@Test
	void T8_MajorScale_FullSystem_Base2NonCyclic()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 6.283185307179586476925286766559);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(interval);
		
		MajorScale testScale = new MajorScale();
		try									{ testScale.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(testScale);
		
		List<Integer> scale = new ArrayList<Integer>();
//		scale.add(0);
//		scale.add(1);
//		scale.add(3);
//		
//		assertEquals(scale, testScale.apply());
	}
}
