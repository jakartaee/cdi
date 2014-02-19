package javax.enterprise.inject.spi;

/**
 * <p>
 * Thrown when a definition error occurs.
 * </p>
 * 
 * <p>
 * Definition errors are developer errors. They may be detected by tooling at development time, and are also detected by the
 * container at initialization time. If a definition error exists in a deployment, initialization will be aborted by the
 * container.
 * </p>
 * 
 * <p>
 * The container is permitted to define a non-portable mode, for use at development time, in which some definition errors do not
 * cause application initialization to abort.
 * </p>
 * 
 * <p>
 * An implementation is permitted to throw a subclass of {@link DefinitionException} for any definition error which exists.
 * </p>
 * 
 * @author Pete Muir
 * @since 1.1
 */
public class DefinitionException extends RuntimeException {

    private static final long serialVersionUID = -2699170549782567339L;

    public DefinitionException(String message, Throwable t) {
        super(message, t);
    }

    public DefinitionException(String message) {
        super(message);
    }

    public DefinitionException(Throwable t) {
        super(t);
    }

}
