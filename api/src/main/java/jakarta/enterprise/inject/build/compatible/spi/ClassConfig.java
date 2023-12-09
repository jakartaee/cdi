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
import java.util.Collection;
import java.util.function.Predicate;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * Allows adding annotations to and removing annotations from a class.
 * Note that the class is not physically altered, the modifications
 * are only seen by the CDI container.
 *
 * @see Enhancement
 * @since 4.0
 */
public interface ClassConfig extends DeclarationConfig {
    /**
     * Returns the {@link ClassInfo} corresponding to this transformed class.
     *
     * @return the {@link ClassInfo} corresponding to this transformed class, never {@code null}
     */
    @Override
    ClassInfo info();

    /**
     * Adds a marker annotation of given type to this class.
     * Does not allow configuring annotation members.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ClassConfig addAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Adds given annotation to this class. The {@link AnnotationInfo} can be obtained
     * from an annotation target, or constructed from scratch using {@link AnnotationBuilder}.
     *
     * @param annotation the annotation to add to this class, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ClassConfig addAnnotation(AnnotationInfo annotation);

    /**
     * Adds given annotation to this class. The annotation instance is typically
     * a subclass of {@link jakarta.enterprise.util.AnnotationLiteral AnnotationLiteral}.
     *
     * @param annotation the annotation to add to this class, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ClassConfig addAnnotation(Annotation annotation);

    /**
     * Removes all annotations matching given predicate from this class.
     *
     * @param predicate an annotation predicate, must not be {@code null}
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ClassConfig removeAnnotation(Predicate<AnnotationInfo> predicate);

    /**
     * Removes all annotations from this class.
     *
     * @return this configurator object, to allow fluent usage
     */
    @Override
    ClassConfig removeAllAnnotations();

    /**
     * Returns a collection of {@link MethodConfig} objects for each constructor of this class,
     * as defined by {@link ClassInfo#constructors() ClassInfo.constructors}.
     *
     * @return immutable collection of {@link MethodConfig} objects, never {@code null}
     */
    Collection<MethodConfig> constructors();

    /**
     * Returns a collection of {@link MethodConfig} objects for each method of this class,
     * as defined by {@link ClassInfo#methods() ClassInfo.methods}.
     *
     * @return immutable collection of {@link MethodConfig} objects, never {@code null}
     */
    Collection<MethodConfig> methods();

    /**
     * Returns a collection of {@link FieldConfig} objects for each field of this class,
     * as defined by {@link ClassInfo#fields() ClassInfo.fields}.
     *
     * @return immutable collection of {@link FieldConfig} objects, never {@code null}
     */
    Collection<FieldConfig> fields();

    // there's no `Collection<RecordComponentConfig> recordComponents()`, because CDI
    // doesn't know anything about records and treats them as plain classes
}
