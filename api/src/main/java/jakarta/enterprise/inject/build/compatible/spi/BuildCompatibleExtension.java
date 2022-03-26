/*
 * Copyright (c) 2021 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

/**
 * Build compatible extensions are service providers for this interface, as defined in {@link java.util.ServiceLoader}.
 * This means: they are classes that implement this interface, provide a {@code META-INF/services} file,
 * and satisfy all other service provider constraints. Additionally, build compatible extensions must not be beans
 * and must not be referred to by application code.
 * <p>
 * Build compatible extensions may define arbitrary {@code public}, non-{@code static}, {@code void}-returning methods
 * without type parameters, annotated with one of the extension annotations. Such methods are called extension methods.
 * <p>
 * Extension methods are executed in 5 phases, corresponding to 5 extension annotations:
 * <ul>
 * <li>{@link Discovery @Discovery}</li>
 * <li>{@link Enhancement @Enhancement}</li>
 * <li>{@link Registration @Registration}</li>
 * <li>{@link Synthesis @Synthesis}</li>
 * <li>{@link Validation @Validation}</li>
 * </ul>
 * <p>
 * Extension methods may declare arbitrary number of parameters. In each execution phase, different types
 * of parameters may be declared, as documented in the corresponding extension annotation. All the parameters
 * will be provided by the container when the extension method is invoked. If an extension method declares
 * a parameter of a type unsupported in the execution phase, the container treats it as a deployment problem.
 * <p>
 * Extension methods may be assigned a priority using {@link jakarta.annotation.Priority @Priority}.
 * Extension methods with smaller priority values are invoked first. Extension methods without specified priority
 * have a default priority of {@link jakarta.interceptor.Interceptor.Priority#APPLICATION Priority.APPLICATION} + 500.
 * If two extension methods have equal priority, the ordering between them is undefined. Note that priority
 * only affects order of extension methods in a single phase.
 * <p>
 * For each build compatible extension, the container creates a single instance. All extension methods
 * are invoked on the same instance.
 * <p>
 * When extension methods are invoked, a CDI container does not have to be running, so calling {@code CDI.current()}
 * from an extension method, or attempting to access a running CDI container in any other way, results in
 * non-portable behavior.
 * <p>
 * Build compatible extensions may be annotated {@link SkipIfPortableExtensionPresent @SkipIfPortableExtensionPresent}
 * when they are supposed to be ignored in presence of a given portable extension.
 * <p>
 * CDI implementations are not required to accept custom implementations of any {@code jakarta.enterprise.lang.model}
 * or {@code jakarta.enterprise.inject.build.compatible.spi} interface. In other words, users may only use instances
 * of these interfaces that they previously obtained from the corresponding API. If not, non-portable behavior results.
 * <p>
 * In build compatible extensions, implementations of {@link jakarta.enterprise.lang.model.AnnotationTarget}
 * only return annotations with the {@linkplain java.lang.annotation.RetentionPolicy#RUNTIME runtime} retention policy.
 *
 * @since 4.0
 */
public interface BuildCompatibleExtension {
}
