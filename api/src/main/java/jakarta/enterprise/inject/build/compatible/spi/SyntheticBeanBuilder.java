package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.lang.annotation.Annotation;

/**
 * Builder for synthetic beans.
 * Instances are not reusable. For each synthetic bean, new instance
 * must be created by {@link SyntheticComponents#addBean(Class)}.
 *
 * @param <T> the bean class of this synthetic bean
 * @since 4.0
 */
public interface SyntheticBeanBuilder<T> {
    /**
     * Adds {@code type} to the set of bean types of this synthetic bean. This method may be called
     * multiple times to add multiple bean types.
     * <p>
     * If not called, the set of bean types of this synthetic bean will be a singleton set
     * containing {@code java.lang.Object}.
     *
     * @param type the bean type, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> type(Class<?> type);

    /**
     * Adds {@code type} to the set of bean types of this synthetic bean. This method may be called
     * multiple times to add multiple bean types.
     * <p>
     * If not called, the set of bean types of this synthetic bean will be a singleton set
     * containing {@code java.lang.Object}.
     *
     * @param type the bean type, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> type(ClassInfo type);

    /**
     * Adds {@code type} to the set of bean types of this synthetic bean. This method may be called
     * multiple times to add multiple bean types.
     * <p>
     * If not called, the set of bean types of this synthetic bean will be a singleton set
     * containing {@code java.lang.Object}.
     *
     * @param type the bean type, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> type(Type type);

    /**
     * Adds a marker annotation of given type to the set of qualifiers of this synthetic bean.
     * This method may be called multiple times to add multiple qualifiers.
     * <p>
     * If not called, this synthetic bean will have the {@code @Default} qualifier
     * (and the {@code @Any} qualifier that all beans have).
     *
     * @param annotationType the type of the marker annotation, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> qualifier(Class<? extends Annotation> annotationType);

    /**
     * Adds given annotation to the set of qualifiers of this synthetic bean.
     * This method may be called multiple times to add multiple qualifiers.
     * <p>
     * If not called, this synthetic bean will have the {@code @Default} qualifier
     * (and the {@code @Any} qualifier that all beans have).
     *
     * @param qualifierAnnotation the annotation, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> qualifier(AnnotationInfo qualifierAnnotation);

    /**
     * Adds given annotation to the set of qualifiers of this synthetic bean.
     * This method may be called multiple times to add multiple qualifiers.
     * <p>
     * If not called, this synthetic bean will have the {@code @Default} qualifier
     * (and the {@code @Any} qualifier that all beans have).
     *
     * @param qualifierAnnotation the annotation, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> qualifier(Annotation qualifierAnnotation);

    /**
     * Sets the scope of this synthetic bean to given scope type.
     * <p>
     * If not called, this synthetic bean will be {@code @Dependent}.
     *
     * @param scopeAnnotation the scope type, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> scope(Class<? extends Annotation> scopeAnnotation);

    /**
     * Marks this synthetic bean as an alternative if desired. To make this synthetic bean
     * an enabled alternative, call both {@code alternative(true)} and {@code priority(some priority)}.
     * <p>
     * If this synthetic bean is an alternative, not setting a priority means
     * that it is not enabled, which is equivalent to not registering it at all.
     * <p>
     * If not called, this synthetic bean will not be an alternative.
     *
     * @param isAlternative whether this synthetic bean should be an alternative
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> alternative(boolean isAlternative);

    /**
     * Sets a priority of this synthetic bean. To make this synthetic bean an enabled alternative,
     * call both {@code alternative(true)} and {@code priority(some priority)}.
     * <p>
     * If this synthetic bean is an alternative, not setting a priority means
     * that it is not enabled, which is equivalent to not registering it at all.
     * <p>
     * If not called, this synthetic bean will not have a priority.
     * If this synthetic bean is not an alternative, the priority is ignored.
     *
     * @param priority the priority of this synthetic bean
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> priority(int priority);

    /**
     * Sets the bean name of this synthetic bean. If {@code beanName} is {@code null},
     * this synthetic bean will not have a name.
     * <p>
     * If not called, this synthetic bean will not have a name.
     *
     * @param beanName the bean name of this synthetic bean
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> name(String beanName);

    /**
     * Adds {@code stereotypeAnnotation} to the set of stereotypes of this synthetic bean.
     * This method may be called multiple times to add multiple stereotypes.
     * <p>
     * If not called, this synthetic bean will have no stereotype.
     *
     * @param stereotypeAnnotation the stereotype, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> stereotype(Class<? extends Annotation> stereotypeAnnotation);

    /**
     * Adds {@code stereotypeAnnotation} to the set of stereotypes of this synthetic bean.
     * This method may be called multiple times to add multiple stereotypes.
     * <p>
     * If not called, this synthetic bean will have no stereotype.
     *
     * @param stereotypeAnnotation the stereotype, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> stereotype(ClassInfo stereotypeAnnotation);

    /**
     * Adds a {@code boolean}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, boolean value);

    /**
     * Adds a {@code boolean} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, boolean[] value);

    /**
     * Adds an {@code int}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, int value);

    /**
     * Adds an {@code int} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, int[] value);

    /**
     * Adds a {@code long}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, long value);

    /**
     * Adds a {@code long} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, long[] value);

    /**
     * Adds a {@code double}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, double value);

    /**
     * Adds a {@code double} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, double[] value);

    /**
     * Adds a {@code String}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, String value);

    /**
     * Adds a {@code String} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, String[] value);

    /**
     * Adds a {@code Class}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Class<?> value);
    // TODO add a variant that takes a `ClassInfo`? the value would be `Class` at runtime

    /**
     * Adds a {@code Class} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Class<?>[] value);
    // TODO add a variant that takes a `ClassInfo[]`? the value would be `Class[]` at runtime

    /**
     * Adds an {@code annotation}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     * <p>
     * When looked up from the parameter map in the creation/destruction function, the value will be
     * an instance of the annotation type, <em>not</em> an {@code AnnotationInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, AnnotationInfo value);

    /**
     * Adds an {@code annotation}-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Annotation value);

    /**
     * Adds an {@code annotation} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     * <p>
     * When looked up from the parameter map in the creation/destruction function, the values will be
     * instances of the corresponding annotation types, <em>not</em> {@code AnnotationInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, AnnotationInfo[] value);

    /**
     * Adds an {@code annotation} array-valued parameter to the map of construction/destruction parameters.
     * The parameter map is passed to the {@linkplain SyntheticBeanCreator creation} and
     * {@linkplain SyntheticBeanDisposer destruction} function when the bean is instantiated/disposed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Annotation[] value);

    /**
     * Sets the class of the synthetic bean {@linkplain SyntheticBeanCreator creation} function.
     * The class must be {@code public} and have a {@code public} zero-parameter constructor.
     * <p>
     * If not called, the synthetic bean registration will fail.
     *
     * @param creatorClass the {@linkplain SyntheticBeanCreator creation} function class, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> createWith(Class<? extends SyntheticBeanCreator<T>> creatorClass);

    /**
     * Sets the class of the synthetic bean {@linkplain SyntheticBeanDisposer destruction} function.
     * The class must be {@code public} and have a {@code public} zero-parameter constructor.
     * <p>
     * If not called, a noop destruction function will be used.
     *
     * @param disposerClass the {@linkplain SyntheticBeanDisposer destruction} function class, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> disposeWith(Class<? extends SyntheticBeanDisposer<T>> disposerClass);
}
