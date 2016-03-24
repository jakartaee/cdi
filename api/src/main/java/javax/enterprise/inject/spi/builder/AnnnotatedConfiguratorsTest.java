package javax.enterprise.inject.spi.builder;

import javax.enterprise.event.Observes;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

public class AnnnotatedConfiguratorsTest {

    @SuppressWarnings("null")
    public void test1() {

        AnnotatedTypeConfigurator<?> configurator = null;

        // TYPE
        configurator.add(InjectLiteral.INSTANCE);
        configurator.remove(Observes.class);

        // METHODS

        // Add @Inject to all methods with at least one param
        configurator.findMethods((m) -> m.getParameters().size() > 0).forEach((m) -> m.add(InjectLiteral.INSTANCE));

        // Find first method with return type Integer and remove @Inject
        configurator.findMethod((m) -> m.getBaseType().equals(Integer.class)).remove(Inject.class);

        // Find first method with name "foo" and remove @Observes from all params
        configurator.findMethod((m) -> m.getJavaMember().getName().equals("foo")).params()
                .forEach((p) -> p.remove(Observes.class));

        // Find all methods with name "foo" and remove @Observes from all params
        configurator.findMethods((m) -> m.getJavaMember().getName().equals("foo"))
                .forEach((m) -> m.params().forEach((p) -> p.remove(Observes.class)));

        // Remove all annotations from all params of all methods
        configurator.methods().forEach((m) -> m.params().forEach((p) -> p.removeAll()));

        // Iterate over all methods
        // 1. Add @Inject to all params
        // 2. Remove @Inject from the first param (suppose all methods have at least one param ;-)
        // 3. Remove @Inject from all params of type String
        // 4. Finally remove all annotations from all params
        for (AnnotatedMethodConfigurator<?> m : configurator.methods()) {
            m.params().forEach((p) -> p.add(InjectLiteral.INSTANCE));
            m.param(0).remove(Inject.class);
            m.findParam((p) -> p.getBaseType().equals(String.class)).remove(Inject.class);
            for (AnnotatedParameterConfigurator<?> p : m.params()) {
                p.removeAll();
            }
        }

        // FIELDS
        configurator.findFields((f) -> f.getBaseType().equals(Integer.class)).forEach((f) -> f.add(InjectLiteral.INSTANCE));
        // configurator.fields().forEach((f) -> f.removeAll());

        // CONSTRUCTORS
        // Remove @Inject from all constructors
        configurator.constructors().forEach((c) -> c.remove(Inject.class));

        // Remove all annotations from all constructors with single param
        configurator.findConstructors((c) -> c.getParameters().size() == 1).forEach((c) -> c.removeAll());
    }

    private static class InjectLiteral extends AnnotationLiteral<Inject> implements Inject {

        private static final long serialVersionUID = 1L;

        static InjectLiteral INSTANCE = new InjectLiteral();

    }

}
