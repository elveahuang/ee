package cn.elvea.platform.commons.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import static cn.elvea.platform.commons.core.constants.DateTimeConstants.*;
import static java.time.temporal.ChronoField.MILLI_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class DateTimeUtils {

    public static final DateTimeFormatter TIME_FORMATTER;
    public static final DateTimeFormatter SIMPLE_TIME_FORMATTER;
    public static final DateTimeFormatter FULL_TIME_FORMATTER;
    public static final DateTimeFormatter DATE_FORMATTER;
    public static final DateTimeFormatter DATE_TIME_FORMATTER;
    public static final DateTimeFormatter SIMPLE_DATE_TIME_FORMATTER;
    public static final DateTimeFormatter FULL_DATE_TIME_FORMATTER;

    static {

        TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DEFAULT_TIME_PATTERN).toFormatter();

        SIMPLE_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DEFAULT_SIMPLE_TIME_PATTERN)
                .parseDefaulting(SECOND_OF_MINUTE, 1)
                .toFormatter();

        FULL_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DEFAULT_FULL_TIME_PATTERN).toFormatter();

        DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_DATE_TIME_PATTERN)
                .parseDefaulting(MILLI_OF_SECOND, 0)
                .toFormatter();

        SIMPLE_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_SIMPLE_DATE_TIME_PATTERN)
                .parseDefaulting(SECOND_OF_MINUTE, 0)
                .parseDefaulting(MILLI_OF_SECOND, 0)
                .toFormatter();

        FULL_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DEFAULT_FULL_DATE_TIME_PATTERN).toFormatter();

        DATE_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DEFAULT_DATE_PATTERN).toFormatter();

    }

    public static final Map<Pattern, String> DATE_TIME_PATTERNS;

    static {
        Map<Pattern, String> formats = new EnumMap<>(Pattern.class);
        formats.put(Pattern.DATE, DEFAULT_DATE_PATTERN);
        formats.put(Pattern.DATE_TIME, DEFAULT_DATE_TIME_PATTERN);
        formats.put(Pattern.FULL_DATE_TIME, DEFAULT_FULL_DATE_TIME_PATTERN);
        formats.put(Pattern.SIMPLE_DATE_TIME, DEFAULT_SIMPLE_DATE_TIME_PATTERN);
        formats.put(Pattern.TIME, DEFAULT_TIME_PATTERN);
        formats.put(Pattern.SIMPLE_TIME, DEFAULT_SIMPLE_TIME_PATTERN);
        DATE_TIME_PATTERNS = Collections.unmodifiableMap(formats);
    }

    /**
     * 转换时区
     */
    public static LocalDateTime transfer(LocalDateTime localDateTime, ZoneId targetZoneId) {
        return transfer(localDateTime, ZONE_ID_DEFAULT, targetZoneId);
    }

    /**
     * 转换时区
     */
    public static LocalDateTime transfer(LocalDateTime localDateTime, ZoneId fromZoneId, ZoneId targetZoneId) {
        return localDateTime.atZone(fromZoneId).withZoneSameInstant(targetZoneId).toLocalDateTime();
    }

    /**
     * 格式化日期
     */
    public static <E extends TemporalAccessor> String format(E e, String patten) {
        return format(e, createFormatter(patten));
    }

    /**
     * 格式化日期
     */
    public static <E extends TemporalAccessor> String format(E e, Pattern pattern) {
        return format(e, getFormatter(pattern));
    }

    /**
     * 格式化日期
     */
    public static <E extends TemporalAccessor> String format(E e, DateTimeFormatter formatter) {
        return formatter.format(e);
    }

    /**
     * 解析日期
     */
    public static <T extends TemporalAccessor> T parse(String text, String pattern, Class<T> classType) {
        return parse(text, createFormatter(pattern), classType);
    }

    /**
     * 解析日期
     */
    public static <T extends TemporalAccessor> T parse(String text, Pattern pattern, Class<T> classType) {
        return parse(text, getFormatter(pattern), classType);
    }

    /**
     * 解析日期
     */
    @SuppressWarnings("unchecked")
    public static <T extends TemporalAccessor> T parse(String text, DateTimeFormatter formatter, Class<T> classType) {
        if (LocalDate.class == classType) {
            return (T) LocalDate.parse(text, formatter);
        } else if (LocalTime.class == classType) {
            return (T) LocalTime.parse(text, formatter);
        } else if (LocalDateTime.class == classType) {
            return (T) LocalDateTime.parse(text, formatter);
        } else if (ZonedDateTime.class == classType) {
            return (T) ZonedDateTime.parse(text, formatter);
        } else if (OffsetDateTime.class == classType) {
            return (T) OffsetDateTime.parse(text, formatter);
        } else if (OffsetTime.class == classType) {
            return (T) OffsetTime.parse(text, formatter);
        } else {
            log.error("DateTimeUtils parse failed. text [{}].", text);
            throw new IllegalStateException("Unsupported TemporalAccessor type: " + classType);
        }
    }

    /**
     * 获取预设的解析器
     */
    public static DateTimeFormatter getFormatter(Pattern dateTimePattern) {
        return switch (dateTimePattern) {
            case DATE -> DATE_FORMATTER;
            case DATE_TIME -> DATE_TIME_FORMATTER;
            case SIMPLE_DATE_TIME -> SIMPLE_DATE_TIME_FORMATTER;
            case FULL_DATE_TIME -> FULL_DATE_TIME_FORMATTER;
            case TIME -> TIME_FORMATTER;
            case SIMPLE_TIME -> SIMPLE_TIME_FORMATTER;
            case FULL_TIME -> FULL_TIME_FORMATTER;
            default -> throw new IllegalStateException("Unsupported pattern: " + dateTimePattern);
        };
    }

    public static DateTimeFormatter createFormatter(String pattern) {
        Objects.requireNonNull(pattern, "pattern");
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date != null) {
            return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID_DEFAULT);
        }
        return null;
    }

    public static LocalDateTime toLocalDateTime(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    public static LocalTime toLocalTime(Date date) {
        return toLocalDateTime(date).toLocalTime();
    }

}
