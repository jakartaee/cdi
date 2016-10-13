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
package javax.enterprise.inject.spi.configurator;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.util.Nonbinding;

/**
 *
 * This interface is part of the {@link AnnotatedTypeConfigurator} spi and helps defining an {@link AnnotatedConstructor}
 * 
 * @author Martin Kouba
 * @author Antoine Sabot-Durand
 * @since 2.0
 * @param <T> the class declaring the constructor
 */
public interface AnnotatedConstructorConfigurator<T> {

    /**
     * 
     * @return the original {@link AnnotatedConstructor}
     */
    AnnotatedConstructor<T> getAnnotated();

    /**
     * Add an annotation to the constructor.
     * 
     * @param annotation annotation to add
     * @return self
     */
    AnnotatedConstructorConfigurator<T> add(Annotation annotation);

    /**
     * Remove all annotation with (a) the same type and (b) the same annotation member value for each member which is not
     * annotated {@link Nonbinding}. The container calls the {@link Object#equals(Object)} method of the annotation member value
     * to compare values.
     * 
     * @param annotation annotation to remove
     * @return self
     */
    AnnotatedConstructorConfigurator<T> remove(Annotation annotation);

    /**
     * Removes all annotations with the same type. Annotation members are ignored.
     * 
     * @param annotationType annotation class to remove
     * @return self
     */
    AnnotatedConstructorConfigurator<T> remove(Class<? extends Annotation> annotationType);

    /**
     * Remove all annotations from the constructor.
     * 
     * @return self
     */
    AnnotatedConstructorConfigurator<T> removeAll();

    /**
     * 
     * @return an immutable list of {@link AnnotatedParameterConfigurator}s reflecting the
     *         {@link AnnotatedConstructor#getParameters()}
     */
    List<AnnotatedParameterConfigurator<T>> params();

    /**
     * 
     * @param predicate Testing the original {@link AnnotatedParameter}
     * @return a sequence of {@link AnnotatedParameterConfigurator}s matching the given predicate
     * @see AnnotatedParameterConfigurator#getAnnotated()
     */
    default Stream<AnnotatedParameterConfigurator<T>> filterParams(Predicate<AnnotatedParameter<T>> predicate) {
        return params().stream().filter(p -> predicate.test(p.getAnnotated()));
    }

}
