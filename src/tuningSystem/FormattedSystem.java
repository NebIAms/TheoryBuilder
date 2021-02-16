package tuningSystem;

/**
 * Container class that provides the center index, center frequency, base, and space between bases for formatted systems that follow 
 *  tones being grouped by a base, defined by a center index and frequency. (ie. equal temperament, well temperament, meantone temperament, etc.)
 * @author MT
 *
 */
public abstract class FormattedSystem extends GenericSystem
{
	protected int centerIndex;
	protected double centerFrequency;
	protected double base;
	protected double baseSpacing;
	
	public double getCenterFrequency() 	{ return centerFrequency; }
	public double getBase() 			{ return base; }
	public double getBaseSpacing()		{ return baseSpacing; }
	
	public void setCenterFrequency(double centerFrequency)	{ this.centerFrequency = centerFrequency; }
	public void setBase(double base) 						{ this.base = base; }
	public void setBaseSpacing(double spacing)				{ this.baseSpacing = spacing; }
	
	@Override
	public String toString()
	{
		return super.toString() + "\n    format(centerIndex: " + this.centerIndex + ", centerFrequency: " + this.centerFrequency + ", base: " + this.base + ", baseSpacing: " + this.baseSpacing + ")";	
	}
}
