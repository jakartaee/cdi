package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import java.util.List;

public interface ParameterizedType extends Type {
    ClassInfo declaration();

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
