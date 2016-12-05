/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, 2015, Red Hat, Inc., and individual contributors
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

import javax.enterprise.inject.spi.configurator.ObserverMethodConfigurator;

/**
 * <p>
 * The container fires an event of this type for each {@linkplain javax.enterprise.event.Observes observer method} of each
 * enabled bean, before registering the {@link javax.enterprise.inject.spi.ObserverMethod} object.
 * </p>
 * <p>
 * For a custom implementation of {@link ObserverMethod}, the container must raise an event of type {@link ProcessSyntheticObserverMethod}.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link javax.enterprise.inject.spi.ObserverMethod} by calling either {@link #setObserverMethod(ObserverMethod)} or {@link #configureObserverMethod()}.
 * If both methods are called within an observer notification an {@link IllegalStateException} is thrown.
 * The container must use the final value of this property, after all observers have been called, he container must use the final
 * value of this property, after all observers have been called, whenever it performs observer resolution.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessObserverMethod} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 *
 * @see ObserverMethod
 * @author Gavin King
 * @author David Allen
 * @author  Antoine Sabot-Durand
 * @param <T> The type of the event being observed
 * @param <X> The bean type containing the observer method
 */
public interface ProcessObserverMethod<T, X> {
    
    /**
     * The {@link javax.enterprise.inject.spi.AnnotatedMethod} representing the observer method.
     * 
     * <p>
     * If invoked upon a {@link ProcessSyntheticObserverMethod} event, non-portable behavior results and the returned value should be ignored.
     * </p>
     *
     * @return the {@link javax.enterprise.inject.spi.AnnotatedMethod} representing the observer method
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public AnnotatedMethod<X> getAnnotatedMethod();

    /**
     * The {@link javax.enterprise.inject.spi.ObserverMethod} object that will be used by the container to invoke the observer
     * when a matching event is fired.
     *
     * @return the {@link javax.enterprise.inject.spi.ObserverMethod} object that will be used by the container to call the
     *         observer method
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public ObserverMethod<T> getObserverMethod();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t A {@link java.lang.Throwable} representing the definition error
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);

    /**
     * Replaces the {@link javax.enterprise.inject.spi.ObserverMethod}.
     *
     * @param observerMethod the new {@link javax.enterprise.inject.spi.ObserverMethod} object to use
     * @throws IllegalStateException if called outside of the observer method invocation
     *
     * @since 2.0
     */
    public void setObserverMethod(ObserverMethod<T> observerMethod);

    /**
     * Returns a {@link ObserverMethodConfigurator} initialized with the {@link ObserverMethod} processed by this event,
     * to configure a new ObserverMethod that will replace the original one at the end of the observer invocation.
     *
     * Each call returns the same ObserverMethodConfigurator
     *
     * @return a non reusable {@link ObserverMethodConfigurator} to configure the replacing ObserverMethod
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public ObserverMethodConfigurator<T> configureObserverMethod();

    /**
     * Forces the container to ignore the observer method.
     * 
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public void veto();
}
