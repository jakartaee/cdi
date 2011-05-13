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
/**
 * <p>Annotations and interfaces relating to scopes and contexts.</p>
 * 
 * <p>A scope type is a Java annotation annotated 
 * {@link javax.inject.Scope &#064;Scope} or 
 * {@link javax.enterprise.context.NormalScope &#064;NormalScope}.
 * The scope of a bean determines the lifecycle and visibility of
 * its instances. In particular, the scope determines:</p>
 * 
 * <ul>
 * <li>When a new instance of the bean is created</li>
 * <li>When an existing instance of the bean is destroyed</li>
 * <li>Which injected references refer to any instance of the
 * bean</li>
 * </ul>
 * 
 * <h3>Built-in scopes</h3>
 * 
 * <p>The following built-in scopes are provided:
 * {@link javax.enterprise.context.Dependent &#064;Dependent},
 * {@link javax.enterprise.context.RequestScoped &#064;RequestScoped},
 * {@link javax.enterprise.context.ConversationScoped &#064;ConversationScoped},
 * {@link javax.enterprise.context.SessionScoped &#064;SessionScoped},
 * {@link javax.enterprise.context.ApplicationScoped &#064;ApplicationScoped},
 * {@link javax.inject.Singleton &#064;Singleton}.</p>
 * 
 * <p>The container provides an implementation of the <tt>Context</tt> 
 * interface for each of the built-in scopes. The built-in request, 
 * session, and application contexts support servlet, web service 
 * and EJB invocations. The built-in conversation context supports
 * JSF requests.</p>
 * 
 * <p>For other kinds of invocations, a portable extension may define a 
 * custom {@linkplain javax.enterprise.context.spi.Context context object} 
 * for any or all of the built-in scopes. For example, a third-party web 
 * application framework might provide a conversation context object for 
 * the built-in conversation scope.</p>
 * 
 * <p>The context associated with a built-in scope propagates across 
 * local, synchronous Java method calls, including invocation of EJB 
 * local business methods. The context does not propagate across remote 
 * method invocations or to asynchronous processes such as JMS message 
 * listeners or EJB timer service timeouts.</p>
 * 
 * <h3>Normal scopes and pseudo-scopes</h3>
 * 
 * <p>Most scopes are <em>normal scopes</em>. Normal scopes are declared 
 * using {@link javax.enterprise.context.NormalScope &#064;NormalScope}. 
 * If a bean has a normal scope, every client executing in a certain 
 * thread sees the same contextual instance of the bean. This instance is 
 * called the <em>current instance</em> of the bean. The operation 
 * {@link javax.enterprise.context.spi.Context#get(Contextual)} of the 
 * context object for a normal scope type always returns the current 
 * instance of the given bean.</p> 
 *
 * <p>Any scope that is not a normal scope is called a <em>pseudo-scope</em>.
 * Pseudo-scopes are declared using {@link javax.inject.Scope &#064;Scope}. 
 * The concept of a current instance is not well-defined in the case of 
 * a pseudo-scope. Different clients executing in the same thread may
 * see different instances of the bean. In the extreme case of the
 * {@link javax.enterprise.context.Dependent &#064;Dependent} pseudo-scope,
 * every client has its own private instance of the bean.</p> 
 * 
 * <p>All built-in scopes are normal scopes, except for the 
 * {@link javax.enterprise.context.Dependent &#064;Dependent} and
 * {@link javax.inject.Singleton &#064;Singleton} pseudo-scopes.</p>
 * 
 * <h3>Contextual and injected reference validity</h3>
 * 
 * <p>A reference to a bean obtained from the container via {@linkplain 
 * javax.enterprise.inject.Instance programmatic lookup} is called a 
 * contextual reference. A contextual reference for a bean with a normal 
 * scope refers to the current instance of the bean. A contextual 
 * reference for a bean are valid only for a certain period of time. The 
 * application should not invoke a method of an invalid reference.</p>
 * 
 * <p>The validity of a contextual reference for a bean depends upon 
 * whether the scope of the bean is a normal scope or a pseudo-scope:</p>
 * 
 * <ul>
 * <li>Any reference to a bean with a normal scope is valid as long as 
 * the application maintains a hard reference to it. However, it may 
 * only be invoked when the context associated with the normal scope is 
 * active. If it is invoked when the context is inactive, a 
 * {@link javax.enterprise.context.ContextNotActiveException} is thrown 
 * by the container.</li>
 * <li>Any reference to a bean with a pseudo-scope is valid until the 
 * bean instance to which it refers is destroyed. It may be invoked 
 * even if the context associated with the pseudo-scope is not active. 
 * If the application invokes a method of a reference to an instance 
 * that has already been destroyed, the behavior is undefined.</li>
 * </ul>
 * 
 * <p>A reference to a bean obtained from the container via {@linkplain 
 * javax.inject.Inject dependency injection} is a special kind of
 * contextual reference, called an injected reference. Additional
 * restrictions apply to the validity of an injected reference:</p>
 * 
 * <ul>
 * <li>A reference to a bean injected into a field, bean constructor or 
 * initializer method is only valid until the object into which it was 
 * injected is destroyed.</li>
 * <li>A reference to a bean injected into a producer method is only 
 * valid until the producer method bean instance that is being produced 
 * is destroyed.<li>
 * <li>A reference to a bean injected into a disposer method or observer 
 * method is only valid until the invocation of the method completes.</li>
 * </ul>
 * 
 * @see javax.enterprise.inject
 * 
 */
package javax.enterprise.context;

