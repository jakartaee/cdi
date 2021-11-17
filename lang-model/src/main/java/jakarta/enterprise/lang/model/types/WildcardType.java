package jakarta.enterprise.lang.model.types;

/**
 * A wildcard type. May have 3 forms:
 * <ul>
 * <li>{@code ? extends Number}: has an upper bound</li>
 * <li>{@code ? super Number}: has a lower bound</li>
 * <li>{@code ?}: unbounded, has neither upper bound nor lower bound</li>
 * </ul>
 * Note that {@code ? extends Object} is equivalent to {@code ?} and is represented as such.
 */
public interface WildcardType extends Type {
    /**
     * Returns the upper bound of this wildcard type.
     * Returns {@code null} if this wildcard type does not have an upper bound.
     *
     * @return upper bound of this wildcard type, or {@code null} if this wildcard type does not have an upper bound
     */
    Type upperBound();

    /**
     * Returns the lower bound of this wildcard type.
     * Returns {@code null} if this wildcard type does not have a lower bound.
     *
     * @return lower bound of this wildcard type, or {@code null} if this wildcard type does not have a lower bound
     */
    Type lowerBound();

    // ---

    @Override
    default Kind kind() {
        return Kind.WILDCARD_TYPE;
    }

    @Override
    default WildcardType asWildcardType() {
        return this;
    }
}
