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

import javax.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;

/**
 * <p>
 * The container fires an event of this type for each Java class or interface it discovers in a bean archive, before it reads
 * the declared annotations.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link javax.enterprise.inject.spi.AnnotatedType} by calling either {@link #setAnnotatedType(AnnotatedType)} or {@link #configureAnnotatedType()}.
 * If both methods are called within an observer notification an {@link IllegalStateException} is thrown.
 * The container must use the final value of this property, after all observers have been called, to discover the types and read the annotations of the program elements.
 * </p>
 * <p>
 * For example, the following observer decorates the {@link javax.enterprise.inject.spi.AnnotatedType} for every class that is
 * discovered by the container.
 * </p>
 * 
 * <pre>
 * public &lt;T&gt; void decorateAnnotatedType(@Observes ProcessAnnotatedType&lt;T&gt; pat) {
 *     pat.setAnnotatedType(decorate(pat.getAnnotatedType()));
 * }
 * </pre>
 * <p>
 * If any observer method of a {@code ProcessAnnotatedType} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @author David Allen
 * @author Antoine Sabot-Durand
 * @see AnnotatedType
 * @param <X> The class being annotated
 */
public interface ProcessAnnotatedType<X> {
    /**
     * Returns the {@link javax.enterprise.inject.spi.AnnotatedType} object that will be used by the container to read the
     * declared annotations.
     * 
     * @return the {@code AnnotatedType} object
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public AnnotatedType<X> getAnnotatedType();

    /**
     * Replaces the {@link javax.enterprise.inject.spi.AnnotatedType}.
     * 
     * @param type the new {@link javax.enterprise.inject.spi.AnnotatedType} object to use
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void setAnnotatedType(AnnotatedType<X> type);

    /**
     * Returns an {@link AnnotatedTypeConfigurator} initialized with the {@link AnnotatedType} processed by this event
     * to configure a new AnnotatedType that will replace the original one at the end of the observer invocation.
     *
     * Each call returns the same AnnotatedTypeConfigurator.
     *
     * @return a non reusable {@link AnnotatedTypeConfigurator} to configure the replacing AnnotatedType
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public AnnotatedTypeConfigurator<X> configureAnnotatedType();

    /**
     * Forces the container to ignore this type.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void veto();
}
