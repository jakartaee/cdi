/*
 * Copyright 2016, Red Hat, Inc., and individual contributors
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
 * An event with this qualifier is fired when a context is about to be destroyed, i.e. before the actual destruction.
 *
 * @author Pete Muir
 * @author Martin Kouba
 * @see Initialized
 * @see Destroyed
 * @since 2.0
 */
@Qualifier
@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
public @interface BeforeDestroyed {

    /**
     * The scope for which to observe destruction
     *
     * @return the scope type class
     */
    Class<? extends Annotation> value();

    /**
     * Supports inline instantiation of the {@link BeforeDestroyed} qualifier.
     */
    final class Literal extends AnnotationLiteral<BeforeDestroyed> implements BeforeDestroyed {
        /** Default BeforeDestroyed literal for the RequestScoped scope */
        public static final Literal REQUEST = of(RequestScoped.class);

        /** Default BeforeDestroyed literal for the ConversationScoped scope */
        public static final Literal CONVERSATION = of(ConversationScoped.class);

        /** Default BeforeDestroyed literal for the SessionScoped scope */
        public static final Literal SESSION = of(SessionScoped.class);

        /** Default BeforeDestroyed literal for the ApplicationScoped scope */
        public static final Literal APPLICATION = of(ApplicationScoped.class);

        private static final long serialVersionUID = 1L;

        /** The scope annotation */
        private final Class<? extends Annotation> value;

        /**
         * Obtain the literal for the provided scope annotation
         *
         * @param value the scope annotation
         * @return a new literal value for the provided scope annotation
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

        public Class<? extends Annotation> annotationType() {
            return BeforeDestroyed.class;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (other instanceof BeforeDestroyed that) {
                return this.value.equals(that.value());
            } else {
                return false;
            }
        }

        public int hashCode() {
            int result = 0;
            result += 127 * "value".hashCode() ^ this.value.hashCode();
            return result;
        }

        public String toString() {
            return "@jakarta.enterprise.context.BeforeDestroyed(" + this.value.getName() + ".class)";
        }
    }

}
