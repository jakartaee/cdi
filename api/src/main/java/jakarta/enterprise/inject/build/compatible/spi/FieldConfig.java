package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.FieldInfo;

/**
 * @param <T> type of whomever declares the configured field
 */
public interface FieldConfig<T> extends FieldInfo<T>, AnnotationConfig {
    // TODO remove the type parameter?
    // TODO even if FieldInfo has equals/hashCode, FieldConfig probably shouldn't
}
