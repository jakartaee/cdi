/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
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
 * This interface allows some SPI implementation to change their priority programmatically.
 * </p>
 *
 * <p>
 * For instance {@link ObserverMethod} extends this interface to set the observer priority.
 *
 * A custom alternative {@link Bean}, {@link Interceptor} or {@link Decorator} may implement this interface to be activated
 * with a given priority
 *
 * </p>
 *
 * @see Bean
 * @author Mark Paluch
 * @author Antoine Sabot-Durand
 * @since 2.0
 */
public interface Prioritized {

    /**
     * <p>
     * Returns the priority for this SPI element.
     * </p>
     *
     * @return the priority value
     */
    int getPriority();
}
