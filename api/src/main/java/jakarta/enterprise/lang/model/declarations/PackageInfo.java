package jakarta.enterprise.lang.model.declarations;

// TODO is this useful? perhaps `ClassInfo.packageName` returning a `String` would be enough?
public interface PackageInfo extends DeclarationInfo {
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
