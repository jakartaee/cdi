package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.AnnotationTarget;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.DeclarationInfo;
import jakarta.enterprise.lang.model.declarations.PackageInfo;

/**
 * Types are:
 *
 * <ul>
 * <li>the <i>void</i> type</li>
 * <li>a <i>primitive</i> type, such as {@code int}</li>
 * <li>a <i>class</i> type, such as {@code String}</li>
 * <li>an <i>array</i> type, such as {@code int[]} or {@code String[][]}</li>
 * <li>a <i>parameterized type</i>, such as {@code List<String>}</li>
 * <li>a <i>type variable</i>, such as {@code T} when used in a class that declares a type parameter {@code T}</li>
 * <li>a <i>wildcard</i> type, such as the type argument declared in {@code List<? extends Number>}</li>
 * </ul>
 */
public interface Type extends AnnotationTarget {
    @Override
    default boolean isDeclaration() {
        return false;
    }

    @Override
    default boolean isType() {
        return true;
    }

    @Override
    default DeclarationInfo asDeclaration() {
        throw new IllegalStateException("Not a declaration");
    }

    @Override
    default Type asType() {
        return this;
    }

    enum Kind {
        /** E.g. when method returns {@code void}. */
        VOID,
        /** E.g. when method returns {@code int}. */
        PRIMITIVE,
        /** E.g. when method returns {@code String}. */
        CLASS,
        /** E.g. when method returns {@code int[]} or {@code String[][]}. */
        ARRAY,
        /** E.g. when method returns {@code List<String>}. */
        PARAMETERIZED_TYPE,
        /** E.g. when method returns {@code T} and {@code T} is a type parameter of the declaring class. */
        TYPE_VARIABLE,
        /**
         * E.g. when method returns {@code List<? extends Number>}. On the first level, we have a {@code PARAMETERIZED_TYPE},
         * but on the second level, the first (and only) type argument is a {@code WILDCARD_TYPE}.
         */
        WILDCARD_TYPE,
    }

    /**
     * Returns the {@link Kind kind} of this type.
     *
     * @return the kind of this type
     */
    Kind kind();

    /**
     * Returns whether this type is the {@link VoidType void} type.
     *
     * @return {@code true} if this is void, {@code false} otherwise
     */
    default boolean isVoid() {
        return kind() == Kind.VOID;
    }

    /**
     * Returns whether this type is a {@link PrimitiveType primitive} type.
     *
     * @return {@code true} if this is a primitive type, {@code false} otherwise
     */
    default boolean isPrimitive() {
        return kind() == Kind.PRIMITIVE;
    }

    /**
     * Returns whether this type is a {@link ClassType class} type.
     *
     * @return {@code true} if this is a class type, {@code false} otherwise
     */
    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    /**
     * Returns whether this type is an {@link ArrayType array} type.
     *
     * @return {@code true} if this is an array type, {@code false} otherwise
     */
    default boolean isArray() {
        return kind() == Kind.ARRAY;
    }

    /**
     * Returns whether this type is a {@link ParameterizedType parameterized} type.
     *
     * @return {@code true} if this is a parameterized type, {@code false} otherwise
     */
    default boolean isParameterizedType() {
        return kind() == Kind.PARAMETERIZED_TYPE;
    }

    /**
     * Returns whether this type is a {@link TypeVariable type variable}.
     * Type variables are also used to represent type parameters in declarations
     * of parameterized types.
     *
     * @return {@code true} if this is a primitive type, {@code false} otherwise
     */
    default boolean isTypeVariable() {
        return kind() == Kind.TYPE_VARIABLE;
    }

    /**
     * Returns whether this type is a {@link WildcardType wildcard} type.
     *
     * @return {@code true} if this is a wildcard type, {@code false} otherwise
     */
    default boolean isWildcardType() {
        return kind() == Kind.WILDCARD_TYPE;
    }

    /**
     * Returns this type as the {@link VoidType void} type.
     *
     * @return this void type, never {@code null}
     * @throws IllegalStateException if {@link #isVoid()} returns {@code false}
     */
    default VoidType asVoid() {
        throw new IllegalStateException("Not a void");
    }

    /**
     * Returns this type as a {@link PrimitiveType primitive} type.
     *
     * @return this primitive type, never {@code null}
     * @throws IllegalStateException if {@link #isPrimitive()} returns {@code false}
     */
    default PrimitiveType asPrimitive() {
        throw new IllegalStateException("Not a primitive");
    }

    /**
     * Returns this type as a {@link ClassType class} type.
     *
     * @return this class type, never {@code null}
     * @throws IllegalStateException if {@link #isClass()} returns {@code false}
     */
    default ClassType asClass() {
        throw new IllegalStateException("Not a class");
    }

    /**
     * Returns this type as an {@link ArrayType array} type.
     *
     * @return this array type, never {@code null}
     * @throws IllegalStateException if {@link #isArray()} returns {@code false}
     */
    default ArrayType asArray() {
        throw new IllegalStateException("Not an array");
    }

    /**
     * Returns this type as a {@link ParameterizedType parameterized} type.
     *
     * @return this parameterized type, never {@code null}
     * @throws IllegalStateException if {@link #isParameterizedType()} returns {@code false}
     */
    default ParameterizedType asParameterizedType() {
        throw new IllegalStateException("Not a parameterized type");
    }

    /**
     * Returns this type as a {@link TypeVariable type variable}.
     * Type variables are also used to represent type parameters in declarations
     * of parameterized types.
     *
     * @return this type variable, never {@code null}
     * @throws IllegalStateException if {@link #isTypeVariable()} returns {@code false}
     */
    default TypeVariable asTypeVariable() {
        throw new IllegalStateException("Not a type variable");
    }

    /**
     * Returns this type as a {@link WildcardType wildcard} type.
     *
     * @return this wildcard type, never {@code null}
     * @throws IllegalStateException if {@link #isWildcardType()} returns {@code false}
     */
    default WildcardType asWildcardType() {
        throw new IllegalStateException("Not a wildcard type");
    }
}
