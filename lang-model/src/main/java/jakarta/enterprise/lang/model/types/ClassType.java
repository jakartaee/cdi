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

import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * A class type, including interface types, enum types, annotation types and record types.
 * Class types are introduced by class {@linkplain #declaration() declarations}.
 *
 * @since 4.0
 */
public interface ClassType extends Type {
    /**
     * Returns the {@linkplain ClassInfo declaration} of this class type.
     *
     * @return the {@linkplain ClassInfo declaration} of this class type
     */
    ClassInfo declaration();

    // ---

    @Override
    default Kind kind() {
        return Kind.CLASS;
    }

    @Override
    default ClassType asClass() {
        return this;
    }
}
