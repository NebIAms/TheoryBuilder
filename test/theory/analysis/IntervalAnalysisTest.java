package theory.analysis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import theory.Theory;
import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.FormattedSystem;
import tuningSystem.GenericSystem;

class IntervalAnalysisTest
{

	// A4 Interval Analysis tonic, first tonic only
	@Test
	void A4_IntervalAnalysis_TonicFirstTone()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 1);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC);
		
		assertEquals(intervalList, interval.getAnalysis());
	}

	// A5 Interval Analysis tonic (whole number ratio results)
	@Test
	void A5_IntervalAnalysis_Tonic()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 2);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC);
		intervalList.add(BASIC_INTERVAL.TONIC);
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A6 Interval Analysis TriTone, base 2 
	@Test
	void A6_IntervalAnalysis_TritoneBase2()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 2);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC);
		intervalList.add(BASIC_INTERVAL.TRI_TONE);
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A7 Interval Analysis Major Minor
	@Test
	void A7_IntervalAnalysis_MajorMinor()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 3);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); // 1/1
		intervalList.add(BASIC_INTERVAL.MAJOR); // 5/4
		intervalList.add(BASIC_INTERVAL.MINOR); // 8/5
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A8 Interval Analysis Minor Major
	@Test
	void A8_IntervalAnalysis_MinorMajor()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 3);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		// swap 1 and 2
		Ratio r1 = ratio.getAnalysis().get(1);
		Ratio r2 = ratio.getAnalysis().get(2);
		
		ratio.getAnalysis().remove(1);
		ratio.getAnalysis().remove(1);
		
		ratio.getAnalysis().add(r2);
		ratio.getAnalysis().add(r1);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); // 1/1
		intervalList.add(BASIC_INTERVAL.MINOR); // 5/4
		intervalList.add(BASIC_INTERVAL.MAJOR); // 8/5
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A9 Interval Analysis Equal Equal
	@Test
	void A9_IntervalAnalysis_EqualEqual()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 3);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		// make 1 and 2 have equal complexity (not numerator)
		
		ratio.getAnalysis().remove(1);
		ratio.getAnalysis().remove(1);
		
		ratio.getAnalysis().add(new Ratio(4, 3));
		ratio.getAnalysis().add(new Ratio(5, 3));
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); // 1/1
		intervalList.add(BASIC_INTERVAL.EQUAL); // 4/3
		intervalList.add(BASIC_INTERVAL.EQUAL); // 5/3
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A10 Interval Analysis, perfects in base 2
	@Test
	void A10_IntervalAnalysis_PerfectBase2()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 4);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); 		// 1/1
		intervalList.add(BASIC_INTERVAL.PERFECT); 	// 6/5
		intervalList.add(BASIC_INTERVAL.TRI_TONE); 	// 17/12
		intervalList.add(BASIC_INTERVAL.PERFECT); 	// 5/3
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A11 Interval Analysis, full system base 2 with tritone and perfects eqt(2, 12)
	@Test
	void A11_IntervalAnalysis_FullSystem_Base2WithTritoneAndPerfects()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 12);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); 		// 1/1
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 16/15
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 9/8
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 6/5
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 5/4
		intervalList.add(BASIC_INTERVAL.PERFECT); 	// 4/3
		intervalList.add(BASIC_INTERVAL.TRI_TONE); 	// 17/12
		intervalList.add(BASIC_INTERVAL.PERFECT); 	// 3/2
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 8/5
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 5/3
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 16/9
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 15/8
	
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A12 Interval Analysis, full system base 2 without tritone eqt(2, 11)
	@Test
	void A12_IntervalAnalysis_FullSystem_Base2WithoutTritone()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); 		// 1/1
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 15/4
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 8/7
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 6/5
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 9/7
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 11/8
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 16/11
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 14/9
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 5/3
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 7/4
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 15/8
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A13 Interval Analysis, full system base 2 with equal intervals eqt(2, 7)
	@Test
	void A13_IntervalAnalysis_FullSystem_Base2WithEqualIntervals()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 7);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); 		// 1/1
		intervalList.add(BASIC_INTERVAL.EQUAL); 		// 10/9
		intervalList.add(BASIC_INTERVAL.EQUAL); 		// 11/9
		// NOTE: 4/3 and 3/2 are perfects in another system
		// but they should still work as a major minor pair here
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 4/3
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 3/2
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 13/8
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 9/5
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A14 Interval Analysis, full system base 2 with tritone but not perfects eqt(2, 10)
	@Test
	void A14_IntervalAnalysis_FullSystem_Base2WithTritoneNoPerfects()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 10);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
		intervalList.add(BASIC_INTERVAL.TONIC); 		// 1/1
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 14/13
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 8/7
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 11/9
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 17/13
		intervalList.add(BASIC_INTERVAL.TRI_TONE); 	// 17/12
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 26/17
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 13/8
		intervalList.add(BASIC_INTERVAL.MAJOR); 		// 7/4
		intervalList.add(BASIC_INTERVAL.MINOR); 		// 13/7
		
		assertEquals(intervalList, interval.getAnalysis());
	}
	
	// A15 Interval Analysis, full system base 2 non-cyclic system (2, 6.283185307179586476925286766559) or 2*Pi
	// does not test the full system 
	@Test
	void A15_IntervalAnalysis_FullSystem_Base2NonCyclic()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 6.283185307179586476925286766559);
		List<Theory> theoryList = new ArrayList<Theory>();
		RatioAnalysis ratio = new RatioAnalysis();
		try 								{ ratio.make(system, null); } 
		catch (MissingTheoryException e)	{ fail(); }
		theoryList.add(ratio);
		
		BasicIntervalAnalysis interval = new BasicIntervalAnalysis();
		try 								{ interval.make(system, theoryList); }
		catch (MissingTheoryException e)	{ fail(); }
		
		List<BASIC_INTERVAL> intervalList = new ArrayList<BASIC_INTERVAL>();
	
		// not sure how this works yet... OR how much to measure
		// [TONIC, MINOR, MAJOR, MAJOR, MINOR, MAJOR, MINOR, MINOR, MAJOR, PERFECT, TONIC, PERFECT, MAJOR, MINOR, MAJOR, MINOR, MINOR, MAJOR, MAJOR, MINOR, TONIC, MINOR, MAJOR, PERFECT, TONIC, PERFECT, EQUAL, EQUAL, T
		// 1/1, 9/8, 5/4, 7/5, 14/9, 7/4, 25/13, 13/6, 12/5, 19/7, 6/2, 10/3, 15/4, 21/5, 14/3, 21/4, 29/5, 13/2, 22/3, 41/5, 18/2, 51/5, 34/3, 38/3, 28/2, 47/3, 35/2, 39/2, 44/2
	}
	
//	// temporary test, for perfects/imperfects
//	@Test
//	void IntervalAnalysis_PerfectImperfect_Test()
//	{
//		// 2, 8:  [1/1, ... 9/7, 17/12, 14/9 ...]
//		// 2, 12: [1/1, ... 4/3, 17/12, 3/2 ...]
//		// 2, 16: [1/1, ... 15/11, 17/12, 22/15 ...]
//		// 2, 20: [1/1, ... 11/8, 17/12, 16/11 ...]
//		// 2, 24: [1/1, ... 11/8, 17/12, 13/9 ...]
//		// 2, 28: [1/1, ... 11/8, 17/12, 13/9, ...]
//		// 2, 32: [1/1, ... 11/8, 17/12, 13/9, ...]				
//		// 2, 36: [1/1, ... 7/5, 17/12, 10/7, ...]
//		// 2, 40: [1/1, ... 7/5, 17/12, 10/7, ...]
//		// 2, 44: [1/1, ... 7/5, 17/12, 10/7, ...]
//		// 2, 48: [1/1, ... 7/5, 17/12, 10/7, ...]
//		// 2, 52: [1/1, ... 7/5, 17/12, 10/7, ...]
//		// 2, 56: [1/1, , 11/8, 7/5, 17/12, 10/7, 13/9, 16/11, 3/2, 3/2, 20/13, 14/9, 11/7, 11/7, 8/5, 13/8, 13/8, 5/3, 5/3, 12/7, 12/7, 7/4, 7/4, 9/5, 9/5, 11/6, 13/7, 15/8, 17/9, 21/11, 29/15, 45/23]
//		// 2, 60: [1/1, 11/8, 7/5, 17/12, 10/7, 13/9, 16/11, 22/15, 3/2, 26/17, 17/11, 14/9, 11/7, 8/5, 8/5, 13/8, 18/11, 5/3, 5/3, 12/7, 12/7, 7/4, 7/4, 16/9, 9/5, 11/6, 11/6, 13/7, 15/8, 19/10, 23/12, 31/16, 47/24]
//
//		
//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 20);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 24);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//		
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 28);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//		
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 32);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//		
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 36);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 40);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//	
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 44);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 48);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//		
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 52);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//		
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 56);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//
//		system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 60);
//		ratio = new RatioAnalysis(system);
//		ratio.make();
//		System.out.print(ratio.getAnalysis() + "\n");
//	}
	
//	// temporary test, for tritones
//	@Test
//	void A5_IntervalAnalysis_TriTones()
//	{
//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 12);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//		[1/1, 9/8, 5/4, 17/12, 8/5, 16/9, 4/2, 9/4, 5/2, 17/6, 16/5, 25/7]
		
//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 3, 6);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//		[1/1, 6/5, 10/7, 19/11, 21/10, 5/2]
		
//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 5, 10);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//		[1/1, 7/6, 11/8, 13/8, 17/9, 9/4, 21/8, 28/9, 18/5, 17/4]

//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 6, 12);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//      [1/1, 7/6, 19/14, 11/7, 9/5, 17/8, 17/7, 17/6, 10/3, 19/5, 31/7, 26/5]
		
//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 7, 14);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//		[1/1, 8/7, 4/3, 26/17, 7/4, 4/2, 16/7, 8/3, 46/15, 7/2, 8/2, 23/5, 16/3, 43/7]		
		
//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 8, 16);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//		[1/1, 8/7, 9/7, 22/15, 5/3, 19/10, 11/5, 5/2, 17/6, 13/4, 11/3, 21/5, 19/4, 27/5, 31/5, 14/2]

//		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 9, 18);
//		RatioAnalysis ratio = new RatioAnalysis(system);
//		ratio.make();
//		
//		IntervalAnalysis interval = new IntervalAnalysis(system, ratio);
//		[1/1, 9/8, 9/7, 10/7, 13/8, 11/6, 21/10, 7/3, 8/3, 6/2, 17/5, 19/5, 13/3, 34/7, 11/2, 25/4, 14/2, 16/2]
//	}
}
