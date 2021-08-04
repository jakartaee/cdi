package jakarta.enterprise.lang.model.declarations;

/**
 * Provides read-only information about a package.
 */
// TODO is this useful? perhaps `ClassInfo.packageName` returning a `String` would be enough?
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
