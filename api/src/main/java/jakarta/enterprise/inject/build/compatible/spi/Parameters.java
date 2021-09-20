package jakarta.enterprise.inject.build.compatible.spi;

/**
 * A {@code String}-keyed parameter map. The parameter mappings are defined
 * by a synthetic component builder. The CDI container passes the map
 * to functions defined by the same synthetic component builder, whenever
 * it needs to invoke those functions. That is:
 *
 * <ul>
 * <li>a synthetic bean {@linkplain SyntheticBeanCreator creation} and
 *   {@linkplain SyntheticBeanDisposer destruction} function, defined by
 *   {@link SyntheticBeanBuilder};</li>
 * <li>a synthetic observer {@linkplain SyntheticObserver notification}
 *   function, defined by {@link SyntheticObserverBuilder}.</li>
 * </ul>
 *
 * Parameter values are transferred from the builder to the {@code Parameters}-accepting
 * function without a change. For example, if the builder defines an {@code int} parameter,
 * it must be looked up as {@code int} and cannot be looked up as {@code long}.
 * <p>
 * Values of primitive types may be looked up either using the primitive type (such as
 * {@code int.class}), or using the corresponding wrapper type ({@code Integer.class}).
 * The return value is always of the wrapper type.
 * <p>
 * Class-typed parameters are available as instances of {@link Class}, even if an instance
 * of {@code ClassInfo} was passed to the builder.
 * <p>
 * Similarly, annotation-typed parameters are always available as instances of the annotation
 * type, even if an instance of {@code AnnotationInfo} was passed to the builder.
 */
public interface Parameters {
    /**
     * Returns the parameter with given {@code key}. The value is expected to be of given type.
     *
     * @param key the parameter key; must not be {@code null}
     * @param type the parameter type; must not be {@code null}
     * @param <T> the parameter type
     * @return the parameter value, or {@code null} if parameter with given {@code key} does not exist
     * @throws ClassCastException if the parameter exists, but is of a different type
     */
    <T> T get(String key, Class<T> type);

    /**
     * Returns the parameter with given {@code key}. The value is expected to be of given type.
     * If the parameter does not exist, returns {@code defaultValue}.
     *
     * @param key the parameter key; must not be {@code null}
     * @param type the parameter type; must not be {@code null}
     * @param <T> the parameter type
     * @return the parameter value, or {@code defaultValue} if parameter with given {@code key} does not exist
     * @throws ClassCastException if the parameter exists, but is of a different type
     */
    <T> T get(String key, Class<T> type, T defaultValue);

    // TODO another possible design would be (with or without the methods accepting default values):
    //  - Boolean      getBoolean(String key)
    //  - boolean      getBoolean(String key, boolean defaultValue)
    //  - boolean[]    getBooleans(String key)
    //  - boolean[]    getBooleans(String key, boolean[] defaultValues)
    //  - Integer      getInt(String key)
    //  - int          getInt(String key, int defaultValue)
    //  - int[]        getInts(String key)
    //  - int[]        getInts(String key, int[] defaultValues)
    //  - Long         getLong(String key)
    //  - long         getLong(String key, long defaultValue)
    //  - long[]       getLongs(String key)
    //  - long[]       getLongs(String key, long[] defaultValues)
    //  - Double       getDouble(String key)
    //  - double       getDouble(String key, double defaultValue)
    //  - double[]     getDoubles(String key)
    //  - double[]     getDoubles(String key, double[] defaultValues)
    //  - String       getString(String key)
    //  - String       getString(String key, String defaultValue)
    //  - String[]     getStrings(String key)
    //  - String[]     getStrings(String key, String[] defaultValues)
    //  - Enum<?>      getEnum(String key)
    //  - Enum<?>      getEnum(String key, Enum<?> defaultValue)
    //  - Enum<?>[]    getEnums(String key)
    //  - Enum<?>[]    getEnums(String key, Enum<?>[] defaultValues)
    //  - Class<?>     getClass(String key) -- different name would be better, as java.lang.Object has getClass()
    //  - Class<?>     getClass(String key, Class<?> defaultValue)
    //  - Class<?>[]   getClasses(String key)
    //  - Class<?>[]   getClasses(String key, Class<?>[] defaultValues)
    //  - Annotation   getAnnotation(String key)
    //  - Annotation   getAnnotation(String key, Annotation defaultValue)
    //  - Annotation[] getAnnotations(String key)
    //  - Annotation[] getAnnotations(String key, Annotation[] defaultValues)
}
