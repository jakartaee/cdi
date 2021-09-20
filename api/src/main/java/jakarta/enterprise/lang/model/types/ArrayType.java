package jakarta.enterprise.lang.model.types;

/**
 * An array type. That is, the {@linkplain #elementType() element type} and
 * the {@linkplain #dimensions() number of dimensions}.
 *
 * @since 4.0
 */
public interface ArrayType extends Type {
    // TODO this model (the element type + number of dimensions) might not be the best;
    //  specifically, it does not allow access to type-use annotations on component types
    //  (for example: @C int @A [] @B [] f;)

    /**
     * Returns the number of dimentions of this array type. In other words, the depth of nesting
     * of this array type. For example, {@code int[]} has 1 dimension, while {@code String[][]} has 2 dimensions.
     *
     * @return the number of dimensions, never less than 1
     */
    int dimensions();

    /**
     * Returns the element type of this array type, as defined by <cite>The Java&trade; Language Specification</cite>.
     * That is, the element type is never an array type. Types of multidimensional (nested) arrays are represented
     * as a single {@code ArrayType} with more than 1 {@linkplain #dimensions() dimension}.
     *
     * @return the element type, never {@code null}
     */
    Type elementType();

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
