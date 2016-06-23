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

package org.jboss.cdi.api.test.bootstrap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.enterprise.inject.bootstrap.UserContainerInitializer;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Test for CDIProvider resolution in CDI abstract class.
 * @author Antoine Sabot-durand
 *
 */
public class UserContainerInitializerTest {

    private static final String SERVICE_PATH = System.getProperty("serviceDir");
    private static final String SERVICE_FILE_NAME = SERVICE_PATH + UserContainerInitializer.class.getName();

    @BeforeMethod
    public void setUp() throws Exception {
        ContainerInitChild.reset();
        Files.deleteIfExists(FileSystems.getDefault().getPath(SERVICE_FILE_NAME));

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithoutServiceFile() throws Exception {
        UserContainerInitializer.getInstance();
    }

    @Test
    public void testWithOneGoodUserContainerInitializer() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyUserContainerInitializer.class.getName());
        fw.close();
        Assert.assertEquals(UserContainerInitializer.getInstance().getClass(), DummyUserContainerInitializer.class);

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithOneBadUserContainerInitializer() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write("badprovider");
        fw.close();

        UserContainerInitializer.getInstance();

    }

    @Test
    public void testWithTwoGoodUserContainerInitializer() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyUserContainerInitializer.class.getName());
        fw.write('\n');
        fw.write(DummyUserContainerInitializer2.class.getName());
        fw.close();
        Assert.assertTrue(UserContainerInitializer.getInstance().getClass().equals(DummyUserContainerInitializer.class) ||
                UserContainerInitializer.getInstance().getClass().equals(DummyUserContainerInitializer2.class));
    }

    private static abstract class ContainerInitChild extends UserContainerInitializer {

        public static void reset() {
            userContainerInitializer = null;
        }


    }

    /*@Test
    public void testWithTwoCDIProviderOneWithNullCDIAndOneGood() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProviderWithNullCDI.class.getName());
        fw.write('\n');
        fw.write(DummyCDIProvider2.class.getName());
        fw.close();
        Assert.assertTrue(CDI.current().getClass().equals(DummyCDIProvider.DummyCDI.class) ||
                CDI.current().getClass().equals(DummyCDIProvider2.DummyCDI2.class));
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithFirstGoodCDIProvider() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProvider.class.getName());
        fw.write('\n');
        fw.write("badprovider");
        fw.close();
        CDI.current();
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithCDIProviderBadClass() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(getClass().getName());
        fw.close();
        CDI.current();
    }*/
}