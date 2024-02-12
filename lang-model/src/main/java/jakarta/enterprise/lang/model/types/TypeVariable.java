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

/**
 * Type variables represent type parameters declared on generic classes or methods.
 * All type variables have bounds. A type variable with no bound declared is equivalent to
 * a type variable with a single bound of {@code java.lang.Object} and is represented as such.
 * If one bound is declared, it is a type variable or a class type, possibly parameterized.
 * If more than one bound is declared, the first bound is a class type or an interface type,
 * possibly parameterized, and the following bounds are interface types, possibly parameterized.
 */
public interface TypeVariable extends Type {
    /**
     * Returns the name of this type variable.
     *
     * @return the name of this type variable, never {@code null}
     */
    String name();

    /**
     * Returns the bounds declared for this type variable.
     *
     * @return the bounds declared for this type variable, never {@code null} or empty
     */
    List<Type> bounds();

    // ---

    @Override
    default Kind kind() {
        return Kind.TYPE_VARIABLE;
    }

    @Override
    default TypeVariable asTypeVariable() {
        return this;
    }
}
