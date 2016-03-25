/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
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

package javax.enterprise.inject.spi.builder;

import javax.enterprise.inject.spi.Bean;

/**
 * This API is an helper to configure and build a new {@link Bean} instance.
 * CDI container must provides an implementation of this interface.
 *
 * The same BeanBuilder can be used multiple times.
 *
 * This builder is not thread safe and shall not be used concurrently.
 *
 * @see Builders#bean(Class)
 * @author Antoine Sabot-Durand
 * @param <T> the class of the bean instance
 * @since 2.0
 */
public interface BeanBuilder<T> {

    /**
     * Give access to the builder configuration
     *
     * @return an BeanConfigurator to configure the builder
     */
    BeanConfigurator<T> configure();

    /**
     * Build the Bean
     *
     * @return the built Bean
     */
    Bean<T> build();

}
