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

/**
 * <p>
 * The container fires an event of this type for each {@linkplain javax.enterprise.inject.Produces producer method or field} of
 * each enabled bean, including resources.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@code Producer}. The container must use the final value
 * of this property, after all observers have been called, whenever it calls the producer or disposer.
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
     */
    public AnnotatedMember<T> getAnnotatedMember();

    /**
     * Returns the {@link javax.enterprise.inject.spi.Producer} object that will be used by the container to call the producer
     * method or read the producer field.
     * 
     * @return the {@link javax.enterprise.inject.spi.Producer} invoker object used by the container
     */
    public Producer<X> getProducer();

    /**
     * Replaces the {@link javax.enterprise.inject.spi.Producer} object that will be used by the container to call the producer
     * method or read the producer field.
     * 
     * @param producer the new {@link javax.enterprise.inject.spi.Producer} object to use
     */
    public void setProducer(Producer<X> producer);

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t The definition error to register as a {@link java.lang.Throwable}
     */
    public void addDefinitionError(Throwable t);
}
