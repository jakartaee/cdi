package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;

public interface ParameterInfo extends DeclarationInfo {
    String name(); // TODO doesn't have to be known

    Type type();

    MethodInfo<?> declaringMethod();

    // ---

    @Override
    default Kind kind() {
        return Kind.PARAMETER;
    }

    @Override
    default ParameterInfo asParameter() {
        return this;
    }
}
