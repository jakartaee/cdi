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
 * Identifies the kind of Jakarta Enterprise Bean session bean.
 * 
 * @author Gavin King
 * 
 */
public enum SessionBeanType {
    /**
     * A {@linkplain javax.ejb.Stateless stateless} session bean
     */
    STATELESS,
    /**
     * A {@linkplain javax.ejb.Stateful stateful} session bean
     */
    STATEFUL,
    /**
     * A {@linkplain javax.ejb.Singleton singleton} session bean
     */
    SINGLETON
}