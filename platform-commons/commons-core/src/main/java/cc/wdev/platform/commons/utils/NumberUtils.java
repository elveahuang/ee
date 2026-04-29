package cc.wdev.platform.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

/**
 * @author elvea
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class NumberUtils {

    public static Long nvl(Long value) {
        return nvl(value, 0L);
    }

    public static Long nvl(Long value, Long defaultValue) {
        return isNull(value) ? defaultValue : value;
    }

    public static String toString(Number num) {
        if (num == null) {
            return null;
        }
        return String.valueOf(num);
    }

    public static Long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            log.warn("Invalid number format: {}", str);
            return null;
        }
    }

    public static Integer toInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            log.warn("Invalid number format: {}", str);
            return null;
        }
    }

}
