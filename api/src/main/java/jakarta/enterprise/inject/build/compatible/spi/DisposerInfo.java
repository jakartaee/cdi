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

import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;

/**
 * Disposer methods may exist for producer-based beans. Each disposer method
 * has a {@linkplain #disposedParameter() disposed parameter}.
 *
 * @since 4.0
 */
public interface DisposerInfo {
    /**
     * Returns the {@linkplain MethodInfo declaration} of this disposer method.
     *
     * @return the {@linkplain MethodInfo declaration} of this disposer method, never {@code null}
     */
    MethodInfo disposerMethod();

    /**
     * Returns the {@linkplain ParameterInfo declaration} of the disposed parameter of this disposer method.
     *
     * @return the {@linkplain ParameterInfo declaration} of the disposed parameter of this disposer method, never {@code null}
     */
    ParameterInfo disposedParameter();
}
