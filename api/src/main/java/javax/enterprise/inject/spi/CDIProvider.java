package javax.enterprise.inject.spi;

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
    public CDI<Object> getCDI();

}
