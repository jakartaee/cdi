package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import java.util.List;

/**
 * A parameterized type. That is, a {@linkplain #declaration() generic class} together with
 * a list of {@linkplain #typeArguments() type arguments}. The list of type arguments
 * has the same shape as the class's list of type parameters.
 *
 * @since 4.0
 */
public interface ParameterizedType extends Type {
    /**
     * Returns the {@linkplain ClassInfo declaration} of the generic class that was parameterized with a list
     * of type arguments to form this parameterized type.
     *
     * @return the {@linkplain ClassInfo declaration} of the generic class, never {@code null}
     */
    ClassInfo declaration();

    /**
     * Returns the list of type arguments that were applied to a generic class to form this parameterized type.
     *
     * @return immutable list of type arguments, never {@code null} or empty
     */
    List<Type> typeArguments();

    // ---

    @Override
    default Kind kind() {
        return Kind.PARAMETERIZED_TYPE;
    }

    @Override
    default ParameterizedType asParameterizedType() {
        return this;
    }
}
