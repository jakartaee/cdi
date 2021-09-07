package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationTarget;

/**
 * Allows logging and producing errors during {@link BuildCompatibleExtension} execution.
 * If an error is produced, application deployment will fail.
 *
 * @since 4.0
 */
public interface Messages {
    /**
     * Add a generic information message that is not related to any particular element, or that information is not known.
     *
     * @param message information message
     */
    void info(String message);

    /**
     * Add an information message which is related to given {@link AnnotationTarget} (which is most likely
     * a {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo DeclarationInfo}).
     *
     * @param message information message
     * @param relatedTo annotation target to which the message is related
     */
    void info(String message, AnnotationTarget relatedTo);

    /**
     * Add an information message which is related to given {@link BeanInfo}.
     *
     * @param message information message
     * @param relatedTo bean to which the message is related
     */
    void info(String message, BeanInfo relatedTo);

    /**
     * Add an information message which is related to given {@link ObserverInfo}.
     *
     * @param message information message
     * @param relatedTo observer to which the message is related
     */
    void info(String message, ObserverInfo relatedTo);

    /**
     * Add a generic warning that is not related to any particular element, or that information is not known.
     *
     * @param message warning message
     */
    void warn(String message);

    /**
     * Add a warning which is related to given {@link AnnotationTarget} (which is most likely
     * a {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo DeclarationInfo}).
     *
     * @param message warning message
     * @param relatedTo annotation target to which the message is related
     */
    void warn(String message, AnnotationTarget relatedTo);

    /**
     * Add a warning which is related to given {@link BeanInfo}.
     *
     * @param message warning message
     * @param relatedTo bean to which the message is related
     */
    void warn(String message, BeanInfo relatedTo);

    /**
     * Add a warning which is related to given {@link ObserverInfo}.
     *
     * @param message warning message
     * @param relatedTo observer to which the message is related
     */
    void warn(String message, ObserverInfo relatedTo);

    /**
     * Add a generic error that is not related to any particular element, or that information is not known.
     *
     * @param message error message
     */
    void error(String message);

    /**
     * Add an error which is related to given {@link AnnotationTarget} (which is most likely
     * a {@link jakarta.enterprise.lang.model.declarations.DeclarationInfo DeclarationInfo}).
     *
     * @param message error message
     * @param relatedTo annotation target to which the message is related
     */
    void error(String message, AnnotationTarget relatedTo);

    /**
     * Add an error which is related to given {@link BeanInfo}.
     *
     * @param message error message
     * @param relatedTo bean to which the message is related
     */
    void error(String message, BeanInfo relatedTo);

    /**
     * Add an error which is related to given {@link ObserverInfo}.
     *
     * @param message error message
     * @param relatedTo observer to which the message is related
     */
    void error(String message, ObserverInfo relatedTo);

    /**
     * Add a generic error that is represented by an exception.
     *
     * @param exception error, represented by an exception
     */
    void error(Exception exception);
}
