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

package jakarta.enterprise.context;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Specifies that a bean is application scoped.
 * </p>
 *
 * <p>
 * The application scope is global. Extensions are not allowed to register a custom context for it.
 * </p>
 *
 * <p>
 * An event with qualifier {@code @Initialized(ApplicationScoped.class)} is fired after the application context
 * is initialized. An event with qualifier {@code @BeforeDestroyed(ApplicationScoped.class)} is fired before
 * the application context is destroyed. An event with qualifier {@code @Destroyed(ApplicationScoped.class)} is fired
 * after the application context is destroyed. In all cases, the event payload is:
 * </p>
 *
 * <ul>
 * <li>the <code>ServletContext</code> if the application is a web application deployed to a Servlet container, or</li>
 * <li>any <code>java.lang.Object</code> for other types of application.</li>
 * </ul>
 *
 * @author Gavin King
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 */

@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@NormalScope
@Inherited
public @interface ApplicationScoped {

    /**
     * Supports inline instantiation of the {@link ApplicationScoped} annotation.
     *
     * @author Martin Kouba
     * @since 2.0
     */
    final class Literal extends AnnotationLiteral<ApplicationScoped> implements ApplicationScoped {
        /** Default ApplicationScoped literal */
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

        public Class<? extends Annotation> annotationType() {
            return ApplicationScoped.class;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else {
                return other instanceof Annotation that && ApplicationScoped.class.equals(that.annotationType());
            }
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "@jakarta.enterprise.context.ApplicationScoped()";
        }
    }

}
