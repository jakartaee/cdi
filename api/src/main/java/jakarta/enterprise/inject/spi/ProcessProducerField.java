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

/**
 * <p>
 * The container fires an event of this type for each enabled producer field, before registering the
 * {@link Bean} object. Resources are considered to be producer fields.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessProducerField} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for Portable Extensions.
 * </p>
 *
 * @author David Allen
 * @param <T> The type of the producer field
 * @param <X> The class of the bean declaring the producer field
 *
 */
public interface ProcessProducerField<T, X> extends ProcessBean<X> {
    /**
     * Returns the {@link AnnotatedField} representing the producer field.
     *
     * @return the {@link AnnotatedField} for the producer field being registered
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public AnnotatedField<T> getAnnotatedProducerField();

    /**
     * Returns the {@link AnnotatedParameter} for any matching injection point of the same type as
     * the producer field return type found on a disposal method.
     *
     * @return the disposal method's {@link AnnotatedParameter}
     * @throws IllegalStateException if called outside of the observer method invocation
     * @since 1.1
     */
    public AnnotatedParameter<T> getAnnotatedDisposedParameter();
}
