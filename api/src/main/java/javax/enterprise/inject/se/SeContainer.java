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

package javax.enterprise.inject.se;

import javax.enterprise.inject.spi.CDI;

/**
 * Provides access to the current container in Java SE.
 *
 * @author Antoine Sabot-Durand
 * @author John D. Ament
 * @since 2.0
 */
public abstract class SeContainer<T> extends CDI<T> implements AutoCloseable {


    /**
     * <p>
     * Shuts down this SeContainer instance when it is no longer in scope. Implemented from {@link AutoCloseable},
     * </p>
     */
    @Override
    public abstract void close();


}
