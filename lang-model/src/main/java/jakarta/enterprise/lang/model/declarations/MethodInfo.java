package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;
import java.util.List;

/**
 * A method or a constructor, {@linkplain #declaringClass() declared} in some class.
 *
 * @since 4.0
 */
public interface MethodInfo extends DeclarationInfo {
    /**
     * Returns the name of this method. In case of constructors, returns the binary name of the declaring class.
     *
     * @return the name of this method, never {@code null}
     */
    String name();

    /**
     * Returns a list of {@linkplain ParameterInfo parameters} declared or implicitly declared on this method.
     *
     * @return immutable list of {@linkplain ParameterInfo parameters}, never {@code null}
     */
    List<ParameterInfo> parameters();

    /**
     * Returns the return type of this method. In case of constructors, returns the type of the declaring class.
     *
     * @return the return type of this method, never {@code null}
     */
    Type returnType();

    /**
     * Returns the {@linkplain Type type} of the receiver parameter declared by this method.
     * Returns {@code null} if this method cannot declare a receiver parameter; that is, if this method
     * is {@code static} or is a constructor of a top-level class or a {@code static} nested class.
     * If this method may declare a receiver parameter but does not, returns a {@link Type} with no annotations.
     *
     * @return the type of the receiver parameter declared by this method, with or without annotations,
     * or {@code null} if this method cannot declare a receiver parameter
     */
    Type receiverType();

    /**
     * Returns a list of {@linkplain Type exception types} that are declared to be thrown by this method.
     * Returns an empty list if this method does not declare any exception.
     *
     * @return immutable list of {@linkplain Type exception types}, never {@code null}
     */
    List<Type> throwsTypes();

    /**
     * Returns a list of {@linkplain TypeVariable type parameters} declared on this method.
     * Returns an empty list if this method is not generic and so does not declare type parameters.
     *
     * @return immutable list of {@linkplain TypeVariable type parameters}, never {@code null}
     */
    List<TypeVariable> typeParameters();

    /**
     * Returns whether this method is, in fact, a constructor.
     *
     * @return whether this method is, in fact, a constructor
     */
    boolean isConstructor();

    /**
     * Returns whether this method is {@code static}.
     *
     * @return whether this method is {@code static}.
     */
    boolean isStatic();

    /**
     * Returns whether this method is abstract.
     * <p>
     * A {@code static} method is never abstract.
     * An instance method declared on a plain class or an enum is abstract if declared {@code abstract}.
     * An instance method declared on an interface is abstract unless declared {@code default}.
     * An instance method declared on an annotation type is always abstract.
     * An instance method declared on a record type is never abstract.
     *
     * @return whether this method is {@code abstract}.
     */
    boolean isAbstract();

    /**
     * Returns whether this method is {@code final}.
     *
     * @return whether this method is {@code final}.
     */
    boolean isFinal();

    /**
     * Returns the modifiers of this method as an {@code int}.
     * Use {@link java.lang.reflect.Modifier Modifier} to inspect the value.
     *
     * @return the modifiers of this method
     */
    int modifiers();

    /**
     * Returns the {@linkplain ClassInfo class} that declares this method.
     *
     * @return the {@linkplain ClassInfo class} that declares this method, never {@code null}
     */
    ClassInfo declaringClass();

    // ---

    @Override
    default Kind kind() {
        return Kind.METHOD;
    }

    @Override
    default MethodInfo asMethod() {
        return this;
    }
}
