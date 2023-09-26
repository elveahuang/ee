package cn.elvea.platform.commons.core.utils;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class ObjectUtils extends org.springframework.util.ObjectUtils {

    /**
     * 如果第一个参数为空，则返回第二个参数
     */
    public static <T> T nvl(final T object, final T defaultObject) {
        return isEmpty(object) ? defaultObject : object;
    }

    /**
     *
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 instanceof Number n1 && o2 instanceof Number n2) {
            return n1.longValue() == n2.longValue();
        }
        return nullSafeEquals(o1, o2);
    }

}
