/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

import javax.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;
import java.util.List;

/**
 * <p>
 * This event type is thrown by the container after type discovery is complete. If any observer method of the
 * {@code AfterTypeDiscovery} event throws an exception, the exception is treated as a definition error by the container.
 * </p>
 * <p>
 * Any observer of this event is permitted to add classes to, or remove classes from, the list of alternatives, list of
 * interceptors or list of decorators. The container will use the final values of these lists, after all observers have been
 * called, to determine the enabled alternatives, interceptors, and decorators for application.
 * Changes made to these lists after the invocation of the last observer method of the {@code AfterTypeDiscovery} are ignored.
 * </p>
 *
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 * @since 1.1
 */
public interface AfterTypeDiscovery {

    /**
     * @return the list of enabled alternatives for the application, sorted by priority in ascending order. Alternatives enabled for a bean archive are not included.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public List<Class<?>> getAlternatives();

    /**
     * @return the list of enabled interceptors for the application, sorted by priority in ascending order. Interceptors enabled for a bean archive are not included.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public List<Class<?>> getInterceptors();

    /**
     * @return the list of enabled decorators for the application, sorted by priority in ascending order. Decorators enabled for a bean archive are not included.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public List<Class<?>> getDecorators();

    /**
     * <p>
     * Adds a given {@link javax.enterprise.inject.spi.AnnotatedType} to the set of types which will be scanned during bean
     * discovery.
     * </p>
     *
     * <p>
     * Thanks to the id parameter, this method allows multiple annotated types, based on the same underlying type, to be defined. {@link AnnotatedType}s
     * discovered by the container use the fully qualified class name of {@link AnnotatedType#getJavaClass()} to identify the
     * type.
     * </p>
     *
     * <p>
     * {@link AfterBeanDiscovery#getAnnotatedType(Class, String)} and {@link AfterBeanDiscovery#getAnnotatedTypes(Class)} allows
     * annotated types to be obtained by identifier.
     * </p>
     *
     * @param type The {@link javax.enterprise.inject.spi.AnnotatedType} to add for later scanning
     * @param id the identifier used to distinguish this AnnotatedType from an other one based on the same underlying type
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addAnnotatedType(AnnotatedType<?> type, String id);

    /**
     * <p>
     * Obtains a new {@link AnnotatedTypeConfigurator} to configure a new {@link javax.enterprise.inject.spi.AnnotatedType} and
     * add it to the set of types which will be scanned during bean discovery at the end of the observer invocation.
     * Calling this method multiple times will return a new AnnotatedTypeConfigurator.
     * </p>
     *
     * <p>
     * Thanks to the id parameter, this method allows multiple annotated types, based on the same underlying type, to be defined. {@link AnnotatedType}s
     * discovered by the container use the fully qualified class name of {@link AnnotatedType#getJavaClass()} to identify the
     * type.
     * </p>
     *
     * <p>
     * {@link AfterBeanDiscovery#getAnnotatedType(Class, String)} and {@link AfterBeanDiscovery#getAnnotatedTypes(Class)} allows
     * annotated types to be obtained by identifier.
     * </p>
     *
     * Each call returns a new AnnotatedTypeConfigurator.
     *
     *
     * @param type class used to initialized the type and annotations on the {@link AnnotatedTypeConfigurator}
     * @param id the identifier used to distinguish this AnnotatedType from an other one based on the same underlying type
     * @return a non reusable {@link AnnotatedTypeConfigurator} to configure the new AnnotatedType
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public <T> AnnotatedTypeConfigurator<T> addAnnotatedType(Class<T> type, String id);

}
