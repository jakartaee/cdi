package javax.enterprise.inject.spi;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * This event type is thrown by the container before the deployment validation process begins. If any observer method of the
 * {@code BeforeDeploymentValidation} event throws an exception, the exception is treated as a definition error by the container.
 * </p>
 * <p>
 * Any observer of this event is permitted to add classes to, or remove classes from, the list of alternatives, list of
 * interceptors or list of decorators. The container will use the final values of these lists, after all observers have
 * been called, to determine the enabled alternatives, interceptors, and decorators for application.
 * </p>
 * 
 * @author Pete Muir
 */
public interface BeforeDeploymentValidation {

    /**
     * @return the ordered list of enabled alternatives of the bean deployment archive
     */
    public List<Class<?>> getAlternatives();

    /**
     * @return the ordered list of enabled interceptors of the bean deployment archive.
     */
    public List<Class<?>> getInterceptors();

    /**
     * @return the ordered list of enabled decorators of the bean deployment archive
     */
    public List<Class<?>> getDecorators();

}