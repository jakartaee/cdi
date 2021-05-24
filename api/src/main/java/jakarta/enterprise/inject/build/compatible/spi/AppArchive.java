package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.FieldInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.lang.annotation.Annotation;
import java.util.function.Consumer;

// TODO remove entirely?
public interface AppArchive {
    ClassQuery classes();

    MethodQuery constructors(); // no static initializers

    MethodQuery methods(); // no constructors nor static initializers

    FieldQuery fields();

    /**
     * The {@code exactly} and {@code subtypeOf} methods are additive.
     * When called multiple times, they form a union of requested classes (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.classes()
     *     .exactly(Foo.class)
     *     .subtypeOf(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for the {@code Foo} class and all subtypes of the {@code Bar} class.
     * <p>
     * The {@code annotatedWith} methods are additive.
     * When called multiple times, they form a union of requested annotations (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.classes()
     *     .annotatedWith(Foo.class)
     *     .annotatedWith(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all classes annotated either with {@code @Foo} or with {@code @Bar} (or both).
     */
    interface ClassQuery {
        ClassQuery exactly(Class<?> clazz);

        ClassQuery subtypeOf(Class<?> clazz);

        ClassQuery annotatedWith(Class<? extends Annotation> annotationType);

        void forEach(Consumer<ClassInfo<?>> consumer);
    }

    /**
     * The {@code declaredOn} method is additive.
     * When called multiple times, it forms a union of requested classes (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.methods()
     *     .declaredOn(appArchive.classes().exactly(Foo.class))
     *     .declaredOn(appArchive.classes().subtypeOf(Bar.class))
     *     .forEach(...)
     * }</pre>
     * runs given code for all methods declared on the {@code Foo} class and on all subtypes of the {@code Bar} class.
     * Note that this example can be rewritten as
     * <pre>{@code
     * appArchive.methods()
     *     .declaredOn(appArchive.classes().exactly(Foo.class).subtypeOf(Bar.class))
     *     .forEach(...)
     * }</pre>
     * which is probably easier to understand.
     * <p>
     * The {@code withReturnType} methods are additive.
     * When called multiple times, they form a union of requested return types (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.methods()
     *     .withReturnType(Foo.class)
     *     .withReturnType(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all methods that return either {@code Foo} or {@code Bar}.
     * <p>
     * The {@code annotatedWith} methods are additive.
     * When called multiple times, they form a union of requested annotations (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.methods()
     *     .annotatedWith(Foo.class)
     *     .annotatedWith(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all methods annotated either with {@code @Foo} or with {@code @Bar} (or both).
     */
    interface MethodQuery {
        MethodQuery declaredOn(ClassQuery classes);

        // equivalent to `withReturnType(Types.of(type))`
        MethodQuery withReturnType(Class<?> type);

        MethodQuery withReturnType(Type type);

        // TODO parameters?

        MethodQuery annotatedWith(Class<? extends Annotation> annotationType);

        void forEach(Consumer<MethodInfo<?>> consumer);
    }

    /**
     * The {@code declaredOn} method is additive.
     * When called multiple times, it forms a union of requested classes (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.fields()
     *     .declaredOn(appArchive.classes().exactly(Foo.class))
     *     .declaredOn(appArchive.classes().subtypeOf(Bar.class))
     *     .forEach(...)
     * }</pre>
     * runs given code for all fields declared on the {@code Foo} class and on all subtypes of the {@code Bar} class.
     * Note that this example can be rewritten as
     * <pre>{@code
     * appArchive.fields()
     *     .declaredOn(appArchive.classes().exactly(Foo.class).subtypeOf(Bar.class))
     *     .forEach()
     * }</pre>
     * which is probably easier to understand.
     * <p>
     * The {@code ofType} methods are additive.
     * When called multiple times, they form a union of requested field types (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.fields()
     *     .ofType(Foo.class)
     *     .ofType(Bar.class)
     *     .forEach()
     * }</pre>
     * runs given code for all fields that are of type either {@code Foo} or {@code Bar}.
     * <p>
     * The {@code annotatedWith} methods are additive.
     * When called multiple times, they form a union of requested annotations (not an intersection).
     * For example,
     * <pre>{@code
     * appArchive.fields()
     *     .annotatedWith(Foo.class)
     *     .annotatedWith(Bar.class)
     *     .forEach(...)
     * }</pre>
     * runs given code for all fields annotated either with {@code @Foo} or with {@code @Bar} (or both).
     */
    interface FieldQuery {
        FieldQuery declaredOn(ClassQuery classes);

        // equivalent to `withReturnType(Types.of(type))`
        FieldQuery ofType(Class<?> type);

        FieldQuery ofType(Type type);

        FieldQuery annotatedWith(Class<? extends Annotation> annotationType);

        void forEach(Consumer<FieldInfo<?>> consumer);
    }
}
