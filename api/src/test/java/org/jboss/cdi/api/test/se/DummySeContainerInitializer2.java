/*
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

package org.jboss.cdi.api.test.se;

import java.lang.annotation.Annotation;
import java.util.Map;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.Extension;

/**
 *
 * Dummy implementation of {@link SeContainerInitializer} for tests
 *
 * @author Antoine Sabot-Durand.
 */
public class DummySeContainerInitializer2 extends SeContainerInitializer {
    @Override
    public SeContainerInitializer addBeanClasses(Class<?>... classes) {
        return null;
    }

    @Override
    public SeContainerInitializer addPackages(Class<?>... packageClasses) {
        return null;
    }

    @Override
    public SeContainerInitializer addPackages(boolean scanRecursively, Class<?>... packageClasses) {
        return null;
    }

    @Override
    public SeContainerInitializer addPackages(Package... packages) {
        return null;
    }

    @Override
    public SeContainerInitializer addPackages(boolean scanRecursively, Package... packages) {
        return null;
    }

    @Override
    public SeContainerInitializer addExtensions(Extension... extensions) {
        return null;
    }

    @SafeVarargs
    @Override
    public final SeContainerInitializer addExtensions(Class<? extends Extension>... extensions) {
        return null;
    }

    @Override
    public SeContainerInitializer enableInterceptors(Class<?>... interceptorClasses) {
        return null;
    }

    @Override
    public SeContainerInitializer enableDecorators(Class<?>... decoratorClasses) {
        return null;
    }

    @Override
    public SeContainerInitializer selectAlternatives(Class<?>... alternativeClasses) {
        return null;
    }

    @SafeVarargs
    @Override
    public final SeContainerInitializer selectAlternativeStereotypes(
            Class<? extends Annotation>... alternativeStereotypeClasses) {
        return null;
    }

    @Override
    public SeContainerInitializer addProperty(String key, Object value) {
        return null;
    }

    @Override
    public SeContainerInitializer setProperties(Map<String, Object> properties) {
        return null;
    }

    @Override
    public SeContainerInitializer disableDiscovery() {
        return null;
    }

    @Override
    public SeContainerInitializer setClassLoader(ClassLoader classLoader) {
        return null;
    }

    @Override
    public SeContainer initialize() {
        return null;
    }
}
