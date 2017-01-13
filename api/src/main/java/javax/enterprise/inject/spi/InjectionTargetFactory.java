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

package javax.enterprise.inject.spi;

import javax.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;

/**
 * <p>
 * An {@link InjectionTargetFactory} can create an {@link InjectionTarget} for a given bean.
 * </p>
 * 
 * <p>
 * The {@link InjectionTargetFactory} obtained from {@link BeanManager#getInjectionTargetFactory(AnnotatedType)} is capable of providing
 * container created injection targets. This factory can be wrapped to add behavior to container created injection targets.
 * </p>
 * 
 * <p>
 * For example:
 * </p>
 * 
 * <pre>
 * BeanAttributes&lt;MyBean&gt; myBeanAttributes = beanManager.createBeanAttributes(myBeanAnnotatedType);
 * beanManager.createBean(myBeanAttributes, MyBean.class, new InjectionTargetFactory() {
 * 
 *     public &lt;T&gt; InjectionTarget&lt;T&gt; createInjectionTarget(Bean&lt;T&gt; bean) {
 *         return new WrappingInjectionTarget&lt;T&gt;(beanManager.getInjectionTargetFactory(myBeanAnnotatedType).createInjectionTarget(
 *                 bean));
 *     }
 * });
 * </pre>
 * 
 * @author Pete Muir
 * @author Antoine Sabot-Durand
 * @since 1.1
 *
 * @param <T> type on which this InjectionTarget operates
 */
public interface InjectionTargetFactory<T> {

    /**
     * Create a new injection target for a bean.
     * 
     * @param bean the bean to create the injection target for, or null if creating a non-contextual object
     * @return the injection target
     */
    public InjectionTarget<T> createInjectionTarget(Bean<T> bean);

    /**
     *
     * Returns an {@link AnnotatedTypeConfigurator} to to configure the {@link AnnotatedType} used to create the {@link InjectionTarget}.
     *
     * Each call returns the same AnnotatedTypeConfigurator.
     *
     * @return an {@link AnnotatedTypeConfigurator} to configure injection points
     * @throws IllegalStateException if used after {@link #createInjectionTarget(Bean)} invocation
     * @since 2.0
     */
    public default AnnotatedTypeConfigurator<T> configure() {
        throw new UnsupportedOperationException("Configuration not supported here");
    }

}
