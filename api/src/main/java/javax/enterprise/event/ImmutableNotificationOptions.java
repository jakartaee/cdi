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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The immutable implementation of {@link NotificationOptions}.
 * 
 * @author Martin Kouba
 *
 */
class ImmutableNotificationOptions implements NotificationOptions {

    private final Executor executor;

    private final Map<String, Object> options;

    private ImmutableNotificationOptions(Executor executor, Map<String, Object> options) {
        this.executor = executor;
        this.options = new HashMap<>(options);
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public Object get(String name) {
        return options.get(name);
    }

    static class Builder implements javax.enterprise.event.NotificationOptions.Builder {

        private Executor executor;

        private Map<String, Object> options;

        Builder() {
            this.options = new HashMap<>();
        }

        public Builder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder set(String name, Object value) {
            options.put(name, value);
            return this;
        }

        public ImmutableNotificationOptions build() {
            return new ImmutableNotificationOptions(executor, options);
        }

    }

}
