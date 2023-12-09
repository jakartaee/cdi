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

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;

/**
 * Allows adding annotations to and removing annotations from a method parameter.
 * Note that the method parameter is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 * @since 4.0
 */
public interface ParameterConfig extends DeclarationConfig {
    /**
     * Returns the {@link ParameterInfo} corresponding to this transformed method parameter.
     *
     * @return the {@link ParameterInfo} corresponding to this transformed method parameter, never {@code null}
     */
    @Override
    ParameterInfo info();

    /**
     * Adds a marker annotation of given type to this method parameter.
     * Does not allow configuring annotation members.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ParameterConfig addAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Adds given annotation to this method parameter. The {@link AnnotationInfo} can be obtained
     * from an annotation target, or constructed from scratch using {@link AnnotationBuilder}.
     *
     * @param annotation the annotation to add to this method parameter, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ParameterConfig addAnnotation(AnnotationInfo annotation);

    /**
     * Adds given annotation to this method parameter. The annotation instance is typically
     * a subclass of {@link jakarta.enterprise.util.AnnotationLiteral AnnotationLiteral}.
     *
     * @param annotation the annotation to add to this method parameter, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ParameterConfig addAnnotation(Annotation annotation);

    /**
     * Removes all annotations matching given predicate from this method parameter.
     *
     * @param predicate an annotation predicate, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ParameterConfig removeAnnotation(Predicate<AnnotationInfo> predicate);

    /**
     * Removes all annotations from this method parameter.
     *
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ParameterConfig removeAllAnnotations();
}
