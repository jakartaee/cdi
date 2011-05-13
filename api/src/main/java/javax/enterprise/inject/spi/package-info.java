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
 * <h3>The <tt>BeanManager</tt> object</h3>
 * 
 * <p>Portable extensions sometimes interact directly with the container 
 * via programmatic API call. The interface 
 * {@link javax.enterprise.inject.spi.BeanManager} provides operations 
 * for obtaining contextual references for beans, along with many other 
 * operations of use to portable extensions.</p>
 * 
 * <h3>Container lifecycle events</h3>
 * 
 * <p>During the application initialization process, the container fires 
 * a series of {@linkplain javax.enterprise.event events}, allowing 
 * portable extensions to integrate with the container initialization 
 * process. Observer methods of these events must belong to
 * {@linkplain javax.enterprise.inject.spi.Extension extensions} declared 
 * in <tt>META-INF/services</tt>.</p>
 * 
 * <p>Lifecycle events include
 * {@link javax.enterprise.inject.spi.BeforeBeanDiscovery},
 * {@link javax.enterprise.inject.spi.AfterBeanDiscovery},
 * {@link javax.enterprise.inject.spi.AfterDeploymentValidation} and
 * {@link javax.enterprise.inject.spi.BeforeShutdown}.</p>
 * 
 * <h3>Interfaces representing enabled beans</h3>
 * 
 * <p>The interfaces 
 * {@link javax.enterprise.inject.spi.Bean},
 * {@link javax.enterprise.inject.spi.Decorator},
 * {@link javax.enterprise.inject.spi.Interceptor} and
 * {@link javax.enterprise.inject.spi.ObserverMethod}
 * define everything the container needs to manage instances of 
 * a bean, interceptor, decorator or observer method.</p>
 * 
 * <p>An instance of <tt>Bean</tt> exists for every  
 * {@linkplain javax.enterprise.inject enabled bean}. A portable 
 * extension may add support for new kinds of beans by implementing 
 * <tt>Bean</tt>, observing the event
 * {@link javax.enterprise.inject.spi.AfterBeanDiscovery} event 
 * {@linkplain javax.enterprise.inject.spi.AfterBeanDiscovery#addBean(Bean) 
 * registering beans} with the container. An instance of 
 * <tt>ObserverMethod</tt> exists for every 
 * {@linkplain javax.enterprise.event observer method} of every 
 * enabled bean. A portable extension may add observers by implementing 
 * <tt>ObserverMethod</tt> and 
 * {@linkplain javax.enterprise.inject.spi.AfterBeanDiscovery#addObserverMethod(ObserverMethod) 
 * registering an instance} with the container.</p>
 * 
 * <p>A portable extension may be notified of the existence of an
 * enabled bean by observing the container lifecycle event type 
 * {@link javax.enterprise.inject.spi.ProcessBean} or one of its 
 * {@linkplain javax.enterprise.inject.spi.ProcessBean subtypes},
 * or of the existence of an observer method of an enabled bean by 
 * observing the event type
 * {@link javax.enterprise.inject.spi.ProcessObserverMethod}.</p>
 * 
 * <h3>Alternate metadata sources</h3>
 * 
 * <p>A portable extension may provide an alternative metadata 
 * source, such as configuration by XML.</p>
 * 
 * <p>{@link javax.enterprise.inject.spi.Annotated}
 * and its subtypes allow a portable extension to specify 
 * metadata that overrides the annotations that exist on a 
 * bean class. The portable extension is responsible for 
 * implementing the interfaces, thereby exposing the metadata 
 * to the container. The container must use the operations of 
 * <tt>Annotated</tt> and its subinterfaces to discover program 
 * element types and annotations, instead of directly calling the 
 * Java Reflection API.</p>
 * 
 * <p>A portable extension provides its metadata to the 
 * container by observing the event 
 * {@link javax.enterprise.inject.spi.ProcessAnnotatedType} and 
 * {@linkplain javax.enterprise.inject.spi.ProcessAnnotatedType#setAnnotatedType(AnnotatedType)
 * wrapping} the {@link javax.enterprise.inject.spi.AnnotatedType}.</p>
 * 
 * <h3><tt>Producer</tt> and <tt>InjectionTarget</tt></h3>
 * 
 * <pr>The interfaces {@link javax.enterprise.inject.spi.Producer} and
 * {@link javax.enterprise.inject.spi.InjectionTarget} abstract the 
 * basic lifecycle of (contextual or non-contextual) container managed 
 * objects, including instantiation and destruction, dependency injection 
 * and lifecycle callbacks.</p>
 * 
 * <p>An instance of {@link javax.enterprise.inject.spi.InjectionTarget} 
 * may be 
 * {@linkplain javax.enterprise.inject.spi.BeanManager#createInjectionTarget(AnnotatedType) 
 * obtained} from the {@link javax.enterprise.inject.spi.BeanManager},
 * allowing a portable extension to request these container services for
 * objects under the control of the portable extension.</p>
 * 
 * <p>Furthermore, a portable extension may replace the implementation
 * of {@link javax.enterprise.inject.spi.InjectionTarget} or
 * {@link javax.enterprise.inject.spi.Producer} used by the container
 * with its own implementation by observing the events
 * {@link javax.enterprise.inject.spi.ProcessInjectionTarget} or
 * {@link javax.enterprise.inject.spi.ProcessProducer}.</p>
 * 
 * @see javax.enterprise.inject
 * @see javax.enterprise.context.spi
 */
package javax.enterprise.inject.spi;

