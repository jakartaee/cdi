package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.util.Collection;

/**
 * Models an annotation definition, providing access to the {@link AnnotationMemberValue}
 * instances.
 *
 * @param <T>  The annotation type.
 */
public interface AnnotationInfo<T extends Annotation> {
    /**
     * The commonly used {@code value()} member.
     */
    String MEMBER_VALUE = "value";

    /**
     * Declaration of this annotation's type.
     *
     * @return declaration of this annotation, never {@code null}
     */
    ClassInfo<T> declaration();

    /**
     * Fully qualified name of this annotation.
     * Equivalent to {@code declaration().name()}.
     *
     * @return fully qualified name of this annotation, never {@code null}
     */
    default String name() {
        return declaration().name();
    }

    /**
     * Returns whether this annotation is repeatable. In other words, returns whether
     * this annotation's type is meta-annotated with {@code @Repeatable}.
     *
     * @return whether this annotation is repeatable
     */
    default boolean isRepeatable() {
        return declaration().hasAnnotation(Repeatable.class);
    }

    /**
     * Whether this annotation has a member with given {@code name}.
     *
     * @param name member name, never {@code null}
     * @return whether this annotation has a member with given {@code name}
     * @throws java.lang.NullPointerException if the argument is {@code null}
     */
    boolean hasMember(String name);

    /**
     * Value of this annotation's attribute with given {@code name}.
     *
     * @param name attribute name, never {@code null}
     * @return value of this annotation's attribute with given {@code name} or {@code null} if it doesn't exist.
     * @throws java.lang.NullPointerException if the argument is {@code null}
     */
    AnnotationMemberValue member(String name);

    /**
     * Returns whether this annotation has the {@link #MEMBER_VALUE} member.
     *
     * @return Returns {@code true} if the {@link #MEMBER_VALUE} is set, {@code false} otherwise
     */
    default boolean hasValue() {
        return hasMember(MEMBER_VALUE);
    }

    /**
     * Returns the {@link AnnotationMemberValue} instance that represents
     * the value of the {@link #MEMBER_VALUE} member.
     * @return An {@link AnnotationMemberValue} instance or {@code null} if none exists.
     */
    default AnnotationMemberValue value() {
        return member(MEMBER_VALUE);
    }

    /**
     * All members of this annotation.
     *
     * @return An immutable collection of all members of this annotation. Never {@code null}.
     */
    Collection<AnnotationMember> members();
}
