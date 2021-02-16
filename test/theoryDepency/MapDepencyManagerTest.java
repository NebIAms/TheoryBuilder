package theoryDepency;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import theory.Theory;
import theory.analysis.ListAnalysis;
import theory.analysis.RatioAnalysis;
import theoryDependency.DepencyManager;
import theoryDependency.MapDependencyManager;
import theoryDependency.MissingTheoryException;
import tuningSystem.EqualTemperament;
import tuningSystem.GenericSystem;

class MapDepencyManagerTest
{

	// D1 Dependency Manager, get filled theory
	@Test
	public void D1_DepencyManager_GetFilledTheory()
	{
		// Make ratio analysis
		EqualTemperament eqt = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 2);
		RatioAnalysis ratio = new RatioAnalysis();
		
		try
		{
			ratio.make(eqt, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		// manager
		List<Theory> theoryList = new ArrayList<Theory>();
		theoryList.add(ratio);
		
		// mimics what a theory that is dependent on Ratio Analysis would do
		DepencyManager manager = new MapDependencyManager();
		manager.add(RatioAnalysis.class);
		manager.fill(theoryList);
		
		try
		{
			Theory depency = manager.get(RatioAnalysis.class);
			assertTrue(depency.checkMade());
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
	}
	
	// D2 Dependency Manager, get missing theory
	@Test
	public void D2_DepencyManager_GetMissingTheory()
	{
		// Make ratio analysis
		EqualTemperament eqt = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 2);
		RatioAnalysis ratio = new RatioAnalysis();
				
		try
		{
			ratio.make(eqt, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		// manager
		List<Theory> theoryList = new ArrayList<Theory>();
		theoryList.add(ratio);
		
		// mimics what a theory that is dependent on Ratio Analysis would do
		DepencyManager manager = new MapDependencyManager();
		manager.add(RatioAnalysis.class);
		
		// the theory is registered but not filled
		assertThrows(MissingTheoryException.class, () -> manager.get(RatioAnalysis.class));
		
		try
		{
			manager.get(RatioAnalysis.class);
		} 
		catch (MissingTheoryException e)
		{
			// Knows what theory is missing
			assertEquals(RatioAnalysis.class, e.getMissingTheory());
		}
	}
	
	// D3 Dependency Manager, get theory that was not added
	@Test
	public void D3_DepencyManager_GetNotAddedTheory()
	{
		// Make ratio analysis
		EqualTemperament eqt = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 2);
		RatioAnalysis ratio = new RatioAnalysis();
				
		try
		{
			ratio.make(eqt, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		// mimics what a theory that is dependent on Ratio Analysis would do
		DepencyManager manager = new MapDependencyManager();
		
		// the theory is registered but not filled
		assertThrows(MissingTheoryException.class, () -> manager.get(RatioAnalysis.class));
				
		try
		{
			manager.get(RatioAnalysis.class);
		} 
		catch (MissingTheoryException e)
		{
			// Knows what theory is missing
			assertEquals(RatioAnalysis.class, e.getMissingTheory());
		}
	}
	
	// D4 Dependency Manager, desired dependency is a superclass/interface to a dependency that is entered
	@Test
	public void D4_DepencyManager_AddIsSuperclass()
	{
		// Make ratio analysis
		EqualTemperament eqt = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 48, 440, 4, 2);
		RatioAnalysis ratio = new RatioAnalysis();
				
		try
		{
			ratio.make(eqt, null);
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
		
		// manager
		List<Theory> theoryList = new ArrayList<Theory>();
		theoryList.add(ratio);
		
		// mimics what a theory that is dependent on Ratio Analysis would do
		// List Analysis is an interface of ratioAnalysis
		DepencyManager manager = new MapDependencyManager();
		manager.add(ListAnalysis.class);
		manager.fill(theoryList);
		
		try
		{
			Theory dependency = manager.get(ListAnalysis.class);
			assertTrue(dependency.checkMade());
			assertEquals(RatioAnalysis.class, dependency.getClass());
		} 
		catch (MissingTheoryException e)
		{
			fail();
		}
	}
}
