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

package javax.enterprise.util;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Excludes a member of an annotation type (such as a {@linkplain javax.inject.Qualifier qualifier type} or
 * {@linkplain javax.interceptor interceptor binding type}) from consideration when the container compares two annotation
 * instances.
 * </p>
 *
 * <pre>
 * &#064;Qualifier
 * &#064;Retention(RUNTIME)
 * &#064;Target({ METHOD, FIELD, PARAMETER, TYPE })
 * public @interface PayBy {
 *     PaymentMethod value();
 *
 *     &#064;Nonbinding
 *     String comment();
 * }
 * </pre>
 *
 * @author Gavin King
 *
 * @see javax.inject.Qualifier &#064;Qualifier
 * @see javax.interceptor.InterceptorBinding &#064;InterceptorBinding
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Nonbinding {

    /**
     * Supports inline instantiation of the {@link Nonbinding} annotation.
     *
     * @author Martin Kouba
     * @since 2.0
     */
    public static final class Literal extends AnnotationLiteral<Nonbinding> implements Nonbinding {

        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

    }
}
