package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2nd phase of {@linkplain BuildCompatibleExtension build compatible extension} execution.
 * Allows transforming annotations.
 * <p>
 * In the following text, the term <em>expected types</em> denotes the set of types defined by
 * the {@link #types() types}, {@link #withSubtypes() withSubtypes} and {@link #withAnnotations() withAnnotations}
 * members of the {@code @Enhancement} annotation. The term <em>discovered types</em> denotes
 * the subset of <em>expected types</em> that were discovered during bean discovery.
 * <p>
 * Methods annotated {@code @Enhancement} must declare exactly one parameter of one of these types:
 * <ul>
 * <li>{@link ClassConfig} or {@link jakarta.enterprise.lang.model.declarations.ClassInfo ClassInfo}</li>
 * <li>{@link MethodConfig} or {@link jakarta.enterprise.lang.model.declarations.MethodInfo MethodInfo}</li>
 * <li>{@link FieldConfig} or {@link jakarta.enterprise.lang.model.declarations.FieldInfo FieldInfo}</li>
 * </ul>
 * If an {@code @Enhancement} method has a parameter of type {@code ClassConfig} or {@code ClassInfo},
 * the method is called once for each <em>discovered type</em>.
 * <p>
 * If an {@code @Enhancement} method has a parameter of type {@code MethodConfig} or {@code MethodInfo},
 * the method is called once for each constructor or method that is declared on each <em>discovered type</em>,
 * as defined in {@link jakarta.enterprise.lang.model.declarations.ClassInfo#constructors() ClassInfo.constructors}
 * and {@link jakarta.enterprise.lang.model.declarations.ClassInfo#methods() ClassInfo.methods}.
 * <p>
 * If an {@code @Enhancement} method has a parameter of type {@code FieldConfig} or {@code FieldInfo},
 * the method is called once for each field that is declared on each <em>discovered type</em>, as defined
 * in {@link jakarta.enterprise.lang.model.declarations.ClassInfo#fields() ClassInfo.fields}.
 * <p>
 * Additionally, methods annotated {@code @Enhancement} may declare parameters of these types:
 * <ul>
 * <li>{@link Messages}</li>
 * <li>{@link Types}</li>
 * </ul>
 * <p>
 * Finally, {@link AnnotationBuilder} may be used to create instances
 * of {@link jakarta.enterprise.lang.model.AnnotationInfo AnnotationInfo}.
 *
 * @since 4.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enhancement {
    /**
     * Defines the set of <em>expected types</em>. If {@link #withSubtypes() withSubtypes}
     * is {@code true}, the set of <em>expected types</em> includes all direct and indirect
     * subtypes of these types. If {@link #withAnnotations() withAnnotations} is defined,
     * the set of <em>expected types</em> only includes types that use given annotations.
     *
     * @return the set of <em>expected types</em>
     */
    Class<?>[] types();

    /**
     * If {@code true}, the set of <em>expected types</em> includes all direct and
     * indirect subtypes of given {@link #types() types}.
     *
     * @return whether subtypes should be included in the set of <em>expected types</em>
     */
    boolean withSubtypes() default false;

    /**
     * Narrows down the set of <em>expected types</em>, defined by {@link #types() types}
     * and {@link #withSubtypes() withSubtypes}, to types that use any of given annotations.
     * The annotation can appear on the type, or on any member of the type, or on any
     * parameter of any member of the type, or as a meta-annotation on any annotation
     * that is considered by these rules.
     * <p>
     * If empty, the set of <em>expected types</em> is not narrowed down in any way.
     * If {@code java.lang.Annotation} is present, the set of <em>expected types</em>
     * is narrowed down to types that use any annotation.
     * The {@link BeanDefiningAnnotations @BeanDefiningAnnotations} marker type may
     * be used to narrow down the set of <em>expected types</em> to types that use
     * any bean defining annotation.
     * <p>
     * Defaults to the {@linkplain BeanDefiningAnnotations set of bean defining annotations}.
     *
     * @return types of annotations that must be present on the <em>expected types</em>
     */
    Class<? extends Annotation>[] withAnnotations() default BeanDefiningAnnotations.class;

    /**
     * Marker annotation type that, for the purpose of {@link Enhancement#withAnnotations()},
     * represents set of bean defining annotations after the {@link Discovery @Discovery}
     * phase is finished. That is, it includes custom normal scope annotations as well as
     * custom stereotypes.
     */
    @interface BeanDefiningAnnotations {
    }
}
