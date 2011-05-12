package org.jboss.cdi.api.test;

import org.jboss.cdi.api.test.Foo.FooLiteral;
import org.testng.annotations.Test;

@Foo(name="pete")
public class AnnotationLiteralTest
{

   @Test(expectedExceptions=IllegalArgumentException.class)
   public void testNullMemberValueOnHashCode()
   {
      new FooLiteral(null).hashCode();      
   }
   
   @Test(expectedExceptions=IllegalArgumentException.class)
   public void testNullMemberValueOnEquals1()
   {
      new FooLiteral(null).equals(AnnotationLiteralTest.class.getAnnotation(Foo.class));      
   }
   
   @Test(expectedExceptions=IllegalArgumentException.class)
   public void testNullMemberValueOnEquals2()
   {
      new FooLiteral(null).equals(new FooLiteral(null));      
   }
   
   @Test(expectedExceptions=IllegalArgumentException.class)
   public void testNullMemberValueOnToString()
   {
      new FooLiteral(null).hashCode();      
   }
   
}
