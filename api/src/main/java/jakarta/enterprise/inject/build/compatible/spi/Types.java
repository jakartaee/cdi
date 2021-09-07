package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.ArrayType;
import jakarta.enterprise.lang.model.types.ClassType;
import jakarta.enterprise.lang.model.types.ParameterizedType;
import jakarta.enterprise.lang.model.types.PrimitiveType;
import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.VoidType;
import jakarta.enterprise.lang.model.types.WildcardType;

// TODO move to jakarta.enterprise.lang.model.types? probably not, it doesn't model any part of Java language

/**
 * A factory interface for creating instances of {@link Type}.
 *
 * @since 4.0
 */
public interface Types {
    /**
     * Returns a type from the given class literal.
     *
     * For example:
     * <ul>
     * <li>{@code of(void.class)}: same as {@code ofVoid()}</li>
     * <li>{@code of(int.class)}: same as {@code ofPrimitive(PrimitiveKind.INT)}</li>
     * <li>{@code of(String.class)}: same as {@code ofClass(... ClassInfo for String ...)}</li>
     * <li>{@code of(int[].class)}: same as {@code ofArray(ofPrimitive(PrimitiveKind.INT), 1)}</li>
     * <li>{@code of(String[][].class)}: same as {@code ofArray(ofClass(... ClassInfo for String ...), 2)}</li>
     * </ul>
     *
     * @param clazz the class literal, must not be {@code null}
     * @return {@link Type} object representing the given class literal
     */
    Type of(Class<?> clazz);

    /**
     * Returns a {@link VoidType}, representing the {@code void} pseudo-type.
     *
     * @return the {@link VoidType}, never {@code null}
     */
    VoidType ofVoid();

    /**
     * Returns a {@link PrimitiveType} for the given {@linkplain PrimitiveType.PrimitiveKind kind} of primitive type.
     *
     * @param kind the primitive type kind, must not be {@code null}
     * @return the {@link PrimitiveType}, never {@code null}
     */
    PrimitiveType ofPrimitive(PrimitiveType.PrimitiveKind kind);

    /**
     * Returns a {@link ClassType} for the given binary name, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getName()}.
     * <p>
     * Note that this method returns {@link ClassType}, so {@code name} can only be a name of a class.
     * For primitives, use {@link #ofPrimitive(PrimitiveType.PrimitiveKind)}. For arrays, use {@link #ofArray(Type, int)}.
     *
     * @param name the binary name of the class, must not be {@code null}
     * @return the {@link ClassType} or {@code null} if the type doesn't exist on the application classpath
     */
    ClassType ofClass(String name);

    /**
     * Returns a {@link ClassType} for the given {@linkplain ClassInfo class declaration}.
     *
     * @param clazz the {@link ClassInfo}, must not be {@code null}
     * @return the {@link ClassType}, never {@code null}
     */
    ClassType ofClass(ClassInfo clazz);

    /**
     * Returns an {@link ArrayType} for the given {@linkplain Type element type} and number of dimensions.
     *
     * @param elementType the element {@link Type}, must not be {@code null}
     * @param dimensions the number of dimensions
     * @return the {@link ArrayType}, never {@code null}
     * @throws IllegalArgumentException if the element type is an array type
     */
    // TODO more error conditions? e.g. void array doesn't make sense either
    ArrayType ofArray(Type elementType, int dimensions);

    /**
     * Returns a {@link ParameterizedType} for the given generic type and type arguments.
     * The array of type arguments must have the same shape as the generic type's
     * list of type parameters.
     *
     * @param genericType the type to parameterize, must not be {@code null}
     * @param typeArguments one or more type arguments
     * @return the parameterized type, never {@code null}
     * @throws IllegalArgumentException if given {@code genericType} is not generic or if the number of type arguments
     * doesn't match the number of type parameters declared by {@code genericType}
     */
    ParameterizedType parameterized(Class<?> genericType, Class<?>... typeArguments);

    /**
     * Returns a {@link ParameterizedType} for the given generic type and type arguments.
     * The array of type arguments must have the same shape as the generic type's
     * list of type parameters.
     *
     * @param genericType the type to parameterize, must not be {@code null}
     * @param typeArguments one or more type arguments
     * @return the parameterized type, never {@code null}
     * @throws IllegalArgumentException if given {@code genericType} is not generic or if the number of type arguments
     * doesn't match the number of type parameters declared by {@code genericType}
     */
    ParameterizedType parameterized(Class<?> genericType, Type... typeArguments);

    /**
     * Returns a  {@link ParameterizedType} for the given generic type and type arguments.
     * The array of type arguments must have the same shape as the generic type's
     * list of type parameters.
     *
     * @param genericType the type to parameterize, must not be {@code null}
     * @param typeArguments one or more type arguments
     * @return the parameterized type, never {@code null}
     * @throws IllegalArgumentException if given {@code genericType} is not generic or if the number of type arguments
     * doesn't match the number of type parameters declared by {@code genericType}
     */
    ParameterizedType parameterized(ClassType genericType, Type... typeArguments);

    /**
     * Returns a {@link WildcardType} that represents an equivalent of {@code ? extends upperBound}.
     * Note that if {@code upperBound} represents the {@code java.lang.Object} type, then the result
     * is equivalent to {@link #wildcardUnbounded()}.
     *
     * @param upperBound upper bound type, must not be {@code null}
     * @return a {@link WildcardType} object representing a wildcard type with given upper bound
     */
    WildcardType wildcardWithUpperBound(Type upperBound);

    /**
     * Returns a {@link WildcardType} that represents an equivalent of {@code ? super lowerBound}.
     *
     * @param lowerBound lower bound type, must not be {@code null}
     * @return a {@link WildcardType} object representing a wildcard type with given upper bound
     */
    WildcardType wildcardWithLowerBound(Type lowerBound);

    /**
     * Returns a {@link WildcardType} that represents an equivalent of {@code ?}.
     *
     * @return a {@link WildcardType} object representing an unbounded wildcard type
     */
    WildcardType wildcardUnbounded();
}
