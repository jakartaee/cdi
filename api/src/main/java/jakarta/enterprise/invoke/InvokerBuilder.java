/*
 * Copyright (c) 2022 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.invoke;

/**
 * Builder of {@link Invoker}s.
 *
 * @param <T> type of outcome of this builder; always represents an {@code Invoker},
 *            but does not necessarily have to be an {@code Invoker} instance directly
 * @since 4.1
 */
public interface InvokerBuilder<T> {
    /**
     * Returns the built {@link Invoker} or some represention of it. Implementations are allowed
     * but not required to reuse already built invokers for the same target method.
     *
     * @return the built invoker
     */
    T build();
}
