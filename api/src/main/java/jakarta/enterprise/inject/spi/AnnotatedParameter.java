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
import java.lang.reflect.Executable;
import java.lang.reflect.Member;
import java.lang.reflect.Parameter;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * Represents a parameter of a method or constructor.
 * </p>
 *
 * @author Gavin King
 * @author Pete Muir
 * @author Jozef Hartinger
 *
 * @param <X> the type that declares the method or constructor
 */
public interface AnnotatedParameter<X> extends Annotated {

    /**
     * <p>
     * Get the position of the parameter in the method or constructor argument list.
     * </p>
     *
     * @return the position of the parameter
     */
    public int getPosition();

    /**
     * <p>
     * Get the declaring {@linkplain AnnotatedCallable method or constructor}.
     * </p>
     *
     * @return the declaring callable
     */
    public AnnotatedCallable<X> getDeclaringCallable();

    /**
     * Get the underlying {@link Parameter}.
     *
     * @return the {@link Parameter}
     */
    default Parameter getJavaParameter() {
        Member member = getDeclaringCallable().getJavaMember();
        if (!(member instanceof Executable)) {
            throw new IllegalStateException("Parameter does not belong to an executable: " + member);
        }
        Executable executable = (Executable) member;
        return executable.getParameters()[getPosition()];
    }

    @Override
    default <T extends Annotation> Set<T> getAnnotations(Class<T> annotationType) {
        T[] annotationsByType = getJavaParameter().getAnnotationsByType(annotationType);
        return new LinkedHashSet<>(asList(annotationsByType));
    }

}
