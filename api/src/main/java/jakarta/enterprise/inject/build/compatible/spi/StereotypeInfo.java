package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import java.util.Collection;

/**
 * A stereotype. May define {@linkplain #defaultScope() default scope},
 * a set of {@linkplain #interceptorBindings() interceptor bindings}, and
 * whether all beans with the stereotype are {@linkplain #isAlternative() alternatives}
 * or have {@linkplain #isNamed() default names}.
 *
 * @since 4.0
 */
public interface StereotypeInfo {
    /**
     * Returns the {@linkplain ScopeInfo default scope} defined by this stereotype.
     * Returns {@code null} if this stereotype doesn't define a default scope.
     *
     * @return the default scope or {@code null}
     */
    ScopeInfo defaultScope();

    /**
     * Returns the set of {@linkplain AnnotationInfo interceptor binding annotations} defined by this stereotype.
     * Returns an empty collection if this stereotype doesn't define any interceptor binding.
     *
     * @return immutable collection of {@linkplain AnnotationInfo interceptor binding annotations}, never {@code null}
     */
    Collection<AnnotationInfo> interceptorBindings();

    /**
     * Returns whether this stereotype is meta-annotated {@link jakarta.enterprise.inject.Alternative @Alternative}.
     * This means that all beans with this stereotype are alternatives.
     *
     * @return whether this stereotype is meta-annotated {@link jakarta.enterprise.inject.Alternative @Alternative}.
     */
    boolean isAlternative();

    // TODO https://github.com/eclipse-ee4j/cdi/issues/495
    //int priority();

    /**
     * Returns whether this stereotype is meta-annotated {@link jakarta.inject.Named @Named}.
     * This means that all beans with this stereotype have default bean names.
     *
     * @return whether this stereotype is meta-annotated {@link jakarta.inject.Named @Named}.
     */
    boolean isNamed();
}
