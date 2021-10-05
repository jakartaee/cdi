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
 * The return value is always of the wrapper type, so that {@code null} can be returned
 * when the parameter does not exist. Note that this does not apply to arrays
 * of primitive types; an {@code int[]} cannot be looked up as {@code Integer[]}.
 * This is because arrays are reference types and so {@code null} can be returned.
 * <p>
 * Class-typed parameters are available as instances of {@link Class}, even if an instance
 * of {@code ClassInfo} was passed to the builder.
 * <p>
 * Annotation-typed parameters are available as instances of the annotation type,
 * even if an instance of {@code AnnotationInfo} was passed to the builder.
 */
public interface Parameters {
    /**
     * Returns the value of a parameter with given {@code key}. The value is expected to be of given {@code type}.
     *
     * @param key the parameter key; must not be {@code null}
     * @param type the parameter type; must not be {@code null}
     * @param <T> the parameter type
     * @return the parameter value, or {@code null} if parameter with given {@code key} does not exist
     * @throws ClassCastException if the parameter exists, but is of a different type
     */
    <T> T get(String key, Class<T> type);

    /**
     * Returns the value of a parameter with given {@code key}. The value is expected to be of given {@code type}.
     * If the parameter does not exist, returns {@code defaultValue}.
     *
     * @param key the parameter key; must not be {@code null}
     * @param type the parameter type; must not be {@code null}
     * @param defaultValue the value to return if parameter with given {@code key} does not exist
     * @param <T> the parameter type
     * @return the parameter value, or {@code defaultValue} if parameter with given {@code key} does not exist
     * @throws ClassCastException if the parameter exists, but is of a different type
     */
    <T> T get(String key, Class<T> type, T defaultValue);
}
