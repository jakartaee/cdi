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
package jakarta.enterprise.inject.spi;

import jakarta.enterprise.context.spi.CreationalContext;

/**
 * <p>
 * Provides operations for performing {@linkplain jakarta.enterprise.inject dependency injection} and lifecycle callbacks on an
 * instance of a type.
 * </p>
 * 
 * @see jakarta.annotation.PostConstruct
 * @see jakarta.annotation.PreDestroy
 * 
 * @author Pete Muir
 * @author David Allen
 * @param <T> The class of the instance
 */
public interface InjectionTarget<T> extends Producer<T> {

    /**
     * <p>
     * Performs dependency injection upon the given object. Performs Java EE component environment injection, sets the value of
     * all injected fields, and calls all initializer methods.
     * </p>
     * 
     * @param instance The instance upon which to perform injection
     * @param ctx The {@link CreationalContext} to use for creating new instances
     */
    public void inject(T instance, CreationalContext<T> ctx);

    /**
     * <p>
     * Calls the {@link jakarta.annotation.PostConstruct} callback, if it exists, according to the semantics required by the Java
     * EE platform specification.
     * </p>
     * 
     * @param instance The instance on which to invoke the {@link jakarta.annotation.PostConstruct} method
     */
    public void postConstruct(T instance);

    /**
     * <p>
     * Calls the {@link jakarta.annotation.PreDestroy} callback, if it exists, according to the semantics required by the Java EE
     * platform specification.
     * </p>
     * 
     * @param instance The instance on which to invoke the {@link jakarta.annotation.PreDestroy} method
     */
    public void preDestroy(T instance);

}
