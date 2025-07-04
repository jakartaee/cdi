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
 * Destruction function for a synthetic bean defined by {@link SyntheticBeanBuilder}.
 * CDI container will create an instance of the destruction function every time when it needs
 * to destroy an instance of the synthetic bean. Implementations must be {@code public}
 * classes with a {@code public} zero-parameter constructor; they must not be beans.
 * <p>
 * Starting with CDI 5.0, the {@link #dispose(Object, Instance, Parameters)} method is deprecated
 * for removal; suggested replacement is {@link #dispose(Object, SyntheticInjections, Parameters)}.
 * Exactly one of these methods must be implemented. The CDI container must check which method
 * exists and call it. The CDI container may assume that the {@link #dispose(Object, SyntheticInjections, Parameters)}
 * method is present directly on the class registered using {@link SyntheticBeanBuilder#disposeWith(Class)}
 * and not inherited from a superclass or a superinterface.
 *
 * @param <T> the implementation class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanDisposer<T> {
    /**
     * Destroys an instance of the synthetic bean.
     * <p>
     * The {@link SyntheticInjections} parameter may be used to simulate disposer method parameter injection.
     * All injectable references looked up from {@code SyntheticInjections} have to previously be registered using
     * {@code SyntheticBeanBuilder.withInjectionPoint()}. All {@code @Dependent} bean instances obtained
     * from {@code SyntheticInjections} during execution are destroyed when execution completes.
     * <p>
     * Trying to look up {@code InjectionPoint} from the {@code SyntheticInjections} parameter is invalid.
     * <p>
     * The parameter map contains the same values that were passed to {@code SyntheticBeanBuilder.withParam()}.
     *
     * @param instance the synthetic bean instance, never {@code null}
     * @param lookup {@code SyntheticInjections} that can be used to obtain injectable references for
     *        previously registered injection points, never {@code null}
     * @param params the parameter map, never {@code null}
     * @since 5.0
     */
    default void dispose(T instance, SyntheticInjections lookup, Parameters params) {
        throw new UnsupportedOperationException("Either dispose(T, SyntheticInjections, Parameters)"
                + " or dispose(T, Instance<Object>, Parameters) must be implemented");
    }

    /**
     * Destroys an instance of the synthetic bean.
     * <p>
     * The {@link Instance} parameter may be used to simulate disposer method parameter injection.
     * All {@code @Dependent} bean instances obtained from the {@code Instance} during execution
     * are destroyed when execution completes.
     * <p>
     * Trying to look up {@code InjectionPoint} from the {@code Instance} parameter is invalid.
     * <p>
     * The parameter map contains the same values that were passed to {@code SyntheticBeanBuilder.withParam()}.
     *
     * @param instance the synthetic bean instance, never {@code null}
     * @param lookup an {@link Instance} that can be used to lookup other beans, never {@code null}
     * @param params the parameter map, never {@code null}
     * @deprecated use {@link #dispose(Object, SyntheticInjections, Parameters)} and register all potentially looked up
     *             beans using {@code SyntheticBeanBuilder.withInjectionPoint()}
     */
    @Deprecated(forRemoval = true, since = "5.0")
    default void dispose(T instance, Instance<Object> lookup, Parameters params) {
        throw new UnsupportedOperationException("Either dispose(T, SyntheticInjections, Parameters)"
                + " or dispose(T, Instance<Object>, Parameters) must be implemented");
    }
}
