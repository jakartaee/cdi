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
import jakarta.enterprise.lang.model.declarations.DeclarationInfo;

/**
 * Allows adding annotations to and removing annotations from a declaration.
 * Note that the declaration is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 * @since 4.0
 */
public interface DeclarationConfig {
    /**
     * Returns the {@link DeclarationInfo} corresponding to this transformed declaration.
     *
     * @return the {@link DeclarationInfo} corresponding to this transformed declaration, never {@code null}
     */
    DeclarationInfo info();

    /**
     * Adds a marker annotation of given type to this declaration.
     * Does not allow configuring annotation members.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    DeclarationConfig addAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Adds given annotation to this declaration. The {@link AnnotationInfo} can be obtained
     * from an annotation target, or constructed from scratch using {@link AnnotationBuilder}.
     *
     * @param annotation the annotation to add to this declaration, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    DeclarationConfig addAnnotation(AnnotationInfo annotation);

    /**
     * Adds given annotation to this declaration. The annotation instance is typically
     * a subclass of {@link jakarta.enterprise.util.AnnotationLiteral AnnotationLiteral}.
     *
     * @param annotation the annotation to add to this declaration, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    DeclarationConfig addAnnotation(Annotation annotation);

    /**
     * Removes all annotations matching given predicate from this declaration.
     *
     * @param predicate an annotation predicate, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    DeclarationConfig removeAnnotation(Predicate<AnnotationInfo> predicate);

    /**
     * Removes all annotations from this declaration.
     *
     * @return this configurator object, to allow fluent usage
     */
    DeclarationConfig removeAllAnnotations();
}
