package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

/**
 * Allows registering custom CDI meta-annotations: qualifiers, interceptor bindings,
 * stereotypes, and custom scopes.
 *
 * @see #addQualifier(Class, Consumer)
 * @see #addInterceptorBinding(Class, Consumer)
 * @see #addStereotype(Class, Consumer)
 * @see #addContext()
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
    void addQualifier(Class<? extends Annotation> annotation, Consumer<ClassConfig<?>> config);

    /**
     * Registers {@code annotation} as an interceptor binding annotation. Only makes sense if the annotation
     * isn't meta-annotated {@code @InterceptorBinding}.
     *
     * @param annotation annotation type
     * @param config allows transforming annotations on the {@code annotation}
     */
    void addInterceptorBinding(Class<? extends Annotation> annotation, Consumer<ClassConfig<?>> config);

    /**
     * Registers {@code annotation} as a stereotype annotation. Only makes sense if the annotation
     * isn't meta-annotated {@code @Stereotype}.
     *
     * @param annotation annotation type
     * @param config allows transforming annotations on the {@code annotation}
     */
    void addStereotype(Class<? extends Annotation> annotation, Consumer<ClassConfig<?>> config);

    /**
     * Registers custom context as configured by the returned {@link ContextConfig}.
     */
    ContextConfig addContext();
}
