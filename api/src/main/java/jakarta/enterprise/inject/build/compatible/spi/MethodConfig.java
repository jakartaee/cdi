package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.MethodInfo;

/**
 * Allows adding annotations to and removing annotations from a method.
 * Note that the method is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 */
public interface MethodConfig extends DeclarationConfig<MethodConfig> {
    // TODO split MethodConfig into MethodConfig/ConstructorConfig?

    /**
     * Returns the {@link MethodInfo read-only information} about this transformed method.
     *
     * @return the {@link MethodInfo} corresponding to this transformed method, never {@code null}
     */
    @Override
    MethodInfo info();
}
