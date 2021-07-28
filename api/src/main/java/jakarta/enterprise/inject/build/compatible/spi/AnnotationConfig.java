package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

// TODO better name?
public interface AnnotationConfig {
    void addAnnotation(Class<? extends Annotation> annotationType); // for marker annotations

    void addAnnotation(AnnotationInfo annotation);

    void addAnnotation(Annotation annotation);

    void removeAnnotation(Predicate<AnnotationInfo> predicate);

    void removeAllAnnotations();
}
