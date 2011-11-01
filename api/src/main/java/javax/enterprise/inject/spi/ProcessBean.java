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
 * The container fires an event of this type for each enabled bean, interceptor or decorator deployed in a bean archive, before
 * registering the {@link javax.enterprise.inject.spi.Bean} object.
 * </p>
 * <p>
 * The event object type depends upon what kind of bean was discovered:
 * </p>
 * <ul>
 * <li>For a managed bean with bean class X, the container must raise an event of type
 * {@link javax.enterprise.inject.spi.ProcessManagedBean}<X>.</li>
 * <li>For a session bean with bean class X, the container must raise an event of type
 * {@link javax.enterprise.inject.spi.ProcessSessionBean}<X>.</li>
 * <li>For a producer method with method return type X of a bean with bean class T, the container must raise an event of type
 * {@link javax.enterprise.inject.spi.ProcessProducerMethod}<T, X>.</li>
 * <li>For a producer field with field type X of a bean with bean class T, the container must raise an event of type
 * {@link javax.enterprise.inject.spi.ProcessProducerField}<T, X>.</li>
 * </ul>
 * <p>
 * Resources are considered to be producer fields.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessBean} event throws an exception, the exception is treated as a definition error by
 * the container.
 * </p>
 * 
 * @see Bean
 * @author David Allen
 * @param <X> The class of the bean
 */
public interface ProcessBean<X> {
    /**
     * Returns the {@link javax.enterprise.inject.spi.AnnotatedType} representing the bean class, the
     * {@link javax.enterprise.inject.spi.AnnotatedMethod} representing the producer method, or the
     * {@link javax.enterprise.inject.spi.AnnotatedField} representing the producer field.
     * 
     * @return the {@link javax.enterprise.inject.spi.AnnotatedType} for the bean being registered
     */
    public Annotated getAnnotated();

    /**
     * Returns the {@link javax.enterprise.inject.spi.Bean} object that is about to be registered. The
     * {@link javax.enterprise.inject.spi.Bean} may implement {@link javax.enterprise.inject.spi.Interceptor} or
     * {@link javax.decorator.Decorator}.
     * 
     * @return the {@link javax.enterprise.inject.spi.Bean} object about to be registered
     */
    public Bean<X> getBean();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t The definition error to register as a {@link java.lang.Throwable}
     */
    public void addDefinitionError(Throwable t);
}
