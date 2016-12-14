/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, 2016, Red Hat, Inc., and individual contributors
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
 * The container fires an event of this type for each custom implementation of {@link ObserverMethod} added through
 * {@link AfterBeanDiscovery#addObserverMethod(ObserverMethod)} or {@link AfterBeanDiscovery#addObserverMethod()}, before
 * registering the {@link javax.enterprise.inject.spi.ObserverMethod} object.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessSyntheticObserverMethod} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 *
 * @see ObserverMethod
 * @author Martin Kouba
 * @param <T> The type of the event being observed
 * @param <X> The bean type containing the observer method, i.e. {@link ObserverMethod#getBeanClass()}
 * @since 2.0
 */
public interface ProcessSyntheticObserverMethod<T, X> extends ProcessObserverMethod<T, X> {

    /**
     * Get the extension instance which added the {@link ObserverMethod} for which this event is being fired.
     * 
     * @return the extension instance
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Extension getSource();

}
