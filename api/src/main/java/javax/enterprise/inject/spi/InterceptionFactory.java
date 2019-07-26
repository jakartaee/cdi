/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.enterprise.inject.spi;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;

/**
 * {@link InterceptionFactory} allows to create a wrapper instance whose method invocations are intercepted by method
 * interceptors and forwarded to a provided instance.
 *
 * <p>
 * An implementation of {@link InterceptionFactory} may be obtained by calling
 * {@link BeanManager#createInterceptionFactory(CreationalContext, Class)} to be used in the create method of a custom bean for
 * example.
 * </p>
 *
 * <pre>{@code
 * public class MyCustomBean implements Bean<MyClass> {
 *
 *     BeanManager bm;
 *
 *     public MyBean(BeanManager bm) {
 *        this.bm = bm;
 *     }
 *
 *     public MyClass create(CreationalContext<MyClass> creationalContext) {
 *
 *         InterceptionFactory<MyClass> factory = bm.createInterceptionFactory(creationalContext, MyClass.class);
 *
 *         factory.configure().filterMethods(m -> m.getJavaMember().getName().equals("shouldBeTransactional")).findFirst()
 *                 .ifPresent(m -> m.add(new AnnotationLiteral<Transactional>() {
 *                 }));
 *
 *         return factory.createInterceptedInstance(new MyClass());
 *     }
 * }
 * }</pre>
 *
 * <p>
 * The container must also provide a built-in bean with scope {@link Dependent}, bean type {@link InterceptionFactory} and
 * qualifier {@link Default} that can be injected in a producer method parameter.
 * </p>
 *
 * <pre>
 * {@code
 * &#64;Produces
 * &#64;RequestScoped
 * public MyClass produceMyClass(InterceptionFactory<MyClass> factory) {
 *     factory.configure().add(new AnnotationLiteral<Transactional>() {
 *     });
 *     return factory.createInterceptedInstance(new MyClass());
 * }
 * }</pre>
 *
 * <p>
 * Instances of this class are neither reusable nor suitable for sharing between threads.
 * </p>
 *
 * @author Antoine Sabot-Durand
 * @since 2.0
 * @param <T> type for which the wrapper is created
 */
public interface InterceptionFactory<T> {

    /**
     * Instructs the container to ignore all non-static, final methods with public, protected or default visibility declared by
     * any class in the type hierarchy of the intercepted instance during invocation of
     * {@link #createInterceptedInstance(Object)}.
     *
     * <p>
     * Ignored methods should never be invoked upon the wrapper instance created by
     * {@link #createInterceptedInstance(Object)}. Otherwise, unpredictable behavior results.
     * </p>
     *
     * @return self
     */
    InterceptionFactory<T> ignoreFinalMethods();

    /**
     * Returns an {@link AnnotatedTypeConfigurator} initialized with the {@link AnnotatedType} created either for the class
     * passed to {@link BeanManager#createInterceptionFactory(CreationalContext, Class)} or derived from the
     * {@link InterceptionFactory} parameter injection point.
     *
     * <p>
     * The configurator allows to add or remove interceptor bindings used to associate interceptors with the wrapper instance
     * returned by {@link #createInterceptedInstance(Object)}.
     * </p>
     *
     * <p>
     * Each call returns the same {@link AnnotatedTypeConfigurator}.
     * </p>
     *
     * @return an {@link AnnotatedTypeConfigurator} to configure interceptors bindings
     */
    AnnotatedTypeConfigurator<T> configure();

    /**
     * Returns a wrapper instance whose method invocations are intercepted by method interceptors and forwarded to a provided
     * instance.
     *
     * <p>
     * This method should only be called once, subsequent calls will throw an {@link IllegalStateException}.
     * </p>
     *
     * <p>
     * If T is not proxyable as defined in section 3.11 of the spec an
     * {@link javax.enterprise.inject.UnproxyableResolutionException} exception is thrown. Calling {@link #ignoreFinalMethods()}
     * before this method can loosen these restrictions.
     * </p>
     *
     * <p>
     * If the provided instance is an internal container construct (such as client proxy), non-portable behavior results.
     * </p>
     *
     * @param instance The provided instance
     * @return a wrapper instance
     */
    T createInterceptedInstance(T instance);

}
