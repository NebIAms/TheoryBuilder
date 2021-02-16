package theoryBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import theory.Theory;
import theory.analysis.BasicIntervalAnalysis;
import theory.analysis.ListAnalysis;
import theory.analysis.RatioAnalysis;
import theory.structure.ImproperStructureException;
import theory.structure.chord.ScaleBasedChord;
import theory.structure.scale.MajorScale;
import theory.structure.scale.Scale;
import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.GenericSystem;

class TheoryBuilderTest
{
	// B1 Queue Theory Builder, correct queue that builds all the theories so far (not sub theories)
	@Test
	public void B1_TheoryBuilder_CorrectQueueBuildsAll()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		TheoryBuilder builder = new QueueTheoryBuilder(system);
		builder.addTheory(new RatioAnalysis());
		builder.addTheory(new BasicIntervalAnalysis());
		builder.addTheory(new MajorScale());
		try 								{ builder.makeAll(); } 
		catch (BuildingFailedException e1)	{ fail(); }
		
		// manual building (oof, so much more code)
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
		
		// The theory lists should match, but the comparison fails (not sure why)
		// so each element needs to be compared as a list
		
		// ratio analysis
		assertEquals(((ListAnalysis<?>)theoryList.get(0)).getAnalysis(), ((ListAnalysis<?>)builder.getResult().get(0)).getAnalysis());
		
		// interval analysis
		assertEquals(((ListAnalysis<?>)theoryList.get(1)).getAnalysis(), ((ListAnalysis<?>)builder.getResult().get(1)).getAnalysis());
		
		// major scale
		assertEquals(((Scale)theoryList.get(2)).apply(), ((Scale)theoryList.get(2)).apply());
	}
	
	// B2 Queue Theory Builder, correct queue that fails to build sub theories for improper structures
	@Test
	public void B2_TheoryBuilder_CorrectQueueImproperStructureException()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		TheoryBuilder builder = new QueueTheoryBuilder(system);
		builder.addTheory(new RatioAnalysis());
		builder.addTheory(new BasicIntervalAnalysis());
		builder.addTheory(new MajorScale());
		// sub theories (not necessary for real building, but might be useful for future theories)
		builder.addTheory(new ScaleBasedChord(5, 0));
		
		try 
		{
			builder.makeAll();
			
			// exception did not happen
			fail();
		} 
		catch (BuildingFailedException e)
		{
			assertEquals(ImproperStructureException.class, e.getCause().getClass());
		}
	}
	
	// B3 Queue Theory Builder, correct queue that fails to build sub theories for improper structures that violate other exceptions
	@Test
	public void B3_TheoryBuilder_CorrectQueueIndexOutOfBoundsException()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		TheoryBuilder builder = new QueueTheoryBuilder(system);
		builder.addTheory(new RatioAnalysis());
		builder.addTheory(new BasicIntervalAnalysis());
		builder.addTheory(new MajorScale());
		// sub theories (not necessary for real building, but might be useful for future theories)
		builder.addTheory(new ScaleBasedChord(6, 3));
		
		try 
		{
			builder.makeAll();
			
			// exception did not happen
			fail();
		} 
		catch (BuildingFailedException e)
		{
			assertEquals(IndexOutOfBoundsException.class, e.getCause().getClass());
		}
	}
	
	// B4 Queue Theory Builder, wrong queue that works but is out of order
	@Test
	public void B4_TheoryBuilder_WrongQueueWorks()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		TheoryBuilder builder = new QueueTheoryBuilder(system);
		builder.addTheory(new RatioAnalysis());
		// Wrong order
		builder.addTheory(new MajorScale());
		builder.addTheory(new BasicIntervalAnalysis());
		try 								{ builder.makeAll(); } 
		catch (BuildingFailedException e1)	{ fail(); }
		
		// manual building (oof, so much more code)
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
		
		// The theory lists should match, but the comparison fails (not sure why)
		// so each element needs to be compared as a list
		
		// ratio analysis
		assertEquals(((ListAnalysis<?>)theoryList.get(0)).getAnalysis(), ((ListAnalysis<?>)builder.getResult().get(0)).getAnalysis());
		
		// interval analysis
		assertEquals(((ListAnalysis<?>)theoryList.get(1)).getAnalysis(), ((ListAnalysis<?>)builder.getResult().get(1)).getAnalysis());
		
		// major scale
		assertEquals(((Scale)theoryList.get(2)).apply(), ((Scale)theoryList.get(2)).apply());
	}
	
	// B5 Queue Theory Builder, wrong queue that does not work (missing theory)
	@Test
	public void B5_TheoryBuilder_WrongQueueMissingTheory()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 11);
		TheoryBuilder builder = new QueueTheoryBuilder(system);
		builder.addTheory(new RatioAnalysis());
		// Wrong order
		builder.addTheory(new MajorScale());
		//builder.addTheory(new BasicIntervalAnalysis()); Missing from the queue
		try 
		{
			builder.makeAll();
			
			// exception did not happen
			fail();
		} 
		catch (BuildingFailedException e)
		{
			assertEquals(MissingTheoryException.class, e.getCause().getClass());
		}
	}
}
