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
import java.util.Set;

import javax.interceptor.InvocationContext;

/**
 * <p>
 * Represents an enabled {@linkplain javax.interceptor interceptor}.
 * </p>
 * <p>
 * Since CDI 2.0, an implementation of this interface may implement {@link Prioritized} in order to enable the interceptor with
 * given priority value for entire application.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author David Allen
 * 
 * @param <T> the interceptor bean class
 */
public interface Interceptor<T> extends Bean<T> {

    /**
     * <p>
     * Obtains the {@linkplain javax.interceptor.InterceptorBinding interceptor bindings} of the interceptor.
     * </p>
     * 
     * @return the set of {@linkplain javax.interceptor.InterceptorBinding interceptor bindings}
     */
    public Set<Annotation> getInterceptorBindings();

    /**
     * <p>
     * Determines if the interceptor intercepts the specified {@linkplain InterceptionType kind of lifecycle callback or method
     * invocation}.
     * </p>
     * 
     * @param type the {@linkplain InterceptionType kind of interception}
     * @return returns <tt>true</tt> if the interceptor intercepts callbacks or business methods of the given type, and
     *         <tt>false</tt> otherwise.
     */
    public boolean intercepts(InterceptionType type);

    /**
     * <p>
     * Invokes the specified {@linkplain InterceptionType kind of lifecycle callback or method invocation interception} upon the
     * given interceptor instance.
     * </p>
     * 
     * @param type the {@linkplain InterceptionType kind of interception}
     * @param instance the interceptor instance to invoke
     * @param ctx the context for the invocation
     * @return the invocation return value
     * @throws Exception thrown by the target method and/or the following interceptors in the chain
     */
    public Object intercept(InterceptionType type, T instance, InvocationContext ctx) throws Exception;

}
