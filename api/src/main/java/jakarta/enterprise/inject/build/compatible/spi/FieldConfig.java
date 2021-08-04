package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.FieldInfo;

/**
 * Allows adding annotations to and removing annotations from a field.
 * Note that the field is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 */
public interface FieldConfig extends DeclarationConfig<FieldConfig> {
    /**
     * Returns the {@link FieldInfo read-only information} about this transformed field.
     *
     * @return the {@link FieldInfo} corresponding to this transformed field, never {@code null}
     */
    @Override
    FieldInfo info();
}
