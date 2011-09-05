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
 * The <code>@Conversation</code> qualifier.
 * </p>
 * 
 * <p>
 * CDI will fire an event of type {@link javax.faces.context.FacesContext} and with qualifiers
 * <code>@Conversation</code> <code>@Initialized</code> when the conversation context is initialized
 * and an event with qualifiers <code>@Conversation</code> <code>@Destroyed</code> when the
 * conversation context is destroyed.
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
public @interface Conversation {

}
