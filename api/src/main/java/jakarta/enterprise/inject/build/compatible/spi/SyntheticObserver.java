package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.event.ObserverException;
import jakarta.enterprise.inject.spi.EventContext;

import java.util.Map;

/**
 * The event notification function for a synthetic observer defined by {@link SyntheticObserverBuilder}.
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> the observed event type of the synthetic observer
 * @since 4.0
 */
public interface SyntheticObserver<T> {
    /**
     * Consumes an event. The {@link EventContext} provides access to the event payload,
     * as well as the {@link jakarta.enterprise.inject.spi.EventMetadata EventMetadata}.
     * <p>
     * The parameter map is defined by the {@link SyntheticObserverBuilder}. Note that
     * annotation-typed parameters are always passed to this function as instances of
     * the annotation type, even if an instance of {@code AnnotationInfo} was passed
     * to the {@code SyntheticObserverBuilder}.
     *
     * @param event the event context, never {@code null}
     * @param params immutable map of parameters, never {@code null}
     * @throws Exception checked exception will be wrapped and rethrown as an {@link ObserverException}
     */
    void observe(EventContext<T> event, Map<String, Object> params) throws Exception;
}
