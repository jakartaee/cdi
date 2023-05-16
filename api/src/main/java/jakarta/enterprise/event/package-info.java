/*
 * Copyright 2010, 2015, Red Hat, Inc., and individual contributors
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
/**
 * <p>Annotations and interfaces relating to events.</p>
 * 
 * <p>{@linkplain jakarta.enterprise.inject Beans} may produce and 
 * consume events. Events allows beans to interact in a completely 
 * decoupled fashion, with no compile-time dependency between the 
 * interacting beans. Most importantly, it allows stateful beans 
 * in one architectural tier of the application to synchronize 
 * their internal state with state changes that occur in a 
 * different tier.</p>
 * 
 * <p>Events may be fired synchronously or asynchronously.</p>
 * 
 * <p>An event comprises:</p>
 * 
 * <ul>
 * <li>A Java object, called the event object</li>
 * <li>A (possibly empty) set of instances of qualifier types, called 
 * the event qualifiers</li>
 * </ul>
 * 
 * <p>The {@link jakarta.enterprise.event.Event} interface is used to 
 * fire events.</p>
 * 
 * <h2>Event objects and event types</h2>
 * 
 * <p>The event object acts as a payload, to propagate state from 
 * producer to consumer. An event object is an instance of a concrete 
 * Java class with no type variables.</p> 
 * 
 * <p>The event types of the event include all superclasses and 
 * interfaces of the runtime class of the event object. An event type 
 * may not contain a type variable.</p>
 * 
 * <h2>Event qualifiers</h2>
 * 
 * <p>The event qualifiers act as topic selectors, allowing the consumer 
 * to narrow the set of events it observes. An event qualifier may be an
 * instance of any {@linkplain jakarta.inject.Qualifier qualifier type}.</p>
 * 
 * <h2>Observer methods</h2>
 * 
 * <p>An {@linkplain jakarta.enterprise.event.Observes observer method} 
 * allows the application to receive and respond synchronously to event notifications.
 * And an {@linkplain jakarta.enterprise.event.ObservesAsync async observer method} 
 * allows the application to receive and respond asynchronously to event notifications.
 * they both act as event consumers, observing events of a specific type, with a
 * specific set of qualifiers. Any Java type may be observed by an 
 * observer method.</p>
 * 
 * <p>An observer method is a method of a bean class or 
 * {@linkplain jakarta.enterprise.inject.spi.Extension extension} with a 
 * parameter annotated {@link jakarta.enterprise.event.Observes &#064;Observes}
 * or {@link jakarta.enterprise.event.ObservesAsync &#064;ObservesAsync}.</p>
 * 
 * <p>An observer method will be notified of an event if:</p> 
 * 
 * <ul>
 * <li>the event object is assignable to the type observed by the observer 
 * method,</li>
 * <li>the observer method has all the event qualifiers of the event, and</li>
 * <li>either the event is not a 
 * {@linkplain jakarta.enterprise.inject.spi container lifecycle event}, or 
 * the observer method belongs to an 
 * {@linkplain jakarta.enterprise.inject.spi.Extension extension}.
 * </ul>
 * 
 * <p>If a synchronous observer method is a 
 * {@linkplain jakarta.enterprise.event.TransactionPhase transactional 
 * observer method} and there is a JTA transaction in progress when the
 * event is fired, the observer method is notified during the appropriate 
 * transaction completion phase. Otherwise, the observer is notified when 
 * the event is fired.</p>
 * 
 * <p>The order in which observer methods are called depends on the value of
 * the &#064;{@linkplain jakarta.annotation.Priority Priority} applied to the observer.</p>
 * <p>If no priority is defined on a observer, its priority is jakarta.interceptor.Interceptor.Priority.APPLICATION+500.</p>
 * <p>If two observer have the same priority their relative order is undefined.</p>
 * 
 * <p>Observer methods may throw exceptions:</p>
 * 
 * <ul>
 * <li>If the observer method is a 
 * {@linkplain jakarta.enterprise.event.TransactionPhase transactional 
 * observer method}, any exception is caught and logged by the container.</li>
 * <li>If the observer method is asynchronous, any exception is caught by the container and added as a suppressed exception
 * to a {@link java.util.concurrent.CompletionException} that could be handle by the application</li>
 * <li>Otherwise, the exception aborts processing of the event.
 * No other observer methods of that event will be called. The 
 * exception is rethrown. If the exception is a checked exception, 
 * it is wrapped and rethrown as an (unchecked) 
 * {@link jakarta.enterprise.event.ObserverException}.</li>
 * </ul>
 * 
 * @see jakarta.enterprise.inject
 * 
 * @see jakarta.enterprise.event.Observes
 * @see jakarta.enterprise.event.Event
 * @see jakarta.inject.Qualifier
 */
package jakarta.enterprise.event;

