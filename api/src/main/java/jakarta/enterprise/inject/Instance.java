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

package jakarta.enterprise.inject;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.enterprise.util.TypeLiteral;
import jakarta.inject.Provider;

/**
 * <p>
 * Allows the application to dynamically obtain instances of beans with a specified combination of required type and qualifiers.
 * </p>
 * 
 * <p>
 * In certain situations, injection is not the most convenient way to obtain a contextual reference. For example, it may not be
 * used when:
 * </p>
 * 
 * <ul>
 * <li>the bean type or qualifiers vary dynamically at runtime, or</li>
 * <li>depending upon the deployment, there may be no bean which satisfies the type and qualifiers, or</li>
 * <li>we would like to iterate over all beans of a certain type.</li>
 * </ul>
 * 
 * <p>
 * In these situations, an instance of the <code>Instance</code> may be injected:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * Instance&lt;PaymentProcessor&gt; paymentProcessor;
 * </pre>
 * 
 * <p>
 * Any combination of qualifiers may be specified at the injection point:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * &#064;PayBy(CHEQUE)
 * Instance&lt;PaymentProcessor&gt; chequePaymentProcessor;
 * </pre>
 * 
 * <p>
 * Or, the {@link Any &#064;Any} qualifier may be used, allowing the application to specify qualifiers
 * dynamically:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * &#064;Any
 * Instance&lt;PaymentProcessor&gt; anyPaymentProcessor;
 * </pre>
 * 
 * <p>
 * For an injected <code>Instance</code>:
 * </p>
 * 
 * <ul>
 * <li>the <em>required type</em> is the type parameter specified at the injection point, and</li>
 * <li>the <em>required qualifiers</em> are the qualifiers specified at the injection point.</li>
 * </ul>
 * 
 * <p>
 * The inherited {@link jakarta.inject.Provider#get()} method returns a contextual references for the unique bean that matches the
 * required type and required qualifiers and is eligible for injection into the class into which the parent <code>Instance</code>
 * was injected, or throws an {@link UnsatisfiedResolutionException} or
 * {@link AmbiguousResolutionException}.
 * </p>
 * 
 * <pre>
 * PaymentProcessor pp = chequePaymentProcessor.get();
 * </pre>
 * 
 * <p>
 * The inherited {@link java.lang.Iterable#iterator()} method returns an iterator over contextual references for beans that
 * match the required type and required qualifiers and are eligible for injection into the class into which the parent
 * <code>Instance</code> was injected.
 * </p>
 * 
 * <pre>
 * for (PaymentProcessor pp : anyPaymentProcessor)
 *     pp.test();
 * </pre>
 * 
 * @see jakarta.inject.Provider#get()
 * @see java.lang.Iterable#iterator()
 * @see AnnotationLiteral
 * @see TypeLiteral
 * 
 * @author Gavin King
 * @author John Ament
 * @author Martin Kouba
 * 
 * @param <T> the required bean type
 */

public interface Instance<T> extends Iterable<T>, Provider<T> {

    /**
     * <p>
     * Obtains a child <code>Instance</code> for the given additional required qualifiers.
     * </p>
     * 
     * @param qualifiers the additional required qualifiers
     * @return the child <code>Instance</code>
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an annotation that
     *         is not a qualifier type
     * @throws IllegalStateException if the container is already shutdown
     */
    Instance<T> select(Annotation... qualifiers);

    /**
     * <p>
     * Obtains a child <code>Instance</code> for the given required type and additional required qualifiers.
     * </p>
     * 
     * @param <U> the required type
     * @param subtype a {@link java.lang.Class} representing the required type
     * @param qualifiers the additional required qualifiers
     * @return the child <code>Instance</code>
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an annotation that
     *         is not a qualifier type
     * @throws IllegalStateException if the container is already shutdown
     */
    <U extends T> Instance<U> select(Class<U> subtype, Annotation... qualifiers);

    /**
     * <p>
     * Obtains a child <code>Instance</code> for the given required type and additional required qualifiers.
     * </p>
     * 
     * @param <U> the required type
     * @param subtype a {@link TypeLiteral} representing the required type
     * @param qualifiers the additional required qualifiers
     * @return the child <code>Instance</code>
     * @throws IllegalArgumentException if passed two instances of the same non repeating qualifier type, or an instance of an annotation that
     *         is not a qualifier type
     * @throws IllegalStateException if the container is already shutdown
     */
    <U extends T> Instance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers);

    /**
     * <p>
     * When called, provides back a Stream of the beans available in this Instance. If no beans are found, it returns an empty
     * stream.
     * </p>
     *
     * @since 2.0
     * @return a <code>Stream</code> representing the beans associated with this {@link Instance} object
     */
    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * <p>
     * Determines if there is no bean that matches the required type and qualifiers and is eligible for injection into the class
     * into which the parent <code>Instance</code> was injected.
     * </p>
     * 
     * @return <code>true</code> if there is no bean that matches the required type and qualifiers and is eligible for injection
     *         into the class into which the parent <code>Instance</code> was injected, or <code>false</code> otherwise.
     */
    boolean isUnsatisfied();

    /**
     * <p>
     * Determines if there is more than one bean that matches the required type and qualifiers and is eligible for injection
     * into the class into which the parent <code>Instance</code> was injected.
     * </p>
     * 
     * @return <code>true</code> if there is more than one bean that matches the required type and qualifiers and is eligible for
     *         injection into the class into which the parent <code>Instance</code> was injected, or <code>false</code> otherwise.
     */
    boolean isAmbiguous();

    /**
     * <p>
     * Determines if there is exactly one bean that matches the required type and qualifiers and is eligible for injection
     * into the class into which the parent <code>Instance</code> was injected.
     * </p>
     *
     * @since 2.0
     * @return <code>true</code> if there is exactly one bean that matches the required type and qualifiers and is eligible for
     *         injection into the class into which the parent <code>Instance</code> was injected, or <code>false</code> otherwise.
     */
    default boolean isResolvable() {
        return !isUnsatisfied() && !isAmbiguous();
    }

    /**
     * <p>
     * When called, the container destroys the instance if the active context object for the scope type of the bean supports
     * destroying bean instances. All normal scoped built-in contexts support destroying bean instances.
     * </p>
     * 
     * <p>
     * The instance passed should either be a dependent scoped bean instance obtained from the same {@link Instance} object, or
     * the client proxy for a normal scoped bean instance.
     * </p>
     * 
     * 
     * @since 1.1
     * @param instance the instance to destroy
     * @throws UnsupportedOperationException if the active context object for the scope type of the bean does not support
     *         destroying bean instances
     */
    void destroy(T instance);

    /**
     * Obtains an initialized contextual reference handle for a bean that has the required type and qualifiers and is
     * eligible for injection. Throws exceptions if there is no such bean or more than one.
     *
     * <p>
     * The contextual reference is obtained lazily, i.e. when first needed.
     * </p>
     *
     * @return a new {@link Handle} instnace
     * @throws UnsatisfiedResolutionException if there is no bean with given type and qualifiers
     * @throws AmbiguousResolutionException if there is more than one bean given type and qualifiers
     */
    Handle<T> getHandle();

    /**
     * Allows iterating over contextual reference handles for all beans that have the required type and required qualifiers and are eligible
     * for injection.
     *
     * <p>
     * Note that the returned {@link Iterable} is stateless. Therefore, each {@link Iterable#iterator()} produces a new set of handles.
     * </p>
     *
     * @return a new iterable
     */
    Iterable<? extends Handle<T>> handles();

    /**
     *  Returns stream of {@link Handle} objects.
     *
     * @return a new stream of contextual reference handles
     */
    default Stream<? extends Handle<T>> handlesStream() {
        return StreamSupport.stream(handles().spliterator(), false);
    }

    /**
     * This interface represents a contextual reference handle.
     * <p>
     * Allows to inspect the metadata of the relevant bean before resolving its contextual reference and also to destroy
     * the underlying contextual instance.
     * </p>
     *
     * @author Matej Novotny
     * @param <T> the required bean type
     */
    interface Handle<T> extends AutoCloseable {

        /**
         * The contextual reference is obtained lazily, i.e. when first needed.
         *
         * @return the contextual reference
         * @see Instance#get()
         * @throws IllegalStateException If the producing {@link Instance} does not exist
         * @throws IllegalStateException If invoked on {@link Handle} that previously successfully destroyed its
         * underlying contextual reference
         */
        T get();

        /**
         *
         * @return the bean metadata
         */
        Bean<T> getBean();

        /**
         * Destroy the contextual instance.
         *
         * It's a no-op if:
         * <ul>
         * <li>called multiple times</li>
         * <li>if the producing {@link Instance} does not exist</li>
         * <li>if the handle does not hold a contextual reference, i.e. {@link #get()} was never called</li>
         * </ul>
         *
         * @see Instance#destroy(Object)
         */
        void destroy();

        /**
         * Delegates to {@link #destroy()}.
         */
        @Override
        void close();

    }

}
