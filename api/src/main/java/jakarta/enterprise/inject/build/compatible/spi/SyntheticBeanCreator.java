package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.InjectionPoint;

/**
 * Creation function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> the bean class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanCreator<T> {
    // TODO maybe a more high-level API that takes an Instance?
    //  compare with BeanConfigurator.createWith and BeanConfigurator.produceWith

    /**
     * Creates an instance of the synthetic bean.
     * <p>
     * The parameter map contains the same values that were passed to
     * the {@link SyntheticBeanBuilder} that defined the synthetic bean.
     *
     * @param creationalContext the creational context, never {@code null}
     * @param injectionPoint the injection point into which the new instance will be injected,
     * or {@code null} if the synthetic bean is not {@code @Dependent}
     * @param params the parameter map, never {@code null}
     * @return an instance of the bean, may only be {@code null} if the synthetic bean is {@code @Dependent}
     */
    T create(CreationalContext<T> creationalContext, InjectionPoint injectionPoint, Parameters params);
}
