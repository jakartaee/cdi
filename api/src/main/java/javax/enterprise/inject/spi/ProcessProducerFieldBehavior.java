package javax.enterprise.inject.spi;

/**
 * <p>
 * The container fires an event of this type for each
 * {@linkplain javax.enterprise.inject.Produces producer field} of each enabled
 * bean, including resources.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the
 * {@code Producer}. The container must use the final value of this property,
 * after all observers have been called, whenever it calls the producer or
 * disposer.
 * </p>
 * 
 * <p>
 * An observer of this event may also set the initial value of a producer
 * field.
 * </p>
 * 
 * <p>
 * For example, this observer decorates the {@code Producer} for the all
 * producer fields of type {@code EntityManager}.
 * </p>
 * 
 * <pre>
 * void decorateEntityManager(@Observes ProcessProducerFieldBehavior&lt;?, EntityManager&gt; pp) {
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
 * @see Initializer
 * @param <T>
 *            The bean class of the bean that declares the producer field
 * @param <X>
 *            The type of the producer field
 */
public interface ProcessProducerFieldBehavior<T, X> extends
	ProcessProducer<T, X> {

    /**
     * <p>
     * Returns the {@link javax.enterprise.inject.spi.AnnotatedField}
     * representing the producer field.
     * </p>
     * 
     * @return the {@link javax.enterprise.inject.spi.AnnotatedField}
     *         representing the producer
     */
    public AnnotatedField<T> getAnnotatedMember();

    /**
     * <p>
     * Sets the Initializer object that will be used by the container to obtain
     * the initial value of the producer field.
     * </p>
     * 
     * @param producer
     *            the initializer to use
     */
    public void setInitializer(Initializer<X> producer);
}