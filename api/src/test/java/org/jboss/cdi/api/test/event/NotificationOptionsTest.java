/*
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
package org.jboss.cdi.api.test.event;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.time.Duration;
import java.util.concurrent.Executor;

import jakarta.enterprise.event.NotificationOptions;

import org.testng.annotations.Test;

/**
 *
 * @author Martin Kouba
 */
public class NotificationOptionsTest {

    @Test
    public void testBuilder() {
        Duration timeout = Duration.ofDays(1);
        NotificationOptions options = NotificationOptions.of("timeout", timeout);
        assertEquals(timeout, options.get("timeout"));
        assertNull(options.getExecutor());
        assertNull(options.get("alpha"));
        options = NotificationOptions.builder().set("foo", "bar").setExecutor(new Executor() {
            @Override
            public void execute(Runnable command) {
            }
        }).build();
        assertEquals("bar", options.get("foo"));
        assertNotNull(options.getExecutor());
        options = NotificationOptions.ofExecutor(new Executor() {
            @Override
            public void execute(Runnable command) {
            }
        });
        Executor executor = options.getExecutor();
        assertNotNull(executor);
        assertNull(options.get("timeout"));
    }

}
