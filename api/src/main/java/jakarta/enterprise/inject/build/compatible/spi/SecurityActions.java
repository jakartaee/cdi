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

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;

/**
 * This utility class is used to optimize invocation made through the SecurityManager
 *
 * @author Antoine Sabot-durand
 */

final class SecurityActions {
    private SecurityActions() {
    }

    static <T> ServiceLoader<T> loadService(Class<T> service, ClassLoader classLoader) {
        return AccessController.doPrivileged(
                (PrivilegedAction<ServiceLoader<T>>) () -> ServiceLoader.load(service, classLoader)
        );
    }
}
