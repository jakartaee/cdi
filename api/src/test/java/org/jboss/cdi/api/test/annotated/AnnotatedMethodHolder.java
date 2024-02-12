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

import java.lang.reflect.Method;

import jakarta.enterprise.inject.spi.AnnotatedMethod;

public class AnnotatedMethodHolder<T> extends AnnotatedCallableHolder<T> implements AnnotatedMethod<T> {
    private final Method method;

    public AnnotatedMethodHolder(Method method) {
        this.method = method;
    }

    @Override
    public Method getJavaMember() {
        return method;
    }
}
