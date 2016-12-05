/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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
package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * <p>
 * Represents a field of a Java class.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * 
 * @param <X> the declaring type
 * @see Field
 */
public interface AnnotatedField<X> extends AnnotatedMember<X> {

    /**
     * <p>
     * Get the underlying {@link Field}.
     * </p>
     * 
     * @return the {@link Field}
     */
    public Field getJavaMember();

    @Override default <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType) {
        T[] annotationsByType = getJavaMember().getAnnotationsByType(annotationType);
        return new LinkedHashSet<>(asList(annotationsByType));
    }
}
