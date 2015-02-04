package org.jboss.cdi.tck.audit;

import java.net.URL;

/**
 * @author Tomas Remes
 */
public class Test {

    private String testMethodName;

    private String testPackageName;
    private URL refUrl;

    public Test(String testMethodName, URL refUrl, String testPackageName) {
        this.testMethodName = testMethodName;
        this.refUrl = refUrl;
        this.testPackageName = testPackageName;
    }

    public String getTestMethodName() {
        return testMethodName;
    }

    public URL getRefUrl() {
        return refUrl;
    }

}
