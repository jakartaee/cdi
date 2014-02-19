package javax.enterprise.inject.spi;

/**
 * <p>
 * Thrown when a deployment problem occurs.
 * </p>
 * 
 * <p>
 * Deployment problems are detected by the container at initialization time. If a deployment problem exists in a deployment,
 * initialization will be aborted by the container.
 * </p>
 * 
 * <p>
 * The container is permitted to define a non-portable mode, for use at development time, in which some deployment problems do
 * not cause application initialization to abort.
 * </p>
 * 
 * <p>
 * An implementation is permitted to throw a subclass of {@link DeploymentException} for any deployment problem.
 * </p>
 * 
 * @author Pete Muir
 * @since 1.1
 * 
 */
public class DeploymentException extends RuntimeException {

    private static final long serialVersionUID = 2604707587772339984L;

    public DeploymentException(String message, Throwable t) {
        super(message, t);
    }

    public DeploymentException(String message) {
        super(message);
    }

    public DeploymentException(Throwable t) {
        super(t);
    }

}
