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
package jakarta.enterprise.lang.model.declarations;

/**
 * A package, possibly annotated in {@code package-info.java}.
 * Obtaining the set of members present in this package is not possible.
 *
 * @since 4.0
 */
public interface PackageInfo extends DeclarationInfo {
    /**
     * Returns the fully qualified name of this package, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the package name as returned by {@link Package#getName()}.
     *
     * @return fully qualified name of this package, never {@code null}
     */
    String name();

    // ---

    @Override
    default Kind kind() {
        return Kind.PACKAGE;
    }

    @Override
    default PackageInfo asPackage() {
        return this;
    }
}
