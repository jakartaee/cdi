/*
 * Copyright 2025, Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import java.util.function.Supplier;

/**
 * Includes methods for validating invokers during {@link Validation @Validation}.
 *
 * @since 5.0
 */
public interface InvokerValidation {
    /**
     * Ensures that an {@linkplain jakarta.enterprise.invoke.AsyncHandler async handler} exists for the given async type.
     * If it does not exist, registers a deployment problem as if {@link Messages#error(Exception)} was called.
     *
     * @param asyncType the required async type, must not be {@code null}
     */
    default void ensureAsyncHandlerExists(Class<?> asyncType) {
        ensureAsyncHandlerExists(asyncType, () -> null);
    }

    /**
     * Ensures that an {@linkplain jakarta.enterprise.invoke.AsyncHandler async handler} exists for the given async type.
     * If it does not exist, registers a deployment problem as if {@link Messages#error(Exception)} was called.
     * The deployment problem includes the given {@code message} in a suitable, implementation-defined manner.
     *
     * @param asyncType the required async type, must not be {@code null}
     * @param message supplier of the error message, must not be {@code null} but may return {@code null}
     */
    void ensureAsyncHandlerExists(Class<?> asyncType, Supplier<String> message);
}
