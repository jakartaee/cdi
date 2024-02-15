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

import java.util.List;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * A parameterized type. That is, a {@linkplain #genericClass() generic class} together with
 * a list of {@linkplain #typeArguments() type arguments}. The list of type arguments
 * has the same shape as the class's list of type parameters.
 *
 * @since 4.0
 */
public interface ParameterizedType extends Type {
    /**
     * Returns the {@linkplain ClassType type} of the generic class that was parameterized with a list
     * of type arguments to form this parameterized type.
     *
     * @return the {@linkplain ClassType type} of the generic class, never {@code null}
     */
    ClassType genericClass();

    /**
     * Returns the list of type arguments that were applied to a generic class to form this parameterized type.
     *
     * @return immutable list of type arguments, never {@code null} or empty
     */
    List<Type> typeArguments();

    /**
     * Returns the {@linkplain ClassInfo declaration} of the {@linkplain #genericClass() generic class}
     * that was parameterized with a list of type arguments to form this parameterized type.
     *
     * @return the {@linkplain ClassInfo declaration} of the generic class, never {@code null}
     */
    default ClassInfo declaration() {
        return genericClass().declaration();
    }

    // ---

    @Override
    default Kind kind() {
        return Kind.PARAMETERIZED_TYPE;
    }

    @Override
    default ParameterizedType asParameterizedType() {
        return this;
    }
}
