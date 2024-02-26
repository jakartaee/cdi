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
 * Indicates that multiple beans match a certain combination of required type and required qualifiers and are eligible for
 * injection into a certain class.
 * </p>
 *
 * @author Pete Muir
 * @author Gavin King
 */
public class AmbiguousResolutionException extends ResolutionException {

    private static final long serialVersionUID = -2132733164534544788L;

    /**
     * default ctor
     */
    public AmbiguousResolutionException() {
    }

    /**
     * Create exception with given message and cause
     *
     * @param message - context information
     * @param throwable - cause of exception
     */
    public AmbiguousResolutionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Create exception with given message
     *
     * @param message - context information
     */
    public AmbiguousResolutionException(String message) {
        super(message);
    }

    /**
     * Create exception with given cause
     *
     * @param throwable - cause of exception
     */
    public AmbiguousResolutionException(Throwable throwable) {
        super(throwable);
    }

}
