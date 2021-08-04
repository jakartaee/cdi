package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * Provides read-only information about bean's scope.
 */
public interface ScopeInfo {
    /**
     * Returns the declaration of this scope annotation.
     *
     * @return declaration of this scope annotation, never {@code null}
     */
    ClassInfo annotation();

    /**
     * Binary name of this scope annotation, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the scope annotation name as returned by {@link Class#getName()}.
     * Equivalent to {@code annotation().name()}.
     *
     * @return binary name of this scope annotation, never {@code null}
     */
    default String name() {
        return annotation().name();
    }

    /**
     * Returns whether this scope is normal. In other words, returns whether
     * this scope annotation is meta-annotated {@code @NormalScope}.
     *
     * @return whether this scope is normal
     */
    boolean isNormal();
}
