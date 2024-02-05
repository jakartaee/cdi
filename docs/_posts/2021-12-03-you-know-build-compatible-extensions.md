<!--
    Copyright (c) 2021 Red Hat, Inc. and others
    
    This program and the accompanying materials are made available under the
    Apache Software License 2.0 which is available at:
    https://www.apache.org/licenses/LICENSE-2.0.
    
    SPDX-License-Identifier: Apache-2.0
-->
---
title: "You already know Build Compatible Extensions"
summary: Comparison of Portable Extensions and Build Compatible Extensions
layout: post
author: ladt
tag: development-tips
priority: 1
change_frequency: daily
---

As you have learned from a [previous post](https://eclipse-ee4j.github.io/cdi/2021/10/25/way-to-cdi4.html), CDI Lite comes with a new extension API called _Build Compatible Extensions_.
In this post, you will learn more about this API by comparison with _Portable Extensions_ and my hope is that by the end, you will see that _you already know Build Compatible Extensions_.

If you don't know Portable Extensions, don't worry.
The Build Compatible Extensions API has detailed documentation and all examples in this text should be easy to follow for anyone.

## Extension API

Build Compatible Extensions, similarly to Portable Extensions, are service providers for the `BuildCompatibleExtension` interface.
Contrary to Portable Extensions, Build Compatible Extensions do not declare observer methods for CDI events.
This is a major design goal of the entire CDI Lite effort: make it possible to perform bean discovery before the application is started, such as during compilation.
At that point in time, the CDI container obviously doesn't exist, so it can't deliver events.

That said, Build Compatible Extensions are still pretty close to what you're used to.
Instead of observer methods, you declare methods annotated with one of the execution phase annotations.
On these methods, you then declare parameters that let you affect the bean discovery process.
Each execution phase allows a certain set of parameter types to be declared.

The Build Compatible Extensions API has 5 execution phases.
These phases roughly correspond to Portable Extensions lifecycle events:

| Build Compatible Extensions | Portable Extensions                                |
|-----------------------------|----------------------------------------------------|
| `@Discovery`                | `BeforeBeanDiscovery`                              |
| `@Enhancement`              | `ProcessAnnotatedType`                             |
| `@Registration`             | `ProcessBean`, `ProcessObserverMethod` (read-only) |
| `@Synthesis`                | `AfterBeanDiscovery`                               |
| `@Validation`               | `AfterDeploymentValidation`                        |

This covers the most important events in Portable Extensions.
One significant part is intentionally missing, and that's an equivalent of the `ProcessBeanAttributes` event, plus the modification part of `ProcessObserverMethod`.
If deemed necessary, these could be added to Build Compatible Extensions in the future.

A simple _Hello, world!_ extension would look like this:

```java
// the corresponding META-INF/services/jakarta.enterprise.inject.build.compatible.spi.BuildCompatibleExtension file must also exist
public class MyExtension implements BuildCompatibleExtension {
    @Discovery
    public void hello(Messages msg) {
        msg.info("Hello, world!");
    }
}
```

`Messages` is one of the types that extension methods can declare as a parameter.
Let's take a look at all the phases and compare them to Portable Extensions in more detail.

### @Discovery

In `@Discovery`, you can add types to be scanned during bean discovery and register custom meta-annotations.
Extension methods annotated `@Discovery` can declare parameters of these types:

- `MetaAnnotations`: register custom meta-annotations (qualifiers, interceptor bindings, stereotypes, and scopes)
- `ScannedClasses`: add types to the set of types discovered during type discovery, so that they are scanned during bean discovery
- `Messages`: logging and validation

This is very similar to the `BeforeBeanDiscovery` event:

| Build Compatible Extensions             | Portable Extensions                                              |
|-----------------------------------------|------------------------------------------------------------------|
| `ScannedClasses.add`                    | `BeforeBeanDiscovery.addAnnotatedType`                           |
| `MetaAnnotations.addQualifier`          | `BeforeBeanDiscovery.configureQualifier`                         |
| `MetaAnnotations.addInterceptorBinding` | `BeforeBeanDiscovery.configureInterceptorBinding`                |
| `MetaAnnotations.addStereotype`         | `BeforeBeanDiscovery.addStereotype`                              |
| `MetaAnnotations.addContext`            | `BeforeBeanDiscovery.addScope` + `AfterBeanDiscovery.addContext` |

One noticeable difference is that when adding a custom scope, you don't provide a _context object_.
Instead, you provide a _class_ of the context object, which is then instantiated at runtime (and so must have a public zero-parameter constructor).

### @Enhancement

In `@Enhancement`, you can look at discovered types and modify annotations on them.
Extension methods annotated `@Enhancement` can declare parameters of these types:

- `ClassInfo`: inspect classes that satisfy given criteria (see below)
- `MethodInfo`: inspect methods that satisfy given criteria (see below)
- `FieldInfo`: inspect fields that satisfy given criteria (see below)
- `ClassConfig`: transform annotations on classes that satisfy given criteria (see below)
- `MethodConfig`: transform annotations on methods that satisfy given criteria (see below)
- `FieldConfig`: transform annotations on fields that satisfy given criteria (see below)
- `Types`: utility to create instances of `Type`
- `Messages`: logging and validation

Each `@Enhancement` method must declare exactly 1 parameter of type `ClassInfo`, `MethodInfo`, `FieldInfo`, `ClassConfig`, `MethodConfig`, or `FieldConfig`.

When the method declares a parameter of type `ClassInfo` or `ClassConfig`, it is called once for each matching type.
When it declares a parameter of type `MethodInfo` or `MethodConfig`, it is called once for each method and constructor of each matching type.
Finally, when it declares a parameter of type `FieldInfo` or `FieldConfig`, it is called once for each field of each matching type.

How are these "matching types" determined?
The `@Enhancement` annotation itself has members that, collectively, express this set:

- `types` is a simple set of matching types -- this member is mandatory;
- `withSubtypes` is a boolean flag that says whether all subtypes of given `types` are also matching;
- `withAnnotations` is a set of annotation types that must be present on the types so that they match.

The `types` and `withSubtypes` members corresponds to a type argument that you would apply to the `ProcessAnnotatedType` generic event type.
The `withAnnotations` member corresponds to the `@WithAnnotations` annotation.

As an example, let's move a qualifier from one class to another:

```java
// 1. a qualifier annotation
@Qualifier
@Retention(RUNTIME)
public @interface MyQualifier {
}

// 2. a service interface
public interface MyService {
    String hello();
}

// 3. two implementations, one with qualifier and the other unqualified
@Singleton
@MyQualifier
public class MyFooService implements MyService {
    @Override
    public String hello() {
        return "foo";
    }
}

@Singleton
public class MyBarService implements MyService {
    @Override
    public String hello() {
        return "bar";
    }
}

// 4. a class that uses the service
@Singleton
public class MyServiceUser {
    @Inject
    @MyQualifier
    MyService myService;
}

// 5. the extension
public class MyExtension implements BuildCompatibleExtension {
    @Enhancement(types = MyFooService.class)
    public void foo(ClassConfig clazz) {
        clazz.removeAnnotation(it -> it.name().equals(MyQualifier.class.getName()));
    }

    @Enhancement(types = MyBarService.class)
    public void bar(ClassConfig clazz) {
        clazz.addAnnotation(MyQualifier.class);
    }
}
```

The API is used somewhat differently than `ProcessAnnotatedType`, because in Portable Extensions, you first have to get an `AnnotatedTypeConfigurator`.
From there, however, the correspondence is pretty clear:

| Build Compatible Extensions            | Portable Extensions                                                                         |
|----------------------------------------|---------------------------------------------------------------------------------------------|
| `ClassConfig.info`                     | `AnnotatedTypeConfigurator.getAnnotated`                                                    |
| `ClassConfig.addAnnotation`            | `AnnotatedTypeConfigurator.add`                                                             |
| `ClassConfig.removeAnnotation`         | `AnnotatedTypeConfigurator.remove`                                                          |
| `ClassConfig.removeAllAnnotations`     | `AnnotatedTypeConfigurator.removeAll`                                                       |
| `ClassConfig.constructors`             | `AnnotatedTypeConfigurator.constructors`                                                    |
| `ClassConfig.methods`                  | `AnnotatedTypeConfigurator.methods`                                                         |
| `ClassConfig.fields`                   | `AnnotatedTypeConfigurator.fields`                                                          |
| `MethodConfig.info`                    | `AnnotatedMethodConfigurator.getAnnotated`, `AnnotatedConstructorConfigurator.getAnnotated` |
| `MethodConfig.addAnnotation`           | `AnnotatedMethodConfigurator.add`, `AnnotatedConstructorConfigurator.add`                   |
| `MethodConfig.removeAnnotation`        | `AnnotatedMethodConfigurator.remove`, `AnnotatedConstructorConfigurator.remove`             |
| `MethodConfig.removeAllAnnotations`    | `AnnotatedMethodConfigurator.removeAll`, `AnnotatedConstructorConfigurator.removeAll`       |
| `MethodConfig.params`                  | `AnnotatedMethodConfigurator.params`, `AnnotatedConstructorConfigurator.params`             |
| `ParameterConfig.info`                 | `AnnotatedParameterConfigurator.getAnnotated`                                               |
| `ParameterConfig.addAnnotation`        | `AnnotatedParameterConfigurator.add`                                                        |
| `ParameterConfig.removeAnnotation`     | `AnnotatedParameterConfigurator.remove`                                                     |
| `ParameterConfig.removeAllAnnotations` | `AnnotatedParameterConfigurator.removeAll`                                                  |
| `FieldConfig.info`                     | `AnnotatedFieldConfigurator.getAnnotated`                                                   |
| `FieldConfig.addAnnotation`            | `AnnotatedFieldConfigurator.add`                                                            |
| `FieldConfig.removeAnnotation`         | `AnnotatedFieldConfigurator.remove`                                                         |
| `FieldConfig.removeAllAnnotations`     | `AnnotatedFieldConfigurator.removeAll`                                                      |

`ProcessAnnotatedType.getAnnotatedType` corresponds to declaring a parameter of type `ClassInfo`.
That type is described below, in the _Langugae Model_ section.

Careful reader will note that `ProcessAnnotatedType` allows creating a whole new `AnnotatedType` manually and supplying it to the container.
Build Compatible Extensions do not allow this style of usage, only the simplified and more straightforward `*Configurator` style.

### @Registration

In `@Registration`, you can look at registered beans, interceptors, and observers.
Extension methods annotated `@Registration` can declare parameters of these types:

- `BeanInfo`: look at registered beans that satisfy given criteria (see below)
- `InterceptorInfo`: look at registered interceptors that satisfy given criteria (see below)
- `ObserverInfo`: look at registered observers that satisfy given criteria (see below)
- `Types`: utility to create instances of `Type`
- `Messages`: logging and validation

Each `@Registration` method must declare exactly 1 parameter of type `BeanInfo`, `InterceptorInfo`, or `ObserverInfo`.

When the method declares a parameter of type `BeanInfo`, it is called once for each bean whose set of bean types contains at least one given type.
When it declares a parameter of type `InterceptorInfo`, it is called once for each interceptor whose set of bean types contains at least one given type.
Finally, when it declares a parameter of type `ObserverInfo`, it is called once for each observer whose observed event type is assignable to at least one given type.

Similarly to `@Enhancement`, these "given types" come from the `@Registration` annotation itself.
The annotation has a single mandatory member called `types` whose value is the set of "given types".
The `types` member corresponds to a type argument that you would apply to the `ProcessBean` or `ProcessObserverMethod` generic event types.

Again, Portable Extensions are somewhat different in usage, but there's a direct correspondence:

| Build Compatible Extensions | Portable Extensions                                    |
|-----------------------------|--------------------------------------------------------|
| `BeanInfo`                  | `Bean` obtained from `ProcessBean`                     |
| `InterceptorInfo`           | `Interceptor` obtained from `ProcessBean`              |
| `ObserverInfo`              | `ObserverMethod` obtained from `ProcessObserverMethod` |

### @Synthesis

In `@Synthesis`, you can register synthetic beans and observers.
Extension methods annotated `@Synthesis` can declare parameters of these types:

- `SyntheticComponents`: register synthetic beans and observers
- `Types`: utility to create instances of `Type`
- `Messages`: logging and validation

As an example, let's create a synthetic bean.
Synthetic observers are very similar.

```java
// 1. a bean class
public class MyPojo {
    public final String data;

    public MyPojo(String data) {
        this.data = data;
    }
}

// 2. bean creation function
public class MyPojoCreator implements SyntheticBeanCreator<MyPojo> {
    @Override
    public MyPojo create(Instance<Object> lookup, Parameters params) {
        String name = params.get("name", String.class);
        return new MyPojo("Hello " + name);
    }
}

// 3. bean disposal function
public static class MyPojoDisposer implements SyntheticBeanDisposer<MyPojo> {
    @Override
    public void dispose(MyPojo instance, Instance<Object> lookup, Parameters params) {
        System.out.println("disposing " + instance.data);
    }
}

// 4. the extension
public static class MyExtension implements BuildCompatibleExtension {
    @Synthesis
    public void synthesise(SyntheticComponents syn) {
        syn.addBean(MyPojo.class)
                .type(MyPojo.class)
                .withParam("name", "World")
                .createWith(MyPojoCreator.class)
                .disposeWith(MyPojoDisposer.class);
    }
}
```

An important observation is that the `MyExtension` code runs during bean discovery, which may be during application compilation, while the `MyPojoCreator` and `MyPojoDisposer` functions run at runtime.
The `Parameters` map is transferred from the extension code to the runtime code.
What kind of parameter types are possible?
Essentially anything that you can put into an annotation, including annotations themselves.

Comparison with Portable Extensions is straightforward:

| Build Compatible Extensions       | Portable Extensions                                                   |
|-----------------------------------|-----------------------------------------------------------------------|
| `SyntheticComponents.addBean`     | `AfterBeanDiscovery.addBean`                                          |
| `SyntheticComponents.addObserver` | `AfterBeanDiscovery.addObserverMethod`                                |

Similarly to `@Enhancement`, Build Compatible Extensions do not have an equivalent of creating a whole new `Bean` or `ObserverMethod` object from scratch.
Only the simplified and more straightforward `*Configurator` style is present.

### @Validation

In `@Validation`, you can perform custom validation.
Extension methods annotated `@Validation` can declare parameters of these types:

- `Messages`: logging and validation
- `Types`: utility to create instances of `Type`

Comparison with Portable Extensions is obvious:

| Build Compatible Extensions | Portable Extensions                              |
|-----------------------------|--------------------------------------------------|
| `Messages.error`            | `AfterDeploymentValidation.addDeploymentProblem` |

At this point, you may be asking, what's the real difference?
Isn't this just a different set of names for existing concepts?
Is a new API truly necessary?

The answer, again, comes from the architectural constraint that all this must be implementable during application compilation.
This means the CDI container can't be running, but it also means that using reflection is costly or even forbidden.
Portable Extensions, unfortunately, are very reflection-heavy.
That's why, in addition to a new extension API, we also had to create a new _language model_ API.
All those weird types called `ClassInfo` etc. are part of that.
In the following section, we'll describe the API in more detail and compare with existing similar APIs.

## Language Model

The `jakarta.enterprise.lang.model` API models the Java language from a high-level perspective.
It is completely reflection-free, though it may easily be implemented on top of reflection.
The core value is that it can also be implemented on top of other representations, such as `javax.lang.model` or Jandex.

The model is structured as a hierarchy of types.
The root in this hierarchy is `AnnotationTarget`.
That is anything that can be annotated:

- _declarations_, such as classes, methods or fields;
- _types_, such as reference types, type variables or wildcard types.

Annotations are represented by `AnnotationInfo`.
An `AnnotationInfo` has a `String`-keyed map of annotation members, represented by `AnnotationMember`.

| Build Compatible Extensions | Portable Extensions                       | Reflection                           | Annotation Processing                      | Jandex                                |
|-----------------------------|-------------------------------------------|--------------------------------------|--------------------------------------------|---------------------------------------|
| `AnnotationTarget`          | `jakarta.enterprise.inject.spi.Annotated` | `java.lang.reflect.AnnotatedElement` | `javax.lang.model.AnnotatedConstruct`      | `org.jboss.jandex.AnnotationTarget`   |
| `AnnotationInfo`            | none, uses `java.lang.Annotation`         | none                                 | `javax.lang.model.AnnotationMirror`        | `org.jboss.jandex.AnnotationInstance` |
| `AnnotationMember`          | none<sup>1</sup>                          | none                                 | `javax.lang.model.element.AnnotationValue` | `org.jboss.jandex.AnnotationValue`    |

1. Reflective access is possible, but usually not necessary.

As mentioned above, there are two kinds of `AnnotationTarget`s: declarations and types.
Therefore, we have `DeclarationInfo` as the top-level type for representing Java declarations, and `Type` as the top-level type for representing Java types.

Declarations are:
- packages;
- classes, including interfaces, enums, annotations, and records;
- fields;
- methods, including constructors;
- method parameters;
- record components.

| Build Compatible Extensions | Portable Extensions                                                                                               | Reflection                                                  | Annotation Processing                             | Jandex                                         |
|-----------------------------|-------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------|---------------------------------------------------|------------------------------------------------|
| `DeclarationInfo`           | none, just `jakarta.enterprise.inject.spi.Annotated`                                                              | none<sup>4</sup>                                            | `javax.lang.model.element.Element`                | none, just `org.jboss.jandex.AnnotationTarget` |
| `PackageInfo`               | none<sup>1</sup>                                                                                                  | `java.lang.Package`                                         | `javax.lang.model.element.PackageElement`         | none<sup>5</sup>                               |
| `ClassInfo`                 | `jakarta.enterprise.inject.spi.AnnotatedType`<sup>2</sup>                                                         | `java.lang.Class`                                           | `javax.lang.model.element.TypeElement`            | `org.jboss.jandex.ClassInfo`                   |
| `FieldInfo`                 | `jakarta.enterprise.inject.spi.AnnotatedField`<sup>2</sup>                                                        | `java.lang.reflect.Field`                                   | `javax.lang.model.element.VariableElement`        | `org.jboss.jandex.FieldInfo`                   |
| `MethodInfo`                | `jakarta.enterprise.inject.spi.AnnotatedMethod`, `jakarta.enterprise.inject.spi.AnnotatedConstructor`<sup>2</sup> | `java.lang.reflect.Method`, `java.lang.reflect.Constructor` | `javax.lang.model.element.ExecutableElement`      | `org.jboss.jandex.MethodInfo`                  |
| `ParameterInfo`             | `jakarta.enterprise.inject.spi.AnnotatedParameter`<sup>2</sup>                                                    | `java.lang.reflect.Parameter`                               | `javax.lang.model.element.VariableElement`        | `org.jboss.jandex.MethodParameterInfo`         |
| `RecordComponentInfo`       | none<sup>3</sup>                                                                                                  | `java.lang.reflect.RecordComponent`                         | `javax.lang.model.element.RecordComponentElement` | `org.jboss.jandex.RecordComponentInfo`         |

1. Relies on reflection.
2. Provides access to the corresponding reflection object.
3. CDI doesn't reflect the existence of records yet.
4. There are types such as `java.lang.reflect.GenericDeclaration` or `java.lang.reflect.AccessibleObject`, but nothing directly corresponding,
5. It is possible to obtain a `ClassInfo` for `package-info.class`,

Types are:
- the `void` pseudo-type;
- primitive types, such as `int`;
- class types, such as `String`;
- array types, such as `int[]` or `String[][]`;
- parameterized types, such as `List<String>`;
- type variables, such as `T` when used in a class or method that declares a type parameter `T`;
- wildcard types, such as `? extends Number`.

| Build Compatible Extensions | Portable Extensions | Reflection                                                          | Annotation Processing                 | Jandex                                                                     |
|-----------------------------|---------------------|---------------------------------------------------------------------|---------------------------------------|----------------------------------------------------------------------------|
| `Type`                      | none<sup>1</sup>    | `java.lang.reflect.Type`<sup>2</sup>                                | `javax.lang.model.type.TypeMirror`    | `org.jboss.jandex.Type`                                                    |
| `VoidType`                  | none<sup>1</sup>    | `java.lang.Class`<sup>2</sup>                                       | `javax.lang.model.type.NoType`        | `org.jboss.jandex.VoidType`                                                |
| `PrimitiveType`             | none<sup>1</sup>    | `java.lang.Class`<sup>2</sup>                                       | `javax.lang.model.type.PrimitiveType` | `org.jboss.jandex.PrimitiveType`                                           |
| `ClassType`                 | none<sup>1</sup>    | `java.lang.Class`<sup>2</sup>                                       | `javax.lang.model.type.DeclaredType`  | `org.jboss.jandex.ClassType`                                               |
| `ArrayType`                 | none<sup>1</sup>    | `java.lang.Class`, `java.lang.reflect.GenericArrayType`<sup>2</sup> | `javax.lang.model.type.ArrayType`     | `org.jboss.jandex.ArrayType`                                               |
| `ParameterizedType`         | none<sup>1</sup>    | `java.lang.reflect.ParameterizedType`<sup>2</sup>                   | `javax.lang.model.type.DeclaredType`  | `org.jboss.jandex.ParameterizedType`                                       |
| `TypeVariable`              | none<sup>1</sup>    | `java.lang.reflect.TypeVariable`<sup>2</sup>                        | `javax.lang.model.type.TypeVariable`  | `org.jboss.jandex.TypeVariable`, `org.jboss.jandex.UnresolvedTypeVariable` |
| `WildcardType`              | none<sup>1</sup>    | `java.lang.reflect.WildcardType`<sup>2</sup>                        | `javax.lang.model.type.WildcardType`  | `org.jboss.jandex.WildcardType`                                            |

1. Relies on reflection.
2. There's also an alternative hierarchy rooted at `java.lang.reflect.AnnotatedType`.

## Conclusion

It's been a ride, but you've seen the most important bits of the Build Compatible Extensions API.
You've also seen how it compares to other APIs, particularly to Portable Extensions.

By now, you hopefully realize that while there are some necessary differences, you would be able to write an extension just fine.
If not, the `cdi-dev` mailing list is a great place to ask!
