package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.context.spi.CreationalContext;

/**
 * Destruction function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> the bean class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanDisposer<T> {
    // TODO maybe a more high-level API that takes an Instance?
    //  compare with BeanConfigurator.destroyWith and BeanConfigurator.disposeWith

    /**
     * Destroys an instance of the synthetic bean.
     * <p>
     * The parameter map contains the same values that were passed to
     * the {@link SyntheticBeanBuilder} that defined the synthetic bean.
     *
     * @param instance the synthetic bean instance, never {@code null} (TODO what if @Dependent?)
     * @param creationalContext the creational context, never {@code null}
     * @param params the parameter map, never {@code null}
     */
    void dispose(T instance, CreationalContext<T> creationalContext, Parameters params);
}
