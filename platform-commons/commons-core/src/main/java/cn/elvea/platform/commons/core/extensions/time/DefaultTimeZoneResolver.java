package cn.elvea.platform.commons.core.extensions.time;

import java.time.ZoneId;
import java.util.TimeZone;

import static cn.elvea.platform.commons.core.constants.DateTimeConstants.ZONE_ID_DEFAULT;

/**
 * 默认时区解析器
 * 直接返回当前系统的默认时区。
 *
 * @author elvea
 * @since 0.0.1
 */
public class DefaultTimeZoneResolver implements TimeZoneResolver {

    /**
     * 用户时区
     */
    private final ZoneId userZoneId;

    /**
     * 系统时区
     */
    private final ZoneId systemZoneId;

    public DefaultTimeZoneResolver() {
        this(ZONE_ID_DEFAULT, ZONE_ID_DEFAULT);
    }

    public DefaultTimeZoneResolver(ZoneId userZoneId, ZoneId systemZoneId) {
        this.userZoneId = userZoneId;
        this.systemZoneId = systemZoneId;
    }

    /**
     * @see TimeZoneResolver#resolveUserZoneId()
     */
    @Override
    public ZoneId resolveUserZoneId() {
        return this.userZoneId;
    }

    /**
     * @see TimeZoneResolver#resolveUserTimeZone()
     */
    @Override
    public TimeZone resolveUserTimeZone() {
        return TimeZone.getTimeZone(resolveUserZoneId());
    }

    /**
     * @see TimeZoneResolver#resolveSystemZoneId()
     */
    @Override
    public ZoneId resolveSystemZoneId() {
        return this.systemZoneId;
    }

    /**
     * @see TimeZoneResolver#resolveSystemTimeZone()
     */
    @Override
    public TimeZone resolveSystemTimeZone() {
        return TimeZone.getTimeZone(resolveSystemZoneId());
    }

}
