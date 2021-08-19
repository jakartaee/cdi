package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.context.spi.AlterableContext;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

/**
 * Allows registering custom CDI meta-annotations: qualifiers, interceptor bindings,
 * stereotypes, and scopes. When registering a custom scope, a context class must
 * also be provided.
 *
 * @since 4.0
 */
public interface MetaAnnotations {
    // TODO this API style is not very common, and makes addContext too different
    //  I only added it to make Quarkus implementation easier, but should definitely be reconsidered

    /**
     * Registers {@code annotation} as a qualifier annotation. Only makes sense if the annotation
     * isn't meta-annotated {@code @Qualifier}.
     *
     * @param annotation annotation type
     * @param config allows transforming annotations on the {@code annotation}
     */
    void addQualifier(Class<? extends Annotation> annotation, Consumer<ClassConfig> config);

    /**
     * Registers {@code annotation} as an interceptor binding annotation. Only makes sense if the annotation
     * isn't meta-annotated {@code @InterceptorBinding}.
     *
     * @param annotation annotation type
     * @param config allows transforming annotations on the {@code annotation}
     */
    void addInterceptorBinding(Class<? extends Annotation> annotation, Consumer<ClassConfig> config);

    /**
     * Registers {@code annotation} as a stereotype annotation. Only makes sense if the annotation
     * isn't meta-annotated {@code @Stereotype}.
     *
     * @param annotation annotation type
     * @param config allows transforming annotations on the {@code annotation}
     */
    void addStereotype(Class<? extends Annotation> annotation, Consumer<ClassConfig> config);

    /**
     * Registers custom context for given {@code scopeAnnotation} and given {@code contextClass}.
     * The context class must be {@code public} and have a {@code public} zero-parameter constructor.
     * <p>
     * Whether the scope is normal is discovered from the scope annotation. This means that the scope
     * annotation must be meta-annotated either {@link jakarta.enterprise.context.NormalScope @NormalScope}
     * or {@link jakarta.inject.Scope @Scope}.
     *
     * @param scopeAnnotation the scope annotation type, must not be {@code null}
     * @param contextClass the context class, must not be {@code null}
     * @throws IllegalArgumentException if the {@code scopeAnnotation} isn't meta-annotated {@code @NormalScope}
     * or {@code @Scope}
     */
    void addContext(Class<? extends Annotation> scopeAnnotation, Class<? extends AlterableContext> contextClass);

    /**
     * Registers custom context for given {@code scopeAnnotation} and given {@code contextClass}.
     * The context class must be {@code public} and have a {@code public} zero-parameter constructor.
     * <p>
     * The {@code isNormal} parameter determines whether the scope is a normal scope or a pseudo-scope.
     *
     * @param scopeAnnotation the scope annotation type, must not be {@code null}
     * @param isNormal whether the scope is normal
     * @param contextClass the context class, must not be {@code null}
     */
    void addContext(Class<? extends Annotation> scopeAnnotation, boolean isNormal, Class<? extends AlterableContext> contextClass);
}
