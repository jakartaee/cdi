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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;

/**
 * Includes logic for determining when an asynchronous action started by an invocation
 * of an asynchronous method completes. Service providers of this interface are called
 * <em>async handlers</em>. For the purpose of method invokers, a method is called
 * <em>asynchronous</em> if it matches an async handler.
 * <p>
 * An <em>async type</em> of an async handler is the erasure of the type argument
 * to the {@code AsyncHandler} direct superinterface type.
 * <p>
 * An async handler must be annotated either {@link ReturnType} or {@link ParameterType}.
 * This annotation determines how methods are matched and how the async handler is used.
 * <p>
 * The {@link #transform(Object, Runnable) transform()} method of a matching async handler
 * is called exactly once by {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}.
 * It takes an {@code original} asynchronous action and returns a transformation of it.
 * This transformation calls {@code completion.run()} exactly once, when the asynchronous
 * action completes.
 * <p>
 * For example, an async handler for {@link java.util.concurrent.CompletionStage} may look like:
 *
 * <pre>
 * &#064;ReturnType
 * public class CompletionStageAsyncHandler&lt;T&gt; implements AsyncHandler&lt;CompletionStage&lt;T&gt;&gt; {
 *     public CompletionStage&lt;T&gt; transform(CompletionStage&lt;T&gt; original, Runnable completion) {
 *         return original.whenComplete((value, error) -> {
 *             completion.run();
 *         });
 *     }
 * }
 * </pre>
 *
 * The CDI container must provide an async handler for the return types of
 * {@link java.util.concurrent.CompletionStage CompletionStage},
 * {@link java.util.concurrent.CompletableFuture CompletableFuture} and
 * {@link Flow.Publisher Flow.Publisher}.
 * These are used by passing {@code AsyncHandler.ForCompletionStage.class},
 * {@code AsyncHandler.ForCompletableFuture.class} or {@code AsyncHandler.ForFlowPublisher.class},
 * respectively, to {@link InvokerBuilder#withAsync(Class)}.
 *
 * @param <T> the type that represents the asynchronous action
 */
public interface AsyncHandler<T> {
    /**
     * Takes the {@code original} asynchronous action and returns a variant of it, transformed
     * such that {@code completion.run()} is called on completion. Note that {@code completion.run()}
     * must be called exactly once.
     * <p>
     * It is recommended that {@code completion.run()} is called <em>before</em> completion
     * is propagated to the caller.
     *
     * @param original the original asynchronous action
     * @param completion action that must be executed when the asynchronous action completes
     * @return the transformed async action
     */
    T transform(T original, Runnable completion);

    /**
     * If an async handler is annotated {@code @ReturnType}, the target method matches when
     * the erasure of its return type is identical to the async type of the async handler.
     * <p>
     * In such case, the {@code AsyncHandler.transform()} method is called after the target
     * method returns. The original return value is passed to {@code transform()} as
     * {@code original} and the return value of {@code transform()} is returned to the caller.
     * <p>
     * If an async handler is annotated {@code @ReturnType}, it must not be annotated
     * {@code @ParameterType}, otherwise deployment problem occurs.
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface ReturnType {
    }

    /**
     * If an async handler is annotated {@code @ParameterType}, the target method matches when
     * it declares exactly one parameter whose type's erasure is identical to the async type
     * of the async handler.
     * <p>
     * In such case, the {@code AsyncHandler.transform()} method is called before the target
     * method is called. The original argument value is passed to {@code transform()} as
     * {@code original} and the return value of {@code transform()} is passed as an argument
     * to the target method.
     * <p>
     * If an async handler is annotated {@code @ParameterType}, it must not be annotated
     * {@code @ReturnType}, otherwise deployment problem occurs.
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface ParameterType {
    }

    /**
     * When {@code AsyncHandler.ForCompletionStage.class} is passed to
     * {@link InvokerBuilder#withAsync(Class)}, it means the container should
     * use its built-in implementation of {@link AsyncHandler}
     * for the {@link java.util.concurrent.CompletionStage CompletionStage} return type.
     * <p>
     * This interface should not be used in any other way.
     */
    interface ForCompletionStage<T> extends AsyncHandler<CompletionStage<T>> {
    }

    /**
     * When {@code AsyncHandler.ForCompletableFuture.class} is passed to
     * {@link InvokerBuilder#withAsync(Class)}, it means the container should
     * use its built-in implementation of {@link AsyncHandler}
     * for the {@link java.util.concurrent.CompletableFuture CompletableFuture} return type.
     * <p>
     * This interface should not be used in any other way.
     */
    interface ForCompletableFuture<T> extends AsyncHandler<CompletableFuture<T>> {
    }

    /**
     * When {@code AsyncHandler.ForFlowPublisher.class} is passed to
     * {@link InvokerBuilder#withAsync(Class)}, it means the container should
     * use its built-in implementation of {@link AsyncHandler}
     * for the {@link Flow.Publisher Flow.Publisher} return type.
     * <p>
     * This interface should not be used in any other way.
     */
    interface ForFlowPublisher<T> extends AsyncHandler<Flow.Publisher<T>> {
    }
}
