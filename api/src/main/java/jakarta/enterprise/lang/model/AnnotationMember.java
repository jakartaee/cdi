package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.util.List;

/**
 * The value of an annotation member. Annotation member values are of several kinds:
 * <ul>
 * <li>primitive constants;</li>
 * <li>{@link String} constants;</li>
 * <li>{@link Enum} constants;</li>
 * <li>{@link Class} literals;</li>
 * <li>nested {@link java.lang.annotation.Annotation Annotation}s;</li>
 * <li>arrays of previously mentioned types.</li>
 * </ul>
 * The {@link #kind()} method returns the kind of this annotation member value.
 * The {@code is*} methods (such as {@link #isBoolean()}) allow checking
 * if this annotation member value is of given kind. The {@code as*} methods
 * (such as {@link #asBoolean()} allow "unwrapping" this annotation member value,
 * if it is of the corresponding kind.
 * <p>
 * Implementations of this interface are required to define the {@code equals} and {@code hashCode} methods.
 * Implementations of this interface are encouraged to define the {@code toString} method such that
 * it returns a text resembling the corresponding Java&trade; syntax.
 * <p>
 * There is no guarantee that any particular annotation member, represented by an implementation of this interface,
 * will always be represented by the same object. This includes natural singletons such as {@code boolean} values.
 * Instances should always be compared using {@code equals}.
 *
 * @since 4.0
 */
// TODO do the as* methods allow coercion or not? E.g., does asLong return a long
//  if the value is of kind int, or does it throw? Fortunately, this only applies
//  to the numeric primitive types and maybe String. I currently left the numeric
//  as* methods documented as coercing, while asString as not coercing, but this
//  needs more discussion. I personally don't like coercion here and would always
//  throw if the type mismatches.
public interface AnnotationMember {
    /**
     * Name of the commonly used {@code value()} annotation member.
     */
    String VALUE = "value";

    /**
     * The kind of the annotation member value.
     */
    enum Kind {
        /**
         * A primitive {@code boolean} value.
         */
        BOOLEAN,
        /**
         * A primitive {@code byte} value.
         */
        BYTE,
        /**
         * A primitive {@code short} value.
         */
        SHORT,
        /**
         * A primitive {@code int} value.
         */
        INT,
        /**
         * A primitive {@code long} value.
         */
        LONG,
        /**
         * A primitive {@code float} value.
         */
        FLOAT,
        /**
         * A primitive {@code double} value.
         */
        DOUBLE,
        /**
         * A primitive {@code char} value.
         */
        CHAR,
        /**
         * A {@link String} value.
         */
        STRING,
        /**
         * An {@link Enum} value.
         */
        ENUM,
        /**
         * A {@link Class} value. Represented as {@link Type}.
         */
        CLASS,
        /**
         * A nested {@link java.lang.annotation.Annotation Annotation} value.
         * Represented as {@link AnnotationInfo}.
         */
        NESTED_ANNOTATION,
        /**
         * An array value.
         */
        ARRAY,
    }

    /**
     * Returns the kind of this annotation member value.
     *
     * @return the kind of this annotation member value, never {@code null}
     */
    Kind kind();

    /**
     * @return {@code true} if the kind is a {@code boolean}, {@code false} otherwise
     */
    default boolean isBoolean() {
        return kind() == Kind.BOOLEAN;
    }

    /**
     * @return {@code true} if the kind is a {@code byte}, {@code false} otherwise
     */
    default boolean isByte() {
        return kind() == Kind.BYTE;
    }

    /**
     * @return {@code true} if the kind is a {@code short}, {@code false} otherwise
     */
    default boolean isShort() {
        return kind() == Kind.SHORT;
    }

    /**
     * @return {@code true} if the kind is an {@code int}, {@code false} otherwise
     */
    default boolean isInt() {
        return kind() == Kind.INT;
    }

    /**
     * @return {@code true} if the kind is a {@code long}, {@code false} otherwise
     */
    default boolean isLong() {
        return kind() == Kind.LONG;
    }

    /**
     * @return {@code true} if the kind is a {@code float}, {@code false} otherwise
     */
    default boolean isFloat() {
        return kind() == Kind.FLOAT;
    }

    /**
     * @return {@code true} if the kind is a {@code double}, {@code false} otherwise
     */
    default boolean isDouble() {
        return kind() == Kind.DOUBLE;
    }

    /**
     * @return {@code true} if the kind is a {@code char}, {@code false} otherwise
     */
    default boolean isChar() {
        return kind() == Kind.CHAR;
    }

    /**
     * @return {@code true} if the kind is a {@link String}, {@code false} otherwise
     */
    default boolean isString() {
        return kind() == Kind.STRING;
    }

    /**
     * @return {@code true} if the kind is an {@link Enum}, {@code false} otherwise
     */
    default boolean isEnum() {
        return kind() == Kind.ENUM;
    }

    /**
     * @return {@code true} if the kind is a {@link Class}, {@code false} otherwise
     */
    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    /**
     * @return {@code true} if the kind is a nested {@link java.lang.annotation.Annotation Annotation}, {@code false} otherwise
     */
    default boolean isNestedAnnotation() {
        return kind() == Kind.NESTED_ANNOTATION;
    }

    /**
     * @return {@code true} if the kind is an array, {@code false} otherwise
     */
    default boolean isArray() {
        return kind() == Kind.ARRAY;
    }

    /**
     * Returns this value as a boolean.
     *
     * @return the boolean value
     * @throws IllegalStateException if this annotation member value is not a boolean
     */
    boolean asBoolean();

    /**
     * Returns this value as a byte.
     *
     * @return the byte value
     * @throws IllegalStateException if the value cannot be represented as a byte
     */
    byte asByte();

    /**
     * Returns this value as a short.
     *
     * @return the short value
     * @throws IllegalStateException if the value cannot be represented as a short.
     */
    short asShort();

    /**
     * Returns this value as an int.
     *
     * @return the int value
     * @throws IllegalStateException if the value cannot be represented as an int.
     */
    int asInt();

    /**
     * Returns this value as a long.
     *
     * @return the long value
     * @throws IllegalStateException if the value cannot be represented as a long.
     */
    long asLong();

    /**
     * Returns this value as a float.
     *
     * @return the float value
     * @throws IllegalStateException if the value cannot be represented as a float.
     */
    float asFloat();

    /**
     * Returns this value as a double.
     *
     * @return the double value
     * @throws IllegalStateException if the value cannot be represented as a double.
     */
    double asDouble();

    /**
     * Returns this value as a char.
     *
     * @return the char value
     * @throws IllegalStateException if this annotation member value is not a char
     */
    char asChar();

    /**
     * Returns this value as a String.
     *
     * @return the String value
     * @throws IllegalStateException if this annotation member value is not a String
     */
    String asString();

    /**
     * Returns this enum value as an instance of the enum type.
     *
     * @param enumType the enum type
     * @param <E> the enum generic type
     * @return the enum instance
     * @throws IllegalArgumentException if given {@code enumType} is not an enum type
     * @throws IllegalStateException if this annotation member value is not an enum value
     */
    <E extends Enum<E>> E asEnum(Class<E> enumType);

    /**
     * Returns the type of this enum value.
     *
     * @return a {@link ClassInfo} representing the enum type
     * @throws IllegalStateException if this annotation member value is not an enum value
     */
    ClassInfo asEnumClass();

    /**
     * Returns the name of this enum value.
     *
     * @return the name of this enum value
     * @throws IllegalStateException if this annotation member value is not an enum value
     */
    String asEnumConstant();

    /**
     * Returns this class value as a {@link Type}. It can be:
     * <ul>
     * <li>the {@linkplain jakarta.enterprise.lang.model.types.VoidType void} pseudo-type;</li>
     * <li>a {@linkplain jakarta.enterprise.lang.model.types.PrimitiveType primitive} type;</li>
     * <li>a {@linkplain jakarta.enterprise.lang.model.types.ClassType class} type;</li>
     * <li>an {@linkplain jakarta.enterprise.lang.model.types.ArrayType array} type, whose element type
     * is a primitive type or a class type.</li>
     * </ul>
     *
     * @return the class value, as a {@link Type}
     * @throws IllegalStateException if this annotation member value is not a class value
     */
    Type asType();

    /**
     * Returns this nested annotation value as an {@link AnnotationInfo}.
     *
     * @return an {@link AnnotationInfo} instance
     * @throws IllegalStateException if this annotation member value is not a nested annotation
     */
    AnnotationInfo asNestedAnnotation();

    /**
     * Returns this array value as an immutable {@link List} of {@link AnnotationMember}s.
     * Returns an empty list if the array is empty.
     *
     * @return an immutable list of {@link AnnotationMember}s
     * @throws IllegalStateException if this annotation member value is not an array
     */
    List<AnnotationMember> asArray();
}
