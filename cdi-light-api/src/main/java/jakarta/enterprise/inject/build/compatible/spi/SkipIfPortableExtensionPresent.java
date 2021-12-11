package jakarta.enterprise.inject.build.compatible.spi;

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
     * Binary name of a portable extension class, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getName()}.
     * <p>
     * Non-portable behavior occurs if given class exists but is not a portable extension.
     *
     * @return binary name of a portable extension class
     */
    String value();
}
