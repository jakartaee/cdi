package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

import java.util.Collection;

/**
 * Allows adding annotations to and removing annotations from a class.
 * Note that the class is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 */
public interface ClassConfig extends DeclarationConfig<ClassConfig> {
    /**
     * Returns the {@link ClassInfo read-only information} about this transformed class.
     *
     * @return the {@link ClassInfo} corresponding to this transformed class, never {@code null}
     */
    @Override
    ClassInfo info();

    /**
     * Returns a collection of {@link MethodConfig} objects for each constructor of this class.
     *
     * @return immutable collection of {@link MethodConfig} objects, never {@code null}
     */
    // TODO specify whether inherited constructors are also included
    Collection<MethodConfig> constructors();

    /**
     * Returns a collection of {@link MethodConfig} objects for each method of this class.
     *
     * @return immutable collection of {@link MethodConfig} objects, never {@code null}
     */
    // TODO specify whether inherited methods are also included
    Collection<MethodConfig> methods();

    /**
     * Returns a collection of {@link FieldConfig} objects for each field of this class.
     *
     * @return immutable collection of {@link FieldConfig} objects, never {@code null}
     */
    // TODO specify whether inherited fields are also included
    Collection<FieldConfig> fields();
}
