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

import javax.enterprise.inject.New;
import javax.enterprise.inject.spi.configurator.BeanAttributesConfigurator;

/**
 * <p>
 * The container fires an event of this type for each enabled bean, interceptor or decorator deployed in a bean archive before
 * registering the {@link javax.enterprise.inject.spi.Bean} object.
 * </p>
 * <p>
 * No event is fired for {@link New} qualified beans.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link BeanAttributes} by calling either {@link #setBeanAttributes(BeanAttributes)} or {@link #configureBeanAttributes()}.
 * If both methods are called within an observer notification an {@link IllegalStateException} is thrown.
 * The container must use the final value of this property, after all observers have been called, to manage instances of the bean.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessBeanAttributes} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 * @param <T> The class of the bean
 * @since 1.1
 */
public interface ProcessBeanAttributes<T> {

    /**
     * @return the {@link AnnotatedType} representing the managed bean class or session bean class, the {@link AnnotatedMethod}
     *         representing the producer field, or the {@link AnnotatedField} representing the producer field
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Annotated getAnnotated();

    /**
     * @return the {@link BeanAttributes} object that will be used by the container to manage instances of the bean
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public BeanAttributes<T> getBeanAttributes();

    /**
     * Replaces the {@link BeanAttributes}.
     * 
     * @param beanAttributes the new {@link BeanAttributes} to use
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void setBeanAttributes(BeanAttributes<T> beanAttributes);

    /**
     * returns a {@link BeanAttributesConfigurator} initialized with the {@link BeanAttributes} processed by this event
     * to configure a new BeanAttributes that will replace the original one at the end of the observer invocation.
     *
     * Each call returns the same BeanAttributesConfigurator.
     *
     * @return a non reusable {@link BeanAttributesConfigurator} to configure the replacing BeanAttributes
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public BeanAttributesConfigurator<T> configureBeanAttributes();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t the error to add
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);

    /**
     * Forces the container to ignore the bean.
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void veto();


    /**
     * <p>Instructs the container to ignore all non-static, final methods with public, protected or default visibility
     * declared on any bean type of the specific bean during validation of injection points that require proxyable bean type.</p>
     *
     * <p>These method should never be invoked upon bean instances. Otherwise, unpredictable behavior results.</p>
     *
     *
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public void ignoreFinalMethods();
}
