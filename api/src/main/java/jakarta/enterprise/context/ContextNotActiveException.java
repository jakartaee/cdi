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

package jakarta.enterprise.context;

import jakarta.enterprise.context.spi.Context;

/**
 * <p>
 * Indicates that a context is not active.
 * </p>
 *
 * @see Context
 *
 * @author Pete Muir
 * @author Shane Bryzak
 * @author Gavin King
 */

public class ContextNotActiveException extends ContextException {

    private static final long serialVersionUID = -3599813072560026919L;

    /**
     * Default ctor
     */
    public ContextNotActiveException() {
        super();
    }

    /**
     * Create exception with given message
     * @param message - context information
     */
    public ContextNotActiveException(String message) {
        super(message);
    }

    /**
     * Create exception with given cause
     * @param cause - cause of exception
     */
    public ContextNotActiveException(Throwable cause) {
        super(cause);
    }

    /**
     * Create exception with given message and cause
     * @param message - context information
     * @param cause - cause of exception
     */
    public ContextNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }

}
