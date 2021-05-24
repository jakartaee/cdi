package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.types.Type;
import java.lang.annotation.Annotation;
import java.util.function.Consumer;

// TODO remove entirely, if we remove AppArchive/AppDeployment
// TODO maybe AppArchiveConfig shouldn't extend AppArchive, and *ConfigQuery shouldn't extend *Query
public interface AppArchiveConfig extends AppArchive {
    @Override
    ClassConfigQuery classes();

    @Override
    MethodConfigQuery constructors(); // no static initializers

    @Override
    MethodConfigQuery methods(); // no constructors nor static initializers

    @Override
    FieldConfigQuery fields();

    interface ClassConfigQuery extends ClassQuery {
        @Override
        ClassConfigQuery exactly(Class<?> clazz);

        @Override
        ClassConfigQuery subtypeOf(Class<?> clazz);

        @Override
        ClassConfigQuery annotatedWith(Class<? extends Annotation> annotationType);

        void configure(Consumer<ClassConfig<?>> consumer);
    }

    interface MethodConfigQuery extends MethodQuery {
        @Override
        MethodConfigQuery declaredOn(ClassQuery classes);

        @Override
        MethodConfigQuery withReturnType(Class<?> type);

        @Override
        MethodConfigQuery withReturnType(Type type);

        @Override
        MethodConfigQuery annotatedWith(Class<? extends Annotation> annotationType);

        void configure(Consumer<MethodConfig<?>> consumer);
    }

    interface FieldConfigQuery extends FieldQuery {
        @Override
        FieldConfigQuery declaredOn(ClassQuery classes);

        @Override
        FieldConfigQuery ofType(Class<?> type);

        @Override
        FieldConfigQuery ofType(Type type);

        @Override
        FieldConfigQuery annotatedWith(Class<? extends Annotation> annotationType);

        void configure(Consumer<FieldConfig<?>> consumer);
    }
}
