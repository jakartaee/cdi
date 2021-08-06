package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Collections;
import java.util.Comparator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

final class BuildServicesResolver {
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
            throw new IllegalStateException("Unable to locate AnnotationBuilderFactory implementation");
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
}
