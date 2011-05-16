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

package javax.enterprise.util;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>
 * Supports inline instantiation of objects that represent parameterized types with actual type parameters.
 * </p>
 * 
 * <p>
 * An object that represents any parameterized type may be obtained by subclassing <tt>TypeLiteral</tt>.
 * </p>
 * 
 * <pre>
 * TypeLiteral&lt;List&lt;String&gt;&gt; stringListType = new TypeLiteral&lt;List&lt;String&gt;&gt;() {
 * };
 * </pre>
 * 
 * @author Gavin King
 * @author Pete Muir
 * 
 * @param <T> the type, including all actual type parameters
 * 
 * @see javax.enterprise.inject.Instance#select(TypeLiteral, Annotation...)
 * @see javax.enterprise.event.Event#select(TypeLiteral, Annotation...)
 * 
 */
public abstract class TypeLiteral<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient Type actualType;

    protected TypeLiteral() {
    }

    /**
     * @return the actual type represented by this object
     */
    public final Type getType() {
        if (actualType == null) {
            Class<?> typeLiteralSubclass = getTypeLiteralSubclass(this.getClass());
            if (typeLiteralSubclass == null) {
                throw new RuntimeException(getClass() + " is not a subclass of TypeLiteral");
            }
            actualType = getTypeParameter(typeLiteralSubclass);
            if (actualType == null) {
                throw new RuntimeException(getClass() + " does not specify the type parameter T of TypeLiteral<T>");
            }
        }
        return actualType;
    }

    /**
     * @return the raw type represented by this object
     */
    @SuppressWarnings("unchecked")
    public final Class<T> getRawType() {
        Type type = getType();
        if (type instanceof Class) {
            return (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<T>) ((ParameterizedType) type).getRawType();
        } else if (type instanceof GenericArrayType) {
            return (Class<T>) Object[].class;
        } else {
            throw new RuntimeException("Illegal type");
        }
    }

    private static Class<?> getTypeLiteralSubclass(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass.equals(TypeLiteral.class)) {
            return clazz;
        } else if (superclass.equals(Object.class)) {
            return null;
        } else {
            return (getTypeLiteralSubclass(superclass));
        }
    }

    private static Type getTypeParameter(Class<?> superclass) {
        Type type = superclass.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (parameterizedType.getActualTypeArguments().length == 1) {
                return parameterizedType.getActualTypeArguments()[0];
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypeLiteral<?>) {
            TypeLiteral<?> that = (TypeLiteral<?>) obj;
            return this.getType().equals(that.getType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getType().hashCode();
    }

    @Override
    public String toString() {
        return getType().toString();
    }

}
