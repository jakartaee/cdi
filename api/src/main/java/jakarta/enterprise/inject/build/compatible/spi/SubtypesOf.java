package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constraints the {@link Enhancement @Enhancement} method to subtypes of given types.
 * If the {@code Enhancement} method has a parameter of type {@code ClassConfig},
 * the method is called once for each subtype of each given type.
 * If the {@code @Enhancement} method has a parameter of type {@code MethodConfig} or {@code FieldConfig},
 * the method is called once for each method or field of each subtype of each given type.
 * <p>
 * If the {@code annotatedWith} attribute is set, only types that use given annotations are considered.
 * The annotations can appear on the type, or on any member of the type, or any parameter of any member of the type.
 */
// TODO should the given type itself also match? (in theory yes, as subtyping is reflexive)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubtypesOf {
    Class<?> type();

    // default = any annotation, does that make sense?
    Class<? extends Annotation>[] annotatedWith() default Annotation.class;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        SubtypesOf[] value();
    }
}
