package cn.elvea.platform.commons.core.constants;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface DateTimeConstants {

    Long EPOCH = 1672502400000L;

    String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    String DEFAULT_SIMPLE_TIME_PATTERN = "HH:mm";
    String DEFAULT_FULL_TIME_PATTERN = "HH:mm:ss.SSS";
    String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    String DEFAULT_SIMPLE_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    String DEFAULT_FULL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    ZoneId ZONE_ID_DEFAULT = ZoneId.systemDefault();
    ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
    ZoneId ZONE_ID_CHINA = ZoneId.of("+08:00");

    TimeZone TIME_ZONE_DEFAULT = TimeZone.getTimeZone(ZONE_ID_DEFAULT);
    TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone(ZONE_ID_UTC);
    TimeZone TIME_ZONE_CHINA = TimeZone.getTimeZone(ZONE_ID_CHINA);

    enum Pattern {
        DATE,
        DATE_TIME,
        FULL_DATE_TIME,
        SIMPLE_DATE_TIME,
        TIME,
        SIMPLE_TIME,
        FULL_TIME,
        NONE,
    }

}
