package org.jboss.cdi.api.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.enterprise.util.AnnotationLiteral;

@Retention(RetentionPolicy.RUNTIME)
public @interface Foo
{
   
   public String name();
   
   public class FooLiteral extends AnnotationLiteral<Foo> implements Foo 
   {
      
      private final String name;
      
      public FooLiteral(String name)
      {
         this.name = name;
      }
      
      public String name()
      {
         return name;
      }
      
   }

}
