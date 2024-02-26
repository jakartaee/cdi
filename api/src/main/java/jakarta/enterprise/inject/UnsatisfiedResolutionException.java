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

/**
 * <p>
 * Indicates that no bean matches a certain combination of required type and required qualifiers and is eligible for injection
 * into a certain class.
 * </p>
 *
 * @author Pete Muir
 * @author Gavin King
 */
public class UnsatisfiedResolutionException extends ResolutionException {

    private static final long serialVersionUID = 5350603312442756709L;

    /**
     * Default ctor
     */
    public UnsatisfiedResolutionException() {
        super();
    }

    /**
     * Create exception with given message and cause
     *
     * @param message - context information
     * @param cause - cause of exception
     */
    public UnsatisfiedResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create exception with given message
     *
     * @param message - context information
     */
    public UnsatisfiedResolutionException(String message) {
        super(message);
    }

    /**
     * Create exception with given cause
     *
     * @param cause - cause of exception
     */
    public UnsatisfiedResolutionException(Throwable cause) {
        super(cause);
    }

}
