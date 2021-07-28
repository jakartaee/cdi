package jakarta.enterprise.lang.model;

/**
 * An annotation member: name and {@link AnnotationMemberValue value}.
 *
 * @see #name()
 * @see #value()
 */
// TODO maybe remove this and let AnnotationInfo.members() return Map<String, AnnotationMemberValue>?
public interface AnnotationMember {
    /**
     * Returns the name of this annotation member.
     *
     * @return the name of this annotation member, never {@code null}
     */
    String name();

    /**
     * Returns the {@link AnnotationMemberValue value} of this annotation member.
     *
     * @return the value of this annotation member, never {@code null}
     */
    AnnotationMemberValue value();
}
