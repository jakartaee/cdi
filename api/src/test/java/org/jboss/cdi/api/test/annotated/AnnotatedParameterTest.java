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

import org.testng.annotations.BeforeClass;

import jakarta.enterprise.inject.spi.Annotated;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class AnnotatedParameterTest extends AbstractAnnotatedTest {
    private Method method;
    private Parameter parameter;
    @BeforeClass
    public void getMethod() throws Exception {
        this.method = RepeatBean.class.getMethod("doRepeat", RepeatBean.class);
        this.parameter = method.getParameters()[0];
    }
    @Override protected Annotated getAnnotated() {
        return new AnnotatedParameterHolder<>(new AnnotatedMethodHolder<>(method));
    }

    @Override protected <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return parameter.getAnnotationsByType(annotationClass);
    }

    @Override protected <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return parameter.getAnnotation(annotationClass);
    }
}
