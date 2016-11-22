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

package javax.enterprise.inject.spi.configurator;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.ProcessInjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * This API is an helper to configure an existing {@link InjectionPoint} instance.
 * CDI container must provides an implementation of this interface.
 *
 * This builder is not thread safe and shall not be used concurrently.
 *
 * @see ProcessInjectionPoint#configureInjectionPoint()
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public interface InjectionPointConfigurator {

    /**
     * Set the required {@link Type} (that will be used during typesafe resolution)
     * of the InjectionPoint to build.
     *
     * @param requiredType for the InjectionPoint to build
     * @return self
     */
    InjectionPointConfigurator type(Type requiredType);

    /**
     *
     * Add the qualifier to the InjectionPoint to build
     *
     * @param qualifier the qualifier to add
     * @return self
     */
    InjectionPointConfigurator addQualifier(Annotation qualifier);

    /**
     *
     * Add all the qualifiers to the InjectionPoint to build
     *
     * @param qualifiers a varargs or array of qualifiers to add
     * @return self
     */
    InjectionPointConfigurator addQualifiers(Annotation... qualifiers);

    /**
     *
     * Add all the qualifiers to the InjectionPoint to build
     *
     * @param qualifiers a Set of qualifiers to add
     * @return self
     */
    InjectionPointConfigurator addQualifiers(Set<Annotation> qualifiers);

    /**
     * Replace all qualifiers.
     *
     * @param qualifiers a varargs or array of qualifiers to replace to existing ones
     * @return self
     */
    InjectionPointConfigurator qualifiers(Annotation... qualifiers);

    /**
     * Replace all qualifiers.
     *
     * @param qualifiers a Set of qualifiers to replace to existing ones
     * @return self
     */
    InjectionPointConfigurator qualifiers(Set<Annotation> qualifiers);

    /**
     *
     * Change the delegate status of the built InjectionPoint.
     * By default the InjectionPoint is not a delegate one.
     *
     * @param delegate boolean to define or undefine the delegate nature of the configured InjectionPoint
     * @return self
     */
    InjectionPointConfigurator delegate(boolean delegate);

    /**
     *
     * Change the transient status of the built InjectionPoint.
     * By default the InjectionPoint is not transient.
     *
     * @param trans boolean to define or undefine the transient nature of the configured InjectionPoint
     * @return self
     */
    InjectionPointConfigurator transientField(boolean trans);
}
