package javax.enterprise.inject.spi;

/**
 * <p>
 * An {@link ProducerFactory} can create an {@link Producer} for a given bean.
 * </p>
 * 
 * <p>
 * The {@link ProducerFactory} obtained from {@link BeanManager#getProducerFactory()} is capable of providing container created
 * producers. This factory can be wrapped to add behavior to container created producers.
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
 *     public &lt;T&gt; Producer&lt;T&gt; createProducer(Bean&lt;T&gt; bean) {
 *         return new WrappingProducer&lt;T&gt;(beanManager.getProducerFactory(myBeanAnnotatedField).createProducer(bean));
 *     }
 * });
 * </pre>
 * 
 * @author Pete Muir
 * @since 1.1
 */
public interface ProducerFactory<X> {

    /**
     * Create a new producer for a bean.
     * 
     * @param bean the bean to create the producer for, or null if creating a non-contextual object
     * @return the producer
     */
    public <T> Producer<T> createProducer(Bean<T> bean);

}
