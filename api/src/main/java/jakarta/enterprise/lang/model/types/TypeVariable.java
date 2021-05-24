package jakarta.enterprise.lang.model.types;

import java.util.List;

public interface TypeVariable extends Type {
    String name();

    List<Type> bounds();

    // ---

    @Override
    default Kind kind() {
        return Kind.TYPE_VARIABLE;
    }

    @Override
    default TypeVariable asTypeVariable() {
        return this;
    }
}
