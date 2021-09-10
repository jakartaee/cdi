package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Map;
import jakarta.enterprise.context.spi.CreationalContext;

/**
 * Destruction function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> the bean class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanDisposer<T> {
    // TODO maybe a more high-level API that takes an Instance?
    //  compare with BeanConfigurator.destroyWith and BeanConfigurator.disposeWith

    /**
     * Destroys an instance of the synthetic bean.
     * <p>
     * The parameter map is defined by the {@link SyntheticBeanBuilder}. Note that
     * annotation-typed parameters are always passed to this function as instances of
     * the annotation type, even if an instance of {@code AnnotationInfo} was passed
     * to the {@code SyntheticBeanBuilder}.
     *
     * @param instance the synthetic bean instance, never {@code null} (TODO what if @Dependent?)
     * @param creationalContext the creational context, never {@code null}
     * @param params immutable map of parameters, never {@code null}
     */
    void dispose(T instance, CreationalContext<T> creationalContext, Map<String, Object> params);
}
