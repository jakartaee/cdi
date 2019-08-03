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

import javax.enterprise.inject.spi.configurator.InjectionPointConfigurator;

/**
 * <p>
 * The container fires an event of this type for every injection point of every Jakarta EE component class supporting injection
 * that may be instantiated by the container at runtime, including every managed bean declared using
 * {@code javax.annotation.ManagedBean}, Jakarta Enterprise Bean session or message-driven bean, enabled bean, enabled interceptor or enabled
 * decorator.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link javax.enterprise.inject.spi.InjectionPoint} by calling either {@link #setInjectionPoint(InjectionPoint)} or {@link #configureInjectionPoint()}.
 * If both methods are called within an observer notification an {@link IllegalStateException} is thrown.
 * The container must use the final value of this property, after all observers have been called, he container must use the final
 * value of this property, after all observers have been called, whenever it performs injection upon the injection point.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessInjectionPoint} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @see InjectionPoint
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 * @param <X> the declared type of the injection point.
 * @param <T> the bean class of the bean that declares the injection point
 */
public interface ProcessInjectionPoint<T, X> {

    /**
     * @return the InjectionPoint object that will be used by the container to perform injection
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public InjectionPoint getInjectionPoint();

    /**
     * Replaces the InjectionPoint.
     * 
     * @param injectionPoint the new injection point
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void setInjectionPoint(InjectionPoint injectionPoint);

    /**
     * Returns an {@link InjectionPointConfigurator} initialized with the {@link InjectionPoint} processed by this event
     * to configure a new InjectionPoint that will replace the original one at the end of the observer invocation.
     *
     * Each call returns the same InjectionPointConfigurator
     *
     * @return a non reusable {@link InjectionPointConfigurator} to configure the replacing InjectionPoint
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public InjectionPointConfigurator configureInjectionPoint();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t the definition error
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);
}
