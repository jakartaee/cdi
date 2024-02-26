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
 * Thrown when a definition error occurs.
 * </p>
 *
 * <p>
 * Definition errors are developer errors. They may be detected by tooling at development time, and are also detected by the
 * container at initialization time. If a definition error exists in a deployment, initialization will be aborted by the
 * container.
 * </p>
 *
 * <p>
 * The container is permitted to define a non-portable mode, for use at development time, in which some definition errors do not
 * cause application initialization to abort.
 * </p>
 *
 * <p>
 * An implementation is permitted to throw a subclass of {@link DefinitionException} for any definition error which exists.
 * </p>
 *
 * @author Pete Muir
 * @since 1.1
 */
public class DefinitionException extends RuntimeException {

    private static final long serialVersionUID = -2699170549782567339L;

    /**
     * Create exception with given message and cause
     *
     * @param message - context information
     * @param cause - cause of exception
     */
    public DefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create exception with given message
     *
     * @param message - context information
     */
    public DefinitionException(String message) {
        super(message);
    }

    /**
     * Create exception with given cause
     *
     * @param cause - cause of exception
     */
    public DefinitionException(Throwable cause) {
        super(cause);
    }

}
