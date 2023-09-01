package cn.elvea.platform.commons.core.extensions.json.jackson;

import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.extensions.time.TimeZoneResolver;
import cn.elvea.platform.commons.core.utils.DateTimeUtils;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author elvea
 * @since 0.0.1
 */
public final class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> implements ContextualSerializer {

    private final TimeZoneResolver resolver;

    private String pattern;

    private boolean timeZoneConvert = false;

    public LocalDateTimeSerializer(TimeZoneResolver resolver) {
        super(LocalDateTime.class);
        this.resolver = resolver;
    }

    public LocalDateTimeSerializer(TimeZoneResolver resolver, String pattern, boolean timeZoneConvert) {
        this(resolver);
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (timeZoneConvert) {
            LocalDateTime targetLocalDateTime = DateTimeUtils.transfer(localDateTime, resolver.resolveSystemZoneId(), resolver.resolveUserZoneId());
            generator.writeString(DateTimeUtils.format(targetLocalDateTime, getFormatter()));
        } else {
            generator.writeString(DateTimeUtils.format(localDateTime, DateTimeConstants.Pattern.DATE_TIME));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
        if (!ObjectUtils.isEmpty(property)) {
            com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(provider, property, handledType());
            JsonFormat annotation = property.getAnnotation(JsonFormat.class);
            if (annotation != null) {
                return new LocalDateTimeSerializer(resolver, annotation.pattern(), annotation.timeZoneConvert());
            }
        }
        return new LocalDateTimeSerializer(resolver);
    }

    private DateTimeFormatter getFormatter() {
        String pattern = StringUtils.isNotEmpty(this.pattern) ? this.pattern : DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;
        DateTimeFormatter formatter = null;
        if (StringUtils.isNotEmpty(this.pattern)) {
            formatter = DateTimeFormatter.ofPattern(this.pattern);
        }
        return formatter;
    }

}
