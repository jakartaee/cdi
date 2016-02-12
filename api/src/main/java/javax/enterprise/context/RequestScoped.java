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

package javax.enterprise.context;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Specifies that a bean is request scoped.
 * </p>
 * <p>
 * While <tt>RequestScoped</tt> must be associated with the built-in request context required by the specification,
 * third-party extensions are
 * allowed to also associate it with their own context. Behavior described below is only related to the built-in request context.
 * </p>
 *
 * <p>
 * The request scope is active:
 * </p>
 *
 * <ul>
 * <li>during the <tt>service()</tt> method of any servlet in the web application, during the <tt>doFilter()</tt> method of any
 * servlet filter and when the container calls any <tt>ServletRequestListener</tt> or <tt>AsyncListener</tt>,</li>
 * <li>during any Java EE web service invocation,</li>
 * <li>during any remote method invocation of any EJB, during any asynchronous method invocation of any EJB, during any call to
 * an EJB timeout method and during message delivery to any EJB message-driven bean, and</li>
 * <li>during <tt>@PostConstruct</tt> callback of any bean.</li>
 * </ul>
 *
 * <p>
 * The request context is destroyed:
 * </p>
 *
 * <ul>
 * <li>at the end of the servlet request, after the <tt>service()</tt> method, all <tt>doFilter()</tt> methods, and all
 * <tt>requestDestroyed()</tt> and <tt>onComplete()</tt> notifications return,</li>
 * <li>after the web service invocation completes,</li>
 * <li>after the EJB remote method invocation, asynchronous method invocation, timeout or message delivery completes if it
 * did not already exist when the invocation occurred, or</li>
 * <li>after the <tt>@PostConstruct</tt> callback completes, if it did not already exist when the <tt>@PostConstruct</tt>
 * callback occurred.</li>
 * </ul>
 *
 * <p>
 * An event with qualifier <tt>@Initialized(RequestScoped.class)</tt> is fired when the request context is initialized and an
 * event
 * with qualifier <tt>@Destroyed(RequestScoped.class)</tt> when the request context is destroyed. The event payload is:
 * </p>
 *
 * <ul>
 * <li>the <tt>ServletRequest</tt> if the context is initialized or destroyed due to a servlet request, or</li>
 * <li>the <tt>ServletRequest</tt> if the context is initialized or destroyed due to a web service invocation, or</li>
 * <li>any <tt>java.lang.Object</tt> for other types of request.</li>
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
public @interface RequestScoped {

    /**
     * Supports inline instantiation of the {@link RequestScoped} annotation.
     *
     * @author Martin Kouba
     * @since 2.0
     */
    public final static class Literal extends AnnotationLiteral<RequestScoped> implements RequestScoped {

        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

    }

}
