/*
 * Copyright (c) 2022 Red Hat and others
 *
 * This program and the accompanying materials are made available under the
 * Apache Software License 2.0 which is available at:
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.invoke.Invoker;
import jakarta.enterprise.lang.model.declarations.MethodInfo;

/**
 * Opaque token that stands in for an invoker registered using {@link BeanInfo#createInvoker(MethodInfo)}.
 * It can only be used to materialize an {@link Invoker} in a synthetic component; see
 * {@link SyntheticBeanBuilder#withParam(String, InvokerInfo) SyntheticBeanBuilder.withParam()} or
 * {@link SyntheticObserverBuilder#withParam(String, InvokerInfo) SyntheticObserverBuilder.withParam()}.
 *
 * @since 4.1
 */
public interface InvokerInfo {
}
