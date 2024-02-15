/*
 * Copyright 2016, Red Hat, Inc., and individual contributors
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

package org.jboss.cdi.api.test.annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.inject.spi.AnnotatedCallable;
import jakarta.enterprise.inject.spi.AnnotatedParameter;
import jakarta.enterprise.inject.spi.AnnotatedType;

public abstract class AnnotatedCallableHolder<T> implements AnnotatedCallable<T> {
    @Override
    public List<AnnotatedParameter<T>> getParameters() {
        return null;
    }

    @Override
    public Member getJavaMember() {
        return null;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public AnnotatedType<T> getDeclaringType() {
        return null;
    }

    @Override
    public Type getBaseType() {
        return null;
    }

    @Override
    public Set<Type> getTypeClosure() {
        return null;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return null;
    }

    @Override
    public Set<Annotation> getAnnotations() {
        return null;
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return false;
    }
}
