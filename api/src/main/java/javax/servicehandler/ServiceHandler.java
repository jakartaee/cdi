package javax.servicehandler;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specifies that a class is a service handler.
 * 
 * @author George Gastaldi <gegastaldi@gmail.com>
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface ServiceHandler {
}
