package theory.structure.scale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import theory.Theory;
import theory.analysis.BasicIntervalAnalysis;
import theory.analysis.RatioAnalysis;
import theory.structure.ImproperStructureException;
import theory.structure.scale.MajorScale;
import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.GenericSystem;

class ScaleTest
{
	// T9 Scale, start next
	@Test
	public void T9_Scale_StartNext()
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
		
		testScale.start(40, 0);
		
		assertEquals(40, testScale.nextStep());
		assertEquals(42, testScale.nextStep());
	}
	
	// T10 Scale, start next then previous
	@Test
	public void T10_Scale_StartNextThenPrevious()
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
		
		testScale.start(40, 0);
		
		assertEquals(40, testScale.nextStep());
		assertEquals(42, testScale.nextStep());
		assertEquals(42, testScale.previousStep());
		assertEquals(40, testScale.previousStep());
	}
	
	// T11 Scale, start next across octaves
	@Test
	public void T11_Scale_StartNextAcrossOctaves()
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
		
		testScale.start(40, 0);
		
		assertEquals(40, testScale.nextStep());
		assertEquals(42, testScale.nextStep());
		assertEquals(44, testScale.nextStep());
		assertEquals(45, testScale.nextStep());
		assertEquals(47, testScale.nextStep());
		assertEquals(49, testScale.nextStep());
		assertEquals(51, testScale.nextStep());
		// Next octave start
		assertEquals(52, testScale.nextStep());
	}	
	
	// T12 Scale, start previous across octaves
	@Test
	public void T12_Scale_StartPreviousAcrossOctaves()
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
		
		testScale.start(40, 0);
		
		// previous across start
		// TODO: this is wrong, it should return 40, then 39
		assertEquals(40, testScale.previousStep());
		assertEquals(39, testScale.previousStep());
	}
	
	// T13 Scale, apply
	@Test
	public void T13_Scale_Apply()
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
	
	// T14 Scale, applyAt (full system)
	@Test
	public void T14_Scale_Apply()
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
		scale.add(10);
		scale.add(12);
		scale.add(14);
		scale.add(15);
		scale.add(17);
		scale.add(19);
		scale.add(21);
		
		assertEquals(scale, testScale.applyAt(10));
	}
	
	// T15 Scale, next scale limit (128)
	@Test
	public void T15_Scale_NextSystemLimit()
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
		
		testScale.start(127, 0);
		
		assertEquals(127, testScale.nextStep());
		assertThrows(IndexOutOfBoundsException.class, () -> testScale.nextStep());
	}
	
	// T16 Scale, previous scale limit (0)
	@Test
	public void T16_Scale_PreviousSystemLimit()
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
		
		testScale.start(0, 0);
		
		assertEquals(0, testScale.previousStep());
		assertThrows(IndexOutOfBoundsException.class, () -> testScale.previousStep());
	}
	
	// T17 Scale, applyAt upper scale limit
	@Test
	public void T17_Scale_ApplyAtUpperScaleLimit()
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
		
		assertThrows(IndexOutOfBoundsException.class, () -> testScale.applyAt(120));
	}
	
	// T18 Scale, applyAt lower scale limit
	@Test
	public void T18_Scale_ApplyAtLowerScaleLimit()
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
		
		assertThrows(IndexOutOfBoundsException.class, () -> testScale.applyAt(-5));
	}
	
	// T19 Scale, mode 0
	@Test
	public void T19_Scale_Mode0()
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
		List<Integer> mode0 = testScale.apply();
		
		testScale.mode(0).apply();
		
		assertEquals(mode0, testScale.mode(0).apply());
	}
	
	// T20 Scale, mode 1
	@Test
	public void T20_Scale_Mode1()
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
		scale.add(3);
		scale.add(5);
		scale.add(7);
		scale.add(9);
		scale.add(10);
		
		assertEquals(scale, testScale.mode(1).apply());
	}
	
	// T21 Scale, mode 6
	@Test
	public void T21_Scale_Mode6()
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
		scale.add(1);
		scale.add(3);
		scale.add(5);
		scale.add(6);
		scale.add(8);
		scale.add(10);
		
		assertEquals(scale, testScale.mode(6).apply());
	}
	
	// T22 Scale, mode 3 back to mode 0
	@Test
	public void T22_Scale_Mode3Mode0()
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
		scale.add(6);
		scale.add(7);
		scale.add(9);
		scale.add(11);
		
		assertEquals(scale, testScale.mode(3).apply());
		
		scale.clear();
		scale.add(0);
		scale.add(2);
		scale.add(4);
		scale.add(5);
		scale.add(7);
		scale.add(9);
		scale.add(11);
		
		assertEquals(scale, testScale.mode(3).mode(4).apply());
	}
	
	// T23 Scale, mode 7 out of bounds
	@Test
	public void T23_Scale_Mode7OutOfBounds()
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

		assertThrows(IndexOutOfBoundsException.class, () -> testScale.mode(7));
	}
	
	// T24 Scale, chord 0, 2 tones 
	@Test
	public void T24_Scale_Chord0Tones2()
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

		List<Integer> chord = new ArrayList<Integer>();		
		chord.add(0);
		chord.add(4);
		
		try
		{
			assertEquals(chord, testScale.chord(0, 2).apply());
		} 
		catch (ImproperStructureException e)
		{
			e.printStackTrace();
		}
	}
	
	// T25 Scale, chord 0, 3 tones
	@Test
	public void T25_Scale_Chord0Tones3()
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

		List<Integer> chord = new ArrayList<Integer>();		
		chord.add(0);
		chord.add(4);
		chord.add(7);
		
		try
		{
			assertEquals(chord, testScale.chord(0, 3).apply());
		} 
		catch (ImproperStructureException e)
		{
			e.printStackTrace();
		}
	}
			
	// T26 Scale, chord 0, 1 or 0 tones throws an exception
	@Test
	public void T26_Scale_Chord0Tones0Or1()
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
		
		assertThrows(ImproperStructureException.class, () -> testScale.chord(0, 1).apply());
		assertThrows(ImproperStructureException.class, () -> testScale.chord(0, 0).apply());
	}
	
	// T27 Scale, chord 2, 3 tones
	@Test
	public void T27_Scale_Chord2Tones3()
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
		
		List<Integer> chord = new ArrayList<Integer>();	
		chord.add(0);
		chord.add(3);
		chord.add(7);
		
		try
		{
			assertEquals(chord, testScale.chord(2, 3).apply());
		} 
		catch (ImproperStructureException e)
		{
			e.printStackTrace();
		}
	}
	
	// T28 Scale, chord 4, 4 tones (across octaves)
	@Test
	public void T28_Scale_Chord4Tones4()
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
		
		List<Integer> chord = new ArrayList<Integer>();	
		chord.add(0);
		chord.add(4);
		chord.add(7);
		chord.add(10);
		
		try
		{
			assertEquals(chord, testScale.chord(4, 4).apply());
		} 
		catch (ImproperStructureException e)
		{
			e.printStackTrace();
		}
	}
}
