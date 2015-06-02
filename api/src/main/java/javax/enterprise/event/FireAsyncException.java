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
