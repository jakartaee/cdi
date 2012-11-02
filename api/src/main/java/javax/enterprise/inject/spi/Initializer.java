package javax.enterprise.inject.spi;

/**
 * <p>
 * The interface Initializer lets a portable extension provide an initial value
 * for a producer field.
 * </p>
 * 
 * @author Pete Muir
 * @author Gavin King
 * 
 * @param <X>
 */
public interface Initializer<X> {

    /**
     * <p>
     * Returns the initial value of a producer field.
     * </p>
     * 
     * <p>
     * If the producer field is non-static, the container must inject this value
     * to the producer field when it performs Java EE component environment
     * injection upon an instance of the bean that declares the producer field.
     * The container must use this value as the value of the producer field,
     * whether static or non-static, unless the application explicitly assigns a
     * value to the field before the field is accessed by the container.
     * </p>
     * 
     * @param field
     *            the producer field
     * @return the initial value
     */
    public X getInitialValue(AnnotatedField<?> field);

}