package jakarta.enterprise.inject.build.compatible.spi;

/**
 * Allows registering additional classes to be scanned during bean discovery.
 * Annotations on these classes can later be transformed using {@link Enhancement @Enhancement}.
 *
 * @since 4.0
 */
public interface ScannedClasses {
    /**
     * Adds a class with given name to the set of classes to be scanned during bean discovery.
     *
     * @param className binary name of the class, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getName()}
     */
    void add(String className);
}
