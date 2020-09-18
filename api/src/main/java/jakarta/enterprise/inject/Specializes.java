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

package jakarta.enterprise.inject;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.event.Event;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * <p>
 * Indicates that a bean directly specializes another bean. May be applied to a bean class or producer method.
 * </p>
 * 
 * <p>
 * If a bean directly specializes a second bean, it inherits:
 * </p>
 * 
 * <ul>
 * <li>all qualifiers of the second bean, and</li>
 * <li>the name, if any, of the second bean.</li>
 * </ul>
 * 
 * <p>
 * If the second bean has a name, the bean may not declare a name using {@link jakarta.inject.Named &#064;Named}. Furthermore, the
 * bean must have all the bean types of the second bean.
 * </p>
 * 
 * <ul>
 * <li>If a bean class of a managed bean is annotated <code>&#064;Specializes</code> , then the bean class must directly extend the
 * bean class of a second managed bean. Then the first managed bean directly specializes the second managed bean.</li>
 * 
 * <li>If a bean class of a session bean is annotated <code>&#064;Specializes</code> , then the bean class must directly extend the
 * bean class of a second session bean. Then the first session bean directly specializes the second session bean.</li>
 * 
 * <li>If a producer method is annotated <code>&#064;Specializes</code>, then it must be non-static and directly override another
 * producer method. Then the first producer method directly specializes the second producer method.</li>
 * </ul>
 * 
 * <p>
 * If a bean is specialized by any enabled bean, the first bean is disabled.
 * </p>
 * 
 * @author Gavin King
 * @author Pete Muir
 */

@Target({ TYPE, METHOD })
@Retention(RUNTIME)
@Documented
public @interface Specializes {
    
    /**
     * Supports inline instantiation of the {@link Specializes} annotation.
     *
     * @author Martin Kouba
     * @since 2.0
     * @see Instance
     * @see Event
     */
    public static final class Literal extends AnnotationLiteral<Specializes> implements Specializes {

        private static final long serialVersionUID = 1L;

        public static final Literal INSTANCE = new Literal();

    }
}
