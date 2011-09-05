package javax.enterprise.context.lifecycle;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * The <code>@Application</code> qualifier.
 * </p>
 * 
 * <p>
 * CDI will fire an event with qualifiers <code>@Application</code> <code>@Initialized</code> when
 * the application context is initialized and an event with qualifiers <code>@Application</code>
 * <code>@Destroyed</code> is fired when the application is destroyed. The event type is:
 * </p>
 * 
 * <ul>
 * <li>{@link javax.servlet.ServletContextEvent} if the application is a web application using
 * deployed to a Servlet container, or</li>
 * <li>{@link Object} in other types of application</li>
 * </ul>
 * 
 * @author Pete Muir
 * @see Initialized
 * @see Destroyed
 * 
 */
@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Application {

}
