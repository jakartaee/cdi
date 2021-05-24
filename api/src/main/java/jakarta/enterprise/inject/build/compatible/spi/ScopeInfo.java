package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

public interface ScopeInfo {
    /**
     * @return declaration of the scope annotation
     */
    ClassInfo<?> annotation();

    /**
     * Equivalent to {@code annotation().name()}.
     *
     * @return fully qualified name of the annotation
     */
    default String name() {
        return annotation().name();
    }

    /**
     * Returns whether the scope is normal. In other words, returns whether
     * the scope annotation type is meta-annotated with {@code @NormalScope}.
     *
     * @return whether the scope is normal
     */
    boolean isNormal();
}
