/*
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Stereotype;

/**
 * The BeanAttributes interface exposes the basic attributes of a bean.
 *
 * @author Pete Muir
 * @since 1.1
 * @param <T> the class of the bean instance
 */
public interface BeanAttributes<T> {

    /**
     * Obtains the {@linkplain jakarta.enterprise.inject bean types} of the bean.
     *
     * @return the {@linkplain jakarta.enterprise.inject bean types}
     */
    public Set<Type> getTypes();

    /**
     * Obtains the {@linkplain jakarta.inject.Qualifier qualifiers} of the bean.
     *
     * @return the {@linkplain jakarta.inject.Qualifier qualifiers}
     */
    public Set<Annotation> getQualifiers();

    /**
     * Obtains the {@linkplain jakarta.enterprise.context scope} of the bean.
     *
     * @return the {@linkplain jakarta.enterprise.context scope}
     */
    public Class<? extends Annotation> getScope();

    /**
     * Obtains the bean name of the bean, if it has one.
     * If this bean has no name, returns {@code null}.
     *
     * @return the bean name
     */
    public String getName();

    /**
     * Obtains the {@linkplain Stereotype stereotypes} of the bean.
     *
     * @return the set of {@linkplain Stereotype stereotypes}
     */
    public Set<Class<? extends Annotation>> getStereotypes();

    /**
     * Determines if the bean is an {@linkplain Alternative alternative}.
     *
     * A custom implementation of {@link Bean} may implement {@link Prioritized} in order to be selected for the application.
     * {@link Prioritized#getPriority()} determines the priority used to resolve ambiguities.
     *
     * @return <code>true</code> if the bean is an {@linkplain Alternative alternative}, and <code>false</code>
     *         otherwise.
     */
    public boolean isAlternative();

}
