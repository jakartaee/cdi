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
/**
 * <p>The custom context SPI.</p>
 * 
 * <p>Associated with every 
 * {@linkplain javax.enterprise.context scope type} is a 
 * {@linkplain javax.enterprise.context.spi.Context context object}.
 * The context object implements the semantics of the scope type.</p>
 * 
 * <p>The context implementation collaborates with the container via 
 * the {@link javax.enterprise.context.spi.Context Context} and 
 * {@link javax.enterprise.context.spi.Contextual Contextual} 
 * interfaces to create and destroy contextual instances.</p>
 * 
 * @see javax.enterprise.context
 * @see javax.enterprise.inject.spi
 */
package javax.enterprise.context.spi;

