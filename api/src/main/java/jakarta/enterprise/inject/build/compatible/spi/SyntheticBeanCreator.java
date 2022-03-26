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
 *
 * @param <T> the bean class of the synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanCreator<T> {
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
     * The parameter map contains the same values that were passed to the {@link SyntheticBeanBuilder}
     * that defined the synthetic bean.
     *
     * @param lookup an {@link Instance} that can be used to lookup other beans, never {@code null}
     * @param params the parameter map, never {@code null}
     * @return an instance of the bean, may only be {@code null} if the synthetic bean is {@code @Dependent}
     */
    T create(Instance<Object> lookup, Parameters params);
}
