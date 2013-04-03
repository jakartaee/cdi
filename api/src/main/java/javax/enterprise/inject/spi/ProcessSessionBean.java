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
 * The container fires an event of this type for each enabled session bean, before registering the
 * {@link javax.enterprise.inject.spi.Bean} object.
 * </p>
 * 
 * <p>
 * If any observer method of a {@code ProcessSessionBean} event throws an exception, the exception is treated as a definition
 * error by the container.
 * </p>
 * 
 * <p>
 * Note that the type parameter of the super-interface of {@link ProcessSessionBean} is {@link Object} as {@link ProcessBean}
 * allows you access to the {@link Bean}, which in turn allows you to instantiate an instance, which, for interface-view EJBs
 * will not be an instance of X.
 * </p>
 * 
 * @author David Allen
 * @param <X>
 */
public interface ProcessSessionBean<X> extends ProcessManagedBean<Object> {
    /**
     * Returns the EJB name of the session bean.
     * 
     * @return the name of the EJB
     */
    public String getEjbName();

    /**
     * Returns a {@link javax.enterprise.inject.spi.SessionBeanType} representing the kind of session bean.
     * 
     * @return the {@link javax.enterprise.inject.spi.SessionBeanType}
     */
    public SessionBeanType getSessionBeanType();
}