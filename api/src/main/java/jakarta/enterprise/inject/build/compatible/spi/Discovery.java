package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1st phase of CDI Lite extension execution.
 * Allow registering additional classes to be scanned during bean discovery.
 * Also allows registering custom CDI meta-annotations.
 * <p>
 * Methods annotated {@code @Discovery} can define parameters of these types:
 * <ul>
 * <li>{@link ScannedClasses}: to register classes to be scanned during bean discovery</li>
 * <li>{@link MetaAnnotations}: to register custom meta-annotations:
 * qualifiers, interceptor bindings, stereotypes and scopes</li>
 * <li>{@link Messages}: to produce log messages and validation errors</li>
 * </ul>
 *
 * @since 4.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Discovery {
}
