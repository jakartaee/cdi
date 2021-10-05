package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;

/**
 * A record component, {@linkplain #declaringRecord() declared} in some record.
 *
 * @since 4.0
 */
public interface RecordComponentInfo extends DeclarationInfo {
    /**
     * Returns the name of this record component.
     *
     * @return the name of this record component, never {@code null}
     */
    String name();

    /**
     * Returns the {@linkplain Type type} of this record component.
     *
     * @return the {@linkplain Type type} of this record component, never {@code null}
     */
    Type type();

    /**
     * Returns the field corresponding to this record component.
     *
     * @return the field, never {@code null}
     */
    FieldInfo field();

    /**
     * Returns the accessor method corresponding to this record component.
     *
     * @return the accessor method, never {@code null}
     */
    MethodInfo accessor();

    /**
     * Returns the {@linkplain ClassInfo record} that declares this component.
     *
     * @return the {@linkplain ClassInfo record} that declares this component, never {@code null}
     */
    ClassInfo declaringRecord();

    // ---

    @Override
    default Kind kind() {
        return Kind.RECORD_COMPONENT;
    }

    @Override
    default RecordComponentInfo asRecordComponent() {
        return this;
    }
}
