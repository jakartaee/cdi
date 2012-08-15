package javax.enterprise.inject.spi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * The BeanAttributes interface exposes the basic attributes of a bean.
 * 
 * @author Pete Muir
 * 
 * @param <T> the class of the bean instance
 */
public interface BeanAttributes<T> {

   /**
    * Obtains the {@linkplain javax.enterprise.inject bean types} of the bean.
    * 
    * @return the {@linkplain javax.enterprise.inject bean types}
    */
   public Set<Type> getTypes();

   /**
    * Obtains the {@linkplain javax.inject.Qualifier qualifiers} of the bean.
    * 
    * @return the {@linkplain javax.inject.Qualifier qualifiers}
    */
   public Set<Annotation> getQualifiers();

   /**
    * Obtains the {@linkplain javax.enterprise.context scope} of the bean.
    * 
    * @return the {@linkplain javax.enterprise.context scope}
    */
   public Class<? extends Annotation> getScope();

   /**
    * Obtains the {@linkplain javax.enterprise.inject EL name} of a bean, if it has one.
    * 
    * @return the {@linkplain javax.enterprise.inject EL name}
    */
   public String getName();

   /**
    * Obtains the {@linkplain javax.enterprise.inject.Stereotype stereotypes} of the bean.
    * 
    * @return the set of {@linkplain javax.enterprise.inject.Stereotype stereotypes}
    */
   public Set<Class<? extends Annotation>> getStereotypes();

   /**
    * Determines if the bean is an {@linkplain javax.enterprise.inject.Alternative alternative}.
    * 
    * @return <tt>true</tt> if the bean is an {@linkplain javax.enterprise.inject.Alternative
    *         alternative}, and <tt>false</tt> otherwise.
    */
   public boolean isAlternative();

   /**
    * Determines if {@link javax.enterprise.context.spi.Contextual#create(CreationalContext)}
    * sometimes return a null value.
    * 
    * @return <tt>true</tt> if the {@code create()} method may return a null value, and
    *         <tt>false</tt> otherwise
    */
   public boolean isNullable();

}