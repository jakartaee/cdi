package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.DeclarationInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.util.Collection;

public interface InjectionPointInfo {
    Type type();

    // TODO method(s) for getting AnnotationInfo for given qualifier class?
    Collection<AnnotationInfo> qualifiers();

    /**
     * Returns a {@code FieldInfo} for field injection, or {@code ParameterInfo} for:
     * <ul>
     * <li>constructor injection,</li>
     * <li>initializer method,</li>
     * <li>disposer method,</li>
     * <li>producer method,</li>
     * <li>observer method.</li>
     * </ul>
     *
     * @return declaration of this injection point
     */
    DeclarationInfo declaration();
}
