/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
import javax.enterprise.inject.Instance;
import java.lang.annotation.Annotation;

/**
 * Representation of a context which has been activated
 *
 * @author John D. Ament
 */
public interface ManagedContext {

    /**
     * Provides access to an internal ID used to uniquely identify this active context
     *
     * @return the internal ID of this context, if it is supported
     */
    String getId();

    /**
     * Associates the context to the given thread.
     * @param thread the thread to associate with.  This does not remove associations with other threads, but acts additive
     * @throws IllegalArgumentException if the given thread is already associated with this context.
     */
    void associate(Thread thread);

    /**
     * Disassociates
     * @param thread
     */
    void disassociate(Thread thread);

    /**
     * Get the scope type of the context object.
     *
     * @return the scope
     */
    Class<? extends Annotation> getScope();

    /**
     * Determines if the context object is active.
     *
     * @return <tt>true</tt> if the context is active, or <tt>false</tt> otherwise.
     */
    boolean isActive();

    /**
     * Provides an Instance object that is bound to this context.  This instance may be used to retrieve or create
     * beans within the scope of this context.
     *
     * @return an Instance object to retrieve beans from this context.
     */
    Instance<Object> instance();

    /**
     * Deactivates the given managed context.  Can only be used to deactivate contexts that were activated by a context controller
     *
     * @throws ContextNotActiveException if the given context has already been deactivated
     */
    void deactivate();

}
