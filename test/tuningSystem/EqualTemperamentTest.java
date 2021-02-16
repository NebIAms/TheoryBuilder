package tuningSystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EqualTemperamentTest 
{

	// S1 Equal Temperament creation process
	@Test
	void S1_EqualTemperament_Create() 
	{
		TuningSystem eqt = new EqualTemperament(GenericSystem.TuningSystemSize.MIDI.getSize(), 69, 440, 2, 12);
		
		// Proof test
		double spacing = Math.pow(2.0, 1.0/12.0);
		
		for (int i = 0; i < (eqt.getSystemMap().size() - 1); i++)
		{
			assertEquals(spacing, eqt.getSystemMap().get(i + 1) / eqt.getSystemMap().get(i), 0.001);
		}
	}

}
