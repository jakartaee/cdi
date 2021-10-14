package jakarta.enterprise.event;

/**
 * <p>
 * A CDI event with payload of type {@link Startup} and qualifier {@link jakarta.enterprise.inject.Any} is
 * <i>synchronously</i> fired by CDI container during application initialization.
 * Applications must never manually fire any events with {@link Startup} as payload.
 * </p>
 *
 * <p>
 * Implementations have to fire this event after the event with qualifier {@code @Initialized(ApplicationScope.class)}
 * but before processing requests.
 * </p>
 *
 * <p>
 * This event can be observed by integrators and libraries to perform any kind of early initialization as well as by
 * users as a reliable entry point for when the CDI container is ready.
 * </p>
 *
 * <p>
 * Observers are encouraged to specify {@code @Priority} to determine ordering with lower priority numbers being
 * recommended for platform/framework/library integration and higher numbers for user applications.
 * </p>
 * See also {@link jakarta.interceptor.Interceptor.Priority}
 */
public class Startup {
}
