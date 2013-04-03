package javax.enterprise.inject.spi;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * {@link WithAnnotations} may be applied to any portable extension observer method with an event parameter type of
 * {@link ProcessAnnotatedType} to filter the events delivered.
 * </p>
 * 
 * <p>
 * If the {@link WithAnnotations} annotation is applied to a portable extension observer method, then only
 * {@link ProcessAnnotatedType} events for types which have at least one of the annotations specified are observed. The
 * annotation can appear on the type, or any of it's supertypes, on any field, method or constructor declared by the type, or
 * any of its supertypes, or on any parameter of any method or constructor declared by the type, or any of it's supertypes or
 * may be applied as a meta-annotation.
 * </p>
 * 
 * @author Pete Muir
 * @since 1.1
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface WithAnnotations {

    Class<? extends Annotation>[] value();

}
