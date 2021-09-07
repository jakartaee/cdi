package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 3rd phase of CDI Lite extension processing.
 * Allows processing registered beans and observers.
 * Note that synthetic beans and observers, registered in {@link Synthesis @Synthesis}, will <i>not</i> be processed.
 * <p>
 * Methods annotated {@code @Processing} must define exactly one parameter of one of these types:
 * <ul>
 * <li>{@link BeanInfo BeanInfo}</li>
 * <li>{@link ObserverInfo ObserverInfo}</li>
 * </ul>
 * The method must also have at least one annotation {@link ExactType @ExactType} or {@link SubtypesOf @SubtypesOf}.
 * <p>
 * You can also declare a parameter of type {@link Messages Messages} to produce log messages and validation errors.
 * <p>
 * If you need to create instances of {@link jakarta.enterprise.lang.model.types.Type Type}, you can also declare
 * a parameter of type {@link Types}. It provides factory methods for the void pseudo-type, primitive types,
 * class types, array types, parameterized types and wildcard types.
 *
 * @since 4.0
 */
// TODO allow @Processing methods to be executed even for synthetic components? that would break the nice
//  sequential model, but being able to observe synthetic components is important
// TODO add a way to _modify_ beans? at least veto-ing should be possible
//  (add BeanConfig extending BeanInfo? or some other way?)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Processing {
}
