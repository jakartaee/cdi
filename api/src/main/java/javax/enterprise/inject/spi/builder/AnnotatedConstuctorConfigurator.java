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
import java.util.List;
import java.util.function.Predicate;

import javax.enterprise.inject.spi.AnnotatedParameter;

/**
 * 
 * @author Martin Kouba
 * @since 2.0
 */
public interface AnnotatedConstuctorConfigurator<T> {
    
    AnnotatedConstuctorConfigurator<T> add(Annotation annotation);
    
    AnnotatedConstuctorConfigurator<T> remove(Annotation annotation);
    
    AnnotatedConstuctorConfigurator<T> remove(Class<? extends Annotation> annotationType);
    
    AnnotatedConstuctorConfigurator<T> removeAll();
    
    AnnotatedParameterConfigurator<T> findParam(Predicate<AnnotatedParameter<T>> predicate);
    
    AnnotatedParameterConfigurator<T> param(int position);
    
    List<AnnotatedParameterConfigurator<T>> params();

}
