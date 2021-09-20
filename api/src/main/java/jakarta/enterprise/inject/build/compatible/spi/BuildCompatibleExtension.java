package jakarta.enterprise.inject.build.compatible.spi;

/**
 * Build compatible extensions are service providers for this interface, as defined in {@link java.util.ServiceLoader}.
 * This means: they are classes that implement this interface, provide a {@code META-INF/services} file,
 * and satisfy all other service provider constraints. Additionally, extensions should not be CDI beans
 * and should not be used at application runtime.
 * <p>
 * Extensions can define arbitrary {@code public}, non-{@code static}, {@code void}-returning methods
 * without type parameters, annotated with one of the extension annotations.
 * <p>
 * Extension processing occurs in 5 phases, corresponding to 5 extension annotations:
 * <ul>
 * <li>{@link Discovery @Discovery}</li>
 * <li>{@link Enhancement @Enhancement}</li>
 * <li>{@link Processing @Processing}</li>
 * <li>{@link Synthesis @Synthesis}</li>
 * <li>{@link Validation @Validation}</li>
 * </ul>
 * <p>
 * These methods can declare arbitrary number of parameters. Which parameters can be declared depends
 * on the particular processing phase and is documented in the corresponding extension annotation.
 * All the parameters will be provided by the container when the extension is invoked.
 * <p>
 * Extension methods can be assigned a priority using {@link jakarta.annotation.Priority @Priority}.
 * Extension methods with smaller priority values are invoked first. Extension methods without specified priority
 * have a default priority of {@link jakarta.interceptor.Interceptor.Priority#APPLICATION Priority.APPLICATION} + 500.
 * If two extension methods have equal priority, the ordering among them is undefined. Note that priority
 * only affects order of extensions in a single phase.
 * <p>
 * If the extension declares multiple methods, they are all invoked on the same instance of the class.
 * <p>
 * Extension classes can be annotated {@link SkipIfPortableExtensionPresent @SkipIfPortablExtensionPresent}
 * when they are supposed to be ignored in presence of a given portable extension.
 * <p>
 * CDI implementations are not required to accept custom implementations of any {@code jakarta.enterprise.lang.model}
 * or {@code jakarta.enterprise.inject.build.compatible.spi} interface. In other words, users may only use instances
 * of these interfaces that they previously obtained from the corresponding API. If not, non-portable behavior results.
 *
 * @since 4.0
 */
public interface BuildCompatibleExtension {
    // TODO rename? "build compatible" is too long; ideally, we'd have a single word that describes
    //  the true nature of the "new" extension API (which IMHO is: there's a barrier between extension execution
    //  and application execution, there's only a very narrow way how to pass information from extension
    //  to application, and there's _no way whatsoever_ to pass anything in the other direction)
}
