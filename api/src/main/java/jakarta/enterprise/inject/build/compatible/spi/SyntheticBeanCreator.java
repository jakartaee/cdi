package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Map;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.InjectionPoint;

/**
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> type of created instances
 */
public interface SyntheticBeanCreator<T> {
    // TODO maybe a more high-level API that takes an Instance?
    //  compare with BeanConfigurator.createWith and BeanConfigurator.produceWith

    // injectionPoint is only set when the synthetic bean is @Dependent
    T create(CreationalContext<T> creationalContext, InjectionPoint injectionPoint, Map<String, Object> params);
}
