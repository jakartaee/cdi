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

package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.UnsatisfiedResolutionException;

/**
 * <p>Allows a portable extension to interact directly with the container.
 * Provides operations for obtaining contextual references for beans, along 
 * with many other operations of use to portable extensions.</p>
 * 
 * <p>Any bean may obtain an instance of <tt>BeanManager</tt> by injecting
 * it:</p>
 * 
 * <pre>
 * &#064;Inject BeanManager manager;
 * </pre>
 * 
 * <p>Java EE components may obtain an instance of <tt>BeanManager</tt> from
 * {@linkplain javax.naming JNDI} by looking up the name {@code
 * java:comp/BeanManager}.</p>
 * 
 * <p>Any operation of <tt>BeanManager</tt> may be called at any time during 
 * the execution of the application.</p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author Clint Popetz
 * @author David Allen
 */
public interface BeanManager
{

   /**
    * Obtains a contextual reference for a certain {@linkplain Bean bean} and 
    * a certain bean type of the bean.
    * 
    * @param bean the {@link Bean} object representing the bean
    * @param beanType a bean type that must be implemented by any client proxy 
    *           that is returned
    * @param ctx a {@link javax.enterprise.context.spi.CreationalContext} that 
    *           may be used to destroy any object with scope 
    *           {javax.enterprise.context.Dependent} that is created
    * @return a contextual reference representing the bean
    * @throws IllegalArgumentException if the given type is not a bean type of
    *           the given bean
    */
   public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx);

   /**
    * Obtains an injectable reference for a certain {@linkplain InjectionPoint 
    * injection point}.
    * 
    * @param ij the target injection point
    * @param ctx a {@link javax.enterprise.context.spi.CreationalContext} that 
    *           may be used to destroy any object with scope 
    *           {javax.enterprise.context.Dependent} that is created
    * @return the injectable reference
    * @throws UnsatisfiedResolutionException if typesafe resolution results in 
    *           an unsatisfied dependency
    * @throws AmbiguousResolutionException typesafe resolution results in an 
    *           unresolvable ambiguous dependency
    */
   public Object getInjectableReference(InjectionPoint ij, CreationalContext<?> ctx);

   /**
    * Obtain an instance of a {@link javax.enterprise.context.spi.CreationalContext} 
    * for the given {@linkplain javax.enterprise.context.spi.Contextual contextual type}, 
    * or for a non-contextual object.
    * 
    * @param contextual the {@link javax.enterprise.context.spi.Contextual}, or
    *           a null value in the case of a non-contextual object
    * @return the new {@link javax.enterprise.context.spi.CreationalContext}
    */
   public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual);

   /**
    * Return the set of beans which have the given required type and qualifiers 
    * and are available for injection in the module or library containing the class 
    * into which the <tt>BeanManager</tt> was injected or the Java EE component from 
    * whose JNDI environment namespace the <tt>BeanManager</tt> was obtained, 
    * according to the rules of typesafe resolution. If no qualifiers are given, the 
    * {@linkplain javax.enterprise.inject.Default default qualifier} is assumed.
    * 
    * @param beanType the required bean type
    * @param qualifiers the required qualifiers
    * @return the resulting set of {@linkplain Bean beans}
    * @throws IllegalArgumentException if the given type represents a type
    *            variable
    * @throws IllegalArgumentException if two instances of the same qualifier type
    *            are given
    * @throws IllegalArgumentException if an instance of an annotation that is
    *            not a qualifier type is given
    */
   public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers);

   /**
    * Return the set of beans which have the given EL name and are available for 
    * injection in the module or library containing the class into which the 
    * <tt>BeanManager</tt> was injected or the Java EE component from whose JNDI 
    * environment namespace the <tt>BeanManager</tt> was obtained, according to 
    * the rules of EL name resolution.
    * 
    * @param name the EL name
    * @return the resulting set of {@linkplain Bean beans}
    */
   public Set<Bean<?>> getBeans(String name);

   /**
    * Returns the {@link javax.enterprise.inject.spi.PassivationCapable} bean with 
    * the given identifier.
    * 
    * @param id the identifier
    * @return a {@link Bean} that implements 
    *            {@link javax.enterprise.inject.spi.PassivationCapable}
    *            and has the given identifier, or a null value if there 
    *            is no such bean
    */
   public Bean<?> getPassivationCapableBean(String id);

   /**
    * Apply the ambiguous dependency resolution rules to a set of 
    * {@linkplain Bean beans}.
    * 
    * @param <X> a common type of the beans
    * @param beans a set of {@linkplain Bean beans} of the given type
    * @throws AmbiguousResolutionException if the ambiguous dependency
    *            resolution rules fail
    */
   public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans);

   /**
    * Validate a certain {@linkplain InjectionPoint injection point}.
    * 
    * @param injectionPoint the {@linkplain InjectionPoint injection point} to 
    *            validate
    * @throws InjectionException if there is a deployment problem (for
    *            example, an unsatisfied or unresolvable ambiguous dependency)
    *            associated with the injection point
    */
   public void validate(InjectionPoint injectionPoint);

   /**
    * Fire an event and notify observers.
    * 
    * @param event the event object
    * @param qualifiers the event qualifiers
    * @throws IllegalArgumentException if the runtime type of the event object
    *            contains a type variable
    * @throws IllegalArgumentException if two instances of the same qualifier type
    *            are given
    * @throws IllegalArgumentException if an instance of an annotation that is not 
    * a qualifier type is given
    */
   public void fireEvent(Object event, Annotation... qualifiers);

   /**
    * Return the set of observers for an event.
    * 
    * @param <T> the type of the event
    * @param event the event object
    * @param qualifiers the event qualifiers
    * @throws IllegalArgumentException if the runtime type of the event object 
    *            contains a type variable
    * @throws IllegalArgumentException if two instances of the same qualifier type
    *            are given
    * @throws IllegalArgumentException if an instance of an annotation that is not
    *            a qualifier type is given
    */
   public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T event, Annotation... qualifiers);

   /**
    * Return an ordered list of {@linkplain Decorator decorators} for a set of 
    * bean types and a set of qualifiers and which are enabled in the module or 
    * library containing the class into which the <tt>BeanManager</tt> was
    * injected or the Java EE component from whose JNDI environment namespace 
    * the <tt>BeanManager</tt> was obtained.
    * 
    * @param types the set of bean types of the decorated bean
    * @param qualifiers the qualifiers declared by the decorated bean
    * @return the resulting set of {@linkplain Decorator decorators}
    * @throws IllegalArgumentException if the set of bean types is empty
    * @throws IllegalArgumentException if an annotation which is not a binding
    *            type is passed
    * @throws IllegalArgumentException if two instances of the same binding type
    *            are passed
    */
   public List<Decorator<?>> resolveDecorators(Set<Type> types, Annotation... qualifiers);

   /**
    * Return an ordered list of enabled {@linkplain Interceptor interceptors} for 
    * a set of interceptor bindings and a type of interception and which are enabled 
    * in the module or library containing the class into which the <tt>BeanManager</tt> 
    * was injected or the Java EE component from whose JNDI environment namespace 
    * the <tt>BeanManager</tt> was obtained.
    * 
    * @param type the type of the interception
    * @param interceptorBindings the interceptor bindings
    * @return the resulting set of {@linkplain Interceptor interceptors}
    * @throws IllegalArgumentException if no interceptor binding type is given
    * @throws IllegalArgumentException if two instances of the same interceptor 
    *            binding type are given
    * @throws IllegalArgumentException if an instance of an annotation that is not 
    *            an interceptor binding type is given
    */
   public List<Interceptor<?>> resolveInterceptors(InterceptionType type, Annotation... interceptorBindings);

   /**
    * Test the given annotation type to determine if it is a
    * {@linkplain javax.enterprise.context scope type}.
    * 
    * @param annotationType the annotation type
    * @return true if the annotation type is a 
    *            {@linkplain javax.enterprise.context scope type}
    */
   public boolean isScope(Class<? extends Annotation> annotationType);

   /**
    * Test the given annotation type to determine if it is a 
    * {@linkplain javax.enterprise.context normal scope type}.
    * 
    * @param annotationType the annotation type
    * @return <tt>true</tt> if the annotation type is a
    *         {@linkplain javax.enterprise.context normal scope type}
    */
   public boolean isNormalScope(Class<? extends Annotation> annotationType);

   /**
    * Test the given annotation type to determine if it is a passivating
    * {@linkplain javax.enterprise.context scope type}.
    * 
    * @param annotationType the annotation type
    * @return <tt>true</tt> if the annotation type is a passivating scope type
    */
   public boolean isPassivatingScope(Class<? extends Annotation> annotationType);

   /**
    * Test the given annotation type to determine if it is a
    * {@linkplain javax.inject.Qualifier qualifier type}.
    * 
    * @param annotationType the annotation type
    * @return <tt>true</tt> if the annotation type is a 
    *            {@linkplain javax.inject.Qualifier qualifier type}
    */
   public boolean isQualifier(Class<? extends Annotation> annotationType);

   /**
    * Test the given annotation type to determine if it is an
    * {@linkplain javax.interceptor.InterceptorBinding interceptor binding type}.
    * 
    * @param annotationType the annotation to test
    * @return <tt>true</tt> if the annotation type is a {@linkplain 
    *            javax.interceptor.InterceptorBinding interceptor binding type} 
    */
   public boolean isInterceptorBinding(Class<? extends Annotation> annotationType);

   /**
    * Test the given annotation type to determine if it is a
    * {@linkplain javax.enterprise.inject.Stereotype stereotype}.
    * 
    * @param annotationType the annotation type
    * @return <tt>true</tt> if the annotation type is a
    *         {@linkplain javax.enterprise.inject.Stereotype stereotype}
    */
   public boolean isStereotype(Class<? extends Annotation> annotationType);

   /**
    * Obtains the set of meta-annotations for a certain 
    * {@linkplain javax.interceptor.InterceptorBinding interceptor binding type}.
    * 
    * @param bindingType the
    *           {@linkplain javax.interceptor.InterceptorBinding interceptor binding type}
    * @return the set of meta-annotations
    */
   public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> bindingType);

   /**
    * Obtains meta-annotations for a certain
    * {@linkplain javax.enterprise.inject.Stereotype stereotype}.
    * 
    * @param stereotype the {@linkplain javax.enterprise.inject.Stereotype
    *           stereotype}
    * @return the set of meta-annotations
    */
   public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> stereotype);

   /**
    * Obtains an active {@linkplain javax.enterprise.context.spi.Context
    * context object} for the given {@linkplain javax.enterprise.context scope}.
    * 
    * @param scopeType the {@linkplain javax.enterprise.context scope}
    * @return the {@linkplain javax.enterprise.context.spi.Context context object}
    * @throws ContextNotActiveException if there is no active context object for the
    *            given scope
    * @throws IllegalArgumentException if there is more than one active context object
    *            for the given scope
    */
   public Context getContext(Class<? extends Annotation> scopeType);

   /**
    * Returns a {@link javax.el.ELResolver} that resolves beans by EL name.
    */
   public ELResolver getELResolver();

   /**
    * Returns a wrapper {@link javax.el.ExpressionFactory} that delegates
    * {@link javax.el.MethodExpression} and {@link javax.el.ValueExpression}
    * creation to the given {@link javax.el.ExpressionFactory}. When a Unified
    * EL expression is evaluated using a {@link javax.el.MethodExpression} or
    * {@link javax.el.ValueExpression} returned by the wrapper
    * {@link javax.el.ExpressionFactory}, the container handles destruction of
    * objects with scope {@link javax.enterprise.context.Dependent}.
    * 
    * 
    * @param expressionFactory the {@link javax.el.ExpressionFactory} to wrap
    * @return the wrapped {@link javax.el.ExpressionFactory}
    */
   public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory);

   /**
    * Obtain an {@link AnnotatedType} that may be used to read the annotations 
    * of the given class or interface.
    * 
    * @param <T> the class or interface
    * @param type the {@link java.lang.Class} object
    * @return the {@link AnnotatedType}
    */
   public <T> AnnotatedType<T> createAnnotatedType(Class<T> type);

   /**
    * Obtains an {@link InjectionTarget} for the given {@link AnnotatedType}. 
    * The container ignores the annotations and types declared by the elements 
    * of the actual Java class and uses the metadata provided via the 
    * {@link Annotated} interface instead.
    * 
    * @param <T> the type
    * @param type the {@link AnnotatedType}
    * @returns a container provided implementation of {@link InjectionTarget}
    * @throws IllegalArgumentException if there is a definition error associated
    *            with any injection point of the type
    */
   public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type);

}
