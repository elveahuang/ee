package cn.elvea.platform.commons.core.extensions.time;

import cn.elvea.platform.commons.core.annotations.DateTimeFormat;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.lang.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 传统的日期时间处理器
 *
 * @author elvea
 * @since 0.0.1
 */
public class LegacyDateTimeAnnotationFormatterFactory extends EmbeddedValueResolutionSupport
        implements AnnotationFormatterFactory<DateTimeFormat> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        FIELD_TYPES = Set.of(Date.class, Calendar.class);
    }

    private final TimeZoneResolver resolver;

    public LegacyDateTimeAnnotationFormatterFactory(TimeZoneResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    @NonNull
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    @NonNull
    public Printer<?> getPrinter(@NonNull DateTimeFormat annotation, @NonNull Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    @Override
    @NonNull
    public Parser<?> getParser(@NonNull DateTimeFormat annotation, @NonNull Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    protected Formatter<Date> getFormatter(DateTimeFormat annotation, Class<?> fieldType) {
        LegacyDateTimeFormatter formatter = new LegacyDateTimeFormatter();
        formatter.setResolver(resolver);
        formatter.setSource(annotation);
        formatter.setPattern(annotation.pattern());
        formatter.setTimeZoneConvert(annotation.timeZoneConvert());
        return formatter;
    }

}
