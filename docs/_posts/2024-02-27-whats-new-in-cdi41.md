---
# Copyright (c) 2024 Red Hat, Inc. and others
#
# This program and the accompanying materials are made available under the
# Apache Software License 2.0 which is available at:
# https://www.apache.org/licenses/LICENSE-2.0.
#
# SPDX-License-Identifier: Apache-2.0

title: "What's new in CDI 4.1?"
author: ladt
tag: development-tips
---

If you're following the activity on the [CDI repository](https://github.com/jakartaee/cdi/), or in Jakarta EE in general, you might have noticed that the CDI 4.1 release is coming soon.
In this short post, we'll take a look at what's new in the wonderful world of CDI.

## Method Invokers

The most important (or at least the biggest) addition to CDI is _method invokers_.
It is not terribly interesting for application developers, but it should be very useful for people who build CDI-based frameworks.

A method invoker is, unsurprisingly, an object that allows invoking a method indirectly.
It implements the `jakarta.enterprise.invoke.Invoker` interface, which is very simple:

```java
interface Invoker<T, R> {
    R invoke(T instance, Object[] arguments) throws Exception;
}
```

CDI extensions can create an invoker for methods (including `static` methods) declared on managed beans.
For example, let's say the application contains this bean:

```java
@Dependent
public class MyService {
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
```

A hypothetical CDI-based framework can decide that it might need to invoke the method at runtime.
During deployment, the CDI extension provided by the framework would use

- `ProcessManagedBean.createInvoker()`, if using a Portable Extension
- `InvokerFactory.createInvoker()`, if using a Build Compatible Extension

to obtain an `InvokerBuilder`. Calling `build()` on it will produce

- an `Invoker` directly, if using a Portable Extension
- an `InvokerInfo` that materializes as an `Invoker` at application runtime, if using a Build Compatible Extension

The `Invoker` then needs to be stored somewhere until the framework actually needs it.
When the time comes to invoke the method, the framework takes the `Invoker`, obtains an instance of `MyService` and all the parameter values, and calls

```java
invoker.invoke(myService, new Object[] { "world" })
```

The return value is `"Hello world!"`, as expected.

### Lookups

It is fairly common that some of the parameters the method accepts are CDI beans.
It is possible to configure the `InvokerBuilder` to resolve and instantiate a correct value for such parameter automatically.
In fact, this is possible also for the instance on which the method should be invoked.
The `InvokerBuilder` has methods `withInstanceLookup()` and `withArgumentLookup()` to configure that.

Returning back to the example above, if the CDI extension called `withInstanceLookup()` before calling `InvokerBuilder.build()`, it wouldn't have to obtain the instance of `MyService` manually.
The invoker would do it automatically.
The invocation would just use `null` as the instance value:

```java
invoker.invoke(null, new Object[] { "world" })
```

The result is still the same: `"Hello world!"`.

## Other Improvements

There's a couple of quality of life improvements in this release, too.

Interceptors can now access the interceptor binding annotations from `InvocationContext`, using methods `getInterceptorBinding()` and `getInteceptorBindings()`.
This is technically a change in the Interceptors specification, but is most useful for CDI programmers, so we mention it here as well. 

Producer methods and fields can now declare a `@Priority` directly (or obtain a priority from a stereotype); they no longer have to rely on the priority value of their declaring bean.
When both the producer and its declaring bean have a priority, the producer's priority wins.

The newly added methods `BeanContainer.isMatchingBean()` and `isMatchingEvent()` provide programmatic access to the typesafe resolution and observer resolution rules.
This is useful for frameworks that want to perform the same matching as CDI, but don't want to reimplement the rules themselves.

The newly added method `BeanContainer.getContexts()` returns context objects of all registered contexts for given scope, regardless of whether they are active or inactive.
This is useful for authors of Build Compatible Extensions, who don't provide a context object to the container directly.
Instead, they provide a _class_ of the context object, and they need to use this method to gain access to the context object which the CDI container creates internally.

## Deprecations

CDI 4.1 prepares for removing a dependency on Unified EL from the core CDI API (`cdi-api.jar`).
This dependency is very narrow and really only useful for application server authors, but it's still there.

Specifically, there are 2 methods on the `BeanManager` interface that use EL types in their signatures:

- `getELResolver()`, which returns `ELResolver`
- `wrapExpressionFactory()`, which accepts and returns `ExpressionFactory`

These 2 methods are deprecated in CDI 4.1 and shall be removed in CDI 5.0.

The suggested replacement is the `ELAwareBeanManager` interface, which extends `BeanManager` and contains the same 2 methods, but exists outside of the core CDI API, in a supplemental CDI EL API artifact (`cdi-el-api.jar`).

As mentioned above, these methods are mainly used by application servers, so this shouldn't really affect CDI users.
Also, CDI users don't have to consume the new supplemental artifact; compiling against the existing CDI API is fine.

## CDI EE Specification Moved

This is more of a curiosity from user perspective, but relatively important change internally.

The _CDI in Jakarta EE_ part of the CDI specification, or _CDI EE_ for short, was moved from the CDI specification to the Jakarta EE Platform specification.
Together with that, the corresponding part of the CDI TCK (the `web` module, basically) was moved to the Jakarta EE Platform TCK.

This doesn't affect CDI users, but is important for CDI implementations.

## Conclusion

CDI 4.1 is not a huge release, but it contains improvements useful for both application developers and framework authors.
If you have any questions, the friendly mailing list `cdi-dev@eclipse.org` is a great place to ask!
