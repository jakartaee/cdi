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
import java.lang.reflect.Type;
import java.util.Set;

/**
 * <p>
 * Represents an enabled {@linkplain javax.decorator decorator}.
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
     * Obtains the {@linkplain Type type} of the {@linkplain javax.decorator.Delegate delegate injection point}.
     * </p>
     * 
     * @return the delegate {@linkplain Type type}
     */
    public Type getDelegateType();

    /**
     * <p>
     * Obtains the {@linkplain javax.inject.Qualifier qualifiers} of the {@linkplain javax.decorator.Delegate delegate injection
     * point}.
     * </p>
     * 
     * @return the delegate {@linkplain javax.inject.Qualifier qualifiers}
     */
    public Set<Annotation> getDelegateQualifiers();

    /**
     * <p>
     * Obtains the {@linkplain javax.decorator decorated types}.
     * </p>
     * 
     * @return the set of decorated {@linkplain Type types}
     */
    public Set<Type> getDecoratedTypes();

}