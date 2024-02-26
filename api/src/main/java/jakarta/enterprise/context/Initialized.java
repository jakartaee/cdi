/*
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.context;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;

/**
 * An event with this qualifier is fired when a context is initialized, i.e. ready for use.
 *
 * @author Pete Muir
 * @see BeforeDestroyed
 * @see Destroyed
 * @since 1.1
 */
@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Initialized {

    /**
     * The scope for which to observe initialization
     *
     * @return the scope type class
     */
    Class<? extends Annotation> value();

    /**
     * Supports inline instantiation of the {@link Initialized} qualifier.
     *
     * @author Martin Kouba
     */
    public final static class Literal extends AnnotationLiteral<Initialized> implements Initialized {
        /** Default RequestScoped literal */
        public static final Literal REQUEST = of(RequestScoped.class);
        /** Default ConversationScoped literal */

        public static final Literal CONVERSATION = of(ConversationScoped.class);
        /** Default SessionScoped literal */

        public static final Literal SESSION = of(SessionScoped.class);
        /** Default ApplicationScoped literal */

        public static final Literal APPLICATION = of(ApplicationScoped.class);

        private static final long serialVersionUID = 1L;

        /** */
        private final Class<? extends Annotation> value;

        /**
         * Obtain the literal of the provided scope annotation
         * @param value - the scope annotation
         * @return a new Literal value for the provided scope annotation
         */
        public static Literal of(Class<? extends Annotation> value) {
            return new Literal(value);
        }

        private Literal(Class<? extends Annotation> value) {
            this.value = value;
        }

        public Class<? extends Annotation> value() {
            return value;
        }
    }

}
