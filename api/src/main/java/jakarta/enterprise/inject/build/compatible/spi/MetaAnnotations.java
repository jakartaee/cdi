package jakarta.enterprise.inject.build.compatible.spi;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

public interface MetaAnnotations {
    // TODO this API style is not very common, and makes addContext too different
    //  I only added it to make Quarkus implementation easier, but should definitely be reconsidered

    void addQualifier(Class<? extends Annotation> annotation, Consumer<ClassConfig<?>> config);

    void addInterceptorBinding(Class<? extends Annotation> annotation, Consumer<ClassConfig<?>> config);

    void addStereotype(Class<? extends Annotation> annotation, Consumer<ClassConfig<?>> config);

    // includes defining the scope annotation
    ContextBuilder addContext();
}
