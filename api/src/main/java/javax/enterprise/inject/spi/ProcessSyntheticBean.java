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
package javax.enterprise.inject.spi;

/**
 * <p>
 * The container fires an event of this type for each custom bean implementation added through
 * {@link AfterBeanDiscovery#addBean()} or {@link AfterBeanDiscovery#addBean(Bean)}, before registering the
 * {@link javax.enterprise.inject.spi.Bean} object.
 * </p>
 * <p>
 * If any observer method of a {@code ProcessSyntheticBean} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * @author Martin Kouba
 * @param <X> The class of the bean
 * @since 2.0
 */
public interface ProcessSyntheticBean<X> extends ProcessBean<X> {

    /**
     * Get the extension instance which added the {@link Bean} for which this event is being fired.
     * 
     * @return the extension instance
     * @throws IllegalStateException if called outside of the observer method invocation
     */
    public Extension getSource();

}
