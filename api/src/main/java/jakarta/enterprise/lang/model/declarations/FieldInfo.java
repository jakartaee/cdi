package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;

/**
 * A field, {@linkplain #declaringClass() declared} in some class.
 *
 * @since 4.0
 */
public interface FieldInfo extends DeclarationInfo {
    /**
     * Returns the name of this field.
     *
     * @return the name of this field, never {@code null}
     */
    String name();

    /**
     * Returns the {@linkplain Type type} of this field.
     *
     * @return the {@linkplain Type type} of this field, never {@code null}
     */
    Type type();

    /**
     * Returns whether this field is {@code static}.
     *
     * @return whether this field is {@code static}.
     */
    boolean isStatic();

    /**
     * Returns whether this field is {@code final}.
     *
     * @return whether this field is {@code final}.
     */
    boolean isFinal();

    /**
     * Returns the modifiers of this field as an {@code int}.
     * Use {@link java.lang.reflect.Modifier Modifier} to inspect the value.
     *
     * @return the modifiers of this field
     */
    int modifiers();

    /**
     * Returns the {@linkplain ClassInfo class} that declares this field.
     *
     * @return the {@linkplain ClassInfo class} that declares this field, never {@code null}
     */
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
