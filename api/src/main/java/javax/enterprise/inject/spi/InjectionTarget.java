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

import javax.enterprise.context.spi.CreationalContext;

/**
 * <p>
 * Provides operations for performing {@linkplain javax.enterprise.inject dependency injection} and lifecycle callbacks on an
 * instance of a type.
 * </p>
 * 
 * @see javax.annotation.PostConstruct
 * @see javax.annotation.PreDestroy
 * 
 * @author Pete Muir
 * @author David Allen
 * @param <T> The class of the instance
 */
public interface InjectionTarget<T> extends Producer<T> {

    /**
     * <p>
     * Performs dependency injection upon the given object. Performs Jakarta EE component environment injection, sets the value of
     * all injected fields, and calls all initializer methods.
     * </p>
     * 
     * @param instance The instance upon which to perform injection
     * @param ctx The {@link javax.enterprise.context.spi.CreationalContext} to use for creating new instances
     */
    public void inject(T instance, CreationalContext<T> ctx);

    /**
     * <p>
     * Calls the {@link javax.annotation.PostConstruct} callback, if it exists, according to the semantics required by the Java
     * EE platform specification.
     * </p>
     * 
     * @param instance The instance on which to invoke the {@link javax.annotation.PostConstruct} method
     */
    public void postConstruct(T instance);

    /**
     * <p>
     * Calls the {@link javax.annotation.PreDestroy} callback, if it exists, according to the semantics required by the Jakarta EE
     * platform specification.
     * </p>
     * 
     * @param instance The instance on which to invoke the {@link javax.annotation.PreDestroy} method
     */
    public void preDestroy(T instance);

}
