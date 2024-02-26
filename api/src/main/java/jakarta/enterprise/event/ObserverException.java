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

package jakarta.enterprise.event;

/**
 * <p>
 * Indicates that a checked exception was thrown by an observer method during event notification.
 * </p>
 *
 * @author Pete Muir
 * @author Gavin King
 */
public class ObserverException extends RuntimeException {

    private static final long serialVersionUID = -801836224808304381L;

    /**
     * Default ctor
     */
    public ObserverException() {

    }

    /**
     * Create exception with given message
     * @param message - context information
     */
    public ObserverException(String message) {
        super(message);
    }

    /**
     * Create exception with given cause
     * @param cause - cause of exception
     */
    public ObserverException(Throwable cause) {
        super(cause);
    }

    /**
     * Create exception with given message and cause
     * @param message - context information
     * @param cause - cause of exception
     */
    public ObserverException(String message, Throwable cause) {
        super(message, cause);
    }

}
