package javax.enterprise.inject.spi;

import java.util.Collections;
import java.util.Map;

/**
 * Represents a bootable CDIContainer.
 */
public interface CDIContainer extends AutoCloseable {
    /**
     * Initializes a CDI Container.  Cannot be called within an application server or if the container is already started.
     * If called in either of these ways, an {@link java.lang.IllegalStateException} is thrown.
     *
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI.current()}
     */
    default CDI<Object> initialize() {
        return initialize(Collections.EMPTY_MAP);
    }

    /**
     * Initializes a CDI Container.  Cannot be called within an application server or if the container is already started.
     * If called in either of these ways, an {@link java.lang.IllegalStateException} is thrown.
     *
     * @param params optional parameters, may be null or empty.  May also be immutable.
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI.current()}
     */
    CDI<Object> initialize(Map<String,Object> params);

    /**
     * Shuts down the current CDIContainer.  Cannot be invoked within an application server or if the container is not
     * already started.  If called in either of these ways, an {@link java.lang.IllegalStateException} is thrown.
     */
    void shutdown();

    /**
     * Close the container, invoking shutdown on the current container.
     * @throws Exception
     */
    @Override
    default void close() throws Exception {
        shutdown();
    }
}
