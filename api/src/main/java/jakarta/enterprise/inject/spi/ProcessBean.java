/*
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
package jakarta.enterprise.inject.spi;

import jakarta.decorator.Decorator;

/**
 * <p>
 * The container fires an event of this type for each enabled bean, interceptor or decorator deployed in a bean archive, before
 * registering the {@link Bean} object.
 * </p>
 * <p>
 * The event object type depends upon what kind of bean was discovered:
 * </p>
 * <ul>
 * <li>For a managed bean with bean class X, the container must raise an event of type
 * {@link ProcessManagedBean}.</li>
 * <li>For a session bean with bean class X, the container must raise an event of type
 * {@link ProcessSessionBean}.</li>
 * <li>For a producer method with method return type X of a bean with bean class T, the container must raise an event of type
 * {@link ProcessProducerMethod}.</li>
 * <li>For a producer field with field type X of a bean with bean class T, the container must raise an event of type
 * {@link ProcessProducerField}.</li>
 * <li>For a custom implementation of {@link Bean}, the container must raise an event of type {@link ProcessSyntheticBean}.</li>
 * </ul>
 * <p>
 * Resources are considered to be producer fields.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessBean} event throws an exception, the exception is treated as a definition error by
 * the container.
 * </p>
 *
 * <p>CDI Lite implementations are not required to provide support for Portable Extensions.</p>
 *
 * @see Bean
 * @author David Allen
 * @param <X> The class of the bean
 */
public interface ProcessBean<X> {

    /**
     * Returns the {@link AnnotatedType} representing the bean class, the
     * {@link AnnotatedMethod} representing the producer method, or the
     * {@link AnnotatedField} representing the producer field.
     * 
     * <p>
     * If invoked upon a {@link ProcessSyntheticBean} event, non-portable behavior results and the returned value should be ignored.
     * </p>
     * 
     * @return the {@link Annotated} for the bean being registered
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Annotated getAnnotated();

    /**
     * Returns the {@link Bean} object that is about to be registered. The
     * {@link Bean} may implement {@link Interceptor} or
     * {@link Decorator}.
     * 
     * @return the {@link Bean} object about to be registered
     * @throws IllegalStateException if called outside of the observer method invocation 
     */
    public Bean<X> getBean();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t The definition error to register as a {@link java.lang.Throwable}
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);
}
