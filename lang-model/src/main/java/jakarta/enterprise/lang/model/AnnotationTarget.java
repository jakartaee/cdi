package jakarta.enterprise.lang.model;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

import jakarta.enterprise.lang.model.declarations.DeclarationInfo;
import jakarta.enterprise.lang.model.types.Type;

/**
 * An annotation target is anything that can be annotated.
 * That is:
 *
 * <ul>
 * <li>a {@linkplain DeclarationInfo declaration}, such as a class, a method, a field, etc.</li>
 * <li>a {@linkplain Type type}, such as the type of a method parameter, the type of a field, a type argument,
 * a type parameter, present in a declaration of a generic class or method, etc.</li>
 * </ul>
 *
 * The {@code hasAnnotation}, {@code annotation}, {@code repeatableAnnotation} and {@code annotations} methods
 * may be used to obtain information about annotations present on this annotation target. The phrase
 * "present on this annotation target" means: either the annotation is declared or implicitly declared
 * directly on this annotation target, or this annotation target is a class declaration and the annotation is
 * {@linkplain java.lang.annotation.Inherited inherited} from a superclass.
 * <p>
 * Note that if more than one annotation of a {@linkplain java.lang.annotation.Repeatable repeatable} annotation type
 * is declared on an annotation target, only an implicitly declared <em>container annotation</em> is present
 * on the annotation target; the originally declared annotations are not. If exactly one annotation of a repeatable
 * annotation type is declared on an annotation target, that annotation is present.
 * <p>
 * Annotations are represented as {@link AnnotationInfo}, so that implementations of this interface are not required
 * to instantiate the annotation type.
 * <p>
 * Implementations of this interface are required to define the {@code equals} and {@code hashCode} methods.
 * Implementations of this interface are encouraged to define the {@code toString} method such that
 * it returns a text resembling the corresponding Java&trade; syntax.
 * <p>
 * There is no guarantee that any particular construct, represented by an implementation of this interface,
 * will always be represented by the same object. That includes natural singletons such as the {@code java.lang.Object}
 * class declaration, or the {@code void} pseudo-type. Instances should always be compared using {@code equals}.
 *
 * @since 4.0
 */
public interface AnnotationTarget {
    /**
     * Returns whether this annotation target is a {@linkplain DeclarationInfo declaration}.
     *
     * @return {@code true} if this is a declaration, {@code false} otherwise
     */
    boolean isDeclaration();

    /**
     * Returns whether this annotation target is a {@linkplain Type type}.
     *
     * @return {@code true} if this is a type, {@code false} otherwise
     */
    boolean isType();

    /**
     * Returns this annotation target as a {@linkplain DeclarationInfo declaration}.
     *
     * @return this declaration, never {@code null}
     * @throws IllegalStateException if {@link #isDeclaration()} returns {@code false}
     */
    DeclarationInfo asDeclaration();

    /**
     * Returns this annotation target as a {@linkplain Type type}.
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
     * @return {@code true} if given predicate matches any annotation present on this annotation target, {@code false}
     *         otherwise.
     */
    boolean hasAnnotation(Predicate<AnnotationInfo> predicate);

    /**
     * Returns an annotation of given type, if it is present on this annotation target.
     * Returns {@code null} if no such annotation is present.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @param <T> the annotation generic type
     * @return the {@link AnnotationInfo} or {@code null} if no such annotation is present
     */
    <T extends Annotation> AnnotationInfo annotation(Class<T> annotationType);

    /**
     * Returns a collection of annotations of given {@linkplain java.lang.annotation.Repeatable repeatable}
     * {@code annotationType} that are present on this annotation target. Returns an empty collection if
     * no such annotation is present.
     * <p>
     * For the purpose of this method, annotations in the {@code value} member of a container annotation,
     * as defined using {@link java.lang.annotation.Repeatable @Repeatable}, are considered to be present
     * on the annotation target on which the container annotation is present.
     *
     * @param annotationType the {@code @Repeatable} annotation type, must not be {@code null}
     * @param <T> the annotation generic type
     * @return immutable collection of {@link AnnotationInfo}, never {@code null}
     */
    <T extends Annotation> Collection<AnnotationInfo> repeatableAnnotation(Class<T> annotationType);

    /**
     * Returns a collection of all annotations present on this annotation target that match given predicate.
     * Returns an empty collection if no such annotation is present.
     *
     * @param predicate annotation predicate, must not be {@code null}
     * @return immutable collection of {@link AnnotationInfo}, never {@code null}
     */
    Collection<AnnotationInfo> annotations(Predicate<AnnotationInfo> predicate);

    /**
     * Returns a collection of all annotations present on this annotation target.
     * Returns an empty collection if no annotation is present.
     *
     * @return immutable collection of {@link AnnotationInfo}, never {@code null}
     */
    Collection<AnnotationInfo> annotations();
}
