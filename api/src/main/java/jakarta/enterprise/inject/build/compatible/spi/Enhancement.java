package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2nd phase of CDI Lite extension processing.
 * Allows transforming annotations.
 * <p>
 * Methods annotated {@code @Enhancement} must define exactly one parameter of one of these types:
 * <ul>
 * <li>{@link ClassConfig ClassConfig}</li>
 * <li>{@link MethodConfig MethodConfig}</li>
 * <li>{@link FieldConfig FieldConfig}</li>
 * <li>{@link AppArchiveConfig AppArchiveConfig}</li>
 * </ul>
 * If the parameter is {@code ClassConfig}, {@code MethodConfig} or {@code FieldConfig}, the method
 * must have at least one annotation {@link ExactType @ExactType} or {@link SubtypesOf @SubtypesOf}.
 * <p>
 * You can also declare a parameter of type {@link Messages Messages} to produce log messages and validation errors.
 * <p>
 * If you need to create instances of {@link jakarta.enterprise.lang.model.types.Type Type}, you can also declare
 * a parameter of type {@link Types Types}. It provides factory methods for the void type, primitive types,
 * class types, array types, parameterized types and wildcard types.
 * <p>
 * If you need to create instances of {@link jakarta.enterprise.lang.model.AnnotationAttribute AnnotationAttribute} or
 * {@link jakarta.enterprise.lang.model.AnnotationMember AnnotationAttributeValue}, you can also declare
 * a parameter of type {@link Annotations Annotations}. It provides factory methods for all kinds of annotation attributes.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enhancement {
}
