/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, 2015, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.inject.spi;

import jakarta.enterprise.inject.Instance;

import java.util.Collections;
import java.util.Comparator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

/**
 * Provides access to the current container.
 *
 * <p>
 * CDI implements {@link Instance} and therefore might be used to perform programmatic lookup.
 * If no qualifier is passed to {@link #select} method, the <code>@Default</code> qualifier is assumed.
 * </p>
 *
 *
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 * @author John D. Ament
 * @since 1.1
 * @param <T> type inherited from {@link Instance}. Always Object for CDI
 */
public abstract class CDI<T> implements Instance<T> {

    private static final Object lock = new Object();
    private static volatile boolean providerSetManually = false;
    protected static volatile Set<CDIProvider> discoveredProviders = null;
    protected static volatile CDIProvider configuredProvider = null;

    /**
     * <p>
     * Get the CDI instance that provides access to the current container.
     * </p>
     * 
     * <p>
     * If there are no providers available, an {@link IllegalStateException} is thrown, otherwise the first provider which can
     * access the container is used.
     * </p>
     * 
     * @throws IllegalStateException if no {@link CDIProvider} is available
     * @return the CDI instance
     */
    public static CDI<Object> current() {
        return getCDIProvider().getCDI();
    }

    /**
     *
     * Obtain the {@link CDIProvider} the user set with {@link #setCDIProvider(CDIProvider)} or the last returned
     * {@link CDIProvider} if it returns valid CDI container. Otherwise use the serviceloader to retrieve the
     * {@link CDIProvider} with the highest priority.
     *
     * @return the {@link CDIProvider} set by user or retrieved by serviceloader
     */
    private static CDIProvider getCDIProvider() {
        try {
            if (configuredProvider != null && configuredProvider.getCDI() != null) {
                return configuredProvider;
            }
        } catch (IllegalStateException e) {
            //if the provider is set manually we do not look for a different provider.
            if (providerSetManually) {
                throw e;
            }
        }
        configuredProvider = null;
        // Discover providers and cache
        if (discoveredProviders == null) {
            synchronized (lock) {
                if (discoveredProviders == null) {
                    findAllProviders();
                }
            }
        }
        configuredProvider = discoveredProviders.stream()
                .filter(CDI::checkProvider)
                .findFirst().orElseThrow(() -> new IllegalStateException("Unable to access CDI"));
        return configuredProvider;
    }

    private static boolean checkProvider(CDIProvider c) {
        try {
            return c.getCDI() != null;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    /**
     * <p>
     * Set the {@link CDIProvider} to use.
     * </p>
     *
     * <p>
     * If a {@link CDIProvider} is set using this method, any provider specified as a service provider will not be used.
     * </p>
     *
     * @param provider the provider to use
     * @throws IllegalStateException if the {@link CDIProvider} is already set
     */
    public static void setCDIProvider(CDIProvider provider) {
        if (provider != null) {
            providerSetManually = true;
            configuredProvider = provider;
        } else {
            throw new IllegalStateException("CDIProvider must not be null");
        }
    }

    private static void findAllProviders() {

        ServiceLoader<CDIProvider> providerLoader;
        Set<CDIProvider> providers = new TreeSet<>(Comparator.comparingInt(CDIProvider::getPriority).reversed());

        providerLoader = SecurityActions.loadService(CDIProvider.class, CDI.class.getClassLoader());

        if(! providerLoader.iterator().hasNext()) {
            throw new IllegalStateException("Unable to locate CDIProvider");
        }

        try {
            providerLoader.forEach(providers::add);
        } catch (ServiceConfigurationError e) {
            throw new IllegalStateException(e);
        }
        CDI.discoveredProviders = Collections.unmodifiableSet(providers);
    }

    // Helper methods

    /**
     * Get the CDI BeanManager for the current context
     *
     * @return the {@link BeanManager}
     */
    public abstract BeanManager getBeanManager();

    /**
     * Get the CDI {@link BeanContainer} for the current context.
     *
     * Default implementation just forwards the call to {@link #getBeanManager()}.
     *
     * @return the {@link BeanContainer}
     */
    public BeanContainer getBeanContainer() {
        return getBeanManager();
    }

}
