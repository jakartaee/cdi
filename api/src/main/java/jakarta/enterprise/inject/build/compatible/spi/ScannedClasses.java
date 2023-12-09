/*
 * Copyright (c) 2021 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

/**
 * Allows adding additional classes to the set of types discovered during type discovery.
 * Such classes will therefore be scanned during bean discovery. Annotations on these classes
 * can later be transformed using {@link Enhancement @Enhancement}.
 *
 * @since 4.0
 */
public interface ScannedClasses {
    /**
     * Adds a class with given name to the set of types discovered during type discovery.
     * The class will therefore be scanned during bean discovery.
     * <p>
     * Adding the same class multiple times, or adding a class that is automatically discovered
     * by the container, leads to non-portable behavior.
     *
     * @param className binary name of the class, as defined by <cite>The Java&trade; Language Specification</cite>;
     *        in other words, the class name as returned by {@link Class#getName()}
     */
    void add(String className);
}
