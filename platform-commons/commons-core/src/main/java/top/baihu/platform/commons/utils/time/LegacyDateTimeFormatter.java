package top.baihu.platform.commons.utils.time;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import top.baihu.platform.commons.constants.DateTimeConstants;
import top.baihu.platform.commons.utils.DateUtils;
import top.baihu.platform.commons.utils.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * LegacyDateTimeFormatter
 *
 * @author elvea
 */
@Setter
@Getter
@Slf4j
public class LegacyDateTimeFormatter implements Formatter<Date> {

    private TimeZoneResolver resolver;

    private Object source;

    private String pattern;

    private boolean timeZoneConvert = false;

    @Override
    public Date parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        log.info("LegacyDateTimeFormatter.parse - text {}", text);
        String pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.isNotEmpty(this.pattern)) {
            pattern = this.pattern;
        }
        if (timeZoneConvert) {
            return DateUtils.parse(text, pattern, resolver.resolveUserTimeZone());
        } else {
            return DateUtils.parse(text, pattern);
        }
    }

    @Override
    public String print(@NonNull Date date, @NonNull Locale locale) {
        log.info("LegacyDateTimeFormatter.print - date {}.", date);
        String pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.isNotEmpty(this.pattern)) {
            pattern = this.pattern;
        }
        if (timeZoneConvert) {
            return DateUtils.format(date, pattern, resolver.resolveUserTimeZone());
        } else {
            return DateUtils.format(date, pattern);
        }
    }

}
