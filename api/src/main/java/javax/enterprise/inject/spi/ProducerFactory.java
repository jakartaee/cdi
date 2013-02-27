package javax.enterprise.inject.spi;

/**
 * <p>
 * An {@link ProducerFactory} can create an {@link Producer} for a given bean.
 * </p>
 * 
 * <p>
 * The {@link ProducerFactory} obtained from {@link BeanManager#getProducerFactory()} is 
 * capable of providing container created producers. This factory can be wrapped to add behavior
 * to container created producers.  
 * </p>
 * 
 * <p>
 * For example:
 * </p>
 * 
 * <pre>
 * BeanAttributes&lt;MyBean&gt; myBeanAttributes = beanManager.createBeanAttributes(myBeanAnnotatedFieldField);
 * beanManager.createBean(myBeanAttributes, MyBean.class, new ProducerFactory() {
 *
 *     public <T> Producer<T> createProducer(Bean<T> bean) {
 *         return new WrappingProducer<T>(beanManager.getProducerFactory(myBeanAnnotatedField).createProducer(bean));
 *     }
 * });
 * </pre>
 * 
 * @author Pete Muir
 *
 */
public interface ProducerFactory<X> {

    /**
     * Create a new producer for a bean.
     * 
     * @param bean the bean to create the producer for
     * @return the producer
     */
    public Producer<?> createProducer(Bean<X> bean);
    
}
