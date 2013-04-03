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
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * <p>
 * Supports inline instantiation of annotation type instances.
 * </p>
 * 
 * <p>
 * An instance of an annotation type may be obtained by subclassing <tt>AnnotationLiteral</tt>.
 * </p>
 * 
 * <pre>
 * public abstract class PayByQualifier extends AnnotationLiteral&lt;PayBy&gt; implements PayBy {
 * }
 * </pre>
 * 
 * <pre>
 * PayBy paybyCheque = new PayByQualifier() {
 *     public PaymentMethod value() {
 *         return CHEQUE;
 *     }
 * };
 * </pre>
 * 
 * @author Pete Muir
 * @author Gavin King
 * @author Marko Luksa
 * 
 * @param <T> the annotation type
 * 
 * @see javax.enterprise.inject.Instance#select(Annotation...)
 * @see javax.enterprise.event.Event#select(Annotation...)
 * 
 */
public abstract class AnnotationLiteral<T extends Annotation> implements Annotation, Serializable {

    private static final long serialVersionUID = 1L;

    private transient Class<T> annotationType;
    private transient Method[] members;
    private transient Integer cachedHashCode;

    protected AnnotationLiteral() {
        if (getMembers().length == 0) {
            this.cachedHashCode = 0;
        } else {
            this.cachedHashCode = null;
        }
    }

    private Method[] getMembers() {
        if (members == null) {
            members = annotationType().getDeclaredMethods();
            if (members.length > 0 && !annotationType().isAssignableFrom(this.getClass())) {
                throw new RuntimeException(getClass() + " does not implement the annotation type with members "
                        + annotationType().getName());
            }
        }
        return members;
    }

    private static Class<?> getAnnotationLiteralSubclass(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass.equals(AnnotationLiteral.class)) {
            return clazz;
        } else if (superclass.equals(Object.class)) {
            return null;
        } else {
            return (getAnnotationLiteralSubclass(superclass));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getTypeParameter(Class<?> annotationLiteralSuperclass) {
        Type type = annotationLiteralSuperclass.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (parameterizedType.getActualTypeArguments().length == 1) {
                return (Class<T>) parameterizedType.getActualTypeArguments()[0];
            }
        }
        return null;
    }

    public Class<? extends Annotation> annotationType() {
        if (annotationType == null) {
            Class<?> annotationLiteralSubclass = getAnnotationLiteralSubclass(this.getClass());
            if (annotationLiteralSubclass == null) {
                throw new RuntimeException(getClass() + " is not a subclass of AnnotationLiteral");
            }
            annotationType = getTypeParameter(annotationLiteralSubclass);
            if (annotationType == null) {
                throw new RuntimeException(getClass() + " does not specify the type parameter T of AnnotationLiteral<T>");
            }
        }
        return annotationType;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append('@').append(annotationType().getName()).append('(');
        for (int i = 0; i < getMembers().length; i++) {
            string.append(getMembers()[i].getName()).append('=');
            Object value = getMemberValue(getMembers()[i], this);
            if (value instanceof boolean[]) {
                appendInBraces(string, Arrays.toString((boolean[]) value));
            } else if (value instanceof byte[]) {
                appendInBraces(string, Arrays.toString((byte[]) value));
            } else if (value instanceof short[]) {
                appendInBraces(string, Arrays.toString((short[]) value));
            } else if (value instanceof int[]) {
                appendInBraces(string, Arrays.toString((int[]) value));
            } else if (value instanceof long[]) {
                appendInBraces(string, Arrays.toString((long[]) value));
            } else if (value instanceof float[]) {
                appendInBraces(string, Arrays.toString((float[]) value));
            } else if (value instanceof double[]) {
                appendInBraces(string, Arrays.toString((double[]) value));
            } else if (value instanceof char[]) {
                appendInBraces(string, Arrays.toString((char[]) value));
            } else if (value instanceof String[]) {
                String[] strings = (String[]) value;
                String[] quoted = new String[strings.length];
                for (int j = 0; j < strings.length; j++) {
                    quoted[j] = "\"" + strings[j] + "\"";
                }
                appendInBraces(string, Arrays.toString(quoted));
            } else if (value instanceof Class<?>[]) {
                Class<?>[] classes = (Class<?>[]) value;
                String[] names = new String[classes.length];
                for (int j = 0; j < classes.length; j++) {
                    names[j] = classes[j].getName() + ".class";
                }
                appendInBraces(string, Arrays.toString(names));
            } else if (value instanceof Object[]) {
                appendInBraces(string, Arrays.toString((Object[]) value));
            } else if (value instanceof String) {
                string.append('"').append(value).append('"');
            } else if (value instanceof Class<?>) {
                string.append(((Class<?>) value).getName()).append(".class");
            } else {
                string.append(value);
            }
            if (i < getMembers().length - 1) {
                string.append(", ");
            }
        }
        return string.append(')').toString();
    }

    private void appendInBraces(StringBuilder buf, String s) {
        buf.append('{').append(s.substring(1, s.length() - 1)).append('}');
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other instanceof Annotation) {
            Annotation that = (Annotation) other;
            if (this.annotationType().equals(that.annotationType())) {
                for (Method member : getMembers()) {
                    Object thisValue = getMemberValue(member, this);
                    Object thatValue = getMemberValue(member, that);
                    if (thisValue instanceof byte[] && thatValue instanceof byte[]) {
                        if (!Arrays.equals((byte[]) thisValue, (byte[]) thatValue))
                            return false;
                    } else if (thisValue instanceof short[] && thatValue instanceof short[]) {
                        if (!Arrays.equals((short[]) thisValue, (short[]) thatValue))
                            return false;
                    } else if (thisValue instanceof int[] && thatValue instanceof int[]) {
                        if (!Arrays.equals((int[]) thisValue, (int[]) thatValue))
                            return false;
                    } else if (thisValue instanceof long[] && thatValue instanceof long[]) {
                        if (!Arrays.equals((long[]) thisValue, (long[]) thatValue))
                            return false;
                    } else if (thisValue instanceof float[] && thatValue instanceof float[]) {
                        if (!Arrays.equals((float[]) thisValue, (float[]) thatValue))
                            return false;
                    } else if (thisValue instanceof double[] && thatValue instanceof double[]) {
                        if (!Arrays.equals((double[]) thisValue, (double[]) thatValue))
                            return false;
                    } else if (thisValue instanceof char[] && thatValue instanceof char[]) {
                        if (!Arrays.equals((char[]) thisValue, (char[]) thatValue))
                            return false;
                    } else if (thisValue instanceof boolean[] && thatValue instanceof boolean[]) {
                        if (!Arrays.equals((boolean[]) thisValue, (boolean[]) thatValue))
                            return false;
                    } else if (thisValue instanceof Object[] && thatValue instanceof Object[]) {
                        if (!Arrays.equals((Object[]) thisValue, (Object[]) thatValue))
                            return false;
                    } else {
                        if (!thisValue.equals(thatValue))
                            return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (cachedHashCode != null) {
            return cachedHashCode;
        } else {
            int hashCode = 0;
            for (Method member : getMembers()) {
                int memberNameHashCode = 127 * member.getName().hashCode();
                Object value = getMemberValue(member, this);
                int memberValueHashCode;
                if (value instanceof boolean[]) {
                    memberValueHashCode = Arrays.hashCode((boolean[]) value);
                } else if (value instanceof short[]) {
                    memberValueHashCode = Arrays.hashCode((short[]) value);
                } else if (value instanceof int[]) {
                    memberValueHashCode = Arrays.hashCode((int[]) value);
                } else if (value instanceof long[]) {
                    memberValueHashCode = Arrays.hashCode((long[]) value);
                } else if (value instanceof float[]) {
                    memberValueHashCode = Arrays.hashCode((float[]) value);
                } else if (value instanceof double[]) {
                    memberValueHashCode = Arrays.hashCode((double[]) value);
                } else if (value instanceof byte[]) {
                    memberValueHashCode = Arrays.hashCode((byte[]) value);
                } else if (value instanceof char[]) {
                    memberValueHashCode = Arrays.hashCode((char[]) value);
                } else if (value instanceof Object[]) {
                    memberValueHashCode = Arrays.hashCode((Object[]) value);
                } else {
                    memberValueHashCode = value.hashCode();
                }
                hashCode += memberNameHashCode ^ memberValueHashCode;
            }
            return hashCode;
        }
    }

    private static Object getMemberValue(Method member, Annotation instance) {
        Object value = invoke(member, instance);
        if (value == null) {
            throw new IllegalArgumentException("Annotation member value " + instance.getClass().getName() + "."
                    + member.getName() + " must not be null");
        }
        return value;
    }

    private static Object invoke(Method method, Object instance) {
        try {
            if (!method.isAccessible())
                method.setAccessible(true);
            return method.invoke(instance);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on "
                    + method.getDeclaringClass(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on "
                    + method.getDeclaringClass(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on "
                    + method.getDeclaringClass(), e);
        }
    }

}
