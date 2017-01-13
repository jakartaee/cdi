/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
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

import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.ProcessBeanAttributes;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * This API is an helper to configure a new {@link BeanAttributes} instance.
 * CDI container must provides an implementation of this interface.
 *
 * This configurator is not thread safe and shall not be used concurrently.
 *
 * @see ProcessBeanAttributes#configureBeanAttributes()
 * @param <T> the class of the bean instance
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public interface BeanAttributesConfigurator<T> {

    /**
     *
     * Add a type to the bean types
     *
     * @param type the type to add
     * @return self
     */
    BeanAttributesConfigurator<T> addType(Type type);

    /**
     *
     * Add a type to the bean types
     *
     * @param typeLiteral the type to add
     * @return self
     */
    BeanAttributesConfigurator<T> addType(TypeLiteral<?> typeLiteral);

    /**
     *
     * Add types to the bean types
     *
     * @param types types to add
     * @return self
     */
    BeanAttributesConfigurator<T> addTypes(Type... types);

    /**
     *
     * Add types to the bean types
     *
     * @param types types to add
     * @return self
     */
    BeanAttributesConfigurator<T> addTypes(Set<Type> types);

    /**
     * Adds an unrestricted set of bean types for the given type as if it represented a bean class of a managed bean.
     * Illegal bean types are omitted.
     *
     * @param type to build the closure from
     * @return self
     */
    BeanAttributesConfigurator<T> addTransitiveTypeClosure(Type type);

    /**
     *
     * Replace bean types
     *
     * @param types the types of the configured bean
     * @return self
     */
    BeanAttributesConfigurator<T> types(Type... types);

    /**
     *
     * Replace bean types
     *
     * @param types the types of the configured bean
     * @return self
     */
    BeanAttributesConfigurator<T> types(Set<Type> types);

    /**
     *
     * Replace Bean scope
     *
     * @param scope new scope for the configured bean
     * @return self
     */
    BeanAttributesConfigurator<T> scope(Class<? extends Annotation> scope);

    /**
     *
     * Add a qualifier to the configured bean
     *
     * @param qualifier qualifier to add
     * @return self
     */
    BeanAttributesConfigurator<T> addQualifier(Annotation qualifier);

    /**
     *
     * Add qualifiers to the bean.
     *
     * @param qualifiers qualifiers to add
     * @return self
     */
    BeanAttributesConfigurator<T> addQualifiers(Annotation... qualifiers);

    /**
     *
     * Add qualifiers to the bean.
     *
     * @param qualifiers qualifiers to add
     * @return self
     */
    BeanAttributesConfigurator<T> addQualifiers(Set<Annotation> qualifiers);

    /**
     * Replace all qualifiers.
     *
     * @param qualifiers qualifiers for the build bean
     * @return self
     */
    BeanAttributesConfigurator<T> qualifiers(Annotation... qualifiers);

    /**
     * Replace all qualifiers.
     *
     * @param qualifiers for the configured bean
     * @return self
     */
    BeanAttributesConfigurator<T> qualifiers(Set<Annotation> qualifiers);

    /**
     *
     * Add a stereotype to the configured bean
     *
     * @param stereotype stereotype to add
     * @return self
     */
    BeanAttributesConfigurator<T> addStereotype(Class<? extends Annotation> stereotype);

    /**
     *
     * Add stereotypes to the configured bean
     *
     * @param stereotypes stereotypes to add
     * @return self
     */
    BeanAttributesConfigurator<T> addStereotypes(Set<Class<? extends Annotation>> stereotypes);

    /**
     *
     * Replace stereotypes on the configured bean
     *
     * @param stereotypes for the configured bean
     * @return self
     */
    BeanAttributesConfigurator<T> stereotypes(Set<Class<? extends Annotation>> stereotypes);

    /**
     *
     * Set the name of the configured bean
     *
     * @param name name for the configured bean
     * @return self
     */
    BeanAttributesConfigurator<T> name(String name);

    /**
     *
     * Change the alternative status of the configured bean.
     * By default the configured bean is not an alternative.
     *
     * @param value value for alternative property
     * @return self
     */
    BeanAttributesConfigurator<T> alternative(boolean value);

}
