package jakarta.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.AmbiguousResolutionException;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Stereotype;

/**
 * <p>
 * {@code BeanContainer} is a superclass of {@link BeanManager} containing capabilities that are portable across
 * all CDI environments.
 * </p>
 *
 * <p>
 * Provides operations for obtaining contextual references for beans, along with many other operations of use to
 * CDI applications.
 * </p>
 *
 * <p>
 * Any bean may obtain an instance of <code>BeanContainer</code> by injecting it:
 * </p>
 *
 * <pre>
 * &#064;Inject
 * BeanContainer container;
 * </pre>
 *
 * @author Matej Novotny
 * @since 4.0
 */
public interface BeanContainer {

    /**
     * <p>
     * Obtains a contextual reference for a certain {@linkplain Bean bean} and a certain bean type of the bean.
     * </p>
     *
     * @param bean the {@link Bean} object representing the bean
     * @param beanType a bean type that must be implemented by any client proxy that is returned
     * @param ctx a {@link CreationalContext} that may be used to destroy any object with scope
     *        {@link Dependent} that is created
     * @return a contextual reference representing the bean
     * @throws IllegalArgumentException if the given type is not a bean type of the given bean
     * @throws IllegalStateException if called during application initialization, before the {@link AfterDeploymentValidation}
     *         event is fired.
     */
    Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx);

    /**
     * Obtain an instance of a {@link CreationalContext} for the given
     * {@linkplain Contextual contextual type}, or for a non-contextual object.
     *
     * @param <T> type of the instance
     * @param contextual the {@link Contextual}, or a null value in the case of a non-contextual
     *        object
     * @return the new {@link CreationalContext}
     */
    <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual);

    /**
     * Return the set of beans which have the given required type and qualifiers and are available for injection in the module
     * or library containing the class into which the <code>BeanManager</code>/<code>BeanContainer</code> was injected or,
     * in the Jakarta EE environment, the Jakarta EE component from whose JNDI environment namespace the
     * <code>BeanManager</code>/<code>BeanContainer</code> was obtained, according to the rules of typesafe resolution.
     * If no qualifiers are given, the {@linkplain Default default qualifier} is assumed.
     * <p>
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
    Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers);

    /**
     * Return the set of beans which have the given EL name and are available for injection in the module or library containing
     * the class into which the <code>BeanManager</code>/<code>BeanContainer</code> was injected or, in the Jakarta EE
     * environment, the Jakarta EE component from whose JNDI environment namespace the
     * <code>BeanManager</code>/<code>BeanContainer</code> was obtained, according to the rules of EL name resolution.
     * <p>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer,
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     *
     * @param name the EL name
     * @return the resulting set of {@linkplain Bean beans}
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    Set<Bean<?>> getBeans(String name);

    /**
     * Apply the ambiguous dependency resolution rules to a set of {@linkplain Bean beans}.
     * <p>
     * Note that when called during invocation of an {@link AfterBeanDiscovery} event observer,
     * this method will only return beans discovered by the container before the {@link AfterBeanDiscovery} event is fired.
     *
     * @param <X> a common type of the beans
     * @param beans a set of {@linkplain Bean beans} of the given type
     * @return the resolved bean, or null if null or an empty set is passed
     * @throws AmbiguousResolutionException if the ambiguous dependency resolution rules fail
     * @throws IllegalStateException if called during application initialization, before the {@link AfterBeanDiscovery}
     *         event is fired.
     */
    <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans);

    /**
     * Return an ordered set of {@linkplain ObserverMethod observer methods} for an event.
     * <p>
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
    <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T event, Annotation... qualifiers);

    /**
     * Return an ordered list of enabled {@linkplain Interceptor interceptors} for a set of interceptor bindings and a type of
     * interception and which are enabled in the module or library containing the class into which the
     * <code>BeanManager</code>/<code>BeanContainer</code> was injected or, in the Jakarta EE environment,
     * the Jakarta EE component from whose JNDI environment namespace the
     * <code>BeanManager</code>/<code>BeanContainer</code> was obtained.
     * <p>
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
    List<Interceptor<?>> resolveInterceptors(InterceptionType type, Annotation... interceptorBindings);

    /**
     * Test the given annotation type to determine if it is a {@linkplain jakarta.enterprise.context scope type}.
     *
     * @param annotationType the annotation type
     * @return true if the annotation type is a {@linkplain jakarta.enterprise.context scope type}
     */
    boolean isScope(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a {@linkplain jakarta.enterprise.context normal scope type}.
     *
     * @param annotationType the annotation type
     * @return <code>true</code> if the annotation type is a {@linkplain jakarta.enterprise.context normal scope type}
     */
    boolean isNormalScope(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a {@linkplain jakarta.inject.Qualifier qualifier type}.
     *
     * @param annotationType the annotation type
     * @return <code>true</code> if the annotation type is a {@linkplain jakarta.inject.Qualifier qualifier type}
     */
    boolean isQualifier(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is a {@linkplain Stereotype stereotype}.
     *
     * @param annotationType the annotation type
     * @return <code>true</code> if the annotation type is a {@linkplain Stereotype stereotype}
     */
    boolean isStereotype(Class<? extends Annotation> annotationType);

    /**
     * Test the given annotation type to determine if it is an {@linkplain jakarta.interceptor.InterceptorBinding interceptor
     * binding type} .
     *
     * @param annotationType the annotation to test
     * @return <code>true</code> if the annotation type is a {@linkplain jakarta.interceptor.InterceptorBinding interceptor
     *         binding
     *         type}
     */
    boolean isInterceptorBinding(Class<? extends Annotation> annotationType);

    /**
     * Obtains an active {@linkplain Context context object} for the given
     * {@linkplain jakarta.enterprise.context scope} .
     *
     * @param scopeType the {@linkplain jakarta.enterprise.context scope}
     * @return the {@linkplain Context context object}
     * @throws ContextNotActiveException if there is no active context object for the given scope
     * @throws IllegalArgumentException if there is more than one active context object for the given scope
     */
    Context getContext(Class<? extends Annotation> scopeType);

    /**
     * Obtains all {@linkplain Context context objects}, active and inactive, for the given
     * {@linkplain jakarta.enterprise.context scope}.
     *
     * @param scopeType the {@linkplain jakarta.enterprise.context scope}; must not be {@code null}
     * @return immutable collection of {@linkplain Context context objects}; never {@code null}, but may be empty
     */
    Collection<Context> getContexts(Class<? extends Annotation> scopeType);

    /**
     * Returns an instance of Event with specified type <code>java.lang.Object</code> and specified qualifier
     * <code>@Default</code>
     * It allows typesafe synchronous or asynchronous event firing without injection of {@link Event} built-in bean requirement.
     *
     * @return a new {@link Event} object whose event type is <code>Object</code> and qualifier <code>@Default</code>
     * @since 2.0
     */
    Event<Object> getEvent();

    /**
     * Obtains an {@link Instance} object to access to beans instances.
     * <p>
     * The returned <code>Instance</code> object can only access instances of beans that are available for injection in the
     * module
     * or library containing the class into which the <code>BeanManager</code>/<code>BeanContainer</code> was injected
     * or, in the Jakarta EE environment, the Jakarta EE component from whose JNDI environment namespace the
     * <code>BeanContainer</code> was obtained, according to the rules of typesafe resolution.
     * <p>
     * Instances of dependent scoped beans obtained with this <code>Instance</code> must be explicitly destroyed by calling
     * {@link Instance#destroy(Object)}
     * <p>
     * If no qualifier is passed to {@link Instance#select} method, the <code>@Default</code> qualifier is assumed.
     *
     * @return an {@link Instance} object to request beans instances
     * @throws IllegalStateException if called during application initialization, before the {@link AfterDeploymentValidation}
     *         event is fired.
     * @since 2.0
     */
    Instance<Object> createInstance();

    /**
     * Returns true if a bean with given bean types and qualifiers would be assignable
     * to an injection point with given required type and required qualifiers, false otherwise.
     * <p>
     * Callers do not need to include implied qualifiers ({@code @Default}, {@code Any}).
     * These will be automatically added where applicable.
     * <p>
     * Throws {@link IllegalArgumentException} if any of the arguments is {@code null}.
     *
     * @param beanTypes bean types of a bean; must not be {@code null}
     * @param beanQualifiers qualifiers of a bean; must not be {@code null}
     * @param requiredType required type of an injection point; must not be {@code null}
     * @param requiredQualifiers required qualifiers of an injection point; must not be {@code null}
     * @return true if a bean with given bean types and qualifiers would be assignable
     *         to an injection point with given required type and required qualifiers, false otherwise
     */
    boolean isMatchingBean(Set<Type> beanTypes, Set<Annotation> beanQualifiers, Type requiredType,
            Set<Annotation> requiredQualifiers);

    /**
     * Returns true if an event object with given type and qualifiers would match
     * an observer method with given observed event type and observed event qualifiers, false otherwise.
     * <p>
     * Callers do not need to include implied qualifiers ({@code @Default}, {@code Any}).
     * These will be automatically added where applicable.
     * <p>
     * Throws {@link IllegalArgumentException} if any of the arguments is {@code null}.
     *
     * @param eventType type of an event object; must not be {@code null}
     * @param eventQualifiers event qualifiers; must not be {@code null}
     * @param observedEventType observed event type of an observer method; must not be {@code null}
     * @param observedEventQualifiers observed event qualifiers on an observer method; must not be {@code null}
     * @return true if an event object with given type and qualifiers would result in notifying
     *         an observer method with given observed event type and observed event qualifiers, false otherwise
     */
    boolean isMatchingEvent(Type eventType, Set<Annotation> eventQualifiers, Type observedEventType,
            Set<Annotation> observedEventQualifiers);
}
