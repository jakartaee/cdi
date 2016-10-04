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

package javax.enterprise.context.control;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The container provides a built in interceptor that may be used to annotate classes and methods to indicate
 * that a request context should be activated when this method is invoked.
 *
 * The request context will be activated before the method is called, and deactivated when the method invocation is
 * complete (regardless of any exceptions being thrown).  If the context is already active, it is ignored, neither
 * activated nor deactivated.
 *
 * @since 2.0
 * @author John D. Ament
 */
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
@Documented
public @interface ActivateRequestContext {
}