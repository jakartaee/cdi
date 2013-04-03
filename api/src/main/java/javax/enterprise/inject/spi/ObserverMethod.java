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

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;

/**
 * <p>
 * Represents an {@linkplain javax.enterprise.event.Observes observer method} of an {@linkplain javax.enterprise.inject enabled
 * bean}. Defines everything the container needs to know about an observer method.
 * </p>
 * 
 * @author Gavin King
 * @author David Allen
 * @param <T> the event type
 */
public interface ObserverMethod<T> {
    /**
     * <p>
     * Obtains the {@linkplain Class class} of the type that declares the observer method.
     * </p>
     * 
     * @return the defining {@linkplain Class class}
     */
    public Class<?> getBeanClass();

    /**
     * Obtains the {@linkplain javax.enterprise.event observed event type}.
     * 
     * @return the observed event {@linkplain Type type}
     */
    public Type getObservedType();

    /**
     * Obtains the set of {@linkplain javax.enterprise.event observed event qualifiers}.
     * 
     * @return the observed event {@linkplain javax.inject.Qualifier qualifiers}
     */
    public Set<Annotation> getObservedQualifiers();

    /**
     * Obtains the specified {@link Reception} for the observer method. This indicates if the observer is conditional or not.
     * 
     * @return the {@link Reception}
     */
    public Reception getReception();

    /**
     * Obtains the specified {@link TransactionPhase} for the observer method.
     * 
     * @return the {@link TransactionPhase}
     */
    public TransactionPhase getTransactionPhase();

    /**
     * <p>
     * Calls the observer method, passing the given event object.
     * </p>
     * 
     * <p>
     * The implementation of {@link #notify(Object)} for a custom observer method is responsible for deciding whether to call
     * the method if the {@link #getReception()} returns {@link Reception#IF_EXISTS}.
     * </p>
     * 
     * @param event the event object
     */
    public void notify(T event);

}
