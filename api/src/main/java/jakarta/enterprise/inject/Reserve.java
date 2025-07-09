/*
 * Copyright 2025, Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.annotation.Priority;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Specifies that a bean is a reserve. May be applied to a bean class, producer method or field or
 * {@linkplain Stereotype stereotype}.
 * </p>
 *
 * <pre>
 * &#064;Reserve
 * &#064;Priority(1)
 * &#064;Dependent
 * public class DefaultOrder extends Order { ... }
 * </pre>
 *
 * <p>
 * During typesafe resolution, non-reserve beans take precedence over reserve beans.
 * </p>
 *
 * <p>
 * A reserve is not available for injection, lookup or name resolution in a module unless
 * the reserve is <em>selected</em> for the application (by adding the {@link Priority} annotation).
 * </p>
 *
 * <p>
 * Unlike {@linkplain Alternative alternatives}, reserves cannot be selected for a bean archive in {@code beans.xml}.
 * </p>
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Reserve {
    /**
     * Supports inline instantiation of the {@link Reserve} annotation.
     */
    final class Literal extends AnnotationLiteral<Reserve> implements Reserve {
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;
    }
}
