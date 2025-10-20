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
 * Builder of {@link Invoker}s. Allows configuring additional behaviors on top of a plain
 * method invocation.
 *
 * <h2>Lookups</h2>
 *
 * For the target bean instance ({@link #withInstanceLookup()}) and for each target method
 * parameter ({@link #withArgumentLookup(int)}), it is possible to specify that the corresponding
 * value passed to {@code Invoker.invoke()} shall be ignored and a value shall be looked up
 * from the CDI container instead.
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
 * A CDI-based framework may want to build an invoker for the {@code hello()} method that
 * automatically looks up {@code MyService} from the CDI container, instead of having to
 * obtain a contextual reference manually.
 * <p>
 * Assuming that {@code builder} is an {@code InvokerBuilder} for {@code MyService.hello()},
 * such invoker can be built:
 *
 * <pre>
 * builder.withInstanceLookup().build();
 * </pre>
 *
 * Later, to invoke the {@code hello()} method, a framework could pass {@code null} as the instance:
 *
 * <pre>
 * invoker.invoke(null, new Object[] { "world" })
 * </pre>
 *
 * The invoker would look up the instance of the target bean automatically, so the method would be
 * invoked correctly and the return value would be {@code "Hello world!"}.
 *
 * <h2>Asynchronous invocations</h2>
 *
 * By calling {@link #withAsync(Class)}, it is possible to specify that the invoker should
 * be asynchronous and what {@link AsyncHandler} should be used. An asynchronous invoker does not
 * destroy instances of {@code @Dependent} looked up beans after the method returns/throws,
 * but when the asynchronous action started by the method completes.
 *
 * @param <T> type of outcome of this builder; always represents an {@code Invoker},
 *        but does not necessarily have to be an {@code Invoker} instance directly
 * @since 4.1
 */
public interface InvokerBuilder<T> {
    /**
     * Enables lookup of the target bean instance.
     * <p>
     * When enabled, the {@code instance} passed to {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}
     * is ignored and the target instance is looked up from the CDI container instead.
     *
     * @return this builder
     */
    InvokerBuilder<T> withInstanceLookup();

    /**
     * Enables lookup of the argument on given {@code position}.
     * <p>
     * When enabled, the corresponding element of the {@code arguments} array passed
     * to {@link Invoker#invoke(Object, Object[]) Invoker.invoke()} is ignored and the argument
     * is looked up from the CDI container instead.
     *
     * @param position zero-based position of the target method parameter for which lookup should be enabled
     * @return this builder
     * @throws IllegalArgumentException if {@code position} is less than 0 or greater than
     *         or equal to the number of parameters declared by the target method
     */
    InvokerBuilder<T> withArgumentLookup(int position);

    /**
     * Sets the {@link AsyncHandler} to use when invoking the asynchronous method. when
     * {@code asyncHandler} is not {@code null}, the built invoker is asynchronous. It may
     * be {@code null} in the (unlikely) case of an asynchronous method that should
     * <em>not</em> be invoked using an asynchronous invoker.
     * <p>
     * This method must be called when the target method is asynchronous and must not be called
     * when the target method is not asynchronous.
     *
     * @param asyncHandler the {@link AsyncHandler} to use; may be {@code null}
     * @return this builder
     */
    @SuppressWarnings("rawtypes")
    InvokerBuilder<T> withAsync(Class<? extends AsyncHandler> asyncHandler);

    /**
     * Returns the built {@link Invoker} or some representation of it. Implementations are allowed
     * but not required to reuse already built invokers when possible.
     *
     * @return the built invoker
     */
    T build();
}
