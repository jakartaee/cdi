package javax.enterprise.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Veto the processing of the type. Any beans or observer methods defined by
 * this class will not be installed.
 * 
 * When placed on package, all beans in the package are prevented from being
 * installed.
 * 
 * @author Stuart Douglas
 * 
 */
@Target({ ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Veto
{

}