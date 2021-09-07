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
// TODO this should be removed and @Priority should be used instead;
//  see https://github.com/eclipse-ee4j/common-annotations-api/pull/87
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionPriority {
    /**
     * @return the priority value
     */
    int value();
}
