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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.annotation.Priority;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Specifies that a bean is an alternative. May be applied to a bean class, producer method or field or
 * {@linkplain Stereotype stereotype}.
 * </p>
 *
 * <pre>
 * &#064;Alternative
 * &#064;Priority(1)
 * &#064;Dependent
 * public class MockOrder extends Order { ... }
 * </pre>
 *
 * <p>
 * During typesafe resolution, alternative beans take precedence over non-alternative beans.
 * </p>
 *
 * <p>
 * An alternative is not available for injection, lookup or name resolution in a module unless
 * the alternative is <em>selected</em> for the application (by adding the {@link Priority} annotation)
 * or the module is a bean archive and the alternative is <em>selected</em> for that bean archive.
 * </p>
 *
 * <p>
 * By default, a bean archive has no selected alternatives. An alternative must be explicitly declared using the
 * <code>&lt;alternatives&gt;</code> element of the <code>beans.xml</code> file of the bean archive. The
 * <code>&lt;alternatives&gt;</code> element contains a list of bean classes and stereotypes. An alternative
 * is selected for the bean archive if either:
 * </p>
 *
 * <ul>
 * <li>the alternative is a managed bean or session bean and the bean class of the bean is listed,</li>
 * <li>the alternative is a producer method, field or resource, and the bean class that declares the method or field is listed,
 * or</li>
 * <li>any <code>&#064;Alternative</code> stereotype of the alternative is listed.</li>
 * </ul>
 *
 * @author Gavin King
 * @author Pete Muir
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Alternative {

    /**
     * Supports inline instantiation of the {@link Alternative} annotation.
     *
     * @author Martin Kouba
     * @since 2.0
     */
    public final static class Literal extends AnnotationLiteral<Alternative> implements Alternative {
        /** Default Alternative literal */
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

    }

}
