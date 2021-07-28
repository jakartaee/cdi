package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.DeclarationInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * An annotation target is anything that can be annotated.
 * That is:
 *
 * <ul>
 * <li>a <i>declaration</i>, such as a class, method, field, etc. See {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo}.</li>
 * <li>a <i>type parameter</i>, occurring in class declarations and method declarations.</li>
 * <li>a <i>type use</i>, such as a type of method parameter, a type of field, a type argument, etc. See {@link jakarta.enterprise.lang.model.types.Type}.</li>
 * </ul>
 *
 * Annotations are represented as {@link AnnotationInfo}, so that implementations of this API are not required
 * to instantiate the annotation type.
 *
 * @since 4.0.0
 */
public interface AnnotationTarget {
    // TODO specify equals/hashCode (for the entire .lang.model hierarchy)
    // TODO we decided that we won't use Optional and will just return null; need to make this consistent everywhere
    // TODO what about declared vs not declared (inherited) annotations?
    //  probably should always return all, including inherited, that's what Portable Extensions do

    /**
     * Returns whether this annotation target is a {@link DeclarationInfo declaration}.
     *
     * @return {@code true} if this is a declaration, {@code false} otherwise
     * @see jakarta.enterprise.lang.model.declarations.DeclarationInfo
     */
    boolean isDeclaration();

    /**
     * Returns whether this annotation target is a {@link Type type}.
     *
     * @return {@code true} if this is a type, {@code false} otherwise
     * @see jakarta.enterprise.lang.model.types.Type
     */
    boolean isType();

    /**
     * Returns this annotation target as a {@link DeclarationInfo declaration}.
     *
     * @return this declaration, never {@code null}
     * @throws IllegalStateException if {@link #isDeclaration()} returns {@code false}
     */
    DeclarationInfo asDeclaration();

    /**
     * Returns this annotation target as a {@link Type type}.
     *
     * @return this type, never {@code null}
     * @throws IllegalStateException if {@link #isType()} returns {@code false}
     */
    Type asType();

    /**
     * Returns whether an annotation of given type is present on this annotation target.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return {@code true} if given annotation type is present on this annotation target, {@code false} otherwise
     */
    boolean hasAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Returns whether given predicate matches any annotation present on this annotation target.
     *
     * @param predicate annotation predicate, must not be {@code null}
     * @return {@code true} if given predicate matches any annotation present on this annotation target, {@code false} otherwise.
     */
    boolean hasAnnotation(Predicate<AnnotationInfo<?>> predicate);

    /**
     * Returns an annotation of given type, if it is present on this annotation target.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @param <T> the annotation generic type
     * @return the {@link AnnotationInfo} or {@code null} if no such annotation is present on this annotation target
     */
    <T extends Annotation> AnnotationInfo<T> annotation(Class<T> annotationType);

    /**
     * Returns a collection of annotations of given repeatable annotation type
     * (an annotation type that is meta-annotated {@link java.lang.annotation.Repeatable @Repeatable})
     * present on this annotation target. Returns an empty collection if no such
     * annotation is present.
     *
     * @param annotationType the {@code @Repeatable} annotation type, must not be {@code null}
     * @param <T> the annotation generic type
     * @return immutable collection of {@link AnnotationInfo}, never {@code null}
     */
    <T extends Annotation> Collection<AnnotationInfo<T>> repeatableAnnotation(Class<T> annotationType);

    /**
     * Returns a collection of all annotations present on this annotation target that match given predicate.
     * Returns an empty collection if no such annotation is present.
     *
     * @param predicate annotation predicate, must not be {@code null}
     * @return immutable collection of {@link AnnotationInfo}, never {@code null}
     */
    Collection<AnnotationInfo<?>> annotations(Predicate<AnnotationInfo<?>> predicate);

    /**
     * Returns a collection of all annotations present on this annotation target.
     * Returns an empty collection if no annotation is present.
     *
     * @return immutable collection of {@link AnnotationInfo}, never {@code null}
     */
    Collection<AnnotationInfo<?>> annotations();
}
