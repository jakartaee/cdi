package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

public interface ScopeInfo {
    /**
     * Returns the declaration of this scope annotation's type.
     *
     * @return declaration of this scope annotation's type, never {@code null}
     */
    ClassInfo<?> annotation();

    /**
     * Binary name of this scope annotation's type, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the scope annotation type name as returned by {@link Class#getName()}.
     * Equivalent to {@code annotation().name()}.
     *
     * @return binary name of this scope annotation's type, never {@code null}
     */
    default String name() {
        return annotation().name();
    }

    /**
     * Returns whether the scope is normal. In other words, returns whether
     * the scope annotation type is meta-annotated {@code @NormalScope}.
     *
     * @return whether the scope is normal
     */
    boolean isNormal();
}
