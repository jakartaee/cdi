package jakarta.enterprise.lang.model;

/**
 * Represents an annotation member and associated {@link jakarta.enterprise.lang.model.AnnotationMemberValue}.
 */
public interface AnnotationMember {
    /**
     * @return The name of the annotation member. Never {@code null}.
     */
    String name();

    /**
     * Supplies the {@link jakarta.enterprise.lang.model.AnnotationMemberValue} associated with the annotation member.
     *
     * @return The value of the annotation member. Never {@code null}.
     */
    AnnotationMemberValue value();
}
