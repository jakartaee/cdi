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

package jakarta.enterprise.inject.spi;

import java.util.Set;

import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;

/**
 * <p>
 * Represents an {@linkplain jakarta.enterprise.inject enabled bean}. This interface defines everything the container needs to
 * manage instances of the bean.
 * </p>
 *
 * @author Gavin King
 * @author David Allen
 * @param <T> the class of the bean instance
 */
public interface Bean<T> extends Contextual<T>, BeanAttributes<T> {

    /**
     * Destroy an instance of this bean.
     * <p>
     * Implementations of {@code Bean} that are not backed by a {@link Producer} should include the following code
     * at the end of {@code destroy()} to satisfy the {@link Contextual#destroy(Object, CreationalContext)} requirements
     * and support auto-closeable beans:
     *
     * <pre>{@code
     * if (isAutoClose() && instance instanceof AutoCloseable ac) {
     *     try {
     *         ac.close();
     *     } catch (Exception e) {
     *         // log an error or handle the exception in some other way
     *         // bean destruction is not allowed to throw an exception
     *     }
     * }
     *
     * creationalContext.release();
     * }</pre>
     */
    @Override
    public void destroy(T instance, CreationalContext<T> creationalContext);

    /**
     * The bean {@linkplain Class class} of the managed bean or session bean or of the bean that declares the producer method or
     * field.
     *
     * @return the bean {@linkplain Class class}
     */
    public Class<?> getBeanClass();

    /**
     * Obtains the {@link InjectionPoint} objects representing injection points of the bean, that
     * will be validated by the container at initialization time.
     *
     * @return the set of {@linkplain InjectionPoint injection points} of the bean
     */
    public Set<InjectionPoint> getInjectionPoints();
}
