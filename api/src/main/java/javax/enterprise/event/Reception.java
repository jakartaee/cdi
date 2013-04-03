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
package javax.enterprise.event;

/**
 * <p>
 * Distinguishes conditional {@linkplain javax.enterprise.event.Observes observer methods} from observer methods which are
 * always notified.
 * </p>
 * 
 * <p>
 * A conditional observer method is an observer method which is notified of an event only if an instance of the bean that
 * defines the observer method already exists in the current context.
 * </p>
 * 
 * <p>
 * Beans with scope {@link javax.enterprise.context.Dependent &#064;Dependent} may not have conditional observer methods.
 * </p>
 * 
 * @author Gavin King
 * @author Dan Allen
 * @author David Allen
 */
public enum Reception {
    /**
     * <p>
     * Specifies that an observer method is only called if the current instance of the bean declaring the observer method
     * already exists.
     * </p>
     * <p>
     * If there is no active context for the scope to which the bean declaring this observer method belongs, then the observer
     * method is not called.
     * </p>
     */
    IF_EXISTS,

    /**
     * Specifies that an observer method always receives event notifications.
     */
    ALWAYS
}