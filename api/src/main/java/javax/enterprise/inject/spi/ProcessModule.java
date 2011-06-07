package javax.enterprise.inject.spi;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * The container fires an event of this type for each, before it processes the classes packaged in that module.
 * </p>
 * <p>
 * Any observer of this event is permitted to add classes to, or remove classes from, the set of alternatives, list of
 * interceptors or list of decorators. The container will use the final values of these collections, after all observers have
 * been called, to determine the enabled alternatives, interceptors, and decorators for the bean deployment archive. The initial
 * values of these collections is determined by reading the <code>beans.xml</code> file of the bean deployment archive.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessModule} event throws an exception, the exception is treated as a definition error
 * by the container.
 * </p>
 * 
 * @author Pete Muir
 */
public interface ProcessModule {

    /**
     * @return the set of enabled alternatives of the bean deployment archive
     */
    public Set<Class<?>> getAlternatives();

    /**
     * @return the list of enabled interceptors of the bean deployment archive.
     */
    public List<Class<?>> getInterceptors();

    /**
     * @return the list of enabled decorators of the bean deployment archive
     */
    public List<Class<?>> getDecorators();

    /**
     * @return an iterator over <code>AnnotatedType</code> objects representing the Java classes and interfaces in the
     *         bean archive
     */
    public Iterator<AnnotatedType<?>> getAnnotatedTypes();

    /**
     * @return an input stream which can be used to read in the <code>beans.xml</code> for this module
     */
    public InputStream getBeansXml();
}