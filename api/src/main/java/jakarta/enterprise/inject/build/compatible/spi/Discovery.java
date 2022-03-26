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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1st phase of {@linkplain BuildCompatibleExtension build compatible extension} execution.
 * Allow adding additional classes to the set of types discovered during type discovery.
 * Also allows registering custom CDI meta-annotations.
 * <p>
 * Methods annotated {@code @Discovery} may declare parameters of these types:
 * <ul>
 * <li>{@link ScannedClasses}</li>
 * <li>{@link MetaAnnotations}</li>
 * <li>{@link Messages}</li>
 * </ul>
 *
 * @since 4.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Discovery {
}
