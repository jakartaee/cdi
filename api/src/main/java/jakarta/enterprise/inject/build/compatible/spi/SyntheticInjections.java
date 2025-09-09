/*
 * Copyright (c) 2025 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.util.TypeLiteral;

/**
 * Contains injectable references for injection points registered for a synthetic bean.
 * A synthetic bean creation/destruction function can look up beans in this container
 * that were previously registered using
 * {@link SyntheticBeanBuilder#withInjectionPoint(Type, AnnotationInfo...) SyntheticBeanBuilder.withInjectionPoint()}.
 *
 * @since 5.0
 */
public interface SyntheticInjections {
    /**
     * Returns an injectable reference to a bean of given {@code type} with given {@code qualifiers},
     * assuming an injection point with the same type and qualifiers was registered
     * on the {@link SyntheticBeanBuilder}. Returns {@code null} if the type/qualifiers
     * combination was not registered on the {@code SyntheticBeanBuilder}.
     * <p>
     * If no qualifier is passed, {@code @Default} is assumed.
     *
     * @param type the type of the bean to lookup, must not be {@code null}
     * @param qualifiers the qualifiers of the bean to lookup, must not be {@code null}
     * @return injectable reference to the bean, or {@code null} if no such type/qualifiers combination was registered
     * @param <T> the type of the bean to lookup
     */
    <T> T get(Class<T> type, Annotation... qualifiers);

    /**
     * Returns an injectable reference to a bean of given {@code type} with given {@code qualifiers},
     * assuming an injection point with the same type and qualifiers was registered
     * on the {@link SyntheticBeanBuilder}. Returns {@code null} if the type/qualifiers
     * combination was not registered on the {@code SyntheticBeanBuilder}.
     * <p>
     * If no qualifier is passed, {@code @Default} is assumed.
     *
     * @param type the type of the bean to lookup, must not be {@code null}
     * @param qualifiers the qualifiers of the bean to lookup, must not be {@code null}
     * @return injectable reference to the bean, or {@code null} if no such type/qualifiers combination was registered
     * @param <T> the type of the bean to lookup
     */
    <T> T get(TypeLiteral<T> type, Annotation... qualifiers);
}
