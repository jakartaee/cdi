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

package javax.enterprise.inject.spi.builder;

import javax.enterprise.inject.spi.ObserverMethod;

/**
 * This API is an helper to build a new {@link ObserverMethod} instance.
 * CDI container must provides an implementation of this interface accessible.
 *
 * The same ObserverMethodBuilder can be used multiple times.
 *
 * This builder is not thread safe and shall not be used concurrently.
 *
 * @param <T> type of the event the configured ObserverMethod will observe
 * @see Builders#observerMethod(Class)
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public interface ObserverMethodBuilder<T> {

    /**
     * Give access to the builder configuration
     *
     * @return an ObserverMethodConfigurator to configure the builder
     */
    ObserverMethodConfigurator<T> configure();

    /**
     * Build the ObserverMethod
     *
     * @return the built ObserverMethod
     */
    ObserverMethod<T> build();
}
