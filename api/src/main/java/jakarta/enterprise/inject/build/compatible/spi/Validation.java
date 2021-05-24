package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 5th phase of CDI Lite extension processing.
 * Allows custom validation.
 * <p>
 * Methods annotated {@code @Validation} can define parameters of these types:
 * <ul>
 * <li>{@link AppArchive}: to find declarations in the application</li>
 * <li>{@link AppDeployment}: to find beans and observers in the application</li>
 * <li>{@link Messages}: to produce log messages and validation errors</li>
 * </ul>
 * If you need to create instances of {@link jakarta.enterprise.lang.model.types.Type Type}, you can also declare
 * a parameter of type {@link Types}. It provides factory methods for the void type, primitive types, class types,
 * array types, parameterized types and wildcard types.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {
}
