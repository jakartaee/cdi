/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
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
package javax.enterprise.inject.spi.configurator;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.ProcessProducer;
import javax.enterprise.inject.spi.Producer;

/**
 * <p>
 * A {@link ProducerConfigurator} can configure a {@link Producer}. The container must provide an implementation of this
 * interface.
 * </p>
 * 
 * <p>
 * This configurator is not thread safe and shall not be used concurrently.
 * </p>
 *
 * @param <T> The return type of the producer method or the type of the producer field
 * @author Martin Kouba
 * @see ProcessProducer#configureProducer()
 * @since 2.0
 */
public interface ProducerConfigurator<T> {

    /**
     * Set a callback to produce a new instance.
     * 
     * @param callback a {@link Function} defining the callback to set
     * @return self
     * @see Producer#produce(CreationalContext)
     */
    <U extends T> ProducerConfigurator<T> produceWith(Function<CreationalContext<U>, U> callback);

    /**
     * Set a callback to destroy the produced instance.
     * 
     * @param callback a {@link Consumer} defining the callback to set
     * @return self
     * @see Producer#dispose(Object)
     */
    ProducerConfigurator<T> disposeWith(Consumer<T> callback);


}
