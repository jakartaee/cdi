/*
 * Copyright 2018, Red Hat, Inc., and individual contributors
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

package org.jboss.cdi.api.test.privileged.annotation;

import jakarta.enterprise.util.AnnotationLiteral;

public class MyAnnotationLiteral extends AnnotationLiteral<MyAnnotation> implements MyAnnotation {

    public MyAnnotationLiteral(String m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public String member1() {
        return m1;
    }

    @Override
    public int member2() {
        return m2;
    }

    private String m1;

    private int m2;

}
