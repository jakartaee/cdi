package javax.enterprise.inject.spi;

/**
 * {@link IdentifiedAnnotatedType} allows multiple annotated types, based on the same underlying
 * type, to be defined. If an {@link AnnotatedType} is an instance of
 * {@link IdentifiedAnnotatedType} then {@link IdentifiedAnnotatedType#getID()} is used to identify
 * the annotated type to the container, otherwise the fully qualified class name of
 * {@link AnnotatedType#getJavaClass()} is used to identify the type. {@link AnnotatedType}s
 * discovered by the container use the fully qualified class name of
 * {@link AnnotatedType#getJavaClass()} to identify the type.
 * 
 * @author Pete Muir
 * 
 * @param <X> the type
 */
public interface IdentifiedAnnotatedType<X> extends AnnotatedType<X> {

   /**
    * The {@link AnnotatedType} identifier
    */
   public String getId();

}
