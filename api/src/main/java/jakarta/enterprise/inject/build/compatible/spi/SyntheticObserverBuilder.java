package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.event.Reception;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.lang.annotation.Annotation;

/**
 * Instances are not reusable. For each synthetic observer, new instance
 * must be created by {@link SyntheticComponents#addObserver()}.
 */
public interface SyntheticObserverBuilder {
    // TODO should add a type parameter? (see also SyntheticBeanBuilder and SyntheticComponents)

    /**
     * Class that, hypothetically, "declares" the synthetic observer.
     * If not called, the class declaring the extension which creates this synthetic observer is used.
     * <p>
     * Used to identify an observer when multiple synthetic observers are otherwise identical.
     * TODO can this have implementation consequences? e.g., must the class be added to the bean archive?
     *
     * @param declaringClass class that, hypothetically, "declares" the synthetic observer
     * @return this {@code SyntheticObserverBuilder}
     */
    // if called multiple times, last call wins
    SyntheticObserverBuilder declaringClass(Class<?> declaringClass);

    SyntheticObserverBuilder declaringClass(ClassInfo declaringClass);

    // if called multiple times, last call wins
    // TODO methods to add multiple types at once?
    SyntheticObserverBuilder type(Class<?> type);

    SyntheticObserverBuilder type(ClassInfo type);

    SyntheticObserverBuilder type(Type type);

    // can be called multiple times and is additive
    // TODO methods to add multiple qualifiers at once?
    SyntheticObserverBuilder qualifier(Class<? extends Annotation> annotationType); // for marker annotations

    SyntheticObserverBuilder qualifier(AnnotationInfo qualifierAnnotation);

    SyntheticObserverBuilder qualifier(Annotation qualifierAnnotation);

    // if called multiple times, last call wins
    SyntheticObserverBuilder priority(int priority);

    // if called multiple times, last call wins
    SyntheticObserverBuilder async(boolean isAsync);

    // if called multiple times, last call wins
    // TODO this probably doesn't make sense for synthetic observers? but Portable Extensions have it?
    SyntheticObserverBuilder reception(Reception reception);

    // if called multiple times, last call wins
    SyntheticObserverBuilder transactionPhase(TransactionPhase transactionPhase);

    // TODO params? ArC doesn't have them for observers, only for beans, do they make sense here?

    SyntheticObserverBuilder observeWith(Class<? extends SyntheticObserver<?>> syntheticObserverClass);
}
