package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;

/**
 * Provides read-only information about a field.
 */
public interface FieldInfo extends DeclarationInfo {
    String name();

    Type type();

    boolean isStatic();

    boolean isFinal();

    int modifiers();

    ClassInfo declaringClass();

    // ---

    @Override
    default Kind kind() {
        return Kind.FIELD;
    }

    @Override
    default FieldInfo asField() {
        return this;
    }
}
