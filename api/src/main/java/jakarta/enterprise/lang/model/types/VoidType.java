package jakarta.enterprise.lang.model.types;

/**
 * The {@code void} pseudo-type.
 */
public interface VoidType extends Type {
    /**
     * Returns the name of the {@code void} pseudo-type, that is, the string {@code void}.
     *
     * @return the string {@code void}
     */
    String name();

    // ---

    @Override
    default Kind kind() {
        return Kind.VOID;
    }

    @Override
    default VoidType asVoid() {
        return this;
    }
}
