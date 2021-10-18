package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.inject.Instance;

/**
 * Destruction function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * CDI container will create an instance of the destruction function every time when it needs
 * to destroy an instance of the synthetic bean. Implementations must be {@code public}
 * classes with a {@code public} zero-parameter constructor; they must not be beans.
 *
 * @param <T> the bean class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanDisposer<T> {
    /**
     * Destroys an instance of the synthetic bean.
     * <p>
     * The {@link Instance} parameter may be used to simulate disposer method parameter injection.
     * All {@code @Dependent} bean instances obtained from the {@code Instance} during execution
     * are destroyed when execution completes.
     * <p>
     * Trying to look up {@code InjectionPoint} from the {@code Instance} parameter is invalid.
     * <p>
     * The parameter map contains the same values that were passed to the {@link SyntheticBeanBuilder}
     * that defined the synthetic bean.
     *
     * @param instance the synthetic bean instance, never {@code null}
     * @param lookup an {@link Instance} that can be used to lookup other beans, never {@code null}
     * @param params the parameter map, never {@code null}
     */
    void dispose(T instance, Instance<Object> lookup, Parameters params);
}
