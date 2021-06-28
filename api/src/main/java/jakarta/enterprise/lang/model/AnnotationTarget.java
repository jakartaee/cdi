package jakarta.enterprise.lang.model;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Annotation target is anything that can be annotated.
 * That is:
 *
 * <ul>
 * <li>a <i>declaration</i>, such as a class, method, field, etc.</li>
 * <li>a <i>type parameter</i>, occurring in class declarations and method declarations</li>
 * <li>a <i>type use</i>, such as a type of method parameter, a type of field, a type argument, etc.</li>
 * </ul>
 */
public interface AnnotationTarget {
    // TODO specify equals/hashCode (for the entire .lang.model hierarchy)
    // TODO settle on using Optional everywhere, or allowing null everywhere; it's a mix right now

    boolean hasAnnotation(Class<? extends Annotation> annotationType);

    boolean hasAnnotation(Predicate<AnnotationInfo> predicate);

    // TODO what if missing?
    AnnotationInfo annotation(Class<? extends Annotation> annotationType);

    Collection<AnnotationInfo> repeatableAnnotation(Class<? extends Annotation> annotationType);

    Collection<AnnotationInfo> annotations(Predicate<AnnotationInfo> predicate);

    Collection<AnnotationInfo> annotations();
}
