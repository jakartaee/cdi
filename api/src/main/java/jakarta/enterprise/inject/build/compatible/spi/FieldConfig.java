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
import jakarta.enterprise.lang.model.declarations.FieldInfo;

/**
 * Allows adding annotations to and removing annotations from a field.
 * Note that the field is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 * @since 4.0
 */
public interface FieldConfig extends DeclarationConfig {
    /**
     * Returns the {@link FieldInfo} corresponding to this transformed field.
     *
     * @return the {@link FieldInfo} corresponding to this transformed field, never {@code null}
     */
    @Override
    FieldInfo info();

    /**
     * Adds a marker annotation of given type to this field.
     * Does not allow configuring annotation members.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    FieldConfig addAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Adds given annotation to this field. The {@link AnnotationInfo} can be obtained
     * from an annotation target, or constructed from scratch using {@link AnnotationBuilder}.
     *
     * @param annotation the annotation to add to this field, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    FieldConfig addAnnotation(AnnotationInfo annotation);

    /**
     * Adds given annotation to this field. The annotation instance is typically
     * a subclass of {@link jakarta.enterprise.util.AnnotationLiteral AnnotationLiteral}.
     *
     * @param annotation the annotation to add to this field, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    FieldConfig addAnnotation(Annotation annotation);

    /**
     * Removes all annotations matching given predicate from this field.
     *
     * @param predicate an annotation predicate, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    FieldConfig removeAnnotation(Predicate<AnnotationInfo> predicate);

    /**
     * Removes all annotations from this field.
     *
     * @return this configurator object, to allow fluent usage
     */
    @Override
    FieldConfig removeAllAnnotations();
}
