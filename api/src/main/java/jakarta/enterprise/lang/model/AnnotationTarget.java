package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.DeclarationInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * An Annotation target is anything that can be annotated.
 * That is:
 *
 * <ul>
 * <li>a <i>declaration</i>, such as a class, method, field, etc. See {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo}.</li>
 * <li>a <i>type parameter</i>, occurring in class declarations and method declarations.</li>
 * <li>a <i>type use</i>, such as a type of method parameter, a type of field, a type argument, etc. See {@link jakarta.enterprise.lang.model.types.Type}.</li>
 * </ul>
 *
 *
 * @since 4.0.0
 */
public interface AnnotationTarget {
    // TODO specify equals/hashCode (for the entire .lang.model hierarchy)
    // TODO settle on using Optional everywhere, or allowing null everywhere; it's a mix right now
    // TODO what about declared vs not declared annotations?

    /**
     * Returns whether this annotation target a {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo}.
     *
     * @see jakarta.enterprise.lang.model.declarations.DeclarationInfo
     * @return Will return {@code true} if it is a {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo} and {@code false} otherwise
     */
    boolean isDeclaration();

    /**
     * Returns whether this annotation target is a {@link jakarta.enterprise.lang.model.types.Type}.
     * @return Will return {@code true} if it is a {@link jakarta.enterprise.lang.model.types.Type} and {@code false} otherwise
     */
    boolean isType();

    /**
     * Coerce this annotation target to a {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo}.
     * @return The {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo} instance, never {@code null}
     * @throws java.lang.IllegalStateException If {@link #isDeclaration()} returns {@code false}
     */
    DeclarationInfo asDeclaration();

    /**
     * Coerce this annotation target to a {@link jakarta.enterprise.lang.model.types.Type}.
     * @return The {@link jakarta.enterprise.lang.model.types.Type} instance, never {@code null}
     * @throws java.lang.IllegalStateException If {@link #isType()} returns {@code false}
     */
    Type asType();

    /**
     * Return with the given annotation type is present on this annotation target.
     *
     * @param annotationType The annotation type, never {@code null}
     * @return Returns {@code true} if the given annotation type is present on this annotation info, {@code false} otherwise.
     * @throws java.lang.IllegalArgumentException If {@code null} is passed as an argument
     */
    boolean hasAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Evaluate the given predicate, returning {@code true} if the predicate matches any {@link jakarta.enterprise.lang.model.AnnotationInfo} present on this annotation target.
     * @param predicate The predicate, never {@code null}
     * @return Returns {@code true} if the given predicate matches any {@link jakarta.enterprise.lang.model.AnnotationInfo} instance, {@code false} otherwise.
     * @throws java.lang.IllegalArgumentException If {@code null} is passed as an argument
     */
    boolean hasAnnotation(Predicate<AnnotationInfo<?>> predicate);

    /**
     * Obtains an {@link jakarta.enterprise.lang.model.AnnotationInfo} for the given annotation type if it is present on this annotation target.
     * @param annotationType The annotation type, never {@code null}
     * @param <T> The annotation generic type
     * @return The {@link jakarta.enterprise.lang.model.AnnotationInfo} or {@code null} if it doesn't exist.
     * @throws java.lang.IllegalArgumentException If {@code null} is passed as an argument
     */
    <T extends Annotation> AnnotationInfo<T> annotation(Class<T> annotationType);

    /**
     * Obtains a collection of the repeatable {@link jakarta.enterprise.lang.model.AnnotationInfo} instances for the given repeatable annotation type (An annotation type that is annotated with {@link java.lang.annotation.Repeatable}).
     *
     * @param annotationType The annotation type
     * @return An immutable collection of {@link jakarta.enterprise.lang.model.AnnotationInfo}, never {@code null}
     */
    <T extends Annotation> Collection<AnnotationInfo<T>> repeatableAnnotation(Class<T> annotationType);

    /**
     * Obtains a collection of the {@link jakarta.enterprise.lang.model.AnnotationInfo} instances that match the given predicate.
     *
     * @param predicate The predicate used to evaluate matching {@link jakarta.enterprise.lang.model.AnnotationInfo} instances.
     * @return An immutable collection of {@link jakarta.enterprise.lang.model.AnnotationInfo}, never {@code null}
     * @throws java.lang.IllegalArgumentException If {@code null} is passed as an argument
     */
    Collection<AnnotationInfo<?>> annotations(Predicate<AnnotationInfo<?>> predicate);

    /**
     * Obtains all of the {@link jakarta.enterprise.lang.model.AnnotationInfo} instances for the given annotation target.
     * @return An immutable collection of {@link jakarta.enterprise.lang.model.AnnotationInfo}, never {@code null}
     */
    Collection<AnnotationInfo<?>> annotations();
}
