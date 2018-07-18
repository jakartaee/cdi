/*
 * JBoss, Home of Professional Open Source
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

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * This class test usage of privileged bloc in {@link javax.enterprise.util.AnnotationLiteral}
 * They launched with Java security Manager.
 *
 * @author Antoine Sabot-Durand
 *
 */
public class AnnotationLiteralTest {

    @Test
    public void toStringShouldWork() {
        Assert.assertNotNull(mal.toString());
    }

    @Test
    public void annotationTypeShouldWork() {
        Assert.assertEquals(MyAnnotation.class, mal.annotationType());
    }

    @Test
    public void equalsShouldWork() {
        MyAnnotationLiteral mal2 = new MyAnnotationLiteral("hello", 42);
        Assert.assertEquals(mal, mal2);
    }

    @Test
    public void hashCodeShouldWork() {
        Assert.assertNotEquals(0, mal.hashCode());
    }

    private static final MyAnnotationLiteral mal = new MyAnnotationLiteral("hello", 42);
}
