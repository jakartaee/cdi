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
 * The <code>@Session</code> qualifier.
 * </p>
 * 
 * <p>
 * An event of type {@link javax.servlet.http.HttpSessionEvent} and with qualifiers
 * <code>@Session</code> <code>@Initialized</code> is fired when the session context is initialized
 * and an event with qualifiers <code>@Session</code> <code>@Destroyed</code> when the session
 * context is destroyed.
 * </p>
 * 
 * @author Pete Muir
 * @see Initialized
 * @see Destroyed
 */
@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Session {

}
