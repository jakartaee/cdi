package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

/**
 * A class type, including interface types, enum types, annotation types and record types.
 * Class types are introduced by class {@linkplain #declaration() declarations}.
 *
 * @since 4.0
 */
public interface ClassType extends Type {
    /**
     * Returns the {@linkplain ClassInfo declaration} of this class type.
     *
     * @return the {@linkplain ClassInfo declaration} of this class type
     */
    ClassInfo declaration();

    // ---

    @Override
    default Kind kind() {
        return Kind.CLASS;
    }

    @Override
    default ClassType asClass() {
        return this;
    }
}
