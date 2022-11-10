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
 * Allows indirectly invoking a method that belongs to a managed bean (the <em>target method</em>).
 * To invoke the method, the caller must provide all the arguments that the target method accepts,
 * as well as the instance on which the target method is to be invoked, if it is not {@code static}.
 * <p>
 * Whenever a direct invocation of a method is a business method invocation, an indirect invocation
 * of that method through an invoker is also a business method invocation.
 * <p>
 * Invoker implementations must be thread-safe. It is possible to use a single invoker instance
 * to perform multiple independent invocations of the target method, possibly on different instances
 * and with different arguments.
 *
 * <h2>Obtaining an invoker</h2>
 *
 * The CDI container allows {@linkplain InvokerBuilder building} an invoker for non-private
 * methods declared on a managed bean class or inherited from a supertype. Attempting to build
 * an invoker for a private method or a constructor of a managed bean class leads to a deployment
 * problem. Attempting to build an invoker for a method of a class that is not a managed bean class
 * or that is an interceptor or decorator class leads to a deployment problem.
 * <p>
 * Multiple managed beans may inherit a method from a common supertype. In that case, each bean
 * conceptually has its own method and an invoker obtained for one bean may not be used to invoke
 * the method on the other bean.
 * <p>
 * Using the {@link InvokerBuilder} is the only way to obtain an invoker. An {@code InvokerBuilder}
 * can only be obtained in CDI portable extensions and build compatible extensions.
 *
 * <h2>Example</h2>
 *
 * To illustrate how invokers work, let's take a look at an example. Say that the following bean
 * exists and has a method that you want to invoke indirectly:
 *
 * <pre>
 * &#64;Dependent
 * class MyService {
 *     String hello(String name) {
 *         return "Hello " + name + "!";
 *     }
 * }
 * </pre>
 *
 * When you obtain an {@code InvokerBuilder} for the {@code hello()} method, you can
 * immediately build a direct invoker. In a portable extension, this results in an invoker:
 *
 * <pre>
 * InvokerBuilder&lt;Invoker&lt;MyService, String&gt;&gt; builder = ...;
 * Invoker&lt;MyService, String&gt; invoker = builder.build();
 * </pre>
 *
 * In a build compatible extension, this results in an opaque token that later
 * materializes as an invoker:
 *
 * <pre>
 * InvokerBuilder&lt;InvokerInfo&gt; builder = ...;
 * InvokerInfo invoker = builder.build();
 * </pre>
 *
 * To call the {@code hello()} method through this invoker, call
 * {@code invoker.invoke(myService, new Object[] {"world"})}.
 * The return value is {@code "Hello world!"}.
 * <p>
 * An implementation of the direct invoker above is equivalent to the following class:
 *
 * <pre>
 * class TheInvoker implements Invoker&lt;MyService, String&gt; {
 *     String invoke(MyService instance, Object[] arguments) {
 *         return instance.hello((String) arguments[0]);
 *     }
 * }
 * </pre>
 *
 * @param <T> type of the target instance
 * @param <R> return type of the method
 * @since 4.1
 * @see #invoke(Object, Object[])
 */
public interface Invoker<T, R> {
    /**
     * Invokes the target method of this invoker on given {@code instance}, passing given
     * {@code arguments}. If the target method is {@code static}, the {@code instance} is ignored;
     * by convention, it should be {@code null}. If the target method returns normally, this
     * method returns its return value, unless the target method is declared {@code void},
     * in which case this method returns {@code null}. If the target method throws an exception,
     * this method rethrows it directly.
     * <p>
     * If some parameter of the target method declares a primitive type, the corresponding element of
     * the {@code arguments} array must be of the corresponding wrapper type. No type conversions are
     * performed, so if the parameter is declared {@code int}, the argument must be an {@code Integer}
     * and may not be {@code Short} or {@code Long}. If the argument is {@code null}, the default value
     * of the primitive type is used. Note that this does not apply to arrays of primitive types;
     * if a parameter is declared {@code int[]}, the argument must be {@code int[]} and may not be
     * {@code Integer[]}.
     * <p>
     * If the target method is not {@code static} and {@code instance} is {@code null},
     * a {@link NullPointerException} is thrown. If the target method is not {@code static} and
     * the {@code instance} is not assignable to the class of the bean to which the method belongs,
     * a {@link ClassCastException} is thrown.
     * <p>
     * If the target method declares no parameter, {@code arguments} are ignored. If the target method
     * declares any parameter and {@code arguments} is {@code null}, {@link NullPointerException} is
     * thrown. If the {@code arguments} array has fewer elements than the number of parameters of
     * the target method, {@link ArrayIndexOutOfBoundsException} is thrown. If the {@code arguments}
     * array has more elements than the number of parameters of the target method, the excess elements
     * are ignored. If some of the {@code arguments} is not assignable to the declared type of
     * the corresponding parameter of the target method, {@link ClassCastException} is thrown.
     *
     * TODO the previous 2 paragraphs refer to "assignability", which needs to be defined somewhere!
     *
     * @param instance the instance on which the target method is to be invoked, may only be {@code null}
     *                 if the method is {@code static}
     * @param arguments arguments to be supplied to the target method, may only be {@code null}
     *                  if the method declares no parameter
     * @return return value of the target method, or {@code null} if the method is declared {@code void}
     */
    R invoke(T instance, Object[] arguments); // TODO throws Exception ?
}
