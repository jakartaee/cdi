package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

import java.lang.annotation.Annotation;

/**
 * Service provider interface that supports creating {@link AnnotationBuilder}.
 * Should not be called directly by users; the static methods on {@link AnnotationBuilder} are preferred.
 */
public interface AnnotationBuilderFactory {
    /**
     * Returns a new {@link AnnotationBuilder} for given annotation type.
     *
     * @param annotationType the annotation type
     * @return a new {@link AnnotationBuilder}
     */
    AnnotationBuilder create(Class<? extends Annotation> annotationType);

    /**
     * Returns a new {@link AnnotationBuilder} for given annotation type.
     *
     * @param annotationType the annotation type
     * @return a new {@link AnnotationBuilder}
     */
    AnnotationBuilder create(ClassInfo annotationType);
}
