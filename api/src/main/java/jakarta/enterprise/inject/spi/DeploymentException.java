/*
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

/**
 * <p>
 * Thrown when a deployment problem occurs.
 * </p>
 *
 * <p>
 * Deployment problems are detected by the container at initialization time. If a deployment problem exists in a deployment,
 * initialization will be aborted by the container.
 * </p>
 *
 * <p>
 * The container is permitted to define a non-portable mode, for use at development time, in which some deployment problems do
 * not cause application initialization to abort.
 * </p>
 *
 * <p>
 * An implementation is permitted to throw a subclass of {@link DeploymentException} for any deployment problem.
 * </p>
 *
 * @author Pete Muir
 * @since 1.1
 *
 */
public class DeploymentException extends RuntimeException {

    private static final long serialVersionUID = 2604707587772339984L;

    /**
     * Creates the exception with given detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public DeploymentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates the exception with given detail message.
     *
     * @param message the detail message
     */
    public DeploymentException(String message) {
        super(message);
    }

    /**
     * Creates the exception with given cause.
     *
     * @param cause the cause
     */
    public DeploymentException(Throwable cause) {
        super(cause);
    }

}
