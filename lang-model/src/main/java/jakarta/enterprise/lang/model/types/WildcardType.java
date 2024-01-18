/*
 * Copyright 2021, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
 * A wildcard type. May have 3 forms:
 * <ul>
 * <li>{@code ? extends Number}: has an upper bound</li>
 * <li>{@code ? super Number}: has a lower bound</li>
 * <li>{@code ?}: unbounded, has an implicit upper bound of {@code java.lang.Object}</li>
 * </ul>
 * Note that {@code ?} is equivalent to {@code ? extends Object} and is represented as such.
 * Therefore, either {@link #upperBound()} or {@link #lowerBound()} always returns non-{@code null}.
 */
public interface WildcardType extends Type {
    /**
     * Returns the upper bound of this wildcard type.
     * Returns {@code null} if this wildcard type does not have an upper bound.
     *
     * @return upper bound of this wildcard type, or {@code null} if this wildcard type does not have an upper bound
     */
    Type upperBound();

    /**
     * Returns the lower bound of this wildcard type.
     * Returns {@code null} if this wildcard type does not have a lower bound.
     *
     * @return lower bound of this wildcard type, or {@code null} if this wildcard type does not have a lower bound
     */
    Type lowerBound();

    // ---

    @Override
    default Kind kind() {
        return Kind.WILDCARD_TYPE;
    }

    @Override
    default WildcardType asWildcardType() {
        return this;
    }
}
