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

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.util.Nonbinding;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This API is an helper to configure a new {@link AnnotatedType} instance. The CDI container must provide an implementation of
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

    /**
     * 
     * @return the original {@link AnnotatedType}
     */
    AnnotatedType<T> getAnnotated();

    /**
     * Add an annotation to the type.
     * 
     * @param annotation
     * @return self
     */
    AnnotatedTypeConfigurator<T> add(Annotation annotation);

    /**
     * Remove annotations with (a) the same type and (b) the same annotation member value for each member which is not
     * annotated {@link Nonbinding}. The container calls the {@link Object#equals(Object)} method of the annotation member value
     * to compare values.
     * 
     * @param annotation
     * @return self
     */
    AnnotatedTypeConfigurator<T> remove(Annotation annotation);

    /**
     * Removes all annotations with the same type. Annotation members are ignored.
     * 
     * @param annotation
     * @return self
     */
    AnnotatedTypeConfigurator<T> remove(Class<? extends Annotation> annotationType);

    /**
     * Remove all annotations from the type.
     * 
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeAll();

    /**
     * 
     * @return an immutable set of {@link AnnotatedMethodConfigurator}s reflecting the {@link AnnotatedType#getMethods()}
     */
    Set<AnnotatedMethodConfigurator<T>> methods();

    /**
     * @param predicate Testing the original {@link AnnotatedMethod}
     * @return a sequence of {@link AnnotatedMethodConfigurator}s matching the given predicate
     * @see AnnotatedMethodConfigurator#getAnnotated()
     */
    default Stream<AnnotatedMethodConfigurator<T>> filterMethods(Predicate<AnnotatedMethod<T>> predicate) {
        return methods().stream().filter(c -> predicate.test(c.getAnnotated()));
    }

    /**
     * 
     * @return an immutable set of {@link AnnotatedFieldConfigurator}s reflecting the {@link AnnotatedType#getFields()}
     */
    Set<AnnotatedFieldConfigurator<T>> fields();

    /**
     * @param predicate Testing the original {@link AnnotatedField}
     * @return a sequence of {@link AnnotatedFieldConfigurator}s matching the given predicate
     * @see AnnotatedFieldConfigurator#getAnnotated()
     */
    default Stream<AnnotatedFieldConfigurator<T>> filterFields(Predicate<AnnotatedField<T>> predicate) {
        return fields().stream().filter(f -> predicate.test(f.getAnnotated()));
    }

    /**
     * 
     * @return an immutable set of {@link AnnotatedConstuctorConfigurator}s reflecting the
     *         {@link AnnotatedType#getConstructors()}
     */
    Set<AnnotatedConstuctorConfigurator<T>> constructors();

    /**
     * 
     * @param predicate Testing the original {@link AnnotatedConstructor}
     * @return a sequence of {@link AnnotatedConstuctorConfigurator}s matching the given predicate
     * @see AnnotatedConstuctorConfigurator#getAnnotated()
     */
    default Stream<AnnotatedConstuctorConfigurator<T>> filterConstructors(Predicate<AnnotatedConstructor<T>> predicate) {
        return constructors().stream().filter(c -> predicate.test(c.getAnnotated()));
    }

}
