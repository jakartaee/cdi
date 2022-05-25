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

import java.util.Collections;
import java.util.Comparator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

/**
 * An internal helper to resolve {@link BuildServices} implementations.
 * This class is public only for integrators and should <em>not</em> be used by applications.
 *
 * @since 4.0
 */
public final class BuildServicesResolver {
    private static final Object lock = new Object();
    private static volatile Set<BuildServices> discoveredBuildServices;
    private static volatile BuildServices configuredBuildServices;

    static BuildServices get() {
        if (configuredBuildServices != null) {
            return configuredBuildServices;
        }

        if (discoveredBuildServices == null) {
            synchronized (lock) {
                if (discoveredBuildServices == null) {
                    discoverFactories();
                }
            }
        }

        configuredBuildServices = discoveredBuildServices.iterator().next();

        return configuredBuildServices;
    }

    private static void discoverFactories() {
        Set<BuildServices> factories = new TreeSet<>(
                Comparator.comparingInt(BuildServices::getPriority).reversed());

        ServiceLoader<BuildServices> loader = SecurityActions.loadService(
                BuildServices.class, BuildServicesResolver.class.getClassLoader());

        if (!loader.iterator().hasNext()) {
            throw new IllegalStateException("Unable to locate BuildServices implementation");
        }

        try {
            for (BuildServices buildServicies : loader) {
                factories.add(buildServicies);
            }
        } catch (ServiceConfigurationError e) {
            throw new IllegalStateException(e);
        }

        BuildServicesResolver.discoveredBuildServices = Collections.unmodifiableSet(factories);
    }

    /**
     * This method should <em>not</em> be used by applications. It is only exposed for integrators
     * with complex classloading architectures, where service loader lookup doesn't work out of the box.
     * With this method, an integrator may manually provide an instance of {@link BuildServices} and
     * this class will no longer attempt to look it up using service loader.
     *
     * @param instance a {@link BuildServices} instance that should be used, must not be {@code null}
     * @throws IllegalArgumentException if the provided argument is null
     * @throws IllegalStateException if the {@link BuildServices} are already set
     */
    public static void setBuildServices(BuildServices instance) {
        if (instance == null) {
            throw new IllegalArgumentException("BuildServices instance must not be null");
        }
        if (configuredBuildServices != null) {
            configuredBuildServices = instance;
        } else {
            throw new IllegalStateException("BuildServices cannot be set repeatedly. Existing BuildServices are " + configuredBuildServices);
        }
    }
}
