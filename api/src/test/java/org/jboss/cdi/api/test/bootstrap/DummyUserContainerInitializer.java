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

package org.jboss.cdi.api.test.bootstrap;

import javax.enterprise.inject.bootstrap.UserContainer;
import javax.enterprise.inject.bootstrap.UserContainerInitializer;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.Extension;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by antoine on 23/06/2016.
 */
public class DummyUserContainerInitializer extends UserContainerInitializer {
    @Override
    public UserContainerInitializer addBeanClasses(Class<?>... classes) {
        return null;
    }

    @Override
    public UserContainerInitializer addPackages(Class<?>... packageClasses) {
        return null;
    }

    @Override
    public UserContainerInitializer addPackages(boolean scanRecursively, Class<?>... packageClasses) {
        return null;
    }

    @Override
    public UserContainerInitializer addPackages(Package... packages) {
        return null;
    }

    @Override
    public UserContainerInitializer addPackages(boolean scanRecursively, Package... packages) {
        return null;
    }

    @Override
    public UserContainerInitializer addAnnotatedTypes(AnnotatedType<?>... annotatedTypes) {
        return null;
    }

    @Override
    public UserContainerInitializer addExtensions(Extension... extensions) {
        return null;
    }

    @Override
    public UserContainerInitializer addExtensions(Class<? extends Extension>... extensions) {
        return null;
    }

    @Override
    public UserContainerInitializer addInterceptors(Class<?>... interceptorClasses) {
        return null;
    }

    @Override
    public UserContainerInitializer addDecorators(Class<?>... decoratorClasses) {
        return null;
    }

    @Override
    public UserContainerInitializer addAlternatives(Class<?>... alternativeClasses) {
        return null;
    }

    @Override
    public UserContainerInitializer addAlternativeStereotypes(Class<? extends Annotation>... alternativeStereotypeClasses) {
        return null;
    }

    @Override
    public UserContainerInitializer addProperty(String key, Object value) {
        return null;
    }

    @Override
    public UserContainerInitializer properties(Map<String, Object> properties) {
        return null;
    }

    @Override
    public UserContainerInitializer addBeans(Bean<?>... beans) {
        return null;
    }

    @Override
    public UserContainerInitializer disableDiscovery() {
        return null;
    }

    @Override
    public UserContainerInitializer setClassLoader(ClassLoader classLoader) {
        return null;
    }

    @Override
    public UserContainer<Object> initialize() {
        return null;
    }
}
