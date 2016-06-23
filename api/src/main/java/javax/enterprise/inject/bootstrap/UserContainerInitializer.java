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

package javax.enterprise.inject.bootstrap;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.Extension;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 * Container initializer to bootstrap CDI in Java SE.
 * It is obtained by calling the {@link UserContainerInitializer#getInstance()} static method
 * <p>
 * <p>
 * Typical usage looks like this:
 * </p>
 * <p>
 * <pre>
 * UserContainer<Object> container = UserContainerInitializer.getInstance().initialize();
 * container.select(Foo.class).get();
 * container.event().select(Bar.class).fire(new Bar());
 * container.shutdown();
 * </pre>
 * <p>
 * <p>
 * The {@link UserContainer} interface implements AutoCloseable:
 * </p>
 * <p>
 * <pre>
 * try (UserContainer<Object> container = UserContainerInitializer.getInstance().initialize()) {
 *     container.select(Foo.class).get();
 * }
 * </pre>
 * <p>
 * <p>
 * By default, the discovery is enabled so that all beans from all discovered bean archives are considered. However, it's possible to define a "synthetic" bean
 * archive, or the set of bean classes and enablement respectively:
 * </p>
 * <p>
 * <pre>
 * UserContainer<Object> container = UserContainerInitializer.getInstance().beanClasses(Foo.class, Bar.class).alternatives(Bar.class).initialize());
 * </pre>
 * <p>
 * <p>
 * Moreover, it's also possible to disable the discovery completely so that only the "synthetic" bean archive is considered:
 * </p>
 * <p>
 * <pre>
 * UserContainer<Object> container = UserContainerInitializer.getInstance().disableDiscovery().beanClasses(Foo.class, Bar.class).initialize());
 * </pre>
 * <p>
 * <p>
 * <p>
 * In the same manner, it is possible to explicitly declare interceptors, decorators, extensions and implementation specific options using the builder.
 * </p>
 * <p>
 * <pre>
 * UserContainerInitializer container = UserContainerInitializer.getInstance()
 *    .disableDiscovery()
 *    .packages(Main.class, Utils.class)
 *    .interceptors(TransactionalInterceptor.class)
 *    .property("property", true);
 * UserContainer<Object> container = container.initialize();
 * </pre>
 *
 * @author Antoine Sabot-Durand
 * @author Martin Kouba
 * @author John D. Ament
 * @since 2.0
 */
public abstract class UserContainerInitializer {

    protected static volatile UserContainerInitializer userContainerInitializer = null;


    /**
     * Returns an instance of {@link UserContainerInitializer}
     * Each call returns a new instance
     *
     * @return a new UserContainerInitializer instance.
     * @throws IllegalStateException if called in a Java EE container
     */
    public static UserContainerInitializer getInstance() {
        if (userContainerInitializer == null)
            userContainerInitializer = findUserContainerInitializer();
        return userContainerInitializer;
    }

    private static UserContainerInitializer findUserContainerInitializer() {
        ServiceLoader<UserContainerInitializer> loader;

        loader = ServiceLoader.load(UserContainerInitializer.class, UserContainerInitializer.class.getClassLoader());
        if (!loader.iterator().hasNext()) {
            throw new IllegalStateException("Unable to locate UserContainerInitializer");
        }
        try {
            return loader.iterator().next();
        } catch (ServiceConfigurationError e) {
            throw new IllegalStateException("Error while while processing provider", e);
        }
    }

    /**
     * Add provided bean classes to the synthetic bean archive.
     *
     * @param classes classes to add to the synthetic bean archive
     * @return self
     */
    public abstract UserContainerInitializer addBeanClasses(Class<?>... classes);


    /**
     * All classes from the packages of the specified classes will be added to the set of bean classes for the synthetic bean archive.
     * <p>
     * <p>
     * Note that the scanning possibilities are limited. Therefore, only directories and jar files from the filesystem are supported.
     * </p>
     * <p>
     * <p>
     * Scanning may also have negative impact on bootstrap performance.
     * </p>
     *
     * @param packageClasses classes whose packages will be added to the synthetic bean archive
     * @return self
     */
    public abstract UserContainerInitializer addPackages(Class<?>... packageClasses);

    /**
     * Packages of the specified classes will be scanned and found classes will be added to the set of bean classes for the synthetic bean archive.*
     * <p>
     * <p>
     * Note that the scanning possibilities are limited. Therefore, only directories and jar files from the filesystem are supported.
     * </p>
     * <p>
     * <p>
     * Scanning may also have negative impact on bootstrap performance.
     * </p>
     *
     * @param scanRecursively should subpackages be scanned or not
     * @param packageClasses  classes whose packages will be scanned
     * @return self
     */
    public abstract UserContainerInitializer addPackages(boolean scanRecursively, Class<?>... packageClasses);


    /**
     * All classes from the specified packages will be added to the set of bean classes for the synthetic bean archive.
     * <p>
     * <p>
     * Note that the scanning possibilities are limited. Therefore, only directories and jar files from the filesystem are supported.
     * </p>
     * <p>
     * <p>
     * Scanning may also have negative impact on bootstrap performance.
     * </p>
     *
     * @param packages packages that will be added to the synthetic bean archive
     * @return self
     */
    public abstract UserContainerInitializer addPackages(Package... packages);

    /**
     * All classes from the specified packages will be added to the set of bean classes for the synthetic bean archive.
     * <p>
     * <p>
     * Note that the scanning possibilities are limited. Therefore, only directories and jar files from the filesystem are supported.
     * </p>
     * <p>
     * <p>
     * Scanning may also have negative impact on bootstrap performance.
     * </p>
     *
     * @param scanRecursively should subpackages be scanned or not
     * @param packages        packages that will be added to the synthetic bean archive
     * @return self
     */
    public abstract UserContainerInitializer addPackages(boolean scanRecursively, Package... packages);


    /**
     * Add a given set of {@link AnnotatedType} to the set of discovered types.
     *
     * @param annotatedTypes the AnnotatedTypes to add
     * @return self
     */
    public abstract UserContainerInitializer addAnnotatedTypes(AnnotatedType<?>... annotatedTypes);


    /**
     * Add extensions to the set of extensions.
     *
     * @param extensions extensions to use in the container
     * @return self
     */
    public abstract UserContainerInitializer addExtensions(Extension... extensions);

    /**
     * Add extensions to the set of extensions.
     *
     * @param extensions extensions class to use in the container
     * @return self
     */
    public abstract UserContainerInitializer addExtensions(Class<? extends Extension>... extensions);

    /**
     * Add interceptors classes to the list of enabled interceptors for a synthetic bean archive.
     *
     * @param interceptorClasses classes of the interceptors to enable.
     * @return self
     */
    public abstract UserContainerInitializer addInterceptors(Class<?>... interceptorClasses);


    /**
     * Add decorators for a synthetic bean archive. Decorator classes are automatically added to the set of bean classes for the synthetic bean archive.
     *
     * @param decoratorClasses classes of the decorators to enable.
     * @return self
     */
    public abstract UserContainerInitializer addDecorators(Class<?>... decoratorClasses);


    /**
     * Add alternatives classes for a synthetic bean archive.
     *
     * @param alternativeClasses classes of the alternatives to select
     * @return self
     */
    public abstract UserContainerInitializer addAlternatives(Class<?>... alternativeClasses);


    /**
     * Add alternative stereotypes for a synthetic bean archive.
     *
     * @param alternativeStereotypeClasses alternatives stereotypes to select
     * @return self
     */
    public abstract UserContainerInitializer addAlternativeStereotypes(Class<? extends Annotation>... alternativeStereotypeClasses);


    /**
     * Add a configuration property for the container
     *
     * @param key   property name
     * @param value property value
     * @return self
     */
    public abstract UserContainerInitializer addProperty(String key, Object value);

    /**
     * Set all the configuration properties.
     *
     * @param properties a map containing properties to add
     * @return self
     */
    public abstract UserContainerInitializer properties(Map<String, Object> properties);


    /**
     * Add the provided beans to set of discovered beans.
     *
     * @param beans beans to add
     * @return self
     */
    public abstract UserContainerInitializer addBeans(Bean<?>... beans);


    /**
     * By default, the discovery is enabled. However, it's possible to disable the discovery completely so that only the "synthetic" bean archive is considered.
     *
     * @return self
     */
    public abstract UserContainerInitializer disableDiscovery();


    /**
     * Set a {@link ClassLoader}. The given {@link ClassLoader} will be scanned automatically for bean archives if scanning is enabled.
     *
     * @param classLoader the class loader to use
     * @return self
     */
    public abstract UserContainerInitializer setClassLoader(ClassLoader classLoader);

    /**
     * <p>
     * Initializes a CDI UserContainerInitializer.
     * </p>
     * <p>
     * Cannot be called within an application server.
     * </p>
     *
     * @return the {@link UserContainer} instance associated with the container.
     * @throws UnsupportedOperationException if called within an application server
     */
    public abstract UserContainer<Object> initialize();


}
