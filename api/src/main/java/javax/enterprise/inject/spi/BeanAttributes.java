/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
import java.lang.reflect.Type;
import java.util.Set;

/**
 * The BeanAttributes interface exposes the basic attributes of a bean.
 * 
 * @author Pete Muir
 * @since 1.1
 * @param <T> the class of the bean instance
 */
public interface BeanAttributes<T> {

    /**
     * Obtains the {@linkplain javax.enterprise.inject bean types} of the bean.
     * 
     * @return the {@linkplain javax.enterprise.inject bean types}
     */
    public Set<Type> getTypes();

    /**
     * Obtains the {@linkplain javax.inject.Qualifier qualifiers} of the bean.
     * 
     * @return the {@linkplain javax.inject.Qualifier qualifiers}
     */
    public Set<Annotation> getQualifiers();

    /**
     * Obtains the {@linkplain javax.enterprise.context scope} of the bean.
     * 
     * @return the {@linkplain javax.enterprise.context scope}
     */
    public Class<? extends Annotation> getScope();

    /**
     * Obtains the {@linkplain javax.enterprise.inject EL name} of a bean, if it has one.
     * 
     * @return the {@linkplain javax.enterprise.inject EL name}
     */
    public String getName();

    /**
     * Obtains the {@linkplain javax.enterprise.inject.Stereotype stereotypes} of the bean.
     * 
     * @return the set of {@linkplain javax.enterprise.inject.Stereotype stereotypes}
     */
    public Set<Class<? extends Annotation>> getStereotypes();

    /**
     * Determines if the bean is an {@linkplain javax.enterprise.inject.Alternative alternative}.
     *
     * A custom implementation of {@link Bean} may implement {@link Prioritized} in order to be selected for the application.
     * {@link Prioritized#getPriority()} determines the priority used to resolve ambiguities.
     *
     * @return <tt>true</tt> if the bean is an {@linkplain javax.enterprise.inject.Alternative alternative}, and <tt>false</tt>
     *         otherwise.
     */
    public boolean isAlternative();

}
