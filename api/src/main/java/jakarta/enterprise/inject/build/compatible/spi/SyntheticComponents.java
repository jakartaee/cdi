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

import jakarta.enterprise.lang.model.types.Type;

/**
 * Allows registering synthetic beans and observers.
 *
 * @since 4.0
 */
public interface SyntheticComponents {
    /**
     * Creates a {@link SyntheticBeanBuilder} that allows configuring a new synthetic bean
     * of given {@code implementationClass}. The synthetic bean will be registered at the end of
     * the {@link Synthesis @Synthesis} method.
     *
     * @param implementationClass the implementation class of the new synthetic bean, must not be {@code null}
     * @param <T> the implementation class of the new synthetic bean
     * @return a new {@link SyntheticBeanBuilder}, never {@code null}
     */
    <T> SyntheticBeanBuilder<T> addBean(Class<T> implementationClass);

    /**
     * Creates a {@link SyntheticObserverBuilder} that allows configuring a new synthetic observer
     * for given {@code eventType}. The synthetic observer will be registered at the end of
     * the {@link Synthesis @Synthesis} method.
     *
     * @param eventType the observed event type of the new synthetic observer, must not be {@code null}
     * @param <T> the observed event type of the new synthetic observer
     * @return a new {@link SyntheticObserverBuilder}, never {@code null}
     */
    <T> SyntheticObserverBuilder<T> addObserver(Class<T> eventType);

    /**
     * Creates a {@link SyntheticObserverBuilder} that allows configuring a new synthetic observer
     * for given {@code eventType}. The synthetic observer will be registered at the end of
     * the {@link Synthesis @Synthesis} method.
     * <p>
     * This method is supposed to be called with explicitly provided type arguments. For example,
     * to define a synthetic observer of event type {@code List<String>}, one would call:
     * <pre>{@code
     * // types is of type Types
     * // syntheticComponents is of type SyntheticComponents
     * syntheticComponents.<List<String>>addObserver(types.parameterized(List.class, String.class))
     *     ...
     * }</pre>
     *
     * @param eventType the observed event type of the new synthetic observer, must not be {@code null}
     * @param <T> the observed event type of the new synthetic observer
     * @return a new {@link SyntheticObserverBuilder}, never {@code null}
     */
    <T> SyntheticObserverBuilder<T> addObserver(Type eventType);
}
