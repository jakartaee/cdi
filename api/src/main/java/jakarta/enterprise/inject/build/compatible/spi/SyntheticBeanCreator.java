/*
 * Copyright (c) 2021 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.inject.Instance;

/**
 * Creation function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * CDI container will create an instance of the creation function every time when it needs
 * to obtain an instance of the synthetic bean. Implementations must be {@code public}
 * classes with a {@code public} zero-parameter constructor; they must not be beans.
 * <p>
 * Starting with CDI 5.0, the {@link #create(Instance, Parameters)} method is deprecated
 * for removal; suggested replacement is {@link #create(SyntheticInjections, Parameters)}.
 * Exactly one of these methods must be implemented. The CDI container must check which method
 * exists and call it. The CDI container may assume that the {@link #create(SyntheticInjections, Parameters)}
 * method is present directly on the class registered using {@link SyntheticBeanBuilder#createWith(Class)}
 * and not inherited from a superclass or a superinterface.
 *
 * @param <T> the implementation class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanCreator<T> {
    /**
     * Creates an instance of the synthetic bean.
     * May only return {@code null} if the synthetic bean is {@code @Dependent}.
     * <p>
     * The {@link SyntheticInjections} parameter may be used to simulate producer method parameter injection.
     * All injectable references looked up from {@code SyntheticInjections} have to previously be registered using
     * {@code SyntheticBeanBuilder.withInjectionPoint()}. All {@code @Dependent} bean instances obtained from
     * {@code SyntheticInjections} during execution remain managed until the synthetic bean instance is destroyed.
     * Therefore, implementations are encouraged to destroy unneeded {@code @Dependent} bean instances obtained
     * from {@code SyntheticInjections}.
     * <p>
     * If the synthetic bean is {@code @Dependent}, the {@code InjectionPoint} to which it is injected
     * may be obtained from the {@code SyntheticInjections} parameter, if previously registered.
     * <p>
     * The parameter map contains the same values that were passed to {@code SyntheticBeanBuilder.withParam()}.
     *
     * @param lookup {@code SyntheticInjections} that can be used to obtain injectable references for
     *        previously registered injection points, never {@code null}
     * @param params the parameter map, never {@code null}
     * @return an instance of the bean, may only be {@code null} if the synthetic bean is {@code @Dependent}
     * @since 5.0
     */
    default T create(SyntheticInjections lookup, Parameters params) {
        throw new UnsupportedOperationException("Either create(SyntheticInjections, Parameters)"
                + " or create(Instance<Object>, Parameters) must be implemented");
    }

    /**
     * Creates an instance of the synthetic bean.
     * May only return {@code null} if the synthetic bean is {@code @Dependent}.
     * <p>
     * The {@link Instance} parameter may be used to simulate producer method parameter injection.
     * However, {@code @Dependent} bean instances obtained from the {@code Instance} during execution
     * remain managed until the synthetic bean instance is destroyed. Therefore, implementations
     * are encouraged to destroy unneeded {@code @Dependent} bean instances obtained from the {@code Instance}.
     * <p>
     * If the synthetic bean is {@code @Dependent}, the {@code InjectionPoint} to which it is injected
     * may be looked up from the {@code Instance} parameter.
     * <p>
     * The parameter map contains the same values that were passed to {@code SyntheticBeanBuilder.withParam()}.
     *
     * @param lookup an {@link Instance} that can be used to lookup other beans, never {@code null}
     * @param params the parameter map, never {@code null}
     * @return an instance of the bean, may only be {@code null} if the synthetic bean is {@code @Dependent}
     * @deprecated use {@link #create(SyntheticInjections, Parameters)} and register all potentially looked up
     *             beans using {@code SyntheticBeanBuilder.withInjectionPoint()}
     */
    @Deprecated(forRemoval = true, since = "5.0")
    default T create(Instance<Object> lookup, Parameters params) {
        throw new UnsupportedOperationException("Either create(SyntheticInjections, Parameters)"
                + " or create(Instance<Object>, Parameters) must be implemented");
    }
}
