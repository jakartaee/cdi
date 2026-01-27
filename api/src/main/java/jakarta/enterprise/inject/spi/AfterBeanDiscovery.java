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

import jakarta.decorator.Decorator;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.configurator.BeanConfigurator;
import jakarta.enterprise.inject.spi.configurator.ObserverMethodConfigurator;

/**
 * <p>
 * The event type of the second event fired by the container when it has fully completed the bean discovery process, validated
 * that there are no definition errors relating to the discovered beans, and registered {@link Bean}
 * and {@link ObserverMethod} objects for the discovered beans, but before detecting deployment
 * problems.
 * </p>
 * <p>
 * A portable extension may take advantage of this event to register {@linkplain Bean beans},
 * {@linkplain Interceptor interceptors}, {@linkplain Decorator decorators},
 * {@linkplain Observes observer methods} and {@linkplain jakarta.enterprise.context custom context}
 * objects with the container.
 * </p>
 *
 * <pre>
 *     void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager manager) { ... }
 * </pre>
 * <p>
 * If any observer method of the {@code AfterBeanDiscovery} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for Portable Extensions.
 * </p>
 *
 * @author David Allen
 * @author Antoine Sabot-Durand
 */
public interface AfterBeanDiscovery {
    /**
     * Registers a definition error with the container, causing the container to abort deployment after all observers have been
     * notified.
     *
     * @param t The definition error as a {@link java.lang.Throwable}
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);

    /**
     * Fires an event of type {@link ProcessBean} containing the given
     * {@link Bean} and then registers the {@link Bean} with the
     * container, thereby making a bean which is not an interceptor nor a decorator available for injection into other beans.
     * The given {@link Bean} may implement {@link Interceptor} or {@link Decorator}.
     *
     * @param bean The bean to add to the deployment
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addBean(Bean<?> bean);

    /**
     *
     * Obtains a new {@link BeanConfigurator} to configure a new {@link Bean} and add it at the end of the observer invocation.
     * It will then fire an event of type {@link ProcessBean} containing the built
     * {@link Bean} from this configuration and then register it with the
     * container, thereby making it available for injection into other beans.
     *
     * Each call returns a new BeanConfigurator.
     *
     * @param <T> type of bean
     * @return a non reusable {@link BeanConfigurator} to configure the bean to add
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public <T> BeanConfigurator<T> addBean();

    /**
     * Fires an event of type {@link ProcessObserverMethod} containing the given
     * {@link ObserverMethod} and then registers the
     * {@link ObserverMethod} with the container, thereby making it available for event
     * notifications.
     *
     * @param observerMethod The custom observer method to add to the deployment
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addObserverMethod(ObserverMethod<?> observerMethod);

    /**
     * obtains a new {@link ObserverMethodConfigurator} to configure a new {@link ObserverMethod} and add it at the end of the
     * observer invocation.
     * It will then fire an event of type {@link ProcessObserverMethod} containing the built
     * {@link ObserverMethod} from this configuration and then registers the
     * {@link ObserverMethod} with the container, thereby making it available for event
     * notifications.
     *
     * Each call returns a new ObserverMethodConfigurator.
     *
     * @param <T> type of the event the configured ObserverMethod will observe
     * @return a non reusable {@link ObserverMethodConfigurator} instance
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public <T> ObserverMethodConfigurator<T> addObserverMethod();

    /**
     * Registers a custom {@link Context} object with the container.
     *
     * @param context The custom context to add to the deployment
     * @throws DeploymentException if the custom context declares a global built-in scope
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addContext(Context context);

    /**
     * Obtain the {@link AnnotatedType} that may be used to read the annotations of the given class or interface as defined
     * during container initialization.
     *
     * @param <T> the class or interface
     * @param type the {@link java.lang.Class} object
     * @param id the type identifier. If null, the fully qualifier class name of type is used
     * @return the {@link AnnotatedType}
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 1.1
     */
    public <T> AnnotatedType<T> getAnnotatedType(Class<T> type, String id);

    /**
     * Obtain the {@link AnnotatedType}s that may be used to read the annotations of the given class or interface as defined
     * during container initialization.
     *
     * @param <T> the class or interface
     * @param type the {@link java.lang.Class} object
     * @return the {@link AnnotatedType}s
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 1.1
     */
    public <T> Iterable<AnnotatedType<T>> getAnnotatedTypes(Class<T> type);
}
