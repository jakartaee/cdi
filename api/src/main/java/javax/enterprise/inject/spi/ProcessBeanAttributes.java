package javax.enterprise.inject.spi;

import javax.enterprise.inject.New;

/**
 * <p>
 * The container fires an event of this type for each enabled bean, interceptor or decorator deployed in a bean archive before
 * registering the {@link javax.enterprise.inject.spi.Bean} object.
 * </p>
 * <p>
 * No event is fired for {@link New} qualified beans.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessBeanAttributes} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @author Pete Muir
 * @param <T> The class of the bean
 * @since 1.1
 */
public interface ProcessBeanAttributes<T> {

    /**
     * @return the {@link AnnotatedType} representing the managed bean class or session bean class, the {@link AnnotatedMethod}
     *         representing the producer field, or the {@link AnnotatedField} representing the producer field
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Annotated getAnnotated();

    /**
     * @return the {@link BeanAttributes} object that will be used by the container to manage instances of the bean
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public BeanAttributes<T> getBeanAttributes();

    /**
     * Replaces the {@link BeanAttributes}.
     * 
     * @param beanAttributes the new {@link BeanAttributes} to use
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void setBeanAttributes(BeanAttributes<T> beanAttributes);

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t the error to add
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);

    /**
     * Forces the container to ignore the bean.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void veto();
}
