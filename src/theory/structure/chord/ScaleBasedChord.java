package theory.structure.chord;

import java.util.List;

import theoryDependency.DepencyManager;
import theoryDependency.MapDependencyManager;
import theoryDependency.MissingTheoryException;
import theory.Theory;
import theory.structure.AbstractFormattedStructure;
import theory.structure.ImproperStructureException;
import theory.structure.scale.Scale;
import tuningSystem.FormattedSystem;

/**
 * This type of chord is created from a scale
 * It uses a starting index and a number of tones to define the chord structure
 * @author MT
 *
 */
public class ScaleBasedChord extends AbstractFormattedStructure implements Chord
{
	private int scaleIndex;
	private int numberOfTones;
	
	private boolean made;
	private DepencyManager depencies;
	
	/**
	 * Prepares making a chord
	 * @param startingIndex
	 * @param numberOfTones
	 * @dependency (dependencies = {Scale.class})
	 */
	public ScaleBasedChord(int scaleIndex, int numberOfTones)
	{
		this.scaleIndex = scaleIndex;
		this.numberOfTones = numberOfTones;
		this.made = false;
		this.depencies = new MapDependencyManager();
		depencies.add(Scale.class);
	}
	
	@Override
	public void make(FormattedSystem system, List<Theory> madeTheory) throws MissingTheoryException, ImproperStructureException
	{ 
		// inherited system
		this.system = system;
		
		// structure checking
		if (numberOfTones < 2)
		{
			throw new ImproperStructureException();
		}
		
		this.depencies.fill(madeTheory);
		Scale scale = (Scale) this.depencies.get(Scale.class);
				
		// starts the structure on the scale index
		scale.start(0, this.scaleIndex);
		
		boolean flip = true;
		int tones = 0;
		int i = scale.nextStep();
		int start = i;
		
		while (tones < numberOfTones)
		{
			// tone is included
			if (flip)
			{
				this.structure.add(i - start);		
				tones++;
				flip = false;
			}
			// tone is not included
			else
			{
				flip = true;
			}
			
			i = scale.nextStep();
		}
		
		this.made = true;
	}
	
	@Override
	public boolean checkMade()
	{
		return this.made;
	}

	@Override
	public String toString()
	{
		return "ScaleBasedChord(scaleIndex: " + this.scaleIndex + ", numberOfTones: " + numberOfTones + "): " + this.apply().toString();
	}
}
