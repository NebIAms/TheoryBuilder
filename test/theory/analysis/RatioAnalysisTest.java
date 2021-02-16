package theory.analysis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.FormattedSystem;
import tuningSystem.GenericSystem;

class RatioAnalysisTest {

	// A1 Ratio analysis one octave sample test
	@Test
	void A1_RatioAnalysis_OneOctaveSample()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 12);
		RatioAnalysis ratio = new RatioAnalysis();
		try 
		{
			ratio.make(system, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		List<Ratio> ratios = new ArrayList<Ratio>();
		ratios.add(new Ratio(1, 1));
		ratios.add(new Ratio(16, 15));
		ratios.add(new Ratio(9, 8));
		ratios.add(new Ratio(6, 5));
		ratios.add(new Ratio(5, 4));
		ratios.add(new Ratio(4, 3));
		ratios.add(new Ratio(17, 12));
		ratios.add(new Ratio(3, 2));
		ratios.add(new Ratio(8, 5));
		ratios.add(new Ratio(5, 3));
		ratios.add(new Ratio(16, 9));
		ratios.add(new Ratio(15, 8));

		assertEquals(ratios, ratio.getAnalysis());
	}
	
	// A2 Ratio analysis full range test
	@Test
	void A2_RatioAnalysis_FullRangeSample()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 7.5);
		RatioAnalysis ratio = new RatioAnalysis();
		try 
		{
			ratio.make(system, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		List<Ratio> ratios = new ArrayList<Ratio>();
		ratios.add(new Ratio(1, 1));
		ratios.add(new Ratio(11, 10));
		ratios.add(new Ratio(6, 5));
		ratios.add(new Ratio(17, 13));
		ratios.add(new Ratio(13, 9));
		ratios.add(new Ratio(8, 5));
		ratios.add(new Ratio(7, 4));
		ratios.add(new Ratio(19, 10));
		ratios.add(new Ratio(19, 9));
		ratios.add(new Ratio(16, 7));
		
		// Just compares a sample
		assertEquals(ratios, ratio.getAnalysis().subList(0, 10));
	}
	
	// A3 Ratio analysis dense octave sample test
	@Test
	void A3_RatioAnalysis_OneDenseOctaveSample()
	{
		FormattedSystem system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 69);
		RatioAnalysis ratio = new RatioAnalysis();
		try 
		{
			ratio.make(system, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		List<Entry<Integer, Integer>> ratios = new ArrayList<Entry<Integer, Integer>>();
		ratios.add(new Ratio(1, 1));
		ratios.add(new Ratio(2, 2));
		ratios.add(new Ratio(34, 33));
		ratios.add(new Ratio(26, 25));
		ratios.add(new Ratio(21, 20));
		ratios.add(new Ratio(18, 17));
		ratios.add(new Ratio(15, 14));
		ratios.add(new Ratio(13, 12));
		ratios.add(new Ratio(12, 11));
		ratios.add(new Ratio(11, 10));
		ratios.add(new Ratio(10, 9));
		ratios.add(new Ratio(9, 8));
		ratios.add(new Ratio(9, 8));
		
		// Just compares a sample
		assertEquals(ratios, ratio.getAnalysis().subList(0, 13));
	}

}
