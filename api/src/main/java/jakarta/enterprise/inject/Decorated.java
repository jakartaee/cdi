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

package jakarta.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.inject.Qualifier;

/**
 * <p>
 * A decorator may inject metadata about the bean it is decorating
 * </p>
 * 
 * <pre>
 * &#064;Decorator
 * class TimestampLogger implements Logger {
 *     &#064;Inject
 *     &#064;Delegate
 *     &#064;Any
 *     Logger logger;
 * 
 *     &#064;Inject
 *     &#064;Decorated
 *     Bean&lt;Logger&gt; bean;
 * 
 *     void log(String message) {
 *       ...
 *    }
 * }
 * </pre>
 *
 * <p>CDI Lite implementations are not required to provide support for decorators.</p>
 * 
 * @author Pete Muir
 * @since 1.1
 */

@Target({ PARAMETER, FIELD })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface Decorated {
}
