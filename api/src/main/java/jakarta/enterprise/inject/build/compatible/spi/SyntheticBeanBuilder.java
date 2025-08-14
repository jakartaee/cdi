/*
 * Copyright (c) 2021 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

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
     * If not called, and if no stereotype is added that defines a scope,
     * this synthetic bean will be {@code @Dependent}.
     *
     * @param scopeAnnotation the scope type, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> scope(Class<? extends Annotation> scopeAnnotation);

    /**
     * Marks this synthetic bean as an {@linkplain jakarta.enterprise.inject.Alternative alternative} if desired.
     * To make this synthetic bean a selected alternative for the application, call both {@code alternative(true)}
     * and {@code priority(some priority)}.
     * <p>
     * If this synthetic bean is an alternative, not setting a priority means that it is not selected.
     * <p>
     * If not called, this synthetic bean will not be an alternative.
     *
     * @param isAlternative whether this synthetic bean should be an alternative
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     *         or if the bean was previously marked as {@linkplain #reserve(boolean) reserve}.
     */
    SyntheticBeanBuilder<T> alternative(boolean isAlternative);

    /**
     * Marks this synthetic bean as a {@linkplain jakarta.enterprise.inject.Reserve reserve} if desired.
     * To make this synthetic bean a selected reserve for the application, call both {@code reserve(true)}
     * and {@code priority(some priority)}.
     * <p>
     * If this synthetic bean is a reserve, not setting a priority means that it is not selected.
     * <p>
     * If not called, this synthetic bean will not be a reserve.
     *
     * @param isReserve whether this synthetic bean should be a reserve
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     *         or if the bean was previously marked as {@linkplain #alternative(boolean) alternative}.
     */
    SyntheticBeanBuilder<T> reserve(boolean isReserve);

    /**
     * Sets a priority of this synthetic bean. To make this synthetic bean a selected alternative/reserve
     * for the application, call both {@code alternative(true)}/{@code reserve(true)} and {@code priority(some value)}.
     * <p>
     * If this synthetic bean is an alternative or reserve, not setting a priority means that it is not selected.
     * <p>
     * If not called, this synthetic bean will not have a priority.
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
     * Adds a {@code boolean}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, boolean value);

    /**
     * Adds a {@code boolean} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, boolean[] value);

    /**
     * Adds an {@code int}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, int value);

    /**
     * Adds an {@code int} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, int[] value);

    /**
     * Adds a {@code long}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, long value);

    /**
     * Adds a {@code long} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, long[] value);

    /**
     * Adds a {@code double}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, double value);

    /**
     * Adds a {@code double} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, double[] value);

    /**
     * Adds a {@code String}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, String value);

    /**
     * Adds a {@code String} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, String[] value);

    /**
     * Adds an enum-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Enum<?> value);

    /**
     * Adds an enum array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Enum<?>[] value);

    /**
     * Adds a {@code Class}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Class<?> value);

    /**
     * Adds a {@code Class}-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     * <p>
     * When looked up from the parameter map in the creation/destruction function, the value will be
     * an instance of {@link Class}, <em>not</em> a {@code ClassInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, ClassInfo value);

    /**
     * Adds a {@code Class} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Class<?>[] value);

    /**
     * Adds a {@code Class} array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     * <p>
     * When looked up from the parameter map in the creation/destruction function, the values will be
     * instances of {@link Class}, <em>not</em> {@code ClassInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, ClassInfo[] value);

    /**
     * Adds an annotation-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
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
     * Adds an annotation-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Annotation value);

    /**
     * Adds an annotation array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
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
     * Adds an annotation array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     */
    SyntheticBeanBuilder<T> withParam(String key, Annotation[] value);

    /**
     * Adds an invoker-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     * <p>
     * When looked up from the parameter map in the creation/destruction function, the value will be
     * an instance of {@link jakarta.enterprise.invoke.Invoker Invoker}, <em>not</em> an {@code InvokerInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     * @since 4.1
     */
    SyntheticBeanBuilder<T> withParam(String key, InvokerInfo value);

    /**
     * Adds an invoker array-valued parameter to the parameter map. The parameter map is passed
     * to the {@linkplain SyntheticBeanCreator creation} and {@linkplain SyntheticBeanDisposer destruction}
     * functions when a bean instance is created/destroyed.
     * <p>
     * When looked up from the parameter map in the creation/destruction function, the values will be
     * instances of {@link jakarta.enterprise.invoke.Invoker Invoker}, <em>not</em> {@code InvokerInfo}.
     *
     * @param key the parameter key, must not be {@code null}
     * @param value the parameter value
     * @return this {@code SyntheticBeanBuilder}
     * @since 4.1
     */
    SyntheticBeanBuilder<T> withParam(String key, InvokerInfo[] value);

    /**
     * Sets the class of the synthetic bean {@linkplain SyntheticBeanCreator creation} function.
     * CDI container will create an instance of the creation function every time when it needs
     * to obtain an instance of the synthetic bean. The class must be {@code public} and have
     * a {@code public} zero-parameter constructor; it must not be a bean.
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
     * CDI container will create an instance of the destruction function every time when it needs
     * to destruction an instance of the synthetic bean. The class must be {@code public} and have
     * a {@code public} zero-parameter constructor; it must not be a bean.
     * <p>
     * If not called, a noop destruction function will be used.
     *
     * @param disposerClass the {@linkplain SyntheticBeanDisposer destruction} function class, must not be {@code null}
     * @return this {@code SyntheticBeanBuilder}
     * @throws IllegalStateException if this method is called multiple times
     */
    SyntheticBeanBuilder<T> disposeWith(Class<? extends SyntheticBeanDisposer<T>> disposerClass);
}
