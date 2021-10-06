/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
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

import java.lang.annotation.Annotation;
import java.util.Iterator;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.CDIProvider;
import jakarta.enterprise.util.TypeLiteral;

/**
 * Created by antoine on 16/12/2015.
 */
public class DummyCDIProvider2 implements CDIProvider {


    @Override
    public CDI<Object> getCDI() {
        return new DummyCDI2();
    }

    @Override
    public int getPriority() {
        return 10;
    }

    public static class DummyCDI2 extends CDI<Object> {


        @Override
        public BeanManager getBeanManager() {
            return null;
        }

        @Override
        public Instance<Object> select(Annotation... qualifiers) {
            return null;
        }

        @Override
        public <U> Instance<U> select(Class<U> subtype, Annotation... qualifiers) {
            return null;
        }

        @Override
        public <U> Instance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
            return null;
        }

        @Override
        public boolean isUnsatisfied() {
            return false;
        }

        @Override
        public boolean isAmbiguous() {
            return false;
        }
        
        @Override
        public void destroy(Object instance) {

        }

        @Override
        public Handle<Object> getHandle() {
            return null;
        }

        @Override
        public Iterable<Handle<Object>> handles() {
            return null;
        }

        @Override
        public Iterator<Object> iterator() {
            return null;
        }

        @Override
        public Object get() {
            return null;
        }
    }
}
