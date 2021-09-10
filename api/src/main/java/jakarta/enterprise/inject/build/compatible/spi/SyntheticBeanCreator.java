package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Map;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.InjectionPoint;

/**
 * Creation function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * Implementations must be {@code public} classes with a {@code public} zero-parameter constructor.
 *
 * @param <T> the bean class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanCreator<T> {
    // TODO maybe a more high-level API that takes an Instance?
    //  compare with BeanConfigurator.createWith and BeanConfigurator.produceWith

    /**
     * Creates an instance of the synthetic bean.
     * <p>
     * The parameter map is defined by the {@link SyntheticBeanBuilder}. Note that
     * annotation-typed parameters are always passed to this function as instances of
     * the annotation type, even if an instance of {@code AnnotationInfo} was passed
     * to the {@code SyntheticBeanBuilder}.
     *
     * @param creationalContext the creational context, never {@code null}
     * @param injectionPoint the injection point into which the new instance will be injected,
     * or {@code null} if the synthetic bean is not {@code @Dependent}
     * @param params immutable map of parameters, never {@code null}
     * @return an instance of the bean, may only be {@code null} if the synthetic bean is {@code @Dependent}
     */
    T create(CreationalContext<T> creationalContext, InjectionPoint injectionPoint, Map<String, Object> params);
}
