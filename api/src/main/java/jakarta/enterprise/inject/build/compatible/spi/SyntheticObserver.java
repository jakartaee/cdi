package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.inject.spi.EventContext;

/**
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> type of observed event instances
 *
 * @since 4.0
 */
public interface SyntheticObserver<T> {
    // TODO throws Exception? compare with ObserverMethodConfigurator.EventConsumer.accept
    void observe(EventContext<T> event);
}
