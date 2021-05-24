package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.declarations.ParameterInfo;

public interface DisposerInfo {
    MethodInfo<?> disposerMethod();

    ParameterInfo disposedParameter();
}
