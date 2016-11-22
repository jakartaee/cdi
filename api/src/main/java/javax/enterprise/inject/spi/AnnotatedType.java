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
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * <p>
 * Represents a Java class or interface.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * 
 * @param <X> the type
 * @see java.lang.Class
 */
public interface AnnotatedType<X> extends Annotated {

    /**
     * <p>
     * Get the underlying {@link java.lang.Class}.
     * </p>
     * 
     * @return the {@link java.lang.Class}
     */
    public Class<X> getJavaClass();

    /**
     * <p>
     * Get the {@linkplain AnnotatedConstructor constructors} of the type. If an empty set is returned, a default constructor
     * with no parameters will be assumed.
     * </p>
     * 
     * @return the constructors, or an empty set if none are defined
     */
    public Set<AnnotatedConstructor<X>> getConstructors();

    /**
     * <p>
     * Get the {@linkplain AnnotatedMethod methods} of the type.
     * </p>
     * 
     * @return the methods, or an empty set if none are defined
     */
    public Set<AnnotatedMethod<? super X>> getMethods();

    /**
     * <p>
     * Get the {@linkplain AnnotatedField fields} of the type.
     * <p>
     * 
     * @return the fields, or an empty set if none are defined
     */
    public Set<AnnotatedField<? super X>> getFields();

    @Override default <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType) {
        T[] annotationsByType = getJavaClass().getAnnotationsByType(annotationType);
        return new LinkedHashSet<>(asList(annotationsByType));
    }
}
