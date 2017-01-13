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
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * <p>
 * Represents a Java program element that can be annotated.
 * <p>
 * 
 * @see java.lang.reflect.AnnotatedElement
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author Clint Popetz
 * @author John D. Ament
 * 
 */
public interface Annotated {

    /**
     * <p>
     * Get the type of the annotated program element.
     * </p>
     * 
     * @return the type of the annotated program element
     */
    Type getBaseType();

    /**
     * <p>
     * Get all types to which the base type should be considered assignable.
     * </p>
     * 
     * @return a set of all types to which the base type should be considered assignable
     */
    Set<Type> getTypeClosure();

    /**
     * <p>
     * Get program element annotation of a certain annotation type.
     *
     * The behavior of this method is intended to be the same behavior as {@link AnnotatedElement#getAnnotation(Class)},
     * where repeatable annotations are not supported.
     *</p>
     *
     * @param <T> the type of the annotation
     * @param annotationType the class of the annotation type
     * @return the first program element annotation of the given annotation type, or a null value
     */
    <T extends Annotation> T getAnnotation(Class<T> annotationType);

    /**
     * <p>
     * Get program element annotations of a certain annotation type.
     * <p>
     * This method returns back all annotations, including repeatable annotations of this type.
     *
     * The behavior of this method is intended to be the same behavior as {@link AnnotatedElement#getAnnotationsByType(Class)},
     * where repeatable annotations are supported.
     * </p>
     *
     * @param <T>            the type of the annotation
     * @param annotationType the class of the annotation type
     * @return the program element annotations of the given annotation type, or an empty collection
     */
    <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType);

    /**
     * <p>
     * Get all annotations of the program element.
     * </p>
     * 
     * @return all annotations of the program element, or an empty set if no annotations are present
     */
    Set<Annotation> getAnnotations();

    /**
     * <p>
     * Determine if the program element has an annotation of a certain annotation type.
     *
     * The behavior of this method is similar to {@link AnnotatedElement#isAnnotationPresent(Class)} for the underlying
     * program element.
     *
     * @param annotationType the annotation type to check for
     * @return <tt>true</tt> if the program element has an annotation of the given annotation type, or <tt>false</tt> otherwise
     */
    boolean isAnnotationPresent(Class<? extends Annotation> annotationType);
}
