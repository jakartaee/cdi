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
 * Specifies that a bean is application scoped.
 * </p>
 * <p>
 * While <tt>ApplicationScoped</tt> must be associated with the built-in application context required by the specification,
 * third-party extensions are
 * allowed to also associate it with their own context. Behavior described below is only related to the built-in application context.
 * </p>
 *
 * <p>
 * The application scope is active:
 * </p>
 *
 * <ul>
 * <li>during the <tt>service()</tt> method of any servlet in the web application, during the <tt>doFilter()</tt> method of any
 * servlet filter and when the container calls any <tt>ServletContextListener</tt>, <tt>HttpSessionListener</tt>,
 * <tt>AsyncListener</tt> or <tt>ServletRequestListener</tt>,</li>
 * <li>during any Jakarta EE web service invocation,</li>
 * <li>during any remote method invocation of any Jakarta Enterprise Bean, during any asynchronous method invocation of any Jakarta Enterprise Bean, during any call to
 * an Jakarta Enterprise Bean timeout method and during message delivery to any Jakarta Enterprise Bean message-driven bean,</li>
 * <li>when the disposer method or <tt>@PreDestroy</tt> callback of any bean with any normal scope other than
 * <tt>@ApplicationScoped</tt> is called, and</li>
 * <li>during <tt>@PostConstruct</tt> callback of any bean.</li>
 * </ul>
 *
 * <p>
 * The application context is shared between all servlet requests, web service invocations, Jakarta Enterprise Bean
 * remote method invocations, Jakarta Enterprise Bean
 * asynchronous method invocations, Jakarta Enterprise BeanJakarta Enterprise Bean timeouts and message deliveries to message-driven beans that execute within the same
 * application.
 * </p>
 * <p>
 * The application context is destroyed when the application is shut down.
 * </p>
 *
 * <p>
  * An event with qualifier <tt>@Initialized(ApplicationScoped.class)</tt> is fired when the application context is initialized
  * and an event with qualifier <tt>@Destroyed(ApplicationScoped.class)</tt> when the application context is destroyed.
  * The event payload is:
  * </p>
  *
  * <ul>
  * <li>the <tt>ServletContext</tt> if the application is a web application deployed to a Servlet container, or</li>
  * <li>any <tt>java.lang.Object</tt> for other types of application.</li>
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
    public final static class Literal extends AnnotationLiteral<ApplicationScoped> implements ApplicationScoped {

        public static final Literal INSTANCE = new Literal();

        private static final long serialVersionUID = 1L;

    }

}
