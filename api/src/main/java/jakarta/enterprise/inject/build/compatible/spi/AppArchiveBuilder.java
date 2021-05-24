package jakarta.enterprise.inject.build.compatible.spi;

// TODO better name
public interface AppArchiveBuilder {
    void add(String fullyQualifiedClassName);

    // TODO adds the type itself or not? (in theory yes, as subtyping is reflexive)
    // TODO looks like it can't be implemented on top of Portable Extensions, so maybe remove?
    void addSubtypesOf(String fullyQualifiedClassName);
}
