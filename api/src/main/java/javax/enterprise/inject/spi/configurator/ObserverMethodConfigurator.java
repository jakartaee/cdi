/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
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

package javax.enterprise.inject.spi.configurator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.event.ObserverException;
import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.EventContext;
import javax.enterprise.inject.spi.ObserverMethod;
import javax.enterprise.inject.spi.ProcessObserverMethod;

/**
 * <p>
 * An {@link ObserverMethodConfigurator} can configure an {@link ObserverMethod}. The container must provide an implementation
 * of this interface.
 * </p>
 * 
 * <p>
 * This configurator is not thread safe and shall not be used concurrently.
 * </p>
 *
 * @param <T> type of the event the configured ObserverMethod will observe
 * @author Antoine Sabot-Durand
 * @see ProcessObserverMethod#configureObserverMethod()
 * @see AfterBeanDiscovery#addObserverMethod()
 * @since 2.0
 */
public interface ObserverMethodConfigurator<T> {

    /**
     * Read observer meta data from a existing {@link java.lang.reflect.Method}
     *
     * @param method to read meta data from
     * @return self
     */
    ObserverMethodConfigurator<T> read(Method method);

    /**
     * Read observer meta data from a existing {@link AnnotatedMethod}
     *
     * @param method to read meta data from
     * @return self
     */
    ObserverMethodConfigurator<T> read(AnnotatedMethod<?> method);

    /**
     * Read observer meta data from a existing ObserverMethod
     *
     * @param method to read meta data from
     * @return self
     */
    ObserverMethodConfigurator<T> read(ObserverMethod<T> method);

    /**
     * Set the class of the Bean containing this observer. If not set, the extension class is used.
     *
     * @param type the bean class containing this configurator.
     * @return self
     */
    ObserverMethodConfigurator<T> beanClass(Class<?> type);

    /**
     * Set the type of the observed event
     *
     * @param type of the observed event
     * @return self
     */
    ObserverMethodConfigurator<T> observedType(Type type);

    /**
     * Add the qualifier to the observed event
     *
     * @param qualifier to add to event
     * @return self
     */
    ObserverMethodConfigurator<T> addQualifier(Annotation qualifier);

    /**
     * Add all the qualifiers to the Observed event
     *
     * @param qualifiers to add to event
     * @return self
     */
    ObserverMethodConfigurator<T> addQualifiers(Annotation... qualifiers);

    /**
     * Add all the qualifiers to the Observed event
     *
     * @param qualifiers to add to event
     * @return self
     */
    ObserverMethodConfigurator<T> addQualifiers(Set<Annotation> qualifiers);

    /**
     * Replace all qualifiers on the Observed event.
     *
     * @param qualifiers to put on event
     * @return self
     */
    ObserverMethodConfigurator<T> qualifiers(Annotation... qualifiers);

    /**
     * Replace all qualifiers on the Observed event.
     *
     * @param qualifiers to put on event
     * @return self
     */
    ObserverMethodConfigurator<T> qualifiers(Set<Annotation> qualifiers);

    /**
     * Set the {@link Reception} mode for the observer to build
     *
     * @param reception reception type
     * @return self
     */
    ObserverMethodConfigurator<T> reception(Reception reception);

    /**
     * Set the {@link TransactionPhase} for the observer to build
     *
     * @param transactionPhase phase for the observer
     * @return self
     */
    ObserverMethodConfigurator<T> transactionPhase(TransactionPhase transactionPhase);

    /**
     * Set the priority for the observer to build
     *
     * @param priority priority of the observer
     * @return self
     */
    ObserverMethodConfigurator<T> priority(int priority);

    /**
     * Define an operation that accepts a context of a fired event.
     *
     * @param callback to call for the event notification
     * @return self
     */
    ObserverMethodConfigurator<T> notifyWith(EventConsumer<T> callback);

    /**
     * Allows modification of the asynchronous status of the observer to build.
     *
     * @param async async status
     * @return self
     */
    ObserverMethodConfigurator<T> async(boolean async);

    /**
     * Represents an operation that accepts a context of a fired event.
     * 
     * @author Martin Kouba
     *
     * @param <T>
     * @see EventContext
     */
    @FunctionalInterface
    interface EventConsumer<T> {

        /**
         * 
         * @param eventContext the {@link EventContext} used to fire the event
         * @throws Exception The thrown checked exception is wrapped and rethrown as an (unchecked) {@link ObserverException}
         */
        void accept(EventContext<T> eventContext) throws Exception;

    }
}
