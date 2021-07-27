package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationMember;
import jakarta.enterprise.lang.model.AnnotationMemberValue;
import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import java.lang.annotation.Annotation;
import java.util.List;

// TODO devise a builder-style API instead (see also AnnotationConfig)
public interface Annotations {
    AnnotationMemberValue value(boolean value);

    AnnotationMemberValue value(byte value);

    AnnotationMemberValue value(short value);

    AnnotationMemberValue value(int value);

    AnnotationMemberValue value(long value);

    AnnotationMemberValue value(float value);

    AnnotationMemberValue value(double value);

    AnnotationMemberValue value(char value);

    AnnotationMemberValue value(String value);

    AnnotationMemberValue value(Enum<?> enumValue);

    AnnotationMemberValue value(Class<? extends Enum<?>> enumType, String enumValue);

    AnnotationMemberValue value(ClassInfo<?> enumType, String enumValue);

    AnnotationMemberValue value(Class<?> value);

    AnnotationMemberValue annotationValue(Class<? extends Annotation> annotationType, AnnotationMember... attributes);

    AnnotationMemberValue annotationValue(ClassInfo<?> annotationType, AnnotationMember... attributes);

    AnnotationMemberValue annotationValue(AnnotationInfo annotation);

    AnnotationMemberValue annotationValue(Annotation annotation);

    AnnotationMember attribute(String name, boolean value);

    AnnotationMember attribute(String name, byte value);

    AnnotationMember attribute(String name, short value);

    AnnotationMember attribute(String name, int value);

    AnnotationMember attribute(String name, long value);

    AnnotationMember attribute(String name, float value);

    AnnotationMember attribute(String name, double value);

    AnnotationMember attribute(String name, char value);

    AnnotationMember attribute(String name, String value);

    AnnotationMember attribute(String name, Enum<?> enumValue);

    AnnotationMember attribute(String name, Class<? extends Enum<?>> enumType, String enumValue);

    AnnotationMember attribute(String name, ClassInfo<?> enumType, String enumValue);

    AnnotationMember attribute(String name, Class<?> value);

    AnnotationMember arrayAttribute(String name, AnnotationMemberValue... values);

    AnnotationMember arrayAttribute(String name, List<AnnotationMemberValue> values);

    AnnotationMember annotationAttribute(String name, Class<? extends Annotation> annotationType,
                                         AnnotationMember... attributes);

    AnnotationMember annotationAttribute(String name, ClassInfo<?> annotationType, AnnotationMember... attributes);

    AnnotationMember annotationAttribute(String name, AnnotationInfo annotation);

    AnnotationMember annotationAttribute(String name, Annotation annotation);
}
