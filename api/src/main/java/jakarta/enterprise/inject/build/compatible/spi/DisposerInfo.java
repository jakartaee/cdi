package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;

/**
 * Provides read-only information about a disposer method.
 */
public interface DisposerInfo {
    /**
     * Returns this disposer method represented as {@link MethodInfo}.
     *
     * @return the {@link MethodInfo}, never {@code null}
     */
    MethodInfo disposerMethod();

    /**
     * Returns the disposed parameter of this disposer method.
     *
     * @return the disposed parameter, never {@code null}
     */
    ParameterInfo disposedParameter();
}
