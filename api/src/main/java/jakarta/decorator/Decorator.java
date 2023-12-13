/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

package jakarta.decorator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.inject.Stereotype;

/**
 * <p>
 * Specifies that a class is a decorator. May be applied to a managed bean class.
 * </p>
 *
 * <pre>
 * &#064;Decorator
 * class TimestampLogger implements Logger { ... }
 * </pre>
 *
 * <p>
 * Decorators of a session bean must comply with the bean provider programming restrictions defined by the EJB specification.
 * Decorators of a stateful session bean must comply with the rules for instance passivation and conversational state defined by
 * the EJB specification.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for decorators.
 * </p>
 *
 * @see Delegate &#064;Delegate identifies the delegate injection point of a decorator.
 *
 * @author Gavin King
 * @author Pete Muir
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Stereotype
public @interface Decorator {
}
