/*
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
package jakarta.enterprise.inject.spi;

import static java.util.Arrays.asList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * Represents a method of a Java type.
 * </p>
 *
 * @author Gavin King
 * @author Pete Muir
 *
 * @param <X> the declaring type
 * @see Method
 */
public interface AnnotatedMethod<X> extends AnnotatedCallable<X> {

    /**
     * <p>
     * Get the underlying {@link Method}.
     * </p>
     *
     * @return the {@link Method}
     */
    public Method getJavaMember();

    @Override
    default <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType) {
        T[] annotationsByType = getJavaMember().getAnnotationsByType(annotationType);
        return new LinkedHashSet<>(asList(annotationsByType));
    }
}
