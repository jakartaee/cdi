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

import jakarta.enterprise.inject.spi.InterceptionType;
import jakarta.enterprise.lang.model.AnnotationInfo;

import java.util.Collection;

/**
 * Interceptors are managed beans annotated {@link jakarta.interceptor.Interceptor @Interceptor}.
 * An interceptor declares a set of {@linkplain jakarta.interceptor.InterceptorBinding interceptor binding annotations},
 * used to associate the interceptor with target beans. It also declares at most one interceptor method
 * for each interception type. Interception types are:
 * <ul>
 * <li>{@link jakarta.interceptor.AroundInvoke @AroundInvoke}: intercept business method invocations,</li>
 * <li>{@link jakarta.interceptor.AroundConstruct @AroundConstruct}: intercept constructor invocations,</li>
 * <li>{@link jakarta.annotation.PostConstruct @PostConstruct}: called after the container creates the target instance
 * and completes dependency injection,</li>
 * <li>{@link jakarta.annotation.PreDestroy @PreDestroy}: called before the container destroys the target instance.</li>
 * </ul>
 * Finally, an interceptor also needs to declare a {@link jakarta.annotation.Priority @Priority} to become enabled.
 * <p>
 * Note that this description applies to CDI Lite. There are more ways to declare an interceptor,
 * but those are only present in CDI Full.
 *
 * @since 4.0
 */
public interface InterceptorInfo extends BeanInfo {
    /**
     * Returns the set of {@linkplain jakarta.interceptor.InterceptorBinding interceptor binding annotations}
     * declared on this interceptor.
     *
     * @return immutable set of interceptor binding annotations, never {@code null}
     */
    Collection<AnnotationInfo> interceptorBindings();

    /**
     * Returns whether this interceptor declares an interceptor method for given
     * {@linkplain InterceptionType interception type}.
     *
     * @param interceptionType the type of interception
     * @return whether this interceptor declares an interceptor method for given interception type
     */
    boolean intercepts(InterceptionType interceptionType);

    // ---

    @Override
    default boolean isInterceptor() {
        return true;
    }

    @Override
    default InterceptorInfo asInterceptor() {
        return this;
    }
}
