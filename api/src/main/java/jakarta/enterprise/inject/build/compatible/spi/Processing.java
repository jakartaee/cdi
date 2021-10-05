package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 3rd phase of CDI Lite extension execution.
 * Allows processing registered beans and observers.
 * <p>
 * This phase is executed twice.
 * For non-synthetic beans and observer, this phase is executed <em>before</em> {@linkplain Synthesis synthesis}.
 * For synthetic beans and observers, this phase is executed <em>after</em> {@linkplain Synthesis synthesis}.
 * <p>
 * Methods annotated {@code @Processing} must define exactly one parameter of one of these types:
 * <ul>
 * <li>{@link BeanInfo}</li>
 * <li>{@link InterceptorInfo}</li>
 * <li>{@link ObserverInfo}</li>
 * </ul>
 * Note that interceptors are beans, and {@code InterceptorInfo} is a subtype of {@code BeanInfo}, so if the method
 * has a parameter of type {@code BeanInfo}, it will be called for interceptors as well.
 * <p>
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
// TODO add a way to _modify_ beans? at least veto-ing should be possible
//  (add BeanConfig and/or BeanAttributesConfig, similarly to Portable Extensions? or some other way?)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Processing {
}
