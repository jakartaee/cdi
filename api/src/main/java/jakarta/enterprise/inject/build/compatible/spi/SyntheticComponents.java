package jakarta.enterprise.inject.build.compatible.spi;

public interface SyntheticComponents {
    // TODO type parameters? (see also SyntheticBeanBuilder and SyntheticObserverBuilder)

    <T> SyntheticBeanBuilder<T> addBean(Class<T> implementationClass);

    SyntheticObserverBuilder addObserver();
}
