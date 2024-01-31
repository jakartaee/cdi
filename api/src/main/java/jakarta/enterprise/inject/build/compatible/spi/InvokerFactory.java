/*
 * Copyright (c) 2024 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.invoke.InvokerBuilder;
import jakarta.enterprise.lang.model.declarations.MethodInfo;

/**
 * Factory for {@link InvokerBuilder}s.
 *
 * @since 4.1
 */
public interface InvokerFactory {
    /**
     * Returns a new {@link InvokerBuilder} for given method of given bean. The builder
     * eventually produces an opaque representation of the generated invoker.
     * <p>
     * If an invoker may not be built for given {@code bean} or for given {@code method},
     * an exception is thrown.
     *
     * @param bean target bean of the invoker, must not be {@code null}
     * @param method target method of the invoker, must not be {@code null}
     * @return the invoker builder, never {@code null}
     */
    InvokerBuilder<InvokerInfo> createInvoker(BeanInfo bean, MethodInfo method);
}
