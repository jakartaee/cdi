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
package javax.enterprise.inject.spi.builder;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.util.Nonbinding;

/**
 * 
 * @author Martin Kouba
 * @since 2.0
 */
public interface AnnotatedParameterConfigurator<T> {
    
    /**
     * 
     * @return the original {@link AnnotatedParameter}
     */
    AnnotatedParameter<T> getAnnotated();
    
    /**
     * Add an annotation to the param.
     * 
     * @param annotation
     * @return self
     */
    AnnotatedParameterConfigurator<T> add(Annotation annotation);
    
    /**
     * Remove annotations with (a) the same type and (b) the same annotation member value for each member which is not annotated
     * {@link Nonbinding}. The container calls the {@link Object#equals(Object)} method of the annotation member value to
     * compare values.
     * 
     * @param annotation
     * @return self
     */
    AnnotatedParameterConfigurator<T> remove(Annotation annotation);
    
    /**
     * Removes all annotations with the same type. Annotation members are ignored.
     * 
     * @param annotation
     * @return self
     */
    AnnotatedParameterConfigurator<T> remove(Class<? extends Annotation> annotationType);
    
    /**
     * Remove all annotations from the method.
     * 
     * @return self
     */
    AnnotatedParameterConfigurator<T> removeAll();

}
