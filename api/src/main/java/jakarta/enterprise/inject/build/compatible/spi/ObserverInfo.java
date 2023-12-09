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

import jakarta.enterprise.event.Reception;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;
import jakarta.enterprise.lang.model.types.Type;

/**
 * Observers are:
 *
 * <ul>
 * <li>observer methods</li>
 * <li>synthetic observers</li>
 * </ul>
 *
 * Observer methods directly correspond to a method declaration in program source code.
 * Synthetic observers don't and are instead defined through other mechanisms, such as
 * {@linkplain BuildCompatibleExtension extensions}.
 *
 * @since 4.0
 */
public interface ObserverInfo {
    /**
     * Returns the observed event type of this observer.
     *
     * @return the observed event type of this observer, never {@code null}
     */
    Type eventType();

    /**
     * Returns a collection of observed event qualifiers, represented as {@link AnnotationInfo}.
     *
     * @return immutable collection of observed event qualifiers, never {@code null}
     */
    Collection<AnnotationInfo> qualifiers();

    /**
     * Returns the {@linkplain ClassInfo class} that declares this observer.
     * In case of synthetic observers, returns the class that was designated
     * as a declaring class during synthetic observer registration.
     *
     * @return the class that declares this observer, never {@code null}
     */
    ClassInfo declaringClass();

    /**
     * Returns the {@linkplain MethodInfo declaration} of this observer method.
     * Returns {@code null} if this is a synthetic observer.
     *
     * @return this observer method, or {@code null} if this is a synthetic observer
     */
    MethodInfo observerMethod();

    /**
     * Returns the {@linkplain ParameterInfo event parameter} of this observer method.
     * Returns {@code null} if this is a synthetic observer.
     *
     * @return the event parameter of this observer method, or {@code null} if this is a synthetic observer
     */
    ParameterInfo eventParameter();

    /**
     * Returns the {@link BeanInfo bean} that declares this observer method.
     * Returns {@code null} if this is a synthetic observer.
     *
     * @return the bean declaring this observer method, or {@code null} if this is a synthetic observer
     */
    BeanInfo bean();

    /**
     * Returns whether this observer is synthetic.
     *
     * @return whether this observer is synthetic
     */
    boolean isSynthetic();

    /**
     * Returns the priority of this observer. This is typically defined by adding
     * the {@link jakarta.annotation.Priority @Priority} annotation to the event parameter of the observer method.
     * If the annotation is not used, the default priority, as defined by the CDI specification, is returned,
     *
     * @return the priority of this observer
     */
    int priority();

    /**
     * Returns whether this observer is asynchronous. For observer methods, this means whether
     * this observer method uses {@link jakarta.enterprise.event.ObservesAsync @ObservesAsync}.
     *
     * @return whether this observer is asynchronous
     */
    boolean isAsync();

    /**
     * Returns the {@linkplain Reception reception type} of this observer. Allows distinguishing
     * conditional observer methods from always notified observer methods.
     * Returns {@link Reception#ALWAYS} if this is a synthetic observer.
     *
     * @return the reception type of this observer, never {@code null}
     */
    Reception reception();

    /**
     * Returns the {@link TransactionPhase transaction phase} of this transactional observer.
     * Returns {@link TransactionPhase#IN_PROGRESS} if this is a regular synchronous observer.
     * Returns {@code null} if this is an asynchronous observer.
     *
     * @return the transaction phase of this observer, or {@code null} if this is an asynchronous observer
     */
    TransactionPhase transactionPhase();
}
