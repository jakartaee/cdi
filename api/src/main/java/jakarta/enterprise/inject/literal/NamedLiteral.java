/*
 * Copyright 2008, Red Hat, Inc., and individual contributors
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
package jakarta.enterprise.inject.literal;

import java.lang.annotation.Annotation;

import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Named;

/**
 * Supports inline instantiation of the {@link Named} qualifier.
 *
 * @author Pete Muir
 * @author Jozef Hartinger
 * @since 2.0
 */
public final class NamedLiteral extends AnnotationLiteral<Named> implements Named {
    /** Default Named literal */
    public static final Named INSTANCE = of("");

    private static final long serialVersionUID = 1L;

    /** The name */
    private final String value;

    /**
     * Create a new NamedLiteral for the given name value
     *
     * @param value the name
     * @return new NamedLiteral
     */
    public static NamedLiteral of(String value) {
        return new NamedLiteral(value);
    }

    private NamedLiteral(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Named that) {
            return this.value.equals(that.value());
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = 0;
        result += 127 * "value".hashCode() ^ this.value.hashCode();
        return result;
    }

    public String toString() {
        return "@jakarta.inject.Named(\"" + this.value + "\")";
    }
}
