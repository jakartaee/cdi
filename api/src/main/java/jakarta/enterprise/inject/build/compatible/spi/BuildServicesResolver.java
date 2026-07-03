/*
 * Copyright (c) 2021 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Comparator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An internal helper to resolve {@link BuildServices} implementations.
 * This class is public only for integrators and should <em>not</em> be used by applications.
 *
 * @since 4.0
 */
public final class BuildServicesResolver {
    private static final AtomicReference<BuildServices> configuredBuildServices = new AtomicReference<>();

    static BuildServices get() {
        BuildServices current = configuredBuildServices.get();
        if (current != null) {
            return current;
        }

        BuildServices found = discoverFactories().iterator().next();

        if (configuredBuildServices.compareAndSet(null, found)) {
            return found;
        }
        return configuredBuildServices.get();
    }

    private static Set<BuildServices> discoverFactories() {
        Set<BuildServices> factories = new TreeSet<>(
                Comparator.comparingInt(BuildServices::getPriority).reversed());

        ServiceLoader<BuildServices> loader = ServiceLoader.load(BuildServices.class,
                BuildServicesResolver.class.getClassLoader());

        if (!loader.iterator().hasNext()) {
            throw new IllegalStateException("Unable to locate BuildServices implementation");
        }

        try {
            for (BuildServices buildServices : loader) {
                factories.add(buildServices);
            }
        } catch (ServiceConfigurationError e) {
            throw new IllegalStateException(e);
        }

        return factories;
    }

    /**
     * This method should <em>not</em> be used by applications. It is only exposed for integrators
     * with complex classloading architectures, where service loader lookup doesn't work out of the box.
     * With this method, an integrator may manually provide an instance of {@link BuildServices} and
     * this class will no longer attempt to look it up using service loader.
     *
     * @param instance a {@link BuildServices} instance that should be used, must not be {@code null}
     * @throws IllegalArgumentException if the provided argument is null
     */
    public static void setBuildServices(BuildServices instance) {
        if (instance == null) {
            throw new IllegalArgumentException("BuildServices instance must not be null");
        }
        configuredBuildServices.set(instance);
    }
}
