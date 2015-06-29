package javax.enterprise.inject.spi;

import java.util.Collections;
import java.util.Map;

/**
 * Interface implemented by a CDI provider to provide access to the current container
 * 
 * @author Pete Muir
 * @author John D. Ament
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
     * 
     * @return true if initialized, false if not.
     * @since 2.0
     */
    boolean isInitialized();

    /**
     * <p>
     * Initializes a CDI Container.
     * </p>
     * <p>
     * Cannot be called within an application server.
     * </p>
     *
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI#current()}
     * @throws UnsupportedOperationException if called within an application server
     * @since 2.0
     * 
     */
    default CDI<Object> initialize() {
        return initialize(Collections.emptyMap());
    }

    /**
     * <p>
     * Initializes a CDI Container with custom params.
     * </p>
     * <p>
     * Cannot be called within an application server.
     * </p>
     *
     * @param params optional parameters, may be null or empty.  May also be immutable.
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI#current()}
     * @throws UnsupportedOperationException if called within an application server
     * @since 2.0
     */
    CDI<Object> initialize(Map<String,Object> params);

}
