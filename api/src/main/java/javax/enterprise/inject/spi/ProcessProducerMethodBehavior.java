package javax.enterprise.inject.spi;

/**
 * <p>
 * The container fires an event of this type for each
 * {@linkplain javax.enterprise.inject.Produces producer method} of each enabled
 * bean, including resources.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the
 * {@code Producer}. The container must use the final value of this property,
 * after all observers have been called, whenever it calls the producer or
 * disposer.
 * </p>
 * <p>
 * For example, this observer decorates the {@code Producer} for the all
 * producer methods {@code EntityManager}.
 * </p>
 * 
 * <pre>
 * void decorateEntityManager(&#064;Observes ProcessProducerMethodBehavior&lt;?, EntityManager&gt; pp) {
 *     pit.setProducer(decorate(pp.getProducer()));
 * }
 * </pre>
 * <p>
 * If any observer method of a {@code ProcessProducer} event throws an
 * exception, the exception is treated as a definition error by the container.
 * </p>
 * 
 * @author Pete Muir
 * @author Gavin King
 * @author David Allen
 * 
 * @param <T>
 *            The bean class of the bean that declares the producer method
 * @param <X>
 *            The return type of the producer method
 */
public interface ProcessProducerMethodBehavior<T, X> extends
	ProcessProducer<T, X> {

    /**
     * Returns the {@link javax.enterprise.inject.spi.AnnotatedMethod}
     * representing the producer method.
     * 
     * @return the {@link javax.enterprise.inject.spi.AnnotatedMethod}
     *         representing the producer
     */
    public AnnotatedMethod<T> getAnnotatedMember();

}