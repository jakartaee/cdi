package jakarta.enterprise.event;

/**
 * <p>
 * A CDI event with payload of type {@link Shutdown} and qualifier {@link jakarta.enterprise.inject.Any} is
 * <i>synchronously</i> fired by CDI container during application shutdown.
 * Applications must never manually fire any events with {@link Shutdown} as payload.
 * </p>
 *
 * <p>
 * Implementations have to fire this event during CDI container shutdown, but not later than the event with qualifier
 * {@code @BeforeDestroyed(ApplicationScoped.class)}.
 * </p>
 *
 * <p>
 * This event can be observed by integrators and libraries to perform any kind of pre-shutdown operation as well as by
 * users as a reliable entry point for when the CDI container is about to shut down.
 * </p>
 *
 * <p>
 * Observers are encouraged to specify {@code @Priority} to determine ordering with lower priority numbers being
 * recommended for user applications and higher numbers for platform/framework/library integration.
 * </p>
 * See also {@link jakarta.interceptor.Interceptor.Priority}
 */
public class Shutdown {
}
