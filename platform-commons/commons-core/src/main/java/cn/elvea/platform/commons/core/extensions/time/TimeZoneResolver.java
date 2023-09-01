package cn.elvea.platform.commons.core.extensions.time;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface TimeZoneResolver {

    /**
     * 获取用户时区
     */
    ZoneId resolveUserZoneId();

    /**
     * 获取用户时区
     */
    TimeZone resolveUserTimeZone();

    /**
     * 获取系统时区
     */
    ZoneId resolveSystemZoneId();

    /**
     * 获取系统时区
     */
    TimeZone resolveSystemTimeZone();

}
