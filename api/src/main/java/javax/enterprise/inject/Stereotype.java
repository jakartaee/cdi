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

package javax.enterprise.inject;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Specifies that an annotation type is a stereotype.
 * </p>
 * 
 * <p>
 * In many systems, use of architectural patterns produces a set of recurring bean roles. A stereotype allows a framework
 * developer to identify such a role and declare some common metadata for beans with that role in a central place.
 * </p>
 * 
 * <p>
 * A bean may declare zero, one or multiple stereotypes, by applying the stereotype annotation to the bean class or producer
 * method or field.
 * </p>
 * 
 * <p>
 * A stereotype encapsulates any combination of:
 * </p>
 * 
 * <ul>
 * <li>a default scope, and</li>
 * <li>a set of interceptor bindings.</li>
 * </ul>
 * 
 * <p>
 * The default scope of a stereotype is defined by annotating the stereotype with a scope type. A stereotype may declare at most
 * one scope. If a bean explicitly declares a scope, any default scopes declared by its stereotypes are ignored.
 * </p>
 * 
 * <pre>
 * &#064;RequestScoped
 * &#064;Stereotype
 * &#064;Target(TYPE)
 * &#064;Retention(RUNTIME)
 * public @interface Action {
 * }
 * </pre>
 * 
 * <p>
 * The interceptor bindings of a stereotype are defined by annotating the stereotype with the interceptor binding types. A
 * stereotype may declare zero, one or multiple interceptor bindings. An interceptor binding declared by a stereotype is
 * inherited by any bean that declares that stereotype.
 * </p>
 * 
 * <pre>
 * &#064;RequestScoped
 * &#064;Secure
 * &#064;Transactional
 * &#064;Stereotype
 * &#064;Target(TYPE)
 * &#064;Retention(RUNTIME)
 * public @interface Action {
 * }
 * </pre>
 * 
 * <p>
 * A stereotype may also specify that:
 * </p>
 * 
 * <ul>
 * <li>all beans with the stereotype have defaulted bean EL names, or that</li>
 * <li>all beans with the stereotype are alternatives.</li>
 * </ul>
 * 
 * <p>
 * A stereotype may declare an empty {@link javax.inject.Named &#064;Named} annotation, which specifies that every bean with the
 * stereotype has a defaulted name when a name is not explicitly specified by the bean.
 * </p>
 * 
 * <pre>
 * &#064;RequestScoped
 * &#064;Named
 * &#064;Secure
 * &#064;Transactional
 * &#064;Stereotype
 * &#064;Target(TYPE)
 * &#064;Retention(RUNTIME)
 * public @interface Action {
 * }
 * </pre>
 * 
 * <p>
 * A stereotype may declare an {@link javax.enterprise.inject.Alternative &#064;Alternative} annotation, which specifies that
 * every bean with the stereotype is an alternative.
 * </p>
 * 
 * <pre>
 * &#064;Alternative
 * &#064;Stereotype
 * &#064;Target(TYPE)
 * &#064;Retention(RUNTIME)
 * public @interface Mock {
 * }
 * </pre>
 * 
 * <p>
 * A stereotype may declare other stereotypes. Stereotype declarations are transitive. A stereotype declared by a second
 * stereotype is inherited by all beans and other stereotypes that declare the second stereotype.
 * </p>
 * 
 * @see javax.enterprise.inject.Model the built-in stereotype <tt>&#064;Model</tt>
 * 
 * @author Pete Muir
 * @author Gavin King
 */

@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
@Documented
public @interface Stereotype {
}
