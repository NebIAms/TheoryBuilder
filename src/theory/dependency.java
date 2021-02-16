package theory;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describes the dependencies needed to construct a theory
 * Useful when adding to a builder.
 * @author MT
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.CONSTRUCTOR)
@Documented
public @interface dependency
{
	public Class<?>[] dependencies();
}
