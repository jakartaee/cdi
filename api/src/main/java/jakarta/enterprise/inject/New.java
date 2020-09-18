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

package jakarta.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;

/**
 * <p>
 * The {@link New} qualifier was deprecated in CDI 1.1. CDI applications are encouraged to inject {@link Dependent} scoped beans
 * instead.
 * </p>
 *
 * <p>
 * The <code>&#064;New</code> qualifier allows the application to obtain a new instance of a bean which is not bound to the declared
 * scope, but has had dependency injection performed.
 * </p>
 *
 * <pre>
 * &#064;Produces &#064;ConversationScoped
 * &#064;Special Order getSpecialOrder(&#064;New(Order.class) Order order) {
 *    ...
 *    return order;
 * }
 * </pre>
 *
 * <p>
 * When the <code>&#064;New</code> qualifier is specified at an injection point and no {@link New#value()
 * value} member is explicitly specified, the container defaults the {@link New#value() value} to the
 * declared type of the injection point. So the following injection point has qualifier <code>&#064;New(Order.class)</code>:
 * </p>
 *
 * <pre>
 * &#064;Produces &#064;ConversationScoped
 * &#064;Special Order getSpecialOrder(&#064;New Order order) { ... }
 * </pre>
 *
 * @author Gavin King
 * @author Pete Muir
 */

@Target({ FIELD, PARAMETER, METHOD, TYPE })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface New {
    /**
     * <p>
     * Specifies the bean class of the new instance. The class must be the bean class of an enabled or disabled bean. The bean
     * class need not be deployed in a bean archive.
     * </p>
     *
     * <p>
     * Defaults to the declared type of the injection point if not specified.
     * </p>
     *
     * @return the bean class of the new instance
     */
    Class<?> value() default New.class;

    /**
     * Supports inline instantiation of the {@link New} qualifier.
     *
     * @author Martin Kouba
     * @since 2.0
     */
    public final static class Literal extends AnnotationLiteral<New> implements New {

        public static final Literal INSTANCE = of(New.class);

        private static final long serialVersionUID = 1L;

        private final Class<?> value;

        public static Literal of(Class<?> value) {
            return new Literal(value);
        }

        private Literal(Class<?> value) {
            this.value = value;
        }

        public Class<?> value() {
            return value;
        }
    }


}
