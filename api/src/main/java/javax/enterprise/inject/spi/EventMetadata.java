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
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.event.Observes;

/**
 * <p>
 * Provides access to metadata about an observed event payload. The metadata may be for events fired with either of
 * {@link javax.enterprise.event.Event} or {@link BeanManager#fireEvent(Object, Annotation...)}.
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
 */
public interface EventMetadata
{
   /**
    * Get the qualifiers for which event payload was fired.
    */
   public Set<Annotation> getQualifiers();

   /**
    * Get the {@link InjectionPoint} from which the event fired, or <code>null</code> if it was fired
    * from {@link BeanManager#fireEvent(Object, Annotation...)};
    */
   public InjectionPoint getInjectionPoint();

   /**
    * Returns the resolved event {@link Type}.
    */
   public Type getType();
}
