package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.lang.annotation.Annotation;
import java.util.function.Consumer;

// TODO remove entirely?
public interface AppDeployment {
    BeanQuery beans();

    ObserverQuery observers();

    /**
     * The {@code scope} methods are additive.
     * When called multiple times, they form a union of requested scope types (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.beans()
     *     .scope(Foo.class)
     *     .scope(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all beans with the {@code @Foo} scope or the {@code @Bar} scope.
     * <p>
     * The {@code type} methods are additive.
     * When called multiple times, they form a union of requested bean types (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.beans()
     *     .type(Foo.class)
     *     .type(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all beans with the {@code Foo} type or the {@code Bar} type (or both).
     * Note that bean type is not just the class which declares the bean (or return type of a producer method,
     * or type of producer field). All superclasses and superinterfaces are also included in the set of bean types.
     * <p>
     * The {@code qualifier} methods are additive.
     * When called multiple times, they form a union of requested qualifiers (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.beans()
     *     .qualifier(Foo.class)
     *     .qualifier(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all beans with the {@code @Foo} qualifier or the {@code @Bar} qualifier (or both).
     * <p>
     * The {@code declaringClass} methods are additive.
     * When called multiple times, they form a union of requested declaration classes (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.beans()
     *     .declaringClass(Foo.class)
     *     .declaringClass(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all beans declared on the {@code Foo} class or the {@code Bar} class.
     */
    interface BeanQuery {
        BeanQuery scope(Class<? extends Annotation> scopeAnnotation);

        BeanQuery scope(ClassInfo<?> scopeAnnotation);

        BeanQuery type(Class<?> beanType);

        BeanQuery type(ClassInfo<?> beanType);

        BeanQuery type(Type beanType);

        BeanQuery qualifier(Class<? extends Annotation> qualifierAnnotation);

        BeanQuery qualifier(ClassInfo<?> qualifierAnnotation);

        BeanQuery declaringClass(Class<?> declaringClass);

        BeanQuery declaringClass(ClassInfo<?> declaringClass);

        void forEach(Consumer<BeanInfo<?>> consumer);

        void ifNone(Runnable runnable);
    }

    /**
     * The {@code observedType} methods are additive.
     * When called multiple times, they form a union of requested observer types (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.observers()
     *     .observedType(Foo.class)
     *     .observedType(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all observers that observe the {@code Foo} event type or the {@code Bar} event type.
     * <p>
     * The {@code qualifier} methods are additive.
     * When called multiple times, they form a union of requested qualifiers (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.observers()
     *     .qualifier(Foo.class)
     *     .qualifier(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all observers with the {@code @Foo} qualifier or the {@code @Bar} qualifier (or both).
     * <p>
     * The {@code declaringClass} methods are additive.
     * When called multiple times, they form a union of requested declaration classes (not an intersection).
     * For example,
     * <pre>{@code
     * appDeployment.observers()
     *     .declaringClass(Foo.class)
     *     .declaringClass(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all observers declared on the {@code Foo} class or the {@code Bar} class.
     */
    interface ObserverQuery {
        ObserverQuery observedType(Class<?> observedType);

        ObserverQuery observedType(ClassInfo<?> observedType);

        ObserverQuery observedType(Type observedType);

        ObserverQuery qualifier(Class<? extends Annotation> qualifierAnnotation);

        ObserverQuery qualifier(ClassInfo<?> qualifierAnnotation);

        ObserverQuery declaringClass(Class<?> declaringClass);

        ObserverQuery declaringClass(ClassInfo<?> declaringClass);

        void forEach(Consumer<ObserverInfo<?>> consumer);

        void ifNone(Runnable runnable);
    }
}
