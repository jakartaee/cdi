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

import javax.enterprise.context.NormalScope;
import javax.enterprise.inject.Stereotype;
import javax.inject.Qualifier;
import javax.inject.Scope;
import javax.interceptor.InterceptorBinding;

/**
 * <p>
 * This event type is thrown by the container before the bean discovery process begins. If any observer method of the
 * {@code BeforeBeanDiscovery} event throws an exception, the exception is treated as a definition error by the container.
 * </p>
 * 
 * @author Pete Muir
 * @author David Allen
 */
public interface BeforeBeanDiscovery {
    /**
     * <p>
     * Declares an annotation type as a {@linkplain javax.inject.Qualifier} qualifier type.
     * </p>
     * 
     * <p>
     * This is only required if you wish to make an annotation a qualifier without adding {@link Qualifier} to it.
     * </p>
     * 
     * @param qualifier The annotation to treat as a qualifier
     */
    public void addQualifier(Class<? extends Annotation> qualifier);

    /**
     * <p>
     * Declares an annotation type as a {@linkplain javax.inject.Qualifier} qualifier type.
     * </p>
     * 
     * <p>
     * This is only required if you wish to make an annotation a qualifier without adding {@link Qualifier} to it.
     * </p>
     * 
     * @param qualifier The annotation to treat as a qualifier
     * @since 1.1
     */
    public void addQualifier(AnnotatedType<? extends Annotation> qualifier);

    /**
     * <p>
     * Declares an annotation type as a {@linkplain javax.enterprise.context scope type}.
     * </p>
     * 
     * <p>
     * This is only required if you wish to make an annotation a scope type without adding the {@link NormalScope} or
     * {@link Scope} annotations to it. You can also use this method to override an existing normal scope definition.
     * </p>
     * 
     * @see AfterBeanDiscovery#addContext(javax.enterprise.context.spi.Context)
     * 
     * @param scopeType The annotation type to treat as a {@linkplain javax.enterprise.context scope type}
     * @param normal Indicates if the scope is normal
     * @param passivating Indicates if the scope is {@linkplain javax.enterprise.inject.spi.PassivationCapable passivation
     *        capable}
     */
    public void addScope(Class<? extends Annotation> scopeType, boolean normal, boolean passivating);

    /**
     * <p>
     * Declares an annotation type as a {@linkplain javax.enterprise.inject.Stereotype stereotype}, and specifies its
     * meta-annotations.
     * </p>
     * 
     * <p>
     * This is only required if you wish to make an annotation a stereotype without adding {@link Stereotype} to it. You can
     * also use this method to override an existing stereotype definition.
     * </p>
     * 
     * @param stereotype The annotation type to treat as a {@linkplain javax.enterprise.inject.Stereotype stereotype}
     * @param stereotypeDef An optional list of annotations defining the {@linkplain javax.enterprise.inject.Stereotype
     *        stereotype}
     */
    public void addStereotype(Class<? extends Annotation> stereotype, Annotation... stereotypeDef);

    /**
     * <p>
     * Declares an annotation type as an {@linkplain Interceptor interceptor} binding type.
     * </p>
     * 
     * <p>
     * This is only required if you wish to make an annotation an interceptor binding type without adding
     * {@link InterceptorBinding} to it.
     * </p>
     * 
     * @param bindingType The annotation type to treat as an interceptor binding type
     */
    public void addInterceptorBinding(AnnotatedType<? extends Annotation> bindingType);

    /**
     * <p>
     * Declares an annotation type as an {@linkplain Interceptor interceptor} binding type, and specifies its meta-annotations.
     * </p>
     * 
     * <p>
     * This is only required if you wish to make an annotation an interceptor binding type without adding
     * {@link InterceptorBinding} to it.
     * </p>
     * 
     * @param bindingType The annotation type to treat as an interceptor binding type
     * @param bindingTypeDef An optional list of annotations defining the {@linkplain Interceptor interceptor}
     * @since 1.1
     */
    public void addInterceptorBinding(Class<? extends Annotation> bindingType, Annotation... bindingTypeDef);

    /**
     * <p>
     * Adds a given {@link javax.enterprise.inject.spi.AnnotatedType} to the set of types which will be scanned during bean
     * discovery.
     * </p>
     * 
     * <p>
     * This method is deprecated from CDI 1.1 and {@link #addAnnotatedType(AnnotatedType, String)} should be used instead.
     * </p>
     * 
     * @param type The {@link javax.enterprise.inject.spi.AnnotatedType} to add for later scanning
     */
    public void addAnnotatedType(AnnotatedType<?> type);

    /**
     * <p>
     * Adds a given {@link javax.enterprise.inject.spi.AnnotatedType} to the set of types which will be scanned during bean
     * discovery.
     * </p>
     * 
     * <p>
     * This method allows multiple annotated types, based on the same underlying type, to be defined. {@link AnnotatedType}s
     * discovered by the container use the fully qualified class name of {@link AnnotatedType#getJavaClass()} to identify the
     * type.
     * </p>
     * 
     * <p>
     * {@link AfterBeanDiscovery#getAnnotatedType(Class, String)} and {@link AfterBeanDiscovery#getAnnotatedTypes(Class)} allows
     * annotated types to be obtained by identifier.
     * </p>
     * 
     * @param type The {@link javax.enterprise.inject.spi.AnnotatedType} to add for later scanning
     * @param id The id of the annotated type
     * @since 1.1
     */
    public void addAnnotatedType(AnnotatedType<?> type, String id);
}
