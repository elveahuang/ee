package cc.elvea.platform.commons.enums;

import lombok.Getter;

import java.time.ZoneId;

/**
 * @author elvea
 */
@Getter
public enum TimeZoneEnum {
    UTC_M_12("UTC -12:00", "西十二区", ZoneId.of("-12:00")),
    UTC_M_11("UTC -11:00", "西十一区", ZoneId.of("-11:00")),
    UTC_M_10("UTC -10:00", "西十区", ZoneId.of("-10:00")),
    UTC_M_09("UTC -09:00", "西九区", ZoneId.of("-09:00")),
    UTC_M_08("UTC -08:00", "西八区", ZoneId.of("-08:00")),
    UTC_M_07("UTC -07:00", "西七区", ZoneId.of("-07:00")),
    UTC_M_06("UTC -06:00", "西六区", ZoneId.of("-06:00")),
    UTC_M_05("UTC -05:00", "西五区", ZoneId.of("-05:00")),
    UTC_M_04("UTC -04:00", "西四区", ZoneId.of("-04:00")),
    UTC_M_03("UTC -03:00", "西三区", ZoneId.of("-03:00")),
    UTC_M_02("UTC -02:00", "西二区", ZoneId.of("-02:00")),
    UTC_M_01("UTC -01:00", "西一区", ZoneId.of("-01:00")),
    UTC("UTC +00:00", "UTC", ZoneId.of("+00:00")),
    UTC_P_01("UTC +01:00", "东一区", ZoneId.of("+01:00")),
    UTC_P_02("UTC +02:00", "东二区", ZoneId.of("+02:00")),
    UTC_P_03("UTC +03:00", "东三区", ZoneId.of("+03:00")),
    UTC_P_04("UTC +04:00", "东四区", ZoneId.of("+04:00")),
    UTC_P_05("UTC +05:00", "东五区", ZoneId.of("+05:00")),
    UTC_P_06("UTC +06:00", "东六区", ZoneId.of("+06:00")),
    UTC_P_07("UTC +07:00", "东七区", ZoneId.of("+07:00")),
    UTC_P_08("UTC +08:00", "东八区", ZoneId.of("+08:00")),
    UTC_P_09("UTC +09:00", "东九区", ZoneId.of("+09:00")),
    UTC_P_10("UTC +10:00", "东十区", ZoneId.of("+10:00")),
    UTC_P_11("UTC +11:00", "东十一区", ZoneId.of("+11:00")),
    UTC_P_12("UTC +12:00", "东十二区", ZoneId.of("+12:00"));

    private final String title;
    private final String description;
    private final ZoneId zoneId;

    TimeZoneEnum(final String title, final String description, final ZoneId zoneId) {
        this.title = title;
        this.description = description;
        this.zoneId = zoneId;
    }

}
