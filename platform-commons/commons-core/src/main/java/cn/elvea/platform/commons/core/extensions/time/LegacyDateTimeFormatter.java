package cn.elvea.platform.commons.core.extensions.time;

import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.utils.DateUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * LegacyDateTimeFormatter
 *
 * @author elvea
 * @since 0.0.1
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
        log.debug("LegacyDateTimeFormatter.parse - text {}", text);
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
        log.debug("LegacyDateTimeFormatter.print - date {}.", date);
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
