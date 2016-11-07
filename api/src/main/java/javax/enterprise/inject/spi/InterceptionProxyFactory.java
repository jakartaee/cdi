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

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;

/**
 * <p>An {@link InterceptionProxyFactory} can provide for an instance provided by user the services available for instances
 * created by the container.
 * It makes each method invocation in the instance a business method invocation as defined in section 7.2 of the specification document
 * </p>
 *
 * <p>An implementation of {@link InterceptionProxyFactory} may be obtain by
 * calling {@link BeanManager#createInterceptionFactory(CreationalContext, Class)}
 * to be used in the create method of a custom bean for example.</p>
 *
 *
 * <pre>
 * public class MyCustomBean implements Bean&lt;MyClass&gt; {
 *
 *     BeanManager bm;
 *
 *     public MyBean(BeanManager bm) {
 *        this.bm = bm;
 *     }
 *
 *     ...
 *
 *     public MyClass create(CreationalContext&lt;MyClass&gt; creationalContext) {
 *
 *         InterceptionProxyFactory<MyClass> factory = bm.createInterceptionFactory(creationalContext, MyClass.class);
 *
 *         factory.configure()
 *         .filterMethods(m -> m.getJavaMember().getName().equals("shouldBeTransactional"))
 *         .findFirst()
 *         .ifPresent(m -> m.add(new AnnotationLiteral&lt;Transactional&gt;() { }));
 *
 *         return factory.createInterceptionProxy(new MyClass());
 *     }
 *
 *     ...
 * }
 * </pre>
 *
 * The container also provides a built-in bean for {@link InterceptionProxyFactory} that can be injected in a producer to apply
 * interceptors or decorators on the produced instance.
 *
 * <pre>
 * &#64;Produces
 * &#64;RequestScoped
 * public MyClass produceMyClass(InterceptionProxyFactory<MyClass> factory) {
 *     factory.configure().add(new AnnotationLiteral<Transactional>() {});
 *     return factory.createInterceptionProxy(new MyClass());
 * }
 *
 * </pre>
 *
 * {@link InterceptionProxyFactory} is not reusable
 *
 * @author Antoine Sabot-Durand
 * @since 2.0
 * @param <T> type for which the proxy is created
 */
public interface InterceptionProxyFactory<T> {

    /**
     *
     * Forces the instance enhancement even if the targeted class has public final methods.
     *
     * Calling this method will bypass standard rules for unproxyable bean types (section 3.11 of the spec)
     *
     * Final methods on T won't be available on the instance returned by createInterceptionProxy.
     *
     * @return self
     */
    InterceptionProxyFactory<T> ignoreFinalMethods();

    /**
     *
     * Returns an {@link AnnotatedTypeConfigurator} to allow addition of interceptor binding on the instance's methods.
     * The matching annotatedType will be used to apply the interceptors when calling createInterceptionProxy method.
     * Annotations that are not interceptor binding will be ignored.
     *
     * Each call returns the same AnnotatedTypeConfigurator.
     *
     * @return an {@link AnnotatedTypeConfigurator} to configure interceptors bindings
     */
    AnnotatedTypeConfigurator<T> configure();

    /**
     * Returns an enhanced version of the instance for which each method invocations will be a business method invocation.
     * Invocation will pass through Method interceptors and decorators defined in T class or in the {@link AnnotatedTypeConfigurator}
     * defined with the configure method.
     *
     * This method should ony be called once, subsequent calls will throw an {@link IllegalStateException}
     *
     * @param instance on which container should add its services
     * @return an enhanced instance whose method invocations will be business method invocations
     */
     T createInterceptionProxy(T instance);

}
