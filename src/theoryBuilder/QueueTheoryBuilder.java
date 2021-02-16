package theoryBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import theory.Theory;
import theory.structure.ImproperStructureException;
import theoryDependency.MissingTheoryException;
import tuningSystem.FormattedSystem;

/**
 * This type of theory builder uses a queue to order making the system
 * The handling of the queue is rather simple
 * @author MT
 *
 */
public class QueueTheoryBuilder implements TheoryBuilder
{
	private FormattedSystem system;
	private Queue<Theory> theoryQueue;
	private List<Theory> madeTheoryList;
	
	/**
	 * Prepares for adding theories and making
	 * @param system to build the theory for
	 */
	public QueueTheoryBuilder(FormattedSystem system)
	{
		this.system = system;
		this.theoryQueue = new LinkedList<Theory>();
		this.madeTheoryList = new ArrayList<Theory>();
	}
	
	@Override
	public void addTheory(Theory theory)
	{
		theoryQueue.add(theory);
	}

	@Override
	public void makeAll() throws BuildingFailedException
	{
		// assumes the order is correct
		// TODO: test with error states
		
		Set<Theory> failedTheories = new HashSet<Theory>();
		
		while (!theoryQueue.isEmpty())
		{
			Theory currentTheory = theoryQueue.poll();;
			try
			{
				currentTheory.make(this.system, this.madeTheoryList);
				this.madeTheoryList.add(currentTheory);
			} 
			catch (MissingTheoryException e1)
			{
				
				// if the theory is added twice there is no way it can be made
				if (failedTheories.add(currentTheory))
				{
					// tries to make the theory again
					theoryQueue.add(currentTheory);
				}
				else
				{
					// Theory cannot be built
					throw new BuildingFailedException("Building Failed: ", e1);
				}
			}
			catch (ImproperStructureException | IndexOutOfBoundsException e2)
			{
				throw new BuildingFailedException("Building failed: ", e2);
			}
		}
	}

	@Override
	public List<Theory> getResult()
	{
		return this.madeTheoryList;
	}
}
