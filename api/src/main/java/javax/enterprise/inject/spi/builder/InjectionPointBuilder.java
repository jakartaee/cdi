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

import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.ProcessInjectionPoint;

/**
 * This API is an helper to configure a new {@link InjectionPoint} instance.
 * CDI container must provides an implementation of this interface.
 * <p/>
 * The same InjectionPointBuilder can be used multiple times.
 * <p/>
 * This builder is not thread safe and shall not be used concurrently.
 *
 * @author Antoine Sabot-Durand
 * @see ProcessInjectionPoint#configureInjectionPoint()
 * @since 2.0
 */
public interface InjectionPointBuilder {

    /**
     * Give access to the builder configuration
     *
     * @return an InjectionPointConfigurator to configure the builder
     */
    InjectionPointConfigurator configure();

    /**
     * Build the InjectionPoint
     *
     * @return the built InjectionPoint
     */
    InjectionPoint build();

}
