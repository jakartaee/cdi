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

package javax.enterprise.inject.spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.inject.Instance;

/**
 * Provides access to the current container.
 * 
 * @author Pete Muir
 * @author John D. Ament
 * @since 1.1
 */
public abstract class CDI<T> implements Instance<T>, AutoCloseable {

    protected static volatile Set<CDIProvider> discoveredProviders = null;
    protected static volatile CDIProvider configuredProvider = null;

    private static final Object lock = new Object();

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
     * @throws IllegalStateException if no CDI provider is available
     * 
     */
    public static CDI<Object> current() {
        return getCDIProvider().getCDI();
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
            configuredProvider = provider;
        } else {
            throw new IllegalStateException("CDIProvider must not be null");
        }
    }

    /**
     * <p>
     * Retrieves the {@link CDIProvider}.  If one is already configured, uses that.
     * </p>
     * 
     * <p>
     * If {@link #setCDIProvider} was called first, the provider populated there is returned.
     * Otherwise a {@link java.util.ServiceLoader} is used to locate a provider.
     * </p>
     * 
     * @return the configured {@link CDIProvider} or a discovered {@link CDIProvider}.
     * @since 2.0
     */
    public static CDIProvider getCDIProvider() {
        if (configuredProvider != null) {
            return configuredProvider;
        } else {
            // Discover providers and cache
            if (discoveredProviders == null) {
                synchronized (lock) {
                    if (discoveredProviders == null) {
                        findAllProviders();
                    }
                }
            }
            configuredProvider = discoveredProviders.stream()
                    .filter(Objects::nonNull).findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unable to locate CDIProvider"));
            return configuredProvider;
        }
    }

    /**
     * <p>
     * Shuts down this CDI instance.
     * </p>
     *
     * @throws IllegalStateException if called within an application server or if the container is not already started
     * @since 2.0
     */
    public abstract void shutdown();

    /**
     * <p>
     * Shuts down this CDI instance when it is no longer in scope. Implemented from {@link AutoCloseable},
     * </p>
     * 
     * @since 2.0
     */
    @Override
    public void close() {
        this.shutdown();
    }

    // Helper methods

    private static void findAllProviders() {
        Set<CDIProvider> providers = new LinkedHashSet<CDIProvider>();
        try {
            final ClassLoader loader = CDI.class.getClassLoader();
            final Enumeration<URL> resources;
            if (loader != null) {
                resources = loader.getResources("META-INF/services/" + CDIProvider.class.getName());
            } else {
                // this should not happen unless the CDI api is on the boot
                // class path
                resources = ClassLoader.getSystemResources("META-INF/services/" + CDIProvider.class.getName());
            }

            final Set<String> names = new HashSet<String>();
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                InputStream is = url.openStream();
                try {
                    names.addAll(providerNamesFromReader(new BufferedReader(new InputStreamReader(is))));
                } finally {
                    is.close();
                }
            }
            for (String s : names) {
                final Class<CDIProvider> providerClass = (Class<CDIProvider>) Class.forName(s, true, loader);
                providers.add(providerClass.newInstance());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        CDI.discoveredProviders = Collections.unmodifiableSet(providers);
    }

    private static final Pattern nonCommentPattern = Pattern.compile("^([^#]+)");

    private static Set<String> providerNamesFromReader(BufferedReader reader) throws IOException {
        Set<String> names = new HashSet<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            Matcher m = nonCommentPattern.matcher(line);
            if (m.find()) {
                names.add(m.group().trim());
            }
        }
        return names;
    }

    /**
     * Get the CDI BeanManager for the current context
     * 
     */
    public abstract BeanManager getBeanManager();
}
