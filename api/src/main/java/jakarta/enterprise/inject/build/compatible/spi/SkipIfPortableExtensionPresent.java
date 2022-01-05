package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.inject.spi.Extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If a {@linkplain BuildCompatibleExtension build compatible extension} is annotated
 * {@code @SkipIfPortableExtensionPresent}, it is ignored when the CDI container
 * can execute portable extensions and determines that a portable extension
 * of {@linkplain #value() given class} is present.
 * <p>
 * It is expected that the specified portable extension class will mirror the functionality
 * of the annotated build compatible extension. This allows portable extensions
 * and build compatible extensions to coexist.
 *
 * @since 4.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipIfPortableExtensionPresent {
    /**
     * A class implementing {@link Extension} that is expected to mirror the functionality of the annotated
     * build compatible extension.
     *
     * @return a portable extension class
     */
    Class<? extends Extension> value();
}
