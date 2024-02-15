/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.context.spi;

import jakarta.enterprise.inject.CreationException;
import jakarta.enterprise.inject.spi.Bean;

/**
 * <p>
 * Defines operations to create and destroy contextual instances of a certain type. Any implementation of {@code Contextual} is
 * called a contextual type. In particular, all beans are contextual types.
 * </p>
 *
 * @see Bean
 *
 * @author Gavin King
 * @author Nicklas Karlsson
 * @author Pete Muir
 * @param <T> type of the instance
 */
public interface Contextual<T> {
    /**
     * Create a new instance of the contextual type. Instances should use the given
     * {@link CreationalContext} when obtaining contextual references to inject, in order to ensure
     * that any dependent objects are associated with the contextual instance that is being created. An implementation may call
     * {@link CreationalContext#push(Object)} between instantiation and injection to help the
     * container minimize the use of client proxy objects.
     *
     * @param creationalContext the context in which this instance is being created
     * @return the contextual instance
     * @throws CreationException if a checked exception occurs while creating the instance
     */
    public T create(CreationalContext<T> creationalContext);

    /**
     * Destroy an instance of the contextual type. Implementations should call
     * {@link CreationalContext#release()} to allow the container to destroy dependent objects of
     * the contextual instance.
     *
     * @param instance the contextual instance to destroy
     * @param creationalContext the context in which this instance was created
     */
    public void destroy(T instance, CreationalContext<T> creationalContext);
}
