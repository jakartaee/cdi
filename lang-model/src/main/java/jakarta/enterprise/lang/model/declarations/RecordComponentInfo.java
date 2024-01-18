/*
 * Copyright 2021, Red Hat, Inc., and individual contributors
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
package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.types.Type;

/**
 * A record component, {@linkplain #declaringRecord() declared} in some record.
 *
 * @since 4.0
 */
public interface RecordComponentInfo extends DeclarationInfo {
    /**
     * Returns the name of this record component.
     *
     * @return the name of this record component, never {@code null}
     */
    String name();

    /**
     * Returns the {@linkplain Type type} of this record component.
     *
     * @return the {@linkplain Type type} of this record component, never {@code null}
     */
    Type type();

    /**
     * Returns the field corresponding to this record component.
     *
     * @return the field, never {@code null}
     */
    FieldInfo field();

    /**
     * Returns the accessor method corresponding to this record component.
     *
     * @return the accessor method, never {@code null}
     */
    MethodInfo accessor();

    /**
     * Returns the {@linkplain ClassInfo record} that declares this component.
     *
     * @return the {@linkplain ClassInfo record} that declares this component, never {@code null}
     */
    ClassInfo declaringRecord();

    // ---

    @Override
    default Kind kind() {
        return Kind.RECORD_COMPONENT;
    }

    @Override
    default RecordComponentInfo asRecordComponent() {
        return this;
    }
}
