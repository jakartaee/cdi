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

/**
 * <p>
 * This event type is thrown by the container before the bean discovery process
 * begins. If any observer method of the {@code BeforeBeanDiscovery} event
 * throws an exception, the exception is treated as a definition error by the
 * container.
 * </p>
 * 
 * @author Pete Muir
 * @author David Allen
 */
public interface BeforeBeanDiscovery
{
   /**
    * Declares an annotation type as a {@linkplain javax.inject.Qualifier}
    * qualifier type.
    * 
    * @param qualifier The annotation to treat as a qualifier
    */
   public void addQualifier(Class<? extends Annotation> qualifier);

   /**
    * Declares an annotation type as a {@linkplain javax.enterprise.context
    * scope type}.
    * 
    * @param scopeType The annotation type to treat as a
    *           {@linkplain javax.enterprise.context scope type}
    * @param normal Indicates if the scope is normal
    * @param passivating Indicates if the scope is
    *           {@linkplain javax.enterprise.inject.spi.PassivationCapable
    *           passivation capable}
    */
   public void addScope(Class<? extends Annotation> scopeType, boolean normal, boolean passivating);

   /**
    * Declares an annotation type as a
    * {@linkplain javax.enterprise.inject.Stereotype stereotype}, and specifies
    * its meta-annotations.
    * 
    * @param stereotype The annotation type to treat as a
    *           {@linkplain javax.enterprise.inject.Stereotype stereotype}
    * @param stereotypeDef An optional list of annotations defining the
    *           {@linkplain javax.enterprise.inject.Stereotype stereotype}
    */
   public void addStereotype(Class<? extends Annotation> stereotype, Annotation... stereotypeDef);

   /**
    * Declares an annotation type as an {@linkplain Interceptor interceptor}
    * binding type, and specifies its meta-annotations.
    * 
    * @param bindingType The annotation type to treat as an interceptor binding
    *           type
    * @param bindingTypeDef An optional list of annotations defining the {@linkplain Interceptor interceptor}
    */
   public void addInterceptorBinding(Class<? extends Annotation> bindingType, Annotation... bindingTypeDef);

   /**
    * Adds a given {@link javax.enterprise.inject.spi.AnnotatedType} to the set
    * of types which will be scanned during bean discovery.
    * 
    * @param type The {@link javax.enterprise.inject.spi.AnnotatedType} to add
    *           for later scanning
    */
   public void addAnnotatedType(AnnotatedType<?> type);
}
