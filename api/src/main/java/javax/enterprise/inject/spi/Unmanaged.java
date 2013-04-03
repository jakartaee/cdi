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
 */
public class Unmanaged<T> {

    private final InjectionTarget<T> injectionTarget;
    private final BeanManager beanManager;

    /**
     * Create an injector for the given class
     */
    public Unmanaged(BeanManager manager, Class<T> clazz) {
        this.beanManager = manager;
        AnnotatedType<T> type = manager.createAnnotatedType(clazz);
        this.injectionTarget = manager.getInjectionTargetFactory(type).createInjectionTarget(null);
    }

    /**
     * Create an injector for the given class, using the current bean manager
     */
    public Unmanaged(Class<T> clazz) {
        this(CDI.current().getBeanManager(), clazz);
    }

    public UnmanagedInstance<T> newInstance() {
        return new UnmanagedInstance<T>(beanManager, injectionTarget);
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
         */
        public T get() {
            return instance;
        }

        /**
         * Create the instance
         * 
         * @throws IllegalStateException if produce() is called on an already produced instance
         * @throws IllegalStateException if produce() is called on an instance that has already been disposed
         */
        public UnmanagedInstance<T> produce() {
            if (this.instance != null) {
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
         */
        public UnmanagedInstance<T> inject() {
            if (this.instance == null) {
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
         */
        public UnmanagedInstance<T> postConstruct() {
            if (this.instance == null) {
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
         */
        public UnmanagedInstance<T> preDestroy() {
            if (this.instance == null) {
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
         */
        public UnmanagedInstance<T> dispose() {
            if (this.instance == null) {
                throw new IllegalStateException("Trying to call dispose() before produce() was called");
            }
            if (disposed) {
                throw new IllegalStateException("Trying to call dispose() on already disposed instance");
            }
            injectionTarget.dispose(instance);
            ctx.release();
            return this;
        }

    }

}