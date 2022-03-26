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

import jakarta.enterprise.context.spi.AlterableContext;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

/**
 * Allows registering custom CDI meta-annotations: qualifiers, interceptor bindings,
 * stereotypes, and scopes. When registering a custom scope, a context class must
 * also be provided.
 *
 * @since 4.0
 */
public interface MetaAnnotations {
    /**
     * Registers {@code annotation} as a qualifier annotation. Only makes sense if the annotation
     * is not meta-annotated {@code @Qualifier}.
     * <p>
     * Returns a {@linkplain ClassConfig class configurator} object that allows transforming meta-annotations
     * on the {@code annotation}.
     *
     * @param annotation annotation type
     * @return the {@linkplain ClassConfig class configurator}, never {@code null}
     */
    ClassConfig addQualifier(Class<? extends Annotation> annotation);

    /**
     * Registers {@code annotation} as an interceptor binding annotation. Only makes sense if the annotation
     * is not meta-annotated {@code @InterceptorBinding}.
     * <p>
     * Returns a {@linkplain ClassConfig class configurator} object that allows transforming meta-annotations
     * on the {@code annotation}.
     *
     * @param annotation annotation type
     * @return the {@linkplain ClassConfig class configurator}, never {@code null}
     */
    ClassConfig addInterceptorBinding(Class<? extends Annotation> annotation);

    /**
     * Registers {@code annotation} as a stereotype annotation. Only makes sense if the annotation
     * is not meta-annotated {@code @Stereotype}.
     * <p>
     * Returns a {@linkplain ClassConfig class configurator} object that allows transforming meta-annotations
     * on the {@code annotation}.
     *
     * @param annotation annotation type
     * @return the {@linkplain ClassConfig class configurator}, never {@code null}
     */
    ClassConfig addStereotype(Class<? extends Annotation> annotation);

    /**
     * Registers custom context for given {@code scopeAnnotation} and given {@code contextClass}.
     * CDI container will create an instance of the context class once to obtain the context object.
     * The context class must be {@code public} and have a {@code public} zero-parameter constructor;
     * it must not be a bean.
     * <p>
     * Whether the scope is normal is discovered from the scope annotation. This means that the scope
     * annotation must be meta-annotated either {@link jakarta.enterprise.context.NormalScope @NormalScope}
     * or {@link jakarta.inject.Scope @Scope}.
     *
     * @param scopeAnnotation the scope annotation type, must not be {@code null}
     * @param contextClass the context class, must not be {@code null}
     * @throws IllegalArgumentException if the {@code scopeAnnotation} is not meta-annotated {@code @NormalScope}
     * or {@code @Scope}
     */
    void addContext(Class<? extends Annotation> scopeAnnotation, Class<? extends AlterableContext> contextClass);

    /**
     * Registers custom context for given {@code scopeAnnotation} and given {@code contextClass}.
     * CDI container will create an instance of the context class once to obtain the context object.
     * The context class must be {@code public} and have a {@code public} zero-parameter constructor;
     * it must not be a bean.
     * <p>
     * The {@code isNormal} parameter determines whether the scope is a normal scope or a pseudo-scope.
     *
     * @param scopeAnnotation the scope annotation type, must not be {@code null}
     * @param isNormal whether the scope is normal
     * @param contextClass the context class, must not be {@code null}
     */
    void addContext(Class<? extends Annotation> scopeAnnotation, boolean isNormal, Class<? extends AlterableContext> contextClass);
}
