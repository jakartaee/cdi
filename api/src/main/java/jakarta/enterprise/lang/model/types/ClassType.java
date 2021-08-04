package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

public interface ClassType extends Type {
    ClassInfo declaration();

    // ---

    @Override
    default Kind kind() {
        return Kind.CLASS;
    }

    @Override
    default ClassType asClass() {
        return this;
    }
}
