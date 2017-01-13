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

/**
 * <p>
 * An {@link ProducerFactory} can create an {@link Producer} for a given bean.
 * </p>
 * 
 * <p>
 * The {@link ProducerFactory} obtained from {@link BeanManager#getProducerFactory(AnnotatedMethod, Bean)} or
 * {@link BeanManager#getProducerFactory(AnnotatedField, Bean)} is capable of providing container created
 * producers. This factory can be wrapped to add behavior to container created producers.
 * </p>
 * 
 * <p>
 * For example:
 * </p>
 * 
 * <pre>
 * BeanAttributes&lt;MyBean&gt; myBeanAttributes = beanManager.createBeanAttributes(myBeanAnnotatedFieldField);
 * beanManager.createBean(myBeanAttributes, MyBean.class, new ProducerFactory() {
 * 
 *     public &lt;T&gt; Producer&lt;T&gt; createProducer(Bean&lt;T&gt; bean) {
 *         return new WrappingProducer&lt;T&gt;(beanManager.getProducerFactory(myBeanAnnotatedField).createProducer(bean));
 *     }
 * });
 * </pre>
 * 
 * @author Pete Muir
 * @since 1.1
 * @param <X> type of the bean containing the producer
 */
public interface ProducerFactory<X> {

    /**
     * Create a new producer for a bean.
     * 
     * @param bean the bean to create the producer for, or null if creating a non-contextual object
     * @return the producer
     */
    public <T> Producer<T> createProducer(Bean<T> bean);

}
