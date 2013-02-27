package javax.enterprise.inject.spi;


/**
 * <p>
 * An {@link InjectionTargetFactory} can create an {@link InjectionTarget} for a given bean.
 * </p>
 * 
 * <p>
 * The {@link InjectionTargetFactory} obtained from {@link BeanManager#getInjectionTargetFactory()} is 
 * capable of providing container created injection targets. This factory can be wrapped to add behavior
 * to container created injection targets.  
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
 *     public <T> InjectionTarget<T> createInjectionTarget(Bean<T> bean) {
 *         return new WrappingInjectionTarget<T>(beanManager.getInjectionTargetFactory(myBeanAnnotatedType).createInjectionTarget(bean));
 *     }
 * });
 * </pre>
 * 
 * @author Pete Muir
 *
 */
public interface InjectionTargetFactory<T> {

    /**
     * Create a new injection target for a bean.
     * 
     * @param bean the bean to create the injection target for
     * @return the injection target
     */
    public InjectionTarget<T> createInjectionTarget(Bean<T> bean);
    
}
