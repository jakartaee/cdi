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

package javax.enterprise.context.control;

import javax.enterprise.context.ContextNotActiveException;

/**
 * The CDI container provides a built in instance of RequestContextController that is dependent scoped for the purposes
 * of activating and deactivating.  For example:
 *
 * <pre>
 *    &#064;Inject
 *    private RequestContextController requestContextController;
 *
 *    public void doRequest(String body) {
 *       // activate request context
 *       requestContextController.activate();
 *
 *       // do work in a request context.
 *
 *       // deactivate the request context
 *       requestContextController.deactivate();
 *    }
 * </pre>
 *
 * Once the request context has been deactivated, you may activate it once again, creating a brand new request context.
 * The activated request context is bound to the current thread, any injection points targeting a request scoped bean
 * will be satisfied with the same request scoped objects.
 *
 * @since 2.0
 * @author John D. Ament
 */
public interface RequestContextController {

   /**
    * Activates a RequestContext for the current thread if one is not already active.
    * @return true if the context was activated by this invocation, false if not.
    */
   boolean activate();

   /**
    * Deactivates the current Request Context if it was activated by this context controller.  If the context is active
    * but was not activated by this controller, then it may not be deactivated by this controller,
    * meaning this method will do nothing.
    *
    * If the context is not active, a {@linkplain ContextNotActiveException} is thrown.
    *
    * @throws ContextNotActiveException if the context is not active
    */
   void deactivate() throws ContextNotActiveException;
}
