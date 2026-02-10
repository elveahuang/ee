package cc.wdev.platform.commons.utils;

import static java.util.Objects.isNull;

/**
 * @author elvea
 */
public abstract class NumberUtils {

    public static Long nvl(Long value) {
        return nvl(value, 0L);
    }

    public static Long nvl(Long value, Long defaultValue) {
        return isNull(value) ? defaultValue : value;
    }

}
