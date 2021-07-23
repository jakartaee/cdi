package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;
import jakarta.enterprise.context.spi.AlterableContext;

/**
 * Allows configuring custom scope and context. The only mandatory method is
 * {@link ContextConfig#implementation(Class)}, other methods are optional.
 * If they aren't called, correct value is deduced from the context implementation class.
 */
public interface ContextConfig {
    /**
     * If implementation class is defined using {@link #implementation(Class)},
     * this method doesn't have to be called. If it is, it overrides the scope annotation
     * as defined by the implementation class.
     *
     * @param scopeAnnotation scope annotation of the context
     * @return this {@code ContextConfig}
     */
    // TODO if called multiple times, last call wins? or only allow calling once?
    ContextConfig scope(Class<? extends Annotation> scopeAnnotation);

    /**
     * If scope annotation is defined using {@link #scope(Class)},
     * this method doesn't have to be called. If it is, it overrides the normal/pseudo state
     * as defined by the scope annotation.
     *
     * @param isNormal whether the scope is normal
     * @return this {@code ContextConfig}
     */
    // TODO if called multiple times, last call wins? or only allow calling once?
    ContextConfig normal(boolean isNormal);

    /**
     * Defines the context implementation class. It must be {@code public} and
     * have a {@code public} zero-parameter constructor.
     * <p>
     * The implementation class typically provides the scope annotation, and therefore
     * also whether the scope is a normal scope or pseudoscope, but this can be overridden
     * using {@link #scope(Class)} and {@link #normal(boolean)}.
     *
     * @param implementationClass context object implementation class
     * @return this {@code ContextConfig}
     */
    // TODO if called multiple times, last call wins? or only allow calling once?
    ContextConfig implementation(Class<? extends AlterableContext> implementationClass);
}
