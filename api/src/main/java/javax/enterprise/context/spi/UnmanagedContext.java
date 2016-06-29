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

package javax.enterprise.context.spi;

import javax.enterprise.context.ContextNotActiveException;
import java.lang.annotation.Annotation;

/**
 * Provides the same capabilities as a {@linkplain javax.enterprise.context.spi.Context object}, however is not intended to be active when first retrieved.
 * UnmanagedContext is designed for use-cases where you need to manually start a built-in context in a novel way.
 *
 * @author John D. Ament
 * @since 2.0
 */
public interface UnmanagedContext {

    /**
     * Get the scope type of the context object.
     *
     * @return the scope
     */
    Class<? extends Annotation> getScope();

    /**
     * Return an existing instance of certain contextual type or create a new instance by calling
     * {@link javax.enterprise.context.spi.Contextual#create(CreationalContext)} and return the new instance.
     *
     * @param <T> the type of contextual type
     * @param contextual the contextual type
     * @param creationalContext the context in which the new instance will be created
     * @return the contextual instance
     *
     * @throws ContextNotActiveException if the context is not active
     */
    <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext);

    /**
     * Return an existing instance of a certain contextual type or a null value.
     *
     * @param <T> the type of the contextual type
     * @param contextual the contextual type
     * @return the contextual instance, or a null value
     *
     * @throws ContextNotActiveException if the context is not active
     */
    <T> T get(Contextual<T> contextual);

    /**
     * Determines if the context object is active.
     *
     * @return <tt>true</tt> if the context is active, or <tt>false</tt> otherwise.
     */
    boolean isActive();

    /**
     * <p>
     * Destroy the existing contextual instance. If there is no existing instance, no action is taken.
     * </p>
     *
     * @param contextual the contextual type
     * @throws ContextNotActiveException if the context is not active
     */
    void destroy(Contextual<?> contextual);

    /**
     * Starts the given context, allowing beans to be looked up within the bounds of that context.
     *
     * @throws IllegalStateException if the context has already been started.
     */
    void activate();

    /**
     * Deactivates the given context, freeing any objects associated.
     *
     * @throws IllegalStateException if the context has not been started or has been stopped already.
     */
    void deactivate();
}
