package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.util.Map;

/**
 * An annotation instance, typically obtained from an {@link AnnotationTarget}.
 * Provides access to annotation members and their values.
 *
 * @param <T> the annotation type
 */
// TODO does this have to be parameterized?
public interface AnnotationInfo<T extends Annotation> {
    /**
     * Name of the commonly used {@code value()} annotation member.
     */
    String MEMBER_VALUE = "value";

    /**
     * Declaration of this annotation's type.
     *
     * @return declaration of this annotation's type, never {@code null}
     */
    ClassInfo<T> declaration();

    /**
     * Binary name of this annotation's type, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the annotation type name as returned by {@link Class#getName()}.
     * Equivalent to {@code declaration().name()}.
     *
     * @return binary name of this annotation's type, never {@code null}
     */
    default String name() {
        return declaration().name();
    }

    /**
     * Returns whether this annotation is repeatable. In other words, returns whether
     * this annotation's type is meta-annotated {@code @Repeatable}.
     *
     * @return whether this annotation is repeatable
     */
    default boolean isRepeatable() {
        return declaration().hasAnnotation(Repeatable.class);
    }

    /**
     * Returns whether this annotation has a member with given {@code name}.
     *
     * @param name member name, must not be {@code null}
     * @return {@code true} if this annotation has a member with given {@code name}, {@code false} otherwise
     */
    boolean hasMember(String name);

    /**
     * Returns the {@link AnnotationMemberValue value} of this annotation's member with given {@code name}.
     *
     * @param name member name, must not be {@code null}
     * @return value of this annotation's member with given {@code name} or {@code null} if such member doesn't exist
     */
    AnnotationMemberValue member(String name);

    /**
     * Returns whether this annotation has the {@link #MEMBER_VALUE value} member.
     *
     * @return {@code true} if this annotation has the {@link #MEMBER_VALUE value} member, {@code false} otherwise
     */
    default boolean hasValue() {
        return hasMember(MEMBER_VALUE);
    }

    /**
     * Returns the {@link AnnotationMemberValue value} of this annotation's {@link #MEMBER_VALUE value} member.
     *
     * @return value of this annotation's {@link #MEMBER_VALUE value} member or {@code null} if the member doesn't exist
     */
    default AnnotationMemberValue value() {
        return member(MEMBER_VALUE);
    }

    /**
     * Returns all members of this annotation as a map, where the key is the member name
     * and the value is the member value. Returns an empty map if this annotation has no members.
     *
     * @return an immutable map of all members of this annotation, never {@code null}
     */
    Map<String, AnnotationMemberValue> members();
}
