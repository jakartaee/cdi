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

package javax.enterprise.inject.spi;

/**
 * <p>
 * Identifies the kind of lifecycle callback, EJB timeout method or business method interception.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 * 
 */
public enum InterceptionType {

    /**
     * Intercepts method invocation
     */
    AROUND_INVOKE,

    /**
     * Intercepts a timeout
     */
    AROUND_TIMEOUT,

    /**
     * Intercepts a constructor invocation
     */
    AROUND_CONSTRUCT,

    /**
     * Intercepts bean construction
     */
    POST_CONSTRUCT,

    /**
     * Intercepts bean destruction
     */
    PRE_DESTROY,

    /**
     * Intercepts bean passivation, only called for EJBs
     */
    PRE_PASSIVATE,

    /**
     * Intercepts bean activation, only called for EJBs
     */
    POST_ACTIVATE
}