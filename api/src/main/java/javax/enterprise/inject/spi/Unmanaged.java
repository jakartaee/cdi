/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

import javax.enterprise.context.spi.CreationalContext;

/**
 * <p>
 * Helper class for injecting and calling lifecycle callbacks unmanaged instances for use by framework and library integrators.
 * </p>
 * 
 * <pre>
 * Unmanaged&lt;Foo&gt; unmanagedFoo = new Unmanaged&lt;Foo&gt;(Foo.class);
 * UnmanagedInstance&lt;Foo&gt; fooInstance = unmanagedFoo.newInstance();
 * Foo foo = fooInstance.produce().inject().postConstruct().get();
 * ... // Use the foo instance
 * fooInstance.preDestroy().dispose();
 * </pre>
 * 
 * <p>
 * An instance of this class can be safely held for the lifetime of the application.
 * </p>
 * 
 * <p>
 * {@link UnmanagedInstance}s created by this class are not suitable for sharing between threads.
 * </p>
 * 
 * @author Pete Muir
 * @since 1.1
 * @param <T> type of unmanaged instances
 */
public class Unmanaged<T> {

    private final InjectionTarget<T> injectionTarget;
    private final BeanManager beanManager;

    /**
     * Create an injector for the given class
     * @param manager the {@link BeanManager}
     * @param clazz class of the unmanaged instances
     */
    public Unmanaged(BeanManager manager, Class<T> clazz) {
        this.beanManager = manager;
        AnnotatedType<T> type = manager.createAnnotatedType(clazz);
        this.injectionTarget = manager.getInjectionTargetFactory(type).createInjectionTarget(null);
    }

    /**
     * Create an injector for the given class, using the current bean manager
     * @param clazz class of the unmanaged instances
     */
    public Unmanaged(Class<T> clazz) {
        this(CDI.current().getBeanManager(), clazz);
    }

    /**
     * Instantiate a new UnmanagedInstance
     *
     * @return a new {@link UnmanagedInstance}
     */
    public UnmanagedInstance<T> newInstance() {
        return new UnmanagedInstance<>(beanManager, injectionTarget);
    }

    /**
     * Represents a non-contextual instance.
     * 
     * @see Unmanaged
     */
    public static class UnmanagedInstance<T> {

        private final CreationalContext<T> ctx;
        private final InjectionTarget<T> injectionTarget;
        private T instance;
        private boolean disposed = false;

        private UnmanagedInstance(BeanManager beanManager, InjectionTarget<T> injectionTarget) {
            this.injectionTarget = injectionTarget;
            this.ctx = beanManager.createCreationalContext(null);
        }

        /**
         * Get the instance
         * @return the instance
         */
        public T get() {
            return instance;
        }

        /**
         * Create the instance
         * 
         * @throws IllegalStateException if produce() is called on an already produced instance
         * @throws IllegalStateException if produce() is called on an instance that has already been disposed
         * @return self
         */
        public UnmanagedInstance<T> produce() {
            if (instance != null) {
                throw new IllegalStateException("Trying to call produce() on already constructed instance");
            }
            if (disposed) {
                throw new IllegalStateException("Trying to call produce() on an already disposed instance");
            }
            this.instance = injectionTarget.produce(ctx);
            return this;
        }

        /**
         * Inject the instance
         * 
         * @throws IllegalStateException if inject() is called before produce() is called
         * @throws IllegalStateException if inject() is called on an instance that has already been disposed
         * @return self
         */
        public UnmanagedInstance<T> inject() {
            if (instance == null) {
                throw new IllegalStateException("Trying to call inject() before produce() was called");
            }
            if (disposed) {
                throw new IllegalStateException("Trying to call inject() on already disposed instance");
            }
            injectionTarget.inject(instance, ctx);
            return this;
        }

        /**
         * Call the @PostConstruct callback
         * 
         * @throws IllegalStateException if postConstruct() is called before produce() is called
         * @throws IllegalStateException if postConstruct() is called on an instance that has already been disposed
         * @return self
         */
        public UnmanagedInstance<T> postConstruct() {
            if (instance == null) {
                throw new IllegalStateException("Trying to call postConstruct() before produce() was called");
            }
            if (disposed) {
                throw new IllegalStateException("Trying to call postConstruct() on already disposed instance");
            }
            injectionTarget.postConstruct(instance);
            return this;
        }

        /**
         * Call the @PreDestroy callback
         * 
         * @throws IllegalStateException if preDestroy() is called before produce() is called
         * @throws IllegalStateException if preDestroy() is called on an instance that has already been disposed
         * @return self
         */
        public UnmanagedInstance<T> preDestroy() {
            if (instance == null) {
                throw new IllegalStateException("Trying to call preDestroy() before produce() was called");
            }
            if (disposed) {
                throw new IllegalStateException("Trying to call preDestroy() on already disposed instance");
            }
            injectionTarget.preDestroy(instance);
            return this;
        }

        /**
         * Dispose of the instance, doing any necessary cleanup
         * 
         * @throws IllegalStateException if dispose() is called before produce() is called
         * @throws IllegalStateException if dispose() is called on an instance that has already been disposed
         * @return self
         */
        public UnmanagedInstance<T> dispose() {
            if (instance == null) {
                throw new IllegalStateException("Trying to call dispose() before produce() was called");
            }
            if (disposed) {
                throw new IllegalStateException("Trying to call dispose() on already disposed instance");
            }
            disposed = true;
            injectionTarget.dispose(instance);
            ctx.release();
            return this;
        }

    }

}
