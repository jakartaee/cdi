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
package jakarta.enterprise.lang.model;

import java.lang.annotation.Repeatable;
import java.util.Map;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * An annotation instance, typically obtained from an {@link AnnotationTarget}.
 * Provides access to annotation members and their values.
 * <p>
 * Implementations of this interface are required to define the {@code equals} and {@code hashCode} methods.
 * Implementations of this interface are encouraged to define the {@code toString} method such that
 * it returns a text resembling the corresponding Java&trade; syntax.
 * <p>
 * There is no guarantee that any particular annotation instance, represented by an implementation of this interface,
 * will always be represented by the same object. That includes natural singletons such as the {@code jakarta.inject.Singleton}
 * annotation. Instances should always be compared using {@code equals}.
 *
 * @since 4.0
 */
public interface AnnotationInfo {
    /**
     * Returns the {@linkplain ClassInfo declaration} of this annotation's type.
     *
     * @return the {@linkplain ClassInfo declaration} of this annotation's type, never {@code null}
     */
    ClassInfo declaration();

    /**
     * Binary name of this annotation's type, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the annotation type name as returned by {@link Class#getName()}.
     * Equivalent to {@code declaration().name()}.
     *
     * @return binary name of this annotation's type, never {@code null}
     */
    default String name() {
        return declaration().name();
    }

    /**
     * Returns whether this annotation is repeatable. In other words, returns whether
     * this annotation's type is meta-annotated {@code @Repeatable}.
     *
     * @return whether this annotation is repeatable
     */
    default boolean isRepeatable() {
        return declaration().hasAnnotation(Repeatable.class);
    }

    /**
     * Returns whether this annotation has a member with given {@code name}.
     *
     * @param name member name, must not be {@code null}
     * @return {@code true} if this annotation has a member with given {@code name}, {@code false} otherwise
     */
    boolean hasMember(String name);

    /**
     * Returns the {@linkplain AnnotationMember value} of this annotation's member with given {@code name}.
     *
     * @param name member name, must not be {@code null}
     * @return value of this annotation's member with given {@code name} or {@code null} if such member does not exist
     */
    AnnotationMember member(String name);

    /**
     * Returns whether this annotation has the {@link AnnotationMember#VALUE value} member.
     *
     * @return {@code true} if this annotation has the {@link AnnotationMember#VALUE value} member, {@code false} otherwise
     */
    default boolean hasValue() {
        return hasMember(AnnotationMember.VALUE);
    }

    /**
     * Returns the {@linkplain AnnotationMember value} of this annotation's {@link AnnotationMember#VALUE value} member.
     *
     * @return value of this annotation's {@link AnnotationMember#VALUE value} member or {@code null} if the member does not
     *         exist
     */
    default AnnotationMember value() {
        return member(AnnotationMember.VALUE);
    }

    /**
     * Returns all members of this annotation as a map, where the key is the member name
     * and the value is the member value. Returns an empty map if this annotation has no members.
     *
     * @return an immutable map of all members of this annotation, never {@code null}
     */
    Map<String, AnnotationMember> members();
}
