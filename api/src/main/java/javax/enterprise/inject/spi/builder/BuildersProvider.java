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

/**
 * Interface implemented by the CDI provider to give access to builders implementations
 * Implementation will be loaded with {@link java.util.ServiceLoader}
 *
 * @see Builders
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public interface BuildersProvider {

    /**
     * Obtains an {@link AnnotatedTypeBuilder} for the given type
     *
     * @param type the type that the AnnotatedType to build will represent
     * @param <T> generic for type
     * @return a reusable AnnotatedTypeBuilder for the given type
     */
    <T> AnnotatedTypeBuilder<T> getAnnotatedTypeBuilder(Class<T> type);

    /**
     * Obtains a {@link InjectionPointBuilder}
     *
     * @return a reusable InjectionPointBuilder
     */
    InjectionPointBuilder getInjectionPointBuilder();

    /**
     * Obtains a {@link BeanAttributesBuilder} for the given type
     *
     * @param type the type of the instance of the configured Bean
     * @param <T> generic for type
     * @return a reusable BeanAttributesBuilder
     */
    <T> BeanAttributesBuilder<T> getBeanAttributesBuilder(Class<T> type);

    /**
     * Obtains a {@link BeanBuilder} for the given type
     *
     * @param type the type of the instance of the built Bean
     * @param <T> generic for type
     * @return a reusable BeanAttributesBuilder
     */
    <T> BeanBuilder<T> getBeanBuilder(Class<T> type);

    /**
     * Obtains a {@link ObserverMethodBuilder} for the given event type
     *
     * @param type the type of the event for the built ObserverMethod
     * @param <T> generic for type
     * @return a reusable BeanBuilder
     */
    <T> ObserverMethodBuilder<T> getObserverMethodBuilder(Class<T> type);
}
