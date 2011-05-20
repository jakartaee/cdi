package javax.servicehandler;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Meta annotation that is used to specify an invocation handler for automatically implemented bean.
 * </p>
 * 
 * <p>
 * If the annotation that is annotated with this meta-annotation is applied to an interface or abstract class then the container
 * will automatically provide a concrete implementation of the class/interface, and delegate all calls to abstract methods to
 * the handler class specified by this annotations.
 * </p>
 * 
 * <p>
 * The handler class must have a method with the following signature:
 * </p>
 * 
 * <pre>
 *    &#64;AroundInvoke public Object aroundInvoke(final InvocationContext invocation) throws Exception
 * </pre>
 * 
 * <p>
 * Initializer methods and <code>&#64;PostConstruct</code> methods declared on the invocation handler will be called, however
 * <code>&#64;PreDestory</code> methods will not be called.
 * </p>
 * 
 * <p>
 * The annotation should have:
 * </p>
 * 
 * <pre>
 * &#64;Retention(RUNTIME)
 * &#64;Target({ TYPE })
 * </pre>
 * 
 * @author Stuart Douglas <stuart.w.douglas@gmail.com>
 * @author George Gastaldi <gegastaldi@gmail.com>
 */

@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
@Documented
public @interface ServiceHandlerBinding {
}
