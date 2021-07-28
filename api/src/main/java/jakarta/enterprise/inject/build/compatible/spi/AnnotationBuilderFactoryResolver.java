package jakarta.enterprise.inject.build.compatible.spi;

import java.util.Collections;
import java.util.Comparator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

final class AnnotationBuilderFactoryResolver {
    private static final Object lock = new Object();
    private static volatile Set<AnnotationBuilderFactory> discoveredFactories;
    private static volatile AnnotationBuilderFactory configuredFactory;

    static AnnotationBuilderFactory get() {
        if (configuredFactory != null) {
            return configuredFactory;
        }

        if (discoveredFactories == null) {
            synchronized (lock) {
                if (discoveredFactories == null) {
                    discoverFactories();
                }
            }
        }

        configuredFactory = discoveredFactories.iterator().next();

        return configuredFactory;
    }

    private static void discoverFactories() {
        Set<AnnotationBuilderFactory> factories = new TreeSet<>(
                Comparator.comparingInt(AnnotationBuilderFactory::getPriority).reversed());

        ServiceLoader<AnnotationBuilderFactory> loader = SecurityActions.loadService(
                AnnotationBuilderFactory.class, AnnotationBuilderFactoryResolver.class.getClassLoader());

        if (!loader.iterator().hasNext()) {
            throw new IllegalStateException("Unable to locate AnnotationBuilderFactory implementation");
        }

        try {
            for (AnnotationBuilderFactory factory : loader) {
                factories.add(factory);
            }
        } catch (ServiceConfigurationError e) {
            throw new IllegalStateException(e);
        }

        AnnotationBuilderFactoryResolver.discoveredFactories = Collections.unmodifiableSet(factories);
    }
}
