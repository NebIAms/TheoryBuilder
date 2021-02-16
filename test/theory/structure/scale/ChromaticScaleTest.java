package theory.structure.scale;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import theory.Theory;
import theory.structure.ImproperStructureException;
import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.GenericSystem;

class ChromaticScaleTest
{
	// T29 Chromatic scale, full system that cycles
	@Test
	void T29_ChromaticScale_FullSystemThatCycles()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 7);
		List<Theory> theoryList = new ArrayList<Theory>();
		// Chromatic scales have no requirements
		ChromaticScale scale = new ChromaticScale();
		try 
		{
			scale.make(system, theoryList);
		} 
		catch (IndexOutOfBoundsException | MissingTheoryException | ImproperStructureException e)
		{
			fail();
		}
		
		// TODO: test scale traveling
		scale.start(0, 0);
		assertEquals(0, scale.nextStep());
		assertEquals(1, scale.nextStep());
		assertEquals(2, scale.nextStep());
		assertEquals(3, scale.nextStep());
		assertEquals(4, scale.nextStep());
		assertEquals(5, scale.nextStep());
	}
	
	// T30 Chromatic scale, full system that does not cycle
	@Test
	void T30_ChromaticScale_FullSystemThatDoNotCycle()
	{
		EqualTemperament system = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 2, 7.5);
		List<Theory> theoryList = new ArrayList<Theory>();
		// Chromatic scales have no requirements
		ChromaticScale scale = new ChromaticScale();
		try 
		{
			scale.make(system, theoryList);
		} 
		catch (IndexOutOfBoundsException | MissingTheoryException | ImproperStructureException e)
		{
			fail();
		}
		
		// TODO: test scale traveling
		scale.start(0, 0);
		assertEquals(0, scale.nextStep());
		assertEquals(1, scale.nextStep());
		assertEquals(2, scale.nextStep());
		assertEquals(3, scale.nextStep());
		assertEquals(4, scale.nextStep());
		assertEquals(5, scale.nextStep());
	}
}
