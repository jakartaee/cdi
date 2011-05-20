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
 * If the annotation that declares this meta-annotation is applied to an interface or abstract class then the container will
 * automatically provide a concrete implementation of the class/interface, and delegate all calls to abstract methods to the
 * handler class specified by these annotations.
 * </p>
 * 
 * <p>
 * Example of a {@link ServiceHandlerBinding} annotation:
 * </p>
 * 
 * <pre>
 * &#064;Retention(RUNTIME)
 * &#064;Target({ TYPE, METHOD })
 * &#064;ServiceHandlerBindingType
 * public &#064;interface EchoService {
 * }
 * </pre>
 * 
 * @see javax.servicehandler.ServiceHandler javadoc for an example of implementation of this binding type
 * 
 * @author Stuart Douglas <stuart.w.douglas@gmail.com>
 * @author George Gastaldi <gegastaldi@gmail.com>
 */

@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
@Documented
public @interface ServiceHandlerBinding {
}
