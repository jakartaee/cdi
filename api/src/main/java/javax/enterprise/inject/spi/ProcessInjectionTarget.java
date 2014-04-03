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
 * The container fires an event of this type for every Java EE component class supporting injection that may be instantiated by
 * the container at runtime, including every managed bean declared using {@code javax.annotation.ManagedBean}, EJB session or
 * message-driven bean, enabled bean, enabled interceptor or enabled decorator.
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link javax.enterprise.inject.spi.InjectionTarget}. The
 * container must use the final value of this property, after all observers have been called, whenever it performs injection
 * upon the managed bean, session bean or other Java EE component class supporting injection.
 * </p>
 * <p>
 * For example, this observer decorates the {@code InjectionTarget} for all servlets.
 * </p>
 * 
 * <pre>
 * public &lt;T extends Servlet&gt; void decorateServlet(@Observes ProcessInjectionTarget&lt;T&gt; pit) {
 *     pit.setInjectionTarget(decorate(pit.getInjectionTarget()));
 * }
 * </pre>
 * <p>
 * If any observer method of a {@code ProcessInjectionTarget} event throws an exception, the exception is treated as a
 * definition error by the container.
 * </p>
 * 
 * @see InjectionTarget
 * @author David Allen
 * @param <X> The managed bean class, session bean class or Java EE component class supporting injection
 */
public interface ProcessInjectionTarget<X> {
    /**
     * Returns the {@link javax.enterprise.inject.spi.AnnotatedType} representing the managed bean class, session bean class or
     * other Java EE component class supporting injection.
     * 
     * @return the {@link javax.enterprise.inject.spi.AnnotatedType} of the bean with an injection target
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public AnnotatedType<X> getAnnotatedType();

    /**
     * Returns the {@link javax.enterprise.inject.spi.InjectionTarget} object that will be used by the container to perform
     * injection.
     * 
     * @return the {@link javax.enterprise.inject.spi.InjectionTarget} object which performs the injection
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public InjectionTarget<X> getInjectionTarget();

    /**
     * Replaces the {@link javax.enterprise.inject.spi.InjectionTarget} which performs injection for this target.
     * 
     * @param injectionTarget The new {@link javax.enterprise.inject.spi.InjectionTarget} to use
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void setInjectionTarget(InjectionTarget<X> injectionTarget);

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t A {@link java.lang.Throwable} representing the definition error
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDefinitionError(Throwable t);
}
