/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;

import jakarta.enterprise.util.TypeLiteral;

/**
 * Provides injectable references for injection points registered for a synthetic bean.
 * A synthetic bean creation/destruction function can look up beans in this container
 * that were previously registered using {@code SyntheticBeanBuilder.withInjectionPoint()}.
 * <p>
 * The synthetic bean creation/destruction functions do <em>not</em> share the instance
 * of {@code SyntheticInjections} and do <em>not</em> share the injectable references
 * for registered injection points.
 *
 * @since 5.0
 */
public interface SyntheticInjections {
    /**
     * Returns an injectable reference to a bean of given {@code type} with given {@code qualifiers},
     * assuming an injection point with the same type and qualifiers was registered
     * on the {@link SyntheticBeanBuilder}. Throws {@link IllegalArgumentException}
     * if no such injection point was registered.
     * <p>
     * If no qualifier is passed, {@code @Default} is assumed.
     *
     * @param type the type of the bean to lookup, must not be {@code null}
     * @param qualifiers the qualifiers of the bean to lookup, may be {@code null} or empty
     * @return injectable reference to the bean; may only be {@code null} if the bean is {@code @Dependent}
     * @param <T> the type of the bean to lookup
     * @throws IllegalArgumentException if the type/qualifiers combination was not registered
     */
    <T> T get(Class<T> type, Annotation... qualifiers);

    /**
     * Returns an injectable reference to a bean of given {@code type} with given {@code qualifiers},
     * assuming an injection point with the same type and qualifiers was registered
     * on the {@link SyntheticBeanBuilder}. Throws {@link IllegalArgumentException}
     * if no such injection point was registered.
     * <p>
     * If no qualifier is passed, {@code @Default} is assumed.
     *
     * @param type the type of the bean to lookup, must not be {@code null}
     * @param qualifiers the qualifiers of the bean to lookup, may be {@code null} or empty
     * @return injectable reference to the bean; may only be {@code null} if the bean is {@code @Dependent}
     * @param <T> the type of the bean to lookup
     * @throws IllegalArgumentException if the type/qualifiers combination was not registered
     */
    <T> T get(TypeLiteral<T> type, Annotation... qualifiers);
}
