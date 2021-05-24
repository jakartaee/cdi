package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;

/**
 * @param <T> type of whomever declares the inspected field
 */
public interface FieldInfo<T> extends DeclarationInfo {
    // TODO remove the type parameter?

    String name();

    Type type();

    boolean isStatic();

    boolean isFinal();

    int modifiers();

    ClassInfo<T> declaringClass();

    // ---

    @Override
    default Kind kind() {
        return Kind.FIELD;
    }

    @Override
    default FieldInfo<?> asField() {
        return this;
    }
}
