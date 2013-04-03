package javax.enterprise.inject.spi;

/**
 * <p>
 * An {@link InjectionTargetFactory} can create an {@link InjectionTarget} for a given bean.
 * </p>
 * 
 * <p>
 * The {@link InjectionTargetFactory} obtained from {@link BeanManager#getInjectionTargetFactory()} is capable of providing
 * container created injection targets. This factory can be wrapped to add behavior to container created injection targets.
 * </p>
 * 
 * <p>
 * For example:
 * </p>
 * 
 * <pre>
 * BeanAttributes&lt;MyBean&gt; myBeanAttributes = beanManager.createBeanAttributes(myBeanAnnotatedType);
 * beanManager.createBean(myBeanAttributes, MyBean.class, new InjectionTargetFactory() {
 * 
 *     public &lt;T&gt; InjectionTarget&lt;T&gt; createInjectionTarget(Bean&lt;T&gt; bean) {
 *         return new WrappingInjectionTarget&lt;T&gt;(beanManager.getInjectionTargetFactory(myBeanAnnotatedType).createInjectionTarget(
 *                 bean));
 *     }
 * });
 * </pre>
 * 
 * @author Pete Muir
 * @since 1.1
 * 
 */
public interface InjectionTargetFactory<T> {

    /**
     * Create a new injection target for a bean.
     * 
     * @param bean the bean to create the injection target for, or null if creating a non-contextual object
     * @return the injection target
     */
    public InjectionTarget<T> createInjectionTarget(Bean<T> bean);

}
