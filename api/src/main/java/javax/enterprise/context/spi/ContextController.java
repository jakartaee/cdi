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
import java.util.Optional;

/**
 * A programmatic interface for retrieving, starting and stopping built in contexts
 *
 * The container provides a dependent scoped instance of this bean which may be injected to manage contexts
 *
 * <pre>
 * &#064;Inject
 * private ContextController contextController;
 *
 *
 * ManagedContext context = contextController.getManagedContext(RequestScoped.class)
 *                  .orElseGet(() -> contextController.activate(RequestScoped.class));
 *
 * // do some work.
 *
 * context.deactivate();
 *
 * </pre>
 *
 * @author John D. Ament
 */
public interface ContextController {

    /**
     * Obtains an active {@linkplain ManagedContext context object} for the given
     * {@linkplain javax.enterprise.context scope} based on what is active within the current thread.
     *
     * @param scopeType the {@linkplain javax.enterprise.context scope}
     * @return an Optional representation of the {@linkplain ManagedContext context object}
     *         or else Optional.empty
     * @throws IllegalArgumentException if there is more than one active context object for the given scope
     */
    Optional<ManagedContext> getManagedContext(Class<? extends Annotation> scopeType);

    /**
     * Obtains an active {@linkplain ManagedContext context object} for the given
     * {@linkplain javax.enterprise.context scope} based on its id.
     * @param id the id of the context to be looked up
     * @param scopeType the {@linkplain javax.enterprise.context scope}
     * @return the {@linkplain ManagedContext context object}
     * @throws ContextNotActiveException if there is no active context object for the given scope with this ID
     */
    ManagedContext getManagedContext(String id, Class<? extends Annotation> scopeType);

    /**
     * Activates the provided scope type, creating a new Context object for the scope type.
     *
     * The activated context is immediately associated to the current thread.
     */
    ManagedContext activate(Class<? extends Annotation> scopeType) throws IllegalStateException;

}
