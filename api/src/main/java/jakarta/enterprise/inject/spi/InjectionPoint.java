/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jakarta.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Set;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;

/**
 * <p>
 * Provides access to metadata about an injection point. May represent an {@linkplain jakarta.inject.Inject injected field} or a
 * parameter of a {@linkplain jakarta.inject.Inject bean constructor}, {@linkplain jakarta.inject.Inject initializer method},
 * {@linkplain Produces producer method}, {@linkplain Disposes disposer method} or {@linkplain Observes observer method}.
 * If the injection point does not belong to a bean or belongs to a synthetic bean, some methods may return {@code null}.
 * </p>
 *
 * <p>
 * If the injection point is a dynamically obtained instance, then the required type and required qualifiers are
 * defined by the {@link Instance} and other metadata reflect the injection point of the {@code Instance}.
 * If the {@code Instance} is not injected, some methods may return {@code null}.
 * </p>
 *
 * <p>
 * Occasionally, a bean with scope {@link Dependent @Dependent} needs to access metadata relating to the object to which it
 * belongs. The bean may inject an {@code InjectionPoint} representing the injection point into which the bean was injected.
 * </p>
 *
 * <p>
 * For example, the following producer method creates injectable {@code Logger}s. The log category of a
 * {@code Logger} depends upon the class of the object into which it is injected.
 * </p>
 *
 * <pre>
 * &#064;Produces
 * Logger createLogger(InjectionPoint injectionPoint) {
 *     return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
 * }
 * </pre>
 *
 * <p>
 * Only {@linkplain Dependent dependent} objects may obtain information about the injection point to which they belong.
 * </p>
 *
 * @author Gavin King
 * @author Pete Muir
 */
public interface InjectionPoint {

    /**
     * Returns the required type of injection point.
     *
     * @return the required type, never {@code null}
     */
    public Type getType();

    /**
     * Returns the required qualifiers of the injection point.
     *
     * @return the required qualifiers, never {@code null}
     */
    public Set<Annotation> getQualifiers();

    /**
     * Returns the {@link Bean} that defines the injection point.
     * <p>
     * If the injection point belongs to a synthetic bean, may return {@code null}.
     * If the injection point does not belong to a bean or belongs to a dynamically obtained instance
     * where the {@code Instance} is not injected, returns {@code null}.
     *
     * @return the bean that defines the injection point, may be {@code null}
     */
    public Bean<?> getBean();

    /**
     * Returns the {@link java.lang.reflect.Field Field} in the case of field injection,
     * the {@link java.lang.reflect.Method Method} in the case of method parameter injection,
     * or the {@link java.lang.reflect.Constructor Constructor} in the case of constructor parameter injection.
     * <p>
     * If the injection point belongs to a synthetic bean, may return {@code null}.
     * If the injection point belongs to a dynamically obtained instance where the {@code Instance}
     * is not injected, returns {@code null}.
     *
     * @return the member, may be {@code null}
     */
    public Member getMember();

    /**
     * Returns an {@link AnnotatedField} or {@link AnnotatedParameter}, depending on
     * whether the injection point is a field or a constructor/method parameter.
     * <p>
     * If the injection point belongs to a synthetic bean, may return {@code null}.
     * If the injection point belongs to a dynamically obtained instance where the {@code Instance}
     * is not injected, returns {@code null}.
     * <p>
     * May always return {@code null} when the container only supports CDI Lite.
     *
     * @return the annotated member, may be {@code null}
     */
    public Annotated getAnnotated();

    /**
     * Returns whether the injection point is a decorator {@link jakarta.decorator.Delegate @Delegate} injection point.
     *
     * @return {@code true} if the injection point is a decorator delegate injection point, and {@code false} otherwise
     */
    public boolean isDelegate();

    /**
     * Returns whether the {@link #getMember()} method returns a {@code Field} which is declared {@code transient}.
     *
     * @return {@code true} if the injection point is a {@code transient} field, and {@code false} otherwise
     */
    public boolean isTransient();
}
