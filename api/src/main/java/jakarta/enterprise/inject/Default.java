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
 * The default qualifier type.
 * </p>
 *
 * <p>
 * If a bean does not explicitly declare a qualifier other than {@link jakarta.inject.Named &#064;Named}, the bean has the
 * qualifier <code>&#064;Default</code>.
 * </p>
 *
 * <p>
 * If an injection point declares no qualifier, the injection point has exactly one qualifier, the default qualifier
 * <code>&#064;Default</code>.
 * </p>
 *
 * <p>
 * The following are equivalent:
 * </p>
 *
 * <pre>
 * &#064;ConversationScoped
 * public class Order {
 *
 *     private Product product;
 *     private User customer;
 *
 *     &#064;Inject
 *     public void init(@Selected Product product, User customer) {
 *         this.product = product;
 *         this.customer = customer;
 *     }
 *
 * }
 * </pre>
 *
 * <pre>
 * &#064;Default
 * &#064;ConversationScoped
 * public class Order {
 *
 *     private Product product;
 *     private User customer;
 *
 *     &#064;Inject
 *     public void init(@Selected Product product, @Default User customer) {
 *         this.product = product;
 *         this.customer = customer;
 *     }
 *
 * }
 * </pre>
 *
 * @author Pete Muir
 * @author Gavin King
 */

@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface Default {

    /**
     * Supports inline instantiation of the {@link Default} qualifier.
     *
     * @author Martin Kouba
     * @since 2.0
     * @see Instance
     * @see Event
     */
    final class Literal extends AnnotationLiteral<Default> implements Default {
        /** The default Default literal */
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

        public Class<? extends Annotation> annotationType() {
            return Default.class;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else {
                return other instanceof Annotation that && Default.class.equals(that.annotationType());
            }
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "@jakarta.enterprise.inject.Default()";
        }
    }
}
