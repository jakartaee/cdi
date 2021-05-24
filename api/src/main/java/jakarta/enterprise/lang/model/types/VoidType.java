package jakarta.enterprise.lang.model.types;

public interface VoidType extends Type {
    // TODO is name() truly needed?
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
