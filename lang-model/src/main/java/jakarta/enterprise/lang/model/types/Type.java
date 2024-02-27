/*
 * Copyright 2021, Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.AnnotationTarget;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.DeclarationInfo;

/**
 * A type is <i>used</i> in a program source code, but does not have to be <i>declared</i> anywhere.
 * <p>
 * For example, the {@code int} type exists even if it is not declared in any Java source file, while
 * the {@code java.lang.String} type is declared by the class of the same name. Array types,
 * such as {@code int[]} or {@code String[][]}, are not declared anywhere either, but their
 * element types may be. A generic class, such as {@code java.util.List}, declares a raw type
 * of the same name, but it does not declare the parameterized types, such as {@code List<String>}.
 * Parameterized types are created by applying type arguments to generic classes. For example,
 * the {@code List<String>} type is created by applying {@code String} to {@code List<T>}.
 * <p>
 * Types occur on many places. A field has a type, a method has a return type, a method parameter
 * has a type, even the {@code extends} clause in a class declaration contains a type.
 * Occurences of types may be annotated.
 * <p>
 * Types are:
 *
 * <ul>
 * <li>the {@linkplain VoidType void} pseudo-type</li>
 * <li>a {@linkplain PrimitiveType primitive} type, such as {@code int}</li>
 * <li>a {@linkplain ClassType class} type, such as {@code String}</li>
 * <li>an {@linkplain ArrayType array} type, such as {@code int[]} or {@code String[][]}</li>
 * <li>a {@linkplain ParameterizedType parameterized} type, such as {@code List<String>}</li>
 * <li>a {@linkplain TypeVariable type variable}, such as {@code T} when used in a class that declares a type parameter
 * {@code T}</li>
 * <li>a {@linkplain WildcardType wildcard} type, such as the type argument in {@code List<? extends Number>}</li>
 * </ul>
 *
 * Class types and parameterized types allow obtaining their {@linkplain ClassInfo class declarations}.
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

    /**
     * The type kind: void, primitive, class, array, parameterized type, type variable, wildcard type
     */
    enum Kind {
        /** The void pseudo-type, e.g. when method returns {@code void}. */
        VOID,
        /** A primitive type, e.g. when method returns {@code int}. */
        PRIMITIVE,
        /** A class type, e.g. when method returns {@code String}. */
        CLASS,
        /** An array type, e.g. when method returns {@code int[]} or {@code String[][]}. */
        ARRAY,
        /** A parameterized type, e.g. when method returns {@code List<String>}. */
        PARAMETERIZED_TYPE,
        /**
         * A type variable, e.g. when method returns {@code T} and {@code T} is a type parameter
         * of the declaring class.
         */
        TYPE_VARIABLE,
        /**
         * A wildcard type, e.g. when method returns {@code List<? extends Number>}. The kind of such type
         * is {@code PARAMETERIZED_TYPE}, but the first (and only) type argument is a {@code WILDCARD_TYPE}.
         */
        WILDCARD_TYPE,
    }

    /**
     * Returns the {@linkplain Kind kind} of this type.
     *
     * @return the kind of this type
     */
    Kind kind();

    /**
     * Returns whether this type is the {@linkplain VoidType void} pseudo-type.
     *
     * @return {@code true} if this is void, {@code false} otherwise
     */
    default boolean isVoid() {
        return kind() == Kind.VOID;
    }

    /**
     * Returns whether this type is a {@linkplain PrimitiveType primitive} type.
     *
     * @return {@code true} if this is a primitive type, {@code false} otherwise
     */
    default boolean isPrimitive() {
        return kind() == Kind.PRIMITIVE;
    }

    /**
     * Returns whether this type is a {@linkplain ClassType class} type.
     *
     * @return {@code true} if this is a class type, {@code false} otherwise
     */
    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    /**
     * Returns whether this type is an {@linkplain ArrayType array} type.
     *
     * @return {@code true} if this is an array type, {@code false} otherwise
     */
    default boolean isArray() {
        return kind() == Kind.ARRAY;
    }

    /**
     * Returns whether this type is a {@linkplain ParameterizedType parameterized} type.
     *
     * @return {@code true} if this is a parameterized type, {@code false} otherwise
     */
    default boolean isParameterizedType() {
        return kind() == Kind.PARAMETERIZED_TYPE;
    }

    /**
     * Returns whether this type is a {@linkplain TypeVariable type variable}.
     * Type variables are also used to represent type parameters in declarations
     * of parameterized types.
     *
     * @return {@code true} if this is a primitive type, {@code false} otherwise
     */
    default boolean isTypeVariable() {
        return kind() == Kind.TYPE_VARIABLE;
    }

    /**
     * Returns whether this type is a {@linkplain WildcardType wildcard} type.
     *
     * @return {@code true} if this is a wildcard type, {@code false} otherwise
     */
    default boolean isWildcardType() {
        return kind() == Kind.WILDCARD_TYPE;
    }

    /**
     * Returns this type as the {@linkplain VoidType void} pseudo-type.
     *
     * @return this void type, never {@code null}
     * @throws IllegalStateException if {@link #isVoid()} returns {@code false}
     */
    default VoidType asVoid() {
        throw new IllegalStateException("Not a void");
    }

    /**
     * Returns this type as a {@linkplain PrimitiveType primitive} type.
     *
     * @return this primitive type, never {@code null}
     * @throws IllegalStateException if {@link #isPrimitive()} returns {@code false}
     */
    default PrimitiveType asPrimitive() {
        throw new IllegalStateException("Not a primitive");
    }

    /**
     * Returns this type as a {@linkplain ClassType class} type.
     *
     * @return this class type, never {@code null}
     * @throws IllegalStateException if {@link #isClass()} returns {@code false}
     */
    default ClassType asClass() {
        throw new IllegalStateException("Not a class");
    }

    /**
     * Returns this type as an {@linkplain ArrayType array} type.
     *
     * @return this array type, never {@code null}
     * @throws IllegalStateException if {@link #isArray()} returns {@code false}
     */
    default ArrayType asArray() {
        throw new IllegalStateException("Not an array");
    }

    /**
     * Returns this type as a {@linkplain ParameterizedType parameterized} type.
     *
     * @return this parameterized type, never {@code null}
     * @throws IllegalStateException if {@link #isParameterizedType()} returns {@code false}
     */
    default ParameterizedType asParameterizedType() {
        throw new IllegalStateException("Not a parameterized type");
    }

    /**
     * Returns this type as a {@linkplain TypeVariable type variable}.
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
     * Returns this type as a {@linkplain WildcardType wildcard} type.
     *
     * @return this wildcard type, never {@code null}
     * @throws IllegalStateException if {@link #isWildcardType()} returns {@code false}
     */
    default WildcardType asWildcardType() {
        throw new IllegalStateException("Not a wildcard type");
    }
}
