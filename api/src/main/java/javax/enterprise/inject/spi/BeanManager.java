/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, 2013 Red Hat, Inc., and individual contributors
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

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObserverException;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.enterprise.util.Nonbinding;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Allows a portable extension to interact directly with the container. Provides operations for obtaining contextual references
 * for beans, along with many other operations of use to portable extensions.
 * </p>
 * 
 * <p>
 * Any bean may obtain an instance of <tt>BeanManager</tt> by injecting it:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * BeanManager manager;
 * </pre>
 * 
 * <p>
 * Jakarta EE components may obtain an instance of <tt>BeanManager</tt> from {@linkplain javax.naming JNDI} by looking up the name
 * {@code java:comp/BeanManager}.
 * </p>
 * 
 * <p>
 * Most operations of BeanManager may be called at any time during the execution of the application.
 * </p>
 * <p>    
 * However, the following  operations must not be called before the {@link AfterBeanDiscovery} event is fired:
 * </p>
 * <ul>
 *     <li>{@link #getBeans(String)},</li>
 *     <li>{@link #getBeans(java.lang.reflect.Type, java.lang.annotation.Annotation...)},</li>
 *     <li>{@link #getPassivationCapableBean(String)},</li>
 *     <li>{@link #resolve(java.util.Set)},</li>
 *     <li>{@link #resolveDecorators(java.util.Set, java.lang.annotation.Annotation...)},</li>
 *     <li>{@link #resolveInterceptors(InterceptionType, java.lang.annotation.Annotation...)},</li>
 *     <li>{@link #resolveObserverMethods(Object, java.lang.annotation.Annotation...)},</li>
 *     <li>{@link #validate(InjectionPoint)},</li>
 *     <li>{@link #createInstance()}</li>
 * </ul>
 * <p>
 * and the following operations must not be called before the {@link AfterDeploymentValidation} event is fired:
 * </p>
 * <ul>
 *     <li>{@link #getReference(Bean, java.lang.reflect.Type, javax.enterprise.context.spi.CreationalContext)},</li>
 *     <li>{@link #getInjectableReference(InjectionPoint, javax.enterprise.context.spi.CreationalContext)},</li>
 * </ul>
 * <p>
 * or the container will throw an Exception.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * @author Clint Popetz
 * @author David Allen
 * @author Antoine Sabot-Durand
 */
public interface BeanManager {


    /**
     * <p>
     * Obtains a contextual reference for a certain {@linkplain Bean bean} and a certain bean type of the bean.
     * </p>
     * 
     * @param bean the {@link Bean} object representing the bean
     * @param beanType a bean type that must be implemented by any client proxy that is returned
     * @param ctx a {@link javax.enterprise.context.spi.CreationalContext} that may be used to destroy any object with scope
     *        {@link javax.enterprise.context.Dependent} that is created
     * @return a contextual reference representing the bean
     * @throws IllegalArgumentException if the given type is not a bean type of the given bean
     * @throws IllegalStateException if called during application initialization, before the {@link AfterDeploymentValidation}
     *         event is fired.
     */
    public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx);

    /**
     * <p>
     * Obtains an injectable reference for a certain {@linkplain InjectionPoint injection point}.
     * </p>
     * 
     * @param ij the target injection point
     * @param ctx a {@link javax.enterprise.context.spi.CreationalContext} that may be used to destroy any object with scope
     *        {@link javax.enterprise.context.Dependent} that is created
     * @return the injectable reference
     * @throws UnsatisfiedResolutionException if typesafe resolution results in an unsatisfied dependency
     * @throws AmbiguousResolutionException typesafe resolution results in an unresolvable ambiguous dependency
     * @throws IllegalStateException if called during application initialization, before the {@link AfterDeploymentValidation}
     *         event is fired.
     */
    public Object getInjectableReference(InjectionPoint ij, CreationalContext<?> ctx);

    /**
     * Obtain an instance of a {@link javax.enterprise.context.spi.CreationalContext} for the given
     * {@linkplain javax.enterprise.context.spi.Contextual contextual type}, or for a non-contextual object.
     * 
     * @param contextual the {@link javax.enterprise.context.spi.Contextual}, or a null value in the case of a non-contextual
     *        object
     * @return the new {@link javax.enterprise.context.spi.CreationalContext}
     */
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual);

    /**
     * Return the set of beans which have the given required type and qualifiers and are available for injection in the module
     * or library containing the class into which the <tt>BeanManager</tt> was injected or the Jakarta EE component from whose JNDI
     * environment namespace the <tt>BeanManager</tt> was obtained, according to the rules of typesafe resolution. If no
     * qualifiers are given, the {@linkplain javax.enterprise.inject.Default default qualifier} is assumed.
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     *
     * @param beanType the required bean type
     * @param qualifiers the required qualifiers
     * @return the resulting set of {@linkplain Bean beans}
     * @throws IllegalArgumentException if the given type represents a type variable
     * @throws IllegalArgumentException if two instances of the same non repeating qualifier type are given
     * @throws IllegalArgumentException if an instance of an annotation that is not a qualifier type is given
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers);

    /**
     * Return the set of beans which have the given EL name and are available for injection in the module or library containing
     * the class into which the <tt>BeanManager</tt> was injected or the Jakarta EE component from whose JNDI environment namespace
     * the <tt>BeanManager</tt> was obtained, according to the rules of EL name resolution.
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     * 
     * @param name the EL name
     * @return the resulting set of {@linkplain Bean beans}
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public Set<Bean<?>> getBeans(String name);

    /**
     * Returns the {@link javax.enterprise.inject.spi.PassivationCapable} bean with the given identifier.
     *
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     * 
     * @param id the identifier
     * @return a {@link Bean} that implements {@link javax.enterprise.inject.spi.PassivationCapable} and has the given
     *         identifier, or a null value if there is no such bean
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public Bean<?> getPassivationCapableBean(String id);

    /**
     * Apply the ambiguous dependency resolution rules to a set of {@linkplain Bean beans}.
     *
     * <br>
     Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     * 
     * @param <X> a common type of the beans
     * @param beans a set of {@linkplain Bean beans} of the given type
     * @return the resolved bean, or null if null or an empty set is passed
     * @throws AmbiguousResolutionException if the ambiguous dependency resolution rules fail
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans);

    /**
     * Validate a certain {@linkplain InjectionPoint injection point}.
     *
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only validate injection points discovered by the container before the {@link AfterBeanDiscovery} 
     * event is fired.
     * 
     * @param injectionPoint the {@linkplain InjectionPoint injection point} to validate
     * @throws InjectionException if there is a deployment problem (for example, an unsatisfied or unresolvable ambiguous
     *         dependency) associated with the injection point
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public void validate(InjectionPoint injectionPoint);

    /**
     * Fire an event and notify observers.
     *
     * <p>
     * This method is deprecated from CDI 2.0 and {@link #getEvent()} should be used instead.
     * </p>
     *
     * @param event the event object
     * @param qualifiers the event qualifiers
     * @throws IllegalArgumentException if the runtime type of the event object contains a type variable
     * @throws IllegalArgumentException if two instances of the same non repeating qualifier type are given
     * @throws IllegalArgumentException if an instance of an annotation that is not a qualifier type is given
     * @throws IllegalArgumentException if the runtime type of the event object is assignable to the type of a container
     *         lifecycle event
     * @throws ObserverException if a notified observer throws a checked exception, it will be wrapped and rethrown as an
     *         (unchecked) {@link ObserverException}
     */
    public void fireEvent(Object event, Annotation... qualifiers);

    /**
     * Return an ordered set of {@linkplain ObserverMethod observer methods} for an event.
     *
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return observers discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     * 
     * @param <T> the type of the event
     * @param event the event object
     * @param qualifiers the event qualifiers
     * @return the resulting set of {@linkplain ObserverMethod observer methods}
     * @throws IllegalArgumentException if the runtime type of the event object contains a type variable
     * @throws IllegalArgumentException if two instances of the same non repeating qualifier type are given
     * @throws IllegalArgumentException if an instance of an annotation that is not a qualifier type is given
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T event, Annotation... qualifiers);

    /**
     * Return an ordered list of {@linkplain Decorator decorators} for a set of bean types and a set of qualifiers and which are
     * enabled in the module or library containing the class into which the <tt>BeanManager</tt> was injected or the Jakarta EE
     * component from whose JNDI environment namespace the <tt>BeanManager</tt> was obtained.
     *
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return decorators discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     * 
     * @param types the set of bean types of the decorated bean
     * @param qualifiers the qualifiers declared by the decorated bean
     * @return the resulting set of {@linkplain Decorator decorators}
     * @throws IllegalArgumentException if the set of bean types is empty
     * @throws IllegalArgumentException if an annotation which is not a binding type is passed
     * @throws IllegalArgumentException if two instances of the same binding type are passed
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public List<Decorator<?>> resolveDecorators(Set<Type> types, Annotation... qualifiers);

    /**
     * Return an ordered list of enabled {@linkplain Interceptor interceptors} for a set of interceptor bindings and a type of
     * interception and which are enabled in the module or library containing the class into which the <tt>BeanManager</tt> was
     * injected or the Jakarta EE component from whose JNDI environment namespace the <tt>BeanManager</tt> was obtained.
     *
     * <br>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer, 
     * this method will only return interceptors discovered by the container before the {@link AfterBeanDiscovery} event is 
     * fired.
     * 
     * @param type the type of the interception
     * @param interceptorBindings the interceptor bindings
     * @return the resulting set of {@linkplain Interceptor interceptors}
     * @throws IllegalArgumentException if no interceptor binding type is given
     * @throws IllegalArgumentException if two instances of the same interceptor binding type are given
     * @throws IllegalArgumentException if an instance of an annotation that is not an interceptor binding type is given
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    public List<Interceptor<?>> resolveInterceptors(InterceptionType type, Annotation... interceptorBindings);

    /**
     * Test the given annotation type to determine if it is a {@linkplain javax.enterprise.context scope type}.
     * 
     * @param annotationType the annotation type
     * @return true if the annotation type is a {@linkplain javax.enterprise.context scope type}
     */
    public boolean isScope(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a {@linkplain javax.enterprise.context normal scope type}.
     * 
     * @param annotationType the annotation type
     * @return <tt>true</tt> if the annotation type is a {@linkplain javax.enterprise.context normal scope type}
     */
    public boolean isNormalScope(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a passivating {@linkplain javax.enterprise.context scope type}.
     * 
     * @param annotationType the annotation type
     * @return <tt>true</tt> if the annotation type is a passivating scope type
     */
    public boolean isPassivatingScope(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a {@linkplain javax.inject.Qualifier qualifier type}.
     * 
     * @param annotationType the annotation type
     * @return <tt>true</tt> if the annotation type is a {@linkplain javax.inject.Qualifier qualifier type}
     */
    public boolean isQualifier(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is an {@linkplain javax.interceptor.InterceptorBinding interceptor
     * binding type} .
     * 
     * @param annotationType the annotation to test
     * @return <tt>true</tt> if the annotation type is a {@linkplain javax.interceptor.InterceptorBinding interceptor binding
     *         type}
     */
    public boolean isInterceptorBinding(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a {@linkplain javax.enterprise.inject.Stereotype stereotype}.
     * 
     * @param annotationType the annotation type
     * @return <tt>true</tt> if the annotation type is a {@linkplain javax.enterprise.inject.Stereotype stereotype}
     */
    public boolean isStereotype(Class<? extends Annotation> annotationType);

    /**
     * Obtains the set of meta-annotations for a certain {@linkplain javax.interceptor.InterceptorBinding interceptor binding
     * type} .
     * 
     * @param bindingType the {@linkplain javax.interceptor.InterceptorBinding interceptor binding type}
     * @return the set of meta-annotations
     */
    public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> bindingType);

    /**
     * Obtains meta-annotations for a certain {@linkplain javax.enterprise.inject.Stereotype stereotype}.
     * 
     * @param stereotype the {@linkplain javax.enterprise.inject.Stereotype stereotype}
     * @return the set of meta-annotations
     */
    public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> stereotype);

    /**
     * Determine if two qualifiers are considered equivalent for the purposes of typesafe resolution, taking into account any
     * members annotated with {@link Nonbinding}.
     * 
     * @param qualifier1 a qualifier to check
     * @param qualifier2 a qualifier to check
     * @return true if the two qualifiers are equivalent, otherwise false
     * @since 1.1
     */
    public boolean areQualifiersEquivalent(Annotation qualifier1, Annotation qualifier2);

    /**
     * Determine if two interceptor bindings are considered equivalent for the purposes of typesafe resolution, taking into
     * account any members annotated with {@link Nonbinding}.
     * 
     * @param interceptorBinding1 an interceptor binding to check
     * @param interceptorBinding2 an interceptor binding to check
     * @return true if the two interceptor bindings are equivalent, otherwise false
     * @since 1.1
     */
    public boolean areInterceptorBindingsEquivalent(Annotation interceptorBinding1, Annotation interceptorBinding2);

    /**
     * Determine the hash code of a qualifier, using the JDK algorithm for determining an annotation hash code, ignoring any
     * members annotated with {@link Nonbinding}.
     * 
     * @param qualifier the qualifier to consider
     * @return the hashCode for the qualifier
     * @since 1.1
     */
    public int getQualifierHashCode(Annotation qualifier);

    /**
     * Determine the hash code of an interceptor binding, using the JDK algorithm for determining an annotation hash code,
     * ignoring any members annotated with {@link Nonbinding}.
     * 
     * @param interceptorBinding the interceptor binding to consider
     * @return the hashCode for the interceptor binding
     * @since 1.1
     */
    public int getInterceptorBindingHashCode(Annotation interceptorBinding);

    /**
     * Obtains an active {@linkplain javax.enterprise.context.spi.Context context object} for the given
     * {@linkplain javax.enterprise.context scope} .
     * 
     * @param scopeType the {@linkplain javax.enterprise.context scope}
     * @return the {@linkplain javax.enterprise.context.spi.Context context object}
     * @throws ContextNotActiveException if there is no active context object for the given scope
     * @throws IllegalArgumentException if there is more than one active context object for the given scope
     */
    public Context getContext(Class<? extends Annotation> scopeType);

    /**
     * Returns a {@link javax.el.ELResolver} that resolves beans by EL name.
     * 
     * @return the {@link javax.el.ELResolver}
     */
    public ELResolver getELResolver();

    /**
     * Returns a wrapper {@link javax.el.ExpressionFactory} that delegates {@link javax.el.MethodExpression} and
     * {@link javax.el.ValueExpression} creation to the given {@link javax.el.ExpressionFactory}. When a Unified EL expression
     * is evaluated using a {@link javax.el.MethodExpression} or {@link javax.el.ValueExpression} returned by the wrapper
     * {@link javax.el.ExpressionFactory}, the container handles destruction of objects with scope
     * {@link javax.enterprise.context.Dependent}.
     * 
     * 
     * @param expressionFactory the {@link javax.el.ExpressionFactory} to wrap
     * @return the wrapped {@link javax.el.ExpressionFactory}
     */
    public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory);

    /**
     * Obtain an {@link AnnotatedType} that may be used to read the annotations of the given class or interface.
     * 
     * @param <T> the class or interface
     * @param type the {@link java.lang.Class} object
     * @return the {@link AnnotatedType}
     */
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> type);

    /**
     * <p>
     * Obtains an {@link InjectionTarget} for the given {@link AnnotatedType}. The container ignores the annotations and types
     * declared by the elements of the actual Java class and uses the metadata provided via the {@link Annotated} interface
     * instead.
     * </p>
     * 
     * <p>
     * This method is deprecated from CDI 1.1 and {@link #getInjectionTargetFactory(AnnotatedType)} should be used instead.
     * </p>
     * 
     * @param <T> the type
     * @param type the {@link AnnotatedType}
     * @return a container provided implementation of {@link InjectionTarget}
     * @throws IllegalArgumentException if there is a definition error associated with any injection point of the type
     */
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type);

    /**
     * <p>
     * An implementation of {@link InjectionTargetFactory} that provides container created {@link InjectionTarget} instances.
     * </p>
     * 
     * <p>
     * This factory can be wrapped to add behavior to container created injection targets.
     * </p>
     * 
     * @param annotatedType the annotated type to create the injection target factory for
     * @return an {@link InjectionTargetFactory}
     * @since 1.1
     */
    public <T> InjectionTargetFactory<T> getInjectionTargetFactory(AnnotatedType<T> annotatedType);

    /**
     * <p>
     * An implementation of {@link ProducerFactory} that provides container created {@link Producer} instances for the given
     * field.
     * </p>
     * 
     * <p>
     * This factory can be wrapped to add behavior to container created producers.
     * </p>
     * 
     * @param field the field to create the producer factory for
     * @param declaringBean the bean declaring the producer. May be null if the producer is static or the declaring object is
     *        non-contextual
     * @return the producer factory for the field
     * @since 1.1
     */
    public <X> ProducerFactory<X> getProducerFactory(AnnotatedField<? super X> field, Bean<X> declaringBean);

    /**
     * <p>
     * An implementation of {@link ProducerFactory} that provides container created {@link Producer} instances for the given
     * method.
     * </p>
     * 
     * <p>
     * This factory can be wrapped to add behavior to container created producers.
     * </p>
     * 
     * @param method the method to create the producer factory for
     * @param declaringBean the bean declaring the producer. May be null if the producer is static or the declaring object is
     *        non-contextual
     * @return the producer factory for the method
     * @since 1.1
     */
    public <X> ProducerFactory<X> getProducerFactory(AnnotatedMethod<? super X> method, Bean<X> declaringBean);

    /**
     * Obtains a {@link BeanAttributes} for the given {@link AnnotatedType}. The container ignores the annotations and types
     * declared by the elements of the actual Java class and uses the metadata provided via the {@link Annotated} interface
     * instead.
     * 
     * @param <T> the type
     * @param type the {@link AnnotatedType}
     * @return a container provided implementation of {@link InjectionTarget}
     * @since 1.1
     */
    public <T> BeanAttributes<T> createBeanAttributes(AnnotatedType<T> type);

    /**
     * Obtains a {@link BeanAttributes} for the given {@link AnnotatedType}. The container ignores the annotations and types
     * declared by the elements of the actual Java class and uses the metadata provided via the {@link Annotated} interface
     * instead.
     * 
     * @param type the {@link AnnotatedType}
     * @return a container provided implementation of {@link InjectionTarget}
     * @since 1.1
     */
    public BeanAttributes<?> createBeanAttributes(AnnotatedMember<?> type);

    /**
     * <p>
     * Obtains a {@link Bean} for the given {@link BeanAttributes}, bean class and {@link InjectionTarget}.
     * </p>
     * 
     * <p>
     * The {@link InjectionTarget} creates and destroys instances of the bean, performs dependency injection and lifecycle
     * callbacks, and determines the return value of {@link Bean#getInjectionPoints()}. The {@link InjectionTarget} is obtained
     * from the {@link InjectionTargetFactory}. {@link #getInjectionTargetFactory(AnnotatedType)} allows use of a container created
     * {@link InjectionTarget}.
     * </p>
     * 
     * @param <T> the type
     * @param attributes a {@link BeanAttributes} which determines the bean types, qualifiers, scope, name and stereotypes of
     *        the returned {@link Bean}, and the return values of {@link Bean#isAlternative()} and {@link Bean#isNullable()}
     * @param beanClass a class, which determines the return value of {@link Bean#getBeanClass()}
     * @param injectionTargetFactory an {@link InjectionTargetFactory}, used to obtain an {@link InjectionTarget}
     * @return a container provided implementation of {@link Bean}
     * @since 1.1
     */
    public <T> Bean<T> createBean(BeanAttributes<T> attributes, Class<T> beanClass,
            InjectionTargetFactory<T> injectionTargetFactory);

    /**
     * <p>
     * Obtains a {@link Bean} for the given {@link BeanAttributes}, bean class and {@link Producer}.
     * </p>
     * 
     * <p>
     * The {@link Producer} creates and destroys instances of the decorator, and determines the return value of
     * {@link Bean#getInjectionPoints()}. The {@link Producer} is obtained from the {@link ProducerFactory}.
     * {@link #getProducerFactory(AnnotatedMethod, Bean)} or {@link #getProducerFactory(AnnotatedField, Bean)} allows use of a 
     * container created {@link Producer}.
     * </p>
     * 
     * @param <T> the type
     * @param <X> the type of the declaring bean
     * @param attributes a {@link BeanAttributes} which determines the bean types, qualifiers, scope, name and stereotypes of
     *        the returned {@link Bean}, and the return values of {@link Bean#isAlternative()} and {@link Bean#isNullable()}
     * @param beanClass a class, which determines the return value of <tt>Bean.getClass()</tt>
     * @param producerFactory a {@link ProducerFactory}, used to obtain a {@link Producer}
     * @return a container provided implementation of {@link Bean}
     * @since 1.1
     */
    public <T, X> Bean<T> createBean(BeanAttributes<T> attributes, Class<X> beanClass, ProducerFactory<X> producerFactory);

    /**
     * Obtains a container provided implementation of {@link InjectionPoint} for the given {@link AnnotatedField}.
     * 
     * @param field the {@link AnnotatedField} defining the injection point
     * @return the container provided {@link InjectionPoint}
     * @throws IllegalArgumentException if there is a definition error associated with the injection point
     * @since 1.1
     */
    public InjectionPoint createInjectionPoint(AnnotatedField<?> field);

    /**
     * Obtains a container provided implementation of {@link InjectionPoint} for the given {@link AnnotatedParameter}.
     * 
     * @param parameter the {@link AnnotatedParameter} defining the injection point
     * @return the container provided {@link InjectionPoint}
     * @throws IllegalArgumentException if there is a definition error associated with the injection point
     * @since 1.1
     */
    public InjectionPoint createInjectionPoint(AnnotatedParameter<?> parameter);

    /**
     * Obtains the container's instance of an Extension class declared in <code>META-INF/services</code>.
     * 
     * @param <T> the type of the extension
     * @param extensionClass the type of the extension class
     * @return the extension instance
     * @throws IllegalArgumentException if the container has no instance of the given class
     * @since 1.1
     */
    public <T extends Extension> T getExtension(Class<T> extensionClass);

    /**
     *
     * Create an {@link InterceptionFactory} for the given {@link CreationalContext} and type.
     *
     * @param ctx {@link CreationalContext} for the {@link InterceptionFactory} to create
     * @param clazz class of the instance this factory will work on
     * @param <T> type of the instance this factory will work on
     * @return a new {@link InterceptionFactory} to add services on on instances of T
     * @since 2.0
     */
    <T> InterceptionFactory<T> createInterceptionFactory(CreationalContext<T> ctx, Class<T> clazz);


    /**
     *
     * Returns an instance of Event with specified type <tt>java.lang.Object</tt> and specified qualifier <tt>@Default</tt>
     * It allows typesafe synchronous or asynchronous event firing without injection of {@link Event} built-in bean requirement.
     *
     * @return a new {@link Event} object whose event type is <tt>Object</tt> and qualifier <tt>@Default</tt>
     * @since 2.0
     */
    Event<Object> getEvent();


    /**
     *
     * Obtains an {@link Instance} object to access to beans instances.
     *
     * The returned <tt>Instance</tt> object can only access instances of  beans that are available for injection in the module
     * or library containing the class into which the <tt>BeanManager</tt> was injected or the Jakarta EE component from whose JNDI
     * environment namespace the <tt>BeanManager</tt> was obtained, according to the rules of typesafe resolution.
     *
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer,
     * the <tt>Instance</tt> returned by this method will only give access to instances of beans discovered by the container
     * before the {@link AfterBeanDiscovery} event is fired.
     *
     * Instances of dependent scoped beans obtained with this <tt>Instance</tt> must be explicitly destroyed by calling {@link Instance#destroy(Object)}
     *
     * If no qualifier is passed to {@link Instance#select} method, the <tt>@Default</tt> qualifier is assumed.
     *
     * @return an {@link Instance} object to request beans instances
     * @throws IllegalStateException if called during application initialization, before the {@link AfterDeploymentValidation}
     *         event is fired.
     * @since 2.0
     */
    Instance<Object> createInstance();

}
