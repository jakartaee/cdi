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
 * <p>The portable extension integration SPI.</p>
 * 
 * <p>A portable extension may integrate with the container by:</p>
 * 
 * <ul>
 * <li>Providing its own beans, interceptors and decorators to the 
 * container</li>
 * <li>Injecting dependencies into its own objects using the 
 * dependency injection service</li>
 * <li>Providing a context implementation for a custom scope</li>
 * <li>Augmenting or overriding the annotation-based metadata with 
 * metadata from some other source</li>
 * </ul>
 * 
 * <h3>The <code>BeanManager</code> object</h3>
 * 
 * <p>Portable extensions sometimes interact directly with the container 
 * via programmatic API call. The interface 
 * {@link jakarta.enterprise.inject.spi.BeanManager} provides operations 
 * for obtaining contextual references for beans, along with many other 
 * operations of use to portable extensions.</p>
 * 
 * <h3>Container lifecycle events</h3>
 * 
 * <p>During the application initialization process, the container fires 
 * a series of {@linkplain jakarta.enterprise.event events}, allowing 
 * portable extensions to integrate with the container initialization 
 * process. Observer methods of these events must belong to
 * {@linkplain jakarta.enterprise.inject.spi.Extension extensions} declared 
 * in <code>META-INF/services</code>.</p>
 * 
 * <p>Lifecycle events include
 * {@link jakarta.enterprise.inject.spi.BeforeBeanDiscovery},
 * {@link jakarta.enterprise.inject.spi.AfterBeanDiscovery},
 * {@link jakarta.enterprise.inject.spi.AfterDeploymentValidation} and
 * {@link jakarta.enterprise.inject.spi.BeforeShutdown}.</p>
 * 
 * <h3>Interfaces representing enabled beans</h3>
 * 
 * <p>The interfaces 
 * {@link jakarta.enterprise.inject.spi.Bean},
 * {@link jakarta.enterprise.inject.spi.Decorator},
 * {@link jakarta.enterprise.inject.spi.Interceptor} and
 * {@link jakarta.enterprise.inject.spi.ObserverMethod}
 * define everything the container needs to manage instances of 
 * a bean, interceptor, decorator or observer method.</p>
 * 
 * <p>An instance of <code>Bean</code> exists for every
 * {@linkplain jakarta.enterprise.inject enabled bean}. A portable 
 * extension may add support for new kinds of beans by implementing 
 * <code>Bean</code>, observing the event
 * {@link jakarta.enterprise.inject.spi.AfterBeanDiscovery} event 
 * {@linkplain jakarta.enterprise.inject.spi.AfterBeanDiscovery#addBean(Bean) 
 * registering beans} with the container. An instance of 
 * <code>ObserverMethod</code> exists for every
 * {@linkplain jakarta.enterprise.event observer method} of every 
 * enabled bean. A portable extension may add observers by implementing 
 * <code>ObserverMethod</code> and
 * {@linkplain jakarta.enterprise.inject.spi.AfterBeanDiscovery#addObserverMethod(ObserverMethod) 
 * registering an instance} with the container.</p>
 * 
 * <p>A portable extension may be notified of the existence of an
 * enabled bean by observing the container lifecycle event type 
 * {@link jakarta.enterprise.inject.spi.ProcessBean} or one of its 
 * {@linkplain jakarta.enterprise.inject.spi.ProcessBean subtypes},
 * or of the existence of an observer method of an enabled bean by 
 * observing the event type
 * {@link jakarta.enterprise.inject.spi.ProcessObserverMethod}.</p>
 * 
 * <h3>Alternate metadata sources</h3>
 * 
 * <p>A portable extension may provide an alternative metadata 
 * source, such as configuration by XML.</p>
 * 
 * <p>{@link jakarta.enterprise.inject.spi.Annotated}
 * and its subtypes allow a portable extension to specify 
 * metadata that overrides the annotations that exist on a 
 * bean class. The portable extension is responsible for 
 * implementing the interfaces, thereby exposing the metadata 
 * to the container. The container must use the operations of 
 * <code>Annotated</code> and its subinterfaces to discover program
 * element types and annotations, instead of directly calling the 
 * Java Reflection API.</p>
 * 
 * <p>A portable extension provides its metadata to the 
 * container by observing the event 
 * {@link jakarta.enterprise.inject.spi.ProcessAnnotatedType} and 
 * {@linkplain jakarta.enterprise.inject.spi.ProcessAnnotatedType#setAnnotatedType(AnnotatedType)
 * wrapping} the {@link jakarta.enterprise.inject.spi.AnnotatedType}.</p>
 * 
 * <h3><code>Producer</code> and <code>InjectionTarget</code></h3>
 * 
 * <p>The interfaces {@link jakarta.enterprise.inject.spi.Producer} and
 * {@link jakarta.enterprise.inject.spi.InjectionTarget} abstract the 
 * basic lifecycle of (contextual or non-contextual) container managed 
 * objects, including instantiation and destruction, dependency injection 
 * and lifecycle callbacks.</p>
 * 
 * <p>An instance of {@link jakarta.enterprise.inject.spi.InjectionTarget} 
 * may be 
 * {@linkplain jakarta.enterprise.inject.spi.BeanManager#createInjectionTarget(AnnotatedType) 
 * obtained} from the {@link jakarta.enterprise.inject.spi.BeanManager},
 * allowing a portable extension to request these container services for
 * objects under the control of the portable extension.</p>
 * 
 * <p>Furthermore, a portable extension may replace the implementation
 * of {@link jakarta.enterprise.inject.spi.InjectionTarget} or
 * {@link jakarta.enterprise.inject.spi.Producer} used by the container
 * with its own implementation by observing the events
 * {@link jakarta.enterprise.inject.spi.ProcessInjectionTarget} or
 * {@link jakarta.enterprise.inject.spi.ProcessProducer}.</p>
 * 
 * @see jakarta.enterprise.inject
 * @see jakarta.enterprise.context.spi
 */
package jakarta.enterprise.inject.spi;

