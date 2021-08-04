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
 * <li>{@link DeclarationConfig} or {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo DeclarationInfo}</li>
 * <li>{@link ClassConfig} or {@link jakarta.enterprise.lang.model.declarations.ClassInfo ClassInfo}</li>
 * <li>{@link MethodConfig} or {@link jakarta.enterprise.lang.model.declarations.MethodInfo MethodInfo}</li>
 * <li>{@link FieldConfig} or {@link jakarta.enterprise.lang.model.declarations.FieldInfo FieldInfo}</li>
 * </ul>
 * The method must also have at least one annotation {@link ExactType @ExactType} or {@link SubtypesOf @SubtypesOf}.
 * <p>
 * You can also declare a parameter of type {@link Messages Messages} to produce log messages and validation errors.
 * <p>
 * If you need to create instances of {@link jakarta.enterprise.lang.model.types.Type Type}, you can also declare
 * a parameter of type {@link Types}. It provides factory methods for the void type, primitive types,
 * class types, array types, parameterized types and wildcard types.
 * <p>
 * If you need to create instances of {@link jakarta.enterprise.lang.model.AnnotationInfo AnnotationInfo},
 * use {@link AnnotationBuilder}.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enhancement {
}
