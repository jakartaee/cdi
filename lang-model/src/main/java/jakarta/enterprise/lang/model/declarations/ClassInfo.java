/*
 * Copyright 2021, Red Hat, Inc., and individual contributors
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
package jakarta.enterprise.lang.model.declarations;

import java.util.Collection;
import java.util.List;

import jakarta.enterprise.lang.model.types.Type;
import jakarta.enterprise.lang.model.types.TypeVariable;

/**
 * A class. Five kinds of classes are distinguished:
 * <ul>
 * <li>plain classes</li>
 * <li>interfaces</li>
 * <li>enums (restricted kind of classes)</li>
 * <li>annotations (specialized kind of interfaces)</li>
 * <li>records (restricted kind of classes)</li>
 * </ul>
 *
 * Classes are represented as isolated units. That is, if this class is nested, it is not possible
 * to obtain the enclosing class. Similarly, it is not possible to obtain the set of classes
 * nested in this class.
 * <p>
 * At the same time, it is possible to obtain the set of {@linkplain #constructors() constructors},
 * {@linkplain #methods() methods} and {@linkplain #fields() fields} declared in this class, as well
 * as the set of {@linkplain #recordComponents() record components} if this class is a record.
 * It is also possible to obtain the {@linkplain #packageInfo() package} this class is declared in.
 *
 * @since 4.0
 */
public interface ClassInfo extends DeclarationInfo {
    /**
     * Returns the binary name of this class, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getName()}.
     *
     * @return binary name of this class, never {@code null}
     */
    String name();

    /**
     * Returns the simple name of this class, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the class name as returned by {@link Class#getSimpleName()}.
     *
     * @return simple name of this class, never {@code null}
     */
    String simpleName();

    /**
     * Returns the {@linkplain PackageInfo package} this class is part of.
     * Returns {@code null} if this class is part of an unnamed package.
     *
     * @return this class's package, or {@code null} if this class is in an unnamed package
     */
    PackageInfo packageInfo();

    /**
     * Returns a list of {@linkplain TypeVariable type parameters} declared on this class.
     * Returns an empty list if this class is not generic and so does not declare type parameters.
     *
     * @return immutable list of this class's type parameters, never {@code null}
     */
    List<TypeVariable> typeParameters();

    /**
     * Returns the direct superclass {@linkplain Type type} of this class. Returns {@code null} if this class
     * does not have a superclass; that is, if this class is {@code java.lang.Object} or an interface.
     *
     * @return the direct superclass type of this class, or {@code null} if there's no superclass
     */
    Type superClass();

    /**
     * Returns the direct superclass of this class. Returns {@code null} if this class
     * does not have a superclass; that is, if this class is {@code java.lang.Object} or an interface.
     *
     * @return the direct superclass of this class, or {@code null} if there's no superclass
     */
    ClassInfo superClassDeclaration();

    /**
     * Returns a list of direct superinterface {@linkplain Type types} of this class.
     * Returns an empty list if this class has no direct superinterface.
     *
     * @return immutable list of direct superinterface types of this class, never {@code null}
     */
    List<Type> superInterfaces();

    /**
     * Returns a list of direct superinterfaces of this class.
     * Returns an empty list if this class has no direct superinterface.
     *
     * @return immutable list of direct superinterfaces of this class, never {@code null}
     */
    List<ClassInfo> superInterfacesDeclarations();

    /**
     * Returns whether this class is a plain class. That is, not an interface,
     * not an enum, not an annotation, and not a record.
     *
     * @return whether this class is a plain class
     */
    boolean isPlainClass();

    /**
     * Returns whether this class is an interface.
     * If this class is an annotation, returns {@code false}.
     *
     * @return whether this class is an interface
     */
    boolean isInterface();

    /**
     * Returns whether this class is an enum.
     *
     * @return whether this class is an enum
     */
    boolean isEnum();

    /**
     * Returns whether this class is an annotation.
     *
     * @return whether this class is an annotation
     */
    boolean isAnnotation();

    /**
     * Returns whether this class is a record.
     *
     * @return whether this class is a record
     */
    boolean isRecord();

    /**
     * Returns whether this class is abstract.
     * <p>
     * A plain class is abstract if declared {@code abstract}.
     * An enum is abstract if it declares {@code abstract} methods.
     * An interface or an annotation is always abstract.
     * A record is never abstract.
     *
     * @return whether this class is {@code abstract}
     */
    boolean isAbstract();

    /**
     * Returns whether this class is {@code final}.
     *
     * @return whether this class is {@code final}
     */
    boolean isFinal();

    /**
     * Returns the modifiers of this class as an {@code int}.
     * Use {@link java.lang.reflect.Modifier Modifier} to inspect the value.
     *
     * @return the modifiers of this class
     */
    int modifiers();

    /**
     * Returns a collection of {@linkplain MethodInfo constructors} declared or implicitly declared
     * in this class. Constructors declared in direct or indirect superclasses are not included.
     * <p>
     * If this class is an interface or an annotation, returns an empty collection.
     *
     * @return immutable collection of constructors, never {@code null}
     */
    Collection<MethodInfo> constructors();

    /**
     * Returns a collection of {@linkplain MethodInfo methods} declared or implicitly declared
     * in this class and all its superclasses up to and excluding {@code java.lang.Object},
     * as well as all direct and indirect superinterfaces. If this class is {@code java.lang.Object},
     * its methods are not excluded. If this class is an interface, only superinterfaces
     * are considered. Implicitly declared methods corresponding to {@code java.lang.Object}
     * methods in interfaces are omitted.
     * <p>
     * If the collection of methods described above contains multiple methods with the same signature,
     * all such methods are returned. {@link MethodInfo#declaringClass() MethodInfo.declaringClass}
     * should be used to distinguish such methods.
     * <p>
     * Iteration order of the resulting collection is not defined and does not have to correspond
     * to the inheritance hierarchy of this class.
     *
     * @return immutable collection of methods, never {@code null}
     */
    Collection<MethodInfo> methods();

    /**
     * Returns a collection of {@linkplain FieldInfo fields} declared or implicitly declared
     * in this class and all its superclasses up to and excluding {@code java.lang.Object},
     * as well as all direct and indirect superinterfaces. If this class is {@code java.lang.Object},
     * its fields are not excluded. If this class is an interface, only superinterfaces
     * are considered.
     * <p>
     * If the collection of fields described above contains multiple fields with the same name,
     * all such fields are returned. {@link FieldInfo#declaringClass() FieldInfo.declaringClass}
     * should be used to distinguish such fields
     * <p>
     * Iteration order of the resulting collection is not defined and does not have to correspond
     * to the inheritance hierarchy of this class.
     *
     * @return immutable collection of fields, never {@code null}
     */
    Collection<FieldInfo> fields();

    /**
     * Returns a collection of {@linkplain RecordComponentInfo record components} declared in this class.
     * If this class is not a record, returns an empty collection.
     *
     * @return immutable collection of record components, never {@code nul}
     */
    Collection<RecordComponentInfo> recordComponents();

    // ---

    @Override
    default Kind kind() {
        return Kind.CLASS;
    }

    @Override
    default ClassInfo asClass() {
        return this;
    }
}
