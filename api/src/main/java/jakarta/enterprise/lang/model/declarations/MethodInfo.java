package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;
import java.util.List;
import java.util.Optional;

/**
 * @param <T> type of whomever declares the inspected method or constructor
 */
public interface MethodInfo<T> extends DeclarationInfo {
    // TODO remove the type parameter?
    // TODO split MethodInfo into MethodInfo/ConstructorInfo? a lot of methods here don't make sense for constructors,
    //  plus existing APIs (Core Reflection, CDI Portable Extensions) also make this distinction

    // TODO what about constructors?
    String name();

    List<ParameterInfo> parameters();

    // TODO what about constructors?
    Type returnType();

    // TODO return Optional<Type> and only return non-empty if receiver parameter is declared,
    //  or return Type and always return a receiver type, even if not declared (and hence not annotated)?
    Optional<Type> receiverType();

    List<Type> throwsTypes();

    List<TypeVariable> typeParameters();

    boolean isStatic();

    boolean isAbstract();

    boolean isFinal();

    int modifiers();

    ClassInfo<T> declaringClass();

    // ---

    @Override
    default Kind kind() {
        return Kind.METHOD;
    }

    @Override
    default MethodInfo<?> asMethod() {
        return this;
    }
}
