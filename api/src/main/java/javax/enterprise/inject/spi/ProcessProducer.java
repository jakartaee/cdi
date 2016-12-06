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

import javax.enterprise.inject.spi.configurator.ProducerConfigurator;

/**
 * <p>
 * The container fires an event of this type for each {@linkplain javax.enterprise.inject.Produces producer method or field} of
 * each enabled bean, including resources.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@code Producer} by calling either
 * {@link #setProducer(Producer)} or {@link #configureProducer()}. If both methods are called within an observer notification an
 * {@link IllegalStateException} is thrown. The container must use the final value of this property, after all observers have
 * been called, whenever it calls the producer or disposer.
 * </p>
 * <p>
 * For example, this observer decorates the {@code Producer} for the all producer methods and field of type
 * {@code EntityManager}.
 * </p>
 * 
 * <pre>
 * void decorateEntityManager(@Observes ProcessProducer&lt;?, EntityManager&gt; pp) {
 *     pit.setProducer(decorate(pp.getProducer()));
 * }
 * </pre>
 * <p>
 * If any observer method of a {@code ProcessProducer} event throws an exception, the exception is treated as a definition error
 * by the container.
 * </p>
 * 
 * @see Producer
 * @author David Allen
 * @param <T> The bean class of the bean that declares the producer method or field
 * @param <X> The return type of the producer method or the type of the producer field
 */
public interface ProcessProducer<T, X> {
    /**
     * Returns the {@link javax.enterprise.inject.spi.AnnotatedField} representing the producer field or the
     * {@link javax.enterprise.inject.spi.AnnotatedMethod} representing the producer method.
     * 
     * @return the {@link javax.enterprise.inject.spi.AnnotatedMember} representing the producer
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public AnnotatedMember<T> getAnnotatedMember();

    /**
     * Returns the {@link javax.enterprise.inject.spi.Producer} object that will be used by the container to call the producer
     * method or read the producer field.
     * 
     * @return the {@link javax.enterprise.inject.spi.Producer} invoker object used by the container
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Producer<X> getProducer();

    /**
     * Replaces the {@link javax.enterprise.inject.spi.Producer} object that will be used by the container to call the producer
     * method or read the producer field.
     * 
     * @param producer the new {@link javax.enterprise.inject.spi.Producer} object to use
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void setProducer(Producer<X> producer);
    
    /**
     * Returns a {@link ProducerConfigurator} initialized with the {@link Producer} processed by this event, to configure a new
     * {@link Producer} that will replace the original one at the end of the observer invocation.
     * 
     * <p>
     * Each call returns the same configurator instance within an observer notification.
     * </p>
     * 
     * @return a non reusable {@link ProducerConfigurator} to configure the original
     *         {@link javax.enterprise.inject.spi.Producer}.
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public ProducerConfigurator<X> configureProducer();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t The definition error to register as a {@link java.lang.Throwable}
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);
}
