package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * A factory for resolving instances of {@link jakarta.enterprise.lang.model.declarations.ClassInfo}.
 */
public interface ClassInfoFactory {
    /**
     * Allows resolving instances of {@link ClassInfo} from the application class path using the {@link Class#getName() binary name} of the class.
     *
     * @param name The binary name of the class, must not be {@code null}
     * @return A {@link ClassInfo} instance or {@code null} if it does not exist
     */
    ClassInfo resolve(String name);
}
