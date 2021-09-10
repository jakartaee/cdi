package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.DeclarationInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.util.Collection;

/**
 * An injection point defined on some bean. Injection points may be fields
 * or method parameters.
 *
 * @since 4.0
 */
public interface InjectionPointInfo {
    /**
     * Returns the {@link Type type} of this injection point.
     *
     * @return the type of this injection point, never {@code null}
     */
    Type type();

    /**
     * Returns a collection of qualifiers declared on this injection point, represented as {@link AnnotationInfo}.
     *
     * @return collection of qualifiers, never {@code null}
     */
    // TODO method(s) for getting AnnotationInfo for given qualifier class?
    Collection<AnnotationInfo> qualifiers();

    /**
     * Returns the declaration of this injection point.
     * That is a {@link jakarta.enterprise.lang.model.declarations.FieldInfo FieldInfo} for field injection,
     * or {@link jakarta.enterprise.lang.model.declarations.ParameterInfo ParameterInfo} for:
     * <ul>
     * <li>constructor injection,</li>
     * <li>initializer method,</li>
     * <li>disposer method,</li>
     * <li>producer method,</li>
     * <li>observer method.</li>
     * </ul>
     *
     * @return the declaration of this injection point, never {@code null}
     */
    DeclarationInfo declaration();
}
