/*
 * Copyright 2018, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.1-SNAPSHOT (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.1-SNAPSHOT
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.cdi.api.test.privileged;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.CDIProvider;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for CDIProvider resolution in CDI abstract class.
 *
 * @author Antoine Sabot-durand
 */
public class CDIPrivilegedTest {

    private static final String SERVICE_PATH = System.getProperty("serviceDir");

    private static final String SERVICE_FILE_NAME = SERVICE_PATH + CDIProvider.class.getName();

    @Test
    public void cdiCurrentShouldWork() {
        try {
            Files.copy(Paths.get(SERVICE_PATH + "fake"), Paths.get(SERVICE_FILE_NAME));
        } catch (IOException e) {
            Assert.fail("Unabale to create service loader file", e);
        }
        Assert.assertEquals(CDI.current().getClass(), FakeCDIProvider.FakeCDI.class);

    }

}