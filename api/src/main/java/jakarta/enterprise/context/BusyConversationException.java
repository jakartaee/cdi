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

/**
 * <p>
 * Indicates that the container has rejected a request because a concurrent request is associated with the same conversation
 * context.
 * </p>
 *
 * <p>
 * The container ensures that a long-running conversation may be associated with at most one request at a time, by blocking or
 * rejecting concurrent requests. If the container rejects a request, it must associate the request with a new transient
 * conversation and throw an exception of type <code>BusyConversationException</code> from the restore view phase of the JSF
 * lifecycle.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for conversations.
 * </p>
 *
 * @see ConversationScoped
 *
 * @author Pete Muir
 * @author Gavin King
 */

public class BusyConversationException extends ContextException {

    private static final long serialVersionUID = -3599813072560026919L;

    /**
     * default ctor
     */
    public BusyConversationException() {
        super();
    }

    /**
     * Create exception with given message
     * @param message - context information
     */
    public BusyConversationException(String message) {
        super(message);
    }

    /**
     * Create exception with given cause
     * @param cause - cause of exception
     */
    public BusyConversationException(Throwable cause) {
        super(cause);
    }

    /**
     * Create exception with given message and cause
     * @param message - context information
     * @param cause - cause of exception
     */
    public BusyConversationException(String message, Throwable cause) {
        super(message, cause);
    }

}
