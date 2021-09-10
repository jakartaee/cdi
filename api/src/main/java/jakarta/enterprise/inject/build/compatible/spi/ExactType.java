package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constrains the {@link Enhancement @Enhancement} or {@link Processing @Processing} method to given types.
 * <p>
 * If the {@code @Enhancement} method has a parameter of type {@code ClassConfig} or {@code ClassInfo},
 * the method is called once for each given type.
 * If the {@code @Enhancement} method has a parameter of type {@code MethodConfig}, {@code MethodInfo},
 * {@code FieldConfig}, or {@code FieldInfo}, the method is called once for each method or field of each given type.
 * TODO also inherited methods and fields? what about constructors?
 * <p>
 * If the {@code @Processing} method has a parameter of type {@code BeanInfo},
 * the method is called once for each bean whose set of bean types contains at least one given type.
 * If the {@code @Processing} method has a parameter of type {@code ObserverInfo},
 * the method is called once for each observer whose observed event type is present in the set of given types.
 * <p>
 * If {@code annotatedWith} is set, only types that use given annotations are considered for {@code @Enhancement}.
 * The annotations can appear on the type, or on any member of the type, or any parameter of any member of the type.
 * The value of {@code annotatedWith} is ignored for {@code @Processing}.
 *
 * @since 4.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ExactType.List.class)
public @interface ExactType {
    Class<?> type();

    // default = any annotation, does that make sense?
    Class<? extends Annotation>[] annotatedWith() default Annotation.class;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ExactType[] value();
    }
}
