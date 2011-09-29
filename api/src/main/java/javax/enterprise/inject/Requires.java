package javax.enterprise.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Veto the processing of the type if the required classes are not available.
 * Any beans defined by the type will not be installed.
 * </p>
 * 
 * <p>
 * When placed on package, all beans in the package are installed if only if 
 * all of required classes are available.
 * </p>
 * 
 * <p>
 * If annotation is defined both on the package and the bean, union of
 * required classes defined by these annotations is considered.
 * </p>
 * 
 * @author Stuart Douglas
 * @author Jozef Hartinger
 * 
 * @see Veto
 * 
 */
@Target({ ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Requires
{
   String[] value();
}