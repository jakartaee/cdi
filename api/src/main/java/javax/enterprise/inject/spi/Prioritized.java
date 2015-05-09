package javax.enterprise.inject.spi;

/**
 * <p>
 * Provides access to the priority value of prioritized elements.
 * </p>
 *
 * @author Mark Paluch
 * @since 1.1
 */
public interface Prioritized {

    /**
     * <p>
     * Returns the priority value of this prioritized element.
     * </p>
     *
     * @return the priority value
     */
    public int getPriority();
}
