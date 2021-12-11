module jakarta.cdilight {
    exports jakarta.enterprise.inject.build.compatible.spi;

    requires transitive jakarta.annotation;
    requires transitive jakarta.interceptor;
    requires transitive jakarta.cdi.lang.model;
    requires transitive jakarta.inject;
    requires transitive jakarta.cdi;
    requires static jakarta.el;
    // For javadoc
    requires static java.naming;

    uses jakarta.enterprise.inject.build.compatible.spi.BuildServices;
}