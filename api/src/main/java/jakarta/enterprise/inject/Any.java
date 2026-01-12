/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.event.Event;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;

/**
 * <p>
 * The built-in qualifier type.
 * </p>
 *
 * <p>
 * Every bean has the qualifier <code>&#064;Any</code>, even if it does not explicitly declare this qualifier.
 * </p>
 *
 * <p>
 * Every event has the qualifier <code>&#064;Any</code>, even if it was raised without explicitly declaration of this qualifier.
 * </p>
 *
 * <p>
 * The <code>&#064;Any</code> qualifier allows an injection point to refer to all beans or all events of a certain bean type.
 * </p>
 *
 * <pre>
 * &#064;Inject
 * &#064;Any
 * Instance&lt;PaymentProcessor&gt; anyPaymentProcessor;
 * </pre>
 *
 * <pre>
 * &#064;Inject
 * &#064;Any
 * Event&lt;User&gt; anyUserEvent;
 * </pre>
 *
 * <pre>
 * &#064;Inject
 * &#064;Delegate
 * &#064;Any
 * Logger logger;
 * </pre>
 *
 * @author Gavin King
 * @author David Allen
 */

@Qualifier
@Retention(RUNTIME)
@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Documented
public @interface Any {

    /**
     * Supports inline instantiation of the {@link Any} qualifier.
     *
     * @author Martin Kouba
     * @since 2.0
     * @see Instance
     * @see Event
     */
    final class Literal extends AnnotationLiteral<Any> implements Any {
        /** Default Any literal */
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

        public Class<? extends Annotation> annotationType() {
            return Any.class;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else {
                return other instanceof Annotation that && Any.class.equals(that.annotationType());
            }
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "@jakarta.enterprise.inject.Any()";
        }
    }

}
