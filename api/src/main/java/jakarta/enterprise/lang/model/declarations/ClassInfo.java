package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;

import java.util.Collection;
import java.util.List;

/**
 * @param <T> the inspected class
 */
public interface ClassInfo<T> extends DeclarationInfo {
    // TODO remove the type parameter?
    // TODO nested classes don't provide access to enclosing class, but that might be OK for our purposes?

    /**
     * Returns the binary name of this class, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getName()}.
     *
     * @return binary name of this class, never {@code null}
     */
    String name();

    /**
     * Returns the simple name of this class, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getSimpleName()}.
     *
     * @return simple name of this class, never {@code null}
     */
    String simpleName();

    PackageInfo packageInfo();

    // empty if class is not parameterized
    List<TypeVariable> typeParameters();

    // null if this class doesn't have a superclass (e.g. is Object or an interface)
    // TODO or Optional?
    Type superClass();

    // null if this class doesn't have a superclass (e.g. is Object or an interface)
    // TODO or Optional?
    ClassInfo<?> superClassDeclaration();

    // empty if the class has no superinterfaces
    List<Type> superInterfaces();

    // empty if the class has no superinterfaces
    List<ClassInfo<?>> superInterfacesDeclarations();

    // TODO better name?
    boolean isPlainClass();

    boolean isInterface();

    boolean isEnum();

    boolean isAnnotation();

    boolean isAbstract();

    boolean isFinal();

    int modifiers();

    // only constructors declared by this class, not inherited ones
    // no [static] initializers
    Collection<? extends MethodInfo<T>> constructors();

    // only methods declared by this class, not inherited ones
    // no constructors nor [static] initializers
    Collection<? extends MethodInfo<T>> methods();

    // only fields declared by this class, not inherited ones
    Collection<? extends FieldInfo<T>> fields();

    // ---

    @Override
    default Kind kind() {
        return Kind.CLASS;
    }

    @Override
    default ClassInfo<?> asClass() {
        return this;
    }
}
