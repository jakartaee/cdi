package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationAttribute;
import jakarta.enterprise.lang.model.AnnotationAttributeValue;
import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import java.lang.annotation.Annotation;
import java.util.List;

// TODO devise a builder-style API instead (see also AnnotationConfig)
public interface Annotations {
    AnnotationAttributeValue value(boolean value);

    AnnotationAttributeValue value(byte value);

    AnnotationAttributeValue value(short value);

    AnnotationAttributeValue value(int value);

    AnnotationAttributeValue value(long value);

    AnnotationAttributeValue value(float value);

    AnnotationAttributeValue value(double value);

    AnnotationAttributeValue value(char value);

    AnnotationAttributeValue value(String value);

    AnnotationAttributeValue value(Enum<?> enumValue);

    AnnotationAttributeValue value(Class<? extends Enum<?>> enumType, String enumValue);

    AnnotationAttributeValue value(ClassInfo<?> enumType, String enumValue);

    AnnotationAttributeValue value(Class<?> value);

    AnnotationAttributeValue annotationValue(Class<? extends Annotation> annotationType, AnnotationAttribute... attributes);

    AnnotationAttributeValue annotationValue(ClassInfo<?> annotationType, AnnotationAttribute... attributes);

    AnnotationAttributeValue annotationValue(AnnotationInfo annotation);

    AnnotationAttributeValue annotationValue(Annotation annotation);

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

    AnnotationAttribute arrayAttribute(String name, AnnotationAttributeValue... values);

    AnnotationAttribute arrayAttribute(String name, List<AnnotationAttributeValue> values);

    AnnotationAttribute annotationAttribute(String name, Class<? extends Annotation> annotationType,
            AnnotationAttribute... attributes);

    AnnotationAttribute annotationAttribute(String name, ClassInfo<?> annotationType, AnnotationAttribute... attributes);

    AnnotationAttribute annotationAttribute(String name, AnnotationInfo annotation);

    AnnotationAttribute annotationAttribute(String name, Annotation annotation);
}
