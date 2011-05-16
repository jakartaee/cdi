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
 * The container fires an event of this type for each {@linkplain javax.enterprise.event.Observes observer method} of each
 * enabled bean, before registering the {@link javax.enterprise.inject.spi.ObserverMethod} object.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessObserverMethod} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @see ObserverMethod
 * @author Gavin King
 * @author David Allen
 * @param <T> The type of the event being observed
 * @param <X> The bean type containing the observer method
 * 
 */
// These parameters are the wrong way according to the spec, however Oracle/JCP
// compatibility rules require us to
// keep the wrong ordering
public interface ProcessObserverMethod<T, X> {
    /**
     * The {@link javax.enterprise.inject.spi.AnnotatedMethod} representing the observer method.
     * 
     * @return the {@link javax.enterprise.inject.spi.AnnotatedMethod} representing the observer method
     */
    public AnnotatedMethod<X> getAnnotatedMethod();

    /**
     * The {@link javax.enterprise.inject.spi.ObserverMethod} object that will be used by the container to invoke the observer
     * when a matching event is fired.
     * 
     * @return the {@link javax.enterprise.inject.spi.ObserverMethod} object that will be used by the container to call the
     *         observer method
     */
    public ObserverMethod<T> getObserverMethod();

    /**
     * Registers a definition error with the container, causing the container to abort deployment after bean discovery is
     * complete.
     * 
     * @param t A {@link java.lang.Throwable} representing the definition error
     */
    public void addDefinitionError(Throwable t);
}
