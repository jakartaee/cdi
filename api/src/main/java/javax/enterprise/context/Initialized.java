package javax.enterprise.context;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * <p>
 * The <code>@Initialized</code> qualifier.
 * </p>
 * 
 * @author Pete Muir
 * @see Destroyed
 * @since 1.1
 */
@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Initialized {

    /**
     * The scope for which to observe initialization
     */
    Class<? extends Annotation> value();

}
