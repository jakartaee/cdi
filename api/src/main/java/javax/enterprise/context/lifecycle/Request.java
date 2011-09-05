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
 * The <code>@Request</code> qualifier.
 * </p>
 * 
 * <ul>
 * <li>
 * {@link javax.servlet.ServletRequestEvent} if the context is initialized or destroyed due to a
 * servlet request, or</li>
 * <li>
 * {@link javax.servlet.ServletRequestEvent} if the context is initialized or destroyed due to a web
 * service invocation, or</li>
 * <li>
 * <code>???</code> if the context is initialized or destroyed due to a EJB remote method
 * invocation, asynchronous method invocation, timeout or message delivery, or</li>
 * <li>
 * <code>???</code> if the context is initialized or destroyed due to a message delivery to a
 * {@link javax.jms.MessageListener}.</li>
 * </ul>
 * 
 * @author Pete Muir
 * @see Initialized
 * @see Destroyed
 */
@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Request {

}
