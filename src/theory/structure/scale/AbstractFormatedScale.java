package theory.structure.scale;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import theory.Theory;
import theory.structure.AbstractFormattedStructure;
import theory.structure.ImproperStructureException;
import theory.structure.chord.Chord;
import theory.structure.chord.ScaleBasedChord;
import theoryDependency.MissingTheoryException;

/**
 * This abstract class provides the implementation for the scale interface
 * Extensions of this class will provide a method to make the scale.
 * @author MT
 *
 */
public abstract class AbstractFormatedScale extends AbstractFormattedStructure implements Scale
{
	private ListIterator<Integer> iterator;
	private int startingIndex;
	int scaleSize;
	
	@Override
	public void start(int startingIndex, int scaleIndex) throws IndexOutOfBoundsException
	{
		// Checks if the starting index fits the system
		if ((startingIndex >= 0) && (startingIndex < system.getSize()))
		{
			this.startingIndex = startingIndex;
		}
		else 
		{
			throw new IndexOutOfBoundsException(startingIndex);
		}
	
		// The acceptable range for list iterators is (index < 0 || index > size())
		// this range is a problem when index == size.
		if ((scaleIndex >= 0) && (scaleIndex < structure.size()))
		{
			this.iterator = structure.listIterator(scaleIndex);
		}
		else
		{
			throw new IndexOutOfBoundsException(scaleIndex);
		}
	}

	@Override
	public int nextStep() throws NoSuchElementException
	{ 
		int step;
		
		if (this.iterator.hasNext())
		{
			step = this.iterator.next() + this.startingIndex;
		}
		else
		{
			// Start the iterator normally 
			this.iterator = structure.listIterator();
			
			// move up the starting index to the next base size
			this.startingIndex += this.scaleSize;
			step = this.iterator.next() + this.startingIndex;
		}
	
		return super.checkIndex(step);
	}

	@Override
	public int previousStep() throws NoSuchElementException
	{
		int step;
		
		if (this.iterator.hasPrevious())
		{
			step = this.iterator.previous() + this.startingIndex;
		}
		else
		{
			// Start the iterator at the top
			this.iterator = this.structure.listIterator(this.structure.size());
			step = this.startingIndex;
			
			// move down the starting index to the next base size
			this.startingIndex -= this.scaleSize;
		}
		
		return super.checkIndex(step);
	}
	
	@Override
	public Scale mode(int mode)
	{
		// Makes a mode
		Mode tempMode = new Mode(mode);
		List<Theory> theoryList = new ArrayList<Theory>();
		theoryList.add(this);
		
		try 
		{
			tempMode.make(system, theoryList);
		} 
		catch (MissingTheoryException e)
		{
			// should be unreachable
			e.printStackTrace();
		}
		return tempMode;
	}
	
	@Override
	public Chord chord(int structureIndex, int numberOfTones) throws ImproperStructureException
	{
		Chord chord = null;
		try
		{
			// makes a chord
			chord = new ScaleBasedChord(structureIndex, numberOfTones);
			
			List<Theory> theoryList = new ArrayList<Theory>();
			theoryList.add(this);
			
			chord.make(system, theoryList);
		}
		catch (ImproperStructureException e)
		{
			throw e;
		} 
		catch (MissingTheoryException e)
		{
			// should be unreachable
			e.printStackTrace();
		}
		
		return chord;
	}
	
	@Override
	public String toString()
	{
		return "Scale: " + this.apply().toString();
	}
}
