/*
 * Copyright (c) 2022 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.invoke;

/**
 * An invoker allows indirect invocation of its target method on an instance of its target
 * bean.
 * <p>
 * CDI-based frameworks are expected to use invokers when they need to invoke application
 * methods. Applications are not supposed to use invokers, as they can invoke their own
 * methods directly.
 * <p>
 * For example, assume the following managed bean exists:
 *
 * <pre>
 * &#64;Dependent
 * public class MyService {
 *     public String hello(String name) {
 *         return "Hello " + name + "!";
 *     }
 * }
 * </pre>
 *
 * Further, assume that {@code invoker} is an invoker for the {@code hello()} method
 * of the {@code MyService} bean and {@code myService} is a contextual reference for the bean.
 * Then, to invoke the {@code hello()} method indirectly, a framework would call
 *
 * <pre>
 * invoker.invoke(myService, new Object[] { "world" })
 * </pre>
 *
 * The return value would be {@code "Hello world!"}.
 *
 * @param <T> type of the target bean
 * @param <R> return type of the target method
 * @since 4.1
 * @see #invoke(Object, Object[])
 * @see InvokerBuilder
 */
public interface Invoker<T, R> {
    /**
     * Invokes the target method on the given {@code instance} of the target bean, passing
     * given {@code arguments}. If the target method is {@code static}, the {@code instance}
     * is ignored and should be {@code null} by convention. If the target method returns normally,
     * this method returns its return value, unless the target method is declared {@code void},
     * in which case this method returns {@code null}. If the target method throws an exception,
     * it is rethrown directly.
     *
     * @param instance the instance of the target bean on which the target method is to be invoked;
     *        may only be {@code null} if the target method is {@code static} or if instance lookup
     *        was {@linkplain InvokerBuilder#withInstanceLookup() configured}
     * @param arguments arguments to be passed to the target method; may only be {@code null}
     *        if the target method declares no parameter; if the target method declares at least
     *        one parameter, this array must have at least as many elements as the number of parameters
     *        of the target method
     * @return return value of the target method, or {@code null} if the target method
     *         is declared {@code void}
     * @throws RuntimeException when {@code instance} or {@code arguments} are incorrect
     * @throws Exception when the target method throws an exception
     */
    R invoke(T instance, Object[] arguments) throws Exception;
}
