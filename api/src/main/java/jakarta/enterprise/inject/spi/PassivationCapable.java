/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.inject.spi;

import jakarta.enterprise.context.spi.Contextual;

/**
 * Indicates that a custom implementation of {@link Bean} or
 * {@link Contextual} is passivation capable.
 *
 * <p>
 * CDI Lite implementations are not required to provide support for passivation.
 * </p>
 *
 * @author Gavin King
 * @author David Allen
 *
 */
public interface PassivationCapable {
    /**
     * A string that uniquely identifies the instance of {@link Bean} or
     * {@link Contextual}. It is recommended that the string contain the package name of the class
     * that implements {@code Bean} or {@code Contextual}.
     *
     * @return a unique identifier for the {@link Bean} or
     *         {@link Contextual}
     */
    public String getId();
}
