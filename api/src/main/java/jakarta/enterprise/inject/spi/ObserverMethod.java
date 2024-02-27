/*
 * Copyright 2010, 2015 Red Hat, Inc., and individual contributors
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.enterprise.event.Reception;
import jakarta.enterprise.event.TransactionPhase;

/**
 * <p>
 * Represents an {@linkplain Observes observer method} of an {@linkplain jakarta.enterprise.inject enabled
 * bean}. Defines everything the container needs to know about an observer method.
 * </p>
 *
 * <p>
 * If a custom implementation of this interface does not override either {@link #notify(Object)} or
 * {@link #notify(EventContext)}, the container automatically detects the problem and treats it as a definition error.
 * </p>
 *
 * @author Gavin King
 * @author David Allen
 * @author Mark Paluch
 * @author Antoine Sabot-Durand
 * @param <T> the event type
 */
public interface ObserverMethod<T> extends Prioritized {
    /** The default observer priority */
    public static final int DEFAULT_PRIORITY = jakarta.interceptor.Interceptor.Priority.APPLICATION + 500;

    /**
     * <p>
     * Obtains the {@linkplain Class class} of the type that declares the observer method.
     * </p>
     *
     * @return the defining {@linkplain Class class}
     */
    public Class<?> getBeanClass();

    /**
     * <p>
     * Obtains the {@linkplain Bean bean} that declares the observer method.
     * For synthetic observers, the return value is undefined.
     * </p>
     *
     * <p>
     * For the sake of compatibility with existing custom {@link ObserverMethod} implementations, this method by default
     * returns {@code null}.
     * </p>
     *
     * @return the declaring {@linkplain Bean bean}
     * @since 4.0
     */
    default Bean<?> getDeclaringBean() {
        return null;
    }

    /**
     * Obtains the {@linkplain jakarta.enterprise.event observed event type}.
     *
     * @return the observed event {@linkplain Type type}
     */
    public Type getObservedType();

    /**
     * Obtains the set of {@linkplain jakarta.enterprise.event observed event qualifiers}.
     *
     * @return the observed event {@linkplain jakarta.inject.Qualifier qualifiers}
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
     * The priority that will be used by the container to determine the notification order in which event observer
     * methods are invoked.
     *
     * @return The priority that will be used by the container to determine the notification order in which event
     *         observer methods are invoked.
     * @since 2.0
     */
    @Override
    public default int getPriority() {
        return DEFAULT_PRIORITY;
    }

    /**
     * <p>
     * Calls the observer method, passing the given event object.
     * </p>
     *
     * <p>
     * The implementation of this method for a custom observer method is responsible for deciding whether to call
     * the method if the {@link #getReception()} returns {@link Reception#IF_EXISTS}.
     * </p>
     *
     * @param event the event object
     */
    public default void notify(T event) {
    }

    /**
     * Calls the observer method, passing the given event context.
     * <p>
     * The container should always call this method, but the default implementation delegates to {@link #notify(Object)}.
     * <p>
     * The implementation of this method for a custom observer method is responsible for deciding whether to call the method if
     * the {@link #getReception()} returns {@link Reception#IF_EXISTS}.
     *
     * @param eventContext {@link EventContext} used to notify observers
     */
    public default void notify(EventContext<T> eventContext) {
        notify(eventContext.getEvent());
    }

    /**
     * <p>
     * Determines if this observer method is asynchronous
     * </p>
     *
     * @return returns <code>true</code> if the method is an asynchronous observer method (i.e. defined with
     *         {@link ObservesAsync}), otherwise returns <code>false</code>
     *
     */
    public default boolean isAsync() {
        return false;
    }

}
