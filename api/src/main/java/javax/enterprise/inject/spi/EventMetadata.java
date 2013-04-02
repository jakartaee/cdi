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

/**
 * Provides meta-information about an observed event payload, and may represent metadata for events fired with either of
 * {@link javax.enterprise.event.Event} or {@link BeanManager#fireEvent(Object, Annotation...)}.
 * <p>
 * Instances of this class can be retrieved as a parameter of an {@literal @}{@link javax.enterprise.event.Observes}
 * method.
 * 
 * @author Lincoln Baxter, III
 */
public interface EventMetadata
{
   /**
    * Returns the {@link Set} of qualifiers with which the corresponding event payload was fired.
    */
   public Set<Annotation> getQualifiers();

   /**
    * Returns the {@link InjectionPoint} from which the event payload was fired, or <code>null</code> if it was fired
    * from {@link BeanManager#fireEvent(Object, Annotation...)};
    */
   public InjectionPoint getInjectionPoint();

   /**
    * Returns the resolved event {@link Type}.
    */
   public Type getType();
}
