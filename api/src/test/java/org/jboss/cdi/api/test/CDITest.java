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

package org.jboss.cdi.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.CDIProvider;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Test for CDIProvider resolution in CDI abstract class.
 * @author Antoine Sabot-durand
 *
 */
public class CDITest {

    private static final String SERVICE_PATH = System.getProperty("serviceDir");
    private static final String SERVICE_FILE_NAME = SERVICE_PATH + CDIProvider.class.getName();

    private static abstract class CDIChild<T> extends CDI<T> {

        public static void resetCDI() {
            cachedProvider = null;
            discoveredProviders = null;
        }


    }

    @BeforeMethod
    public void setUp() throws Exception {
        CDIChild.resetCDI();
        Files.deleteIfExists(FileSystems.getDefault().getPath(SERVICE_FILE_NAME));

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithoutServiceFile() throws Exception {
        CDI.current();
    }


    @Test
    public void testWithOneGoodCDIProvider() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProvider.class.getName());
        fw.close();
        Assert.assertEquals(CDI.current().getClass(), DummyCDIProvider.DummyCDI.class);

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithCDIProviderHavingNullCDI() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProviderWithNullCDI.class.getName());
        fw.close();
        Assert.assertEquals(CDI.current().getClass(), DummyCDIProvider.DummyCDI.class);

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testWithOneBadCDIProvider() throws Exception {

        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write("badprovider");
        fw.close();

        CDI.current();

    }

    @Test
    public void testWithTwoGoodCDIProvider() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProvider2.class.getName());
        fw.write('\n');
        fw.write(DummyCDIProvider.class.getName());
        fw.close();
        Assert.assertTrue(CDI.current().getClass().equals(DummyCDIProvider.DummyCDI.class));
    }


    @Test
    public void testWithTwoGoodCDIProviderReverse() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProvider.class.getName());
        fw.write('\n');
        fw.write(DummyCDIProvider2.class.getName());
        fw.close();
        Assert.assertTrue(CDI.current().getClass().equals(DummyCDIProvider.DummyCDI.class));
    }

    @Test
    public void testWithTwoCDIProviderOneWithNullCDIAndOneGood() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProviderWithNullCDI.class.getName());
        fw.write('\n');
        fw.write(DummyCDIProvider2.class.getName());
        fw.close();
        Assert.assertTrue(CDI.current().getClass().equals(DummyCDIProvider2.DummyCDI2.class));
    }


    @Test
    public void testWithThreeCDIProviderOneWithNullCDIAndOthersGood() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(DummyCDIProviderWithNullCDI.class.getName());
        fw.write('\n');
        fw.write(DummyCDIProvider2.class.getName());
        fw.write('\n');
        fw.write(DummyCDIProvider.class.getName());
        fw.close();
        Assert.assertTrue(CDI.current().getClass().equals(DummyCDIProvider.DummyCDI.class));
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
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testForClosedContainer() throws Exception {
        FileWriter fw = new FileWriter(SERVICE_FILE_NAME);
        fw.write(ClosableCDIProvider.class.getName());
        fw.close();
        CDI.current();
        ClosableCDIProvider.closeContainer();
        CDI.current();
    }
}