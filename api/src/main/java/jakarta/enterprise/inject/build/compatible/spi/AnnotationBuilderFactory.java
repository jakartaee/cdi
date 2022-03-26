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

import jakarta.enterprise.lang.model.declarations.ClassInfo;

import java.lang.annotation.Annotation;

/**
 * Supports instantiating {@link AnnotationBuilder}.
 * Should not be called directly by users; the static methods on {@link AnnotationBuilder} are preferred.
 *
 * @since 4.0
 */
public interface AnnotationBuilderFactory {
    /**
     * Returns a new {@link AnnotationBuilder} for given annotation type.
     *
     * @param annotationType the annotation type
     * @return a new {@link AnnotationBuilder}
     */
    AnnotationBuilder create(Class<? extends Annotation> annotationType);

    /**
     * Returns a new {@link AnnotationBuilder} for given annotation type.
     *
     * @param annotationType the annotation type
     * @return a new {@link AnnotationBuilder}
     */
    AnnotationBuilder create(ClassInfo annotationType);
}
