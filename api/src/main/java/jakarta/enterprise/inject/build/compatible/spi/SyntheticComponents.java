package jakarta.enterprise.inject.build.compatible.spi;

/**
 * Allows registering synthetic beans and observers.
 *
 * @since 4.0
 */
public interface SyntheticComponents {
    // TODO type parameters? (see also SyntheticBeanBuilder and SyntheticObserverBuilder)

    <T> SyntheticBeanBuilder<T> addBean(Class<T> implementationClass);

    SyntheticObserverBuilder addObserver();
}
