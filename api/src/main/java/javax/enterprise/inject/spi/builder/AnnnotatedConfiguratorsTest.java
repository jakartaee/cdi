package javax.enterprise.inject.spi.builder;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

public class AnnnotatedConfiguratorsTest {

    @SuppressWarnings("null")
    public void test1() {

        ProcessAnnotatedType<?> event = null;

        // TYPE

        // Add @Inject on the type
        event.configureAnnotatedType().add(InjectLiteral.INSTANCE);

        // Remove all annotations from the type
        event.configureAnnotatedType().removeAll();

        // METHODS
        
        // Remove @Inject from all methods
        event.configureAnnotatedType().methods().forEach(m -> m.remove(Inject.class));

        // Add @Inject to all methods with at least one param
        event.configureAnnotatedType().filterMethods((m) -> m.getParameters().size() > 0)
                .forEach(m -> m.add(InjectLiteral.INSTANCE));

        // Find first method with return type Integer and remove @Inject
        event.configureAnnotatedType().filterMethods(m -> m.getBaseType().equals(Integer.class)).findFirst()
                .ifPresent(m -> m.remove(Inject.class));

        // Find first method with name "foo" and remove @Observes from all params
        event.configureAnnotatedType().filterMethods(m -> m.getJavaMember().getName().equals("foo")).findFirst()
                .ifPresent(foo -> foo.params().forEach(p -> p.remove(Observes.class)));

        // Find all methods with name "foo" and remove @Observes from all params
        event.configureAnnotatedType().filterMethods(m -> m.getJavaMember().getName().equals("foo"))
                .forEach(m -> m.params().forEach(p -> p.remove(Observes.class)));

        // Remove all annotations from all params of all methods
        event.configureAnnotatedType().methods().forEach(m -> m.params().forEach((p) -> p.removeAll()));

        // Iterate over all methods
        for (AnnotatedMethodConfigurator<?> m : event.configureAnnotatedType().methods()) {
            // 1. Add @Inject to all params
            m.params().forEach(p -> p.add(InjectLiteral.INSTANCE));
            // 2. Remove @Inject from the first param (suppose all methods have at least one param ;-)
            m.params().get(0).remove(Inject.class);
            // 3. Remove @Inject from all params of type String
            m.filterParams(p -> p.getBaseType().equals(String.class)).forEach(p -> p.remove(Inject.class));
            // 4. Finally remove all annotations from all params
            for (AnnotatedParameterConfigurator<?> p : m.params()) {
                p.removeAll();
            }
        }

        // FIELDS

        // Add @Inject to all fields with type Integer
        event.configureAnnotatedType().filterFields(f -> f.getBaseType().equals(Integer.class))
                .forEach(f -> f.add(InjectLiteral.INSTANCE));

        // CONSTRUCTORS

        // Remove @Inject from all constructors
        event.configureAnnotatedType().constructors().forEach(c -> c.remove(Inject.class));

        // Add @Inject to all constructors with single param
        event.configureAnnotatedType().filterConstructors(c -> c.getParameters().size() == 1)
                .forEach((c) -> c.add(InjectLiteral.INSTANCE));

        // Remove all annotations from all constructors with single param
        event.configureAnnotatedType().filterConstructors(c -> c.getParameters().size() == 1).forEach((c) -> c.removeAll());
    }

    private static class InjectLiteral extends AnnotationLiteral<Inject> implements Inject {

        private static final long serialVersionUID = 1L;

        static InjectLiteral INSTANCE = new InjectLiteral();

    }

}
