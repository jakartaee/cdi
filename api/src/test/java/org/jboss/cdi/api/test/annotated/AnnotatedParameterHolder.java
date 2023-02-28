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

package org.jboss.cdi.api.test.annotated;

import jakarta.enterprise.inject.spi.AnnotatedCallable;
import jakarta.enterprise.inject.spi.AnnotatedParameter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public class AnnotatedParameterHolder<T> implements AnnotatedParameter<T> {
    private final AnnotatedCallable<T> annotatedCallable;

    public AnnotatedParameterHolder(AnnotatedCallable<T> annotatedCallable) {
        this.annotatedCallable = annotatedCallable;
    }

    @Override public int getPosition() {
        return 0;
    }

    @Override public AnnotatedCallable<T> getDeclaringCallable() {
        return this.annotatedCallable;
    }

    @Override public Type getBaseType() {
        return null;
    }

    @Override public Set<Type> getTypeClosure() {
        return null;
    }

    @Override public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return null;
    }

    @Override public Set<Annotation> getAnnotations() {
        return null;
    }

    @Override public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return false;
    }
}
