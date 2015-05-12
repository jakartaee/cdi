package javax.enterprise.inject.spi;

import java.util.Collections;
import java.util.Map;

/**
 * Interface implemented by a CDI provider to provide access to the current container
 * 
 * @author Pete Muir
 * @since 1.1
 */
public interface CDIProvider {

    /**
     * Provides access to the current container
     * 
     * @return the CDI instance for the current container
     */
    CDI<Object> getCDI();

    /**
     * Determines whether or not this CDIProvider has been initialized or not.
     * @return true if initialized, false if not.
     */
    boolean isInitialized();

    /**
     * Initializes a CDI Container.  Cannot be called within an application server.
     * If called in this way, an {@link java.lang.UnsupportedOperationException} is thrown.
     *
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI.current()}
     */
    default CDI<Object> initialize() {
        return initialize(Collections.EMPTY_MAP);
    }

    /**
     * Initializes a CDI Container.  Cannot be called within an application server.
     * If called in this way, an {@link java.lang.UnsupportedOperationException} is thrown.
     *
     * @param params optional parameters, may be null or empty.  May also be immutable.
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI.current()}
     */
    CDI<Object> initialize(Map<String,Object> params);

}
