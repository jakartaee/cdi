package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.PrimitiveType;
import jakarta.enterprise.lang.model.types.Type;

// TODO move to jakarta.enterprise.lang.model.types? probably not, it doesn't model any part of Java language
public interface Types {
    /**
     * Returns a type from given class literal.
     * For example:
     * <ul>
     * <li>{@code of(void.class)}: same as {@code ofVoid()}</li>
     * <li>{@code of(int.class)}: same as {@code ofPrimitive(PrimitiveKind.INT)}</li>
     * <li>{@code of(String.class)}: same as {@code ofClass(... ClassInfo for String ...)}</li>
     * <li>{@code of(int[].class)}: same as {@code ofArray(ofPrimitive(PrimitiveKind.INT), 1)}</li>
     * <li>{@code of(String[][].class)}: same as {@code ofArray(ofClass(... ClassInfo for String ...), 2)}</li>
     * </ul>
     *
     * @param clazz class literal
     * @return {@link Type} object representing the given class literal
     */
    Type of(Class<?> clazz);

    Type ofVoid();

    Type ofPrimitive(PrimitiveType.PrimitiveKind kind);

    Type ofClass(ClassInfo<?> clazz);

    Type ofArray(Type componentType, int dimensions);

    Type parameterized(Class<?> parameterizedType, Class<?>... typeArguments);

    Type parameterized(Class<?> parameterizedType, Type... typeArguments);

    Type parameterized(Type parameterizedType, Type... typeArguments);

    /**
     * Equivalent of {@code ? extends upperBound}.
     *
     * @param upperBound upper bound type
     * @return {@link Type} object representing a wildcard type with given upper bound
     */
    Type wildcardWithUpperBound(Type upperBound);

    /**
     * Equivalent of {@code ? super lowerBound}.
     *
     * @param lowerBound lower bound type
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
