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
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;


/**
 * This API is an helper to configure a new {@link AnnotatedType} instance.
 * The CDI container must provides an implementation of this interface.
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
     * Add an annotation to the type declaration.
     *
     * @param annotation the annotation instance to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToType(Annotation annotation);

    /**
     * Remove an annotation class from the type
     *
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromType(Class<? extends Annotation> annotationType);

    /**
     * Remove an annotation instance from the type
     *
     * @param annotation the annotation to remove
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromType(Annotation annotation);

    /**
     * Add an annotation to the specified field. If the field is not already
     * present, it will be added.
     *
     * @param field      the field to add the annotation to
     * @param annotation the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToField(Field field, Annotation annotation);

    /**
     * Add an annotation to the specified field. If the field is not already
     * present, it will be added.
     *
     * @param field      the field to add the annotation to
     * @param annotation the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToField(AnnotatedField<? super T> field, Annotation annotation);

    /**
     * Remove an annotation from the specified field.
     *
     * @param field          the field to remove the annotation from
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null or if the
     *                                  field is not currently declared on the type
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromField(Field field, Class<? extends Annotation> annotationType);

    /**
     * Remove an annotation from the specified field.
     *
     * @param field          the field to remove the annotation from
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null or if the
     *                                  field is not currently declared on the type
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromField(AnnotatedField<? super T> field,
                                                 Class<? extends Annotation> annotationType);

    /**
     * Add an annotation to the specified method. If the method is not already
     * present, it will be added.
     *
     * @param method     the method to add the annotation to
     * @param annotation the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToMethod(Method method, Annotation annotation);

    /**
     * Add an annotation to the specified method. If the method is not already
     * present, it will be added.
     *
     * @param method     the method to add the annotation to
     * @param annotation the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToMethod(AnnotatedMethod<? super T> method, Annotation annotation);

    /**
     * Remove an annotation from the specified method.
     *
     * @param method         the method to remove the annotation from
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null or if the
     *                                  method is not currently declared on the type
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromMethod(Method method, Class<? extends Annotation> annotationType);

    /**
     * Remove an annotation from the specified method.
     *
     * @param method         the method to remove the annotation from
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null or if the
     *                                  method is not currently declared on the type
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromMethod(AnnotatedMethod<? super T> method,
                                                  Class<? extends Annotation> annotationType);

    /**
     * Add an annotation to the specified method parameter. If the method is not
     * already present, it will be added. If the method parameter is not already
     * present, it will be added.
     *
     * @param method     the method to add the annotation to
     * @param position   the position of the parameter to add
     * @param annotation the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToMethodParameter(Method method, int position, Annotation annotation);

    /**
     * Remove an annotation from the specified method parameter.
     *
     * @param method         the method to remove the annotation from
     * @param position       the position of the parameter to remove
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null, if the
     *                                  method is not currently declared on the type or if the
     *                                  parameter is not declared on the method
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromMethodParameter(Method method,
                                                           int position, Class<? extends Annotation> annotationType);

    /**
     * Add an annotation to the specified constructor. If the constructor is not
     * already present, it will be added.
     *
     * @param constructor the constructor to add the annotation to
     * @param annotation  the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToConstructor(Constructor<T> constructor, Annotation annotation);

    /**
     * Add an annotation to the specified constructor. If the constructor is not
     * already present, it will be added.
     *
     * @param constructor the constructor to add the annotation to
     * @param annotation  the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToConstructor(AnnotatedConstructor<T> constructor, Annotation annotation);

    /**
     * Remove an annotation from the specified constructor.
     *
     * @param constructor    the constructor to add the annotation to
     * @param annotationType the annotation to add
     * @throws IllegalArgumentException if the annotationType is null or if the
     *                                  constructor is not currently declared on the type
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromConstructor(Constructor<T> constructor,
                                                       Class<? extends Annotation> annotationType);

    /**
     * Remove an annotation from the specified constructor.
     *
     * @param constructor    the constructor to add the annotation to
     * @param annotationType the annotation to add
     * @throws IllegalArgumentException if the annotationType is null, if the
     *                                  annotation does not exist on the type or if the constructor is
     *                                  not currently declared on the type
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromConstructor(AnnotatedConstructor<T> constructor,
                                                       Class<? extends Annotation> annotationType);

    /**
     * Add an annotation to the specified constructor parameter. If the
     * constructor is not already present, it will be added. If the constructor
     * parameter is not already present, it will be added.
     *
     * @param constructor the constructor to add the annotation to
     * @param position    the position of the parameter to add
     * @param annotation  the annotation to add
     * @throws IllegalArgumentException if the annotation is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToConstructorParameter(Constructor<T> constructor,
                                                           int position,
                                                           Annotation annotation);

    /**
     * Remove an annotation from the specified constructor parameter.
     *
     * @param constructor    the constructor to remove the annotation from
     * @param position       the position of the parameter to remove
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null, if the
     *                                  constructor is not currently declared on the type or if the
     *                                  parameter is not declared on the constructor
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromConstructorParameter(Constructor<T> constructor,
                                                                int position,
                                                                Class<? extends Annotation> annotationType);

    /**
     * Remove an annotation from the specified parameter.
     *
     * @param parameter      the parameter to remove the annotation from
     * @param annotationType the annotation type to remove
     * @throws IllegalArgumentException if the annotationType is null, if the
     *                                  callable which declares the parameter is not currently declared
     *                                  on the type or if the parameter is not declared on either a
     *                                  constructor or a method
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromParameter(AnnotatedParameter<? super T> parameter,
                                                     Class<? extends Annotation> annotationType);

    /**
     * Add an annotation to the specified parameter. If the callable which
     * declares the parameter is not already present, it will be added. If the
     * parameter is not already present on the callable, it will be added.
     *
     * @param parameter  the parameter to add the annotation to
     * @param annotation the annotation to add
     * @throws IllegalArgumentException if the annotation is null or if the
     *                                  parameter is not declared on either a constructor or a method
     * @return self
     */
    AnnotatedTypeConfigurator<T> addToParameter(AnnotatedParameter<? super T> parameter, Annotation annotation);

    /**
     * Remove annotations from the type, and all of it's members. If an
     * annotation of the specified type appears on the type declaration, or any
     * of it's members it will be removed.
     *
     * @param annotationType the type of annotation to remove
     * @throws IllegalArgumentException if the annotationType is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> removeFromAll(Class<? extends Annotation> annotationType);

    /**
     * Reads in from an existing AnnotatedType. Any elements not present are
     * added. The javaClass will be read in. If the annotation already exists on
     * that element in the configurator the read annotation will be used.
     *
     * @param type the type to read from
     * @throws IllegalArgumentException if type is null
     * @return self
     */
    <U extends T> AnnotatedTypeConfigurator<T> read(AnnotatedType<U> type);

    /**
     * Reads in from an existing AnnotatedType. Any elements not present are
     * added. The javaClass will be read in if overwrite is true. If the
     * annotation already exists on that element in the configurator, overwrite
     * determines whether the original or read annotation will be used.
     *
     * @param type      the type to read from
     * @param overwrite if true, the read annotation will replace any existing
     *                  annotation
     * @throws IllegalArgumentException if type is null
     * @return self
     */
    <U extends T> AnnotatedTypeConfigurator<T> read(AnnotatedType<U> type, boolean overwrite);

    /**
     * Reads the annotations from an existing java type. Annotations already
     * present will be overwritten
     *
     * @param type the type to read from
     * @throws IllegalArgumentException if type is null
     * @return self
     */
    <U extends T> AnnotatedTypeConfigurator<T> read(Class<U> type);

    /**
     * Reads the annotations from an existing java type. If overwrite is true
     * then existing annotations will be overwritten
     *
     * @param type      the type to read from
     * @param overwrite if true, the read annotation will replace any existing
     *                  annotation
     * @return self
     */
    <U extends T> AnnotatedTypeConfigurator<T> read(Class<U> type, boolean overwrite);

    /**
     * Override the declared type of a field
     *
     * @param field the field to override the type on
     * @param type  the new type of the field
     * @throws IllegalArgumentException if field or type is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> overrideFieldType(Field field, Type type);

    /**
     * Override the declared type of a field
     *
     * @param field the field to override the type on
     * @param type  the new type of the field
     * @throws IllegalArgumentException if field or type is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> overrideFieldType(AnnotatedField<? super T> field, Type type);

    /**
     * Override the declared type of a method parameter
     *
     * @param method   the method to override the parameter type on
     * @param position the position of the parameter to override the type on
     * @param type     the new type of the parameter
     * @throws IllegalArgumentException if parameter or type is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> overrideMethodParameterType(Method method, int position, Type type);

    /**
     * Override the declared type of a constructor parameter
     *
     * @param constructor the constructor to override the parameter type on
     * @param position    the position of the parameter to override the type on
     * @param type        the new type of the parameter
     * @throws IllegalArgumentException if parameter or type is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> overrideConstructorParameterType(Constructor<T> constructor, int position, Type type);

    /**
     * Override the declared type of a parameter.
     *
     * @param parameter the parameter to override the type on
     * @param type      the new type of the parameter
     * @throws IllegalArgumentException if parameter or type is null
     * @return self
     */
    AnnotatedTypeConfigurator<T> overrideParameterType(AnnotatedParameter<? super T> parameter, Type type);

    /**
     * getter for the class represented by the AnnotatedType to configure
     *
     * @return Class represented by the AnnotatedType to configure
     *
     */
    Class<T> getJavaClass();

    /**
     * set the class represented by the AnnotatedType to configure
     *
     * @param type the Class represented by the AnnotatedType
     * @return self
     */
    AnnotatedTypeConfigurator<T> setType(Class<T> type);
}
