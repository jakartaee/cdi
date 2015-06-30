/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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

package javax.enterprise.event;

import java.util.concurrent.Executor;

/**
 * <p>
 * This exception is used when {@link Event#fireAsync(Object)} or {@link Event#fireAsync(Object, Executor)}
 * completes exceptionally.
 * If an exception is a checked exception it will be wrapped in an {@link ObserverException}.</p>
 * <p>
 * All the exceptions that were thrown by notified observers are accessible with {@link Throwable#getSuppressed()}
 * </p>
 * 
 * @author Antoine Sabot-Durand
 * @since 2.0
 *
 */
public class FireAsyncException extends RuntimeException {


  private static final long serialVersionUID = 8866346540346425797L;

  public FireAsyncException() {
  }

  public FireAsyncException(Throwable cause) {
    super(cause);
  }

  public FireAsyncException(String message) {
    super(message);
  }

  public FireAsyncException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
