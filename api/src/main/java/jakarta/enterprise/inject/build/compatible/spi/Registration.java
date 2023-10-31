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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 3rd phase of {@linkplain BuildCompatibleExtension build compatible extension} execution.
 * Allows observing registered beans and observers.
 * <p>
 * This phase is executed twice.
 * For non-synthetic beans and observers, this phase is executed <em>before</em> {@linkplain Synthesis synthesis}.
 * For synthetic beans and observers, this phase is executed <em>after</em> synthesis.
 * <p>
 * In the following text, the term <em>expected types</em> denotes the set of types defined by
 * the {@link #types() types} member of the {@code @Registration} annotation.
 * <p>
 * Methods annotated {@code @Registration} must declare exactly one parameter of one of these types:
 * <ul>
 * <li>{@link BeanInfo}</li>
 * <li>{@link InterceptorInfo}</li>
 * <li>{@link ObserverInfo}</li>
 * </ul>
 * If a {@code @Registration} method has a parameter of type {@code BeanInfo}, the method is called once
 * for each bean whose set of bean types contains at least one <em>expected type</em>.
 * <p>
 * If the {@code @Registration} method has a parameter of type {@code InterceptorInfo}, the method is called once
 * for each interceptor whose set of bean types contains at least one <em>expected type</em>.
 * <p>
 * If the {@code @Registration} method has a parameter of type {@code ObserverInfo}, the method is called once
 * for each observer whose observed event type is assignable to at least one <em>expected type</em>.
 * <p>
 * Note that {@code InterceptorInfo} is a subtype of {@code BeanInfo}, so if the method has a parameter
 * of type {@code BeanInfo}, it will be called for interceptors as well.
 * <p>
 * If the {@code @Registration} method doesn't declare any parameter of one of these types,
 * or if it declares more than one, the container treats it as a definition error.
 * <p>
 * Additionally, methods annotated {@code @Registration} may declare parameters of these types:
 * <ul>
 * <li>{@link InvokerFactory}</li>
 * <li>{@link Messages}</li>
 * <li>{@link Types}</li>
 * </ul>
 *
 * @since 4.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Registration {
    /**
     * Defines the set of <em>expected types</em>.
     *
     * @return the set of <em>expected types</em>
     */
    Class<?>[] types();
}
