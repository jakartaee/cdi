module jakarta.cdi {
    exports jakarta.decorator;
    exports jakarta.enterprise.context;
    exports jakarta.enterprise.context.control;
    exports jakarta.enterprise.context.spi;
    exports jakarta.enterprise.event;
    exports jakarta.enterprise.inject;
    exports jakarta.enterprise.inject.build.compatible.spi;
    exports jakarta.enterprise.inject.literal;
    exports jakarta.enterprise.inject.se;
    exports jakarta.enterprise.inject.spi;
    exports jakarta.enterprise.inject.spi.configurator;
    exports jakarta.enterprise.util;

    requires transitive jakarta.annotation;
    requires transitive jakarta.interceptor;
    requires transitive jakarta.cdi.model;
    requires transitive jakarta.inject;
    requires static jakarta.el;
    // For javadoc
    requires static java.naming;
    //TODO: requires static jakarta.transation;

    uses jakarta.enterprise.inject.se.SeContainerInitializer;
    uses jakarta.enterprise.inject.spi.CDIProvider;
}