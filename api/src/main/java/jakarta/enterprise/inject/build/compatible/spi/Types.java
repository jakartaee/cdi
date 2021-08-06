package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.ArrayType;
import jakarta.enterprise.lang.model.types.PrimitiveType;
import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.VoidType;

// TODO move to jakarta.enterprise.lang.model.types? probably not, it doesn't model any part of Java language

/**
 * A factory interface for creating instances of {@link Type}.
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
     * @param clazz class literal, must not be {@code null}
     * @return {@link Type} object representing the given class literal
     */
    Type of(Class<?> clazz);

    /**
     * @return An instance of {@link jakarta.enterprise.lang.model.types.VoidType}, never {@code null}
     */
    VoidType ofVoid();

    /**
     * Returns a {@link PrimitiveType} for the given {@link jakarta.enterprise.lang.model.types.PrimitiveType.PrimitiveKind kind}.
     * @param kind The kind, must not be {@code null}
     * @return The {@link PrimitiveType}, never {@code null}
     */
    PrimitiveType ofPrimitive(PrimitiveType.PrimitiveKind kind);

    /**
     * Returns a {@link Type} for the given {@link ClassInfo clazz}.
     * @param clazz The {@link ClassInfo}, must not be {@code null}
     * @return A {@link Type}, never {@code null}
     */
    Type ofClass(ClassInfo clazz);

    /**
     * Returns an {@link ArrayType} for the given {@link Type componentType} and dimensions
     * @param componentType The component {@link Type}, must not be {@code null}
     * @param dimensions The dimensions
     * @return The {@link ArrayType}, never {@code null}
     */
    ArrayType ofArray(Type componentType, int dimensions);

    /**
     * Returns a parameterized {@link Type} for the given type and type arguments.
     *
     * @param parameterizedType The type to parametrize, must not be {@code null}
     * @param typeArguments Zero or more type arguments
     * @return A potentially parameterized type, never {@code null}
     */
    Type parameterized(Class<?> parameterizedType, Class<?>... typeArguments);

    /**
     * Returns a parameterized {@link Type} for the given type and type arguments.
     *
     * @param parameterizedType The type to parametrize, must not be {@code null}
     * @param typeArguments Zero or more type arguments
     * @return A potentially parameterized type, never {@code null}
     */
    Type parameterized(Class<?> parameterizedType, Type... typeArguments);

    /**
     * Returns a parameterized {@link Type} for the given type and type arguments.
     *
     * @param parameterizedType The type to parametrize, must not be {@code null}
     * @param typeArguments Zero or more type arguments
     * @return A potentially parameterized type, never {@code null}
     */
    Type parameterized(Type parameterizedType, Type... typeArguments);

    /**
     * Equivalent of {@code ? extends upperBound}.
     *
     * @param upperBound upper bound type, must not be {@code null}
     * @return {@link Type} object representing a wildcard type with given upper bound
     */
    Type wildcardWithUpperBound(Type upperBound);

    /**
     * Equivalent of {@code ? super lowerBound}.
     *
     * @param lowerBound lower bound type, must not be {@code null}
     * @return {@link Type} object representing a wildcard type with given lower bound
     */
    Type wildcardWithLowerBound(Type lowerBound);

    /**
     * Equivalent of {@code ?}.
     *
     * @return {@link Type} object representing an unbounded wildcard type
     */
    Type wildcardUnbounded();
}
