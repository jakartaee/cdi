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

package org.jboss.cdi.api.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.builder.AnnotatedTypeBuilder;
import javax.enterprise.inject.spi.builder.Builders;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Created by antoine on 25/02/2016.
 */
public class BuilderTest implements Extension {

    public void processAT(@Observes ProcessAnnotatedType<?> pat) {

        AnnotatedTypeBuilder atb = Builders.annotatedType(pat.getAnnotatedType().getJavaClass());
        atb.configure().read(pat.getAnnotatedType());

        pat.configureAnnotatedType().addToType(new AnnotationLiteral<ApplicationScoped>() {
        });

    }
}
