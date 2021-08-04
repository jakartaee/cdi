package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.util.Collection;
import jakarta.enterprise.event.Reception;
import jakarta.enterprise.event.TransactionPhase;

/**
 * Provides read-only information about an observer.
 */
public interface ObserverInfo {
    String id(); // TODO remove entirely?

    /**
     * Returns the type of events observed by this observer.
     *
     * @return the type of events observed by this observer, never {@code null}
     */
    Type observedType();

    /**
     * Returns a collection of this observer's qualifiers, represented as {@link AnnotationInfo}.
     *
     * @return immutable collection of qualifiers, never {@code null}
     */
    // TODO method(s) for getting AnnotationInfo for given qualifier class?
    Collection<AnnotationInfo> qualifiers();

    /**
     * Returns the {@link ClassInfo class} that declares this observer.
     * In case of synthetic observers, returns the class that was designated
     * as a declaring class during synthetic observer registration.
     *
     * @return the class that declares this observer, never {@code null}
     */
    ClassInfo declaringClass();

    /**
     * Returns the observer method, if this observer corresponds to an observer method.
     * Returns {@code null} if this is a synthetic observer.
     *
     * @return the observer method, or {@code null} if this is a synthetic observer
     */
    MethodInfo observerMethod();

    /**
     * Returns the event parameter of the observer method, if this observer corresponds to an observer method.
     * Returns {@code null} if this is a synthetic observer.
     *
     * @return the event parameter of the observer method, or {@code null} if this is a synthetic observer
     */
    ParameterInfo eventParameter();

    /**
     * Returns the {@link BeanInfo bean} that declares this observer, if it corresponds to an observer method.
     * Returns {@code null} if this is a synthetic observer.
     *
     * @return the bean declaring this observer, or {@code null} if this is a synthetic observer
     */
    BeanInfo bean();

    /**
     * Returns whether this observer is synthetic. In other words, whether this observer
     * doesn't correspond to a declaration in some source code and was created
     * through other means (e.g. using an extension).
     *
     * @return whether this observer is synthetic
     */
    default boolean isSynthetic() {
        return bean() == null;
    }

    /**
     * Returns the priority of this observer. This is typically defined by adding
     * the {@link jakarta.annotation.Priority @Priority} annotation to the event parameter of the observer.
     * If the annotation is not used, the default priority, as defined by the CDI specification, is returned,
     *
     * @return the priority of this observer
     */
    int priority();

    /**
     * Returns whether this observer is asynchronous, that is, whether this observer
     * was declared using {@link jakarta.enterprise.event.ObservesAsync @ObservesAsync}.
     *
     * @return whether this observer is asynchronous
     */
    boolean isAsync();

    /**
     * Returns the {@link Reception reception type} of this observer. Allows distinguishing
     * conditional observer methods from always notified observer methods.
     *
     * @return the reception type of this observer
     */
    Reception reception();

    /**
     * Returns the {@link TransactionPhase transaction phase} of this transactional observer.
     * Returns {@link TransactionPhase#IN_PROGRESS} if this is a regular synchronous observer.
     * Returns {@code null} if this is an asynchronous observer.
     *
     * @return the transaction phase of this observer, or {@code null} if this is an asynchronous observer
     */
    TransactionPhase transactionPhase();
}
