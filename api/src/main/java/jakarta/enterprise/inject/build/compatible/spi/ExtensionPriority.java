package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows specifying priority of extension methods.
 * <p>
 * Extension methods with smaller priority values are invoked first.
 * Extension methods without specified priority are treated as methods with priority {@code 10_000};
 * If two extension methods have equal priority, the ordering among them is undefined.
 *
 * @see BuildCompatibleExtension
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionPriority {
    // TODO propose allowing @Priority to be present on methods

    /**
     * @return the priority value
     */
    int value();
}
