/*
 * Copyright 2023, Red Hat, Inc., and individual contributors
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
package jakarta.enterprise.inject.spi.el;

import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.spi.BeanManager;

/**
 * A {@link BeanManager} that allows integrators to obtain Unified EL objects that are
 * integrated with the CDI container as described in the Jakarta EE Platform specification.
 *
 * @since 4.1
 */
public interface ELAwareBeanManager extends BeanManager {
    /**
     * Returns a {@link jakarta.el.ELResolver} that resolves beans by EL name.
     *
     * @return the {@link jakarta.el.ELResolver}
     */
    public ELResolver getELResolver();

    /**
     * Returns a wrapper {@link jakarta.el.ExpressionFactory} that delegates {@link jakarta.el.MethodExpression} and
     * {@link jakarta.el.ValueExpression} creation to the given {@link jakarta.el.ExpressionFactory}. When a Unified EL
     * expression is evaluated using a {@link jakarta.el.MethodExpression} or {@link jakarta.el.ValueExpression} returned by the
     * wrapper {@link jakarta.el.ExpressionFactory}, the container handles destruction of objects with scope {@link Dependent}.
     *
     * @param expressionFactory the {@link jakarta.el.ExpressionFactory} to wrap
     * @return the wrapped {@link jakarta.el.ExpressionFactory}
     */
    public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory);

}
