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

import jakarta.enterprise.inject.spi.Prioritized;

/**
 * Service provider interface for various services needed by {@link BuildCompatibleExtension} implementations.
 *
 * @since 4.0
 */
public interface BuildServices extends Prioritized {
    /**
     * @return the {@link AnnotationBuilderFactory} instance, never {@code null}
     */
    AnnotationBuilderFactory annotationBuilderFactory();
}
