package jakarta.enterprise.lang.model.types;

public interface ArrayType extends Type {
    // TODO this model (the ultimate component type + number of dimensions) might not be the best;
    //  specifically, it doesn't allow access to type-use annotations on individual component types
    //  (for example: @C int @A [] @B [] f;)

    int dimensions();

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
