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
 * Indicates a problem relating to typesafe resolution.
 *
 * @author Gavin King
 */
public class ResolutionException extends InjectionException {

    private static final long serialVersionUID = -6280627846071966243L;

    /**
     * Creates the exception with no detail message or cause.
     */
    public ResolutionException() {
        super();
    }

    /**
     * Creates the exception with given detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates the exception with given detail message.
     *
     * @param message the detail message
     */
    public ResolutionException(String message) {
        super(message);
    }

    /**
     * Creates the exception with given cause.
     *
     * @param cause the cause
     */
    public ResolutionException(Throwable cause) {
        super(cause);
    }

}
