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

import java.lang.annotation.Annotation;

/**
 * A component that is able to control a specific context type
 *
 * @param <T> the context type for this controller
 *
 * @since 2.0
 * @author John D. Ament
 */
public interface ContextController<T extends Annotation> {

   /**
    * @return the underlying scope type for this ContextController
    */
   T getScope();
}
