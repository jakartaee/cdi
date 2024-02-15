/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

import jakarta.decorator.Delegate;

/**
 * <p>
 * Represents an enabled {@linkplain jakarta.decorator decorator}.
 * </p>
 * <p>
 * Since CDI 2.0, an implementation of this interface may implement {@link Prioritized} in order to enable the decorator with
 * given priority value for entire application.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for decorators.
 * </p>
 *
 * @author Gavin King
 * @author Pete Muir
 *
 * @param <T> the decorator bean class
 */
public interface Decorator<T> extends Bean<T> {

    /**
     * <p>
     * Obtains the {@linkplain Type type} of the {@linkplain Delegate delegate injection point}.
     * </p>
     *
     * @return the delegate {@linkplain Type type}
     */
    public Type getDelegateType();

    /**
     * <p>
     * Obtains the {@linkplain jakarta.inject.Qualifier qualifiers} of the {@linkplain Delegate delegate injection
     * point}.
     * </p>
     *
     * @return the delegate {@linkplain jakarta.inject.Qualifier qualifiers}
     */
    public Set<Annotation> getDelegateQualifiers();

    /**
     * <p>
     * Obtains the {@linkplain jakarta.decorator decorated types}.
     * </p>
     *
     * @return the set of decorated {@linkplain Type types}
     */
    public Set<Type> getDecoratedTypes();

}