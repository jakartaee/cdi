package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Map;
import jakarta.enterprise.context.spi.CreationalContext;

/**
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> type of disposed instances
 */
public interface SyntheticBeanDisposer<T> {
    // TODO maybe a more high-level API that takes an Instance?
    //  compare with BeanConfigurator.destroyWith and BeanConfigurator.disposeWith

    void dispose(T instance, CreationalContext<T> creationalContext, Map<String, Object> params);
}
