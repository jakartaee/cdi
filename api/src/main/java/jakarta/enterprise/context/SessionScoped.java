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

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Specifies that a bean is session scoped.
 * </p>
 * <p>
 * While <code>SessionScoped</code> must be associated with the built-in session context required by the specification,
 * third-party extensions are allowed to also associate it with their own context. Behavior described below is only related to
 * the built-in session context.
 * </p>
 * <p>
 * The session scope is active:
 * </p>
 *
 * <ul>
 * <li>during the <code>service()</code> method of any servlet in the web application,
 * <li>during the doFilter() method of any servlet filter, and</li>
 * <li>when the container calls any <code>HttpSessionListener</code>, <code>AsyncListener</code> or
 * <code>ServletRequestListener</code>.</li>
 * </ul>
 *
 * <p>
 * The session context is shared between all servlet requests that occur in the same HTTP session.
 * </p>
 * <p>
 * The session context is destroyed:
 * </p>
 *
 * <ul>
 * <li>when the HTTPSession times out, after all <code>HttpSessionListeners</code> have been called, or</li>
 * <li>at the very end of any request in which <code>invalidate()</code> was called, after all filters and
 * <code>ServletRequestListeners</code> have been called.</Li>
 * </ul>
 *
 * <p>
 * An event with qualifier <code>@Initialized(SessionScoped.class)</code> is fired when the session context is initialized and
 * an event with qualifier <code>@Destroyed(SessionScoped.class)</code> when the session context is destroyed. The event payload
 * is the <code>HttpSession</code>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for the session scope.
 * </p>
 *
 * @author Gavin King
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 */

@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@NormalScope(passivating = true)
@Inherited
public @interface SessionScoped {

    /**
     * Supports inline instantiation of the {@link SessionScoped} annotation.
     *
     * @author Martin Kouba
     * @since 2.0
     */
    public final static class Literal extends AnnotationLiteral<SessionScoped> implements SessionScoped {
        /** Default SessionScoped literal */
        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

    }

}
