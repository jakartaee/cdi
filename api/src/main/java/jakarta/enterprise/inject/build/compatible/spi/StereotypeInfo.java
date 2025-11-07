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

import java.util.Collection;

import jakarta.enterprise.lang.model.AnnotationInfo;

/**
 * A stereotype. May define {@linkplain #defaultScope() default scope},
 * a set of {@linkplain #interceptorBindings() interceptor bindings},
 * default {@linkplain #priority() priority}, whether all beans with
 * the stereotype are {@linkplain #isAlternative() alternatives}
 * or {@linkplain #isReserve() reserves}, are {@linkplain #isEager() eagerly initialized},
 * or have {@linkplain #isNamed() default names}.
 *
 * @since 4.0
 */
public interface StereotypeInfo {
    /**
     * Returns the {@linkplain ScopeInfo default scope} defined by this stereotype.
     * Returns {@code null} if this stereotype does not define a default scope.
     *
     * @return the default scope or {@code null}
     */
    ScopeInfo defaultScope();

    /**
     * Returns the set of {@linkplain AnnotationInfo interceptor binding annotations} defined by this stereotype.
     * Returns an empty collection if this stereotype does not define any interceptor binding.
     *
     * @return immutable collection of {@linkplain AnnotationInfo interceptor binding annotations}, never {@code null}
     */
    Collection<AnnotationInfo> interceptorBindings();

    /**
     * Returns whether this stereotype is meta-annotated {@link jakarta.enterprise.inject.Alternative @Alternative}.
     * This means that all beans with this stereotype are alternatives.
     *
     * @return whether this stereotype is meta-annotated {@link jakarta.enterprise.inject.Alternative @Alternative}
     */
    boolean isAlternative();

    /**
     * Returns whether this stereotype is meta-annotated {@link jakarta.enterprise.inject.Reserve @Reserve}.
     * This means that all beans with this stereotype are reserves.
     *
     * @return whether this stereotype is meta-annotated {@link jakarta.enterprise.inject.Reserve @Reserve}
     * @since 5.0
     */
    boolean isReserve();

    /**
     * Returns the priority value this stereotype declares using the {@link jakarta.annotation.Priority @Priority}
     * meta-annotation. Priority is used to select alternatives and reserves for application and order them
     * during typesafe resolution, and to enable interceptors and order them during invocation.
     * <p>
     * Returns {@code null} if this stereotype is not meta-annotated {@code @Priority}.
     *
     * @return the {@link jakarta.annotation.Priority @Priority} value declared by this stereotype, or {@code null}
     *         if this stereotype is not meta-annotated {@code @Priority}
     */
    Integer priority();

    /**
     * Returns whether this stereotype is meta-annotated {@link jakarta.enterprise.context.Eager @Eager}.
     * This means that all beans with this stereotype are eagerly initialized.
     *
     * @return whether this stereotype is meta-annotated {@link jakarta.enterprise.context.Eager @Eager}
     * @since 5.0
     */
    boolean isEager();

    /**
     * Returns whether this stereotype is meta-annotated {@link jakarta.inject.Named @Named}.
     * This means that all beans with this stereotype have default bean names.
     *
     * @return whether this stereotype is meta-annotated {@link jakarta.inject.Named @Named}
     */
    boolean isNamed();
}
