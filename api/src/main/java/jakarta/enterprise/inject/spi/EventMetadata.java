/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, 2015 Red Hat, Inc., and individual contributors
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;

/**
 * <p>
 * Provides access to metadata about an observed event payload. The metadata may be for events fired with either of
 * {@link Event} or {@link BeanManager#getEvent()}
 * </p>
 * <p>
 * {@link EventMetadata} may only be injected into an observer method. For example:
 * </p>
 * 
 * <pre>
 * public void afterLogin(&#064;Observes LoggedInEvent event, EventMetadata eventMetadata) { ... }
 * </pre>
 * 
 * @see Observes
 * 
 * @author Lincoln Baxter, III
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 * @since 1.1
 */
public interface EventMetadata {
    /**
     * @return the qualifiers for which event payload was fired.
     */
    public Set<Annotation> getQualifiers();

    /**
     * Get the {@link InjectionPoint} representing the injected {@link Event} instance which fired the event
     *
     * @return InjectionPoint of the Event
     */
    public InjectionPoint getInjectionPoint();

    /**
     * Get the type representing runtime class of the event object with type variables resolved.
     *
     *
     * @return the runtime type of the event object
     */
    public Type getType();
    
}
