/*
 * Copyright (c) 2021 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.AnnotationMember;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.types.Type;

import java.lang.annotation.Annotation;

/**
 * Builder for annotations of given type.
 * Expected usage is:
 * <ol>
 * <li>create the builder using {@link #of(Class)} or {@link #of(ClassInfo)};</li>
 * <li>use the {@code value()} and {@code member()} methods to define annotation members;</li>
 * <li>call {@link #build()} to create an {@link AnnotationInfo}.</li>
 * </ol>
 * One builder instance should not be used to create multiple annotations.
 * <p>
 * Note that values of all members of given annotation type must be defined before
 * calling {@code build()}, except of annotation members that declare a default value.
 * If a value is not defined for an annotation member that does not have a default value,
 * {@code build()} will throw an exception.
 * <p>
 * Defining values of members that do not exist on given annotation type is possible,
 * and such members will be retained in the resulting {@code AnnotationInfo}. However,
 * if that {@code AnnotationInfo} is later transformed to an instance of the annotation
 * type, the non-existing members will disappear.
 *
 * @since 4.0
 */
public interface AnnotationBuilder {
    /**
     * Returns a new {@link AnnotationBuilder} that builds an annotation of given type.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return a new {@code AnnotationBuilder}, never {@code null}
     */
    static AnnotationBuilder of(Class<? extends Annotation> annotationType) {
        return BuildServicesResolver.get().annotationBuilderFactory().create(annotationType);
    }

    /**
     * Returns a new {@link AnnotationBuilder} that builds an annotation of given type.
     *
     * @param annotationType the annotation type, must not be {@code null}
     * @return a new {@code AnnotationBuilder}
     */
    static AnnotationBuilder of(ClassInfo annotationType) {
        return BuildServicesResolver.get().annotationBuilderFactory().create(annotationType);
    }

    /**
     * Adds an annotation member called {@code value}, whose value is given {@code value}.
     *
     * @param value value of the annotation member
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(AnnotationMember value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a boolean-valued annotation member called {@code value}.
     *
     * @param value the boolean value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(boolean value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a boolean array-valued annotation member called {@code value}.
     *
     * @param values the boolean array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(boolean[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a byte-valued annotation member called {@code value}.
     *
     * @param value the byte value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(byte value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a byte array-valued annotation member called {@code value}.
     *
     * @param values the byte array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(byte[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a short-valued annotation member called {@code value}.
     *
     * @param value the short value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(short value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a short array-valued annotation member called {@code value}.
     *
     * @param values the short array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(short[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds an int-valued annotation member called {@code value}.
     *
     * @param value the int value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(int value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds an int array-valued annotation member called {@code value}.
     *
     * @param values the int array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(int[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a long-valued annotation member called {@code value}.
     *
     * @param value the long value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(long value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a long array-valued annotation member called {@code value}.
     *
     * @param values the long array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(long[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a float-valued annotation member called {@code value}.
     *
     * @param value the float value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(float value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a float array-valued annotation member called {@code value}.
     *
     * @param values the float array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(float[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a double-valued annotation member called {@code value}.
     *
     * @param value the double value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(double value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a double array-valued annotation member called {@code value}.
     *
     * @param values the double array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(double[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a char-valued annotation member called {@code value}.
     *
     * @param value the char value
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(char value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a char array-valued annotation member called {@code value}.
     *
     * @param values the char array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(char[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a String-valued annotation member called {@code value}.
     *
     * @param value the String value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(String value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a String array-valued annotation member called {@code value}.
     *
     * @param values the String array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(String[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds an enum-valued annotation member called {@code value}.
     *
     * @param value the enum value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Enum<?> value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds an enum array-valued annotation member called {@code value}.
     *
     * @param values the enum array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Enum<?>[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds an enum-valued annotation member called {@code value}.
     *
     * @param enumType the enum type, must not be {@code null}
     * @param enumValue name of the enum constant, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Class<? extends Enum<?>> enumType, String enumValue) {
        return member(AnnotationMember.VALUE, enumType, enumValue);
    }

    /**
     * Adds an enum array-valued annotation member called {@code value}.
     *
     * @param enumType the enum type, must not be {@code null}
     * @param enumValues names of the enum constants, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Class<? extends Enum<?>> enumType, String[] enumValues) {
        return member(AnnotationMember.VALUE, enumType, enumValues);
    }

    /**
     * Adds an enum-valued annotation member called {@code value}.
     *
     * @param enumType the enum type, must not be {@code null}
     * @param enumValue name of the enum constant, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(ClassInfo enumType, String enumValue) {
        return member(AnnotationMember.VALUE, enumType, enumValue);
    }

    /**
     * Adds an enum array-valued annotation member called {@code value}.
     *
     * @param enumType the enum type, must not be {@code null}
     * @param enumValues names of the enum constants, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(ClassInfo enumType, String[] enumValues) {
        return member(AnnotationMember.VALUE, enumType, enumValues);
    }

    /**
     * Adds a class-valued annotation member called {@code value}.
     *
     * @param value the class value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Class<?> value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a class array-valued annotation member called {@code value}.
     *
     * @param values the class array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Class<?>[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a class-valued annotation member called {@code value}.
     *
     * @param value the class value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(ClassInfo value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a class array-valued annotation member called {@code value}.
     *
     * @param values the class array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(ClassInfo[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds a class-valued annotation member called {@code value}.
     * The {@code value} parameter may only be:
     * <ul>
     * <li>{@link jakarta.enterprise.lang.model.types.VoidType VoidType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ClassType ClassType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ArrayType ArrayType}
     * whose element type is either {@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType}
     * or {@link jakarta.enterprise.lang.model.types.ClassType ClassType}.</li>
     * </ul>
     *
     * @param value the class value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     * @throws IllegalArgumentException if given type is invalid, as described above
     */
    default AnnotationBuilder value(Type value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds a class array-valued annotation member called {@code value}.
     * The {@code values} parameter may only contain:
     * <ul>
     * <li>{@link jakarta.enterprise.lang.model.types.VoidType VoidType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ClassType ClassType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ArrayType ArrayType}
     * whose element type is either {@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType}
     * or {@link jakarta.enterprise.lang.model.types.ClassType ClassType}.</li>
     * </ul>
     *
     * @param values the class array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     * @throws IllegalArgumentException if any given type is invalid, as described above
     */
    default AnnotationBuilder value(Type[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds an annotation-valued annotation member called {@code value}.
     *
     * @param value the annotation value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(AnnotationInfo value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds an annotation array-valued annotation member called {@code value}.
     *
     * @param values the annotation array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(AnnotationInfo[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds an annotation-valued annotation member called {@code value}.
     *
     * @param value the annotation value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Annotation value) {
        return member(AnnotationMember.VALUE, value);
    }

    /**
     * Adds an annotation array-valued annotation member called {@code value}.
     *
     * @param values the annotation array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    default AnnotationBuilder value(Annotation[] values) {
        return member(AnnotationMember.VALUE, values);
    }

    /**
     * Adds an annotation member with given {@code name}, whose value is given {@code value}.
     *
     * @param name name of the annotation member, must not be {@code null}
     * @param value value of the annotation member, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, AnnotationMember value);

    /**
     * Adds a boolean-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the boolean value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, boolean value);

    /**
     * Adds a boolean array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the boolean array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, boolean[] values);

    /**
     * Adds a byte-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the byte value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, byte value);

    /**
     * Adds a byte array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the byte array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, byte[] values);

    /**
     * Adds a short-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the short value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, short value);

    /**
     * Adds a short array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the short array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, short[] values);

    /**
     * Adds an int-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the int value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, int value);

    /**
     * Adds an int array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the int array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, int[] values);

    /**
     * Adds a long-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the long value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, long value);

    /**
     * Adds a long array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the long array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, long[] values);

    /**
     * Adds a float-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the float value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, float value);

    /**
     * Adds a float array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the float array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, float[] values);

    /**
     * Adds a double-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the double value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, double value);

    /**
     * Adds a double array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the double array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, double[] values);

    /**
     * Adds a char-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the char value
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, char value);

    /**
     * Adds a char array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the char array, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, char[] values);

    /**
     * Adds a String-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the String value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, String value);

    /**
     * Adds a String array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the String array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, String[] values);

    /**
     * Adds an enum-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the enum value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Enum<?> value);

    /**
     * Adds an enum array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the enum array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Enum<?>[] values);

    /**
     * Adds an enum-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param enumType the enum type, must not be {@code null}
     * @param enumValue name of the enum constant, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Class<? extends Enum<?>> enumType, String enumValue);

    /**
     * Adds an enum array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param enumType the enum type, must not be {@code null}
     * @param enumValues names of the enum constants, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Class<? extends Enum<?>> enumType, String[] enumValues);

    /**
     * Adds an enum-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param enumType the enum type, must not be {@code null}
     * @param enumValue name of the enum constant, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, ClassInfo enumType, String enumValue);

    /**
     * Adds an enum array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param enumType the enum type, must not be {@code null}
     * @param enumValues names of the enum constants, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, ClassInfo enumType, String[] enumValues);

    /**
     * Adds a class-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the class value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Class<?> value);

    /**
     * Adds a class array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the class array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Class<?>[] values);

    /**
     * Adds a class-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the class value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, ClassInfo value);

    /**
     * Adds a class array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the class array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, ClassInfo[] values);

    /**
     * Adds a class-valued annotation member with given {@code name}.
     * The {@code value} parameter may only be:
     * <ul>
     * <li>{@link jakarta.enterprise.lang.model.types.VoidType VoidType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ClassType ClassType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ArrayType ArrayType}
     * whose element type is either {@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType}
     * or {@link jakarta.enterprise.lang.model.types.ClassType ClassType}.</li>
     * </ul>
     * Any other value results in an exception.
     *
     * @param name the member name, must not be {@code null}
     * @param value the class value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     * @throws IllegalArgumentException if given type is invalid, as described above
     */
    AnnotationBuilder member(String name, Type value);

    /**
     * Adds a class array-valued annotation member with given {@code name}.
     * The {@code values} parameter may only include:
     * <ul>
     * <li>{@link jakarta.enterprise.lang.model.types.VoidType VoidType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ClassType ClassType};</li>
     * <li>{@link jakarta.enterprise.lang.model.types.ArrayType ArrayType}
     * whose element type is either {@link jakarta.enterprise.lang.model.types.PrimitiveType PrimitiveType}
     * or {@link jakarta.enterprise.lang.model.types.ClassType ClassType}.</li>
     * </ul>
     *
     * @param name the member name, must not be {@code null}
     * @param values the class array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     * @throws IllegalArgumentException if any given type is invalid, as described above
     */
    AnnotationBuilder member(String name, Type[] values);

    /**
     * Adds an annotation-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the annotation value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, AnnotationInfo value);

    /**
     * Adds an annotation array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the annotation array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, AnnotationInfo[] values);

    /**
     * Adds an annotation-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param value the annotation value, must not be {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Annotation value);

    /**
     * Adds an annotation array-valued annotation member with given {@code name}.
     *
     * @param name the member name, must not be {@code null}
     * @param values the annotation array, must not be {@code null} or contain {@code null}
     * @return this {@code AnnotationBuilder}
     */
    AnnotationBuilder member(String name, Annotation[] values);

    /**
     * Returns an {@link AnnotationInfo} that includes all annotation members defined by previous method calls
     * on this builder. After {@code build()} is called, this builder instance should be discarded.
     *
     * @return the built {@link AnnotationInfo}, never {@code null}
     * @throws IllegalStateException if a value of some annotation member was not set, and that member
     * does not declare a default value
     */
    AnnotationInfo build();
}
