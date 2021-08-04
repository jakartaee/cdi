package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.AnnotationTarget;
import jakarta.enterprise.lang.model.types.Type;

/**
 * Declarations are:
 *
 * <ul>
 * <li>a <i>package</i>, as declared in {@code package-info.java}</li>
 * <li>a <i>class</i>, including interfaces, enums, and annotations</li>
 * <li>a <i>field</i></li>
 * <li>a <i>method</i>, including constructors</li>
 * <li>a <i>method parameter</i></li>
 * </ul>
 */
public interface DeclarationInfo extends AnnotationTarget {
    // TODO reevaluate the is*/as*/kind() approach (everywhere!); maybe type checks and casts are better, maybe
    //  something completely different is even better

    @Override
    default boolean isDeclaration() {
        return true;
    }

    @Override
    default boolean isType() {
        return false;
    }

    @Override
    default DeclarationInfo asDeclaration() {
        return this;
    }

    @Override
    default Type asType() {
        throw new IllegalStateException("Not a type");
    }

    enum Kind {
        /** Packages can be annotated in {@code package-info.java}. */
        PACKAGE,
        CLASS,
        METHOD,
        PARAMETER,
        FIELD,
    }

    /**
     * Returns the {@link Kind kind} of this declaration.
     *
     * @return the kind of this declaration
     */
    Kind kind();

    /**
     * Returns whether this declaration is a {@link PackageInfo package}.
     *
     * @return {@code true} if this is a package, {@code false} otherwise
     */
    default boolean isPackage() {
        return kind() == Kind.PACKAGE;
    }

    /**
     * Returns whether this declaration is a {@link ClassInfo class}.
     *
     * @return {@code true} if this is a class, {@code false} otherwise
     */
    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    /**
     * Returns whether this declaration is a {@link MethodInfo method}.
     *
     * @return {@code true} if this is a method, {@code false} otherwise
     */
    default boolean isMethod() {
        return kind() == Kind.METHOD;
    }

    /**
     * Returns whether this declaration is a {@link ParameterInfo method parameter}.
     *
     * @return {@code true} if this is a parameter, {@code false} otherwise
     */
    default boolean isParameter() {
        return kind() == Kind.PARAMETER;
    }

    /**
     * Returns whether this declaration is a {@link FieldInfo field}.
     *
     * @return {@code true} if this is a field, {@code false} otherwise
     */
    default boolean isField() {
        return kind() == Kind.FIELD;
    }

    /**
     * Returns this declaration as a {@link PackageInfo package}.
     *
     * @return this package, never {@code null}
     * @throws IllegalStateException if {@link #isPackage()} returns {@code false}
     */
    default PackageInfo asPackage() {
        throw new IllegalStateException("Not a package");
    }

    /**
     * Returns this declaration as a {@link ClassInfo class}.
     *
     * @return this class, never {@code null}
     * @throws IllegalStateException if {@link #isClass()} returns {@code false}
     */
    default ClassInfo asClass() {
        throw new IllegalStateException("Not a class");
    }

    /**
     * Returns this declaration as a {@link MethodInfo method}.
     *
     * @return this method, never {@code null}
     * @throws IllegalStateException if {@link #isMethod()} returns {@code false}
     */
    default MethodInfo asMethod() {
        throw new IllegalStateException("Not a method");
    }

    /**
     * Returns this declaration as a {@link ParameterInfo method parameter}.
     *
     * @return this parameter, never {@code null}
     * @throws IllegalStateException if {@link #isParameter()} returns {@code false}
     */
    default ParameterInfo asParameter() {
        throw new IllegalStateException("Not a parameter");
    }

    /**
     * Returns this declaration as a {@link FieldInfo field}.
     *
     * @return this field, never {@code null}
     * @throws IllegalStateException if {@link #isField()} returns {@code false}
     */
    default FieldInfo asField() {
        throw new IllegalStateException("Not a field");
    }
}
