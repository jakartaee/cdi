/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
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
package jakarta.enterprise.inject.spi;

import java.util.function.Supplier;

/**
 * <p>
 * The event type of the third event fired by the container after it has validated that there are no deployment problems and
 * before creating contexts or processing requests. If any observer method of the {@code AfterDeploymentValidation} event throws
 * an exception, the exception is treated as a deployment problem by the container.
 * </p>
 * <p>
 * No requests will be processed by the deployment until all observers of this event return.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for Portable Extensions.
 * </p>
 *
 * @author David Allen
 */
public interface AfterDeploymentValidation {

    /**
     * Registers a deployment problem with the container, causing the container to abort deployment after all observers have
     * been notified.
     *
     * @param t The deployment problem as a {@link java.lang.Throwable}
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void addDeploymentProblem(Throwable t);

    /**
     * Ensures that an {@linkplain jakarta.enterprise.invoke.AsyncHandler async handler} exists for the given async type.
     * If it does not exist, registers a deployment problem as if {@link #addDeploymentProblem(Throwable)} was called.
     *
     * @param asyncType the required async type, must not be {@code null}
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public default void ensureAsyncHandlerExists(Class<?> asyncType) {
        ensureAsyncHandlerExists(asyncType, () -> null);
    }

    /**
     * Ensures that an {@linkplain jakarta.enterprise.invoke.AsyncHandler async handler} exists for the given async type.
     * If it does not exist, registers a deployment problem as if {@link #addDeploymentProblem(Throwable)} was called.
     * The deployment problem includes the given {@code message} in a suitable, implementation-defined manner.
     *
     * @param asyncType the required async type, must not be {@code null}
     * @param message supplier of the error message, must not be {@code null} but may return {@code null}
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public void ensureAsyncHandlerExists(Class<?> asyncType, Supplier<String> message);

}
