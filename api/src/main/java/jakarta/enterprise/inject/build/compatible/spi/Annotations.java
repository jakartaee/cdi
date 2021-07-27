package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationAttribute;
import jakarta.enterprise.lang.model.AnnotationMember;
import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import java.lang.annotation.Annotation;
import java.util.List;

// TODO devise a builder-style API instead (see also AnnotationConfig)
public interface Annotations {
    AnnotationMember value(boolean value);

    AnnotationMember value(byte value);

    AnnotationMember value(short value);

    AnnotationMember value(int value);

    AnnotationMember value(long value);

    AnnotationMember value(float value);

    AnnotationMember value(double value);

    AnnotationMember value(char value);

    AnnotationMember value(String value);

    AnnotationMember value(Enum<?> enumValue);

    AnnotationMember value(Class<? extends Enum<?>> enumType, String enumValue);

    AnnotationMember value(ClassInfo<?> enumType, String enumValue);

    AnnotationMember value(Class<?> value);

    AnnotationMember annotationValue(Class<? extends Annotation> annotationType, AnnotationAttribute... attributes);

    AnnotationMember annotationValue(ClassInfo<?> annotationType, AnnotationAttribute... attributes);

    AnnotationMember annotationValue(AnnotationInfo annotation);

    AnnotationMember annotationValue(Annotation annotation);

    AnnotationAttribute attribute(String name, boolean value);

    AnnotationAttribute attribute(String name, byte value);

    AnnotationAttribute attribute(String name, short value);

    AnnotationAttribute attribute(String name, int value);

    AnnotationAttribute attribute(String name, long value);

    AnnotationAttribute attribute(String name, float value);

    AnnotationAttribute attribute(String name, double value);

    AnnotationAttribute attribute(String name, char value);

    AnnotationAttribute attribute(String name, String value);

    AnnotationAttribute attribute(String name, Enum<?> enumValue);

    AnnotationAttribute attribute(String name, Class<? extends Enum<?>> enumType, String enumValue);

    AnnotationAttribute attribute(String name, ClassInfo<?> enumType, String enumValue);

    AnnotationAttribute attribute(String name, Class<?> value);

    AnnotationAttribute arrayAttribute(String name, AnnotationMember... values);

    AnnotationAttribute arrayAttribute(String name, List<AnnotationMember> values);

    AnnotationAttribute annotationAttribute(String name, Class<? extends Annotation> annotationType,
            AnnotationAttribute... attributes);

    AnnotationAttribute annotationAttribute(String name, ClassInfo<?> annotationType, AnnotationAttribute... attributes);

    AnnotationAttribute annotationAttribute(String name, AnnotationInfo annotation);

    AnnotationAttribute annotationAttribute(String name, Annotation annotation);
}
