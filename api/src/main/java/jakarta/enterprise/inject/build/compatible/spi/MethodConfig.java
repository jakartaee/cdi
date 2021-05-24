package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.MethodInfo;

/**
 * @param <T> type of whomever declares the configured method or constructor
 */
public interface MethodConfig<T> extends MethodInfo<T>, AnnotationConfig {
    // TODO remove the type parameter?
    // TODO split MethodConfig into MethodConfig/ConstructorConfig?
    // TODO even if MethodInfo has equals/hashCode, MethodConfig probably shouldn't
}
