package jakarta.enterprise.lang.model.types;

/**
 * An array type is created from a {@linkplain #componentType() component type}.
 * For a component type {@code T}, the array type is written {@code T[]}.
 * A component type may itself be an array type. For a component type {@code T[]},
 * the array type is written {@code T[][]}. Such array type is also called
 * multi-dimensional array type.
 * <p>
 * Array types also have an <em>element type</em>, which is obtained by repeatedly
 * asking for the component type until a non-array type is returned. For example,
 * the {@code String[][]} array type has an element type of {@code String}.
 *
 * @since 4.0
 */
public interface ArrayType extends Type {
    /**
     * Returns the component type of this array type, as defined by <cite>The Java&trade; Language Specification</cite>.
     * That is, in case of a single-dimensional array, the element type of the array, and in case of a multi-dimensional
     * array, an array type with one less dimension.
     * <p>
     * For example, the component type of {@code int[]} is the {@code int} type. The component type
     * of {@code String[][]} is the {@code String[]} array type, whose component type is the {@code String} type.
     * <p>
     * Each dimension of the array type may be annotated independently.
     *
     * @return the component type, never {@code null}
     */
    Type componentType();

    // ---

    @Override
    default Kind kind() {
        return Kind.ARRAY;
    }

    @Override
    default ArrayType asArray() {
        return this;
    }
}
