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

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * Marks the annotated bean as auto-closeable. May be applied to a bean class,
 * producer method or field or {@linkplain jakarta.enterprise.inject.Stereotype stereotype}.
 * <p>
 * For an auto-closeable managed bean, the bean class must be assignable to {@link AutoCloseable}.
 * For an auto-closeable producer method, the return type of the method must be assignable to
 * {@link AutoCloseable}.
 * For an auto-closeable producer field, the type of the field must be assignable to
 * {@link AutoCloseable}.
 * These requirements apply regardless of whether the bean is auto-closeable due to a direct
 * {@code @AutoClose} annotation or due to a {@linkplain jakarta.enterprise.inject.Stereotype stereotype}.
 * If these requirements are not satisfied, the container automatically detects
 * the problem and treats it as a definition error.
 * <p>
 * During bean destruction, the {@link AutoCloseable#close()} method is called on
 * the contextual instance.
 * Note that bean destruction ({@link Contextual#destroy(Object, CreationalContext)})
 * may not throw, so exceptions thrown by {@link AutoCloseable#close()} are swallowed.
 *
 * @since 5.0
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoClose {
    /**
     * Supports inline instantiation of the {@link AutoClose} annotation.
     */
    final class Literal extends AnnotationLiteral<AutoClose> implements AutoClose {
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

        public Class<? extends Annotation> annotationType() {
            return AutoClose.class;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else {
                return other instanceof Annotation that && AutoClose.class.equals(that.annotationType());
            }
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "@jakarta.enterprise.context.AutoClose()";
        }
    }
}
