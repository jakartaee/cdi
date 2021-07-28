package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.util.List;

/**
 * Models the value of an {@link jakarta.enterprise.lang.model.AnnotationMember}.
 */
public interface AnnotationMemberValue {
    /**
     * The kind of the member.
     */
    enum Kind {
        /**
         * A primitive {@link java.lang.Boolean}.
         */
        BOOLEAN,
        /**
         * A primitive {@link java.lang.Byte}.
         */
        BYTE,
        /**
         * A primitive {@link java.lang.Short}.
         */
        SHORT,
        /**
         * A primitive {@link java.lang.Integer}.
         */
        INT,
        /**
         * A primitive {@link java.lang.Long}.
         */
        LONG,
        /**
         * A primitive {@link java.lang.Float}.
         */
        FLOAT,
        /**
         * A primitive {@link java.lang.Double}.
         */
        DOUBLE,
        /**
         * A primitive {@link java.lang.Character}.
         */
        CHAR,
        /**
         * A {@link java.lang.String} value.
         */
        STRING,
        /**
         * An {@link java.lang.Enum} value.
         */
        ENUM,
        /**
         * A {@link java.lang.Class} value modelled as {@link jakarta.enterprise.lang.model.types.Type}.
         */
        CLASS,
        /**
         * An array value.
         */
        ARRAY,
        /**
         * A nested annotation definition.
         */
        NESTED_ANNOTATION,
    }

    /**
     * @return The kind of the annotation member. Never {@code null}.
     */
    Kind kind();

    /**
     * @return Returns {@code true} if the kind is a {@code boolean}, {@code false} otherwise.
     */
    default boolean isBoolean() {
        return kind() == Kind.BOOLEAN;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code byte}, {@code false} otherwise.
     */
    default boolean isByte() {
        return kind() == Kind.BYTE;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code short}, {@code false} otherwise.
     */
    default boolean isShort() {
        return kind() == Kind.SHORT;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code int}, {@code false} otherwise.
     */
    default boolean isInt() {
        return kind() == Kind.INT;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code long}, {@code false} otherwise.
     */
    default boolean isLong() {
        return kind() == Kind.LONG;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code float}, {@code false} otherwise.
     */
    default boolean isFloat() {
        return kind() == Kind.FLOAT;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code double}, {@code false} otherwise.
     */
    default boolean isDouble() {
        return kind() == Kind.DOUBLE;
    }

    /**
     * @return Returns {@code true} if the kind is a {@code char}, {@code false} otherwise.
     */
    default boolean isChar() {
        return kind() == Kind.CHAR;
    }

    /**
     * @return Returns {@code true} if the kind is a {@link String}, {@code false} otherwise.
     */
    default boolean isString() {
        return kind() == Kind.STRING;
    }

    /**
     * @return Returns {@code true} if the kind is a {@link Enum}, {@code false} otherwise.
     */
    default boolean isEnum() {
        return kind() == Kind.ENUM;
    }

    /**
     * @return Returns {@code true} if the kind is a {@link Class}, {@code false} otherwise.
     */
    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    /**
     * @return Returns {@code true} if the kind is an array, {@code false} otherwise.
     */
    default boolean isArray() {
        return kind() == Kind.ARRAY;
    }

    /**
     * @return Returns {@code true} if the kind is an {@link java.lang.annotation.Annotation}, {@code false} otherwise.
     */
    default boolean isNestedAnnotation() {
        return kind() == Kind.NESTED_ANNOTATION;
    }

    /**
     * Return the value as a boolean.
     * @return The boolean value
     */
    boolean asBoolean();

    /**
     * Return the value as a byte.
     * @return The byte value
     * @throws NumberFormatException if the value cannot be represented as a byte.
     */
    byte asByte();

    /**
     * Return the value as a short.
     * @return The short value
     * @throws NumberFormatException if the value cannot be represented as a short.
     */
    short asShort();

    /**
     * Return the value as an int.
     * @return The int value
     * @throws java.lang.IllegalStateException if the value cannot be represented as an int.
     */
    int asInt();

    /**
     * Return the value as a long.
     * @return The long value
     * @throws java.lang.IllegalStateException if the value cannot be represented as a long.
     */
    long asLong();

    /**
     * Return the value as a float.
     * @return The float value
     * @throws java.lang.IllegalStateException if the value cannot be represented as a float.
     */
    float asFloat();

    /**
     * Return the value as a double.
     * @return The double value
     * @throws java.lang.IllegalStateException if the value cannot be represented as a double.
     */
    double asDouble();

    /**
     * Return the value as a char.
     * @return The char value
     * @throws java.lang.IllegalStateException if the value cannot be represented as a double.
     */
    char asChar();

    /**
     * Return the value as a string.
     * @return A string representing the value. Never {@code null}.
     */
    String asString();

    /**
     * Return the value to an enum instance.
     * @param enumType The enum type
     * @param <E> The enum generic type
     * @return The enum instance
     * @throws java.lang.IllegalStateException if the enum value cannot be established from the given argument
     */
    <E extends Enum<E>> E asEnum(Class<E> enumType);

    /**
     * Return the enum type that an enum value represents.
     *
     * @return A {@link jakarta.enterprise.lang.model.declarations.ClassInfo} representing the type of an enum value.
     */
    ClassInfo<?> asEnumClass();

    /**
     * Return the type of the value. Valid types include {@link jakarta.enterprise.lang.model.types.PrimitiveType}, {@link jakarta.enterprise.lang.model.types.VoidType} and {@link jakarta.enterprise.lang.model.types.ClassType}.
     *
     * @return The {@link jakarta.enterprise.lang.model.types.Type} of the value.
     */
    Type asType(); // can be a VoidType, PrimitiveType or ClassType

    /**
     * Allows retrieving the values of an array when {@link #isArray()} returns {@code true}.
     *
     * @return An immutable list of {@link jakarta.enterprise.lang.model.AnnotationMemberValue} representing the array values.
     */
    List<AnnotationMemberValue> asArray();

    /**
     * Allows retrieving the a nested annotation value as an an instance of {@link jakarta.enterprise.lang.model.AnnotationInfo} when {@link #isNestedAnnotation()} returns {@code true}.
     * @return The {@link jakarta.enterprise.lang.model.AnnotationInfo} instance.
     * @throws java.lang.IllegalStateException If the value is not an annotation.
     */
    AnnotationInfo<?> asNestedAnnotation();
}
