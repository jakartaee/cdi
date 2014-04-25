package javax.enterprise.inject.spi;

import java.util.List;

/**
 * <p>
 * This event type is thrown by the container after type discovery is complete. If any observer method of the
 * {@code AfterTypeDiscovery} event throws an exception, the exception is treated as a definition error by the container.
 * </p>
 * <p>
 * Any observer of this event is permitted to add classes to, or remove classes from, the list of alternatives, list of
 * interceptors or list of decorators. The container will use the final values of these lists, after all observers have been
 * called, to determine the enabled alternatives, interceptors, and decorators for application.
 * Changes made to these lists after the invocation of the last observer method of the {@code AfterTypeDiscovery} are ignored.
 * </p>
 *
 * @author Pete Muir
 * @since 1.1
 */
public interface AfterTypeDiscovery {

    /**
     * @return the list of enabled alternatives for the application, sorted by priority in ascending order. Alternatives enabled for a bean archive are not included.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public List<Class<?>> getAlternatives();

    /**
     * @return the list of enabled interceptors for the application, sorted by priority in ascending order. Interceptors enabled for a bean archive are not included.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public List<Class<?>> getInterceptors();

    /**
     * @return the list of enabled decorators for the application, sorted by priority in ascending order. Decorators enabled for a bean archive are not included.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public List<Class<?>> getDecorators();

    /**
     * <p>
     * Adds a given {@link javax.enterprise.inject.spi.AnnotatedType} to the set of types which will be scanned during bean
     * discovery.
     * </p>
     *
     * <p>
     * This method allows multiple annotated types, based on the same underlying type, to be defined. {@link AnnotatedType}s
     * discovered by the container use the fully qualified class name of {@link AnnotatedType#getJavaClass()} to identify the
     * type.
     * </p>
     *
     * <p>
     * {@link AfterBeanDiscovery#getAnnotatedType(Class, String)} and {@link AfterBeanDiscovery#getAnnotatedTypes(Class)} allows
     * annotated types to be obtained by identifier.
     * </p>
     *
     * @param type The {@link javax.enterprise.inject.spi.AnnotatedType} to add for later scanning
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addAnnotatedType(AnnotatedType<?> type, String id);

}
