package javax.enterprise.inject.spi;

import static java.util.ServiceLoader.load;

/**
 * Handles the lookup of CDIContainer based on container implementations found on classpath.
 */
public abstract class CDIContainerLoader {

    protected static CDIContainer instance = null;

    /**
     * Retrieves the {@link javax.enterprise.inject.spi.CDIContainer} found on the classpath.  The container is meant
     * to be used like a singleton and repeated calls will return the same instance back to the caller.
     *
     * @return the located {@link javax.enterprise.inject.spi.CDIContainer}
     * @throws java.lang.IllegalStateException if no container implementation can be found on the classpath.
     */
    public static CDIContainer getCDIContainer() {
        synchronized (CDIContainerLoader.class) {
            if (instance == null) {
                locateCDIContainerInstance();
            }
        }
        return instance;
    }

    private static void locateCDIContainerInstance() {
        for(CDIContainer container : load(CDIContainer.class)) {
            if(container!= null) {
                instance = container;
                return;
            }
        }
        if(instance == null) {
            throw new IllegalStateException("No configured CDIContainer found on classpath.");
        }
    }

}
