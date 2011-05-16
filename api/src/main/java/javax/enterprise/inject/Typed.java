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

package javax.enterprise.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Restricts the bean types of a bean. May be applied to a bean class or producer method or field.
 * </p>
 * 
 * <pre>
 * &#064;Typed(Shop.class)
 * public class BookShop
 *       extends Business
 *       implements Shop&lt;Book&gt; { 
 *    ... 
 * }
 * </pre>
 * 
 * <p>
 * When a <tt>&#064;Typed</tt> annotation is specified, only the types whose classes are explicitly listed using the
 * {@link javax.enterprise.inject.Typed#value() value} member, along with {@link java.lang.Object}, are bean types of the bean.
 * </p>
 * 
 * @author Pete Muir
 * @author Gavin King
 * 
 */
@Target({ FIELD, METHOD, TYPE })
@Retention(RUNTIME)
@Documented
public @interface Typed {
    /**
     * <p>
     * Selects the bean types of the bean. Every class must correspond to a type in the unrestricted set of bean types of a
     * bean.
     * </p>
     * 
     * @return the classes corresponding to the bean types of the bean
     */
    Class<?>[] value() default {};

}
