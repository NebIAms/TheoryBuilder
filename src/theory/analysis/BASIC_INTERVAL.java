package theory.analysis;

/**
 * This basic type of interval identifies specific patterns from ratio analysis lists. <br>
 * @author MT
 * 
 */
public enum BASIC_INTERVAL 
{
	/** 
	 * The starting interval and any interval that has a whole number ratio result (ie. 2/1, 4/2)
	 * False tonics can exist where the actual number is extremely close to a whole number ratio.
	 * Included in system Major scales. <br>
	 */
	TONIC,
	/**
	 * The middle interval of even numbered systems, 
	 * usually is more complex than most intervals in a system.
 	 * Not fully sure how these intervals work.
	 */
	TRI_TONE,
	/**
	 * The less complex counterpart to a Minor interval in a major minor interval pair.
	 * Included in system major scales.
	 */
	MAJOR, 
	/**
	 * The more complex counterpart to a Major interval in a major minor interval pair. 
	 */
	MINOR, 
	/**
	 * Where two intervals that could be a major minor pair instead have the same ratio complexity.
	 * May be included in system major scales.
	 */
	EQUAL, 
	/**
	 * Forms a pair around a tritone that have less ratio complexity than the tritone.
	 */
	PERFECT, 
	/**
	 * Forms a pair around a tritone that have more ratio complexity than the tritone.
	 * Not fully sure how these intervals work.
	 */
	IMPERFECT;
}
