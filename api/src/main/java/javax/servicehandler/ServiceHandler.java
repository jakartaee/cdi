package javax.servicehandler;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Specifies that a class is a service handler. The handler class must have a method with the following signature:
 * </p>
 * 
 * <pre>
 *    &#64;javax.interceptor.AroundInvoke public Object handle(final javax.interceptor.InvocationContext invocation) throws Exception
 * </pre>
 * 
 * <p>
 * In other words, it must have a method with a name following the Java Naming Conventions as specified by the language with an
 * annotation {@link javax.interceptor.AroundInvoke} and a single parameter of type {@link javax.interceptor.InvocationContext}.
 * </p>
 * 
 * <p>
 * {@link javax.interceptor.InvocationContext#proceed()} should not be called, as a {@link ServiceHandler} deals with abstract
 * methods only. As per the spec, concrete methods on an abstract class are not handled by Service Handlers.
 * </p>
 * 
 * <p>
 * Example of a {@link ServiceHandler} class:
 * </p>
 * 
 * <pre>
 * 
 * &#064;ServiceHandler
 * &#064;EchoService
 * public class EchoServiceHandler {
 *     &#064;AroundInvoke
 *     public Object handle(InvocationContext ctx) {
 *         return ctx.getMethod().getName().toString();
 *     }
 * }
 * </pre>
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;EchoService
 * public interface HelloWorld {
 *     String helloWorld();
 * }
 * </pre>
 * 
 * @see javax.interceptor.InvocationContext
 * 
 * @author George Gastaldi <gegastaldi@gmail.com>
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface ServiceHandler {
}
