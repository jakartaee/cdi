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

/**
 * <p>
 * Provides an operation for obtaining and destroying contextual instances with a particular scope of any contextual type. Any
 * instance of {@code Context} is called a context object.
 * </p>
 * 
 * <p>
 * {@link AlterableContext} was introduced in CDI 1.1 to allow bean instances to be destroyed by the application. Extensions
 * should implement {@link AlterableContext} instead of {@link Context}.
 * </p>
 * 
 * <p>
 * The context object is responsible for creating and destroying contextual instances by calling operations of
 * {@link javax.enterprise.context.spi.Contextual}. In particular, the context object is responsible for destroying any
 * contextual instance it creates by passing the instance to
 * {@link javax.enterprise.context.spi.Contextual#destroy(Object, CreationalContext)} . A destroyed instance must not
 * subsequently be returned by {@code get()}. The context object must pass the same instance of
 * {@link javax.enterprise.context.spi.CreationalContext} to {@code Contextual.destroy()} that it passed to
 * {@code Contextual.create()} when it created the instance.
 * </p>
 * 
 * <p>
 * A custom context object may be registered with the container using
 * {@link javax.enterprise.inject.spi.AfterBeanDiscovery#addContext(Context)}.
 * </p>
 * 
 * @author Pete Muir
 * @since 1.1
 */
public interface AlterableContext extends Context {

    /**
     * <p>
     * Destroy the existing contextual instance. If there is no existing instance, no action is taken.
     * </p>
     * 
     * @param contextual the contextual type
     * @throws ContextNotActiveException if the context is not active
     */
    public void destroy(Contextual<?> contextual);

}
