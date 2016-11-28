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

import javax.enterprise.context.NormalScope;
import javax.enterprise.inject.Stereotype;
import javax.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;
import javax.inject.Qualifier;
import javax.inject.Scope;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Annotation;

/**
 * <p>
 * This event type is thrown by the container before the bean discovery process begins. If any observer method of the
 * {@code BeforeBeanDiscovery} event throws an exception, the exception is treated as a definition error by the container.
 * </p>
 * 
 * @author Pete Muir
 * @author David Allen
 * @author Antoine Sabot-Durand
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * @throws IllegalStateException if called outside of the observer method invocation
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
     * Thanks to the id parameter, this method allows multiple annotated types, based on the same underlying type, to be defined. {@link AnnotatedType}s
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
     * @param id the identifier used to distinguish this AnnotatedType from an other one based on the same underlying type
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 1.1
     */
    public void addAnnotatedType(AnnotatedType<?> type, String id);

    /**
     * <p>
     * Obtains a new {@link AnnotatedTypeConfigurator} to configure a new {@link javax.enterprise.inject.spi.AnnotatedType} and
     * add it to the set of types which will be scanned during bean discovery at the end of the observer invocation
     * </p>
     *
     * <p>
     * Thanks to the id parameter, this method allows multiple annotated types, based on the same underlying type, to be defined with a builder.
     * {@link AnnotatedType}s discovered by the container use the fully qualified class name of
     * {@link AnnotatedType#getJavaClass()} to identify the type.
     * </p>
     *
     * <p>
     * {@link AfterBeanDiscovery#getAnnotatedType(Class, String)} and {@link AfterBeanDiscovery#getAnnotatedTypes(Class)} allows
     * annotated types to be obtained by identifier.
     * </p>
     *
     * Each call returns a new AnnotatedTypeConfigurator
     *
     *
     * @param type class used to initialized the type and annotations on the {@link AnnotatedTypeConfigurator}
     * @param id the identifier used to distinguish this AnnotatedType from an other one based on the same underlying type
     * @return a non reusable {@link AnnotatedTypeConfigurator} to configure the new AnnotatedType
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 2.0
     */
    public <T> AnnotatedTypeConfigurator<T> addAnnotatedType(Class<T> type, String id);


    /**
     *
     * <p>
     * Obtains a new {@link AnnotatedTypeConfigurator} to configure a new {@link javax.enterprise.inject.spi.AnnotatedType}
     * and declares it as a {@linkplain javax.inject.Qualifier} qualifier type.
     * </p>
     *
     * <p>
     * This is only required if you wish to make an annotation a qualifier without adding {@link Qualifier} to it and need to
     * easily add other annotations (like {@link javax.enterprise.util.Nonbinding} on its members.
     * </p>
     *
     * @param qualifier The annotation class used to initialized the configurator
     * @throws IllegalStateException if called outside of the observer method invocation
     * @return a non reusable {@link AnnotatedTypeConfigurator} to configure the qualifier
     * @since 2.0
     */
     <T extends Annotation> AnnotatedTypeConfigurator<T> configureQualifier(Class<T> qualifier);


    /**
     *
     * <p>
     * Obtains a new {@link AnnotatedTypeConfigurator} to configure a new {@link javax.enterprise.inject.spi.AnnotatedType}
     * and declares it as an {@linkplain Interceptor interceptor} binding type.
     * </p>
     *
     * <p>
     * This is only required if you wish to make an annotation an interceptor binding type without adding
     * {@link InterceptorBinding} to it and need to easily add other annotations
     * (like {@link javax.enterprise.util.Nonbinding} on its members.
     * </p>
     *
     * @param bindingType The annotation class used to initialized the configurator
     * @throws IllegalStateException if called outside of the observer method invocation
     * @return a non reusable {@link AnnotatedTypeConfigurator} to configure the interceptor binding
     * @since 2.0
     */
     <T extends Annotation> AnnotatedTypeConfigurator<T> configureInterceptorBinding(Class<T> bindingType);


}
