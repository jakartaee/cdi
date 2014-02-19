package javax.enterprise.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Veto the processing of the class. Any beans or observer methods defined by this class will not be installed.
 * </p>
 * 
 * <p>
 * When placed on package, all beans in the package are prevented from being installed. If packages are split across jars,
 * non-portable behavior results. An application can prevent packages being split across jars by sealing the package.
 * </p>
 * 
 * <p>
 * No container lifecycle events are fired for classes annotated {@link Vetoed}.
 * </p>
 * 
 * @author Stuart Douglas
 * @since 1.1
 * @see <a href="http://download.java.net/jdk8/docs/technotes/guides/extensions/spec.html#sealing">The Extension Mechanism
 *      Architecture</a>
 * 
 */
@Target({ ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Vetoed {

}