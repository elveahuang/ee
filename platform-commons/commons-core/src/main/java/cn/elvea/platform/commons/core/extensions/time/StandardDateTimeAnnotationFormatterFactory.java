package cn.elvea.platform.commons.core.extensions.time;

import cn.elvea.platform.commons.core.annotations.DateTimeFormat;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.lang.NonNull;

import java.time.*;
import java.time.temporal.TemporalAccessor;
import java.util.Set;

/**
 * 针对JDK1.8以后新的日期时间处理器
 *
 * @author elvea
 * @since 0.0.1
 */
public class StandardDateTimeAnnotationFormatterFactory extends EmbeddedValueResolutionSupport
        implements AnnotationFormatterFactory<DateTimeFormat> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        FIELD_TYPES = Set.of(
                LocalDate.class,
                LocalTime.class,
                LocalDateTime.class,
                ZonedDateTime.class,
                OffsetDateTime.class,
                OffsetTime.class
        );
    }

    private final TimeZoneResolver resolver;

    public StandardDateTimeAnnotationFormatterFactory(TimeZoneResolver resolver) {
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
        return this.getFormatter(annotation, fieldType);
    }

    @Override
    @NonNull
    public Parser<?> getParser(@NonNull DateTimeFormat annotation, @NonNull Class<?> fieldType) {
        return this.getFormatter(annotation, fieldType);
    }

    @SuppressWarnings("unchecked")
    protected Formatter<? extends TemporalAccessor> getFormatter(DateTimeFormat annotation, Class<?> fieldType) {
        StandardDateTimeFormatter formatter = new StandardDateTimeFormatter(this.resolver, (Class<? extends TemporalAccessor>) fieldType);
        formatter.setPattern(annotation.pattern());
        formatter.setTimeZoneConvert(annotation.timeZoneConvert());
        return formatter;
    }

}
