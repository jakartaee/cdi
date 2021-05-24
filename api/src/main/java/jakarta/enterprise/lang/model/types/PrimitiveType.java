package jakarta.enterprise.lang.model.types;

public interface PrimitiveType extends Type {
    // TODO Kind vs. PrimitiveKind?

    enum PrimitiveKind {
        BOOLEAN,
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        CHAR,
    }

    String name();

    PrimitiveKind primitiveKind();

    default boolean isBoolean() {
        return primitiveKind() == PrimitiveKind.BOOLEAN;
    }

    default boolean isByte() {
        return primitiveKind() == PrimitiveKind.BYTE;
    }

    default boolean isShort() {
        return primitiveKind() == PrimitiveKind.SHORT;
    }

    default boolean isInt() {
        return primitiveKind() == PrimitiveKind.INT;
    }

    default boolean isLong() {
        return primitiveKind() == PrimitiveKind.LONG;
    }

    default boolean isFloat() {
        return primitiveKind() == PrimitiveKind.FLOAT;
    }

    default boolean isDouble() {
        return primitiveKind() == PrimitiveKind.DOUBLE;
    }

    default boolean isChar() {
        return primitiveKind() == PrimitiveKind.CHAR;
    }

    // ---

    @Override
    default Kind kind() {
        return Kind.PRIMITIVE;
    }

    @Override
    default PrimitiveType asPrimitive() {
        return this;
    }
}
