/*
 * JBoss, Home of Professional Open Source
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

package javax.decorator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Identifies the delegate injection point of a decorator. May be applied to a field, bean constructor parameter or initializer
 * method parameter of a decorator bean class.
 * </p>
 * 
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { 
 *    &#064;Inject &#064;Delegate &#064;Any Logger logger; 
 *    ... 
 * }
 * </pre>
 * 
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { 
 *    private Logger logger;
 *    
 *    &#064;Inject
 *    public TimestampLogger(&#064;Delegate &#064;Debug Logger logger) { 
 *       this.logger=logger; 
 *    } 
 *    ... 
 * }
 * </pre>
 * 
 * <p>
 * A decorator must have exactly one delegate injection point. The delegate injection point must be an injected field,
 * initializer method parameter or bean constructor method parameter.
 * </p>
 * 
 * <p>
 * The container injects a delegate object to the delegate injection point. The delegate object implements the delegate type and
 * delegates method invocations along the decorator stack. When the container calls a decorator during business method
 * interception, the decorator may invoke any method of the delegate object. If a decorator invokes the delegate object at any
 * other time, the invoked method throws an {@link java.lang.IllegalStateException}.
 * </p>
 * 
 * <pre>
 * &#064;Decorator 
 * class TimestampLogger implements Logger { 
 *    &#064;Inject &#064;Delegate &#064;Any Logger logger; 
 *    
 *    void log(String message) {
 *       logger.log( timestamp() + ": " + message );
 *    }
 *    ...
 * }
 * </pre>
 * 
 * @see javax.decorator.Decorator &#064;Decorator specifies that a class is a decorator.
 * 
 * @author Gavin King
 * @author Pete Muir
 */
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface Delegate {
}
