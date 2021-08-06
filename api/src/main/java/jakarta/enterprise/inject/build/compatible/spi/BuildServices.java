package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.inject.spi.Prioritized;

/**
 * Service facade for various services needed by {@link BuildCompatibleExtension} implementations.
 */
public interface BuildServices  extends Prioritized {
    /**
     * @return The {@link AnnotationBuilderFactory} instance, never {@code null}
     */
    AnnotationBuilderFactory annotationBuilderFactory();

    /**
     *
     * @return The {@link ClassInfoFactory} instance, never {@code null}
     */
    ClassInfoFactory classInfoFactory();
}
