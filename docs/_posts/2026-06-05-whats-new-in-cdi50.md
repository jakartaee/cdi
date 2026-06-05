---
# Copyright 2026, Contributors to the Eclipse Foundation
#
# This program and the accompanying materials are made available under the
# Apache Software License 2.0 which is available at:
# https://www.apache.org/licenses/LICENSE-2.0.
#
# SPDX-License-Identifier: Apache-2.0

title: "What's new in CDI 5.0?"
author: matn
tag: development-tips
---

It's been a busy time in the CDI world.
CDI 5.0 is on its way, bringing several long-requested additions along with it.
This release also raises the minimum Java version to 17.
In this post, we'll go through the most important changes — starting with one that requires your attention even if you don't plan to use any of the new features.

## Maven Coordinate Changes

Before diving into the new features, let's cover an important housekeeping matter.
The Maven coordinates (GAV) of all CDI API artifacts have changed in CDI 5.0 — both the `groupId` and `artifactId`:

| Old (CDI 4.x) | New (CDI 5.0) |
|---|---|
| `jakarta.enterprise:jakarta.enterprise.cdi-api` | `jakarta.cdi:jakarta.cdi-api` |
| `jakarta.enterprise:jakarta.enterprise.cdi-el-api` | `jakarta.cdi:jakarta.cdi-el-api` |
| `jakarta.enterprise:jakarta.enterprise.lang-model` | `jakarta.cdi:jakarta.cdi-lang-model-api` |

Relocation artifacts are published under the old coordinates, so Maven and Gradle will resolve them automatically for now.
However, relocation artifacts will only be present for the 5.0 release — they will not be carried over to future versions.
Please update your POM files or build scripts as soon as possible.

The CDI TCK artifacts have undergone a similar coordinate change but do not provide relocation artifacts.

## Eager Initialization

`@ApplicationScoped` beans are normally created lazily — the container only instantiates them when they are first accessed.
Sometimes, however, you want a bean to be ready as soon as the application starts.

A common pattern for this was to declare a `Startup` event observer, but that is more ceremony than needed when the bean's constructor or `@PostConstruct` already does everything.
CDI 5.0 adds `@Eager` as a dedicated annotation for this case.
`Startup` event observers remain valuable when you need to execute more complex initialization logic.

```java
@ApplicationScoped
@Eager
public class StartupService {

    @PostConstruct
    public void init() {
        // executed during application startup,
        // no need to inject this bean anywhere
    }
}
```

An eagerly initialized bean is instantiated no later than when the container fires the `Startup` event.
It also works on producer methods:

```java
@ApplicationScoped
public class ServiceProducer {

    @Produces
    @ApplicationScoped
    @Eager
    public CacheService createCache() {
        return new CacheService();
    }
}
```

`@Eager` can also be placed on a stereotype.
Note that only `@ApplicationScoped` beans may be eagerly initialized — using `@Eager` on a `@RequestScoped` or `@Dependent` bean is a definition error.
That said, `@Singleton` beans are likely to be supported by major implementations as well.

## Reserve Beans

CDI has long had `@Alternative` as a way to provide a "more important" implementation of a bean.
However, this is quite cumbersome in one common use case: providing a default implementation that should be used only when no other bean is available.

CDI 5.0 introduces `@Reserve` for exactly this purpose: to provide a "less important" implementation of a bean.
A reserve bean only participates in typesafe resolution when no other non-reserve bean matches.
Consider the following example:

```java
public interface Greeter {
    String greet(String name);
}
```

```java
@Dependent
@Reserve
@Priority(1)
public class DefaultGreeter implements Greeter {

    public String greet(String name) {
        return "Hello " + name + "!";
    }
}
```

```java
@Dependent
public class CustomGreeter implements Greeter {

    public String greet(String name) {
        return "Hi " + name + ", welcome back!";
    }
}
```

When `CustomGreeter` is present, it wins — it's a regular bean and takes precedence over any reserve.
If it were removed, `DefaultGreeter` would kick in as the fallback.
When multiple reserves are available, `@Priority` determines which one is selected.

Note that `@Alternative` and `@Reserve` are mutually exclusive — combining them on the same bean is a definition error.

## Automatic Resource Cleanup

Beans that manage external resources often implement `AutoCloseable`.
Until now, you had to write a `@PreDestroy` method that delegated to `close()` — a small but repetitive boilerplate.

CDI 5.0 adds the `@AutoClose` annotation.
When a bean is annotated `@AutoClose` and implements `AutoCloseable`, the container automatically calls `close()` when the bean is destroyed:

```java
@Dependent
@AutoClose
public class ManagedConnection implements AutoCloseable {

    public ManagedConnection() {
        // open connection
    }

    @Override
    public void close() throws Exception {
        // connection is closed automatically
        // when the bean is destroyed
    }
}
```

If the bean also has a `@PreDestroy` method, it is called first, and `close()` is called afterwards.
The behavior is opt-in — if a bean implements `AutoCloseable` but is not annotated `@AutoClose`, the container does not call `close()`.

## Synthetic Bean Injection Points

Build Compatible Extensions can create _synthetic beans_ — beans that don't correspond to a class in the application, but are defined programmatically by an extension.
Until CDI 5.0, the creation function of a synthetic bean received an `Instance<Object>` through which it could resolve any bean in the container dynamically.

CDI 5.0 introduces a more explicit approach.
Extensions can now declare the exact injection points of a synthetic bean using `withInjectionPoint()`.
The creation function then receives a `SyntheticInjections` object to look up those specific dependencies:

```java
public class MyExtension implements BuildCompatibleExtension {

    @Synthesis
    public void register(SyntheticComponents syn) {
        syn.addBean(MyPojo.class)
                .type(MyPojo.class)
                .scope(Dependent.class)
                .withInjectionPoint(MyService.class)
                .createWith(MyPojoCreator.class);
    }
}
```

```java
public class MyPojoCreator implements SyntheticBeanCreator<MyPojo> {

    @Override
    public MyPojo create(SyntheticInjections injections, Parameters params) {
        MyService service = injections.get(MyService.class);
        return new MyPojo(service);
    }
}
```

Because the dependencies are declared up front, the container can validate injection points at deployment time and perform optimizations in build-time environments.
The old `SyntheticBeanCreator.create(Instance<Object>, Parameters)` method is deprecated for removal.

## Further Additions

There are several more additions in CDI 5.0 that are worth noting:

- Asynchronous invokers — the `AsyncHandler` SPI allows framework authors to handle async method invocations through method invokers, with built-in support for `CompletionStage`, `CompletableFuture`, and `Flow.Publisher`.
- Introduction of the _global scope_ concept — a global scope has exactly one context object, active on all threads for the entire lifetime of the container. `@ApplicationScoped` and `@Dependent` are global scopes; `@Singleton` is likely to be treated as global by major implementations as well.
- Programmatic registration of `BuildCompatibleExtension` in CDI SE via `SeContainerInitializer`.
- `@Registration` now allows parameterized types.
- `InjectionPoint` behavior is now defined for beans obtained via `CDI.current()` (previously unspecified and implementation-dependent).

## Removals and Breaking Changes

- `SecurityManager` usage and references have been removed.
- Deprecated `BeanManager` EL methods have been removed (replaced by `ELAwareBeanManager` since CDI 4.1).
- Trimming is now forbidden in non-explicit bean archives.
- Minimum Java version raised to 17.

## Conclusion

CDI 5.0 is a significant release with features useful for both application developers and framework authors.
If you have any questions, suggestions, or ideas, feel free to reach out on the `cdi-dev@eclipse.org` mailing list or open an issue on the [CDI GitHub repository](https://github.com/jakartaee/cdi/issues).
