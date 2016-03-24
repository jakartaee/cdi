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
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 * This API is an helper to configure a new {@link AnnotatedType} instance. The CDI container must provides an implementation of
 * this interface.
 *
 * AnnotatedTypeConfigurator is not reusable.
 *
 * This configurator is not thread safe and shall not be used concurrently.
 *
 * @see javax.enterprise.inject.spi.BeforeBeanDiscovery#addAnnotatedType(String)
 * @see javax.enterprise.inject.spi.AfterTypeDiscovery#addAnnotatedType(String)
 * @see ProcessAnnotatedType#configureAnnotatedType()
 * @param <T> the class represented by the configured AnnotatedType
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public interface AnnotatedTypeConfigurator<T> {

    AnnotatedTypeConfigurator<T> add(Annotation annotation);

    AnnotatedTypeConfigurator<T> remove(Annotation annotation);

    AnnotatedTypeConfigurator<T> remove(Class<? extends Annotation> annotationType);
    
    AnnotatedTypeConfigurator<T> removeAll();

    // All find methods query the original AnnotatedType
    
    Set<AnnotatedMethodConfigurator<T>> methods();
    
    AnnotatedMethodConfigurator<T> findMethod(Predicate<AnnotatedMethod<T>> predicate);

    Stream<AnnotatedMethodConfigurator<T>> findMethods(Predicate<AnnotatedMethod<T>> predicate);

    Set<AnnotatedFieldConfigurator<T>> fields();
    
    AnnotatedFieldConfigurator<T> findField(Predicate<AnnotatedField<T>> predicate);

    Stream<AnnotatedFieldConfigurator<T>> findFields(Predicate<AnnotatedField<T>> predicate);

    Set<AnnotatedConstuctorConfigurator<T>> constructors();
    
    AnnotatedConstuctorConfigurator<T> findConstructor(Predicate<AnnotatedConstructor<T>> predicate);

    Stream<AnnotatedConstuctorConfigurator<T>> findConstructors(Predicate<AnnotatedConstructor<T>> predicate);

}
