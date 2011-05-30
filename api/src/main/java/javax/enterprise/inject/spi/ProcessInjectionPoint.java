package javax.enterprise.inject.spi;

/**
 * <p>
 * The container fires an event of this type for every injection point of every Java EE component class supporting injection
 * that may be instantiated by the container at runtime, including every managed bean declared using
 * {@code javax.annotation.ManagedBean}, EJB session or message-driven bean, enabled bean, enabled interceptor or enabled
 * decorator.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link javax.enterprise.inject.spi.InjectionPoint}. The
 * container must use the final value of this property, after all observers have been called, he container must use the final
 * value of this property, after all observers have been called, whenever it performs injection upon the injection point.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessInjectionPoint} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @see InjectionPoint
 * @author Pete Muir
 * @param <X> the declared type of the injection point.
 * @param <T> the bean class of the bean that declares the injectoion point
 */
public interface ProcessInjectionPoint<T, X> {

    /**
     * @return the InjectionPoint object that will be used by the container to perform injection
     */
    public InjectionPoint getInjectionPoint();

    /**
     * Replaces the InjectionPoint.
     * 
     * @param injectionPoint the new injection point
     */
    public void setInjectionPoint(InjectionPoint injectionPoint);

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t the definition error
     */
    public void addDefinitionError(Throwable t);
}