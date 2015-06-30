/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, 2015 Red Hat, Inc., and individual contributors
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

import java.util.Collections;
import java.util.Map;

/**
 * Interface implemented by a CDI provider to provide access to the current container
 * 
 * @author Pete Muir
 * @author John D. Ament
 * @since 1.1
 */
public interface CDIProvider {

    /**
     * Provides access to the current container
     * 
     * @return the CDI instance for the current container
     */
    CDI<Object> getCDI();

    /**
     * Determines whether or not this CDIProvider has been initialized or not.
     * 
     * @return true if initialized, false if not.
     * @since 2.0
     */
    boolean isInitialized();

    /**
     * <p>
     * Initializes a CDI Container.
     * </p>
     * <p>
     * Cannot be called within an application server.
     * </p>
     *
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI#current()}
     * @throws UnsupportedOperationException if called within an application server
     * @since 2.0
     * 
     */
    default CDI<Object> initialize() {
        return initialize(Collections.emptyMap());
    }

    /**
     * <p>
     * Initializes a CDI Container with custom params.
     * </p>
     * <p>
     * Cannot be called within an application server.
     * </p>
     *
     * @param params optional parameters, may be null or empty.  May also be immutable.
     * @return the {@link CDI} instance associated with the container.  This is the same instance returned by using
     * {@link CDI#current()}
     * @throws UnsupportedOperationException if called within an application server
     * @since 2.0
     */
    CDI<Object> initialize(Map<String,Object> params);

}
