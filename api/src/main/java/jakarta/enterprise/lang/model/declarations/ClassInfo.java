package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;

import java.util.Collection;
import java.util.List;

/**
 * A class. Four kinds of classes are distinguished:
 * <ul>
 * <li>plain classes</li>
 * <li>interfaces</li>
 * <li>enums (specialized kind of plain classes)</li>
 * <li>annotations (specialized kind of interfaces)</li>
 * </ul>
 *
 * Classes are represented as isolated units. That is, if this class is nested, it is not possible
 * to obtain the enclosing class. Similarly, it is not possible to obtain the set of classes
 * nested in this class.
 * <p>
 * At the same time, it is possible to obtain the set of {@linkplain #constructors() constructors},
 * {@linkplain #methods() methods} and {@linkplain #fields() fields} declared in this class.
 * It is also possible to obtain the {@linkplain #packageInfo() package} this class is declared in.
 *
 * @since 4.0
 */
// TODO maybe support records already?
public interface ClassInfo extends DeclarationInfo {
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

    /**
     * Returns the {@linkplain PackageInfo package} this class is part of.
     * Returns {@code null} if this class is part of an unnamed package.
     *
     * @return this class's package, or {@code null} if this class is in an unnamed package
     */
    PackageInfo packageInfo();

    /**
     * Returns a list of {@linkplain TypeVariable type parameters} declared on this class.
     * Returns an empty list if this class isn't generic and so doesn't declare type parameters.
     *
     * @return immutable list of this class's type parameters, never {@code null}
     */
    List<TypeVariable> typeParameters();

    /**
     * Returns the {@linkplain Type type} of this class's superclass. Returns {@code null} if this class
     * doesn't have a superclass; that is, if this class is {@code java.lang.Object} or an interface.
     *
     * @return the type of this class's superclass, or {@code null} if there's no superclass
     */
    Type superClass();

    /**
     * Returns the {@linkplain ClassInfo declaration} of this class's superclass. Returns {@code null} if this class
     * doesn't have a superclass; that is, if this class is {@code java.lang.Object} or an interface.
     *
     * @return the declaration of this class's superclass, or {@code null} if there's no superclass
     */
    ClassInfo superClassDeclaration();

    /**
     * Returns a list of {@linkplain Type types} of this class's direct superinterfaces.
     * Returns an empty list if this class has no direct superinterface.
     *
     * @return immutable list of types of this class's direct superinterfaces, never {@code null}
     */
    List<Type> superInterfaces();

    /**
     * Returns a list of {@linkplain ClassInfo declarations} of this class's direct superinterfaces.
     * Returns an empty list if this class has no direct superinterface.
     *
     * @return immutable list of declarations of this class's direct superinterfaces, never {@code null}
     */
    List<ClassInfo> superInterfacesDeclarations();

    /**
     * Returns whether this class is a plain class. That is, not an interface,
     * not an enum, and not an annotation.
     *
     * @return whether this class is a plain class
     */
    // TODO better name? "plain class" is my invention
    boolean isPlainClass();

    /**
     * Returns whether this class is an interface.
     *
     * @return whether this class is an interface
     */
    boolean isInterface();

    /**
     * Returns whether this class is an enum.
     *
     * @return whether this class is an enum
     */
    boolean isEnum();

    /**
     * Returns whether this class is an annotation.
     *
     * @return whether this class is an annotation
     */
    boolean isAnnotation();

    /**
     * Returns whether this class is {@code abstract}.
     *
     * @return whether this class is {@code abstract}
     */
    boolean isAbstract();

    /**
     * Returns whether this class is {@code final}.
     *
     * @return whether this class is {@code final}
     */
    boolean isFinal();

    /**
     * Returns the modifiers of this class as an {@code int}.
     * Use {@link java.lang.reflect.Modifier Modifier} to inspect the value.
     *
     * @return the modifiers of this class
     */
    int modifiers();

    /**
     * Returns a collection of {@linkplain MethodInfo constructors} declared in this class.
     * Constructors declared in direct or indirect superclasses are not included.
     *
     * @return immutable collection of constructors, never {@code null}
     */
    Collection<MethodInfo> constructors();

    /**
     * Returns a collection of {@linkplain MethodInfo methods} declared in this class and all
     * its superclasses up to and excluding {@code java.lang.Object}. This includes
     * {@code private} methods declared by superclasses. Constructors are not included.
     * <p>
     * Also, {@code default} methods declared in all direct and indirect superinterfaces
     * of this class are included. Abstract or {@code private} methods declared in interfaces
     * are not included.
     * TODO this rule about interface methods comes from current Weld implementation and needs more thinking
     *
     * @return immutable collection of methods, never {@code null}
     */
    Collection<MethodInfo> methods();

    /**
     * Returns a collection of {@link FieldInfo fields} declared in this class and all
     * its superclasses up to and excluding {@code java.lang.Object}. This includes
     * {@code private} fields declared in superclasses.
     * <p>
     * Fields declared in superinterfaces are not included.
     * TODO this rule about interface fields comes from current Weld implementation and needs more thinking
     *
     * @return immutable collection of fields, never {@code null}
     */
    Collection<FieldInfo> fields();

    // ---

    @Override
    default Kind kind() {
        return Kind.CLASS;
    }

    @Override
    default ClassInfo asClass() {
        return this;
    }
}
