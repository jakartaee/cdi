/*
 * Copyright 2016 Red Hat, Inc., and individual contributors
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
 * Represents a context of a fired event. Provides access to an event object and corresponding metadata.
 *
 * @author Martin Kouba
 * @see ObserverMethod#notify(EventContext)
 * @see EventMetadata
 * @since 2.0
 * @param <T> type of event object
 */
public interface EventContext<T> {

    /**
     *
     * @return the event object, aka the payload
     */
    T getEvent();

    /**
     *
     * @return the event metadata
     */
    EventMetadata getMetadata();

}
