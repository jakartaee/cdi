/*
 * Copyright 2025, Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.context;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * Marks the annotated bean as eagerly initialized. May be applied to a bean class,
 * producer method or field or {@linkplain jakarta.enterprise.inject.Stereotype stereotype}.
 * <p>
 * Only {@link ApplicationScoped @ApplicationScoped} beans may be eagerly initialized.
 * <p>
 * Contextual instance of an eagerly initialized bean is created no later than at the moment
 * the container fires the {@link jakarta.enterprise.event.Startup Startup} event.
 *
 * @since 5.0
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Eager {
    /**
     * Supports inline instantiation of the {@link Eager} annotation.
     */
    final class Literal extends AnnotationLiteral<Eager> implements Eager {
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

        private Literal() {
        }
    }
}
