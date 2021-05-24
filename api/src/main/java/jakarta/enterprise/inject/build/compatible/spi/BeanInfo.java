package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.FieldInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.util.Collection;

/**
 * @param <T> type of the inspected bean (that is, not the declaring class, but one of the types the bean has)
 */
public interface BeanInfo<T> {
    // TODO remove the type parameter?

    ScopeInfo scope();

    Collection<Type> types();

    // TODO method(s) for getting AnnotationInfo for given qualifier class?
    Collection<AnnotationInfo> qualifiers();

    /**
     * Returns the class that declares the bean.
     * In case of a bean defined by a class, that is the bean class directly.
     * In case of a producer method or field, that is the class that declares the producer method or field.
     * TODO null for synthetic beans, or return Optional?
     *
     * @return {@link ClassInfo} for the class that declares the bean
     */
    ClassInfo<?> declaringClass();

    boolean isClassBean();

    boolean isProducerMethod();

    boolean isProducerField();

    boolean isSynthetic();

    MethodInfo<?> producerMethod(); // TODO null if not producer method, or return Optional?

    FieldInfo<?> producerField(); // TODO null if not producer field, or return Optional?

    boolean isAlternative();

    int priority();

    // EL name (from @Named), IIUC
    String getName();

    DisposerInfo disposer(); // TODO null if not producer method/field, or return Optional?

    Collection<StereotypeInfo> stereotypes();

    // TODO interceptors?

    Collection<InjectionPointInfo> injectionPoints();
}
