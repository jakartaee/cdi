/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package javax.enterprise.inject.spi;

import javax.enterprise.context.spi.Context;

/**
 * <p>
 * The event type of the second event fired by the container when it has fully completed the bean discovery process, validated
 * that there are no definition errors relating to the discovered beans, and registered {@link javax.enterprise.inject.spi.Bean}
 * and {@link javax.enterprise.inject.spi.ObserverMethod} objects for the discovered beans, but before detecting deployment
 * problems.
 * </p>
 * <p>
 * A portable extension may take advantage of this event to register {@linkplain javax.enterprise.inject.spi.Bean beans},
 * {@linkplain javax.enterprise.inject.spi.Interceptor interceptors}, {@linkplain javax.decorator.Decorator decorators},
 * {@linkplain javax.enterprise.event.Observes observer methods} and {@linkplain javax.enterprise.context custom context}
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
 * @author David Allen
 */
public interface AfterBeanDiscovery {
    /**
     * Registers a definition error with the container, causing the container to abort deployment after all observers have been
     * notified.
     * 
     * @param t The definition error as a {@link java.lang.Throwable}
     */
    public void addDefinitionError(Throwable t);

    /**
     * Fires an event of type {@link javax.enterprise.inject.spi.ProcessBean} containing the given
     * {@link javax.enterprise.inject.spi.Bean} and then registers the {@link javax.enterprise.inject.spi.Bean} with the
     * container, thereby making it available for injection into other beans. The given {@link javax.enterprise.inject.spi.Bean}
     * may implement {@link javax.enterprise.inject.spi.Interceptor} or {@link javax.decorator.Decorator}.
     * 
     * @param bean The bean to add to the deployment
     */
    public void addBean(Bean<?> bean);

    /**
     * Fires an event of type {@link javax.enterprise.inject.spi.ProcessObserverMethod} containing the given
     * {@link javax.enterprise.inject.spi.ObserverMethod} and then registers the
     * {@link javax.enterprise.inject.spi.ObserverMethod} with the container, thereby making it available for event
     * notifications.
     * 
     * @param observerMethod The custom observer method to add to the deployment
     */
    public void addObserverMethod(ObserverMethod<?> observerMethod);

    /**
     * Registers a custom {@link javax.enterprise.context.spi.Context} object with the container.
     * 
     * @param context The custom context to add to the deployment
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
     * @since 1.1
     */
    public <T> Iterable<AnnotatedType<T>> getAnnotatedTypes(Class<T> type);

}
