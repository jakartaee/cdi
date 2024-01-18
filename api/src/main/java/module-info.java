/*
 * Copyright 2021, Red Hat, Inc., and individual contributors
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
    exports jakarta.enterprise.invoke;
    exports jakarta.enterprise.util;

    requires transitive jakarta.annotation;
    requires transitive jakarta.interceptor;
    requires transitive jakarta.cdi.lang.model;
    requires transitive jakarta.inject;
    requires static jakarta.el;
    // For javadoc
    requires static java.naming;
    //TODO: requires static jakarta.transation;

    uses jakarta.enterprise.inject.se.SeContainerInitializer;
    uses jakarta.enterprise.inject.spi.CDIProvider;
    uses jakarta.enterprise.inject.build.compatible.spi.BuildServices;
}