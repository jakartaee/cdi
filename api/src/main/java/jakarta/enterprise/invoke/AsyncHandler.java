/*
 * Copyright 2025, Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.invoke;

/**
 * Determines when an asynchronous action started by an invocation of an asynchronous method
 * completes. For the purpose of method invokers, a method is called <em>asynchronous</em> if it matches
 * exactly one async handler. An <em>async handler</em> is either a {@linkplain ReturnType return type async handler}
 * or a {@linkplain ParameterType parameter type async handler}. For an asynchronous method, the matching async handler
 * is called during {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}.
 * <p>
 * Async handlers must be stateless. No guarantees about their lifecycle are made.
 * <p>
 * The CDI container provides built-in return type async handlers for
 * {@link java.util.concurrent.CompletionStage CompletionStage},
 * {@link java.util.concurrent.CompletableFuture CompletableFuture}
 * and {@link java.util.concurrent.Flow.Publisher Flow.Publisher}.
 *
 */
public interface AsyncHandler {
    /**
     * A <em>return type async handler</em> is a service provider for this interface declared
     * in {@code META-INF/services}. The <em>async type</em> of a return type async handler is the erasure
     * of the type argument to the {@code AsyncHandler.ReturnType} direct superinterface type.
     * <p>
     * A target method matches a return type async handler when the erasure of the method's return type
     * is identical to the async type of the async handler.
     *
     * @see #transform(Object, Runnable)
     * @param <T> the async type of the handler
     */
    interface ReturnType<T> {
        /**
         * Called once by {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}, after the target method
         * returns. When the target method throws an exception synchronously, this method is not called.
         * <p>
         * Takes the {@code original} return value and returns a variant of it, transformed such that
         * {@code completion.run()} is called on completion. Note that {@code completion.run()} must be called
         * exactly once. The result is returned to the invoker caller instead of the {@code original}.
         * <p>
         * It is recommended that {@code completion.run()} is called <em>before</em> completion is propagated
         * to the caller.
         *
         * @param original the original asynchronous return value
         * @param completion action that must be executed when the asynchronous action completes
         * @return the transformed asynchronous return value
         */
        T transform(T original, Runnable completion);
    }

    /**
     * A <em>parameter type async handler</em> is a service provider for this interface declared
     * in {@code META-INF/services}. An <em>async type</em> of a parameter type async handler is the erasure
     * of the type argument to the {@code AsyncHandler.ParameterType} direct superinterface type.
     * <p>
     * A target method matches a parameter type async handler when it declares exactly one parameter
     * whose type's erasure is identical to the async type of the async handler. This parameter is called
     * the <em>async parameter</em>.
     *
     * @see #transformArgument(Object, Runnable)
     * @see #transformReturnValue(Object, Runnable)
     * @param <T> the async type of the handler
     */
    interface ParameterType<T> {
        /**
         * Called once by {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}, before the target method
         * is invoked.
         * <p>
         * Takes the {@code original} argument value for the async parameter and returns a variant of it,
         * transformed such that {@code completion.run()} is called on completion. The result is passed to the
         * target method instead of the {@code original}.
         * <p>
         * The net effect of this method and {@link #transformReturnValue(Object, Runnable)} must be that
         * {@code completion.run()} is called exactly once, after the asynchronous action completes. It is
         * recommended that {@code completion.run()} is called <em>before</em> completion is propagated to the
         * caller.
         *
         * @param original the original argument value for the async parameter
         * @param completion action that must be executed when the asynchronous action completes
         * @return the transformed argument value for the async parameter
         */
        T transformArgument(T original, Runnable completion);

        /**
         * Called once by {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}, after the target method
         * returns. When the target method throws an exception synchronously, this method is not called.
         * <p>
         * Takes the {@code original} return value and returns a variant of it, performing any transformation
         * necessary such that that {@code completion.run()} is called on completion. The result is returned
         * to the invoker caller instead of the {@code original}.
         * <p>
         * The net effect of this method and {@link #transformArgument(Object, Runnable)} must be that
         * {@code completion.run()} is called exactly once, after the asynchronous action completes. It is
         * recommended that {@code completion.run()} is called <em>before</em> completion is propagated to the
         * caller.
         * <p>
         * The default implementation returns {@code original} directly.
         * <p>
         * Note that vast majority of parameter type async handlers do <em>not</em> need to implement
         * this method, because completion is usually signaled solely through the async parameter. This method
         * only needs to be implemented if the return value may also be used to signal completion.
         * As of this writing, the only known situation when this occurs is Kotlin {@code suspend} functions,
         * where synchronous completion is signaled through the return value and asynchronous completion
         * is signaled through the {@code Continuation} parameter.
         *
         * @param original the original return value
         * @param completion action that must be executed when the asynchronous action completes
         * @return the transformed return value
         */
        default Object transformReturnValue(Object original, Runnable completion) {
            return original;
        }
    }
}
