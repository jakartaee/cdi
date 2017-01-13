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
package javax.enterprise.event;

import java.util.concurrent.Executor;

/**
 * Notification options are used to configure observer notification.
 * 
 * @author Martin Kouba
 * @see Event#fireAsync(Object, NotificationOptions)
 * @since 2.0
 */
public interface NotificationOptions {

    /**
     * 
     * @return the executor used to execute an asynchronous event
     */
    Executor getExecutor();

    /**
     * 
     * @param optionName name of the option to get value of
     * @return the value of an option or <code>null</code> if no option for the given name exists
     */
    Object get(String optionName);

    /**
     * 
     * @param executor a specific {@link Executor} to handle observer notification
     * @return an immutable holder of an executor
     */
    static NotificationOptions ofExecutor(Executor executor) {
        return builder().setExecutor(executor).build();
    }

    /**
     * 
     * @param optionName name of the option to set
     * @param optionValue value for the option
     * @return an immutable holder of a single option
     */
    static NotificationOptions of(String optionName, Object optionValue) {
        return builder().set(optionName, optionValue).build();
    }

    /**
     * 
     * @return the options builder
     */
    static Builder builder() {
        return new ImmutableNotificationOptions.Builder();
    }

    /**
     * Notification options builder.
     * 
     * @author Martin Kouba
     * @since 2.0
     */
    interface Builder {

        Builder setExecutor(Executor executor);

        Builder set(String optionName, Object optionValue);

        NotificationOptions build();

    }

}
