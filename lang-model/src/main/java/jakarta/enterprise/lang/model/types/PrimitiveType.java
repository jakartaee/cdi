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

/**
 * Primitive types are:
 *
 * <ul>
 * <li>boolean</li>
 * <li>byte</li>
 * <li>short</li>
 * <li>int</li>
 * <li>long</li>
 * <li>float</li>
 * <li>double</li>
 * <li>char</li>
 * </ul>
 *
 * @since 4.0
 */
public interface PrimitiveType extends Type {
    /**
     * The primitive type kind: boolean, byte, short, int, long, float, double, char
     */
    enum PrimitiveKind {
        /**
         * The {@code boolean} primitive type
         */
        BOOLEAN,
        /**
         * The {@code byte} primitive type
         */
        BYTE,
        /**
         * The {@code short} primitive type
         */
        SHORT,
        /**
         * The {@code int} primitive type
         */
        INT,
        /**
         * The {@code long} primitive type
         */
        LONG,
        /**
         * The {@code float} primitive type
         */
        FLOAT,
        /**
         * The {@code double} primitive type
         */
        DOUBLE,
        /**
         * The {@code char} primitive type
         */
        CHAR,
    }

    /**
     * Returns the name of this primitive type.
     *
     * @return the name of this primitive type
     */
    String name();

    /**
     * Returns the {@linkplain PrimitiveKind kind} of this primitive type.
     *
     * @return the {@linkplain PrimitiveKind kind} of this primitive type
     */
    PrimitiveKind primitiveKind();

    /**
     * Returns whether this primitive type is {@code boolean}.
     *
     * @return whether this primitive type is {@code boolean}
     */
    default boolean isBoolean() {
        return primitiveKind() == PrimitiveKind.BOOLEAN;
    }

    /**
     * Returns whether this primitive type is {@code byte}.
     *
     * @return whether this primitive type is {@code byte}
     */
    default boolean isByte() {
        return primitiveKind() == PrimitiveKind.BYTE;
    }

    /**
     * Returns whether this primitive type is {@code short}.
     *
     * @return whether this primitive type is {@code short}
     */
    default boolean isShort() {
        return primitiveKind() == PrimitiveKind.SHORT;
    }

    /**
     * Returns whether this primitive type is {@code int}.
     *
     * @return whether this primitive type is {@code int}
     */
    default boolean isInt() {
        return primitiveKind() == PrimitiveKind.INT;
    }

    /**
     * Returns whether this primitive type is {@code long}.
     *
     * @return whether this primitive type is {@code long}
     */
    default boolean isLong() {
        return primitiveKind() == PrimitiveKind.LONG;
    }

    /**
     * Returns whether this primitive type is {@code float}.
     *
     * @return whether this primitive type is {@code float}
     */
    default boolean isFloat() {
        return primitiveKind() == PrimitiveKind.FLOAT;
    }

    /**
     * Returns whether this primitive type is {@code double}.
     *
     * @return whether this primitive type is {@code double}
     */
    default boolean isDouble() {
        return primitiveKind() == PrimitiveKind.DOUBLE;
    }

    /**
     * Returns whether this primitive type is {@code char}.
     *
     * @return whether this primitive type is {@code char}
     */
    default boolean isChar() {
        return primitiveKind() == PrimitiveKind.CHAR;
    }

    // ---

    @Override
    default Kind kind() {
        return Kind.PRIMITIVE;
    }

    @Override
    default PrimitiveType asPrimitive() {
        return this;
    }
}
