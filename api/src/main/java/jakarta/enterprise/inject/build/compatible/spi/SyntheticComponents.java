package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.types.Type;

/**
 * Allows registering synthetic beans and observers.
 *
 * @since 4.0
 */
public interface SyntheticComponents {
    /**
     * Creates a {@link SyntheticBeanBuilder} that allows configuring a new synthetic bean
     * of given {@code beanClass}. The synthetic bean will be registered at the end of
     * the {@link Synthesis @Synthesis} method.
     *
     * @param beanClass the bean class of the new synthetic bean, must not be {@code null}
     * @param <T> the bean class of the new synthetic bean
     * @return a new {@link SyntheticBeanBuilder}, never {@code null}
     */
    <T> SyntheticBeanBuilder<T> addBean(Class<T> beanClass);

    /**
     * Creates a {@link SyntheticObserverBuilder} that allows configuring a new synthetic observer
     * for given {@code eventType}. The synthetic observer will be registered at the end of
     * the {@link Synthesis @Synthesis} method.
     *
     * @param eventType the observed event type of the new synthetic observer, must not be {@code null}
     * @param <T> the observed event type of the new synthetic observer
     * @return a new {@link SyntheticObserverBuilder}, never {@code null}
     */
    <T> SyntheticObserverBuilder<T> addObserver(Class<T> eventType);

    /**
     * Creates a {@link SyntheticObserverBuilder} that allows configuring a new synthetic observer
     * for given {@code eventType}. The synthetic observer will be registered at the end of
     * the {@link Synthesis @Synthesis} method.
     * <p>
     * This method is supposed to be called with explicitly provided type arguments. For example,
     * to define a synthetic observer of event type {@code List<String>}, one would call:
     * <pre>{@code
     * // types is of type Types
     * // syntheticComponents is of type SyntheticComponents
     * syntheticComponents.<List<String>>addObserver(types.parameterized(List.class, String.class))
     *     ...
     * }</pre>
     *
     * @param eventType the observed event type of the new synthetic observer, must not be {@code null}
     * @param <T> the observed event type of the new synthetic observer
     * @return a new {@link SyntheticObserverBuilder}, never {@code null}
     */
    <T> SyntheticObserverBuilder<T> addObserver(Type eventType);
}
