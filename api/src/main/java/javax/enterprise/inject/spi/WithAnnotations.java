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

package javax.enterprise.inject.spi;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * {@link WithAnnotations} may be applied to any portable extension observer method with an event parameter type of
 * {@link ProcessAnnotatedType} to filter the events delivered.
 * </p>
 * 
 * <p>
 * If the {@link WithAnnotations} annotation is applied to a portable extension observer method, then only
 * {@link ProcessAnnotatedType} events for types which have at least one of the annotations specified are observed. The
 * annotation can appear on the annotated type, or on any member, or any parameter of any member of the annotated type, as defined 
 * in section <a href="https://docs.jboss.org/cdi/spec/1.2/cdi-spec.html#alternative_metadata_sources">11.4 Alternative metadata sources</a>.
 * The annotation may be applied as a meta-annotation on any annotation considered.
 *
 * @author Pete Muir
 * @since 1.1
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface WithAnnotations {

    Class<? extends Annotation>[] value();

}
