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

/**
 * Interface implemented by a CDI provider to provide access to the current container
 * 
 * @author Pete Muir
 * @since 1.1
 */
public interface CDIProvider extends Prioritized {

    public static final int DEFAULT_CDI_PROVIDER_PRIORITY = 0;

    /**
     * Provides access to the current container
     * 
     * @return the CDI instance for the current container
     * @throws IllegalStateException if no CDI container is available
     */
    CDI<Object> getCDI();


    @Override
    default int getPriority() {
        return DEFAULT_CDI_PROVIDER_PRIORITY;
    };
}
