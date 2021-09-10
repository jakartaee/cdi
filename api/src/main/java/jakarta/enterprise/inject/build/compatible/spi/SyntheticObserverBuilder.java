package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.event.Reception;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.lang.annotation.Annotation;

/**
 * Builder for synthetic observers.
 * Instances are not reusable. For each synthetic observer, new instance
 * must be created by {@link SyntheticComponents#addObserver(Class)}.
 *
 * @param <T> the observed event type of this synthetic observer
 * @since 4.0
 */
public interface SyntheticObserverBuilder<T> {
    /**
     * Sets the bean class that "declares" this synthetic observer.
     * <p>
     * If not called, the class declaring the extension which creates this synthetic observer is used.
     *
     * @param declaringClass bean class that "declares" this synthetic observer, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticObserverBuilder<T> declaringClass(Class<?> declaringClass);

    /**
     * Sets the bean class that "declares" this synthetic observer.
     * <p>
     * If not called, the class declaring the extension which creates this synthetic observer is used.
     *
     * @param declaringClass bean class that "declares" this synthetic observer, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticObserverBuilder<T> declaringClass(ClassInfo declaringClass);

    /**
     * Adds a marker annotation of given type to the set of qualifiers of this synthetic observer.
     * This method may be called multiple times to add multiple qualifiers.
     * <p>
     * If not called, this synthetic observer will have no qualifier.
     *
     * @param annotationType the type of the marker annotation, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticObserverBuilder<T> qualifier(Class<? extends Annotation> annotationType);

    /**
     * Adds given annotation to the set of qualifiers of this synthetic observer.
     * This method may be called multiple times to add multiple qualifiers.
     * <p>
     * If not called, this synthetic observer will have no qualifier.
     *
     * @param qualifierAnnotation the annotation, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticObserverBuilder<T> qualifier(AnnotationInfo qualifierAnnotation);

    /**
     * Adds given annotation to the set of qualifiers of this synthetic observer.
     * This method may be called multiple times to add multiple qualifiers.
     * <p>
     * If not called, this synthetic observer will have no qualifier.
     *
     * @param qualifierAnnotation the annotation, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticObserverBuilder<T> qualifier(Annotation qualifierAnnotation);

    /**
     * Sets a priority of this synthetic observer.
     * <p>
     * If not called, this synthetic observer will have a default priority
     * of {@link jakarta.interceptor.Interceptor.Priority#APPLICATION Priority.APPLICATION} + 500.
     *
     * @param priority the priority of this synthetic observer
     * @return this {@code SyntheticObserverBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticObserverBuilder<T> priority(int priority);

    /**
     * Marks this synthetic observer as asynchronous if desired.
     * <p>
     * If not called, this synthetic observer will not be asynchronous.
     *
     * @param isAsync whether this synthetic observer should be asynchronous
     * @return this {@code SyntheticObserverBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticObserverBuilder<T> async(boolean isAsync);

    /**
     * Sets the {@link TransactionPhase} during which this synthetic observer should be notified.
     * If anything else than {@link TransactionPhase#IN_PROGRESS} is passed, this synthetic observer
     * will be a transactional observer.
     * <p>
     * If not called, this synthetic observer will not be a transactional observer.
     * In other words, the default is {@link TransactionPhase#IN_PROGRESS}.
     * <p>
     * Note that transactional observers can't be asynchronous. If this synthetic observer
     * is configured to be both transactional and asynchronous, its registration will fail.
     *
     * @param transactionPhase the {@link Reception} mode, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticObserverBuilder<T> transactionPhase(TransactionPhase transactionPhase);

    /**
     * Adds a {@code boolean}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, boolean value);

    /**
     * Adds a {@code boolean} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, boolean[] value);

    /**
     * Adds an {@code int}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, int value);

    /**
     * Adds an {@code int} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, int[] value);

    /**
     * Adds a {@code long}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, long value);

    /**
     * Adds a {@code long} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, long[] value);

    /**
     * Adds a {@code double}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, double value);

    /**
     * Adds a {@code double} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, double[] value);

    /**
     * Adds a {@code String}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, String value);

    /**
     * Adds a {@code String} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, String[] value);

    /**
     * Adds a {@code Class}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Class<?> value);
    // TODO add a variant that takes a `ClassInfo`? the value would be `Class` at runtime

    /**
     * Adds a {@code Class} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Class<?>[] value);
    // TODO add a variant that takes a `ClassInfo[]`? the value would be `Class[]` at runtime

    /**
     * Adds an {@code annotation}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     * <p>
     * When looked up from the parameter map in the event notification function, the value will be
     * an instance of the annotation type, <em>not</em> an {@code AnnotationInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, AnnotationInfo value);

    /**
     * Adds an {@code annotation}-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Annotation value);

    /**
     * Adds an {@code annotation} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     * <p>
     * When looked up from the parameter map in the event notification function, the values will be
     * instances of the corresponding annotation types, <em>not</em> {@code AnnotationInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, AnnotationInfo[] value);

    /**
     * Adds an {@code annotation} array-valued parameter to the map of event notification parameters.
     * The parameter map is passed to the {@linkplain SyntheticObserver event notification} function
     * when the event is fired.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticObserverBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Annotation[] value);

    /**
     * Sets the class of the synthetic observer {@linkplain SyntheticObserver event notification} function.
     * The class must be {@code public} and have a {@code public} zero-parameter constructor.
     * <p>
     * If not called, the synthetic observer registration will fail.
     *
     * @param observerClass the {@linkplain SyntheticObserver event notification} function class, must not be {@code null}
     * @return this {@code SyntheticObserverBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticObserverBuilder<T> observeWith(Class<? extends SyntheticObserver<T>> observerClass);
}
