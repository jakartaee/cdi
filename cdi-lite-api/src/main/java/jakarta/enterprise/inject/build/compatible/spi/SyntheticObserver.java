package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.event.ObserverException;
import jakarta.enterprise.inject.spi.EventContext;

/**
 * The event notification function for a synthetic observer defined by {@link SyntheticObserverBuilder}.
 * CDI container will create an instance of the event notification function every time when it needs
 * to notify the synthetic observer. Implementations must be {@code public} classes with a {@code public}
 * zero-parameter constructor; they must not be beans.
 *
 * @param <T> the observed event type of the synthetic observer
 * @since 4.0
 */
public interface SyntheticObserver<T> {
    /**
     * Consumes an event. The {@link EventContext} provides access to the event payload,
     * as well as the {@link jakarta.enterprise.inject.spi.EventMetadata EventMetadata}.
     * <p>
     * The parameter map contains the same values that were passed to
     * the {@link SyntheticObserverBuilder} that defined the synthetic observer.
     *
     * @param event the event context, never {@code null}
     * @param params the parameter map, never {@code null}
     * @throws Exception checked exception will be wrapped and rethrown as an {@link ObserverException}
     */
    void observe(EventContext<T> event, Parameters params) throws Exception;
}
