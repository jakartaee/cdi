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

package org.jboss.cdi.api.test.se;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Test for CDIProvider resolution in CDI abstract class.
 * @author Antoine Sabot-durand
 *
 */
public class SeContainerInitializerTest {

    private static final String SERVICE_PATH = System.getProperty("serviceDir");
    private static final String SERVICE_FILE_NAME = SERVICE_PATH + SeContainerInitializer.class.getName();

    @BeforeMethod
    public void setUp() throws Exception {

        Files.deleteIfExists(FileSystems.getDefault().getPath(SERVICE_FILE_NAME));

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithoutServiceFile() throws Exception {
        SeContainerInitializer.newInstance();
    }

    @Test
    public void testWithOneGoodSeContainerInitializer() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummySeContainerInitializer.class.getName());
        fw.close();
        Assert.assertEquals(SeContainerInitializer.newInstance().getClass(), DummySeContainerInitializer.class);

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithOneBadSeContainerInitializer() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write("badprovider");
        fw.close();

        SeContainerInitializer.newInstance();

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithTwoGoodSeContainerInitializer() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummySeContainerInitializer.class.getName());
        fw.write('\n');
        fw.write(DummySeContainerInitializer2.class.getName());
        fw.close();
        Assert.assertTrue(SeContainerInitializer.newInstance().getClass().equals(DummySeContainerInitializer.class) ||
                SeContainerInitializer.newInstance().getClass().equals(DummySeContainerInitializer2.class));
    }
}