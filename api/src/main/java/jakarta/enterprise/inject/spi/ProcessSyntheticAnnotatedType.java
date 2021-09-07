/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

package jakarta.enterprise.inject.spi;

/**
 * <p>
 * The container fires an event of this type for each Java class or interface added by
 * {@link BeforeBeanDiscovery#addAnnotatedType(AnnotatedType, String)} or
 * {@link AfterTypeDiscovery#addAnnotatedType(AnnotatedType, String)}
 * </p>
 * <p>
 * Any observer of this event is permitted to wrap and/or replace the {@link AnnotatedType}. The
 * container must use the final value of this property, after all observers have been called, to discover the types and read the
 * annotations of the program elements.
 * </p>
 * <p>
 * For example, the following observer decorates the {@link AnnotatedType} for every class that is
 * added by {@link BeforeBeanDiscovery#addAnnotatedType(AnnotatedType, String)}.
 * </p>
 * 
 * <pre>
 * public &lt;T&gt; void decorateAnnotatedType(@Observes ProcessSyntheticAnnotatedType&lt;T&gt; pat) {
 *     pat.setAnnotatedType(decorate(pat.getAnnotatedType()));
 * }
 * </pre>
 * <p>
 * If any observer method of a {@code ProcessSyntheticAnnotatedType} event throws an exception, the exception is treated as a
 * definition error by the container.
 * </p>
 * 
 * @author David Allen
 * @author Pete Muir
 * @see AnnotatedType
 * @see ProcessAnnotatedType
 * @param <X> The class being annotated
 * @since 1.1
 */
public interface ProcessSyntheticAnnotatedType<X> extends ProcessAnnotatedType<X> {
    /**
     * Get the extension instance which added the {@link AnnotatedType} for which this event is being fired.
     * 
     * @return the extension instance
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Extension getSource();
}
