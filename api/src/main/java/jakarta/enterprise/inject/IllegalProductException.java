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
package jakarta.enterprise.inject;

import jakarta.enterprise.context.Dependent;

/**
 * <p>
 * Indicates that a producer method returned a null value or a producer field contained a null value, and the scope of the
 * producer method or field was not {@link Dependent}.
 * </p>
 */
public class IllegalProductException extends InjectionException {

    private static final long serialVersionUID = -6280627846071966243L;

    /**
     * Creates the exception with no detail message or cause.
     */
    public IllegalProductException() {
        super();
    }

    /**
     * Creates the exception with given detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public IllegalProductException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates the exception with given detail message.
     *
     * @param message the detail message
     */
    public IllegalProductException(String message) {
        super(message);
    }

    /**
     * Creates the exception with given cause.
     *
     * @param cause the cause
     */
    public IllegalProductException(Throwable cause) {
        super(cause);
    }

}
