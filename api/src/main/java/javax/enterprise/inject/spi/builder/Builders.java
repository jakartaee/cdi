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

import java.util.ServiceLoader;

/**
 * Provide static access to various CDI builders.
 * Will load {@link BuildersProvider} implementation with {@link ServiceLoader}.
 *
 *
 * @see BuildersProvider
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public final class Builders {

    /**
     * {@link BuildersProvider} instance loader from {@link ServiceLoader}
     */
    protected static volatile BuildersProvider buildersProvider = null;

    /**
     * Avoid instantiation of this service class
     */
    private Builders() {
    }

    /**
     * Obtains an {@link AnnotatedTypeBuilder} for the given type
     *
     * @param type the type that the AnnotatedType to build will represent
     * @param <T> generic for type
     * @return a reusable AnnotatedTypeBuilder for the given type
     */
    public static <T> AnnotatedTypeBuilder<T> annotatedType(Class<T> type) {
        return provider().getAnnotatedTypeBuilder(type);
    }

    /**
     * Obtains a {@link InjectionPointBuilder}
     *
     * @return a reusable InjectionPointBuilder
     */
    public static InjectionPointBuilder injectionPoint() {
        return provider().getInjectionPointBuilder();
    }

    /**
     * Obtains a {@link BeanAttributesBuilder} for the given type
     *
     * @param type the type of the instance of the configured Bean
     * @param <T> generic for type
     * @return a reusable BeanAttributesBuilder
     */
    public static <T> BeanAttributesBuilder<T> beanAttributes(Class<T> type) {
        return provider().getBeanAttributesBuilder(type);
    }

    /**
     * Obtains a {@link BeanBuilder} for the given type
     *
     * @param type the type of the instance of the built Bean
     * @param <T> generic for type
     * @return a reusable BeanBuilder
     */
    public static <T> BeanBuilder<T> bean(Class<T> type) {
        return provider().getBeanBuilder(type);
    }

    /**
     * Obtains a {@link ObserverMethodBuilder} for the given event type
     *
     * @param type the type of the event for the built ObserverMethod
     * @param <T> generic for type
     * @return a reusable ObserverMethodBuilder
     */
    public static <T> ObserverMethodBuilder<T> observerMethod(Class<T> type) {
        return provider().getObserverMethodBuilder(type);
    }


    private static BuildersProvider provider() {
        if (buildersProvider == null)
            buildersProvider = findProvider();
        return buildersProvider;
    }

    private static BuildersProvider findProvider() {
        ServiceLoader<BuildersProvider> providerLoader;

        providerLoader = ServiceLoader.load(BuildersProvider.class, Builders.class.getClassLoader());
        if (!providerLoader.iterator().hasNext()) {
            throw new IllegalStateException("Unable to locate BuildersProvider");
        }
        return providerLoader.iterator().next();
    }
}
