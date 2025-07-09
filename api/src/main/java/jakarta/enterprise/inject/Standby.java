/*
 * Copyright 2025, Contributors to the Eclipse Foundation
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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.annotation.Priority;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Specifies that a bean is a standby. May be applied to a bean class, producer method or field or
 * {@linkplain Stereotype stereotype}.
 * </p>
 *
 * <pre>
 * &#064;Standby
 * &#064;Priority(1)
 * &#064;Dependent
 * public class DefaultOrder extends Order { ... }
 * </pre>
 *
 * <p>
 * A standby is not available for injection, lookup or name resolution in a module unless the module
 * is a bean archive and the standby is explicitly <em>selected</em> for the application (by adding
 * the {@link Priority} annotation). A standby is never available for injection, lookup
 * or name resolution in a module that is not a bean archive.
 * </p>
 *
 * <p>
 * Unlike {@linkplain Alternative alternatives}, standbys cannot be selected for a bean archive in {@code beans.xml}.
 * </p>
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Standby {
    /**
     * Supports inline instantiation of the {@link Standby} annotation.
     */
    final class Literal extends AnnotationLiteral<Standby> implements Standby {
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;
    }
}
