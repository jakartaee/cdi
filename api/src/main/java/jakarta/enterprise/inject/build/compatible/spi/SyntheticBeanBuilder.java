package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.lang.annotation.Annotation;

/**
 * Instances are not reusable. For each synthetic bean, new instance
 * must be created by {@link SyntheticComponents#addBean(Class)}.
 *
 * @param <T> type of bean instances
 * @since 4.0
 */
public interface SyntheticBeanBuilder<T> {
    // TODO should have the type parameter? (see also SyntheticObserverBuilder and SyntheticComponents)

    // can be called multiple times and is additive
    // TODO methods to add multiple types at once?
    SyntheticBeanBuilder<T> type(Class<?> type);

    SyntheticBeanBuilder<T> type(ClassInfo type);

    SyntheticBeanBuilder<T> type(Type type);

    // can be called multiple times and is additive
    // TODO methods to add multiple qualifiers at once?
    SyntheticBeanBuilder<T> qualifier(Class<? extends Annotation> annotationType); // for marker annotations

    SyntheticBeanBuilder<T> qualifier(AnnotationInfo qualifierAnnotation);

    SyntheticBeanBuilder<T> qualifier(Annotation qualifierAnnotation);

    // if called multiple times, last call wins
    // if not called, defaults to @Dependent
    SyntheticBeanBuilder<T> scope(Class<? extends Annotation> scopeAnnotation);

    // if called with `true`, priority is automatically 0, unless `priority` is also called
    // if called multiple times, last call wins
    SyntheticBeanBuilder<T> alternative(boolean isAlternative);

    // if called, alternative is automatically true
    // if called multiple times, last call wins
    SyntheticBeanBuilder<T> priority(int priority);

    // EL name (equivalent to @Named), IIUC
    // if called multiple times, last call wins
    SyntheticBeanBuilder<T> name(String name);

    // can be called multiple times and is additive
    SyntheticBeanBuilder<T> stereotype(Class<? extends Annotation> stereotypeAnnotation);

    SyntheticBeanBuilder<T> stereotype(ClassInfo stereotypeAnnotation);

    // params for creation and destruction functions
    SyntheticBeanBuilder<T> withParam(String key, boolean value);

    SyntheticBeanBuilder<T> withParam(String key, boolean[] value);

    SyntheticBeanBuilder<T> withParam(String key, int value);

    SyntheticBeanBuilder<T> withParam(String key, int[] value);

    SyntheticBeanBuilder<T> withParam(String key, long value);

    SyntheticBeanBuilder<T> withParam(String key, long[] value);

    SyntheticBeanBuilder<T> withParam(String key, double value);

    SyntheticBeanBuilder<T> withParam(String key, double[] value);

    SyntheticBeanBuilder<T> withParam(String key, String value);

    SyntheticBeanBuilder<T> withParam(String key, String[] value);

    SyntheticBeanBuilder<T> withParam(String key, Class<?> value);

    SyntheticBeanBuilder<T> withParam(String key, Class<?>[] value);

    SyntheticBeanBuilder<T> createWith(Class<? extends SyntheticBeanCreator<T>> creatorClass);

    SyntheticBeanBuilder<T> disposeWith(Class<? extends SyntheticBeanDisposer<T>> disposerClass);
}
