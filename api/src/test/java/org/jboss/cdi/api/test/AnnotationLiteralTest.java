/*
 * Copyright 2011, Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.cdi.api.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.annotation.Annotation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.BeforeDestroyed;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Specializes;
import jakarta.enterprise.inject.TransientReference;
import jakarta.enterprise.inject.Typed;
import jakarta.enterprise.inject.Vetoed;
import jakarta.enterprise.inject.literal.InjectLiteral;
import jakarta.enterprise.inject.literal.NamedLiteral;
import jakarta.enterprise.inject.literal.QualifierLiteral;
import jakarta.enterprise.inject.literal.SingletonLiteral;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.enterprise.util.Nonbinding;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Qualifier;
import jakarta.inject.Singleton;

import org.jboss.cdi.api.test.Foo.FooLiteral;
import org.testng.annotations.Test;

@Foo(name = "pete")
public class AnnotationLiteralTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNullMemberValueOnHashCode() {
        new FooLiteral(null).hashCode();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNullMemberValueOnEquals1() {
        new FooLiteral(null).equals(AnnotationLiteralTest.class.getAnnotation(Foo.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNullMemberValueOnEquals2() {
        new FooLiteral(null).equals(new FooLiteral(null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNullMemberValueOnToString() {
        new FooLiteral(null).hashCode();
    }

    @Test
    public void toStringShouldWork() {
        assertNotNull(new FooLiteral("foo").toString());
    }

    @Test
    public void annotationTypeShouldWork() {
        assertEquals(Foo.class, new FooLiteral("foo").annotationType());
    }

    @Test
    public void equalsShouldWork() {
        FooLiteral foo = new FooLiteral("foo");
        FooLiteral foo2 = new FooLiteral("foo");
        assertEquals(foo, foo2);
    }

    @Test
    public void hashCodeShouldWork() {
        assertNotEquals(new FooLiteral("foo").hashCode(), 0);
    }

    @Test
    public void testDefaultLiteral() {
        assertEquals(new AnnotationLiteral<Default>() {
        }, Default.Literal.INSTANCE);
    }

    @Test
    public void testAnyLiteral() {
        assertEquals(new AnnotationLiteral<Any>() {
        }, Any.Literal.INSTANCE);
    }

    @Test
    public void testNonbindingLiteral() {
        assertEquals(new AnnotationLiteral<Nonbinding>() {
        }, Nonbinding.Literal.INSTANCE);
    }

    @Test
    public void testTypedLiteral() {
        assertEquals(Typed.Literal.INSTANCE.value().length, 0);
        assertEquals(Typed.Literal.of(new Class[] { String.class }).value()[0], String.class);
    }

    @Test
    public void testAlternativeLiteral() {
        assertEquals(new AnnotationLiteral<Alternative>() {
        }, Alternative.Literal.INSTANCE);
    }

    @Test
    public void testNamedLiteral() {
        assertEquals(NamedLiteral.INSTANCE.value(), "");
        assertEquals(NamedLiteral.of("foo").value(), "foo");
    }

    @Test
    public void testQualifierLiteral() {
        assertEquals(new AnnotationLiteral<Qualifier>() {
        }, QualifierLiteral.INSTANCE);
    }

    @Test
    public void testSingletonLiteral() {
        assertEquals(new AnnotationLiteral<Singleton>() {
        }, SingletonLiteral.INSTANCE);
    }

    @Test
    public void testInitializedLiteral() {
        assertEquals(Initialized.Literal.of(RequestScoped.class).value(), RequestScoped.class);
        assertEquals(Initialized.Literal.REQUEST.value(), RequestScoped.class);
        assertEquals(Initialized.Literal.CONVERSATION.value(), ConversationScoped.class);
        assertEquals(Initialized.Literal.SESSION.value(), SessionScoped.class);
        assertEquals(Initialized.Literal.APPLICATION.value(), ApplicationScoped.class);
    }

    @Test
    public void testDestroyedLiteral() {
        assertEquals(Destroyed.Literal.of(ConversationScoped.class).value(), ConversationScoped.class);
    }

    @Test
    public void testBeforeDestroyedLiteral() {
        assertEquals(BeforeDestroyed.Literal.of(RequestScoped.class).value(), RequestScoped.class);
        assertEquals(BeforeDestroyed.Literal.REQUEST.value(), RequestScoped.class);
        assertEquals(BeforeDestroyed.Literal.CONVERSATION.value(), ConversationScoped.class);
        assertEquals(BeforeDestroyed.Literal.SESSION.value(), SessionScoped.class);
        assertEquals(BeforeDestroyed.Literal.APPLICATION.value(), ApplicationScoped.class);
    }

    @Test
    public void testApplicationScopedLiteral() {
        assertEquals(new AnnotationLiteral<ApplicationScoped>() {
        }, ApplicationScoped.Literal.INSTANCE);
    }

    @Test
    public void testRequestScopedLiteral() {
        assertEquals(new AnnotationLiteral<RequestScoped>() {
        }, RequestScoped.Literal.INSTANCE);
    }

    @Test
    public void testSessionScopedLiteral() {
        assertEquals(new AnnotationLiteral<SessionScoped>() {
        }, SessionScoped.Literal.INSTANCE);
    }

    @Test
    public void testConversationScopedLiteral() {
        assertEquals(new AnnotationLiteral<ConversationScoped>() {
        }, ConversationScoped.Literal.INSTANCE);
    }

    @Test
    public void testDependentLiteral() {
        assertEquals(new AnnotationLiteral<Dependent>() {
        }, Dependent.Literal.INSTANCE);
    }

    @Test
    public void testVetoedLiteral() {
        assertEquals(new AnnotationLiteral<Vetoed>() {
        }, Vetoed.Literal.INSTANCE);
    }

    @Test
    public void testInjectLiteral() {
        assertEquals(new AnnotationLiteral<Inject>() {
        }, InjectLiteral.INSTANCE);
    }

    @Test
    public void testSpecializesLiteral() {
        assertEquals(new AnnotationLiteral<Specializes>() {
        }, Specializes.Literal.INSTANCE);
    }

    @Test
    public void testTransientReferenceLiteral() {
        assertEquals(new AnnotationLiteral<TransientReference>() {
        }, TransientReference.Literal.INSTANCE);
    }

    // the `Usages` and `UsagesAnn` classes include all annotations for which a literal exists
    // a few of them are present multiple times for better test coverage

    static class Usages {
        @Alternative
        @Any
        @ApplicationScoped
        @BeforeDestroyed(UsagesAnn.class)
        @ConversationScoped
        @Default
        @Dependent
        @Destroyed(UsagesAnn.class)
        @Initialized(UsagesAnn.class)
        @Inject
        @Named
        @RequestScoped
        @SessionScoped
        @Singleton
        @Typed
        String field;

        @Nonbinding
        @Typed(UsagesAnn.class)
        void method(@TransientReference String param) {
        }
    }

    @BeforeDestroyed(RequestScoped.class)
    @Destroyed(RequestScoped.class)
    @Initialized(RequestScoped.class)
    @Qualifier
    @Specializes
    @Typed({ Usages.class, UsagesAnn.class })
    @Vetoed
    @interface UsagesAnn {
    }

    @Test
    public void testAgainstActualUsages() throws NoSuchFieldException, NoSuchMethodException {
        assertCorrect(getUsageFromField(Alternative.class), Alternative.Literal.INSTANCE);
        assertCorrect(getUsageFromField(Any.class), Any.Literal.INSTANCE);
        assertCorrect(getUsageFromField(ApplicationScoped.class), ApplicationScoped.Literal.INSTANCE);
        assertCorrect(getUsageFromField(BeforeDestroyed.class), BeforeDestroyed.Literal.of(UsagesAnn.class));
        assertCorrect(getUsageFromField(ConversationScoped.class), ConversationScoped.Literal.INSTANCE);
        assertCorrect(getUsageFromField(Default.class), Default.Literal.INSTANCE);
        assertCorrect(getUsageFromField(Dependent.class), Dependent.Literal.INSTANCE);
        assertCorrect(getUsageFromField(Destroyed.class), Destroyed.Literal.of(UsagesAnn.class));
        assertCorrect(getUsageFromField(Initialized.class), Initialized.Literal.of(UsagesAnn.class));
        assertCorrect(getUsageFromField(Inject.class), InjectLiteral.INSTANCE);
        assertCorrect(getUsageFromField(Named.class), NamedLiteral.INSTANCE);
        assertCorrect(getUsageFromField(RequestScoped.class), RequestScoped.Literal.INSTANCE);
        assertCorrect(getUsageFromField(SessionScoped.class), SessionScoped.Literal.INSTANCE);
        assertCorrect(getUsageFromField(Singleton.class), SingletonLiteral.INSTANCE);
        assertCorrect(getUsageFromField(Typed.class), Typed.Literal.INSTANCE);
        assertCorrect(getUsageFromMethod(Nonbinding.class), Nonbinding.Literal.INSTANCE);
        assertCorrect(getUsageFromMethod(Typed.class), Typed.Literal.of(new Class[] { UsagesAnn.class }));
        assertCorrect(getUsageFromMethodParam(TransientReference.class), TransientReference.Literal.INSTANCE);
        assertCorrect(getUsageFromAnnType(BeforeDestroyed.class), BeforeDestroyed.Literal.REQUEST);
        assertCorrect(getUsageFromAnnType(Destroyed.class), Destroyed.Literal.REQUEST);
        assertCorrect(getUsageFromAnnType(Initialized.class), Initialized.Literal.REQUEST);
        assertCorrect(getUsageFromAnnType(Qualifier.class), QualifierLiteral.INSTANCE);
        assertCorrect(getUsageFromAnnType(Specializes.class), Specializes.Literal.INSTANCE);
        assertCorrect(getUsageFromAnnType(Typed.class), Typed.Literal.of(new Class[] { Usages.class, UsagesAnn.class }));
        assertCorrect(getUsageFromAnnType(Vetoed.class), Vetoed.Literal.INSTANCE);
    }

    private <T extends Annotation> T getUsageFromField(Class<T> type) throws NoSuchFieldException {
        return Usages.class.getDeclaredField("field").getAnnotation(type);
    }

    private <T extends Annotation> T getUsageFromMethod(Class<T> type) throws NoSuchMethodException {
        return Usages.class.getDeclaredMethod("method", String.class).getAnnotation(type);
    }

    private <T extends Annotation> T getUsageFromMethodParam(Class<T> type) throws NoSuchMethodException {
        return Usages.class.getDeclaredMethod("method", String.class).getParameters()[0].getAnnotation(type);
    }

    private <T extends Annotation> T getUsageFromAnnType(Class<T> type) {
        return UsagesAnn.class.getAnnotation(type);
    }

    private <T extends Annotation> void assertCorrect(T usage, T literal) {
        assertEquals(usage, literal);
        assertEquals(literal, usage);
        assertEquals(literal.hashCode(), usage.hashCode());
    }
}
