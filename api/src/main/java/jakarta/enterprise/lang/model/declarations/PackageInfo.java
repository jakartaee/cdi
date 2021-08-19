package jakarta.enterprise.lang.model.declarations;

/**
 * A package, possibly annotated in {@code package-info.java}.
 * Obtaining the set of members present in this package is not possible.
 *
 * @since 4.0
 */
public interface PackageInfo extends DeclarationInfo {
    /**
     * Returns the fully qualified name of this package, as defined by <cite>The Java&trade; Language Specification</cite>;
     * in other words, the package name as returned by {@link Package#getName()}.
     *
     * @return fully qualified name of this package, never {@code null}
     */
    String name();

    // ---

    @Override
    default Kind kind() {
        return Kind.PACKAGE;
    }

    @Override
    default PackageInfo asPackage() {
        return this;
    }
}
