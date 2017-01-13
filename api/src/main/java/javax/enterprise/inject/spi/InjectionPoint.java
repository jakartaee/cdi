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
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.inject.Instance;

/**
 * <p>
 * Provides access to metadata about an injection point. May represent an {@linkplain javax.inject.Inject injected field} or a
 * parameter of a {@linkplain javax.inject.Inject bean constructor}, {@linkplain javax.inject.Inject initializer method},
 * {@linkplain javax.enterprise.inject.Produces producer method}, {@linkplain javax.enterprise.inject.Disposes disposer method}
 * or {@linkplain javax.enterprise.event.Observes observer method}.
 * </p>
 * 
 * <p>
 * If the injection point is a dynamically selected reference obtained then the metadata obtain reflects the injection point of
 * the {@link Instance}, with the required type and any additional required qualifiers defined by {@linkplain Instance
 * Instance.select()}.
 * </p>
 * 
 * <p>
 * Occasionally, a bean with scope {@link javax.enterprise.context.Dependent &#064;Dependent} needs to access metadata relating
 * to the object to which it belongs. The bean may inject an {@code InjectionPoint} representing the injection point into which
 * the bean was injected.
 * </p>
 * 
 * <p>
 * For example, the following producer method creates injectable <tt>Logger</tt> s. The log category of a <tt>Logger</tt>
 * depends upon the class of the object into which it is injected.
 * </p>
 * 
 * <pre>
 * &#064;Produces
 * Logger createLogger(InjectionPoint injectionPoint) {
 *     return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
 * }
 * </pre>
 * 
 * <p>
 * Only {@linkplain javax.enterprise.context.Dependent dependent} objects, may obtain information about the injection point to
 * which they belong.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 */
public interface InjectionPoint {

    /**
     * Get the required type of injection point.
     * 
     * @return the required type
     */
    public Type getType();

    /**
     * Get the required qualifiers of the injection point.
     * 
     * @return the required qualifiers
     */
    public Set<Annotation> getQualifiers();

    /**
     * Get the {@link javax.enterprise.inject.spi.Bean} object representing the bean that defines the injection point. If the
     * injection point does not belong to a bean, return a null value.
     * 
     * @return the {@link javax.enterprise.inject.spi.Bean} object representing bean that defines the injection point, of null
     *         if the injection point does not belong to a bean
     */
    public Bean<?> getBean();

    /**
     * Get the {@link java.lang.reflect.Field} object in the case of field injection, the {@link java.lang.reflect.Method}
     * object in the case of method parameter injection or the {@link java.lang.reflect.Constructor} object in the case of
     * constructor parameter injection.
     * 
     * @return the member
     */
    public Member getMember();

    /**
     * Obtain an instance of {@link javax.enterprise.inject.spi.AnnotatedField} or
     * {@link javax.enterprise.inject.spi.AnnotatedParameter}, depending upon whether the injection point is an injected field
     * or a constructor/method parameter.
     * 
     * @return an {@code AnnotatedField} or {@code AnnotatedParameter}
     */
    public Annotated getAnnotated();

    /**
     * Determines if the injection point is a decorator delegate injection point.
     * 
     * @return <tt>true</tt> if the injection point is a decorator delegate injection point, and <tt>false</tt> otherwise
     */
    public boolean isDelegate();

    /**
     * Determines if the injection is a transient field.
     * 
     * @return <tt>true</tt> if the injection point is a transient field, and <tt>false</tt> otherwise
     */
    public boolean isTransient();
}
